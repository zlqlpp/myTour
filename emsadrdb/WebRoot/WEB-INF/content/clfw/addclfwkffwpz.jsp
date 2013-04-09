<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/clfw/clfw.jsp"%>
<html>

	<head>
		<title>客户服务范围配置</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var flag='Add';
			$(document).ready(function(){
				
				if('${clfwBean.CLFWKFFW_SEQID}' != ''){
					
					var pnt = '${clfwBean.CLFWKFFW_DISTCD}';
					
					$("#PROVINCE_NAMES").val(pnt.substr(0,2)+'0000');


					if('${clfwBean.CLFWKFFW_YTEFW}' == '全境不纳入'){
						$("input[name='CLFWKFFW_YTEFW'][value='0']").attr("checked",'checked');
					}else if('${clfwBean.CLFWKFFW_YTEFW}' == '全境纳入'){
						$("input[name='CLFWKFFW_YTEFW'][value='1']").attr("checked",'checked');
					}else if('${clfwBean.CLFWKFFW_YTEFW}' == '部分纳入'){
						$("input[name='CLFWKFFW_YTEFW'][value='2']").attr("checked",'checked');
					}
					
					
					
					$("#upquery").show();
					$("#addquery").hide();
					
				}else{
					
					$("#upquery").hide();
					$("#addquery").show();
					
				}
				
				
				viewCLFWKFFW_YTEFW();
				
				viewcity();
				
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
				if(isMunicipalities(tPROVINCE_NAME)){
					$('#COUNTY_NAMES option').remove();
					$("#COUNTY_NAMES").prepend("<option selected='selected' value=''>请选择</option>");
			    	for(var i=0;i<d.length;i++){
						$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
			    	$("#COUNTY_NAMES").val('${clfwBean.CLFWKFFW_DISTCD}');
			    }else{
					$('#CITY_NAMES option').remove();
					for(var i=0;i<d.length;i++){
						$("#CITY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}

					if('${clfwBean.CLFWKFFW_SEQID}' != ''){
						
						var pnt = '${clfwBean.CLFWKFFW_DISTCD}';
	
						$("#CITY_NAMES").val(pnt.substr(0,4)+'00');
						
						if(!$("#CITY_NAMES").val().length<1){
							
							$("#CITY_NAMES").val(pnt);
							
						}

					}
					
					viewcounty();
			    }
				
			}
			function viewcounty(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 			
				if(!isMunicipalities(tPROVINCE_NAME)){
					var tCITY_NAME=$("#CITY_NAMES").find("option:selected").text(); 
					if(tCITY_NAME!='请选择'){
						var url = '${ctx}/clfw/b09r03clfwoption!countys.action';
						var params = {
							CITY_NAME: tCITY_NAME
						 };
						jQuery.post(url, params, callbackcounty, 'json');
					}
				}
			}
			function callbackcounty(data){
				$('#COUNTY_NAMES option').remove();
				var d=data.districtBeans;
				$("#COUNTY_NAMES").prepend("<option selected='selected' value=''>请选择</option>");
				for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
				}
				$("#COUNTY_NAMES").val('${clfwBean.CLFWKFFW_DISTCD}');
			}
			function ajaxAddUpAlert(tflag){
				flag = tflag;

				if(flag=='Add'){
					ajaxAddUp(flag);
				}else{
					$.prompt('该操作将修改此客户服务配置！确认执行操作？',{
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
				
				var tCOUNTY_ID = $("#COUNTY_NAMES").val();
				if(tCOUNTY_ID.length < 1){
					var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
					var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
					if(isMunicipalities(tPROVINCE_NAME)){
						tCOUNTY_ID=tPROVINCE_ID;
				    }else{
				    	tCOUNTY_ID=$("#CITY_NAMES").val();
					}
				}
				
				var tCLFWKFFW_YTEFW=$("input[name='CLFWKFFW_YTEFW'][checked]").val();

				if(tCLFWKFFW_YTEFW == '0'){
					tCLFWKFFW_YTEFW = '全境不纳入'
				}else if(tCLFWKFFW_YTEFW == '1'){
					tCLFWKFFW_YTEFW = '全境纳入'
				}else if(tCLFWKFFW_YTEFW == '2'){
					tCLFWKFFW_YTEFW = '部分纳入'
				}

				var tCLFWKFFW_SWSFW=$('#CLFWKFFW_SWSFW').val();
				
				if(tCLFWKFFW_SWSFW.length>1500){
					$.prompt('未纳入核心服务范围描述字符大于1500',{buttons: {'确定': true}});
					return false;
				}

				var tCLFWKFFW_SEQID=$('#CLFWKFFW_SEQID').val();
				
				var params = {
						CLFWKFFW_SEQID:tCLFWKFFW_SEQID,
						CLFWKFFW_DISTCD:tCOUNTY_ID,
						CLFWKFFW_YTEFW:tCLFWKFFW_YTEFW,
						CLFWKFFW_SWSFW:tCLFWKFFW_SWSFW
	            };
				if(flag=='Add'){
					var url = '${ctx}/clfw/b09cud02clfwconfig!addclfwkffw.action';
				}else{
					var url = '${ctx}/clfw/b09cud02clfwconfig!upclfwkffw.action';
				}
				$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
			    jQuery.post(url, params, callajaxAdd, 'json');
			}
			function callajaxAdd(data){
				$.prompt(data.saveMessage,{buttons: {'确定': true}});
				$.unblockUI();
			}
			function backQuery(flag){
				window.returnValue=flag;
				window.opener =null;
				window.close();
			}
			function viewCLFWKFFW_YTEFW(){
				
				var tCLFWKFFW_YTEFW=$("input[name='CLFWKFFW_YTEFW'][checked]").val();
				
				if(tCLFWKFFW_YTEFW == '2'){
					$('#trCLFWKFFW_SWSFW').show();
				}else{
					$('#trCLFWKFFW_SWSFW').hide();
					$('#CLFWKFFW_SWSFW').val('');
				}
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
										客户服务范围配置
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<input id="CLFWKFFW_SEQID" type="hidden" value="${clfwBean.CLFWKFFW_SEQID}"/>
								<a id="upquery" href='#' onClick="ajaxAddUpAlert('Up')"  style="font-size:12px">修改</a>
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
										<table class="listtable" id="viewquery_table"  width="100%">
											<tr>
												<td class="listtablehead"  colspan="2">
													<font id="fliphelpcon" color="red">
														* 可以在本界面,连续维护数据
														<br>* 【未纳入核心服务范围描述】字数要小于1500字
														<br>“核心服务范围配置”维护分三种情况，一是同一行政区全境纳入核心服务范围，直接勾选即可；二是同一行政区全境不纳入核心服务范围，直接勾选即可；三是同一个行政区部分地区纳入核心服务范围，各地市务必要把未纳入核心服务范围的地区按地址描述说明清楚，细化到街道、门牌，不能让客户产生歧义。
													</font>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="30%">
													行政区域选择:
												</td>
												<td class="js_left_txt" id="td_privince_content"  width="70%">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity();"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty()" style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES" style="width:100"></select>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="150">
													纳入核心服务范围描述:
												</td>
												<td class="js_left_txt" id="td_privince_content">
													<input onclick="viewCLFWKFFW_YTEFW();" type="radio" name="CLFWKFFW_YTEFW" id="CLFWKFFW_YTEFW" value="1" checked="checked">全境纳入
													<input onclick="viewCLFWKFFW_YTEFW();" type="radio" name="CLFWKFFW_YTEFW" id="CLFWKFFW_YTEFW" value="0">全境不纳入
													<input onclick="viewCLFWKFFW_YTEFW();" type="radio" name="CLFWKFFW_YTEFW" id="CLFWKFFW_YTEFW" value="2">部分纳入
												</td>
											</tr>
											<tr id="trCLFWKFFW_SWSFW">
												<td class="listtablehead" width="150">
													未纳入核心服务范围描述:
												</td>
												<td class="js_left_txt" id="td_privince_content">
													<textarea cols="50" id="CLFWKFFW_SWSFW" name="CLFWKFFW_SWSFW" rows="20">${clfwBean.CLFWKFFW_SWSFW}</textarea>
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
