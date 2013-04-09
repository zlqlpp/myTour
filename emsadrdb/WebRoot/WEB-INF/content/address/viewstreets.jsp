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
		<title>街道信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				viewcity();
				//if('${EMS_USER.rulLevel}' == '20'){
					//$('#tr_ALL_DATA_FLAG').hide();
				//}
			})
			//展示市
			function viewcity(){
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
				//if('${EMS_USER.usCityOffice}'.substr(4,2)=='00' || '${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2' || '${EMS_USER.rulLevel}' == '5' || '${EMS_USER.rulLevel}' == '10'){
						$("#COUNTY_NAMES").prepend("<option selected='selected' value=''>请选择</option>");
				//}
				for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
				}
				//backfresh('fresh');
			}
			function ajaxQueryADN(tSSLX){
				if(tSSLX.indexOf("vs2sr")>-1){
					$("#jddss").text("查询中...");
				}else if(tSSLX.indexOf("vs3sr")>-1){
					$("#jdjjs").text("查询中...");
				}else if(tSSLX.indexOf("vs4sr")>-1){
					$("#xqdss").text("查询中...");
				}else if(tSSLX.indexOf("vs5sr")>-1){
					$("#xqjjs").text("查询中...");
				}else if(tSSLX.indexOf("vs6sr")>-1){
					$("#jgdss").text("查询中...");
				}else if(tSSLX.indexOf("vs7sr")>-1){
					$("#jgjjs").text("查询中...");
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
				var url = '${ctx}/address/b04r02addrquery!queryflagnum.action';
			    var params = {
								PROVINCE_ID:tPROVINCE_ID,
								CITY_ID:tCITY_ID,
								COUNTY_ID:tCOUNTY_ID,
								SSLX:tSSLX
			                 };
			    jQuery.post(url, params, callbackajaxQueryADN, 'json');
			}
			function callbackajaxQueryADN(data){
				var d=data.ALL_DATA_NUMBER;
				var str = d.split('#');
				if(str[1].indexOf("vs2sr")>-1){
					$("#jddss").text("");
					$("#jddss").text(str[2]);
				}else if(str[1].indexOf("vs3sr")>-1){
					$("#jdjjs").text("");
					$("#jdjjs").text(str[2]);
				}else if(str[1].indexOf("vs4sr")>-1){
					$("#xqdss").text("");
					$("#xqdss").text(str[2]);
				}else if(str[1].indexOf("vs5sr")>-1){
					$("#xqjjs").text("");
					$("#xqjjs").text(str[2]);
				}else if(str[1].indexOf("vs6sr")>-1){
					$("#jgdss").text("");
					$("#jgdss").text(str[2]);
				}else if(str[1].indexOf("vs7sr")>-1){
					$("#jgjjs").text("");
					$("#jgjjs").text(str[2]);
				}
				
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
				var tSTREET_NAME=$("#STREET_NAME").val();
				var tRSDNBLDG_NAME=$("#RSDNBLDG_NAME").val();
				var tORG_NAME=$("#ORG_NAME").val();
				var tALL_DATA_FLAG=$("input[name='ALL_DATA_FLAG'][checked]").val(); 
				var tPOST_CD=$("#POST_CD").val();
				var url = '${ctx}/address/b04r02addrquery!querystreets.action';
			    var params = {
								POST_CD:tPOST_CD,
								ALL_DATA_FLAG:tALL_DATA_FLAG,
								PROVINCE_ID:tPROVINCE_ID,
								CITY_ID:tCITY_ID,
								COUNTY_ID:tCOUNTY_ID,
								STREET_NAME:tSTREET_NAME,
								RSDNBLDG_NAME:tRSDNBLDG_NAME,
								ORG_NAME:tORG_NAME,
			                  	pageNo:pageNo
			                 };
			    jQuery.post(url, params, callbackajaxQueryPage, 'json');
			}
			function callbackajaxQueryPage(data){
				//添加table
				addquerytable(data);
			}
			function addquerytable(data){
				
				ajaxQueryADN("vs2sr");
				ajaxQueryADN("vs3sr");
				ajaxQueryADN("vs4sr");
				ajaxQueryADN("vs5sr");
				ajaxQueryADN("vs6sr");
				ajaxQueryADN("vs7sr");
				var resultdata=data.page.result;
				$('#address_table tbody').remove();
				if(resultdata.length>0){
					for(var i=0;i<resultdata.length;i++){
						var tr="<tr id='tr_query'>";
						var uscitycode = '${EMS_USER.usCityOffice}';
						if('${EMS_USER.usCityOffice}' == ''){
							uscitycode = '${EMS_USER.usProvinceOffice}';
						}
						if(getRulUsProvinceOfficeTF('${EMS_USER.rulLevel}',uscitycode,resultdata[i].DIST_CD)){
						tr+="<td align=left class=listtablebodyleft1 width=180>";
						tr+=" <a href='#' onClick='xxStreet("+ resultdata[i].DIST_CD + "," + resultdata[i].STRT_ID + ");'>详细</a>  <a href='#' onClick='viewLogsbyid(" + resultdata[i].DIST_CD + "," + resultdata[i].STRT_ID + "," + '"' + resultdata[i].TOTAL_DISTRICT_NAME  + '"' + ");'>日志</a>";
						if($("input[name='ALL_DATA_FLAG'][checked]").val() == 'qbsj' || $("input[name='ALL_DATA_FLAG'][checked]").val() == 'jdds' || $("input[name='ALL_DATA_FLAG'][checked]").val() == 'jdjj'){
						tr+=" <a href='#' onClick='upStreet("+ resultdata[i].DIST_CD + "," + resultdata[i].STRT_ID + ");'>修改</a> <a href='#' onClick='delStreet("+ resultdata[i].DIST_CD + "," + resultdata[i].STRT_ID + "," + '"' + resultdata[i].TOTAL_STREET_NAME + '"' + ");'>删除</a>";
						//tr+=" <a href='#' onClick='hbStreet(" + resultdata[i].DIST_CD + "," + resultdata[i].STRT_ID  + "," + '"' + resultdata[i].TOTAL_STREET_NAME + '"' + ");'>合并</a>";	
						}
						if(getUsWebRule('${EMS_USER.rulLevel}') && getUsWebAudit(resultdata[i].DATA_FLAG)){
							tr+=" <a href='#' onClick='shStreet("+ resultdata[i].DIST_CD + "," + resultdata[i].STRT_ID + ");'>审核</a>";
						}
						tr += "</td>";
						}else{
						tr+="<td align=left class=listtablebodyleft1 width=150>&nbsp;</td>";
						}
						tr+="<td align=left class=listtablebodyleft1  width=75>&nbsp;"+resultdata[i].DATA_FLAG_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=200>&nbsp;"+resultdata[i].TOTAL_DISTRICT_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=200>&nbsp;"+resultdata[i].TOTAL_STREET_NAME+"</td>";	
						tr+="<td align=left class=listtablebodyleft1  width=75>&nbsp;"+resultdata[i].POST_CD+"</td>";			
						tr+="<td align=left class=listtablebodyleft2 width=100>&nbsp;";
						if(getRulUsProvinceOfficeTF('${EMS_USER.rulLevel}','${EMS_USER.usCityOffice}',resultdata[i].DIST_CD)){
						tr+="<a href='#' onClick='viewPD(" + resultdata[i].DIST_CD + "," + resultdata[i].STRT_ID + ");'>批段</a>";
						tr+=" <a href='#' onClick='viewBo(" + resultdata[i].DIST_CD + "," + resultdata[i].STRT_ID  + "," + '"' + resultdata[i].TOTAL_DISTRICT_NAME + '"' + "," + '"' + resultdata[i].TOTAL_STREET_NAME + '"' + ");'>小区&机构</a></td>";
						}else{
						tr+="</td>";
						}
						
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr><td colspan=6 class=listtablebodyleft2>"
						+data.page.ajaxUrl+	
						"<input type='text' name='pagenum' style='width:50px;height:20px;' id='pagenum' class='ac_input'/><a id='pagego' href='#' onClick='pagecx(" + data.page.totalCount + "," + data.page.pageSize + ")' style='font-size:12px'>GO</a>"+
						"</td></tr>";
					$('#address_table').append(url);
				
				}else{
					var url="<tr><td colspan=6 class=listtablebodyleft2>找到 0 条记录</td></tr>";
					$('#address_table').append(url);
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
			function shStreet(tDIST_CD,tSTRT_ID){
				var value = window.showModalDialog("${ctx}/address/b04cud01addrview!shstreet.action?FLAG=SH&DIST_CD=" + tDIST_CD + "&STRT_ID=" + tSTRT_ID,"Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");				
				backfresh(value);
			}
			function xxStreet(tDIST_CD,tSTRT_ID){
				var value = window.showModalDialog("${ctx}/address/b04r01addrview!xxstreet.action?FLAG=XX&DIST_CD=" + tDIST_CD + "&STRT_ID=" + tSTRT_ID,"Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");				
			}
			function addStreet(){
				var value = window.showModalDialog("${ctx}/address/b04cud01addrview!addstreet.action?FLAG=ADD","Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			function upStreet(tDIST_CD,tSTRT_ID){
				var value = window.showModalDialog("${ctx}/address/b04cud01addrview!upstreet.action?FLAG=UPDATE&DIST_CD=" + tDIST_CD + "&STRT_ID=" + tSTRT_ID,"Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");				
				backfresh(value);
			}
			function hbStreet(tDIST_CD,tSTRT_ID,tTOTAL_STREET_NAME){
				var value = window.showModalDialog("${ctx}/address/b04cud01addrview!hbstreet.action?FLAG=HEBING&DIST_CD=" + tDIST_CD + "&STRT_ID=" + tSTRT_ID + "&TOTAL_STREET_NAME=" + encodeURI(tTOTAL_STREET_NAME),"Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");				
				backfresh(value);
			}
			function delStreet(tDIST_CD,tSTRT_ID,tTOTAL_STREET_NAME){
				var txt = '该操作将删除此街道,及关联的小区,机构！确认执行操作？' +
							'<br>备注：<input type="text" id="NOTE" name="NOTE"/>';
				$.prompt(txt,{
					buttons: {'确定': true, '取消': false },
					callback: function(v,m){
						var tNOTE= m.find('#NOTE').val();
						if(v){
							var params = {
								NOTE:tNOTE,
								DIST_CD:tDIST_CD,
								STRT_ID:tSTRT_ID,
								TOTAL_STREET_NAME:tTOTAL_STREET_NAME
							};
							var url = '${ctx}/address/b04cud02addrconfig!delstreet.action';
							if(!(bro.msie && bro.version >=8)){
								$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
							}else{
								$("#nodata").show();
								$("#yesdata").hide();
							}
							jQuery.post(url, params, callajaxdelStreet, 'json');
						}
					}
				});
			}
			function callajaxdelStreet(data){
				$.prompt(data.saveMessage,{buttons: {'确定': true}});
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
				backfresh('fresh');
			}
			function addBldgrsdns(){
				var value = window.showModalDialog("${ctx}/address/b04cud01addrview!addbldgrsdns.action?FLAG=ADD","Code","dialogWidth:600px;dialogHeight:600px;resizable=1,scrollbars=auto");
			}
			function addOrganization(){
				var value = window.showModalDialog("${ctx}/address/b04cud01addrview!addorganization.action?FLAG=ADD","Code","dialogWidth:600px;dialogHeight:600px;resizable=1,scrollbars=auto");
			}
			function viewPD(tDIST_CD,tSTRT_ID){
				var value = window.showModalDialog("${ctx}/adr/c04r01!view.action?distCd=" + tDIST_CD + "&strtId=" + tSTRT_ID,"Code","dialogWidth:1050px;dialogHeight:650px;resizable=1,scrollbars=auto");
			}
			function viewBldgrsdns(tDIST_CD,tSTRT_ID,tTOTAL_DISTRICT_NAME,tTOTAL_STREET_NAME){
				var value = window.showModalDialog("${ctx}/address/b04r01addrview!viewbldgrsdns.action?DIST_CD=" + tDIST_CD + "&STRT_ID=" + tSTRT_ID + "&TOTAL_DISTRICT_NAME=" + encodeURI(tTOTAL_DISTRICT_NAME) + "&TOTAL_STREET_NAME=" + encodeURI(tTOTAL_STREET_NAME)+ "&RSDNBLDG_NAME=" + encodeURI($("#RSDNBLDG_NAME").val()),"Code","dialogWidth:950px;dialogHeight:600px;resizable=1,scrollbars=auto");
			}
			function viewOrganization(tDIST_CD,tSTRT_ID,tTOTAL_DISTRICT_NAME,tTOTAL_STREET_NAME){
				var value = window.showModalDialog("${ctx}/address/b04r01addrview!vieworganizations.action?DIST_CD="+ tDIST_CD + "&STRT_ID=" + tSTRT_ID + "&TOTAL_DISTRICT_NAME=" + encodeURI(tTOTAL_DISTRICT_NAME) + "&TOTAL_STREET_NAME=" + encodeURI(tTOTAL_STREET_NAME)+ "&ORG_NAME=" + encodeURI($("#ORG_NAME").val()),"Code","dialogWidth:950px;dialogHeight:600px;resizable=1,scrollbars=auto");
			}
			function viewBo(tDIST_CD,tSTRT_ID,tTOTAL_DISTRICT_NAME,tTOTAL_STREET_NAME){
				var value = window.showModalDialog("${ctx}/address/b04r01addrview!viewbos.action?DIST_CD="+ tDIST_CD + "&STRT_ID=" + tSTRT_ID + "&TOTAL_DISTRICT_NAME=" + encodeURI(tTOTAL_DISTRICT_NAME) + "&TOTAL_STREET_NAME=" + encodeURI(tTOTAL_STREET_NAME)+ "&ORG_NAME=" + encodeURI($("#ORG_NAME").val()) + "&RSDNBLDG_NAME=" + encodeURI($("#RSDNBLDG_NAME").val())+ "&ALL_DATA_FLAG=" + $("input[name='ALL_DATA_FLAG'][checked]").val(),"Code","dialogWidth:950px;dialogHeight:600px;resizable=1,scrollbars=auto");
			}
			function viewLogsbyid(tDIST_CD,tSTRT_ID,tTOTAL_DISTRICT_NAME,tTOTAL_STREET_NAME){
				var value = window.showModalDialog("${ctx}/address/b04r01addrview!viewlogsbyid.action?DIST_CD="+ tDIST_CD + "&STRT_ID=" + tSTRT_ID + "&TOTAL_DISTRICT_NAME=" + encodeURI(tTOTAL_DISTRICT_NAME) + "&TOTAL_STREET_NAME=" + encodeURI(tTOTAL_STREET_NAME),"Code","dialogWidth:950px;dialogHeight:600px;resizable=1,scrollbars=auto");
			}
			function viewlogs(){
				var value = window.showModalDialog("${ctx}/address/b04r01addrview!viewlogs.action","Code","dialogWidth:950px;dialogHeight:600px;resizable=1,scrollbars=auto");
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
										投递区部基础数据 >>地址维护
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
													行政区域:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty()" style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES" style="width:100"></select>
												</td>
												
											</tr>
											<tr>
												<td class="listtablehead">
													邮编:
												</td>
												<td class="js_left_txt" id="td_tpdistrict_content">
													<input type="text" name="POST_CD" style="width: 300px; height:20px;" id="POST_CD" class="ac_input" size="300"/>
												</td>
												<td class="listtablehead">
													街道地址:
												</td>
												<td class="js_left_txt" id="td_tpdistrict_content" colspan="3">
													<input type="text" name="STREET_NAME" style="width: 300px; height:20px;" id="STREET_NAME" class="ac_input" size="300"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													小区:
												</td>
												<td class="js_left_txt" id="td_city_content">
													<input type="text" name="RSDNBLDG_NAME" style="width: 300px; height:20px;" id="RSDNBLDG_NAME" class="ac_input" size="300"/>
												</td>
												<td class="listtablehead">
													机构:
												</td>
												<td class="js_left_txt" id="td_tpdistrict_content">
													<input type="text" name="ORG_NAME" style="width: 300px; height:20px;" id="ORG_NAME" class="ac_input" size="300"/>
												</td>
											</tr>
											<tr id="tr_ALL_DATA_FLAG">
												<td class="listtablehead" colspan="4">
													<input type="radio" name="ALL_DATA_FLAG" value="qbsj" checked="checked">全部数据
													<input type="radio" name="ALL_DATA_FLAG" value="jdds">街道待审<label id="jddss" style="color:#FF0000"></label>
													<input type="radio" name="ALL_DATA_FLAG" value="xqds">小区待审<label id="xqdss" style="color:#FF0000"></label>
													<input type="radio" name="ALL_DATA_FLAG" value="jgds">机构待审<label id="jgdss" style="color:#FF0000"></label>
													<input type="radio" name="ALL_DATA_FLAG" value="jdjj">街道拒绝<label id="jdjjs" style="color:#FF0000"></label>
													<input type="radio" name="ALL_DATA_FLAG" value="xqjj">小区拒绝<label id="xqjjs" style="color:#FF0000"></label>
													<input type="radio" name="ALL_DATA_FLAG" value="jgjj">机构拒绝<label id="jgjjs" style="color:#FF0000"></label>
												</td>
											</tr>
										</table>
									</div>
									
									<br />
									<div id="tablelistdiv">
										<table class="listtable" width="900"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left">
													街道信息
												</th>
												<th align="right" colspan="5">
													<a href='#' onClick="addStreet();">新增街道</a>
													&nbsp;
													
													<!--<a href='#' onClick="addBldgrsdns();">新增小区</a>
													&nbsp;
													<a href='#' onClick="addOrganization();">新增机构</a>
													&nbsp;-->
													
													<a href='#' onClick="viewlogs();">查询日志</a>
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft" width="180">
													操作
												</td>
												<td align="left" class="listtableheadleft" width="75">
													数据状态
												</td>
												<td align="left" class="listtableheadleft" width="200">
													行政区
												</td>
												<td align="left" class="listtableheadleft" width="200">
													街道名称
												</td>
												<td align="left" class="listtableheadleft" width="75">
													邮编
												</td>
												<td align="left" class="listtableheadleft1" width="100">
													明细
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
