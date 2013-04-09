<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<html>
	<head>
		<title>区信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function role_add(){
				window.location.href="${ctx}/pmn/a02cud01!edit.action";
			}
		</script>
	</head>
	<body>
		<s:form namespace="/pmn" action="a02r01" id="ec">
			<table width="1030" border="0" cellpadding="0" cellspacing="0"
				align="left">
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
										权限信息
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
									<div id="querydiv">
										<table class="listtable" id="viewquery_table">
										</table>
									</div>
									<br />
									<div id="tablelistdiv">
										<table class="listtable" width="960"
											id="resource_table_list">
											<tr>
												<th align="left" colspan="5">
													权限信息
												</th>
											</tr>
											<tr>
												<td align="left" class=listtableheadleft width="100">
													权限ID
												</td>
												<td align="left" class=listtableheadleft width="200">
													权限英文名
												</td>
												<td align="left" class=listtableheadleft width="300">
													权限中文名
												</td>
												<td align="left" class=listtableheadleft width="100">
													查看
												</td>
												<td align="left" class=listtableheadleft>
													操作
												</td>
											</tr>
											<s:iterator id="ele" value="roles">
												<tr>
													<td align="left" class=listtablebodyleft1>
														${ele.rePkId }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.reName }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.reViewName }
													</td>
													<td align="left" class=listtablebodyleft1>
														<a href="${ctx}/pmn/a02cud01!edit.action?rePkId=${ele.rePkId}">查看</a>
													</td>
													<td align="left" class=listtablebodyleft1>
														<a href="${ctx}/pmn/a02cud01!delete.action?rePkId=${ele.rePkId}">删除</a>
													</td>
												</tr>
											</s:iterator>
											<tr id='tr_operator'>
												<td align="left" class=listtablebodyleft1 colspan="4">
													&nbsp;
												</td>
												<td align="left" class=listtablebodyleft1>
													<input type="button" value="添加" onclick="role_add()" />
												</td>
											</tr>
										</table>
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
		</s:form>
	</body>
</html>
