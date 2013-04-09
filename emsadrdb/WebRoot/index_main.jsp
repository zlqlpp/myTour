<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #1D3647;
}
-->
</style>
<script type="text/javascript">
			$(document).ready(function(){
				denglu();
			})			
			
			function denglu(){
				//$("#myform").submit(); 
			}
</script>
<html>
	<head>
		<title>用户登录</title>
		<link href="${ctx}/images/skin.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		
		<table width="100%" height="166" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="42" valign="top">
					<table width="100%" height="42" border="0" cellpadding="0"
						cellspacing="0" class="login_top_bg">
						<tr>
							<td width="1%" height="21">&nbsp;
								
							</td>
							<td height="42">&nbsp;
								
							</td>
							<td width="17%">&nbsp;
								
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<table width="100%" height="532" border="0" cellpadding="0"
						cellspacing="0" class="login_bg">
						<tr>
							<td width="49%" align="right">
								<table width="91%" height="532" border="0" cellpadding="0"
									cellspacing="0" class="login_bg2">
									<tr>
										<td height="138" valign="top">
											<table width="89%" height="427" border="0" cellpadding="0"
												cellspacing="0">
												<tr>
													<td height="149">&nbsp;
														
													</td>
												</tr>
												<tr>
													<td height="80" align="right" valign="top">
														<img src="${ctx}/images/logo.png" width="279" height="68">
													</td>
												</tr>
												<tr>
													<td height="198" align="right" valign="top">
														<table width="100%" border="0" cellpadding="0"
															cellspacing="0">
															<tr>
																<td width="35%">&nbsp;
																	
																</td>
																<td height="25" colspan="2" class="left_txt" style="padding-left:30px">
																	<p>
																		速递邮件全名址匹配正式版
																	</p>
																</td>
															</tr>
															<tr>
																<td>&nbsp;
																	
																</td>
																<td width="30%" height="40" style="padding-left:30px">
																	<img src="${ctx}/images/icon-demo.gif" width="16" height="16">
																	<a class="left_txt3"> 使用说明</a>
																</td>
																<td width="35%">
																	<img src="${ctx}/images/icon-login-seaver.gif" width="16"
																		height="16">
																	<a class="left_txt3">
																		在线客服</a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>

								</table>
							</td>
							<td width="1%">&nbsp;
								
							</td>
							<td width="50%" valign="bottom">
								<table width="100%" height="59" border="0" align="center"
									cellpadding="0" cellspacing="0">
									<tr>
										<td width="4%">&nbsp;
											
										</td>
										<td width="96%" height="38">
											<span class="login_txt_bt">中国邮政速递物流投递区段名址信息维护系统</span>
										</td>
									</tr>
									<tr>
										<td>&nbsp;
											
										</td>
										<td height="21">
											<table cellSpacing="0" cellPadding="0" width="100%"
												border="0" id="table211" height="293">
												<tr>
													<td height="164" colspan="2" align="middle">
														<form name="myform" id="myform" action='${ctx}/j_spring_security_check' method="post">
															<table cellSpacing="0" cellPadding="0" width="100%"
																border="0" height="143" id="table212">
																<tr>
																	<td width="13%" height="38" class="top_hui_text">
																		<span class="login_txt">管理员：&nbsp;&nbsp; </span>
																		
																	</td>
																	<td height="38" colspan="2" class="top_hui_text">
																		<input name="j_username" class="editbox4" value="jtgs02"
																			size="20">
																	</td>
																</tr>
																<tr>
																	<td width="13%" height="35" class="top_hui_text">
																		<span class="login_txt"> 密 码： &nbsp;&nbsp; </span>
																	</td>
																	<td height="35" colspan="2" class="top_hui_text">
																		<input class="editbox4" type="password" size="20"
																			name="j_password" value="ems123">
																		<img src="${ctx}/images/luck.gif" width="19" height="18">
																	</td>
																</tr>
																<tr>
																	<td height="35" colspan="3" class="login_txt">
																		<s:actionmessage/>
																		<%
																			if (request.getParameter("error") != null) {
																				out.print("用户名或者密码错误");
																			}
																		%>
																	</td>
																</tr>
																<!-- <tr>
																	<td width="13%" height="35">
																		<span class="login_txt">验证码：</span>
																	</td>
																	<td height="35" colspan="2" class="top_hui_text">
																		<input class=wenbenkuang name=verifycode type=text
																			value="" maxLength=4 size=10>
																	</td>
																</tr> -->
																<tr>
																	<td height="35">&nbsp;
																		
																	</td>
																	<td width="20%" height="35">
																		<input name="Submit" type="submit" class="button"
																			id="Submit" value="登 陆">
																	</td>
																	<td width="67%" class="top_hui_text">
																		<input name="cs" type="button" class="button" id="cs"
																			value="取 消" onClick=showConfirmMsg1();>
																	</td>
																</tr>
															</table>
															<br>
														</form>
													</td>
												</tr>
												<tr>
													<td width="433" height="164" align="right" valign="bottom">
														<img src="${ctx}/images/login-wel.gif" width="242" height="138">
													</td>
													<td width="57" align="right" valign="bottom">&nbsp;
														
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="20">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="login-buttom-bg">
						<tr>
							<td align="center">
								<span class="login-buttom-txt">Copyright &copy; 2009-2010
									www.865171.cn</span>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
