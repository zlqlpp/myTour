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
		<title>街道合并</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			$(document).ready(function(){
				viewcity();
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
				for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
				}
			}
			
			function viewstreet(){
				var tCOUNTY_ID="";	
				var tCOUNTY_IDnum = 1;	
				if($("#COUNTY_NAMES").val() != null){
					tCOUNTY_ID=$("#COUNTY_NAMES").val();
					tCOUNTY_IDnum=$("#COUNTY_NAMES").get(0).options.length;
				}
				if(tCOUNTY_ID.length < 2 && tCOUNTY_IDnum < 2){
					tCOUNTY_ID=$("#CITY_NAMES").val();
				}
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
			function callbackstreet(data){
				$('#STRT_IDG option').remove();
				var d=data.streetBeans;
				if(d.length>0){
					for(var i=0;i<d.length;i++){
						$("#STRT_IDG").append("<option value='" + d[i].STRT_ID + "'>" + d[i].TOTAL_STREET_NAME + "</option>"); 
					}
				}
				else{
					$("#STRT_IDG").append("<option value=''>请选择</option>"); 
				}
			}
			
			function ajaxAddUpAlert(flag){
				var tSTRT_IDY=$("#STRT_IDY").val();
				var tSTRT_IDG=$("#STRT_IDG").val();
				if(tSTRT_IDG==''){
					$.prompt('请选择合并街道',{buttons: {'确定': true}});
					return false;
				}
				if(tSTRT_IDY==tSTRT_IDG){
					$.prompt('合并街道相同',{buttons: {'确定': true}});
					return false;
				}
				var tDIST_CDY=$("#DIST_CDY").val();
				var tDIST_CDG=$("#PROVINCE_NAMES").val();
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				if(isMunicipalities(tPROVINCE_NAME)){
					tDIST_CDY = tDIST_CDY.substr(0,2);
					tDIST_CDG = tDIST_CDG.substr(0,2);
				}else{
					tDIST_CDY = tDIST_CDY.substr(0,4);
					tDIST_CDG = $("#CITY_NAMES").val().substr(0,4);
				}
				if(tDIST_CDY!=tDIST_CDG){
					$.prompt('合并街道不在同一个城市',{buttons: {'确定': true}});
					return false;
				}
				ajaxAddUp(flag);
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
				var tSTRT_IDY=$("#STRT_IDY").val();
				var tSTRT_IDG=$("#STRT_IDG").val();
				var params = {
						STRT_IDY:tSTRT_IDY,
						STRT_IDG:tSTRT_IDG,
						DIST_CD:tDIST_CD
	            };
				var url = '${ctx}/address/b04cud02addrconfig!hbstreet.action';
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
										街道合并
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<a id="addquery" href='#' onClick="ajaxAddUpAlert('Add')"  style="font-size:12px">合并</a>
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
													被合并街道:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													&nbsp;${TOTAL_STREET_NAME}
													<input id="DIST_CDY" type="hidden" value="${DIST_CD}"/>
													<input id="STRT_IDY" type="hidden" value="${STRT_ID}"/>
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
											<tr id="tr_STRT_ID">
												<td class="listtablehead" width="80">
													合并到街道:
												</td>
												<td class="js_left_txt" id="td_STRT_ID" colspan="3">
													<select class="simple" id="STRT_IDG"  style="width:200">
													</select>
													<input type="text" name="STREET_NAME" style="width: 100px; height:20px;color:#FF0000" id="STREET_NAME" class="ac_input" size="100" value="查询街道" onFocus="this.select();" onBlur="viewstreet()"/><font color="#FF0000">&nbsp;*</font>
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
