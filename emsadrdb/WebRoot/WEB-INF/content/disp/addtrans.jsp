<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/disp/disp.jsp"%>
<html>

	<head>
		<title>经转范围编辑</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			$(document).ready(function(){
				if('${transBean.TRANS_CODE}' != ''){
					$("#upquery").show();
					$("#addquery").hide();
				}else{
					$("#upquery").hide();
					$("#addquery").show();
					viewcity();
				}
			})
			//展示市
			function viewcity(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tRulUsCityOffice= null;
				if(isMunicipalities(tPROVINCE_NAME)){
			    	$('#CITY_NAMES').hide();
					$('#CITY_NAMES option').remove();
					$("#CITY_NAMES").append("<option value=''>请选择</option>"); 
			    }else{
					$('#CITY_NAMES').show();
				}
				var url = '${ctx}/disp/b07r03dispoption!citysname.action';
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
			    }else{
					$('#CITY_NAMES option').remove();
					for(var i=0;i<d.length;i++){
						$("#CITY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
					viewcounty();
			    }
			}
			
			function viewcounty(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 			
				if(!isMunicipalities(tPROVINCE_NAME)){
					var tCITY_NAME=$("#CITY_NAMES").find("option:selected").text(); 
					var url = '${ctx}/disp/b07r03dispoption!countysname.action';
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
					$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
				}
			}
			function ajaxAddUpAlert(flag){
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
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text();
				var tPROVINCE_CODE=$("#PROVINCE_NAMES").val();
				if($("#COUNTY_NAMES").val().length>0){
					var tTRANS_CODE=$("#COUNTY_NAMES").val();
					var tTRANS_NAME = $("#COUNTY_NAMES").find("option:selected").text();
				}else if($("#CITY_NAMES").val().length>0){
					var tTRANS_CODE=$("#CITY_NAMES").val();
					var tTRANS_NAME = $("#CITY_NAMES").find("option:selected").text();
				}else if($("#PROVINCE_NAMES").val().length>0){
					var tTRANS_CODE=$("#PROVINCE_NAMES").val();
					var tTRANS_NAME = $("#PROVINCE_NAMES").find("option:selected").text();
				}
				var tDISP_OFFICE_CODE=$("#DISP_OFFICE_CODE").val();
				
				var tDEGREE="3";
				if(tTRANS_CODE.substr(2,4)=='0000'){
					tDEGREE="1";
				}
				else if(tTRANS_CODE.substr(4,2)=='00'){
					tDEGREE="2";
				}
				
				var tFLAG=3;
				if(tDISP_OFFICE_CODE.substr(0,4)==tTRANS_CODE.substr(0,4)){
					tFLAG=1;
				}else if(tDISP_OFFICE_CODE.substr(0,2)==tTRANS_CODE.substr(0,2)){
					tFLAG=2;
				}
				
				var params = {
						PROVINCE_NAME:tPROVINCE_NAME,
						PROVINCE_CODE:tPROVINCE_CODE,
						TRANS_CODE:tTRANS_CODE,
						TRANS_NAME:tTRANS_NAME,
						DEGREE:tDEGREE,
						DISP_OFFICE_CODE:tDISP_OFFICE_CODE,
						FLAG:tFLAG
	            };
				if(flag=='Add'){
					var url = '${ctx}/disp/b07cud02dispconfig!addtrans.action';
				}else{
					var url = '${ctx}/disp/b07cud02dispconfig!uptrans.action';
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
										经转范围编辑
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<input id="DISP_OFFICE_CODE" type="hidden" value="${DISP_OFFICE_CODE}"/>
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
											<tr id="trans_add">
												<td class="listtablehead" width="80">
													经转范围:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty()"  style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES"  style="width:100"></select>
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
