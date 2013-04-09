<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/address/addr.jsp"%>
<html>

	<head>
		<title>街道编辑</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var flag='Add';
			$(document).ready(function(){
				if('${streetBean.DIST_CD}' != ''){
					$("#PROVINCE_NAMES").val('${streetBean.DIST_CD}'.substr(0,2) + '0000');
					$("#upquery").show();
					$("#addquery").hide();
				}else{
					$("#upquery").hide();
					$("#addquery").show();
				}
				viewcity();
				shName('noabbr');
			})
			//展示市
			function viewcity(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tRulUsCityOffice= null;
				if(isMunicipalities(tPROVINCE_NAME)){
			    	$('#CITY_NAMES').hide();
					$('#CITY_NAMES option').remove();
			    }else{
					$('#CITY_NAMES').show();
				}
				var url = '${ctx}/address/b04r03addroption!citys.action';
				var params = {
					PROVINCE_NAME: tPROVINCE_NAME
				 };
				jQuery.post(url, params, callbackcity, 'json');
			}
			function callbackcity(data){
				var d=data.districtBeans;
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				if(isMunicipalities(tPROVINCE_NAME)){
					$('#COUNTY_NAMES option').remove();
					$("#COUNTY_NAMES").append("<option value=''>请选择</option>"); 
			    	for(var i=0;i<d.length;i++){
						if('${streetBean.DIST_CD}' != '' && '${streetBean.DIST_CD}' == d[i].DISTRICT_CODE){
							$("#COUNTY_NAMES").append("<option selected='selected' value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
						}else{
							$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
						}
					}
			    }else{
					$('#CITY_NAMES option').remove();
					for(var i=0;i<d.length;i++){
						if('${streetBean.DIST_CD}' != '' && '${streetBean.DIST_CD}'.substr(0,4) + '00' == d[i].DISTRICT_CODE){
							$("#CITY_NAMES").append("<option selected='selected' value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
						}else{
							$("#CITY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
						}
					}
					viewcounty();
			    }
			}
			
			function viewcounty(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 			
				if(!isMunicipalities(tPROVINCE_NAME)){
					var tCITY_NAME=$("#CITY_NAMES").find("option:selected").text(); 
					var url = '${ctx}/address/b04r03addroption!countys.action';
					var params = {
						CITY_NAME: tCITY_NAME
					 };
					jQuery.post(url, params, callbackcounty, 'json');
				}
			}
			function callbackcounty(data){
				$('#COUNTY_NAMES option').remove();
				$("#COUNTY_NAMES").append("<option value=''>请选择</option>"); 
				var d=data.districtBeans;
				for(var i=0;i<d.length;i++){
					if('${streetBean.DIST_CD}' != '' && '${streetBean.DIST_CD}' == d[i].DISTRICT_CODE){
						$("#COUNTY_NAMES").append("<option selected='selected' value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
					}else{
							$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
					}
				}
			}
			function ajaxAddUpAlert(tflag){
				flag = tflag;
				var tDIST_CD='';
				var tDIST_CDnum = 1;
				if($("#COUNTY_NAMES").val() != null){
					tDIST_CD=$("#COUNTY_NAMES").val();
					tDIST_CDnum=$("#COUNTY_NAMES").get(0).options.length;
				}
				if(tDIST_CD.length < 2 && tDIST_CDnum < 2){
					tDIST_CD=$("#CITY_NAMES").val();
				}
				var tSTRT_ID=$("#STRT_ID").val();
				var tSTRT1_NAME=$('#STRT1_NAME').val().replace(new RegExp(' ', 'g'), '');
				var tSTRT2_NAME=$('#STRT2_NAME').val().replace(new RegExp(' ', 'g'), '');
				var tSTRT3_NAME=$('#STRT3_NAME').val().replace(new RegExp(' ', 'g'), '');
				var tSTRT4_NAME=$('#STRT4_NAME').val().replace(new RegExp(' ', 'g'), '');
				var tSTRT5_NAME=$('#STRT5_NAME').val().replace(new RegExp(' ', 'g'), '');
				if(tSTRT1_NAME.length > 20){
					$.prompt('街道一段字数超过20',{buttons: {'确定': true}});
					return false;
				}
				if(tSTRT2_NAME.length > 20){
					$.prompt('街道二段字数超过20',{buttons: {'确定': true}});
					return false;
				}
				if(tSTRT3_NAME.length > 20){
					$.prompt('街道三段字数超过20',{buttons: {'确定': true}});
					return false;
				}
				if(tSTRT4_NAME.length > 20){
					$.prompt('街道四段字数超过20',{buttons: {'确定': true}});
					return false;
				}
				if(tSTRT5_NAME.length > 20){
					$.prompt('街道五段字数超过20',{buttons: {'确定': true}});
					return false;
				}
				if(tDIST_CD==''){
					tDIST_CD=$("#CITY_NAMES").val();
					if(!isTrueDistID(tDIST_CD)){
					$.prompt('选择城区名',{buttons: {'确定': true}});
					return false;
					}
				}
				if(tSTRT1_NAME==''){
					$.prompt('请填入街道名',{buttons: {'确定': true}});
					return false;
				}
				var tPOST_CD=$('#POST_CD').val();
				var regEx = /[^0-9]+/gi ;
				/*
				if(tPOST_CD == ''){
					$.prompt('邮编不能为空',{buttons: {'确定': true}});
					return false;
				}
				*/
				if(tPOST_CD.length >0 && (tPOST_CD.length <6 || regEx.test(tPOST_CD) || tPOST_CD<'000000' || tPOST_CD>'999999')){
					$.prompt('请填入正确的邮编',{buttons: {'确定': true}});
					return false;
				}
				var params = {
						STRT_ID:tSTRT_ID,
						DIST_CD:tDIST_CD,
						STRT1_NAME:tSTRT1_NAME,
						STRT2_NAME:tSTRT2_NAME,
						STRT3_NAME:tSTRT3_NAME,
						STRT4_NAME:tSTRT4_NAME,
						STRT5_NAME:tSTRT5_NAME
	            };
				if(flag=='Add'){
					var url = '${ctx}/address/b04cud02addrconfig!alertaddstreet.action';
				}else{
					var url = '${ctx}/address/b04cud02addrconfig!alertupdatestreet.action';
				}
			    jQuery.post(url, params, callajaxAddUpAlert, 'json');
			}
			function callajaxAddUpAlert(data){
				if(data.saveMessage=="true"){
					overajaxAddUpAlert();
				}else{
					$.prompt(data.saveMessage,{
						buttons: {'确定': true, '取消': false },
						callback: function(v){
							if(v){
								overajaxAddUpAlert();
							}
						}
					});
				}
			}
			function overajaxAddUpAlert(){
				if(flag=='Add'){
					ajaxAddUp();
				}else{
					$.prompt('该操作将修改此街道！确认执行操作？',{
						buttons: {'确定': true, '取消': false },
						callback: function(v){
							if(v){
								ajaxAddUp();
							}
						}
					});
				}
			}
			function ajaxAddUp(){
				var tDIST_CD='';
				var tDIST_CDnum = 1;
				if($("#COUNTY_NAMES").val() != null){
					tDIST_CD=$("#COUNTY_NAMES").val();
					tDIST_CDnum=$("#COUNTY_NAMES").get(0).options.length;
				}
				if(tDIST_CD.length < 2 && tDIST_CDnum < 2){
					tDIST_CD=$("#CITY_NAMES").val();
				}
				var tSTRT_ID=$("#STRT_ID").val();
				var tSTRT1_NAME=$('#STRT1_NAME').val().replace(new RegExp(' ', 'g'), '');
				var tSTRT2_NAME=$('#STRT2_NAME').val().replace(new RegExp(' ', 'g'), '');
				var tSTRT3_NAME=$('#STRT3_NAME').val().replace(new RegExp(' ', 'g'), '');
				var tSTRT4_NAME=$('#STRT4_NAME').val().replace(new RegExp(' ', 'g'), '');
				var tSTRT5_NAME=$('#STRT5_NAME').val().replace(new RegExp(' ', 'g'), '');
				var tSTRT1_ABBR_NAME=$('#STRT1_ABBR_NAME').val();
				var tSTRT2_ABBR_NAME=$('#STRT2_ABBR_NAME').val();
				var tSTRT3_ABBR_NAME=$('#STRT3_ABBR_NAME').val();
				var tSTRT4_ABBR_NAME=$('#STRT4_ABBR_NAME').val();
				var tSTRT5_ABBR_NAME=$('#STRT5_ABBR_NAME').val();
				var tSTRT_ABBR=$('#STRT_ABBR').val().replace(new RegExp(' ', 'g'), '');
				var tPOST_CD=$('#POST_CD').val();		
				var tNOTE=$('#NOTE').val();
				var params = {
						STRT_ID:tSTRT_ID,
						DIST_CD:tDIST_CD,
						POST_CD:tPOST_CD,
						STRT1_NAME:tSTRT1_NAME,
						STRT2_NAME:tSTRT2_NAME,
						STRT3_NAME:tSTRT3_NAME,
						STRT4_NAME:tSTRT4_NAME,
						STRT5_NAME:tSTRT5_NAME,
						STRT1_ABBR_NAME:tSTRT1_ABBR_NAME,
						STRT2_ABBR_NAME:tSTRT2_ABBR_NAME,
						STRT3_ABBR_NAME:tSTRT3_ABBR_NAME,
						STRT4_ABBR_NAME:tSTRT4_ABBR_NAME,
						STRT5_ABBR_NAME:tSTRT5_ABBR_NAME,
						STRT_ABBR:tSTRT_ABBR,
						NOTE:tNOTE
	            };
				if(flag=='Add'){
					var url = '${ctx}/address/b04cud02addrconfig!addstreet.action';
				}else{
					var url = '${ctx}/address/b04cud02addrconfig!upstreet.action';
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
			function shName(flag){
				if($('#STRT1_NAME').val().length>0){
					$('#tdSTRT2_NAME').show();
					if($('#STRT2_NAME').val().length>0){
						$('#tdSTRT3_NAME').show();
						if($('#STRT3_NAME').val().length>0){
							$('#tdSTRT4_NAME').show();
							if($('#STRT4_NAME').val().length>0){
								$('#tdSTRT5_NAME').show();
							}else{
								$('#tdSTRT5_NAME').hide();
								$('#STRT5_NAME').val('');
							}
						}else{
							$('#tdSTRT4_NAME').hide();
							$('#tdSTRT5_NAME').hide();
							$('#STRT4_NAME').val('');
							$('#STRT5_NAME').val('');
						}
					}else{
						$('#tdSTRT3_NAME').hide();
						$('#tdSTRT4_NAME').hide();
						$('#tdSTRT5_NAME').hide();
						$('#STRT3_NAME').val('');
						$('#STRT4_NAME').val('');
						$('#STRT5_NAME').val('');
					}
				}else{
					$('#tdSTRT2_NAME').hide();
					$('#tdSTRT3_NAME').hide();
					$('#tdSTRT4_NAME').hide();
					$('#tdSTRT5_NAME').hide();
					$('#STRT2_NAME').val('');
					$('#STRT3_NAME').val('');
					$('#STRT4_NAME').val('');
					$('#STRT5_NAME').val('');
				}
				if(flag=='abbr'){
					getPYf();
				}
			}
			function getPYf(){
				var totalSTRT_NAME=$('#STRT1_NAME').val() + $('#STRT2_NAME').val() + $('#STRT3_NAME').val() + $('#STRT4_NAME').val() + $('#STRT5_NAME').val();
				$('#STRT_ABBR').val(getPY(totalSTRT_NAME));
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
										街道编辑
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<input id="STRT_ID" type="hidden" value="${streetBean.STRT_ID}"/>
								<a id="upquery" href='#' onClick="ajaxAddUpAlert('Up')"  style="font-size:12px">修改</a>
								<a id="addquery" href='#' onClick="ajaxAddUpAlert('Add')"  style="font-size:12px">新增</a>
								
								<a id="queryback" href='#' onClick="backQuery('fresh')"  style="font-size:12px">返回</a>
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
												<td class="listtablehead" colspan="4">
														<font color="#FF0000">带*号的必须填写</font>
												</td>												
											</tr>
											<tr>
												<td class="listtablehead" colspan="4">
														<font color="#FF0000">（新增街道需要审核通过后才能批段）</font>
												</td>												
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													行政区域:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty()"  style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES"  style="width:100"></select>
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													街道:
												</td>
												<td class="js_left_txt" id="td_city_content"  colspan="3">
													<table class="listtable" id="viewquery_table"  width="100%">
													<tr>
														<td class="listtablehead" colspan="8">
														<font color="#FF0000">（此处仅维护街道名，不要维护门牌号）</font>
														</td>												
													</tr>
													<tr>
														<td class="listtablehead">
														街道1段:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT1_NAME" style="width: 150; height:20px;" id="STRT1_NAME" class="ac_input" size="300" onKeyUp="shName('abbr')" value="${streetBean.STRT1_NAME}" onFocus="this.select();" onBlur="getPYf();"/><font color="#FF0000">&nbsp;*</font>
														</td>	
														<td class="listtablehead">
														别名:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT1_ABBR_NAME" style="width: 150; height:20px;" id="STRT1_ABBR_NAME" class="ac_input" size="300" value="${streetBean.STRT1_ABBR_NAME}" onFocus="this.select();"/>
														</td>													
													</tr>
													<tr style="display:none" id="tdSTRT2_NAME">
														<td class="listtablehead">
														街道2段:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT2_NAME" style="width: 150px; height:20px;" id="STRT2_NAME" class="ac_input" size="300" onKeyUp="shName('abbr')" value="${streetBean.STRT2_NAME}" onFocus="this.select();"  onBlur="getPYf();"/>
														</td>
														<td class="listtablehead">
														别名:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT2_ABBR_NAME" style="width: 150; height:20px;" id="STRT2_ABBR_NAME" class="ac_input" size="300" value="${streetBean.STRT2_ABBR_NAME}" onFocus="this.select();"/>
														</td>
													</tr>
													<tr style="display:none" id="tdSTRT3_NAME">
														<td class="listtablehead">
														街道3段:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT3_NAME" style="width: 150px; height:20px;" id="STRT3_NAME" class="ac_input" size="300" onKeyUp="shName('abbr')" value="${streetBean.STRT3_NAME}" onFocus="this.select();"  onBlur="getPYf();"/>
														</td>
														<td class="listtablehead">
														别名:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT3_ABBR_NAME" style="width: 150; height:20px;" id="STRT3_ABBR_NAME" class="ac_input" size="300" value="${streetBean.STRT3_ABBR_NAME}" onFocus="this.select();"/>
														</td>
													</tr>
													<tr style="display:none" id="tdSTRT4_NAME">
														<td class="listtablehead">
														街道4段:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT4_NAME" style="width: 150px; height:20px;" id="STRT4_NAME" class="ac_input" size="300"  onKeyUp="shName('abbr')" value="${streetBean.STRT4_NAME}" onFocus="this.select();"  onBlur="getPYf();"/>
														</td>
														<td class="listtablehead">
														别名:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT4_ABBR_NAME" style="width: 150; height:20px;" id="STRT4_ABBR_NAME" class="ac_input" size="300" value="${streetBean.STRT4_ABBR_NAME}" onFocus="this.select();"/>
														</td>
													</tr>
													<tr style="display:none" id="tdSTRT5_NAME">
														<td class="listtablehead">
														街道5段:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT5_NAME" style="width: 150px; height:20px;" id="STRT5_NAME" class="ac_input" size="300" onKeyUp="shName('abbr')" value="${streetBean.STRT5_NAME}" onFocus="this.select();"  onBlur="getPYf();"/>
														</td>
														<td class="listtablehead">
														别名:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT5_ABBR_NAME" style="width: 150; height:20px;" id="STRT5_ABBR_NAME" class="ac_input" size="300" value="${streetBean.STRT5_ABBR_NAME}" onFocus="this.select();"/>
														</td>
													</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													街道简拼:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="STRT_ABBR" style="width: 300px; height:20px;" id="STRT_ABBR" class="ac_input" size="300" value="${streetBean.STRT_ABBR}" onFocus="this.select();"/>
												</td>
											</tr>
											<tr id="tr_DORPLT_ID">
												<td class="listtablehead" width="80">
													邮编:
												</td>
												<td class="js_left_txt" id="td_POST_CD" colspan="3">
													<input type="text" name="POST_CD" style="width: 300px; height:20px;" id="POST_CD" class="ac_input" size="300" value="${streetBean.POST_CD}" onFocus="this.select();" maxlength="6"/>
												</td>
											</tr>
											<tr id="tr_DORPLT_ID">
												<td class="listtablehead" width="80">
													备注:
												</td>
												<td class="js_left_txt" id="td_NOTE" colspan="3">
													<input type="text" name="NOTE" style="width: 300px; height:20px;" id="NOTE" class="ac_input" size="300" value="${streetBean.NOTE}" onFocus="this.select();"/>
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
