<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/address/addr.jsp"%>
<%@ include file="/scripts/My97DatePicker/My97DatePicker.jsp"%>
<html>

	<head>
		<title>出口信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var bro=$.browser;
			$(document).ready(function(){
				var myDate = new Date(new Date() - 24*3600*1000);
				
				var tMonth = myDate.getMonth()+1;
				if(tMonth<10){
					tMonth = '0' + tMonth;
				}
				 
				var sj = myDate.getFullYear() + '-' +  tMonth  + '-' +   myDate.getDate();
				$("#COLLECT_DATEEND").val(sj);
				viewcity();
				$("#PROVINCE_NAMESJDT").prepend("<option selected='selected' value=''>请选择</option>");
				$("#PROVINCE_NAMESJDT").val("");
				$("#PROVINCE_NAMESJDT").hide();
				sjsn();
			})
			function sjsn(){
				var tISSJN=$("input[name='ISSJN'][checked]").val(); 
				$('#PROVINCE_NAMESJD option').remove();
				if(tISSJN == 1){
					$('#PROVINCE_NAMESJDT option').each(function () {
		                if ($(this).attr("value") == $("#PROVINCE_NAMES").val()) {
		                    $(this).clone().appendTo("#PROVINCE_NAMESJD");
		                }
					});
					viewcityjd();
				}else if(tISSJN == 2){
					$('#PROVINCE_NAMESJDT option').each(function () {
		                if ($(this).attr("value") != $("#PROVINCE_NAMES").val()) {
		                    $(this).clone().appendTo("#PROVINCE_NAMESJD");
		                }
					});
					viewcityjd();
				}else if(tISSJN == ""){
					$('#PROVINCE_NAMESJDT option').each(function () {
		                $(this).clone().appendTo("#PROVINCE_NAMESJD");
					});
					viewcityjd();
				}
				$("#PROVINCE_NAMESJD").val("");
			}
			//展示市
			function viewcity(){
				sjsn();
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var url = '${ctx}/address/b04r03addroption!citys.action';
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
					if('${EMS_USER.usCityOffice}'.substr(4,2)=='00' || '${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2' || '${EMS_USER.rulLevel}' == '5'){
						$("#COUNTY_NAMES").prepend("<option selected='selected' value=''>请选择</option>");
					}
			    	for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
					//backfresh('fresh');
			    }else{
					$('#CITY_NAMES option').remove();
					if('${EMS_USER.rulLevel}'=='0' || '${EMS_USER.rulLevel}'=='2' || '${EMS_USER.rulLevel}'=='5'){
						$("#CITY_NAMES").prepend("<option selected='selected' value=''>请选择</option>");
					}
					for(var i=0;i<d.length;i++){
						if('${EMS_USER.usCityOffice}'==d[i].DISTRICT_CODE){
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
					if(tCITY_NAME!='请选择'){
						var url = '${ctx}/address/b04r03addroption!countys.action';
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
				if('${EMS_USER.usCityOffice}'.substr(4,2)=='00' || '${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2' || '${EMS_USER.rulLevel}' == '5'){
						$("#COUNTY_NAMES").prepend("<option selected='selected' value=''>请选择</option>");
				}
				for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
				}
				//backfresh('fresh');
			}

			//展示市
			function viewcityjd(){
				if($("#PROVINCE_NAMESJD").val().length<1){
					$('#CITY_NAMESJD').hide();
					$('#COUNTY_NAMESJD').hide();
					return null;
				}else{
					$('#CITY_NAMESJD').show();
					$('#COUNTY_NAMESJD').show();
				}
				var tPROVINCE_NAME=$("#PROVINCE_NAMESJD").find("option:selected").text(); 
				var url = '${ctx}/address/b04r03addroption!citys.action';
				var params = {
					PROVINCE_NAME: tPROVINCE_NAME
				 };
				if(isMunicipalities(tPROVINCE_NAME)){
			    	$('#CITY_NAMESJD').hide();
					$('#CITY_NAMESJD option').remove();
			    }else{
					$('#CITY_NAMESJD').show();
				}
				jQuery.post(url, params, callbackcityjd, 'json');
			}
			function callbackcityjd(data){
				var d=data.districtBeans;
				var tPROVINCE_NAME=$("#PROVINCE_NAMESJD").find("option:selected").text(); 
				if(isMunicipalities(tPROVINCE_NAME)){
					$('#COUNTY_NAMESJD option').remove();
					$("#COUNTY_NAMESJD").prepend("<option selected='selected' value=''>请选择</option>");
			    	for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMESJD").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
					//backfresh('fresh');
			    }else{
					$('#CITY_NAMESJD option').remove();
					$("#CITY_NAMESJD").prepend("<option selected='selected' value=''>请选择</option>");
					for(var i=0;i<d.length;i++){
						$("#CITY_NAMESJD").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
					viewcountyjd();
			    }
			}
			
			function viewcountyjd(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMESJD").find("option:selected").text(); 			
				if(!isMunicipalities(tPROVINCE_NAME)){
					var tCITY_NAME=$("#CITY_NAMESJD").find("option:selected").text(); 
					if(tCITY_NAME!='请选择'){
						var url = '${ctx}/address/b04r03addroption!countys.action';
						var params = {
							CITY_NAME: tCITY_NAME
						 };
						jQuery.post(url, params, callbackcountyjd, 'json');
					}
				}
			}
			function callbackcountyjd(data){
				$('#COUNTY_NAMESJD option').remove();
				var d=data.districtBeans;
				$("#COUNTY_NAMESJD").prepend("<option selected='selected' value=''>请选择</option>");
				for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMESJD").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
				}
				//backfresh('fresh');
			}
			
			function ajaxQueryPage(pageNo){
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				if(isMunicipalities(tPROVINCE_NAME)){
					var tCITY_ID="";
			    	var tCOUNTY_ID=$("#COUNTY_NAMES").val();
			    }else{
					var tCITY_ID=$("#CITY_NAMES").val();
					var tCOUNTY_ID=$("#COUNTY_NAMES").val();
				}
				var tORG_NAME=$("#ORG_NAME").val();

				var tPROVINCE_NAMEJD=$("#PROVINCE_NAMESJD").find("option:selected").text(); 
				var tPROVINCE_IDJD=$("#PROVINCE_NAMESJD").val();
				if(isMunicipalities(tPROVINCE_NAMEJD)){
					var tCITY_IDJD="";
			    	var tCOUNTY_IDJD=$("#COUNTY_NAMESJD").val();
			    }else{
					var tCITY_IDJD=$("#CITY_NAMESJD").val();
					var tCOUNTY_IDJD=$("#COUNTY_NAMESJD").val();
				}
				
				var tISSJN=$("input[name='ISSJN'][checked]").val(); 
				var tSOURCE=$("input[name='SOURCE'][checked]").val(); 
				var tIS_DISTRI=$("input[name='IS_DISTRI'][checked]").val(); 
				var tCOLLECT_DATE=$("#COLLECT_DATE").val();
				var tCOLLECT_DATEEND=$("#COLLECT_DATEEND").val();
				var url = '${ctx}/address/b04r02addrquery!querycksr.action';
			    var params = {
								PROVINCE_ID:tPROVINCE_ID,
								CITY_ID:tCITY_ID,
								COUNTY_ID:tCOUNTY_ID,
								ORG_NAME:tORG_NAME,
								PROVINCE_IDJD:tPROVINCE_IDJD,
								CITY_IDJD:tCITY_IDJD,
								COUNTY_IDJD:tCOUNTY_IDJD,
								ISSJN:tISSJN,
								SOURCE:tSOURCE,
								IS_DISTRI:tIS_DISTRI,
								COLLECT_DATE:tCOLLECT_DATE,
								COLLECT_DATEEND:tCOLLECT_DATEEND,
			                  	pageNo:pageNo
			                 };
			    jQuery.post(url, params, callbackajaxQueryPage, 'json');
			}
			function callbackajaxQueryPage(data){
				//添加table
				addquerytable(data);
			}
			function addquerytable(data){
				var resultdata=data.page.result;
				$('#address_table tbody').remove();
				if(resultdata.length>0){
					for(var i=0;i<resultdata.length;i++){
						var tr="<tr id='tr_query'>";
						tr+="<td align=left class=listtablebodyleft1  width=150>&nbsp;"+resultdata[i].ORG_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=150>&nbsp;"+resultdata[i].ITEMNO+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=150>&nbsp;"+resultdata[i].COLLECT_DATE+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=200>&nbsp;"+resultdata[i].TOTAL_DISTRICT_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:200px'>";
						if(resultdata[i].ADDR_FLAG == 1){
							//tr+="<a href='#' onClick='upRgming(" + resultdata[i].SEQID + ");'>批注</a>&nbsp;<br>";
							tr+="街道地址: " + resultdata[i].REC_ALLADDR;
						}else{
							//tr+="<a href='#' onClick='upRgming(" + resultdata[i].SEQID + ");'>批注</a>&nbsp;<br>";
							tr+="街道地址: " + resultdata[i].REC_STREET;
							tr+="<br>机构地址: " + resultdata[i].REC_ORG;
						}
						tr+="<td align=left class=listtablebodyleft1  width=200>&nbsp;"+resultdata[i].REC_POST+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=200>&nbsp;"+resultdata[i].POSTDIST_NAME+"</td>";
						tr+="</td>";
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr><td colspan=7 class=listtablebodyleft2>"+data.page.ajaxUrl+"</td></tr>";
					$('#address_table').append(url);
				}else{
					var url="<tr><td colspan=7 class=listtablebodyleft2>找到 0 条记录</td></tr>";
					$('#address_table').append(url);
				}
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
			}
			function callajaxdelBldg(data){
				$.prompt(data.saveMessage,{buttons: {'确定': true}});
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
				backfresh('fresh');
			}
			function backfresh(value){
				if(value == "fresh") {
					ajaxQueryPage(0);
				}
			}
			function ajaxQueryPageexport(pageNo){
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				if(isMunicipalities(tPROVINCE_NAME)){
					var tCITY_ID="";
			    	var tCOUNTY_ID=$("#COUNTY_NAMES").val();
			    }else{
					var tCITY_ID=$("#CITY_NAMES").val();
					var tCOUNTY_ID=$("#COUNTY_NAMES").val();
				}
				var tORG_NAME=$("#ORG_NAME").val();

				var tPROVINCE_NAMEJD=$("#PROVINCE_NAMESJD").find("option:selected").text(); 
				var tPROVINCE_IDJD=$("#PROVINCE_NAMESJD").val();
				if(isMunicipalities(tPROVINCE_NAMEJD)){
					var tCITY_IDJD="";
			    	var tCOUNTY_IDJD=$("#COUNTY_NAMESJD").val();
			    }else{
					var tCITY_IDJD=$("#CITY_NAMESJD").val();
					var tCOUNTY_IDJD=$("#COUNTY_NAMESJD").val();
				}
				
				var tISSJN=$("input[name='ISSJN'][checked]").val(); 
				var tSOURCE=$("input[name='SOURCE'][checked]").val(); 
				var tIS_DISTRI=$("input[name='IS_DISTRI'][checked]").val(); 
				var tCOLLECT_DATE=$("#COLLECT_DATE").val();
				var tCOLLECT_DATEEND=$("#COLLECT_DATEEND").val();
				var url = '${ctx}/address/b04r02addrquery!exportcksr.action';
			    var params = {
								PROVINCE_ID:tPROVINCE_ID,
								CITY_ID:tCITY_ID,
								COUNTY_ID:tCOUNTY_ID,
								ORG_NAME:tORG_NAME,
								PROVINCE_IDJD:tPROVINCE_IDJD,
								CITY_IDJD:tCITY_IDJD,
								COUNTY_IDJD:tCOUNTY_IDJD,
								ISSJN:tISSJN,
								SOURCE:tSOURCE,
								IS_DISTRI:tIS_DISTRI,
								COLLECT_DATE:tCOLLECT_DATE,
								COLLECT_DATEEND:tCOLLECT_DATEEND,
			                  	pageNo:pageNo
			                 };		
			    jQuery.post(url, params, callbackajaxQueryPageexport, 'json');
			}
			function callbackajaxQueryPageexport(data){
				//添加table
				addquerytableexport(data);
			}
			function addquerytableexport(data){
				window.open(data.EXPORTALLPATH);
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
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
										全名址信息查询>>收容地址查询
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<a id="query" href='#' onClick="ajaxQueryPage(0)" style="font-size:12px">查询</a>
								<a id="queryclear" href='#' style="font-size:12px">重置</a>
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
										<table class="listtable" id="viewquery_table"  width="900">
											<tr>
												<td class="listtablehead" width="80">
													收寄类型
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<input type="radio" name="SOURCE" id="SOURCE" value="" checked="checked" >全部
													<input type="radio" name="SOURCE" id="SOURCE" value="1" >专业收寄
													<input type="radio" name="SOURCE" id="SOURCE" value="2" >大网收寄
												</td>
												<td class="listtablehead" width="80">
													是否集散												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<input type="radio" name="IS_DISTRI" id="IS_DISTRI" value="" checked="checked">全部
													<input type="radio" name="IS_DISTRI" id="IS_DISTRI" value="1">参加
													<input type="radio" name="IS_DISTRI" id="IS_DISTRI" value="0">不参加
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													收寄行政区:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty()" style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES" style="width:100"></select>
												</td>
												<td class="listtablehead">
													收寄单位:
												</td>
												<td class="listtablehead" id="td_tpdistrict_content">
													<input type="text" name="ORG_NAME" style="width: 300px; height:20px;" id="ORG_NAME" class="ac_input" size="300"/>
												</td>
											</tr>											
											<tr>
												<td class="listtablehead" width="80">
													省内/省际
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<input type="radio" name="ISSJN" id="ISSJN" value="" checked="checked" onclick="sjsn()">全部
													<input type="radio" name="ISSJN" id="ISSJN" value="1" onclick="sjsn()">省内
													<input type="radio" name="ISSJN" id="ISSJN" value="2" onclick="sjsn()">省际
												</td>
												<td class="listtablehead" width="80">
													寄达地:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="provincesjd" id="PROVINCE_NAMESJDT" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME"></s:select>
													<s:select theme="simple" list="provincesjd" id="PROVINCE_NAMESJD" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onChange="viewcityjd()"></s:select>
													<select class="simple" id="CITY_NAMESJD" onChange="viewcountyjd()" style="width:100"></select>
													<select class="simple" id="COUNTY_NAMESJD" style="width:100"></select>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													收寄时间  从:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<input type="text" name="COLLECT_DATE" style="width: 100px; height:20px;" id="COLLECT_DATE" class="ac_input" size="10" onClick="WdatePicker()"/>
												</td>
												<td class="listtablehead" width="80">
													到:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<input type="text" name="COLLECT_DATEEND" style="width: 100px; height:20px;" id="COLLECT_DATEEND" class="ac_input" size="10" onClick="WdatePicker()"/>
												</td>
											</tr>											
										</table>
									</div>
									
									<br />
									<div id="tablelistdiv">
										<table class="listtable" width="900"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left" colspan="7">
													出口信息
													&nbsp;&nbsp;&nbsp;
												<!--	<a id="ajaxQueryPageexport" href='#' style="font-size:12px" onClick="ajaxQueryPageexport(0);">导出EXCLE</a>-->
												
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft" width="150">
													收寄局
												</td>
												<td align="left" class="listtableheadleft" width="150">
													邮件号码
												</td>
												<td align="left" class="listtableheadleft" width="150">
													收寄日期
												</td>
												<td align="left" class="listtableheadleft" width="200">
													收件人行政区
												</td>
												<td align="left" class="listtableheadleft1" width="200">
													收件人地址
												</td>
												<td align="left" class="listtableheadleft1" width="100">
													收件人邮编
												</td>
												<td align="left" class="listtableheadleft1" width="100">
													收容投递区
												</td>
											</thead>
										</table>
										<table class="listtable" width="900" id="address_table_list" cellpadding="0" cellspacing="0">
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
