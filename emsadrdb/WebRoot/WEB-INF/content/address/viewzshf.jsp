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
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<title>全信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var tpageNo=1;
			var bro=$.browser;
			$(document).ready(function(){
				viewcity();							
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

			    	viewDT();
			    	viewDTZS();
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
				viewDT();
				viewDTZS();
			}
			function viewDTZS(){
				var tCITY_CODE=$("#PROVINCE_NAMES").val().substr(0,2);	
				if(!isMunicipalitiesID(tCITY_CODE.substr(0,2))){
					if($("#CITY_NAMES").val().length>0){
						tCITY_CODE=$("#CITY_NAMES").val().substr(0,4);
					}
				}
				var url = '${ctx}/address/b04r03addroption!dtsfromwh.action';
				var params = {
					CITY_CODE: tCITY_CODE
				 };
				jQuery.post(url, params, callbackDTZS, 'json');
			}
			function callbackDTZS(data){
				$('#DT_PK_CODEZS option').remove();
				var d=data.cpBeans;
				$("#DT_PK_CODEZS").append("<option value=''>请选择</option>"); 
				if(d.length>0){
					for(var i=0;i<d.length;i++){
						$("#DT_PK_CODEZS").append("<option value='" + d[i].DT_PK_CODE + "'>" + d[i].DT_NAME + "</option>");
					}
				}
			}
			function viewDT(){
				var tCITY_CODE=$("#PROVINCE_NAMES").val();	
				if(isMunicipalitiesID(tCITY_CODE.substr(0,2))){
					var url = '${ctx}/address/b04r03addroption!dts.action';
				}else{
					if($("#CITY_NAMES").val().length>0){
						tCITY_CODE=$("#CITY_NAMES").val();
					}
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
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				if(isMunicipalities(tPROVINCE_NAME)){
					var tCITY_ID=tPROVINCE_ID;
			    }else{
					var tCITY_ID=$("#CITY_NAMES").val();
				}
				var tDT_PK_CODE=$("#DT_PK_CODE").val();
				if(tDT_PK_CODE.length>0){
					var url = '${ctx}/address/b04r03addroption!dms.action';
					var params = {
						CITY_CODE: tCITY_ID,
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
					if(d.length==1){
						$("#PG_PK_CODE").val(d[0].PG_PK_CODE);
					}
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
				var tDT_PK_CODEZS=$("#DT_PK_CODEZS").val();
				var tSTREET_NAME=$("#STREET_NAME").val();
				var tISLX=$("input[name='ISLX'][checked]").val(); 
				var url = '${ctx}/address/b04r02addrquery!queryzshf.action';
			    var params = {
								PROVINCE_ID:tPROVINCE_ID,
								CITY_ID:tCITY_ID,
								COUNTY_ID:tCOUNTY_ID,
								DT_PK_CODEZS:tDT_PK_CODEZS,
								ISLX:tISLX,
								STREET_NAME:tSTREET_NAME,
			                  	pageNo:pageNo
			                 };
			    jQuery.post(url, params, callbackajaxQueryPage, 'json');
			}
			function callbackajaxQueryPage(data){
				//添加table
				addquerytable(data);
			}
			function addquerytable(data){
				tpageNo=data.page.pageNo;
				var resultdata=data.page.result;
				$('#address_table tbody').remove();
				if(resultdata.length>0){
					for(var i=0;i<resultdata.length;i++){
						var tr="<tr id='tr_query" + resultdata[i].RL_PG_ST_ID + "' onclick=xuanze('tr_query" + resultdata[i].RL_PG_ST_ID + "','rlCheckBox" + resultdata[i].RL_PG_ST_ID + "')>";
						tr+=" <td align=right class=listtablebodyleft1 style='width:30px'><input type='checkbox' onclick=xuanze('tr_query" + resultdata[i].RL_PG_ST_ID + "','rlCheckBox" + resultdata[i].RL_PG_ST_ID + "') id='rlCheckBox" + resultdata[i].RL_PG_ST_ID + "'  name='rlCheckBox' value='"+resultdata[i].RL_PG_ST_ID + "'>";
						tr+="</td>";
						tr+="<td align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].TOTAL_DISTRICT_NAME+"</td>";
						var tLX = "街道";
						var tRO = "";
						if(resultdata[i].RSDNBLDG_ID.length>0){
							tLX = "小区";
							tRO = resultdata[i].RSDNBLDG_NAME;
						}else if(resultdata[i].ORG_ID.length>0){
							tLX = "机构";
							tRO = resultdata[i].ORG_NAME;
						}
						if(resultdata[i].FLAG == 4){
							tLX = tLX + "(不分)";
						}else if(resultdata[i].FLAG == 5){
							tLX = tLX + "(单号)";
						}else if(resultdata[i].FLAG == 6){
							tLX = tLX + "(双号)";
						}
						tr+="<td align=left class=listtablebodyleft1  width=100>&nbsp;"+tLX+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].TOTAL_STREET_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].ALL_NUM+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=100>&nbsp;"+tRO+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].DT_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].DM_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft2  width=100>&nbsp;"+resultdata[i].PG_NAME+"</td>";
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="";
						
					url=url + "<tr><td colspan=9 class=listtablebodyleft2>"
							+data.page.ajaxUrl+	
							"<input type='text' name='pagenum' style='width:50px;height:20px;' id='pagenum' class='ac_input'/><a id='pagego' href='#' onClick='pagecx(" + data.page.totalCount + "," + data.page.pageSize + ")' style='font-size:12px'>GO</a>"+
							"</td></tr>";
							
					$('#address_table').append(url);
				}else{
					var url="<tr><td colspan=9 class=listtablebodyleft2>找到 0 条记录</td></tr>";
					$('#address_table').append(url);
				}
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
			}
			function backfresh(value){
				if(value == "fresh") {
					ajaxQueryPage(tpageNo);
				}
			}
			function xuanze(st,id){
				if($("#" + id).attr("checked")){
					$("#" + st).css("background","#FBFBFF");
					$("#" + id).attr("checked",false);
				}else{
					$("#" + st).css("background","#FEEBD0");
					$("#" + id).attr("checked",true);
				}
			}
			function tssq(v){
				var ts = "";
				var url = "";
				if(v=='1'){
					ts = '该操作将删除"机构小区"批区批段内容,不删除"街道"批区批段内容！';
					url = '${ctx}/address/b04cud02addrconfig!delzshf.action';
				}else if(v=='2'){
					ts = '该操作将移除投递区！';
					url = '${ctx}/address/b04cud02addrconfig!delzsyq.action';
				}else if(v=='3'){
					ts = '该操作将移除投递部！';
					url = '${ctx}/address/b04cud02addrconfig!delzsyb.action';
				}else if(v=='4'){
					ts = '该操作将移除投递段！';
					url = '${ctx}/address/b04cud02addrconfig!delzsyd.action';
				}
				var c=$('#address_table tbody input[name=rlCheckBox]:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
					cv = cv+','+$(this).val();
				});
				if(cv==''){
					$.prompt('请选择地址',{buttons: {'确定': true}});
					return false;
				}
				var tTOTAL_ALL_VALUE=cv.substr(1,cv.length);
				var tDIST_CD=$("#PROVINCE_NAMES").val();
				$.prompt(ts,{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									TOTAL_ALL_VALUE:tTOTAL_ALL_VALUE,
									DIST_CD:tDIST_CD
							};
							//var url = '${ctx}/address/b04cud02addrconfig!delzshf.action';
							if(!(bro.msie && bro.version >=8)){
								$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
							}else{
								$("#nodata").show();
								$("#yesdata").hide();
							}
							jQuery.post(url, params, callajaxAdd, 'json');
						}
					}
				});
				
			}
			function uprgpqpd(){
				var c=$('#address_table tbody input[name=rlCheckBox]:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
					cv = cv+','+$(this).val();
				});
				if(cv==''){
					$.prompt('请选择地址',{buttons: {'确定': true}});
					return false;
				}
				var tTOTAL_ALL_VALUE=cv.substr(1,cv.length);
				var tDT_PK_CODE=$("#DT_PK_CODE").val();
				var tDM_PK_CODE=$("#DM_PK_CODE").val();
				var tPG_PK_CODE=$("#PG_PK_CODE").val();
				var tDIST_CD=$("#PROVINCE_NAMES").val();
				
				if(tDT_PK_CODE.length<1){
					$.prompt('请选择配置信息',{buttons: {'确定': true}});
					return false;
				}

				$.prompt('该操作将修改批区批段信息！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									TOTAL_ALL_VALUE:tTOTAL_ALL_VALUE,
									DIST_CD:tDIST_CD,
									DT_PK_CODE:tDT_PK_CODE,
									DM_PK_CODE:tDM_PK_CODE,
									PG_PK_CODE:tPG_PK_CODE
							};
							var url = '${ctx}/address/b04cud02addrconfig!upzsqbd.action';
							if(!(bro.msie && bro.version >=8)){
								$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
							}else{
								$("#nodata").show();
								$("#yesdata").hide();
							}
							jQuery.post(url, params, callajaxAdd, 'json');
							$("#KXDT").val("查询投递区");
							$("#DT_PK_CODE").val("");
							$('#DM_PK_CODE option').remove();
							$("#DM_PK_CODE").append("<option value=''>请选择</option>"); 
							$('#PG_PK_CODE option').remove();
							$("#PG_PK_CODE").append("<option value=''>请选择</option>"); 
							$("#PK_REMARK").val("");
						}
					}
				});
			}
			function callajaxAdd(data){
				$('#queryAll').attr('checked',false);
				$.prompt(data.saveMessage,{buttons: {'确定': true}});
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
				backfresh("fresh");
			}
			function queryAll(){
				var n = $("input[name=chooseName]:checked").length; 
				 if(n==0){ 
					 $("#address_table tbody input[name=rlCheckBox]").each(function(){
			                $(this).attr('checked',false);
			            }); 
				 }else{ 
					$("#address_table tbody input[name=rlCheckBox]").each(function(){
				                $(this).attr('checked',true);
				           });
				 } 
			}
			function kxtdq(){
				var kxz = $("#KXDT").val();
				if(kxz.length > 0 && kxz != '查询投递区'){
					$("select[name='DT_PK_CODE'] option").each(function(i){        //循环拼装被选中项的值
						if($(this).text().indexOf(kxz)>-1){
							$("#DT_PK_CODE").val($(this).val());
							viewDM();
							return false;
						}
					});
				}else{
					$("#DT_PK_CODE").val("");
					$('#DM_PK_CODE option').remove();
					$("#DM_PK_CODE").append("<option value=''>请选择</option>"); 
					$('#PG_PK_CODE option').remove();
					$("#PG_PK_CODE").append("<option value=''>请选择</option>"); 
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
										全名址信息查询>>批区批段全数据查询
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
												<td class="js_left_txt" id="td_privince_content" colspan="1">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty()" style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES" style="width:100"></select>
												</td>
												<td class="listtablehead">
													投递区:
												</td>
												<td class="js_left_txt" id="td_tpdistrict_content" colspan="1">
													<select class="simple" id="DT_PK_CODEZS" style="width:300"></select>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													街道地址:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="1">
													<input type="text" name="STREET_NAME" style="width: 300px; height:20px;" id="STREET_NAME" class="ac_input" size="300"/>
												</td>
												<td class="listtablehead" width="80">
													类型选择:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="1">
													<input type="radio" name="ISLX" id="ISLX" value="">全部
													<input type="radio" name="ISLX" id="ISLX" value="1">街道
													<input type="radio" name="ISLX" id="ISLX" value="2" checked="checked">机构&小区
													<input type="radio" name="ISLX" id="ISLX" value="3">小区
													<input type="radio" name="ISLX" id="ISLX" value="4">机构
												</td>
											</tr>
										</table>
									</div>
									
									<br />
									<div id="tablelistdiv">
										<table class="listtable" width="900"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="10">
													<a href='#' onClick="tssq('1');">&nbsp;删除机构小区批区批段</a>
													&nbsp;&nbsp;&nbsp;
													<a href='#' onClick="tssq('2');">&nbsp;移除投递区</a>
													&nbsp;&nbsp;&nbsp;
													<a href='#' onClick="tssq('3');">&nbsp;移除投递部</a>
													&nbsp;&nbsp;&nbsp;
													<a href='#' onClick="tssq('4');">&nbsp;移除投递段</a>
												</th>
											</thead>
											<thead>
												<th align="left"  colspan="2">
													配置到投递区-部-段：
												</th>
												<th align="left"  colspan="8">
													<input onkeyup="kxtdq()" type="text" name="KXDT" id="KXDT" value="查询投递区" style="width: 100px; height:20px;color:#FF0000" onFocus="this.select();">
													<select class="simple" id="DT_PK_CODE" name="DT_PK_CODE"  onChange="viewDM()"  style="width:200"></select>
													<select class="simple" id="DM_PK_CODE"  onChange="viewPG();"  style="width:100"></select>
													<select class="simple" id="PG_PK_CODE"  style="width:100"></select>
													<a href='#' onClick="uprgpqpd();">&nbsp;提交</a>
												</th>
											</thead>
											<thead>
												<td align="right" class="listtableheadleft" style='width:30px'>
													<input id="queryAll" name="chooseName" type="checkbox" onClick="queryAll()"/>
												</td>
												<td align="left" class="listtableheadleft" width="100">
													行政区
												</td>
												<td align="left" class="listtableheadleft1" width="50">
													类型
												</td>
												<td align="left" class="listtableheadleft1" width="100">
													街道
												</td>
												<td align="left" class="listtableheadleft1" width="100">
													号段
												</td>
												<td align="left" class="listtableheadleft1" width="80">
													小区/机构
												</td>
												<td align="left" class="listtableheadleft1" width="100">
													投递区
												</td>
												<td align="left" class="listtableheadleft1" width="80">
													投递部
												</td>
												<td align="left" class="listtableheadleft1" width="80">
													投递段
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
