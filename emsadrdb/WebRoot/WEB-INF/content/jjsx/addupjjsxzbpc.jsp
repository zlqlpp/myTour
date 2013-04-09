<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/jjsx/jjsx.jsp"%>
<html>

	<head>
		<title>频次配置(总部)编辑</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var flag='Add';
			$(document).ready(function(){
				
				if('${itemBean.SEQID}' != ''){
					
					$("#upquery").show();
					$("#addquery").hide();

					$("input[name='PCSX'][value=${itemBean.PCSX}]").attr("checked",'checked');

					$("#PCSX0").attr("disabled",true);
					$("#PCSX1").attr("disabled",true);

					$("input[name='CQUSXXUS'][value=${itemBean.CQUSXXUS}]").attr("checked",'checked');

					$("#CQUSXXUS1").attr("disabled",true);
					$("#CQUSXXUS0").attr("disabled",true);

					
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

				if(flag=='Add'){
					ajaxAddUp(flag);
				}else{
					$.prompt('该操作将修改信息配置！确认执行操作？',{
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
				
				var regEx = /[^0-9]+/gi;
				var tSEQID=$('#SEQID').val();
				var tPCSX=$("input[name='PCSX'][checked]").val();
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

				var tCQUSXXUS=$("input[name='CQUSXXUS'][checked]").val();

				var tHXJZSJ=$('#HXJZSJ').val();
				var tHXJZJG=$('#HXJZJG').val();
				var tFHXJZSJ=$('#FHXJZSJ').val();
				var tFHXJZJG=$('#FHXJZJG').val();

				if(tHXJZSJ==''){
					$.prompt('核心截止时间不能为空',{buttons: {'确定': true}});
					return false;
				}else if(tHXJZSJ.length!=4){
					$.prompt('核心截止时间不等于4',{buttons: {'确定': true}});
					return false;
				}else if(!(tHXJZSJ.substr(0,2) >= '00' && tHXJZSJ.substr(0,2) <= '24')){
					$.prompt('核心截止时间小时填写不对',{buttons: {'确定': true}});
					return false;
				}else if(!(tHXJZSJ.substr(2,2) >= '00' && tHXJZSJ.substr(2,2) <= '59')){
					$.prompt('核心截止时间分钟填写不对',{buttons: {'确定': true}});
					return false;
				}else if(regEx.test(tHXJZSJ)){
					$.prompt('核心截止时间请填入数字',{buttons: {'确定': true}});
					return false;
				}

				
				if(tHXJZJG==''){
					$.prompt('核心截止间隔日期不能为空',{buttons: {'确定': true}});
					return false;
				}else if(!(tHXJZJG >= '0' && tHXJZJG <= '9')){
					$.prompt('核心截止间隔日期填写[0-9]不对',{buttons: {'确定': true}});
					return false;
				}else if(regEx.test(tHXJZJG)){
					$.prompt('核心截止间隔日期请填入数字',{buttons: {'确定': true}});
					return false;
				}

				if(tFHXJZSJ==''){
					$.prompt('非核心截止时间不能为空',{buttons: {'确定': true}});
					return false;
				}else if(tFHXJZSJ.length!=4){
					$.prompt('非核心截止时间不等于4',{buttons: {'确定': true}});
					return false;
				}else if(!(tFHXJZSJ.substr(0,2) >= '00' && tFHXJZSJ.substr(0,2) <= '24')){
					$.prompt('非核心截止时间小时填写不对',{buttons: {'确定': true}});
					return false;
				}else if(!(tFHXJZSJ.substr(2,2) >= '00' && tFHXJZSJ.substr(2,2) <= '59')){
					$.prompt('非核心截止时间分钟填写不对',{buttons: {'确定': true}});
					return false;
				}else if(regEx.test(tFHXJZSJ)){
					$.prompt('非核心截止时间请填入数字',{buttons: {'确定': true}});
					return false;
				}

				
				if(tFHXJZJG==''){
					$.prompt('非核心截止间隔日期不能为空',{buttons: {'确定': true}});
					return false;
				}else if(!(tFHXJZJG >= '0' && tFHXJZJG <= '9')){
					$.prompt('非核心截止间隔日期填写[0-9]不对',{buttons: {'确定': true}});
					return false;
				}else if(regEx.test(tFHXJZJG)){
					$.prompt('非核心截止间隔日期请填入数字',{buttons: {'确定': true}});
					return false;
				}

				var params = {
						SEQID:tSEQID,
						PCMC:tPCMC,
						README:tREADME,
						PCSX:tPCSX,
						CQUSXXUS:tCQUSXXUS,
						HXJZSJ:tHXJZSJ,
						HXJZJG:tHXJZJG,
						FHXJZSJ:tFHXJZSJ,
						FHXJZJG:tFHXJZJG
	            };
	            
				if(flag=='Add'){
					var url = '${ctx}/jjsx/b10cud02jjsxconfig!addjjsxzbpc.action';
				}else{
					var url = '${ctx}/jjsx/b10cud02jjsxconfig!upjjsxzbpc.action';
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
								<input id="SEQID" type="hidden" value="${itemBean.SEQID}"/>
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
														*【频次名称】,【说明】字数要小于80字
														<br>【时间】为4位【1234】  【间隔】为1位【1】
													</font>
												</td>
											</tr>
											<tr>
												<td align="center" id="td_privince_content" colspan="4">&nbsp;</td>
											</tr>
											<tr>
												<td class="listtablehead" >
													频次名称:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="PCMC"  id="PCMC" class="ac_input" value="${itemBean.PCMC}" onFocus="this.select();" onkeyup="value=util_strUPTRIM(value)"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" >
													频次类别:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<input type="radio" name="PCSX" id="PCSX0" value="0">揽收
													<input type="radio" name="PCSX" id="PCSX1" value="1"  checked="checked">投递
												</td>
											</tr>
											<tr>
												<td class="listtablehead" >
													频次范围:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<input type="radio" name="CQUSXXUS" id="CQUSXXUS1" value="1"  checked="checked">城区
													<input type="radio" name="CQUSXXUS" id="CQUSXXUS0" value="0">辖县
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
												<input style="width: 80px; height:20px;" type="text" name="HXJZSJ"  id="HXJZSJ" class="ac_input" value="${itemBean.HXJZSJ}" onFocus="this.select();"/>
												</td>
												<td class="listtablehead" >
													核心时间间隔:
												</td>
												<td class="js_left_txt" id="td_privince_content">
												<input style="width: 50px; height:20px;" type="text" name="HXJZJG"  id="HXJZJG" class="ac_input" value="${itemBean.HXJZJG}" onFocus="this.select();"/>
												</td>
											</tr>
											<tr>
												<td align="center" id="td_privince_content" colspan="4">&nbsp;</td>
											</tr>
											<tr>
												<td class="listtablehead" >
													非核心截止时间:
												</td>
												<td class="js_left_txt" id="td_privince_content">
												<input style="width: 80px; height:20px;" type="text" name="FHXJZSJ"  id="FHXJZSJ" class="ac_input" value="${itemBean.FHXJZSJ}" onFocus="this.select();"/>
												</td>
												<td class="listtablehead" >
													非核心时间间隔:
												</td>
												<td class="js_left_txt" id="td_privince_content">
												<input style="width: 50px; height:20px;" type="text" name="FHXJZJG"  id="FHXJZJG" class="ac_input" value="${itemBean.FHXJZJG}" onFocus="this.select();"/>
												</td>
											</tr>
											<tr>
												<td align="center" id="td_privince_content" colspan="4">&nbsp;</td>
											</tr>
											<tr>
												<td class="listtablehead" >
													说明:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="README"  id="README" class="ac_input" value="${itemBean.README}" onFocus="this.select();"/>
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
