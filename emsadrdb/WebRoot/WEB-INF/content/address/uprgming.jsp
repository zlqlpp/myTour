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
		<title>地址学习</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				viewcity();
				shName('abbr');
				shNameorg('abbr');
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
						$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
			    	viewDT();
			    }else{
					$('#CITY_NAMES option').remove();
					for(var i=0;i<d.length;i++){
						$("#CITY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
					viewcounty();
			    }
				viewqzui();
				viewhzui();
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
					$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
				}
				viewDT();
			}
			function viewDT(){
				var tPROVINCE_NAME=$("#COUNTY_NAMES").find("option:selected").text();
				var tCITY_CODE=$("#COUNTY_NAMES").val();
				if($("#COUNTY_NAMES").val() != null && $("#COUNTY_NAMES").val() != ''){
					tCITY_CODE=$("#COUNTY_NAMES").val();
				}else if($("#CITY_NAMES").val() != null && $("#CITY_NAMES").val() != ''){
					tCITY_CODE=$("#CITY_NAMES").val();
				}else if($("#PROVINCE_NAMES").val() != null && $("#PROVINCE_NAMES").val() != ''){
					tCITY_CODE=$("#PROVINCE_NAMES").val();
				}
				if(isMunicipalitiesID(tCITY_CODE.substr(0,2))){
					var url = '${ctx}/address/b04r03addroption!dts.action';
				}else{
					var url = '${ctx}/address/b04r03addroption!dtsfromdisp.action';
				}
				var params = {
					CITY_CODE: tCITY_CODE
				 };
				jQuery.post(url, params, callbackDT, 'json');
			}
			function callbackDT(data){
				$('#DT_PK_CODE option').remove();
				var d=data.cpBeans;
				$("#DT_PK_CODE").append("<option value=''>请选择</option>"); 
				if(d.length>0){
					for(var i=0;i<d.length;i++){
						if('${exgcltitemopeBean.DT_PK_CODE}' != '' && '${exgcltitemopeBean.DT_PK_CODE}' == d[i].DT_PK_CODE){
							$("#DT_PK_CODE").append("<option selected='selected' value='" + d[i].DT_PK_CODE + "'>" + d[i].DT_NAME + "</option>"); 
						}else{
							$("#DT_PK_CODE").append("<option value='" + d[i].DT_PK_CODE + "'>" + d[i].DT_NAME + "</option>"); 
						}
					}
					if($("#DT_PK_CODE").val().length>1){
						viewDM();
					}else{
						$('#DM_PK_CODE option').remove();
						$("#DM_PK_CODE").append("<option value=''>请选择</option>"); 
						$('#PG_PK_CODE option').remove();
						$("#PG_PK_CODE").append("<option value=''>请选择</option>"); 
					}
				}
			}
			
			function viewDM(){
				var tDT_PK_CODE=$("#DT_PK_CODE").val();
				if(tDT_PK_CODE.length>0){
					var url = '${ctx}/address/b04r03addroption!dms.action';
					var params = {
						DT_PK_CODE: tDT_PK_CODE
					 };
					jQuery.post(url, params, callbackDM, 'json');
				}else{
					$('#DM_PK_CODE option').remove();
					$("#DM_PK_CODE").append("<option value=''>请选择</option>"); 
					$('#PG_PK_CODE option').remove();
					$("#PG_PK_CODE").append("<option value=''>请选择</option>"); 
				}
			}
			function callbackDM(data){
				$('#DM_PK_CODE option').remove();
				var d=data.dmBeans;
				$("#DM_PK_CODE").append("<option value=''>请选择</option>"); 
				if(d.length>0){
					for(var i=0;i<d.length;i++){
						if('${exgcltitemopeBean.DM_PK_CODE}' != '' && '${exgcltitemopeBean.DM_PK_CODE}' == d[i].DM_PK_CODE){
							$("#DM_PK_CODE").append("<option selected='selected' value='" + d[i].DM_PK_CODE + "'>" + d[i].DM_NAME + "</option>");
						}else{
							$("#DM_PK_CODE").append("<option value='" + d[i].DM_PK_CODE + "'>" + d[i].DM_NAME + "</option>");
						}
					}
					if($("#DM_PK_CODE").val().length>1){
						viewPG();
					}else{
						$('#PG_PK_CODE option').remove();
						$("#PG_PK_CODE").append("<option value=''>请选择</option>"); 
					}
				}
			}
			
			function viewPG(){
				var tDM_PK_CODE=$("#DM_PK_CODE").val();
				if(tDM_PK_CODE.length>0){
					var url = '${ctx}/address/b04r03addroption!pgs.action';
					var params = {
						DM_PK_CODE: tDM_PK_CODE
					 };
					jQuery.post(url, params, callbackPG, 'json');
				}else{
					$('#PG_PK_CODE option').remove();
					$("#PG_PK_CODE").append("<option value=''>请选择</option>"); 
				}
			}
			function callbackPG(data){
				$('#PG_PK_CODE option').remove();
				var d=data.pgBeans;
				$("#PG_PK_CODE").append("<option value=''>请选择</option>"); 
				if(d.length>0){
					for(var i=0;i<d.length;i++){
						if('${exgcltitemopeBean.PG_PK_CODE}' != '' && '${exgcltitemopeBean.PG_PK_CODE}' == d[i].PG_PK_CODE){
							$("#PG_PK_CODE").append("<option selected='selected' value='" + d[i].PG_PK_CODE + "'>" + d[i].PG_NAME + "</option>"); 
						}else{
							$("#PG_PK_CODE").append("<option value='" + d[i].PG_PK_CODE + "'>" + d[i].PG_NAME + "</option>"); 
						}
					}
				}
			}
			function viewqzui(){
				var tDIST_CD='';
				if($("#COUNTY_NAMES").val() != null && $("#COUNTY_NAMES").val().length>0){
					tDIST_CD=$("#COUNTY_NAMES").val();
				}else if($("#CITY_NAMES").val() != null && $("#CITY_NAMES").val().length>0){
					tDIST_CD=$("#CITY_NAMES").val();
				}else{
					tDIST_CD=$("#PROVINCE_NAMES").val();
				}
				if(!isMunicipalitiesID(tDIST_CD.substr(0,2))){
					tDIST_CD=tDIST_CD.substr(0,4) + '00';
				}else{
					tDIST_CD=tDIST_CD.substr(0,2) + '0000';
				}
				var url = '${ctx}/address/b04r03addroption!qzuis.action';
				var params = {
					DIST_CD: tDIST_CD
				 };
				jQuery.post(url, params, callbackqzuit, 'json');
				
			}
			
			
			function callbackqzuit(data){
				$('#PREFIX option').remove();
				var d=data.qhzuiBeans;
				if(d.length>0){
					$("#PREFIX").append("<option value=''>请选择</option>"); 
					for(var i=0;i<d.length;i++){
						if('${bldgrsdnsBean.PREFIX}' != '' && '${bldgrsdnsBean.PREFIX}' == d[i].FIX){
						$("#PREFIX").append("<option selected='selected' value='" + d[i].FIX + "'>" + d[i].FIX + "</option>"); 
					}else{
						$("#PREFIX").append("<option value='" + d[i].FIX + "'>" + d[i].FIX + "</option>"); 
					}
					}
				}
				else{
					$("#PREFIX").append("<option value=''>请选择</option>"); 
				}
			}
			
			function viewhzui(){
				var tDIST_CD='';
				if($("#COUNTY_NAMES").val() != null && $("#COUNTY_NAMES").val().length>0){
					tDIST_CD=$("#COUNTY_NAMES").val();
				}else if($("#CITY_NAMES").val() != null && $("#CITY_NAMES").val().length>0){
					tDIST_CD=$("#CITY_NAMES").val();
				}else{
					tDIST_CD=$("#PROVINCE_NAMES").val();
				}
				if(!isMunicipalitiesID(tDIST_CD.substr(0,2))){
					tDIST_CD=tDIST_CD.substr(0,4) + '00';
				}else{
					tDIST_CD=tDIST_CD.substr(0,2) + '0000';
				}
				var url = '${ctx}/address/b04r03addroption!hzuis.action';
				var params = {
					DIST_CD: tDIST_CD
				 };
				jQuery.post(url, params, callbackhzuit, 'json');
			}
			function callbackhzuit(data){
				$('#SUFFIX option').remove();
				var d=data.qhzuiBeans;
				if(d.length>0){
					for(var i=0;i<d.length;i++){
						if('${bldgrsdnsBean.SUFFIX}' != '' && '${bldgrsdnsBean.SUFFIX}' == d[i].FIX){
						$("#SUFFIX").append("<option selected='selected' value='" + d[i].FIX + "'>" + d[i].FIX + "</option>"); 
					}else{
						$("#SUFFIX").append("<option value='" + d[i].FIX + "'>" + d[i].FIX + "</option>"); 
					}
					}
				}
				else{
					$("#SUFFIX").append("<option value='号'>号</option>"); 
				}
			}
			function ajaxAddUp(flag){
				var tSEQID='${exgcltitemopeBean.SEQID}';
				var tITEMNO='${exgcltitemopeBean.SEQID}';
				var tREC_ALLADDR='${exgcltitemopeBean.REC_ALLADDR}';
				var tREC_ORG='${exgcltitemopeBean.REC_ORG}';
				var tDIST_CD='';
				var tDIST_CDnum = 1;
				if($("#COUNTY_NAMES").val() != null){
					tDIST_CD=$("#COUNTY_NAMES").val();
					tDIST_CDnum=$("#COUNTY_NAMES").get(0).options.length;
				}
				if(tDIST_CD.length < 2 && tDIST_CDnum < 2){
					tDIST_CD=$("#CITY_NAMES").val();
				}
				var tDT_PK_CODE=$('#DT_PK_CODE').val();
				var tDM_PK_CODE=$('#DM_PK_CODE').val();
				var tPG_PK_CODE=$('#PG_PK_CODE').val();
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
				var tRSDNBLDG_NAME=$('#RSDNBLDG_NAME').val();
				var tRSDNBLDG_ABBR=$('#RSDNBLDG_ABBR').val();
				var tORG_NAME=$('#ORG_NAME').val();
				var tORG_ABBR_NAME=$('#ORG_ABBR_NAME').val();
				var tORG_ABBR=$('#ORG_ABBR').val();
				var tNUM_FLAG=$("input[name='NUM_FLAG'][checked]").val(); 
				var tPREFIX=$('#PREFIX').val();
				var tSUFFIX=$('#SUFFIX').val();
				var tSTART_NUM=$('#START_NUM').val();
				var tEND_NUM=$('#END_NUM').val();
				if(tSTART_NUM.length>0 && tEND_NUM.length<1){
					tEND_NUM=tSTART_NUM;
				}
				var tNOTE=$('#NOTE').val();
				if(tDIST_CD==''){
					$.prompt('选择城区名',{buttons: {'确定': true}});
					return false;
				}
				if(tSTRT1_NAME==''){
					$.prompt('请填入街道名',{buttons: {'确定': true}});
					return false;
				}
				var tPOST_CD=$('#POST_CD').val();
				var regEx = /[^0-9]+/gi ;
				if(tPOST_CD == ''){
					$.prompt('邮编不能为空',{buttons: {'确定': true}});
					return false;
				}
				if(tPOST_CD.length >0 && (tPOST_CD.length <6 || regEx.test(tPOST_CD) || tPOST_CD<'000000' || tPOST_CD>'999999')){
					$.prompt('请填入正确的邮编',{buttons: {'确定': true}});
					return false;
				}
				if(tSTART_NUM.length >0 && regEx.test(tSTART_NUM)){
					$.prompt('请填入正确的门牌',{buttons: {'确定': true}});
					return false;
				}
				if(tEND_NUM.length >0 && regEx.test(tEND_NUM)){
					$.prompt('请填入正确的门牌',{buttons: {'确定': true}});
					return false;
				}
				if(tEND_NUM.length < 1 && (tRSDNBLDG_NAME.length>0 || tORG_NAME.length>0)){
					$.prompt('请填入机构和小区的门牌号',{buttons: {'确定': true}});
					return false;
				}
				if(tDT_PK_CODE==''){
					$.prompt('请选择投递区',{buttons: {'确定': true}});
					return false;
				}
				if(tDM_PK_CODE==''){
					$.prompt('请选择投递部',{buttons: {'确定': true}});
					return false;
				}
				if(tPG_PK_CODE==''){
					$.prompt('请选择投递段',{buttons: {'确定': true}});
					return false;
				}
				var params = {
						SEQID:tSEQID,
						ITEMNO:tITEMNO,
						REC_ALLADDR:tREC_ALLADDR,
						REC_ORG:tREC_ORG,
						DIST_CD:tDIST_CD,
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
						RSDNBLDG_NAME:tRSDNBLDG_NAME,
						RSDNBLDG_ABBR:tRSDNBLDG_ABBR,
						ORG_NAME:tORG_NAME,
						ORG_ABBR_NAME:tORG_ABBR_NAME,
						ORG_ABBR:tORG_ABBR,
						NUM_FLAG:tNUM_FLAG,
						PREFIX:tPREFIX,
						SUFFIX:tSUFFIX,
						START_NUM:tSTART_NUM,
						END_NUM:tEND_NUM,
						POST_CD:tPOST_CD,
						DT_PK_CODE:tDT_PK_CODE,
						DM_PK_CODE:tDM_PK_CODE,
						PG_PK_CODE:tPG_PK_CODE,
						NOTE:tNOTE
	            };
				var url = '${ctx}/address/b04cud02addrconfig!uprgming.action';
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
			    jQuery.post(url, params, callajaxAdd, 'json');
			}
			function callajaxAdd(data){
				$.prompt(data.saveMessage,{buttons: {'确定': true}});
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
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
			function shNameorg(flag){
				if(flag=='abbr'){
					var tORG_NAME=$('#ORG_NAME').val();
					$('#ORG_ABBR').val(getPY(tORG_NAME));
				}
			}
			function shNUM_FLAG(flag){
				if($('#START_NUM').val().length>0){
					$('#tr_END_NUM').show();
				}else{
					$('#tr_END_NUM').hide();
					$('#END_NUM').val('');
					$('#tr_NUM_FLAG').hide();
					$("input[name='NUM_FLAG'][value=4]").attr("checked",'checked');
				}
				if(parseInt($('#START_NUM').val())<parseInt($('#END_NUM').val())){
						$('#tr_NUM_FLAG').show();
				}else{
						$('#tr_NUM_FLAG').hide();
						$('#END_NUM').val($('#START_NUM').val());
						$("input[name='NUM_FLAG'][value=4]").attr("checked",'checked');
				}
			}
		</script>
	</head>
	<body>
<div id="nodata" style="display:none">
<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />
</div>
<div id="yesdata">
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
										地址学习
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<a id="addquery" href='#' onClick="ajaxAddUp('Add')"  style="font-size:12px">添加</a>
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
											<tr id="tr_PROVINCE_NAME">
												<td class="listtablehead" width="80">
													原地址:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3" width="500">
													街道:${exgcltitemopeBean.REC_ALLADDR}
													机构:${exgcltitemopeBean.REC_ORG}
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													行政区域:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty();viewqzui();viewhzui();"  style="width:100"></select>
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
															<input type="text" name="STRT1_NAME" style="width: 150; height:20px;" id="STRT1_NAME" class="ac_input" size="300" onKeyUp="shName('abbr')" value="${exgcltitemopeBean.REC_ALLADDR}" onFocus="this.select();" onBlur="getPYf();"/><font color="#FF0000">&nbsp;*</font>
														</td>	
														<td class="listtablehead">
														别名:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT1_ABBR_NAME" style="width: 150; height:20px;" id="STRT1_ABBR_NAME" class="ac_input" size="300"  onFocus="this.select();"/>
														</td>													
													</tr>
													<tr style="display:none" id="tdSTRT2_NAME">
														<td class="listtablehead">
														街道2段:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT2_NAME" style="width: 150px; height:20px;" id="STRT2_NAME" class="ac_input" size="300" onKeyUp="shName('abbr')" onFocus="this.select();"  onBlur="getPYf();"/>
														</td>
														<td class="listtablehead">
														别名:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT2_ABBR_NAME" style="width: 150; height:20px;" id="STRT2_ABBR_NAME" class="ac_input" size="300"  onFocus="this.select();"/>
														</td>
													</tr>
													<tr style="display:none" id="tdSTRT3_NAME">
														<td class="listtablehead">
														街道3段:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT3_NAME" style="width: 150px; height:20px;" id="STRT3_NAME" class="ac_input" size="300" onKeyUp="shName('abbr')"  onFocus="this.select();"  onBlur="getPYf();"/>
														</td>
														<td class="listtablehead">
														别名:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT3_ABBR_NAME" style="width: 150; height:20px;" id="STRT3_ABBR_NAME" class="ac_input" size="300"  onFocus="this.select();"/>
														</td>
													</tr>
													<tr style="display:none" id="tdSTRT4_NAME">
														<td class="listtablehead">
														街道4段:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT4_NAME" style="width: 150px; height:20px;" id="STRT4_NAME" class="ac_input" size="300"  onKeyUp="shName('abbr')"  onFocus="this.select();"  onBlur="getPYf();"/>
														</td>
														<td class="listtablehead">
														别名:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT4_ABBR_NAME" style="width: 150; height:20px;" id="STRT4_ABBR_NAME" class="ac_input" size="300" onFocus="this.select();"/>
														</td>
													</tr>
													<tr style="display:none" id="tdSTRT5_NAME">
														<td class="listtablehead">
														街道5段:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT5_NAME" style="width: 150px; height:20px;" id="STRT5_NAME" class="ac_input" size="300" onKeyUp="shName('abbr')" onFocus="this.select();"  onBlur="getPYf();"/>
														</td>
														<td class="listtablehead">
														别名:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="STRT5_ABBR_NAME" style="width: 150; height:20px;" id="STRT5_ABBR_NAME" class="ac_input" size="300"  onFocus="this.select();"/>
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
												<input type="text" name="STRT_ABBR" style="width: 200px; height:20px;" id="STRT_ABBR" class="ac_input" size="200" onFocus="this.select();"/>
												</td>
											</tr>
											<tr id="tr_DORPLT_ID">
												<td class="listtablehead" width="100">
													门牌:
												</td>
												<td class="js_left_txt" id="td_DORPLT_ID" colspan="3">
													<table class="listtable" id="viewquery_table"  width="100%">
													<tr>
														<td class="listtablehead">
														前缀:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
													<select class="simple" id="PREFIX" style="width:130"></select>
														</td>														
													</tr>
													<tr id="tdSTRT2_NAME">
														<td class="listtablehead">
														开始门牌:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="START_NUM" style="width: 100px; height:20px;" id="START_NUM" class="ac_input" size="100" onFocus="this.select();" onKeyUp="shNUM_FLAG('abbr')"/><font color="#FF0000">&nbsp;(有门牌需要维护门牌)</font>
														</td>
													</tr>
													<tr  id="tr_END_NUM"  style="display:none">
														<td class="listtablehead">
														结束门牌:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="END_NUM" style="width: 100px; height:20px;" id="END_NUM" class="ac_input" size="100"  onFocus="this.select();" onChange="shNUM_FLAG('abbr')"/>
														</td>
													</tr>
													<tr id="tdSTRT4_NAME">
														<td class="listtablehead">
														后缀:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
													<select class="simple" id="SUFFIX" style="width:130"></select>
														</td>
													</tr>
													<tr id="tr_NUM_FLAG"  style="display:none">
														<td class="listtablehead">
														门牌单/双:
														</td>
														<td class="js_left_txt" id="td_NUM_FLAG" colspan="3">
															<input type="radio" name="NUM_FLAG" id="NUM_FLAG" value="4" checked="checked">不分<input type="radio" name="NUM_FLAG" id="NUM_FLAG" value="5">单<input type="radio" name="NUM_FLAG" id="NUM_FLAG" value="6">双
														</td>
													</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													小区名称:
												</td>
												<td class="js_left_txt" id="td_privince_content">
												<input type="text" name="RSDNBLDG_NAME" style="width: 200px; height:20px;" id="RSDNBLDG_NAME" class="ac_input" size="200" onFocus="this.select();" />
												</td>
												<td class="listtablehead" width="80">
													别名:
												</td>
												<td class="js_left_txt" id="td_privince_content">
												<input type="text" name="RSDNBLDG_ABBR" style="width: 100px; height:20px;" id="RSDNBLDG_ABBR" class="ac_input" size="100"  onFocus="this.select();"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													机构名称:
												</td>
												<td class="js_left_txt" id="td_privince_content">
												<input type="text" name="ORG_NAME" style="width: 200px; height:20px;" id="ORG_NAME" class="ac_input" size="200" onFocus="this.select();" onKeyUp="shNameorg('abbr');" value="${exgcltitemopeBean.REC_ORG}" onBlur="shNameorg('abbr');"/>
												</td>
												<td class="listtablehead" width="80">
													别名:
												</td>
												<td class="js_left_txt" id="td_privince_content">
												<input type="text" name="ORG_ABBR_NAME" style="width: 100px; height:20px;" id="ORG_ABBR_NAME" class="ac_input" size="100" onFocus="this.select();"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													机构简拼:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="ORG_ABBR" style="width: 200px; height:20px;" id="ORG_ABBR" class="ac_input" size="200" onFocus="this.select();"/>
												</td>
											</tr>
											<tr id="tr_DORPLT_ID">
												<td class="listtablehead" width="80">
													邮编:
												</td>
												<td class="js_left_txt" id="td_POST_CD" colspan="3">
													<input type="text" name="POST_CD" style="width: 200px; height:20px;" id="POST_CD" class="ac_input" size="200" onFocus="this.select();" maxlength="6"/><font color="#FF0000">&nbsp;*</font>
												</td>
											</tr>
											<tr id="tr_DORPLT_ID">
												<td class="listtablehead" width="80">
													备注:
												</td>
												<td class="js_left_txt" id="td_NOTE" colspan="3">
													<input type="text" name="NOTE" style="width: 200px; height:20px;" id="NOTE" class="ac_input" size="200" onFocus="this.select();"/>
												</td>
											</tr>
											<tr id="tr_DT_PK_CODE_q">
												<td class="listtablehead" width="100">
													投递区:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<select class="simple" id="DT_PK_CODE" onChange="viewDM()"  style="width:200"><option value=''>请选择</option></select>
												</td>
											</tr>
											<tr id="tr_DM_PK_CODE_b">
												<td class="listtablehead" width="100">
													投递部:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<select class="simple" id="DM_PK_CODE" onChange="viewPG()"  style="width:200"><option value=''>请选择</option></select>
												</td>
											</tr>
											<tr id="tr_PG_PK_CODE_d">
												<td class="listtablehead" width="100">
													投递段:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<select class="simple" id="PG_PK_CODE"  style="width:200"><option value=''>请选择</option></select>
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
</div>
	</body>
</html>
