<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/clfw/clfw.jsp"%>
<%@ include file="/scripts/My97DatePicker/My97DatePicker.jsp"%>
<html>

	<head>
		<title>民航航班执行情况填报</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var flag='Add';
			$(document).ready(function(){

				if('${FLAG}' == 'ADD'){
					
					$("#upquery").hide();
					$("#addquery").show();

					
				}else if('${FLAG}' == 'UPDATE'){
					
					$("#upquery").show();
					$("#addquery").hide();

					var pnt = '${clfwBean.CLFW_HBXXGL_SFJID}';
					$("#PROVINCE_NAMES_SFJ").val(pnt.substr(0,2)+'0000');

					var pntt = '${clfwBean.CLFW_HBXXGL_ZDJID}';
					$("#PROVINCE_NAMES_ZDJ").val(pntt.substr(0,2)+'0000');

					document.getElementById("PROVINCE_NAMES_SFJ").disabled=true;
					document.getElementById("CITY_NAMES_SFJ").disabled=true;
					document.getElementById("COUNTY_NAMES_SFJ").disabled=true;
					document.getElementById("CLFW_HBXXGL_CSRQ").disabled=true;
					document.getElementById("PROVINCE_NAMES_ZDJ").disabled=true;
					document.getElementById("CITY_NAMES_ZDJ").disabled=true;
					document.getElementById("COUNTY_NAMES_ZDJ").disabled=true;
					
				}
				viewcity_SFJ();
				viewcity_ZDJ();
			})
			
			//展示市
			function viewcity_SFJ(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES_SFJ").find("option:selected").text(); 
				var url = '${ctx}/clfw/b09r03clfwoption!citysnull.action';
				var params = {
					PROVINCE_NAME: tPROVINCE_NAME
				 };
				if(isMunicipalities(tPROVINCE_NAME)){
			    	$('#CITY_NAMES_SFJ').hide();
					$('#CITY_NAMES_SFJ option').remove();
			    }else{
					$('#CITY_NAMES_SFJ').show();
				}
				jQuery.post(url, params, callbackcity_SFJ, 'json');
			}
			function callbackcity_SFJ(data){
				
				var d=data.districtBeans;
				var tPROVINCE_NAME=$("#PROVINCE_NAMES_SFJ").find("option:selected").text(); 
				if(isMunicipalities(tPROVINCE_NAME)){
					$('#COUNTY_NAMES_SFJ option').remove();
					$("#COUNTY_NAMES_SFJ").prepend("<option selected='selected' value=''>请选择</option>");
			    	for(var i=0;i<d.length;i++){
						$("#COUNTY_NAMES_SFJ").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
					
			    	$("#COUNTY_NAMES_SFJ").val('${clfwBean.CLFW_HBXXGL_SFJID}');
			    	
			    }else{
					$('#CITY_NAMES_SFJ option').remove();
					for(var i=0;i<d.length;i++){
						$("#CITY_NAMES_SFJ").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}

					if('${clfwBean.CLFW_HBXXGL_SEQID}' != ''){
						
						var pnt = '${clfwBean.CLFW_HBXXGL_SFJID}';

						$("#CITY_NAMES_SFJ").val(pnt.substr(0,4)+'00');
						
						if(!$("#CITY_NAMES_SFJ").val().length<1){
							
							$("#CITY_NAMES_SFJ").val(pnt);
							
						}

					}
					
					viewcounty_SFJ();
			    }
			}

			function viewcounty_SFJ(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES_SFJ").find("option:selected").text(); 			
				if(!isMunicipalities(tPROVINCE_NAME)){
					var tCITY_NAME=$("#CITY_NAMES_SFJ").find("option:selected").text(); 
					if(tCITY_NAME!='请选择'){
						var url = '${ctx}/clfw/b09r03clfwoption!countys.action';
						var params = {
							CITY_NAME: tCITY_NAME
						 };
						jQuery.post(url, params, callbackviewcounty_SFJ, 'json');
					}
				}
			}
			function callbackviewcounty_SFJ(data){
				$('#COUNTY_NAMES_SFJ option').remove();
				var d=data.districtBeans;
				$("#COUNTY_NAMES_SFJ").prepend("<option selected='selected' value=''>请选择</option>");
				for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMES_SFJ").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
				}
				$("#COUNTY_NAMES_SFJ").val('${clfwBean.CLFW_HBXXGL_SFJID}');
			}

			//展示市
			function viewcity_ZDJ(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES_ZDJ").find("option:selected").text(); 
				var url = '${ctx}/clfw/b09r03clfwoption!citys.action';
				var params = {
					PROVINCE_NAME: tPROVINCE_NAME
				 };
				if(isMunicipalities(tPROVINCE_NAME)){
			    	$('#CITY_NAMES_ZDJ').hide();
					$('#CITY_NAMES_ZDJ option').remove();
			    }else{
					$('#CITY_NAMES_ZDJ').show();
				}
				jQuery.post(url, params, callbackcity_ZDJ, 'json');
			}
			function callbackcity_ZDJ(data){
				var d=data.districtBeans;
				var tPROVINCE_NAME=$("#PROVINCE_NAMES_ZDJ").find("option:selected").text(); 
				if(isMunicipalities(tPROVINCE_NAME)){
					$('#COUNTY_NAMES_ZDJ option').remove();
					$("#COUNTY_NAMES_ZDJ").prepend("<option selected='selected' value=''>请选择</option>");
			    	for(var i=0;i<d.length;i++){
						$("#COUNTY_NAMES_ZDJ").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
			    	$("#COUNTY_NAMES_ZDJ").val('${clfwBean.CLFW_HBXXGL_ZDJID}');
			    }else{
					$('#CITY_NAMES_ZDJ option').remove();
					for(var i=0;i<d.length;i++){
						$("#CITY_NAMES_ZDJ").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}

					if('${clfwBean.CLFW_HBXXGL_SEQID}' != ''){
						
						var pnt = '${clfwBean.CLFW_HBXXGL_ZDJID}';

						$("#CITY_NAMES_ZDJ").val(pnt.substr(0,4)+'00');
						
						if(!$("#CITY_NAMES_ZDJ").val().length<1){
							
							$("#CITY_NAMES_ZDJ").val(pnt);
							
						}

					}
					
					viewcounty_ZDJ();
			    }

			}

			function viewcounty_ZDJ(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES_ZDJ").find("option:selected").text(); 			
				if(!isMunicipalities(tPROVINCE_NAME)){
					var tCITY_NAME=$("#CITY_NAMES_ZDJ").find("option:selected").text(); 
					if(tCITY_NAME!='请选择'){
						var url = '${ctx}/clfw/b09r03clfwoption!countysnull.action';
						var params = {
							CITY_NAME: tCITY_NAME
						 };
						jQuery.post(url, params, callbackviewcounty_ZDJ, 'json');
					}
				}
			}
			function callbackviewcounty_ZDJ(data){
				$('#COUNTY_NAMES_ZDJ option').remove();
				var d=data.districtBeans;
				$("#COUNTY_NAMES_ZDJ").prepend("<option selected='selected' value=''>请选择</option>");
				for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMES_ZDJ").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
				}
				$("#COUNTY_NAMES_ZDJ").val('${clfwBean.CLFW_HBXXGL_ZDJID}');
			}
			
			function ajaxAddUpAlert(tflag){
				flag = tflag;
				var tCLFW_HBXXGL_CSRQ=$('#CLFW_HBXXGL_CSRQ').val();	
				if(tCLFW_HBXXGL_CSRQ==''){
					$.prompt('请配置出港日期',{buttons: {'确定': true}});
					return false;
				}
				var tCLFW_HBXXGL_HBH=$('#CLFW_HBXXGL_HBH').val();		
				if(tCLFW_HBXXGL_HBH==''){
					$.prompt('请配置航班号',{buttons: {'确定': true}});
					return false;
				}
				
				var tCLFW_HBXXGL_LDZBS=$('#CLFW_HBXXGL_LDZBS').val();
				if(tCLFW_HBXXGL_LDZBS==''){
					$.prompt('请填写路单总包数',{buttons: {'确定': true}});
					return false;
				}
				
				var tCLFW_HBXXGL_SSZBS=$('#CLFW_HBXXGL_SSZBS').val();
				if(tCLFW_HBXXGL_SSZBS==''){
					$.prompt('请填写实收总包数',{buttons: {'确定': true}});
					return false;
				}
				
				if(flag=='Add'){
					ajaxAddUp(flag);
				}else{
					$.prompt('该操作将修改此进出港信息配置！确认执行操作？',{
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
				var tCLFW_HBXXGL_CSRQ=$('#CLFW_HBXXGL_CSRQ').val();				
				if(tCLFW_HBXXGL_CSRQ.length>8){
					$.prompt('日期字符大于8',{buttons: {'确定': true}});
					return false;
				}
				var tCLFW_HBXXGL_HBH=$('#CLFW_HBXXGL_HBH').val();				
				if(tCLFW_HBXXGL_HBH.length>20){
					$.prompt('航班号大于20',{buttons: {'确定': true}});
					return false;
				}

				var tCLFW_HBXXGL_LDZBS=$('#CLFW_HBXXGL_LDZBS').val();
				var tCLFW_HBXXGL_SSZBS=$('#CLFW_HBXXGL_SSZBS').val();

				var regEx = /[^0-9]+/gi;
				
				if(tCLFW_HBXXGL_LDZBS.length >0 && regEx.test(tCLFW_HBXXGL_LDZBS.replace('.',''))){
					$.prompt('请填入正确路单总包数',{buttons: {'确定': true}});
					return false;
				}
				if(tCLFW_HBXXGL_SSZBS.length >0 && regEx.test(tCLFW_HBXXGL_SSZBS.replace('.',''))){
					$.prompt('请填入正确实际总包数',{buttons: {'确定': true}});
					return false;
				}

				var tCOUNTY_ID_SFJ = $("#COUNTY_NAMES_SFJ").val();
				if(tCOUNTY_ID_SFJ.length < 1){
					var tPROVINCE_NAME_SFJ=$("#PROVINCE_NAMES_SFJ").find("option:selected").text(); 
					var tPROVINCE_ID_SFJ=$("#PROVINCE_NAMES_SFJ").val();
					if(isMunicipalities(tPROVINCE_NAME_SFJ)){
						tCOUNTY_ID_SFJ=tPROVINCE_ID_SFJ;
				    }else{
				    	tCOUNTY_ID_SFJ=$("#CITY_NAMES_SFJ").val();
					}
				}

				var tCOUNTY_ID_ZDJ = $("#COUNTY_NAMES_ZDJ ").val();
				if(tCOUNTY_ID_ZDJ .length < 1){
					var tPROVINCE_NAME_ZDJ =$("#PROVINCE_NAMES_ZDJ ").find("option:selected").text(); 
					var tPROVINCE_ID_ZDJ =$("#PROVINCE_NAMES_ZDJ ").val();
					if(isMunicipalities(tPROVINCE_NAME_ZDJ)){
						tCOUNTY_ID_ZDJ =tPROVINCE_ID_ZDJ ;
				    }else{
				    	tCOUNTY_ID_ZDJ=$("#CITY_NAMES_ZDJ").val();
					}
				}
				
				var tCLFW_HBXXGL_SEQID=$('#CLFW_HBXXGL_SEQID').val();

				if(tCOUNTY_ID_ZDJ == tCOUNTY_ID_SFJ){
					$.prompt('进出港城市相同',{buttons: {'确定': true}});
					return false;
				}

				var params = {
						CLFW_HBXXGL_CSRQ:tCLFW_HBXXGL_CSRQ,
						CLFW_HBXXGL_HBH:tCLFW_HBXXGL_HBH,
						CLFW_HBXXGL_LDZBS:tCLFW_HBXXGL_LDZBS,
						CLFW_HBXXGL_SSZBS:tCLFW_HBXXGL_SSZBS,
						CLFW_HBXXGL_SFJID:tCOUNTY_ID_SFJ,
						CLFW_HBXXGL_ZDJID:tCOUNTY_ID_ZDJ,
						CLFW_HBXXGL_SEQID:tCLFW_HBXXGL_SEQID
	            };
				if(flag=='Add'){
					var url = '${ctx}/clfw/b09cud02clfwconfig!addclfwhkwhjg.action';
				}else{
					var url = '${ctx}/clfw/b09cud02clfwconfig!upclfwhkwhjg.action';
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
										出车信息配置
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<input id="CLFW_HBXXGL_SEQID" type="hidden" value="${clfwBean.CLFW_HBXXGL_SEQID}"/>
								<a id="upquery" href='#' onClick="ajaxAddUpAlert('Up')"  style="font-size:12px">填写</a>
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
														<br>* 【出港日期】字数要小于8字,【航班号】字数要小于10字
														<br>* 【总包数】 【重量】 【路单总包数】 【实收总包数】要纯数字
													</font>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="100">
													始发局:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES_SFJ" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity_SFJ();"></s:select>
													<select class="simple" id="CITY_NAMES_SFJ"  onChange="viewcounty_SFJ()" style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES_SFJ" style="width:100"></select>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="100">
													出港日期:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<input type="text" name="CLFW_HBXXGL_CSRQ" style="width: 200px; height:20px;" id="CLFW_HBXXGL_CSRQ" class="ac_input" size="300"  value="${clfwBean.CLFW_HBXXGL_CSRQ}" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="140">
													航班号:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="CLFW_HBXXGL_HBH" style="width: 300px; height:20px;" id="CLFW_HBXXGL_HBH" class="ac_input" size="300" value="${clfwBean.CLFW_HBXXGL_HBH}" onFocus="this.select();"  onkeyup="value=value.toUpperCase()"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="100">
													终到局:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="provincesnull" id="PROVINCE_NAMES_ZDJ" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity_ZDJ();"></s:select>
													<select class="simple" id="CITY_NAMES_ZDJ"   onChange="viewcounty_ZDJ()" style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES_ZDJ" style="width:100"></select>
												</td>
											</tr>
											
											<tr id="trCLFW_HBXXGL_LDZBS">
												<td class="listtablehead" width="100">
													路单总包数:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="CLFW_HBXXGL_LDZBS" style="width: 300px; height:20px;" id="CLFW_HBXXGL_LDZBS" class="ac_input" size="300" value="${clfwBean.CLFW_HBXXGL_LDZBS}" onFocus="this.select();"/>
												</td>
											</tr>
											<tr id="trCLFW_HBXXGL_SSZBS">
												<td class="listtablehead" width="100">
													实收总包数:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="CLFW_HBXXGL_SSZBS" style="width: 300px; height:20px;" id="CLFW_HBXXGL_SSZBS" class="ac_input" size="300" value="${clfwBean.CLFW_HBXXGL_SSZBS}" onFocus="this.select();"/>
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
