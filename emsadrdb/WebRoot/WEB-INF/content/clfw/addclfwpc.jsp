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
		<title>频次配置(总部)编辑</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var flag='Add';
			$(document).ready(function(){
				if('${clfwBean.CLFWPC_SEQID}' != ''){
					$("#upquery").show();
					$("#addquery").hide();
					$("input[name='CLFWPC_SX'][value=${clfwBean.CLFWPC_SX}]").attr("checked",'checked');
				}else{
					$("#upquery").hide();
					$("#addquery").show();
				}
				
				$("#fliphelpct").click(function(){
					$("#fliphelpcon").slideToggle("fast");
				});
				
			})
			function ajaxAddUpAlert(tflag){
				flag = tflag;
				var tCLFWPC_MC=$('#CLFWPC_MC').val();
				if(tCLFWPC_MC==''){
					$.prompt('请输入频次名称',{buttons: {'确定': true}});
					return false;
				}
				if(flag=='Add'){
					ajaxAddUp(flag);
				}else{
					$.prompt('该操作将修改此频次！确认执行操作？',{
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
				var tCLFWPC_SEQID=$('#CLFWPC_SEQID').val();
				var tCLFWPC_MC=$('#CLFWPC_MC').val();
				if(tCLFWPC_MC.length>80){
					$.prompt('频次名称字符大于80',{buttons: {'确定': true}});
					return false;
				}
				var tCLFWPC_SM=$('#CLFWPC_SM').val();
				if(tCLFWPC_SM.length>80){
					$.prompt('频次说明字符大于80',{buttons: {'确定': true}});
					return false;
				}
				var tCLFWPC_SX=$("input[name='CLFWPC_SX'][checked]").val();
				var params = {
						CLFWPC_SEQID:tCLFWPC_SEQID,
						CLFWPC_MC:tCLFWPC_MC,
						CLFWPC_SM:tCLFWPC_SM,
						CLFWPC_SX:tCLFWPC_SX
	            };
				if(flag=='Add'){
					var url = '${ctx}/clfw/b09cud02clfwconfig!addclfwpc.action';
				}else{
					var url = '${ctx}/clfw/b09cud02clfwconfig!upclfwpc.action';
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
										频次配置(总部)编辑
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<input id="CLFWPC_SEQID" type="hidden" value="${clfwBean.CLFWPC_SEQID}"/>
								<a id="upquery" href='#' onClick="ajaxAddUpAlert('Up')"  style="font-size:12px">修改</a>
								<a id="addquery" href='#' onClick="ajaxAddUpAlert('Add')"  style="font-size:12px">新增</a>
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
														* 可以在本界面,连续维护数据
														<br>*【频次名称】,【说明】字数要小于80字
														<br>*省中心/直达频次名称属性标志,【空白】省中心频次，【Z】直达频次,例如0100-省中心频次,Z0100-直达频次
													</font>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="100">
													频次名称:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="CLFWPC_MC" style="width: 300px; height:20px;" id="CLFWPC_MC" class="ac_input" size="300" value="${clfwBean.CLFWPC_MC}" onFocus="this.select();"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="100">
													频次属性:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<input type="radio" name="CLFWPC_SX" id="CLFWPC_SX" value="1"  checked="checked">省中心/直达
													<input type="radio" name="CLFWPC_SX" id="CLFWPC_SX" value="2">揽收
													<input type="radio" name="CLFWPC_SX" id="CLFWPC_SX" value="3">投递
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="100">
													说明:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="CLFWPC_SM" style="width: 300px; height:20px;" id="CLFWPC_SM" class="ac_input" size="300" value="${clfwBean.CLFWPC_SM}" onFocus="this.select();"/>
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
