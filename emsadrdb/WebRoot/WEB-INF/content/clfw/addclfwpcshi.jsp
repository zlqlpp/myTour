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
		<title>市[揽/投]衔接频次配置</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var flag='Add';
			$(document).ready(function(){
				$("#CLFWPC_NAMES").prepend("<option value=''>请选择</option>"); 
				if('${clfwBean.CLFWPCSHI_SEQID}' != ''){
					
					var pnt = '${clfwBean.CLFWPCSHI_DISTCD}';
					$("#PROVINCE_NAMES").val(pnt.substr(0,2)+'0000');
					
					var pntNULL = '${clfwBean.CLFWPCSHENG_DISTCD}';
					$("#PROVINCE_NAMES_NULL").val(pntNULL.substr(0,2)+'0000');
					
					$("input[name='CLFWPC_SX'][value=${clfwBean.CLFWPC_SX}]").attr("checked",'checked');

					$("input[name='CLFWPCSHI_JZYH'][value=${clfwBean.CLFWPCSHI_JZYH}]").attr("checked",'checked');
					
					$("#upquery").show();
					$("#addquery").hide();
				}else{

					$("#PROVINCE_NAMES_NULL").val($("#PROVINCE_NAMES").val());
					
					$("#upquery").hide();
					$("#addquery").show();
				}
				viewcity();
				viewCLFWPC();
				viewCLFWPC_SHENGNAMES();

				$("#fliphelpct").click(function(){
					$("#fliphelpcon").slideToggle("fast");
				});
			})
			function viewCLFWPC(){
				var tCLFWPC_SX=$("input[name='CLFWPC_SX'][checked]").val();
				var url = '${ctx}/clfw/b09r03clfwoption!clfwpcs.action';
				var params = {
						CITY_NAME:"",
						CLFWPC_SX:tCLFWPC_SX
				 };
				jQuery.post(url, params, callbackCLFWPC, 'json');
			}
			function callbackCLFWPC(data){
				$('#CLFWPC_NAMES option').remove();
				var d=data.clfwpcsBeans;
				$("#CLFWPC_NAMES").append("<option value=''>请选择</option>"); 
				if(d.length>0){
					for(var i=0;i<d.length;i++){
						$("#CLFWPC_NAMES").append("<option value='" + d[i].CLFWPC_SEQID + "'>" + d[i].CLFWPC_MC + "</option>");
					}
				}
				$("#CLFWPC_NAMES").val('${clfwBean.CLFWPC_SEQID}');
			}
			
			function viewCLFWPC_SHENGNAMES(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES_NULL").val();
				if(tPROVINCE_NAME.length>0){
					var url = '${ctx}/clfw/b09r03clfwoption!clfwpcssheng.action';
					var params = {
							CITY_NAME:tPROVINCE_NAME,
							CLFWPC_SX:'1'
					 };
					jQuery.post(url, params, callbackCLFWPC_SHENGNAMES, 'json');
				}
			}
			function callbackCLFWPC_SHENGNAMES(data){
				$('#CLFWPCSHENG_SHENGM option').remove();
				var d=data.clfwpcsshengBeans;
				$("#CLFWPCSHENG_SHENGM").append("<option value=''>请选择</option>"); 
				if(d.length>0){
					for(var i=0;i<d.length;i++){
						$("#CLFWPCSHENG_SHENGM").append("<option value='" + d[i].CLFWPCSHENG_SEQID + "'>" + d[i].CLFWPCSHENG_SHENAGM + "</option>");
					}
				}
				$("#CLFWPCSHENG_SHENGM").val('${clfwBean.CLFWPCSHENG_SEQID}');
			}
			
			//展示市
			function viewcity(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var url = '${ctx}/clfw/b09r03clfwoption!citys.action';
				var params = {
					PROVINCE_NAME: tPROVINCE_NAME
				 };
				if(isMunicipalities(tPROVINCE_NAME)){
			    	$('#CITY_NAMES').hide();
					$('#CITY_NAMES option').remove();
			    }else{
					$('#CITY_NAMES').show();
				}
				jQuery.post(url, params, callbackcity, 'json');
			}
			function callbackcity(data){
				var d=data.districtBeans;
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				
				if(isMunicipalities(tPROVINCE_NAME)){
					$('#COUNTY_NAMES option').remove();
					$("#COUNTY_NAMES").prepend("<option selected='selected' value=''>请选择</option>");
			    	for(var i=0;i<d.length;i++){
						$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
			    }else{
				    
					$('#CITY_NAMES option').remove();
					for(var i=0;i<d.length;i++){
						$("#CITY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}

					if('${clfwBean.CLFWPCSHI_SEQID}' != ''){

						var pnt = '${clfwBean.CLFWPCSHI_DISTCD}';
	
						$("#CITY_NAMES").val(pnt.substr(0,4)+'00');
						
						if(!$("#CITY_NAMES").val().length<1){
							
							$("#CITY_NAMES").val(pnt);
							
						}

					}
					
					viewcounty();
			    }

			}
			function viewcounty(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 			
				if(!isMunicipalities(tPROVINCE_NAME)){
					var tCITY_NAME=$("#CITY_NAMES").find("option:selected").text(); 
					if(tCITY_NAME!='请选择'){
						var url = '${ctx}/clfw/b09r03clfwoption!countys.action';
						var params = {
							CITY_NAME: tCITY_NAME
						 };
						jQuery.post(url, params, callbackcounty, 'json');
					}
				}
			}
			function callbackcounty(data){
				$('#COUNTY_NAMES option').remove();
				var d=data.districtBeans;
				$("#COUNTY_NAMES").prepend("<option selected='selected' value=''>请选择</option>");
				for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
				}

				var pnt = '${clfwBean.CLFWPCSHI_DISTCD}';

				$("#COUNTY_NAMES").val(pnt);
			}
			function ajaxAddUpAlert(tflag){
				flag = tflag;
				var tCLFWPC_NAMES=$('#CLFWPC_NAMES').val();
				if(tCLFWPC_NAMES==''){
					$.prompt('请选择市衔接频次',{buttons: {'确定': true}});
					return false;
				}
				var tCLFWPCSHENG_SHENGM=$('#CLFWPCSHENG_SHENGM').val();
				if(tCLFWPCSHENG_SHENGM==''){
					$.prompt('请选择省处理中心频次名称',{buttons: {'确定': true}});
					return false;
				}
				var tCLFWPCSHI_SHIGM=$('#CLFWPCSHI_SHIGM').val();
				if(tCLFWPCSHI_SHIGM==''){
					$.prompt('请输入市衔接频次名称',{buttons: {'确定': true}});
					return false;
				}
				if(flag=='Add'){
					ajaxAddUp(flag);
				}else{
					$.prompt('该操作将修改此市衔接频次！确认执行操作？',{
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
				var tCLFWPCSHI_SM=$('#CLFWPCSHI_SM').val();				
				if(tCLFWPCSHI_SM.length>80){
					$.prompt('说明字符大于80',{buttons: {'确定': true}});
					return false;
				}
				
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				var tCITY_ID=$("#COUNTY_NAMES").val();
				if(!(tCITY_ID.length>0)){
					if(isMunicipalities(tPROVINCE_NAME)){
						tCITY_ID=tPROVINCE_ID;
				    }else{
						tCITY_ID=$("#CITY_NAMES").val();
					}
				}
				
				var tCLFWPC_NAMES=$('#CLFWPC_NAMES').val();
				var tCLFWPCSHENG_SHENGM=$('#CLFWPCSHENG_SHENGM').val();
				var tCLFWPCSHI_SHIGM=$('#CLFWPCSHI_SHIGM').val();
				if(tCLFWPCSHI_SHIGM.length>80){
					$.prompt('市衔接频次名称字符大于80',{buttons: {'确定': true}});
					return false;
				}
				var tCLFWPCSHI_SEQID=$('#CLFWPCSHI_SEQID').val();

				var tCLFWPCSHI_JZYH=$("input[name='CLFWPCSHI_JZYH'][checked]").val();

				var regEx = /[^0-9]+/gi;

				var tCLFWPCSHI_JZSJ=$('#CLFWPCSHI_JZSJ').val();

				

				if(tCLFWPCSHI_JZSJ.length > 0){
					if(tCLFWPCSHI_JZSJ.length!=4){
						$.prompt('邮航揽收截止点位数不等于4',{buttons: {'确定': true}});
						return false;
					}else if(!(tCLFWPCSHI_JZSJ.substr(0,2) >= '00' && tCLFWPCSHI_JZSJ.substr(0,2) <= '23')){
						$.prompt('邮航揽收截止点小时填写不对',{buttons: {'确定': true}});
						return false;
					}else if(!(tCLFWPCSHI_JZSJ.substr(2,2) >= '00' && tCLFWPCSHI_JZSJ.substr(2,2) <= '59')){
						$.prompt('邮航揽收截止点分钟填写不对',{buttons: {'确定': true}});
						return false;
					}else if(regEx.test(tCLFWPCSHI_JZSJ)){
						$.prompt('请填入4位数字',{buttons: {'确定': true}});
						return false;
					}
				}
				
				var params = {
						CLFWPC_SEQID:tCLFWPC_NAMES,
						CLFWPCSHI_SEQID:tCLFWPCSHI_SEQID,
						CLFWPCSHENG_SEQID:tCLFWPCSHENG_SHENGM,
						CLFWPCSHI_DISTCD:tCITY_ID,
						CLFWPCSHI_SM:tCLFWPCSHI_SM,
						CLFWPCSHI_SHIGM:tCLFWPCSHI_SHIGM,
						CLFWPCSHI_JZYH:tCLFWPCSHI_JZYH,
						CLFWPCSHI_JZSJ:tCLFWPCSHI_JZSJ
	            };
				if(flag=='Add'){
					var url = '${ctx}/clfw/b09cud02clfwconfig!addclfwpcshi.action';
				}else{
					var url = '${ctx}/clfw/b09cud02clfwconfig!upclfwpcshi.action';
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
										市[揽/投]衔接频次配置
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<input id="CLFWPCSHI_SEQID" type="hidden" value="${clfwBean.CLFWPCSHI_SEQID}"/>
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
														<br>* 【市配置频次名称】,【说明】字数要小于80字
														<br>* 揽收频次命名规则：城市(2-4字)+"揽收"，城市名称中一般不含"市，州，县"等行政区划名称
														<br>* 投递频次命名规则：城市(2-4字)+"投递"，城市名称中一般不含"市，州，县"等行政区划名称
														<br>* 有本市直达频次的请选择直达频次，类似【浙江-嘉兴】
														<br>* 有区县配置的信息才需要选择【城区】例如【江苏-苏州-太仓】，不需要区县配置的选择本市即可,不要选择到区县
														<br>* 有跨省处理中心的，请选择【外省配置省中心/直达频次信息】，本省不要配置外省省中心/直达频次信息,例如【内蒙-齐齐哈尔】的【1340】频次的【海拉尔揽收】衔接【黑龙江】的【哈尔滨航站】
													</font>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="100">
													行政区域选择:
												</td>
												<td class="js_left_txt" id="td_privince_content">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity();"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty()" style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES" style="width:100"></select>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="140">
													省处理中心频次选择:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="provincesnull" id="PROVINCE_NAMES_NULL" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewCLFWPC_SHENGNAMES();"></s:select>
													<select class="simple" id="CLFWPCSHENG_SHENGM" style="width:300"></select>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="140">
													揽收/投递频次属性:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<input onclick="viewCLFWPC();" type="radio" name="CLFWPC_SX" id="CLFWPC_SX" value="2" checked="checked">揽收
													<input onclick="viewCLFWPC();" type="radio" name="CLFWPC_SX" id="CLFWPC_SX" value="3">投递
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="140">
													揽收/投递频次选择:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<select class="simple" id="CLFWPC_NAMES" style="width:300"></select>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="140">
													市配置频次名称:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="CLFWPCSHI_SHIGM" style="width: 300px; height:20px;" id="CLFWPCSHI_SHIGM" class="ac_input" size="300" value="${clfwBean.CLFWPCSHI_SHIGM}" onFocus="this.select();"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="140">
													邮航揽收截止点:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="CLFWPCSHI_JZSJ" style="width: 100px; height:20px;" id="CLFWPCSHI_JZSJ" class="ac_input" size="100" value="${clfwBean.CLFWPCSHI_JZSJ}" onFocus="this.select();"/>
												<font id="fliphelpcon" color="red">
														* 填写【揽收】【邮航】的截止时间点类似【2010】4位数字
												</font>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="140">
													是否同邮航频次:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<input type="radio" name="CLFWPCSHI_JZYH" id="CLFWPCSHI_JZYH" value="1">是
													<input type="radio" name="CLFWPCSHI_JZYH" id="CLFWPCSHI_JZYH" value="0" checked="checked">否
													<font id="fliphelpcon" color="red">
														* 选择本市没有【揽收】【邮航】频次中的同【邮航】频次的市频次为【是】，其余频次为否
													</font>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="140">
													说明:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="CLFWPCSHI_SM" style="width: 300px; height:20px;" id="CLFWPCSHI_SM" class="ac_input" size="300" value="${clfwBean.CLFWPCSHI_SM}" onFocus="this.select();"/>
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
