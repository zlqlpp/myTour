<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
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
								<div class="titlebt">
									预报关信息
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
				<td valign="top" bgcolor="#F7F8F9" align="center">
					<table>
						<tr>
							<td>
								<s:form namespace="/query" action="entryiteminfo!query.action"
									id="ec">
									<div class="left_txt">
										<br />
										请输入邮件号：
										<input name="itemNo" type="text" size="15" value="${itemNo}"/>
										<input name="submit" type="submit" value="查询" />
									</div>
								</s:form>
								<br />
								<div>

									<s:if test="entryItemInfo!=null">
										
											<table width="100%" border="0" cellpadding="0"
												cellspacing="1" class="viewtable">
												<tr>
													<th colspan="4" align="left" class="viewtableth">
														&nbsp;&nbsp;&nbsp;&nbsp;预报关信息查询
													</th>
												</tr>
												<s:if test="entryItemInfo.customsport!=null">
												<tr>
													<td width="200" class="viewtabletd1" align="right">
														邮件号码 ：
													</td>
													<td width="200" class="viewtabletd1" align="left">
														${itemNo}
													</td>
													<td width="200" class="viewtabletd1" align="right">
														业务系统标识 ：
													</td>
													<td width="200" class="viewtabletd1" align="left">
														<c:if test="${entryItemInfo.businessflag=='ZJ'}">支局</c:if>
														<c:if test="${entryItemInfo.businessflag=='SJ'}">宏网</c:if>
													</td>
												</tr>
												<tr>
													<td width="200" class="viewtabletd2" align="right">
														邮件业务种类标识 ：
													</td>
													<td width="200" class="viewtabletd2" align="left">
														${entryItemInfo.businesstype}
													</td>
													<td width="200" class="viewtabletd2" align="right">
														信息生成单位 ：
													</td>
													<td width="200" class="viewtabletd2" align="left">
														${entryItemInfo.infosource}&nbsp;
													</td>
												</tr>
												<tr>
													<td width="200" class="viewtabletd1" align="right">
														关区代码 ：
													</td>
													<td width="200" class="viewtabletd1" align="left">
														${entryItemInfo.customsport}
													</td>
													<td width="200" class="viewtabletd1" align="right">
														字符日期 ：
													</td>
													<td width="200" class="viewtabletd1" align="left">
														<s:date name="entryItemInfo.declaredate" format="yyyy-MM-dd" />
													</td>
												</tr>
												<tr>
													<td width="200" class="viewtabletd2" align="right">
														进出口标识 ：
													</td>
													<td width="200" class="viewtabletd2" align="left">
														<c:if test="${entryItemInfo.ieflag=='I'}">进口</c:if>
														<c:if test="${entryItemInfo.ieflag=='E'}">出口</c:if>
													</td>
													<td width="200" class="viewtabletd2" align="right">
														国别地区代码 ：
													</td>
													<td width="200" class="viewtabletd2" align="left">
														${entryItemInfo.countrycode}&nbsp;
													</td>
												</tr>
												<tr>
													<td width="200" class="viewtabletd1" align="right">
														collectdate ：
													</td>
													<td width="200" class="viewtabletd1" align="left">
														<s:date name="entryItemInfo.collectdate" format="yyyy-MM-dd" />
													</td>
													<td width="200" class="viewtabletd1" align="right">
														申报类别 ：
													</td>
													<td width="200" class="viewtabletd1" align="left">
														${entryItemInfo.entrytype}&nbsp;
													</td>
												</tr>
												<tr>
													<td width="200" class="viewtabletd2" align="right">
														特殊邮件标识 ：
													</td>
													<td width="200" class="viewtabletd2" align="left">
														${entryItemInfo.specialitemtype}
													</td>
													<td width="200" class="viewtabletd2" align="right">
														申报单位代码：
													</td>
													<td width="200" class="viewtabletd2" align="left">
														${entryItemInfo.tradecode}
													</td>
													
												</tr>
												<tr>
													<td width="200" class="viewtabletd1" align="right">
														原寄局机构代码  ：
													</td>
													<td width="200" class="viewtabletd1" align="left">
														${entryItemInfo.sendofficecode}
													</td>
													<td width="200" class="viewtabletd1" align="right">
														原寄局机构中文名 ：
													</td>
													<td width="200" class="viewtabletd1" align="left">
														${entryItemInfo.sendoffice}
													</td>
													
												</tr>
												<tr>
													<td width="200" class="viewtabletd2" align="right">
														寄达局机构代码 ：
													</td>
													<td width="200" class="viewtabletd2" align="left">
														${entryItemInfo.receiveofficecode}&nbsp;
													</td>
													<td width="200" class="viewtabletd2" align="right">
														寄达局机构中文名 ：
													</td>
													<td width="200" class="viewtabletd2" align="left">
														${entryItemInfo.receiveoffice}
													</td>
												</tr>
												<tr>
													<td width="200" class="viewtabletd1" align="right">
														寄件人姓名 ：
													</td>
													<td width="200" class="viewtabletd1" align="left">
														${entryItemInfo.sendername}&nbsp;
													</td>
													<td width="200" class="viewtabletd1" align="right">
														寄件人地址-省 ：
													</td>
													<td width="200" class="viewtabletd1" align="left">
														${entryItemInfo.senderaddressprov}
													</td>
												</tr>
												<tr>
													<td width="200" class="viewtabletd2" align="right">
														寄件人地址-城市：
													</td>
													<td width="200" class="viewtabletd2" align="left">
														${entryItemInfo.senderaddresscity}&nbsp;
													</td>
													<td width="200" class="viewtabletd2" align="right">
														寄件人地址-街道 ：
													</td>
													<td width="200" class="viewtabletd2" align="left">
														${entryItemInfo.senderaddressstreet}&nbsp;
													</td>
													
												</tr>
												<tr>
													<td class="viewtabletd1" align="right">
														寄件人地址 ：
													</td>
													<td class="viewtabletd1" align="left" colspan="3">
														${entryItemInfo.senderaddress}&nbsp;
													</td>
												</tr>
												<tr>
													<td class="viewtabletd2" align="right">
														寄件人电话或传真 ：
													</td>
													<td class="viewtabletd2" align="left">
														${entryItemInfo.senderphone}&nbsp;
													</td>
													<td class="viewtabletd2" align="right">
														邮政编码 ：
													</td>
													<td class="viewtabletd2" align="left">
														${entryItemInfo.senderpostcode}&nbsp;
													</td>
												</tr>
												<tr>
													<td class="viewtabletd1" align="right">
														收件人姓名 ：
													</td>
													<td class="viewtabletd1" align="left">
														${entryItemInfo.receivername}&nbsp;
													</td>
													<td class="viewtabletd1" align="right">
														收件人地址-省或州 ：
													</td>
													<td class="viewtabletd1" align="left">
														${entryItemInfo.receiveraddressprov}&nbsp;
													</td>
												</tr>
												<tr>
													<td class="viewtabletd2" align="right">
														收件人地址-城市 ：
													</td>
													<td class="viewtabletd2" align="left">
														${entryItemInfo.receiveraddresscity}&nbsp;
													</td>
													<td class="viewtabletd2" align="right">
														收件人地址-街道 ：
													</td>
													<td class="viewtabletd2" align="left">
														${entryItemInfo.receiveraddressstreet}&nbsp;
													</td>
												</tr>
												<tr>
													<td class="viewtabletd1" align="right">
														收件人地址 ：
													</td>
													<td class="viewtabletd1" align="left" colspan="3">
														${entryItemInfo.receiveraddress}&nbsp;
													</td>
												</tr>
												<tr>
													<td class="viewtabletd2" align="right">
														收件人电话或传真 ：
													</td>
													<td class="viewtabletd2" align="left">
														${entryItemInfo.receiverphone}&nbsp;
													</td>
													<td class="viewtabletd2" align="right">
														邮政编码 ：
													</td>
													<td class="viewtabletd2" align="left">
														${entryItemInfo.receiverpostcode}&nbsp;
													</td>
													
												</tr>
												<tr>
													<td class="viewtabletd1" align="right">
														重量 ：
													</td>
													<td class="viewtabletd1" align="left">
														${entryItemInfo.totalweight}&nbsp;
													</td>
													<td class="viewtabletd1" align="right">
														币制代码 ：
													</td>
													<td class="viewtabletd1" align="left">
														${entryItemInfo.currencycode}&nbsp;
													</td>
													
												</tr>
												<tr>
													<td class="viewtabletd2" align="right">
														金额 ：
													</td>
													<td class="viewtabletd2" align="left">
														${entryItemInfo.totalvalue}&nbsp;
													</td>
													<td class="viewtabletd2" align="right">
														postage ：
													</td>
													<td class="viewtabletd2" align="left">
														${entryItemInfo.postage}&nbsp;
													</td>
													
												</tr>
												<tr>
													<td class="viewtabletd1" align="right">
														件数 ：
													</td>
													<td class="viewtabletd1" align="left" colspan="3">
														${entryItemInfo.packnum}&nbsp;
													</td>	
												</tr>
												<tr>
													<td class="viewtabletd2" align="right">
														主要物品名称代码：
													</td>
													<td class="viewtabletd2" align="left">
														${entryItemInfo.maingoodscode}&nbsp;
													</td>
													<td class="viewtabletd2" align="right">
														主要物品名称 ：
													</td>
													<td class="viewtabletd2" align="left">
														${entryItemInfo.maingoodsname}&nbsp;
													</td>
												</tr>
												<tr>
													<td width="200" class="viewtabletd1" align="right">
														主要物品名称中文 ：
													</td>
													<td width="200" class="viewtabletd1" align="left">
														${entryItemInfo.maingoodsnamechs}&nbsp;
													</td>
													<td width="200" class="viewtabletd1" align="right">
														主要物品名称英文 ：
													</td>
													<td width="200" class="viewtabletd1" align="left">
														${entryItemInfo.maingoodsnameeng}&nbsp;
													</td>
													
												</tr>
												<tr>
													<td class="viewtabletd2" align="right">
														备注：
													</td>
													<td class="viewtabletd2" align="left">
														${entryItemInfo.note}&nbsp;
													</td>
													<td class="viewtabletd2" align="right">
														记录入库时间 ：
													</td>
													<td class="viewtabletd2" align="left">
														<s:date name="entryItemInfo.insertdatetime" format="yyyy-MM-dd" />
														&nbsp;
													</td>
												</tr>
												<tr>
													<td class="viewtabletd1" align="right">
														infostatus ：
													</td>
													<td class="viewtabletd1" align="left">
														${entryItemInfo.infostatus}&nbsp;
													</td>
													<td class="viewtabletd1" align="right">
														sendofficeid ：
													</td>
													<td class="viewtabletd1" align="left">
														${entryItemInfo.sendofficeid}&nbsp;
													</td>
												</tr>
												<tr>
													<td class="viewtabletd2" align="right">
														remedyofficeid：
													</td>
													<td class="viewtabletd2" align="left">
														${entryItemInfo.remedyofficeid}&nbsp;
													</td>
													<td class="viewtabletd2" align="right">
														remedytime ：
													</td>
													<td class="viewtabletd2" align="left">
														<s:date name="entryItemInfo.remedytime" format="yyyy-MM-dd" />
														&nbsp;
													</td>
												</tr>
												<tr>
													<td class="viewtabletd1" align="right">
														申报单位代码：
													</td>
													<td class="viewtabletd1" align="left">
														${entryItemInfo.tradecode}&nbsp;
													</td>
													<td class="viewtabletd1" align="right">
														&nbsp;
													</td>
													<td class="viewtabletd1" align="left">
														&nbsp;
													</td>
												</tr>
												</s:if>
												<s:else>
												<tr>
													<td align="left" class="viewtabletd1" width="800">
														<img alt="" src="${ctx}/images/!.gif">&nbsp;&nbsp;&nbsp;&nbsp;没有符合条件的结果，
														请您确定输入的邮件号码是否正确
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
