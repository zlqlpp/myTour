<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<html>
	<head>
		<title>权限信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			$(document).ready(function(){
				var haveCheckedRePkIds="${haveCheckedRePkIds}";
				var rePkIds=haveCheckedRePkIds.split(",");
				var c=$('#role_table_list tbody input[name=resources.rsPkId]');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
				    cv = cv+','+$(this).val();
				});
				if(cv!=''){
					cv=cv.substr(1);
					var tmp=cv.split(",");
					for (i=0;i<tmp.length;i++)
					{			
						for (j=0;j<rePkIds.length;j++ )
						{
							
							if(rePkIds[j]==tmp[i])
								{	
									$("#checkbox"+tmp[i]).attr("checked","checked");break;
								}
						}
					}
				}
			})
		</script>
	</head>
	<body>
		<s:form namespace="/pmn" action="a02cud01!save.action" id="ec">
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
											id="role_table_list">
											<tr>
												<th align="left" colspan="4">
													权限信息
												</th>
											</tr>
											<tr>
												<td align="right" class=listtablebodyleft1 width="80">
													权限名：
												</td>
												<td align="left" class=listtablebodyleft1 width="200">
													<input type="text" name="reName" size="40" value="${model.reName}"/>
													<input type="hidden" name="rePkId" value="${model.rePkId}"/>
												</td>
												<td align="right" class=listtablebodyleft1 width="80">
													权限中文名：
												</td>
												<td align="left" class=listtablebodyleft1 >
													<input type="text" name="reViewName" size="50"  value="${model.reViewName}"/>
												</td>
											</tr>
											<tr>
												<td align="right" class=listtablebodyleft1>
													权限级别：
												</td>
												<td align="left" class=listtablebodyleft1 colspan="3">
													<s:select value="model.rulLevel" name="rulLevel" theme="simple" list="roleLevels" listKey="rulLevel" listValue="rulName">
													</s:select>
												</td>
											</tr>
											<s:iterator id="ele" value="allResources">
												<tr>
													<td align="right" class=listtablebodyleft1 width="80">
														&nbsp;
													</td>
													<td align="left" class=listtablebodyleft1 colspan="3">
														<input id="checkbox${ele.rsPkId}" type="checkbox" name="resources.rsPkId"  value="${ele.rsPkId}"/>
														&nbsp;
														${ele.rsName }--${ele.rsStr }
													</td>
												</tr>
											</s:iterator>
											<tr id='tr_operator'>
												<td align="left" class=listtablebodyleft1 colspan="3">
													<input type="button" value="返回" onclick="history.go(-1)"/>
												</td>
												<td align="right" class=listtablebodyleft1>
													<input type="submit" value="保存" />
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
