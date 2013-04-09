<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<title>预报关信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<table width="960" border="0" cellpadding="0" cellspacing="0" align="left">
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
								<div class="titlebt1">
									海关处理信息
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
				<td valign="middle" background="${ctx}/images/mail_leftbg.gif">
					&nbsp;
				</td>
				<td valign="top" align="center">
					<table>
						<tr>
							<td>
								<s:form namespace="/query"
									action="receiptitemstatus!query.action" id="ec">
									<div class="left_txt">
										<br />
										请输入邮件号：
										<input name="itemNo" type="text" size="15" value="${itemNo}" />
										<input name="submit" type="submit" value="查询" />
									</div>
								</s:form>
								<br />
								<div>
									<s:if test="receiptItemStatus!=null">
										<table class="listtable">
											<tr>
												<th colspan="11" align="left">
													&nbsp;&nbsp;当前状态
												</th>
											</tr>
											<s:if test="receiptItemStatus.customsport!=null">
												<tr>
													<td class="listtableheadleft" width="100">
														邮件号码
													</td>
													<td class="listtablehead" width="70">
														业务系统
													</td>
													<td class="listtablehead" width="100">
														邮件业务种类
													</td>
													<td class="listtablehead" width="70">
														关区代码
													</td>
													<td class="listtablehead" width="100">
														申报处理环节
													</td>
													<td class="listtablehead" width="100">
														申报回执指令
													</td>
													<td class="listtablehead" width="80">
														时间戳
													</td>
													<td class="listtablehead" width="70">
														征税标识
													</td>
													<td class="listtablehead" width="90">
														记录入库时间
													</td>
													<td class="listtablehead" width="70">
														下载标识
													</td>
													<td class="listtablehead" width="80">
														备注
													</td>
												</tr>
												<tr>
													<td class="listtablebodyleft1">
														${receiptItemStatus.itemno}
													</td>
													<td class="listtablebody1">
														${receiptItemStatus.businessflag}
													</td>
													<td class="listtablebody1">
														${receiptItemStatus.businesstype}
													</td>
													<td class="listtablebody1">
														${receiptItemStatus.customsport}
													</td>
													<td class="listtablebody1">
														${receiptItemStatus.entrystepcode}
													</td>
													<td class="listtablebody1">
														${receiptItemStatus.entrystatement}
													</td>
													<td class="listtablebody1">
														<s:date name="receiptItemStatus.receipttime"
															format="yyyy-MM-dd" />
													</td>
													
													<td class="listtablebody1">
														${receiptItemStatus.dutyflag}
													</td>
													<td class="listtablebody1">
														<s:date name="receiptItemStatus.insertdatetime"
															format="yyyy-MM-dd" />
													</td>
													<td class="listtablebody1">
														${receiptItemStatus.downloadflag}
													</td>
													<td class="listtablebody1">
														${receiptItemStatus.note}
													</td>
												</tr>
												
											</s:if>
											<s:if test="receiptItemStatus.customsport==null">
												<tr>
													<td class="listtablebody1" width="950">
														<img alt="" src="${ctx}/images/!.gif">
														&nbsp;&nbsp;&nbsp;&nbsp;没有符合条件的结果， 请您确定输入的邮件号码是否正确
													</td>
												</tr>
											</s:if>

										</table>
									</s:if>
								</div>
								<Br />
								<div>
									<s:if test="receiptItemStatusLogs!=null">
										<table class="listtable">
											<tr>
												<th colspan="10" align="left">
													&nbsp;&nbsp;历史状态
												</th>
											</tr>

											<s:if test="!receiptItemStatusLogs.isEmpty()">

												<tr>
													<td class="listtableheadleft" width="100">
														邮件号码
													</td>
													<td class="listtablehead" width="70">
														业务系统
													</td>
													<td class="listtablehead" width="100">
														邮件业务种类
													</td>
													<td class="listtablehead" width="70">
														关区代码
													</td>
													<td class="listtablehead" width="100">
														申报处理环节
													</td>
													<td class="listtablehead" width="100" >
														申报回执指令
													</td>
													<td class="listtablehead" width="80">
														时间戳
													</td>
													<td class="listtablehead" width="70">
														征税标识
													</td>
													<td class="listtablehead" width="90">
														记录入库时间
													</td>
													<td class="listtablehead" width="160">
														备注
													</td>
												</tr>
												<s:iterator value="receiptItemStatusLogs" status="ele">
													<tr>
														<td class="listtablebodyleft1">
															${id.itemno }
														</td>
														<td class="listtablebody1">
															${id.businessflag}
														</td>
														<td class="listtablebody1">
															${id.businesstype}
														</td>
														<td class="listtablebody1">
															${id.customsport}
														</td>
														<td class="listtablebody1">
															${id.entrystepcode}
														</td>
														<td class="listtablebody1">
															${id.entrystatement}
														</td>
														<td class="listtablebody1">
															<s:date name="id.receipttime" format="yyyy-MM-dd" />
														</td>
														
														<td class="listtablebody1">
															${id.dutyflag}
														</td>
														<td class="listtablebody1">
															<s:date name="id.insertdatetime" format="yyyy-MM-dd" />
														</td>
														<td class="listtablebody1">
															${id.note}
														</td>
													</tr>
												</s:iterator>
											</s:if>
											<s:else>
												<tr>
													<td class="listtablebody1" width="950">
														<img alt="" src="${ctx}/images/!.gif">
														&nbsp;&nbsp;&nbsp;&nbsp;没有符合条件的结果， 请您确定输入的邮件号码是否正确
													</td>
												</tr>
											</s:else>

										</table>
									</s:if>
								</div>
							</td>
						</tr>
						<tr>
							<td height="30">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
				<td valign="middle" background="${ctx}/images/mail_rightbg.gif">
					&nbsp;
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
