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
		<title>经济频次到市配置(省)编辑</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var flag='Add';
			$(document).ready(function(){
				if('${itemBean.SEQID}' != ''){
					$("#upquery").show();
				}else{
					$("#upquery").hide();
				}
				
				$("#fliphelpct").click(function(){
					$("#fliphelpcon").slideToggle("fast");
				});
				
			})
			function ajaxAddUpAlert(tflag){
				flag = tflag;

				$.prompt('该操作将修改此频次！确认执行操作？',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							ajaxAddUp(flag);
						}
					}
				});
			}
			function ajaxAddUp(flag){
				var regEx = /[^0-9]+/gi;
				var tSEQID=$('#SEQID').val();
				var tPCMC=$('#PCMC').val();
				if(tPCMC.length<1){
					$.prompt('请输入频次名称',{buttons: {'确定': true}});
					return false;
				}
				if(tPCMC.length>80){
					$.prompt('频次名称字符大于80',{buttons: {'确定': true}});
					return false;
				}
				var tREADME=$('#README').val();
				if(tREADME.length>80){
					$.prompt('频次说明字符大于80',{buttons: {'确定': true}});
					return false;
				}

				var params = {
						SEQID:tSEQID,
						PCMC:tPCMC,
						README:tREADME
	            };
				var url = '${ctx}/jjsx/b10cud02jjsxconfig!upjjsxsjspc.action';
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
										经济频次到市配置(省)编辑
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<input id="SEQID" type="hidden" value="${itemBean.SEQID}"/>
								<a id="upquery" href='#' onClick="ajaxAddUpAlert('Up')"  style="font-size:12px">修改</a>
								<a id="queryback" href='#' onClick="backQuery('fresh')"  style="font-size:12px">返回</a>
								<a id="fliphelpct" href='#' style="font-size:12px">帮助</a>
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
														*【频次名称】,【说明】字数要小于80字
														<br>*【频次名称】一般是城市名，自动拼装为【频次名称】，例如【北京】->【北京1频[投递][市区核心]】									
													</font>
												</td>
											</tr>
											<tr>
												<td align="center" id="td_privince_content" colspan="4">&nbsp;</td>
											</tr>
											<tr>
												<td class="listtablehead" width="100">
													行政区域:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													${itemBean.DISTNAME}
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="100">
													频次名称:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												${itemBean.ALLPCMC}<br>
												<input type="text" name="PCMC" id="PCMC" class="ac_input" value="${itemBean.PCMC}" onFocus="this.select();"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" >
													频次类别:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													${itemBean.PCSXSTR}
												</td>
											</tr>
											<tr>
												<td class="listtablehead" >
													频次范围:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													${itemBean.CQUSXXUSSTR}
												</td>
											</tr>
											<tr>
												<td align="center" id="td_privince_content" colspan="4">&nbsp;</td>
											</tr>
											<tr>
												<td class="listtablehead" >
													核心截止时间:
												</td>
												<td class="js_left_txt" id="td_privince_content">
												${itemBean.HXJZSJ}</td>
												<td class="listtablehead" >
													核心时间间隔:
												</td>
												<td class="js_left_txt" id="td_privince_content">
												${itemBean.HXJZJG}</td>
											</tr>
											<tr>
												<td align="center" id="td_privince_content" colspan="4">&nbsp;</td>
											</tr>
											<tr>
												<td class="listtablehead" >
													非核心频次截止时间:
												</td>
												<td class="js_left_txt" id="td_privince_content">
												${itemBean.FHXJZSJ}</td>
												<td class="listtablehead" >
													非核心频次时间间隔:
												</td>
												<td class="js_left_txt" id="td_privince_content">
												${itemBean.FHXJZJG}</td>
											</tr>
											<tr>
												<td align="center" id="td_privince_content" colspan="4">&nbsp;</td>
											</tr>
											<tr>
												<td class="listtablehead" width="100">
													说明:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="README" id="README" class="ac_input" value="${itemBean.README}" onFocus="this.select();"/>
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
