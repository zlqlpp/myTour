<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/disp/disp.jsp"%>
<html>

	<head>
		<title>经转范围调整</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			$(document).ready(function(){
				viewdispd();
			})			
			
			function viewdispd(){
				var tDISP_PROVINCE_NAME=$("#DISP_PROVINCE_NAME").find("option:selected").text(); 
				var url = '${ctx}/disp/b07r03dispoption!dispds.action';
				var params = {
					DISP_PROVINCE_NAME: tDISP_PROVINCE_NAME
				 };
				jQuery.post(url, params, callbackdispd, 'json');
			}
			function callbackdispd(data){
				$('#DISP_OFFICE_CODE option').remove();
				var d=data.dispBeans;
				for(var i=0;i<d.length;i++){
					$("#DISP_OFFICE_CODE").append("<option value='" + d[i].DISP_OFFICE_CODE + "'>" + d[i].DISP_OFFICE_ABBR + "</option>"); 
				}
			}
			function ajaxAddUpAlert(flag){
				if(flag=='Add'){
					ajaxAddUp(flag);
				}
			}
			function ajaxAddUp(flag){
				var tTRANS_CODE='${TRANS_CODE}';
				var tDISP_OFFICE_CODE=$("#DISP_OFFICE_CODE").val();
				if(tDISP_OFFICE_CODE == '${DISP_OFFICE_CODE}'){
					$.prompt('经转局名未改变!',{buttons: {'确定': true}});
					return false;
				}
				
				var tFLAG=3;
				if(tDISP_OFFICE_CODE.substr(0,4)==tTRANS_CODE.substr(0,4)){
					tFLAG=1;
				}else if(tDISP_OFFICE_CODE.substr(0,2)==tTRANS_CODE.substr(0,2)){
					tFLAG=2;
				}
				
				var params = {
						TRANS_CODE:tTRANS_CODE,
						DISP_OFFICE_CODE:tDISP_OFFICE_CODE,
						FLAG:tFLAG
	            };
				var url = '${ctx}/disp/b07cud02dispconfig!zhtrans.action';
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
										经转范围调整
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<a id="addquery" href='#' onClick="ajaxAddUpAlert('Add')"  style="font-size:12px">调整</a>
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
											<tr id="disps_up">
												<td class="listtablehead" width="100">
													被调整经转范围:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													 &nbsp;${TRANS_NAME}
												</td>
											</tr>
											<tr id="disps_add">
												<td class="listtablehead" width="100">
													调整至经转局:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="dispp" id="DISP_PROVINCE_NAME" listKey="PROVINCE_CODE" listValue="PROVINCE_NAME" onchange="viewdispd()"></s:select>
													<select class="simple" id="DISP_OFFICE_CODE"  style="width:100"></select>
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
