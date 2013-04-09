<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<title>预报关信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
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
			    <td valign="middle" background="${ctx}/images/mail_leftbg.gif">&nbsp;</td>
			    <td valign="top" bgcolor="#F7F8F9" align="center">
			    	<table>
			    		<tr>
			    			<td>
			    				<div class="left_txt">
			    				<br/>
				    			邮件号：<input name="itemNo" type="text" size="15"/>
				    			<input name="submit" type="submit" value="查询"/>
				    			</div>
				    			<br/>
				    			<div>
				    				<table align="center">
					    				<tr>
					    					<th width="50">邮件号</th>
					    					<th width="90">信息生成单位</th>
					    					<th width="70">关区代码</th>
					    					<th width="70">申报类别 </th>
					    					<th width="70">机构代码 </th>
					    					<th width="50">重量</th>
					    					<th width="60">金额</th>
					    				</tr>
					    				<tr>
					    					<td class="sec1">CP12345678CN</td>
					    					<td class="sec1">北京国际</td>
					    					<td class="sec1">123456</td>
					    					<td class="sec1">1</td>
					    					<td class="sec1">10000100</td>
					    					<td class="sec1">200.00</td>
					    					<td class="sec1">3000.00</td>
					    				</tr>
					    				<tr>
					    					<td class="sec1">CP12345678CN</td>
					    					<td class="sec1">北京国际</td>
					    					<td class="sec1">123456</td>
					    					<td class="sec1">1</td>
					    					<td class="sec1">10000100</td>
					    					<td class="sec1">200.00</td>
					    					<td class="sec1">3000.00</td>
					    				</tr>
					    				<tr>
					    					<td class="sec1">CP12345678CN</td>
					    					<td class="sec1">北京国际</td>
					    					<td class="sec1">123456</td>
					    					<td class="sec1">1</td>
					    					<td class="sec1">10000100</td>
					    					<td class="sec1">200.00</td>
					    					<td class="sec1">3000.00</td>
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
	</body>
</html>
