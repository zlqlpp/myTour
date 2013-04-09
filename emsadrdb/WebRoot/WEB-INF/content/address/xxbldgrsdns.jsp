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
		<title>小区编辑</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			$(document).ready(function(){
				if('${FLAG}' == 'SH'){
					$("#upquery").show();
					$("#addquery").show();
				}else{
					$("#upquery").hide();
					$("#addquery").hide();
				}
			})
			function ajaxAddUp(DATA_FLAG){
				var tRSDNBLDG_ID=$("#RSDNBLDG_ID").val();
				var tDIST_CD=$("#DIST_CD").val();
				var tDATA_FLAG=DATA_FLAG;
				var params = {
						RSDNBLDG_ID:tRSDNBLDG_ID,
						DIST_CD:tDIST_CD,
						DATA_FLAG:tDATA_FLAG,
						TOTAL_STREET_NAME:'${bldgrsdnsBean.TOTAL_STREET_NAME}'
	            };
				var url = '${ctx}/address/b04cud02addrconfig!shbldgrsdns.action';
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
										小区编辑
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<input id="DIST_CD" type="hidden" value="${bldgrsdnsBean.DIST_CD}"/>
								<input id="RSDNBLDG_ID" type="hidden" value="${bldgrsdnsBean.RSDNBLDG_ID}"/>
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
											<tr id="tr_PROVINCE_NAME">
												<td class="listtablehead" width="80">
													行政区域:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													&nbsp;
													${TOTAL_DISTRICT_NAME}
												</td>
											</tr>
											<tr id="tr_STRT_ID">
												<td class="listtablehead" width="80">
													街道:
												</td>
												<td class="js_left_txt" id="td_STRT_ID" colspan="3">
													&nbsp;
													${bldgrsdnsBean.TOTAL_STREET_NAME}
												</td>
											</tr>
											<tr id="tr_DORPLT_ID">
												<td class="listtablehead" width="80">
													门牌:
												</td>
												<td class="js_left_txt" id="td_DORPLT_ID" colspan="3">
													<table class="listtable" id="viewquery_table"  width="100%">
													<tr>
														<td class="listtablehead"  width="80">
														前缀:
														</td>
														<td width="150" class="js_left_txt" id="td_city_content" colspan="3">
															&nbsp;
															${bldgrsdnsBean.PREFIX}
														</td>														
													</tr>
													<tr id="tdSTRT2_NAME">
														<td width="80" class="listtablehead">
														开始门牌:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															&nbsp;
															${bldgrsdnsBean.START_NUM}
														</td>
													</tr>
													<tr id="tdSTRT3_NAME">
														<td width="80" class="listtablehead">
														结束门牌:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															&nbsp;
															${bldgrsdnsBean.END_NUM}
														</td>
													</tr>
													<tr id="tdSTRT4_NAME">
														<td width="80" class="listtablehead">
														后缀:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															&nbsp;
															${bldgrsdnsBean.SUFFIX}
														</td>
													</tr>
													<tr id="tdSTRT4_NAME">
														<td width="80" class="listtablehead">
														门牌单/双:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															&nbsp;
															${bldgrsdnsBean.NUM_FLAG_NAME}
														</td>
													</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													小区名称:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
															&nbsp;
															${bldgrsdnsBean.RSDNBLDG_NAME}
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													小区别名:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
															&nbsp;
															${bldgrsdnsBean.RSDNBLDG_ABBR}
												</td>
											</tr>
											<tr id="tr_DORPLT_ID">
												<td class="listtablehead" width="80">
													邮编:
												</td>
												<td class="js_left_txt" id="td_POST_CD" colspan="3">
															&nbsp;
															${bldgrsdnsBean.POST_CD}
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													备注:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
															&nbsp;
															${bldgrsdnsBean.NOTE}
												</td>
											</tr>
											<tr id="tr_PROVINCE_NAME">
												<td class="listtablehead" width="80">
													投递区-部-段:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													&nbsp;
															${bldgrsdnsBean.DT_PK_NAME}
													&nbsp;
															${bldgrsdnsBean.DM_PK_NAME}
													&nbsp;
															${bldgrsdnsBean.PG_PK_NAME}
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
