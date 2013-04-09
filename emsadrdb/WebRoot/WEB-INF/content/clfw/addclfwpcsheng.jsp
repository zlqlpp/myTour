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
		<title>省处理中心频次配置编辑</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var flag='Add';
			$(document).ready(function(){
				$("#CLFWPC_NAMES").prepend("<option value=''>请选择</option>"); 
				$("#CLFWPC_NAMES").val('');
				if('${clfwBean.CLFWPCSHENG_SEQID}' != ''){
					$("#PROVINCE_NAMES").val('${clfwBean.CLFWPCSHENG_DISTCD}');
					$("#CLFWPC_NAMES").val('${clfwBean.CLFWPC_SEQID}');
					$("#upquery").show();
					$("#addquery").hide();
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
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				if(tPROVINCE_ID==''){
					$.prompt('请选择行政区域',{buttons: {'确定': true}});
					return false;
				}
				var tCLFWPC_NAMES=$('#CLFWPC_NAMES').val();
				if(tCLFWPC_NAMES==''){
					$.prompt('请选择省中心/直达频次',{buttons: {'确定': true}});
					return false;
				}
				var tCLFWPCSHENG_SHENGM=$('#CLFWPCSHENG_SHENGM').val();
				if(tCLFWPCSHENG_SHENGM==''){
					$.prompt('请填入省处理中心名称',{buttons: {'确定': true}});
					return false;
				}
				if(flag=='Add'){
					ajaxAddUp(flag);
				}else{
					$.prompt('该操作将修改此省处理中心频次！确认执行操作？',{
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
				var tCLFWPCSHENG_SHENGM=$('#CLFWPCSHENG_SHENGM').val();				
				if(tCLFWPCSHENG_SHENGM.length>80){
					$.prompt('省处理中心名称字符大于80',{buttons: {'确定': true}});
					return false;
				}
				var tCLFWPCSHENG_SEQID=$('#CLFWPCSHENG_SEQID').val();
				var tCLFWPC_NAMES=$('#CLFWPC_NAMES').val();
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				var tCLFWPCSHENG_SM=$("#CLFWPCSHENG_SM").val();
				if(tCLFWPCSHENG_SM.length>80){
					$.prompt('说明字符大于80',{buttons: {'确定': true}});
					return false;
				}

				var regEx = /[^0-9]+/gi;
				var tCLFWPCSHENG_JGDM=$("#CLFWPCSHENG_JGDM").val();
				if(tCLFWPCSHENG_JGDM==''){
					$.prompt('机构代码字符不能为空',{buttons: {'确定': true}});
					return false;
				}else if(tCLFWPCSHENG_JGDM.length>100){
					$.prompt('机构代码字符位数大于100位',{buttons: {'确定': true}});
					return false;
				}else if(tCLFWPCSHENG_JGDM.length<8){
					$.prompt('机构代码字符位数小于8位',{buttons: {'确定': true}});
					return false;
				}else if(regEx.test(tCLFWPCSHENG_JGDM.replace(new RegExp(",","gm"),""))){
					$.prompt('请填入数字',{buttons: {'确定': true}});
					return false;
				}

				
				var params = {
						CLFWPCSHENG_SEQID:tCLFWPCSHENG_SEQID,
						CLFWPCSHENG_SHENGM:tCLFWPCSHENG_SHENGM,
						CLFWPC_SEQID:tCLFWPC_NAMES,
						CLFWPCSHENG_DISTCD:tPROVINCE_ID,
						CLFWPCSHENG_SM:tCLFWPCSHENG_SM,
						CLFWPCSHENG_JGDM:tCLFWPCSHENG_JGDM
	            };
				if(flag=='Add'){
					var url = '${ctx}/clfw/b09cud02clfwconfig!addclfwpcsheng.action';
				}else{
					var url = '${ctx}/clfw/b09cud02clfwconfig!upclfwpcsheng.action';
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
										省处理中心频次配置编辑
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<input id="CLFWPCSHENG_SEQID" type="hidden" value="${clfwBean.CLFWPCSHENG_SEQID}"/>
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
														<br>*省中心/直达频次名称属性标志,【空白】省中心频次，【Z】直达频次,例如0100-省中心频次,Z0100-直达频次
														<br>* 【省处理中心名称】,【说明】字数要小于80字
														<br>* 全网统一规范各省处理中心作业频次名称
														<br>* 城市(2-4字)+地名或约定名称(2-4字)，城市名称中一般不含"市，州，县"等行政区划名称
													</font>
												</td>
											</tr>
											<tr>
													<td class="listtablehead" width="140">
														行政区域选择:
													</td>
													<td class="js_left_txt" id="td_privince_content">
														<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME"></s:select>
													</td>
											</tr>
											<tr>
												<td class="listtablehead" width="140">
													省中心/直达频次选择:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="clfwpcs" id="CLFWPC_NAMES" listKey="CLFWPC_SEQID" listValue="CLFWPC_MC"></s:select>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="140">
													省处理中心名称:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="CLFWPCSHENG_SHENGM" style="width: 300px; height:20px;" id="CLFWPCSHENG_SHENGM" class="ac_input" size="300" value="${clfwBean.CLFWPCSHENG_SHENGM}" onFocus="this.select();"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="140">
													机构代码<br>(8位机构代码)<br>(以【,】分割):
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="CLFWPCSHENG_JGDM" style="width: 300px; height:20px;" id="CLFWPCSHENG_JGDM" class="ac_input" size="300" value="${clfwBean.CLFWPCSHENG_JGDM}" onFocus="this.select();"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="140">
													说明:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="CLFWPCSHENG_SM" style="width: 300px; height:20px;" id="CLFWPCSHENG_SM" class="ac_input" size="300" value="${clfwBean.CLFWPCSHENG_SM}" onFocus="this.select();"/>
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
