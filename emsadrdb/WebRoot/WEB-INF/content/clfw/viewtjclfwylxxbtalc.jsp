<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/disp/disp.jsp"%>
<%@ include file="/scripts/My97DatePicker/My97DatePicker.jsp"%>
<html>

	<head>
		<title>准班准点统计(组开局)</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){

				if('${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2'){
					$("#PROVINCE_NAMES").prepend("<option selected='selected' value=''>全国</option>"); 
					$("#PROVINCE_NAMES").val('');
				}

				viewcity();
				
				var myDate = new Date();
				
				var tMonth = myDate.getMonth()+1;
				
				if(tMonth<10){
					tMonth = '0' + tMonth;
				}

				var tDate = myDate.getDate();
				if(tDate<10){
					tDate = '0' + tDate;
				}
				var sj = myDate.getFullYear() + '' + tMonth + '' + tDate;
				
				$("#ITEM_DATESS").val(sj);
				$("#ITEM_DATESE").val(sj);
				//backfresh('fresh');

				$("#fliphelpcon").hide();
				$("#fliphelpct").click(function(){
					$("#fliphelpcon").slideToggle("fast");
				});
			})
			
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
					$("#COUNTY_NAMES").prepend("<option selected='selected' value=''>全市</option>");
			    	for(var i=0;i<d.length;i++){
						$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
			    }else{
					$('#CITY_NAMES option').remove();

					if('${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2' || '${EMS_USER.rulLevel}' == '5'){
						$("#CITY_NAMES").prepend("<option selected='selected' value=''>全省</option>");
					}

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
				$("#COUNTY_NAMES").prepend("<option selected='selected' value=''>全市</option>");
				for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
				}
			}
			
			function ajaxQueryPage(pageNo){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				if(isMunicipalities(tPROVINCE_NAME)){
					var tCITY_ID="";
			    	var tCOUNTY_ID=$("#COUNTY_NAMES").val();
			    }else{
					var tCITY_ID=$("#CITY_NAMES").val();
					var tCOUNTY_ID=$("#COUNTY_NAMES").val();
				}
				var tITEM_DATESS=$('#ITEM_DATESS').val();
				if(tITEM_DATESS==''){
					$.prompt('请输入开始日期',{buttons: {'确定': true}});
					return false;
				}
				var tITEM_DATESE=$('#ITEM_DATESE').val();
				if(tITEM_DATESE==''){
					$.prompt('请输入结束日期',{buttons: {'确定': true}});
					return false;
				}
				var tCLFW_HBXXGL_CLSX=$("input:radio[name='CLFW_HBXXGL_CLSX'][checked]").val();
				var tCLFW_POSTALROUTEATTR=$("input:radio[name='CLFW_POSTALROUTEATTR'][checked]").val();
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var url = '${ctx}/clfw/b09r02clfwquery!querytjclfwylxxbtalc.action';
			    var params = {
					    		PROVINCE_ID:tPROVINCE_ID,
								CITY_ID:tCITY_ID,
								COUNTY_ID:tCOUNTY_ID,
								CLFW_HBXXGL_CLSX:tCLFW_HBXXGL_CLSX,
								CLFW_POSTALROUTEATTR:tCLFW_POSTALROUTEATTR,
								ITEM_DATESS:tITEM_DATESS,
								ITEM_DATESE:tITEM_DATESE,
			                  	pageNo:pageNo
			                 };
			    jQuery.post(url, params, callbackajaxQueryPage, 'json');
			}
			function callbackajaxQueryPage(data){
				addquerytable(data);
			}
			function addquerytable(data){
				var resultdata=data.page.result;

				var tr=" ";

				$('#address_table_list tbody').remove();
				$('#address_table_foot tbody').remove();

				if(resultdata.length>0){

					var vCLFWYLXXB_CSRQt = 0;
					var vCLFWYLXXB_QYRQt = 0;
					var vCLFWYLXXB_JZRQt = 0;


					var vOPE_INSERTTIMEINTall = 0;
					var vCLFWYLXXB_CSRQINTall = 0;
					var vCLFWYLXXB_CSRQINTKHall = 0;
					var vCLFWYLXXB_JZRQINTall = 0;
					
					for(var i=0;i<resultdata.length;i++){

						tr="<tr id='tr_query'>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].CLFWYLXXB_SFJID_STR+"</td>";

						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].OPE_INSERTTIMEINT+"</td>";

						vOPE_INSERTTIMEINTall = vOPE_INSERTTIMEINTall + parseInt(resultdata[i].OPE_INSERTTIMEINT);
						
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].CLFWYLXXB_CSRQINT+"</td>";

						vCLFWYLXXB_CSRQINTall = vCLFWYLXXB_CSRQINTall + parseInt(resultdata[i].CLFWYLXXB_CSRQINT);
						
						if(resultdata[i].CLFWYLXXB_CSRQINT > 0){
							vCLFWYLXXB_CSRQt = resultdata[i].CLFWYLXXB_CSRQINT / parseInt(resultdata[i].OPE_INSERTTIMEINT);
							vCLFWYLXXB_CSRQt = Math.round ( vCLFWYLXXB_CSRQt * 10000 ) / 100;
						}else{
							vCLFWYLXXB_CSRQt = 0
						}
						
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+vCLFWYLXXB_CSRQt+"%</td>";
						
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].CLFWYLXXB_CSRQINTKH+"</td>";

						vCLFWYLXXB_CSRQINTKHall = vCLFWYLXXB_CSRQINTKHall + parseInt(resultdata[i].CLFWYLXXB_CSRQINTKH);

						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].CLFWYLXXB_JZRQINT+"</td>";

						vCLFWYLXXB_JZRQINTall = vCLFWYLXXB_JZRQINTall + parseInt(resultdata[i].CLFWYLXXB_JZRQINT);

						if(resultdata[i].CLFWYLXXB_JZRQINT > 0){
							vCLFWYLXXB_JZRQt = resultdata[i].CLFWYLXXB_JZRQINT / parseInt(resultdata[i].CLFWYLXXB_CSRQINTKH);
							vCLFWYLXXB_JZRQt = Math.round ( vCLFWYLXXB_JZRQt * 10000 ) / 100;
						}else{
							vCLFWYLXXB_JZRQt = 0
						}

						
						tr+="<td valign=top align=left class=listtablebodyleft2  width=100>"+vCLFWYLXXB_JZRQt+"%</td>";


						tr+="</tr>";
						$('#address_table_list').append(tr);
					}


					tr="<tr id='tr_query'>";
					tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;合计</td>";

					tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+vOPE_INSERTTIMEINTall+"</td>";
					tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+vCLFWYLXXB_CSRQINTall+"</td>";


					if(vCLFWYLXXB_CSRQINTall > 0){
						vCLFWYLXXB_CSRQt = vCLFWYLXXB_CSRQINTall / vOPE_INSERTTIMEINTall;
						vCLFWYLXXB_CSRQt = Math.round ( vCLFWYLXXB_CSRQt * 10000 ) / 100;
					}else{
						vCLFWYLXXB_CSRQt = 0
					}
					
					tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+vCLFWYLXXB_CSRQt+"%</td>";
					
					tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+vCLFWYLXXB_CSRQINTKHall+"</td>";
					
					tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+vCLFWYLXXB_JZRQINTall+"</td>";

					if(vCLFWYLXXB_JZRQINTall > 0){
						vCLFWYLXXB_JZRQt = vCLFWYLXXB_JZRQINTall / vCLFWYLXXB_CSRQINTKHall;
						vCLFWYLXXB_JZRQt = Math.round ( vCLFWYLXXB_JZRQt * 10000 ) / 100;
					}else{
						vCLFWYLXXB_JZRQt = 0
					}

					
					tr+="<td valign=top align=left class=listtablebodyleft2  width=100>"+vCLFWYLXXB_JZRQt+"%</td>";


					tr+="</tr>";
					$('#address_table_list').append(tr);


					
					
					url="<tr   style='display:none'><td colspan=11 class=listtablebodyleft2>" + data.page.ajaxUrl  + "</td></tr>";
					
					$('#address_table_foot').append(url);

					
				}else{
					var url="<tr><td colspan=11 class=listtablebodyleft2>找到 0 条记录</td>";
					$('#address_table_foot').append(url);
				}
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
			}
			function backfresh(value){
				if(value == "fresh") {
					ajaxQueryPage(0);
				}
			}
		</script>
	</head>
	<body>
<div id="nodata" style="display:none">
<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />
</div>
<div id="yesdata">
			<table width="900" border="0" cellpadding="0" cellspacing="0" align="left">
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
										数据填报 >>准班准点统计(组开局)
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
										<table class="listtable" id="viewquery_table">
											<tr>
												<td class="listtablehead" width="80">
													行政区域:
												</td>
												<td class="listtablehead" id="td_privince_content">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onChange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty()" style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES" style="width:100"></select>
												</td>
												<td class="listtablehead" width="80">
													数据类型:
												</td>
												<td class="listtablehead" id="td_privince_content">
													<input type="radio" name="CLFW_HBXXGL_CLSX" id="CLFW_HBXXGL_CLSX" value="1"  checked="checked">现用
													<input type="radio" name="CLFW_HBXXGL_CLSX" id="CLFW_HBXXGL_CLSX" value="0">历史
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													开始时间:
												</td>
												<td class="listtablehead" id="td_tpdistrict_content">
													<input type="text" name="ITEM_DATESS" style="width: 100px; height:20px;" id="ITEM_DATESS" class="ac_input" size="100" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"/><font color="#FF0000">*</font>
												</td>
												<td class="listtablehead">
													结束时间:
												</td>
												<td class="listtablehead" id="td_tpdistrict_content">
													<input type="text" name="ITEM_DATESE" style="width: 100px; height:20px;" id="ITEM_DATESE" class="ac_input" size="100" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"/><font color="#FF0000">*</font>
												</td>
											</tr>
											<!--  -->
											<tr>
												
												<td class="listtablehead" width="80">
													邮路类型:
												</td>
												<td class="listtablehead" id="td_privince_content">
													<input type="radio" name="CLFW_POSTALROUTEATTR" id="CLFW_POSTALROUTEATTR" value="0"  checked="checked">56
													<input type="radio" name="CLFW_POSTALROUTEATTR" id="CLFW_POSTALROUTEATTR" value="1">73
													<input type="radio" name="CLFW_POSTALROUTEATTR" id="CLFW_POSTALROUTEATTR" value="2">经济速递
												</td>
												<td></td>
												<td></td>
											</tr>
											<!--  -->
											<tr>
												<td class="listtablehead"  colspan="4" align="right">
													<input id="query" type="button" value="查询" onClick="ajaxQueryPage(0)" />
													<input id="queryclear" type="button" value="重置" onClick="ajaxQueryPage(0)" />
													<input id="fliphelpct" type="button" value="帮助"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead"  colspan="4">
													<font id="fliphelpcon" color="red">
														无帮助介绍
													</font>
												</td>
											</tr>
										</table>
									</div>
									
									<br />
									<div id="tablelistdiv">
										<table class="listtable" width="1000"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="center" colspan="11">
													准班准点统计(组开局)
												</th>
											</tr>
											<thead>
												<td align="left" class="listtableheadleft" style='width:100'>
													组开局
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													邮路应发班数
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													实发班数
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													准班率
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													准点考核班数
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													准点到达班数
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													到达准点率
												</td>
											</thead>
										</table>
										<div style='height:300px;overflow:auto;overflow-x:hidden;'>
										<table class="listtable" width="1000" id="address_table_list" cellpadding="0" cellspacing="0">
											
										</table>
										</div>
										<table class="listtable" width="1000" id="address_table_foot" cellpadding="0" cellspacing="0">
											
										</table>
										<br>
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
	<script language="javascript">
//	document.oncontextmenu = function(){
//		return false;
//	}
//	document.onkeydown = function(){
//		if (event.ctrlKey && window.event.keyCode==67){
//		   return false;
//		}
//	}
//	document.body.oncopy = function ()
//	{
//		return false;
//	}
//	document.onselectstart = function(){
//		return false;
//	}
	</script>
</html>
