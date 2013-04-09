<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/clfw/clfw.jsp"%>
<%@ include file="/widgets/jquery/multiselect/multiselect.jsp"%>
<html>

	<head>
		<title>揽投部频次配置</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var flag='Add';
			$(document).ready(function(){
				viewcity();
				$("#WHDIS").multiselect2side({
					selectedPosition: 'right',
					moveOptions: false,
					labelsx: '待选区',
					labeldx: '已选区'
			    });
				
				$("#fliphelpct").click(function(){
					$("#fliphelpcon").slideToggle("fast");
				});
			})
			//展示市
			function viewcity(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var url = '${ctx}/clfw/b09r03clfwoption!citys.action';
				var params = {
					PROVINCE_NAME: tPROVINCE_NAME
				 };
				if(isMunicipalities(tPROVINCE_NAME)){
			    	$('#CITY_NAMES').hide();
					$('#CITY_NAMES option').remove();
			    }else{
					$('#CITY_NAMES').show();
				}
				jQuery.post(url, params, callbackcity, 'json');
			}
			function callbackcity(data){
				var d=data.districtBeans;
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				if(!isMunicipalities(tPROVINCE_NAME)){
					$('#CITY_NAMES option').remove();
					for(var i=0;i<d.length;i++){
						$("#CITY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
			    }
				viewCLFWPC_NAMES();
			}
			function viewCLFWPC_NAMES(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				if(isMunicipalities(tPROVINCE_NAME)){
					var tCITY_ID=tPROVINCE_ID;
			    }else{
					var tCITY_ID=$("#CITY_NAMES").val();
				}
				var tCLFWPC_SX=$("input[name='CLFWPC_SX'][checked]").val();
				var url = '${ctx}/clfw/b09r03clfwoption!clfwpcsshi.action';
				var params = {
						CITY_NAME:tCITY_ID,
						CLFWPC_SX:tCLFWPC_SX
				 };
				jQuery.post(url, params, callbackCLFWPC_NAMES, 'json');
			}
			function callbackCLFWPC_NAMES(data){
				$('#CLFWPC_NAMES option').remove();
				var d=data.clfwpcsshiBeans;
				$("#CLFWPC_NAMES").append("<option value=''>请选择</option>"); 
				if(d.length>0){
					for(var i=0;i<d.length;i++){
						$("#CLFWPC_NAMES").append("<option value='" + d[i].CLFWPCSHI_SEQID + "'>" + d[i].CLFWPCSHI_SHIAGM + "</option>");
					}
				}
				viewwhdistrictl();
			}
			function viewwhdistrictl(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				if(isMunicipalities(tPROVINCE_NAME)){
					var tCITY_ID=tPROVINCE_ID;
			    }else{
					var tCITY_ID=$("#CITY_NAMES").val();
				}
				var tCLFWPC_NAMES=$("#CLFWPC_NAMES").val();
				if(tCLFWPC_NAMES.length>0){
					var url = '${ctx}/clfw/b09r03clfwoption!whclfwtdbl.action';
					var params = {
							CITY_NAME:tCITY_ID,
							CLFWPC_NAMES:tCLFWPC_NAMES
					 };
					jQuery.post(url, params, callbackviewwhdistrictl, 'json');
				}else{
					$('#WHDISms2side__dx option').remove();
					$('#WHDISms2side__sx option').remove();
				}
			}
			function callbackviewwhdistrictl(data){
				$('#WHDISms2side__dx option').remove();
				$('#WHDISms2side__sx option').remove();
				var d=data.whclfwtdbBeans;
				if(d.length>0){
					for(var i=0;i<d.length;i++){
						$("#WHDISms2side__sx").append("<option value='" + d[i].DM_PK_CODE + "'>" + d[i].DM_NAME + "</option>");
					}
				}
			}
			function ajaxAddUpAlert(tflag){
				flag = tflag;
				var tCLFWPC_NAMES=$("#CLFWPC_NAMES").val();
				if(tCLFWPC_NAMES.length<1){
					$.prompt('请选择频次',{buttons: {'确定': true}});
					return false;
				}
				$("#WHDISms2side__dx").children().each(function(){$(this).attr("selected","selected")});
				var tWHDIS = $("#WHDISms2side__dx").val();
				if(tWHDIS==null){
					$.prompt('请选择该频次揽投部名称',{buttons: {'确定': true}});
					return false;
				}
				if(flag=='Add'){
					ajaxAddUp(flag);
				}else{
					$.prompt('该操作将修改该频次揽投部名称！确认执行操作？',{
						buttons: {'确定': true, '取消': false },
						callback: function(v){
							if(v){
								ajaxAddUp(flag);
							}
						}
					});
				}
			}
			function ajaxAddUp(flag){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				if(isMunicipalities(tPROVINCE_NAME)){
					var tCITY_ID=tPROVINCE_ID;
			    }else{
					var tCITY_ID=$("#CITY_NAMES").val();
				}
				var tCLFWPC_NAMES=$("#CLFWPC_NAMES").val();
				var tCLFWTDB_CLSX=$("input[name='CLFWTDB_CLSX'][checked]").val();
				$("#WHDISms2side__dx").children().each(function(){$(this).attr("selected","selected")});
				var tWHDIS = $("#WHDISms2side__dx").val();
				tWHDIS = "(" + tWHDIS + ")";
				var params = {
						CLFWTDB_DISTCD:tCITY_ID,
						CLFWPCSHI_SEQID:tCLFWPC_NAMES,
						CLFWTDB_CLSX:tCLFWTDB_CLSX,
						DM_PK_CODE:tWHDIS
	            };
				if(flag=='Add'){
					var url = '${ctx}/clfw/b09cud02clfwconfig!addclfwtdb.action';
				}else{
					var url = '${ctx}/clfw/b09cud02clfwconfig!upclfwtdb.action';
				}
				$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
			    jQuery.post(url, params, callajaxAdd, 'json');
			}
			function callajaxAdd(data){
				$('#WHDISms2side__dx option').remove();
				$.prompt(data.saveMessage,{buttons: {'确定': true}});
				$.unblockUI();
			}
			function backQuery(flag){
				window.returnValue=flag;
				window.opener =null;
				window.close();
			}
		</script>
	</head>
	<body>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" align="left">
				<tr>
					<td width="17" valign="top"
						background="${ctx}/images/mail_leftbg.gif">
						<img src="${ctx}/images/left-top-right.gif" width="17" height="29" />
					</td>
					<td valign="top" background="${ctx}/images/content-bg.gif">
						<table width="100%" height="31" border="0" cellpadding="0"
							cellspacing="0" class="left_topbg" id="table2">
							<tr>
								<td height="31">
									<div class="titlebt">
										揽投部频次配置
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<a id="addquery" href='#' onClick="ajaxAddUpAlert('Add')"  style="font-size:12px">新增</a>
								<a id="queryback" href='#' onClick="backQuery('fresh')"  style="font-size:12px">返回</a>
								</div>	
								</td>
							</tr>
						</table>
					</td>
					<td width="16" valign="top"
						background="${ctx}/images/mail_rightbg.gif">
						<img src="${ctx}/images/nav-right-bg.gif" width="16" height="29" />
					</td>
				</tr>
				<tr>
					<td valign="middle" background="${ctx}/images/mail_leftbg.gif">&nbsp;
						
					</td>
					<td valign="top" bgcolor="#F7F8F9" align="center">
						<table>
							<tr>
								<td>
									<div id="querydiv">
										<table class="listtable" id="viewquery_table"  width="500">
											<tr>
												<td class="listtablehead"  colspan="4">
													<font id="fliphelpcon" color="red">
														* 可以在本界面,连续维护数据
													</font>
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													行政区域:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity();"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewCLFWPC_NAMES();"  style="width:100"></select>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="140">
													揽收/投递频次属性:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<input onclick="viewCLFWPC_NAMES();" type="radio" name="CLFWPC_SX" id="CLFWPC_SX" value="2" checked="checked">揽收
													<input onclick="viewCLFWPC_NAMES();" type="radio" name="CLFWPC_SX" id="CLFWPC_SX" value="3">投递
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													市频次名称:
												</td>
												<td class="js_left_txt" id="td_privince_content"  colspan="3">
													<select class="simple" id="CLFWPC_NAMES" onChange="viewwhdistrictl();" style="width:300"></select>
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													服务属性：
												</td>
												<td class="js_left_txt"  colspan="3">
													<input type="radio" name="CLFWTDB_CLSX" id="CLFWTDB_CLSX" value="1" checked="checked">全是
													<input style="display:none" type="radio" name="CLFWTDB_CLSX" id="CLFWTDB_CLSX" value="0"><label style="display:none">全否</label>
													<input style="display:none" type="radio" name="CLFWTDB_CLSX" id="CLFWTDB_CLSX" value="2"><label style="display:none">部分</label>
												</td>
											</tr>
											<tr id="trans_add">
												<td class="listtablehead" colspan="4">
													揽投部:
													<select name="WHDIS" id='WHDIS' multiple='multiple' size='16'>
												 	</select>
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</td>
					<td valign="middle" background="${ctx}/images/mail_rightbg.gif">&nbsp;
						
					</td>
				</tr>
				<tr>
					<td valign="bottom" background="${ctx}/images/mail_leftbg.gif">
						<img src="${ctx}/images/buttom_left2.gif" width="17" height="17" />
					</td>
					<td background="${ctx}/images/buttom_bgs.gif">
						<img src="${ctx}/images/buttom_bgs.gif" width="17" height="17">
					</td>
					<td valign="bottom" background="${ctx}/images/mail_rightbg.gif">
						<img src="${ctx}/images/buttom_right2.gif" width="16" height="17" />
					</td>
				</tr>
			</table>
	</body>
</html>
