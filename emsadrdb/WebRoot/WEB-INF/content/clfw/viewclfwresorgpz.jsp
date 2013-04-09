<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/clfw/clfw.jsp"%>
<%@ include file="/scripts/My97DatePicker/My97DatePicker.jsp"%>
<html>

	<head>
		<title>支局代码频次配置</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				$("#th_addclfwresorgpz").hide();
				
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
						CLFWPC_SX:'2'
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
			}
			function ajaxQueryPage(pageNo){
				var tSEL_ORG_CODE = '';
				var tSEL_ORG_CODET=$("#SEL_ORG_CODE").val();
				if(tSEL_ORG_CODET.length>0){
					var tSEL_ORG_CODES = tSEL_ORG_CODET.split(",")
					for(i=0;i<tSEL_ORG_CODES.length;i++){
						if(tSEL_ORG_CODES[i].length>8){
							$.prompt('支局代码查询输入有误' + tSEL_ORG_CODES[i],{buttons: {'确定': true}});
							return false;
						}else{
							if(i==0){
								tSEL_ORG_CODE = tSEL_ORG_CODE + "'" +  tSEL_ORG_CODES[i] + "'";
							}else{
								tSEL_ORG_CODE = tSEL_ORG_CODE + ",'" + tSEL_ORG_CODES[i] + "'";
							}
						}
					}
				}
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				if(isMunicipalities(tPROVINCE_NAME)){
					var tCITY_ID='';
			    }else{
					var tCITY_ID=$("#CITY_NAMES").val();
				}
				var tSEL_DM_NAME=$("#SEL_DM_NAME").val();
				var tSEL_CLFWTDB_CLSX=$("input[name='SEL_CLFWTDB_CLSX'][checked]").val();
				var tRAD_CLFWPC_SX=$("input[name='RAD_CLFWPC_SX'][checked]").val();
				var tSEL_CLFWPCSHENG_SHENGM=$("#SEL_CLFWPCSHENG_SHENGM").val();
				var tSEL_CLFWPCSHI_SHIGM=$("#SEL_CLFWPCSHI_SHIGM").val();
				var tRAD_CLFWPC_QY=$("input[name='RAD_CLFWPC_QY'][checked]").val();
				if(tRAD_CLFWPC_QY == '1'){
					$("#th_delclfwresorgpz").show();
					$("#th_addclfwresorgpz").hide();
				}else if(tRAD_CLFWPC_QY == '0'){
					$("#th_delclfwresorgpz").hide();
					$("#th_addclfwresorgpz").show();
				}
				var url = '${ctx}/clfw/b09r02clfwquery!queryclfwresorgpz.action';
			    var params = {
			    				SEL_ORG_CODE:tSEL_ORG_CODE,
			    				PROVINCE_ID:tPROVINCE_ID,
								CITY_ID:tCITY_ID,
								SEL_DM_NAME:tSEL_DM_NAME,
			    				SEL_CLFWTDB_CLSX:tSEL_CLFWTDB_CLSX,
			    				RAD_CLFWPC_SX:tRAD_CLFWPC_SX,
					    		SEL_CLFWPCSHENG_SHENGM:tSEL_CLFWPCSHENG_SHENGM,
					    		SEL_CLFWPCSHI_SHIGM:tSEL_CLFWPCSHI_SHIGM,
					    		RAD_CLFWPC_QY:tRAD_CLFWPC_QY,
			                  	pageNo:pageNo
			                 };
			    jQuery.post(url, params, callbackajaxQueryPage, 'json');
			}
			function callbackajaxQueryPage(data){
				addquerytable(data);
			}
			function addquerytable(data){
				var resultdata=data.page.result;
				$('#address_table tbody').remove();
				if(resultdata.length>0){
					for(var i=0;i<resultdata.length;i++){
						var tr="<tr id='tr_query" + resultdata[i].CLFWRESORGPZ_SEQID + "' )>";
						tr+=" <td align=right class=listtablebodyleft1 style='width:30px'><input type='checkbox' onclick=xuanze('tr_query" + resultdata[i].CLFWRESORGPZ_SEQID + "') id='rlCheckBox" + resultdata[i].CLFWRESORGPZ_SEQID + "'  name='rlCheckBox' value='"+resultdata[i].CLFWRESORGPZ_SEQID + "'>";
						tr+="</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:120px'>&nbsp;"+resultdata[i].CLFWRESORGPZ_DISTCDSTR+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:200px'>&nbsp;"+resultdata[i].CLFWPCSHENG_SHENGM+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:200px'>&nbsp;"+resultdata[i].CLFWPCSHI_SHIGM+"</td>";
						
						//tr+="<td align=left class=listtablebodyleft1 style='width:80px'>&nbsp;"+resultdata[i].CLFWRESORGPZ_SJYXSC+"</td>";
						//tr+="<td align=left class=listtablebodyleft1 style='width:80px'>&nbsp;"+resultdata[i].CLFWRESORGPZ_LSLZB+"</td>";
						
						tr+="<td align=left class=listtablebodyleft1 style='width:200px'>&nbsp;"+resultdata[i].ORG_CNAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].ORG_CODE+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:80px'>&nbsp;"+resultdata[i].CLFWRESORGPZ_CLSXSTR+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:80px'>&nbsp;"+resultdata[i].CLFWRESORGPZ_SHSXSTR+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:120px'>&nbsp;"+resultdata[i].CLFWRESORGPZ_SHCZSTR+"</td>";
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr ><td colspan=12 class=listtablebodyleft2>"+data.page.ajaxUrl+
					"<input type='text' name='pagenum' style='width:50px;height:20px;' id='pagenum' class='ac_input'/><a id='pagego' href='#' onClick='pagecx(" + data.page.totalCount + "," + data.page.pageSize + ")' style='font-size:12px'>GO</a>"+
					"</td></tr>";
					$('#address_table').append(url);
				}else{
					var url="<tr><td colspan=12 class=listtablebodyleft2>找到 0 条记录</td></tr>";
					$('#address_table').append(url);
				}
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
			}
			function backfresh(value){
				if(value == "fresh") {
					ajaxQueryPage(0);
				}
			}
			function xuanze(st,id){
				if($("#" + id).attr("checked")){
					$("#" + st).css("background","#FEEBD0");
				}else{
					$("#" + st).css("background","#FBFBFF");
				}
			}
		  function queryAll(){
				var n = $("input[name=chooseName]:checked").length; 
				 if(n==0){ 
					 $("#address_table tbody input[name=rlCheckBox]").each(function(){
			                $(this).attr('checked',false);
			            }); 
					 $("#address_table tbody tr").each(function(){
						 $(this).css("background","#FBFBFF");
			            }); 
				 }else{ 
					$("#address_table tbody input[name=rlCheckBox]").each(function(){
				                $(this).attr('checked',true);
				           });
					$("#address_table tbody tr").each(function(){
								$(this).css("background","#FEEBD0");
			            });
				 } 
			}
		  function delclfwresorgpz(){
				var c=$('#address_table tbody input[name=rlCheckBox]:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
					cv = cv+','+$(this).val();
				});
				if(cv==''){
					$.prompt('请选择支局信息',{buttons: {'确定': true}});
					return false;
				}
				var tTOTAL_ALL_VALUE=cv.substr(1,cv.length);
				$.prompt('该操作将删除支局频次配置信息！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									CLFWRESORGPZ_SEQID:tTOTAL_ALL_VALUE
							};
							var url = '${ctx}/clfw/b09cud02clfwconfig!delclfwresorgpz.action';
							if(!(bro.msie && bro.version >=8)){
								$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
							}else{
								$("#nodata").show();
								$("#yesdata").hide();
							}
							jQuery.post(url, params, callajaxAdd, 'json');
						}
					}
				});
			}
		  function addclfwresorgpz(){
				var c=$('#address_table tbody input[name=rlCheckBox]:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
					cv = cv+",'"+$(this).val() + "'";
				});
				if(cv==''){
					$.prompt('请选择支局信息',{buttons: {'确定': true}});
					return false;
				}
				var tTOTAL_ALL_VALUE=cv.substr(1,cv.length);
				var tCLFWPC_NAMES=$("#CLFWPC_NAMES").val();
				if(tCLFWPC_NAMES.length<1){
					$.prompt('请选择频次',{buttons: {'确定': true}});
					return false;
				}
				$.prompt('该操作将添加支局频次配置信息！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									CLFWRESORGPZ_SEQID:tTOTAL_ALL_VALUE,
									CLFWPCSHI_SEQID:tCLFWPC_NAMES
							};
							var url = '${ctx}/clfw/b09cud02clfwconfig!addclfwresorgpz.action';
							if(!(bro.msie && bro.version >=8)){
								$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
							}else{
								$("#nodata").show();
								$("#yesdata").hide();
							}
							jQuery.post(url, params, callajaxAdd, 'json');
						}
					}
				});
			}
		  function callajaxAdd(data){
				$('#queryAll').attr('checked',false);
				$.prompt(data.saveMessage,{buttons: {'确定': true}});
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
				backfresh("fresh");
			}
		</script>
	</head>
	<body>
<div id="nodata" style="display:none">
<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />
</div>
<div id="yesdata">
			<table width="900" border="0" cellpadding="0" cellspacing="0" align="left">
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
										核心服务>>揽投部频次配置
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
										<table class="listtable" id="viewquery_table"  width="900">
											<tr>
												<td class="listtablehead" width="180">
													行政区域:
												</td>
												<td class="js_left_txt" id="td_privince_content">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity();"></s:select>
													<select class="simple" id="CITY_NAMES" style="width:100"  onchange="viewCLFWPC_NAMES()"></select>
												</td>
												<td class="listtablehead" width="180">
													省处理中心频次名称:
												</td>
												<td class="js_left_txt" id="td_common_tempid">
													<input type="text" name="SEL_CLFWPCSHENG_SHENGM" style="width: 200px; height:20px;" id="SEL_CLFWPCSHENG_SHENGM" class="ac_input" size="200"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="180">
													市衔接频次名称:
												</td>
												<td class="js_left_txt" id="td_privince_content">
													<input type="text" name="SEL_CLFWPCSHI_SHIGM" style="width: 200px; height:20px;" id="SEL_CLFWPCSHI_SHIGM" class="ac_input" size="200"/>
												</td>
												<td class="listtablehead" width="180">
													频次属性：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value=""  checked="checked">全部
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value="2">揽收
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value="3">投递
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="120">
													支局名称:
												</td>
												<td class="js_left_txt" id="td_common_tempid">
													<input type="text" name="SEL_DM_NAME" style="width: 200px; height:20px;" id="SEL_DM_NAME" class="ac_input" size="200"/>
													</td>
												<td class="listtablehead" width="120">
													服务属性：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="SEL_CLFWTDB_CLSX" id="SEL_CLFWTDB_CLSX" value=""  checked="checked">全部
													<input type="radio" name="SEL_CLFWTDB_CLSX" id="SEL_CLFWTDB_CLSX" value="1">全是
													<input style="display:none" type="radio" name="SEL_CLFWTDB_CLSX" id="SEL_CLFWTDB_CLSX" value="0"><label style="display:none">全否</label>
													<input style="display:none" type="radio" name="SEL_CLFWTDB_CLSX" id="SEL_CLFWTDB_CLSX" value="2"><label style="display:none">部分</label>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="100">
													支局代码:
												</td>
												<td class="js_left_txt" id="td_common_tempid">
													<input type="text" name="SEL_ORG_CODE" style="width: 200px; height:20px;" id="SEL_ORG_CODE" class="ac_input" size="200"/>
												</td>
												<td class="listtablehead">
													配置属性：
												</td>
												<td class="js_left_txt"  colspan="3">
													<input type="radio" name="RAD_CLFWPC_QY" id="RAD_CLFWPC_QY" value="1" checked="checked">已配置
													<input type="radio" name="RAD_CLFWPC_QY" id="RAD_CLFWPC_QY" value="0">可配置
												</td>
											</tr>
											<tr>
												<td class="listtablehead"  colspan="4" align="right">
													<input id="query" type="button" value="查询" onClick="ajaxQueryPage(0)" />
													<input id="queryclear" type="button" value="重置" onClick="ajaxQueryPage(0)" />
													<input id="fliphelpct" type="button" value="帮助"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead"  colspan="4">
													<font id="fliphelpcon" color="red">
														*操作步骤 点击【可配置】选择机构到【市频次】中即可，点击【帮助】可隐藏此帮助
														<br>*支局代码可以多条查询例如 输入括号中的机构代码，以,分割【1100000,120000】
														<br>*维护要求：在各揽收频次核心服务范围内，包含所有普邮代理收寄EMS的支局所。
													</font>
												</td>
											</tr>
										</table>
									</div>
									<div id="tablelistdiv">
										<table class="listtable" width="1300"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="12">
													支局代码频次配置
												</th>
											</thead>
											<thead>
												<th align="left"  colspan="2">
													<p id="th_delclfwresorgpz">
													<a href='#' onClick="delclfwresorgpz();">&nbsp;删除配置</a>
													</p>
												<th  align="left"  colspan="10">
													<p id="th_addclfwresorgpz">
													市频次名称：
													<select class="simple" id="CLFWPC_NAMES" style="width:300"></select>
													<a href='#' onClick="addclfwresorgpz();">&nbsp;添加配置</a>
													</p>
												</th>
											</thead>
											<thead>
												<td align="right" class="listtableheadleft" style='width:30px'>
													<input id="queryAll" name="chooseName" type="checkbox" onClick="queryAll()"/>
												</td>
												<td align="left" class="listtableheadleft" style='width:120px'>
													行政区域
												</td>
												<td align="left" class="listtableheadleft" style='width:200px'>
													省处理中心频次名称
												</td>
												<td align="left" class="listtableheadleft" style='width:200px'>
													市运行频次名称
												</td>
												<!--
												<td align="left" class="listtableheadleft" style='width:80px'>
													运行时长
												</td>
												<td align="left" class="listtableheadleft" style='width:80px'>
													揽收量占比
												</td>
												  -->
												<td align="left" class="listtableheadleft" style='width:120px'>
													支局名称
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													机构代码
												</td>
												<td align="left" class="listtableheadleft" style='width:80px'>
													服务属性
												</td>
												<td align="left" class="listtableheadleft" style='width:80px'>
													审核属性
												</td>
												<td align="left" class="listtableheadleft1" style='width:120px'>
													待审核操作
												</td>
											</thead>
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
</div>
	</body>
	<script language="javascript">
//	document.oncontextmenu = function(){
//		return false;
//	}
//	document.onkeydown = function(){
//		if (event.ctrlKey && window.event.keyCode==67){
//		   return false;
//		}
//	}
//	document.body.oncopy = function ()
//	{
//		return false;
//	}
//	document.onselectstart = function(){
//		return false;
//	}
	</script>
</html>
