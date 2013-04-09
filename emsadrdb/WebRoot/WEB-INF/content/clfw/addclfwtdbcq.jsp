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
		<title>揽投部属性编辑</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var flag='Add';
			$(document).ready(function(){
				$("#COUNTY_NAMES").prepend("<option selected='selected' value='${clfwBean.CITY_CODE}'>本市</option>");
				$("#COUNTY_NAMES").val('${clfwBean.CLFWTDB_DISTCD}');
				if('${clfwBean.CLFWTDBCQ_SEQID}' != ''){
					$("#upquery").show();
					$("#addquery").hide();
				}else{
					$("#COUNTY_NAMES").val('');
					$("#upquery").hide();
					$("#addquery").show();
				}
				
				$("#fliphelpct").click(function(){
					$("#fliphelpcon").slideToggle("fast");
				});
			})
			function ajaxAddUpAlert(tflag){
				flag = tflag;
				if(flag=='Add'){
					ajaxAddUp(flag);
				}else{
					$.prompt('该操作将修改此揽投部属性！确认执行操作？',{
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
				var tCLFWTDB_FW=$('#CLFWTDB_FW').val();
				if(tCLFWTDB_FW.length>180){
					$.prompt('承诺服务范围字符大于180',{buttons: {'确定': true}});
					return false;
				}
				var tCOUNTY_NAMES = $('#COUNTY_NAMES').val();
				var tDM_PK_CODE = $('#DM_PK_CODE').val();
				var tCLFWTDBCQ_SEQID = $('#CLFWTDBCQ_SEQID').val();
				var params = {
						CLFWTDB_FW:tCLFWTDB_FW,
						CLFWTDB_DISTCD:tCOUNTY_NAMES,
						DM_PK_CODE:tDM_PK_CODE,
						CLFWTDBCQ_SEQID:tCLFWTDBCQ_SEQID
	            };
				if(flag=='Add'){
					var url = '${ctx}/clfw/b09cud02clfwconfig!addclfwtdbcq.action';
				}else{
					var url = '${ctx}/clfw/b09cud02clfwconfig!upclfwtdbcq.action';
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
										揽投部属性编辑
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<input id="CLFWTDBCQ_SEQID" type="hidden" value="${clfwBean.CLFWTDBCQ_SEQID}"/>
								<input id="DM_PK_CODE" type="hidden" value="${clfwBean.DM_PK_CODE}"/>
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
												<td class="listtablehead"  colspan="4">
													<font id="fliphelpcon" color="red">
														* 可以在本界面,连续维护数据
														<br>* 【揽投部服务范围】字数要小于180字
													</font>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="120">
													城区:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="countys" id="COUNTY_NAMES" listKey="DISTRICT_CODE" listValue="COUNTY_NAME"></s:select>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="120">
													揽投部服务范围:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<textarea cols="50" id="CLFWTDB_FW" name="CLFWTDB_FW" rows="10">${clfwBean.CLFWTDB_FW}</textarea>
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
