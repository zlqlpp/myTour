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
		<title>机构编辑</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var dtdmpgs_DT = "";
			var	dtdmpgs_DM = "";
			var	dtdmpgs_PG = "";
			$(document).ready(function(){
				if('${FLAG}' == 'ADD'){
					$("#PROVINCE_NAMES").val('${organizationBean.DIST_CD}'.substr(0,2) + '0000');
					viewcity();
					$("#upquery").hide();
					$("#addquery").show();
					$("#STRT_ID").append("<option value='${organizationBean.STRT_ID}'>${organizationBean.TOTAL_STREET_NAME}</option>");
					$('#SUFFIX').val("号");
				}
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
						if('${organizationBean.DIST_CD}' != '' && '${organizationBean.DIST_CD}' == d[i].DISTRICT_CODE){
							$("#COUNTY_NAMES").append("<option selected='selected' value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
						}else{
							$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
						}
					}
					viewDT();
			    }else{
					$('#CITY_NAMES option').remove();
					for(var i=0;i<d.length;i++){
						if('${organizationBean.DIST_CD}' != '' && '${organizationBean.DIST_CD}'.substr(0,4) + '00' == d[i].DISTRICT_CODE){
							$("#CITY_NAMES").append("<option selected='selected' value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
						}else{
							$("#CITY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
						}
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
				var d=data.districtBeans;
				$("#COUNTY_NAMES").append("<option value=''>请选择</option>"); 
				for(var i=0;i<d.length;i++){
					if('${organizationBean.DIST_CD}' != '' && '${organizationBean.DIST_CD}' == d[i].DISTRICT_CODE){
						$("#COUNTY_NAMES").append("<option selected='selected' value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
					}else{
							$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
					}
				}
				viewDT();
			}
			
			function viewstreet(){
				var tCOUNTY_ID="";
				var tCOUNTY_IDnum = 1;	
				if($("#COUNTY_NAMES").val() != null){
					tCOUNTY_ID=$("#COUNTY_NAMES").val();
					tCOUNTY_IDnum=$("#COUNTY_NAMES").get(0).options.length;
				}
				if(tCOUNTY_ID.length < 2 && tCOUNTY_IDnum < 2 && $("#CITY_NAMES").val() != null){
					tCOUNTY_ID=$("#CITY_NAMES").val();
				}
				if(tCOUNTY_ID.length>0){
					var tSTREET_NAME=$("#STREET_NAME").val();
					if(tSTREET_NAME.length>0 &&　tSTREET_NAME!='查询街道'){
						var url = '${ctx}/address/b04r03addroption!streets.action';
						var params = {
							COUNTY_ID: tCOUNTY_ID,
							STREET_NAME: tSTREET_NAME
						 };
						jQuery.post(url, params, callbackstreet, 'json');
					}
				}
			}
			function callbackstreet(data){
				$('#STRT_ID option').remove();
				var d=data.streetBeans;
				if(d.length>0){
					for(var i=0;i<d.length;i++){
						$("#STRT_ID").append("<option value='" + d[i].STRT_ID + "'>" + d[i].TOTAL_STREET_NAME + "</option>"); 
					}
					shDTDMPG();
				}
				else{
					$("#STRT_ID").append("<option value=''>请选择</option>"); 
				}
			}
			
			function viewDT(){
				var tPROVINCE_NAME=$("#COUNTY_NAMES").find("option:selected").text();
				var tCITY_CODE="";
				if($("#COUNTY_NAMES").val() != null && $("#COUNTY_NAMES").val().length>0){
					tCITY_CODE=$("#COUNTY_NAMES").val();
				}else if($("#CITY_NAMES").val() != null && $("#CITY_NAMES").val().length>0){
					tCITY_CODE=$("#CITY_NAMES").val();
				}else if($("#PROVINCE_NAMES").val().length>0){
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
						$("#DT_PK_CODE").append("<option value='" + d[i].DT_PK_CODE + "'>" + d[i].DT_NAME + "</option>"); 
					}
					if(d.length==1){
						$("#DT_PK_CODE").val(d[0].DT_PK_CODE);
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
						$("#DM_PK_CODE").append("<option value='" + d[i].DM_PK_CODE + "'>" + d[i].DM_NAME + "</option>");
					}
					$("#DM_PK_CODE").val(dtdmpgs_DM);
					dtdmpgs_DM="";
					if(d.length==1){
						$("#DM_PK_CODE").val(d[0].DM_PK_CODE);
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
						$("#PG_PK_CODE").append("<option value='" + d[i].PG_PK_CODE + "'>" + d[i].PG_NAME + "</option>"); 
					}
					$("#PG_PK_CODE").val(dtdmpgs_PG);
					dtdmpgs_PG="";
					if(d.length==1){
						$("#PG_PK_CODE").val(d[0].PG_PK_CODE);
					}
				}
			}
			
			function viewqzui(){
				var tDIST_CD=$("#PROVINCE_NAMES").val();
				if(!isMunicipalitiesID(tDIST_CD.substr(0,2))){
					tDIST_CD=$("#CITY_NAMES").val();
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
						$("#PREFIX").append("<option value='" + d[i].FIX + "'>" + d[i].FIX + "</option>"); 
					}
				}
				else{
					$("#PREFIX").append("<option value=''>请选择</option>"); 
				}
			}
			
			function viewhzui(){
				var tDIST_CD=$("#PROVINCE_NAMES").val();
				if(!isMunicipalitiesID(tDIST_CD.substr(0,2))){
					tDIST_CD=$("#CITY_NAMES").val();
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
					$("#SUFFIX").append("<option value=''>请选择</option>"); 
					for(var i=0;i<d.length;i++){
						$("#SUFFIX").append("<option value='" + d[i].FIX + "'>" + d[i].FIX + "</option>"); 
					}
				}
				else{
					$("#SUFFIX").append("<option value=''>请选择</option>"); 
					$("#SUFFIX").append("<option value='号'>号</option>"); 
				}
			}
			
			function ajaxAddUpAlert(flag){
				var tDIST_CD='';
				var tDIST_CDnum = 1;
				if($("#COUNTY_NAMES").val() != null){
					tDIST_CD=$("#COUNTY_NAMES").val();
					tDIST_CDnum=$("#COUNTY_NAMES").get(0).options.length;
				}
				if(tDIST_CD.length < 2 && tDIST_CDnum < 2){
					tDIST_CD=$("#CITY_NAMES").val();
				}
				var tDORPLT_ID=$("#DORPLT_ID").val();
				var tSTRT_ID=$("#STRT_ID").val();
				var tORG_NAME=$('#ORG_NAME').val();
				var tSTART_NUM=$('#START_NUM').val();
				var tEND_NUM=$('#END_NUM').val();
				var tPOST_CD=$('#POST_CD').val();
				var tSUFFIX=$('#SUFFIX').val();
				var tDT_PK_CODE=$('#DT_PK_CODE').val();
				var tDM_PK_CODE=$('#DM_PK_CODE').val();
				var tPG_PK_CODE=$('#PG_PK_CODE').val();
				if(tDIST_CD==''){
					$.prompt('选择城区名',{buttons: {'确定': true}});
					return false;
				}
				if(tSTRT_ID=='' || tSTRT_ID=='0'){
					$.prompt('请选择街道',{buttons: {'确定': true}});
					return false;
				}
				if(tORG_NAME==''){
					$.prompt('请填入机构',{buttons: {'确定': true}});
					return false;
				}
				if(tORG_NAME.length > 80){
					$.prompt('机构字数超过80',{buttons: {'确定': true}});
					return false;
				}
				/*
				if(tSTART_NUM==''){
					$.prompt('请输入门牌',{buttons: {'确定': true}});
					return false;
				}
				*/
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
				if(tSTART_NUM.length >0 && regEx.test(tSTART_NUM)){
					$.prompt('请填入正确的门牌',{buttons: {'确定': true}});
					return false;
				}
				if(tEND_NUM.length >0 && regEx.test(tEND_NUM)){
					$.prompt('请填入正确的门牌',{buttons: {'确定': true}});
					return false;
				}
				/*
				if(tSUFFIX==''){
					$.prompt('请输入后缀',{buttons: {'确定': true}});
					return false;
				}
				*/
				if(tDT_PK_CODE==''){
					$.prompt('请选择投递区',{buttons: {'确定': true}});
					return false;
				}
				/*
				if(tDM_PK_CODE==''){
					$.prompt('请选择投递部',{buttons: {'确定': true}});
					return false;
				}
				if(tPG_PK_CODE==''){
					$.prompt('请选择投递段',{buttons: {'确定': true}});
					return false;
				}
				*/
				if(flag=='Add'){
					ajaxAddUp(flag);
				}else{
					$.prompt('该操作将修改此机构！确认执行操作？',{
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
				var tDIST_CD='';
				var tDIST_CDnum = 1;
				if($("#COUNTY_NAMES").val() != null){
					tDIST_CD=$("#COUNTY_NAMES").val();
					tDIST_CDnum=$("#COUNTY_NAMES").get(0).options.length;
				}
				if(tDIST_CD.length < 2 && tDIST_CDnum < 2){
					tDIST_CD=$("#CITY_NAMES").val();
				}
				var tORG_ID=$("#ORG_ID").val();
				var tORG_IDTMP=$("#ORG_IDTMP").val();
				var tSTRT_ID=$("#STRT_ID").val();
				var tORG_NAME=$('#ORG_NAME').val();
				var tORG_ABBR_NAME=$('#ORG_ABBR_NAME').val();
				var tORG_ABBR=$('#ORG_ABBR').val();
				var tPOST_CD=$('#POST_CD').val();
				var tNUM_FLAG=$("input[name='NUM_FLAG'][checked]").val(); 
				var tPREFIX=$('#PREFIX').val();
				var tSTART_NUM=$('#START_NUM').val();
				var tEND_NUM=$('#END_NUM').val();
				if(tSTART_NUM.length>0 && tEND_NUM.length<1){
					tEND_NUM=tSTART_NUM;
				}
				var tSUFFIX=$('#SUFFIX').val();
				var tDT_PK_CODE=$('#DT_PK_CODE').val();
				var tDM_PK_CODE=$('#DM_PK_CODE').val();
				var tPG_PK_CODE=$('#PG_PK_CODE').val();
				var tTOTAL_STREET_NAME=$("#STRT_ID").find("option:selected").text(); 
				if(tSTRT_ID == tTOTAL_STREET_NAME){
					tTOTAL_STREET_NAME = '${TOTAL_STREET_NAME}';
				}
				var tNOTE=$('#NOTE').val();
				var params = {
						ORG_IDTMP:tORG_IDTMP,
						ORG_ID:tORG_ID,
						STRT_ID:tSTRT_ID,
						DIST_CD:tDIST_CD,
						ORG_NAME:tORG_NAME,
						ORG_ABBR_NAME:tORG_ABBR_NAME,
						ORG_ABBR:tORG_ABBR,
						POST_CD:tPOST_CD,
						NUM_FLAG:tNUM_FLAG,
						PREFIX:tPREFIX,
						START_NUM:tSTART_NUM,
						END_NUM:tEND_NUM,
						SUFFIX:tSUFFIX,
						DT_PK_CODE:tDT_PK_CODE,
						DM_PK_CODE:tDM_PK_CODE,
						PG_PK_CODE:tPG_PK_CODE,
						TOTAL_STREET_NAME:tTOTAL_STREET_NAME,
						NOTE:tNOTE
	            };
				if(flag=='Add'){
					var url = '';
					if(tSTART_NUM==''){
						url = '${ctx}/address/b04cud02addrconfig!addbldgrsdnsdiusorg.action';
						$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
					    jQuery.post(url, params, callajaxAdd, 'json');
					}else{
						url = '${ctx}/address/b04cud02addrconfig!addorgandius.action';
						$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
					    jQuery.post(url, params, callajaxAdd, 'json');
					}
				}
			}
			function callajaxAdd(data){
				$.prompt(data.saveMessage,{
					buttons: {'确定': true},
					callback: function(v){
						if(v){
							backQuery('fresh');
						}
					}
				});
				$.unblockUI();
			}
			function backQuery(flag){
				window.returnValue=flag;
				window.opener =null;
				window.close();
			}
			function shName(flag){
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
			function shDTDMPG(){
				$("#DT_PK_CODE").val("");
				$("#DM_PK_CODE").val("");
				$("#PG_PK_CODE").val("");
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
				var tNUM_FLAG=$("input[name='NUM_FLAG'][checked]").val(); 
				var tPREFIX=$('#PREFIX').val();
				var tSTART_NUM=$('#START_NUM').val();
				var tSUFFIX=$('#SUFFIX').val();
				if(tSTRT_ID.length>0 && tSTART_NUM.length<1 && tDIST_CD.length>0){
					var params = {
						STRT_ID:tSTRT_ID,
						DIST_CD:tDIST_CD
	            	};
					var url = '${ctx}/address/b04r03addroption!dtdmpgsno.action';
					jQuery.post(url, params, callajaxdtdmpgsAdd, 'json');
				}
				if(tSTRT_ID.length>0 && tSTART_NUM.length>0 && tDIST_CD.length>0){
					var params = {
						STRT_ID:tSTRT_ID,
						DIST_CD:tDIST_CD,
						NUM_FLAG:tNUM_FLAG,
						PREFIX:tPREFIX,
						START_NUM:tSTART_NUM,
						SUFFIX:tSUFFIX
	           		};
					var url = '${ctx}/address/b04r03addroption!dtdmpgs.action';
					jQuery.post(url, params, callajaxdtdmpgsAdd, 'json');
				}
			}
			function callajaxdtdmpgsAdd(data){
				var d=data.cpwhrlpgstBeans;
				if(d.length>0){
					for(var i=0;i<d.length;i++){
						dtdmpgs_DT = d[i].DT_PK_CODE;
						dtdmpgs_DM = d[i].DM_PK_CODE;
						dtdmpgs_PG = d[i].PG_PK_CODE;
						$("#DT_PK_CODE").val(dtdmpgs_DT);
						dtdmpgs_DT="";
						viewDM();
					}
				}
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
										机构编辑
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<input id="ORG_ID" type="hidden" value="${organizationBean.ORG_ID}"/>
								<input id="ORG_IDTMP" type="hidden" value="${organizationBean.ORG_ID}"/>
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
										<table class="listtable" id="viewquery_table"  width="500">
											<tr>
												<td class="listtablehead" colspan="4">
													<font color="#FF0000">&nbsp;带*号的必须填写</font>
												</td>
											</tr>
											<tr id="tr_PROVINCE_NAME">
												<td class="listtablehead" width="80">
													行政区域:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty()"  style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES" onChange=" viewstreet()"  style="width:100"></select>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" colspan="4">
												<font color="#FF0000">（此处可以输入街道名得到街道，没有的街道请先在街道界面维护）</font>
												</td>												
											</tr>
											<tr id="tr_STRT_ID">
												<td class="listtablehead" width="80">
													街道:
												</td>
												<td class="js_left_txt" id="td_STRT_ID" colspan="3">
													<select class="simple" id="STRT_ID"  style="width:200" onChange="shDTDMPG()">
													</select>
													<input type="text" name="STREET_NAME" style="width: 100px; height:20px;color:#FF0000" id="STREET_NAME" class="ac_input" size="100" value="查询街道" onFocus="this.select();" onBlur="viewstreet()"/><font color="#FF0000">&nbsp;*</font>
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
															<input type="text" name="START_NUM" style="width: 200px; height:20px;" id="START_NUM" class="ac_input" size="300"  value="${organizationBean.START_NUM}" onFocus="this.select();" onKeyUp="shNUM_FLAG('abbr')"  onChange="shDTDMPG()"/>
															<br>
															<font color="#FF0000">&nbsp;(有门牌需要维护门牌,无门牌机构,数据将维护到小区中！)</font>
														</td>
													</tr>
													<tr  id="tr_END_NUM"  style="display:none">
														<td class="listtablehead">
														结束门牌:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<input type="text" name="END_NUM" style="width: 200px; height:20px;" id="END_NUM" class="ac_input" size="300"  value="${organizationBean.END_NUM}" onFocus="this.select();" onChange="shNUM_FLAG('abbr')"/>
														</td>
													</tr>
													<tr id="tdSTRT4_NAME">
														<td class="listtablehead">
														后缀:
														</td>
														<td class="js_left_txt" id="td_city_content" colspan="3">
															<select class="simple" id="SUFFIX" style="width:130" onChange="shDTDMPG()"></select>
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
												<td class="listtablehead" colspan="4">
												<font color="#FF0000">（无门牌机构,数据将维护到小区中！）</font>
												</td>												
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													机构名称:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="ORG_NAME" style="width: 300px; height:20px;" id="ORG_NAME" class="ac_input" size="300" value="${organizationBean.ORG_NAME}" onFocus="this.select();" onKeyUp="shName('abbr')" onBlur="shName('abbr')"/><font color="#FF0000">&nbsp;*</font>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													机构别名:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="ORG_ABBR_NAME" style="width: 300px; height:20px;" id="ORG_ABBR_NAME" class="ac_input" size="300" value="${organizationBean.ORG_ABBR_NAME}" onFocus="this.select();"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													机构简拼:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="ORG_ABBR" style="width: 300px; height:20px;" id="ORG_ABBR" class="ac_input" size="300" value="${organizationBean.ORG_ABBR}" onFocus="this.select();"/>
												</td>
											</tr>
											<tr id="tr_DORPLT_ID">
												<td class="listtablehead" width="80">
													邮编:
												</td>
												<td class="js_left_txt" id="td_POST_CD" colspan="3">
													<input type="text" name="POST_CD" style="width: 300px; height:20px;" id="POST_CD" class="ac_input" size="300" value="${organizationBean.POST_CD}" onFocus="this.select();" maxlength="6"/>
												</td>
											</tr>
											<tr id="tr_DORPLT_ID">
												<td class="listtablehead" width="80">
													备注:
												</td>
												<td class="js_left_txt" id="td_NOTE" colspan="3">
													<input type="text" name="NOTE" style="width: 300px; height:20px;" id="NOTE" class="ac_input" size="300" value="${organizationBean.NOTE}" onFocus="this.select();"/>
												</td>
											</tr>
											<tr id="tr_DT_PK_CODE_q">
												<td class="listtablehead" width="100">
													投递区:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<select class="simple" id="DT_PK_CODE" onChange="viewDM()"  style="width:200"><option value=''>请选择</option></select><font color="#FF0000">&nbsp;*</font>
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
	</body>
</html>
