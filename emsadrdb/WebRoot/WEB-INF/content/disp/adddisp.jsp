<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/disp/disp.jsp"%>
<%@ include file="/widgets/jquery/multiselect/multiselect.jsp"%>
<html>

	<head>
		<title>经转局编辑</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var flag='Add';
			$(document).ready(function(){
				$("#WHDIS").multiselect2side({
					selectedPosition: 'right',
					moveOptions: false,
					labelsx: '待选区',
					labeldx: '已选区'
			    });
				if('${dispBean.DISP_OFFICE_CODE}' != ''){
					$("#upquery").show();
					$("#addquery").hide();
					$("#disps_up").show();
					$("#disps_add").hide();
					$("#COUNTY_NAMES").append("<option value='${dispBean.DISP_OFFICE_CODE}'>${dispBean.DISP_OFFICE_CODE}</option>");
					viewwhdistrictl(); 
					viewwhdistrictr();
				}else{
					$("#upquery").hide();
					$("#addquery").show();
					$("#disps_up").hide();
					$("#disps_add").show();
					viewcity();
				}
			})
			//展示市
			function viewcity(){
				viewwhdistrictl(); 
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tRulUsCityOffice= null;
				if(isMunicipalities(tPROVINCE_NAME)){
			    	$('#CITY_NAMES').hide();
					$('#CITY_NAMES option').remove();
					$("#CITY_NAMES").append("<option value=''>请选择</option>"); 
			    }else{
					$('#CITY_NAMES').show();
				}
				var url = '${ctx}/disp/b07r03dispoption!citys.action';
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
					viewwhdistrictr();
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
					var url = '${ctx}/disp/b07r03dispoption!countys.action';
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
				viewwhdistrictr();
			}
			function viewwhdistrictl(nodel){
				var tPROVINCE_CODE=$("#PROVINCE_NAMES").val();
				var url = '${ctx}/disp/b07r03dispoption!whdistrictdispl.action';
				if('${dispBean.DISP_OFFICE_CODE}' != ''){
					tPROVINCE_CODE = '${dispBean.DISP_OFFICE_CODE}';
				}
				var params = {
					PROVINCE_CODE :tPROVINCE_CODE
				 };
				jQuery.post(url, params, callbackviewwhdistrictl, 'json');
			}
			function callbackviewwhdistrictl(data){
				$('#WHDISms2side__sx option').remove();
				var d=data.whdistrictBeans;
				for(var i=0;i<d.length;i++){
					$("#WHDISms2side__sx").append("<option value='" + d[i].DT_PK_CODE + "'>" + d[i].DT_NAME + "</option>"); 
				}
			}
			
			function viewwhdistrictr(){	
				var tCITY_CODE=$("#COUNTY_NAMES").val();
				if($("#COUNTY_NAMES").val().length>0){
					tCITY_CODE=$("#COUNTY_NAMES").val();
				}else if($("#CITY_NAMES").val().length>0){
					tCITY_CODE=$("#CITY_NAMES").val();
				}else if($("#PROVINCE_NAMES").val().length>0){
					tCITY_CODE=$("#PROVINCE_NAMES").val();
				}
				var url = '${ctx}/disp/b07r03dispoption!whdistrictdispr.action';
				var params = {
					CITY_CODE :tCITY_CODE
				 };
				jQuery.post(url, params, callbackviewwhdistrictr, 'json');
			}
			function callbackviewwhdistrictr(data){
				$('#WHDISms2side__dx option').remove();
				var d=data.whdistrictBeans;
				for(var i=0;i<d.length;i++){
					$("#WHDISms2side__dx").append("<option value='" + d[i].DT_PK_CODE + "' selected>" + d[i].DT_NAME + "</option>"); 
				}
			}
			function ajaxAddUpAlert(tflag){
				flag = tflag;
				if(flag=='Add'){
					var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text();
					var tPROVINCE_CODE=$("#PROVINCE_NAMES").val();
					if($("#COUNTY_NAMES").val().length>0){
						var tDISP_OFFICE_CODE=$("#COUNTY_NAMES").val();
						var tDISP_OFFICE_NAME = $("#COUNTY_NAMES").find("option:selected").text();
					}else if($("#CITY_NAMES").val().length>0){
						var tDISP_OFFICE_CODE=$("#CITY_NAMES").val();
						var tDISP_OFFICE_NAME = $("#CITY_NAMES").find("option:selected").text();
					}else if($("#PROVINCE_NAMES").val().length>0){
						var tDISP_OFFICE_CODE=$("#PROVINCE_NAMES").val();
						var tDISP_OFFICE_NAME = $("#PROVINCE_NAMES").find("option:selected").text();
					}
					
					var params = {
							DISP_OFFICE_CODE:tDISP_OFFICE_CODE
					};
	
					var url = '${ctx}/disp/b07cud02dispconfig!alertdisp.action';
					jQuery.post(url, params, callajaxAddUpAlert, 'json');
				}else{
					overajaxAddUpAlert();
				}
			    
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
					$.prompt('该操作将修改此经转局！确认执行操作？',{
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
				$("#WHDISms2side__dx").children().each(function(){$(this).attr("selected","selected")});
				var tWHDIS = $("#WHDISms2side__dx").val();
				tWHDIS = "(" + tWHDIS + ")";
				var tDISP_OFFICE_ABBR=$("#DISP_OFFICE_ABBR").val();
//				if(tDISP_OFFICE_ABBR.length<1){
//					$.prompt('请输入经转局名',{buttons: {'确定': true}});
//					return false;
//				}
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text();
				var tPROVINCE_CODE=$("#PROVINCE_NAMES").val();
				if($("#COUNTY_NAMES").val().length>0){
					var tDISP_OFFICE_CODE=$("#COUNTY_NAMES").val();
					var tDISP_OFFICE_NAME = $("#COUNTY_NAMES").find("option:selected").text();
				}else if($("#CITY_NAMES").val().length>0){
					var tDISP_OFFICE_CODE=$("#CITY_NAMES").val();
					var tDISP_OFFICE_NAME = $("#CITY_NAMES").find("option:selected").text();
				}else if($("#PROVINCE_NAMES").val().length>0){
					var tDISP_OFFICE_CODE=$("#PROVINCE_NAMES").val();
					var tDISP_OFFICE_NAME = $("#PROVINCE_NAMES").find("option:selected").text();
				}
				
				var tDEGREE="3";
				if(tDISP_OFFICE_CODE.substr(2,4)=='0000'){
					tDEGREE="1";
				}
				else if(tDISP_OFFICE_CODE.substr(4,2)=='00'){
					tDEGREE="2";
				}
				
				var params = {
						DISP_OFFICE_ABBR:tDISP_OFFICE_ABBR,
						PROVINCE_NAME:tPROVINCE_NAME,
						PROVINCE_CODE:tPROVINCE_CODE,
						DISP_OFFICE_CODE:tDISP_OFFICE_CODE,
						DISP_OFFICE_NAME:tDISP_OFFICE_NAME,
						DEGREE:tDEGREE,
						WHDIS:tWHDIS
	            };
				if(flag=='Add'){
					var url = '${ctx}/disp/b07cud02dispconfig!adddisp.action';
				}else{
					var url = '${ctx}/disp/b07cud02dispconfig!updisp.action';
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
										经转局编辑
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
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
										<table class="listtable" id="viewquery_table"  width="500">
											<tr id="disps_up">
												<td class="listtablehead" width="80">
													经转局名:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													 &nbsp;${dispBean.DISP_OFFICE_NAME}
												</td>
											</tr>
											<tr id="disps_add">
												<td class="listtablehead" width="80">
													经转局名:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty()"  style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES"  style="width:100" onChange="viewwhdistrictr();"></select>
												</td>
											</tr>
											<%--<tr id="trans_add">
												<td class="listtablehead" width="80">
													经转局名:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<input type="text" name="DISP_OFFICE_ABBR" style="width: 200px; height:20px;" id="DISP_OFFICE_ABBR" class="ac_input" size="300" value="${dispBean.DISP_OFFICE_ABBR}" onFocus="this.select();" />
												</td>
											</tr>--%>
											<tr id="trans_add">
												<td class="listtablehead" colspan="4">
													投递区:
													<select name="WHDIS" id='WHDIS' multiple='multiple' size='16'>
												 	</select>
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
