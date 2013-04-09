<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/address/addr.jsp"%>
<html>

	<head>
		<title>街道详细</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			$(document).ready(function(){
				if('${FLAG}' == 'SH'){
					$("#upquery").show();
					$("#addquery").show();
					
					$("#noadulttr").hide();
					$("#adulttr").show();
				}else{
					$("#upquery").hide();
					$("#addquery").hide();
					
					$("#noadulttr").show();
					$("#adulttr").hide();
				}
			})
			function ajaxAddUp(DATA_FLAG){
				var tSTRT_ID=$("#STRT_ID").val();
				var tDIST_CD=$("#DIST_CD").val();
				var tADULTNOTE=$("#ADULTNOTE").val();
				var tDATA_FLAG=DATA_FLAG;
				var params = {
						ADULTNOTE:tADULTNOTE,
						STRT_ID:tSTRT_ID,
						DIST_CD:tDIST_CD,
						DATA_FLAG:tDATA_FLAG
	            };
				var url = '${ctx}/address/b04cud02addrconfig!shstreet.action';
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
										街道详细
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<input id="DIST_CD" type="hidden" value="${streetBean.DIST_CD}"/>
								<input id="STRT_ID" type="hidden" value="${streetBean.STRT_ID}"/>
								<a id="upquery" href='#' onClick="ajaxAddUp('2')"  style="font-size:12px">同意</a>
								<a id="addquery" href='#' onClick="ajaxAddUp('9')"  style="font-size:12px">拒绝</a>
								
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
										<table class="listtable" id="viewquery_table"  width="550">
											<tr>
												<td class="listtablehead" width="80">
													行政区域:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													&nbsp;
													${streetBean.TOTAL_DISTRICT_NAME}
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													街道:
												</td>
												<td class="js_left_txt" id="td_city_content"  colspan="3">
													<table class="listtable" id="viewquery_table"  width="100%">
													<tr>
														<td class="listtablehead" width="100">
														街道1段:
														</td>
														<td width="150" class="js_left_txt" id="td_city_content">
															&nbsp;
															${streetBean.STRT1_NAME}
														</td>	
														<td class="listtablehead" width="50">
														别名:
														</td>
														<td class="js_left_txt" id="td_city_content">
															&nbsp;
															${streetBean.STRT1_ABBR_NAME}
														</td>													
													</tr>
													<tr id="tdSTRT2_NAME">
														<td class="listtablehead">
														街道2段:
														</td>
														<td class="js_left_txt" id="td_city_content">
															&nbsp;
															${streetBean.STRT2_NAME}
														</td>
														<td class="listtablehead">
														别名:
														</td>
														<td class="js_left_txt" id="td_city_content">
															&nbsp;
															${streetBean.STRT2_ABBR_NAME}
														</td>
													</tr>
													<tr id="tdSTRT3_NAME">
														<td class="listtablehead">
														街道3段:
														</td>
														<td class="js_left_txt" id="td_city_content">
															&nbsp;
															${streetBean.STRT3_NAME}
														</td>
														<td class="listtablehead">
														别名:
														</td>
														<td class="js_left_txt" id="td_city_content">
															&nbsp;
															${streetBean.STRT3_ABBR_NAME}
														</td>
													</tr>
													<tr id="tdSTRT4_NAME">
														<td class="listtablehead">
														街道4段:
														</td>
														<td class="js_left_txt" id="td_city_content">
															&nbsp;
															${streetBean.STRT4_NAME}
														</td>
														<td class="listtablehead">
														别名:
														</td>
														<td class="js_left_txt" id="td_city_content">
															&nbsp;
															${streetBean.STRT4_ABBR_NAME}
														</td>
													</tr>
													<tr id="tdSTRT5_NAME">
														<td class="listtablehead">
														街道5段:
														</td>
														<td class="js_left_txt" id="td_city_content">
															&nbsp;
															${streetBean.STRT5_NAME}
														</td>
														<td class="listtablehead">
														别名:
														</td>
														<td class="js_left_txt" id="td_city_content">
															&nbsp;
															${streetBean.STRT5_ABBR_NAME}
														</td>
													</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													街道简拼:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
															&nbsp;
															${streetBean.STRT_ABBR}
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													邮编:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
															&nbsp;
															${streetBean.POST_CD}
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													备注:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
															&nbsp;
															${streetBean.NOTE}
												</td>
											</tr>
											<tr id="noadulttr">
												<td class="listtablehead" width="80">
													审核原因:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
															&nbsp;
															${streetBean.ADULTNOTE}
												</td>
											</tr>
											<tr id="adulttr">
												<td class="listtablehead" width="80">
													审核原因:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
															&nbsp;
															<input type="text" name="ADULTNOTE" style="width: 200px; height:20px;" id="ADULTNOTE" class="ac_input" size="300" value="${streetBean.ADULTNOTE}" onFocus="this.select();" />
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													数据状态:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
															&nbsp;
															${streetBean.DATA_FLAG_NAME}
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													提交时间:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
															&nbsp;
															${streetBean.DATA_DATE}
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													提交人:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
															&nbsp;
															${streetBean.DATA_USER_NAME}
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
