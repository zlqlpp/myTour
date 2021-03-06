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
		<title>地址缺失小区信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var tpageNo=1;
			var bro=$.browser;
			$(document).ready(function(){
				viewcity();
			})
			function ajaxQueryADN(tSSLX){
				if(tSSLX.indexOf("vbd2sr")>-1){
					$("#yjd").text("查询中...");
				}else if(tSSLX.indexOf("vbd3nr")>-1){
					$("#wjd").text("查询中...");
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
				if(str[1].indexOf("vbd2sr")>-1){
					$("#yjd").text("");
					$("#yjd").text(str[2]);
				}else if(str[1].indexOf("vbd3nr")>-1){
					$("#wjd").text("");
					$("#wjd").text(str[2]);
				}
				
			}
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
	
				var tRSDNBLDG_NAME=$("#RSDNBLDG_NAME").val();
				var tISSTREET_NAME=$("input[name='ISSTREET_NAME'][checked]").val();
				var tSTREET_NAME=$("#STREET_NAME").val();
				var url = '${ctx}/address/b04r02addrquery!querybldgrsdnsdius.action';
			    var params = {
								PROVINCE_ID:tPROVINCE_ID,
								CITY_ID:tCITY_ID,
								COUNTY_ID:tCOUNTY_ID,
								RSDNBLDG_NAME:tRSDNBLDG_NAME,
								STREET_NAME:tSTREET_NAME,
								ISSTREET_NAME:tISSTREET_NAME,
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
				ajaxQueryADN('vbd2sr');
				ajaxQueryADN('vbd3nr');
				var resultdata=data.page.result;
				$('#address_table tbody').remove();
				if(resultdata.length>0){
					for(var i=0;i<resultdata.length;i++){
						var tr="<tr id='tr_query" + resultdata[i].RSDNBLDG_ID + "' onclick=xuanze('tr_query" + resultdata[i].RSDNBLDG_ID + "','rlCheckBox" + resultdata[i].RSDNBLDG_ID + "')>";
						tr+=" <td align=right class=listtablebodyleft1 style='width:30px'><input type='checkbox' onclick=xuanze('tr_query" + resultdata[i].RSDNBLDG_ID + "','rlCheckBox" + resultdata[i].RSDNBLDG_ID + "') id='rlCheckBox" + resultdata[i].RSDNBLDG_ID + "'  name='rlCheckBox' value='"+resultdata[i].RSDNBLDG_ID + "'>";
						tr+="</td>";
						tr+="<td align=left class=listtablebodyleft1 width=100> <a href='#' onClick='upBldgrsdns("+ resultdata[i].DIST_CD + "," + resultdata[i].RSDNBLDG_ID + ");'>维护</a> <a href='#' onClick='delBldgrsdns("+ resultdata[i].DIST_CD + "," + resultdata[i].RSDNBLDG_ID + ");'>删除</a></td>";
						tr+="<td align=left class=listtablebodyleft1  width=200>&nbsp;"+resultdata[i].TOTAL_DISTRICT_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=150>&nbsp;"+resultdata[i].TOTAL_STREET_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=200>&nbsp;"+resultdata[i].RSDNBLDG_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft2  width=200>&nbsp;"+resultdata[i].RSDNBLDG_ABBR+"</td>";			
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr><td colspan=7 class=listtablebodyleft2>"
						+"<a href='#' onClick='tssq();'>&nbsp;<b>批量删除数据提交</b></a>"
						"</td></tr>";
					url=url+"<tr><td colspan=7 class=listtablebodyleft2>"
							+data.page.ajaxUrl+	
							"<input type='text' name='pagenum' style='width:50px;height:20px;' id='pagenum' class='ac_input'/><a id='pagego' href='#' onClick='pagecx(" + data.page.totalCount + "," + data.page.pageSize + ")' style='font-size:12px'>GO</a>"+
							"</td></tr>";
					$('#address_table').append(url);
				}else{
					var url="<tr><td colspan=7 class=listtablebodyleft2>找到 0 条记录</td></tr>";
					$('#address_table').append(url);
				}
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
			}
			function upBldgrsdns(tDIST_CD,tRSDNBLDG_ID){
				value = window.showModalDialog("${ctx}/address/b04cud01addrview!addbldgrsdnsdius.action?FLAG=ADD&DIST_CD=" + tDIST_CD + "&RSDNBLDG_ID=" + tRSDNBLDG_ID,"Code","dialogWidth:600px;dialogHeight:600px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			function delBldgrsdns(tDIST_CD,tRSDNBLDG_ID){
				$.prompt('该操作将删除此机构！确认执行操作？',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									DIST_CD:tDIST_CD,
									RSDNBLDG_ID:tRSDNBLDG_ID
							};
							var url = '${ctx}/address/b04cud02addrconfig!delbldgrsdnsdius.action';
							if(!(bro.msie && bro.version >=8)){
								$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
							}else{
								$("#nodata").show();
								$("#yesdata").hide();
							}
							jQuery.post(url, params, callajaxdelBldg, 'json');
						}
					}
				});
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
			function tssq(){
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
				$.prompt('该操作将删除地址！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									TOTAL_ALL_VALUE:tTOTAL_ALL_VALUE,
									DIST_CD:tDIST_CD
							};
							var url = '${ctx}/address/b04cud02addrconfig!delbldgdiustssq.action';
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
										差异数据维护 >>地址缺失小区信息
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
												<td class="js_left_txt" id="td_tpdistrict_content" colspan="3">
													<input type="text" name="RSDNBLDG_NAME" style="width: 300px; height:20px;" id="RSDNBLDG_NAME" class="ac_input" size="300"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													有/无街道:
												</td>
												<td class="js_left_txt" id="td_tpdistrict_content" colspan="3">
													<input type="radio" name="ISSTREET_NAME" id="ISSTREET_NAME" value="1">有街道<label id="yjd" style="color:#FF0000"></label>
													<input type="radio" name="ISSTREET_NAME" id="ISSTREET_NAME" value="0" checked="checked">无街道<label id="wjd" style="color:#FF0000"></label>
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
													地址缺失小区信息
												</th>
											</thead>
											<thead>
												<th align="left"  colspan="7">
													<a href='#' onClick="tssq();">&nbsp;批量删除数据提交</a>
												</th>
											</thead>
											<thead>
												<td align="right" class="listtableheadleft" style='width:30px'>
													<input id="queryAll" name="chooseName" type="checkbox" onClick="queryAll()"/>
												</td>
												<td align="left" class="listtableheadleft" width="100">
													操作
												</td>
												<td align="left" class="listtableheadleft" width="150">
													行政区
												</td>
												<td align="left" class="listtableheadleft" width="150">
													街道名称
												</td>
												<td align="left" class="listtableheadleft" width="200">
													小区名称
												</td>
												<td align="left" class="listtableheadleft1" width="150">
													小区简拼
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
