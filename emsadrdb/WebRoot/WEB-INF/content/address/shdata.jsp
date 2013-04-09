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
		<title>地址审核</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
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
					backfresh("fresh");
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
				backfresh("fresh");
			}
			function ajaxQueryPage(pageNo){
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var tTYPE_FLAG=$("#TYPE_FLAG").val();
				var tTYPE_DATA=$("#TYPE_DATA").val();
				var tCOUNTY_ID=$("#COUNTY_NAMES").val();
				var tCITY_ID=$("#CITY_NAMES").val();
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				
				var url = '${ctx}/address/b04r02addrquery!queryshdata.action';
			    var params = {
								TYPE_FLAG:tTYPE_FLAG,
								TYPE_DATA:tTYPE_DATA,
								COUNTY_ID:tCOUNTY_ID,
								CITY_ID:tCITY_ID,
								PROVINCE_ID:tPROVINCE_ID,
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
						tr+="<td align=left class=listtablebodyleft1 width=50><input type='checkbox' name='rlCheckBox' value='"+resultdata[i].TOTAL_VALUE + "'></td>";
						tr+="<td align=left class=listtablebodyleft1 width=75> <a href='#' onClick='viewLogsbyid(" + resultdata[i].DIST_CD + "," + resultdata[i].TYPE_ID + "," + '"' + resultdata[i].TOTAL_NAME  + '"' + ");'>日志</a></td>";
						tr+="<td align=left class=listtablebodyleft1  width=75>&nbsp;"+resultdata[i].TYPE_FLAG_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=75>&nbsp;"+resultdata[i].TYPE_DATA_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=500>&nbsp;"+resultdata[i].TOTAL_NAME+"</td>";	
						tr+="<td align=left class=listtablebodyleft2  width=200>&nbsp;"+resultdata[i].NOTE+"</td>";		
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr><td colspan=6 class=listtablebodyleft2>"+data.page.ajaxUrl+"</td></tr>";
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
			function viewLogsbyid(tDIST_CD,tTYPE_ID,tTOTAL_DISTRICT_NAME,tTOTAL_STREET_NAME){
				if($("#TYPE_DATA").val()=='strt'){
					var value = window.showModalDialog("${ctx}/address/b04r01addrview!viewlogsbyid.action?DIST_CD="+ tDIST_CD + "&STRT_ID=" + tTYPE_ID + "&TOTAL_DISTRICT_NAME=" + encodeURI(tTOTAL_DISTRICT_NAME) + "&TOTAL_STREET_NAME=" + encodeURI(tTOTAL_STREET_NAME),"Code","dialogWidth:950px;dialogHeight:600px;resizable=1,scrollbars=auto");
				}else if($("#TYPE_DATA").val()=='bldgrsdns'){
					var value = window.showModalDialog("${ctx}/address/b04r01addrview!viewlogsbyid.action?DIST_CD="+ tDIST_CD + "&RSDNBLDG_ID=" + tTYPE_ID + "&TOTAL_DISTRICT_NAME=" + encodeURI(tTOTAL_DISTRICT_NAME) + "&TOTAL_STREET_NAME=" + encodeURI(tTOTAL_STREET_NAME),"Code","dialogWidth:950px;dialogHeight:600px;resizable=1,scrollbars=auto");
				}else if($("#TYPE_DATA").val()=='organization'){
					var value = window.showModalDialog("${ctx}/address/b04r01addrview!viewlogsbyid.action?DIST_CD="+ tDIST_CD + "&ORG_ID=" + tTYPE_ID + "&TOTAL_DISTRICT_NAME=" + encodeURI(tTOTAL_DISTRICT_NAME) + "&TOTAL_STREET_NAME=" + encodeURI(tTOTAL_STREET_NAME),"Code","dialogWidth:950px;dialogHeight:600px;resizable=1,scrollbars=auto");
				}
			}
			function shTRUE(){
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
				var params = {
						DATA_FLAG:'2',
						TOTAL_ALL_VALUE:tTOTAL_ALL_VALUE
	            };
				if($("#TYPE_DATA").val()=='strt'){
					var url = '${ctx}/address/b04cud02addrconfig!shstreets.action';
				}else if($("#TYPE_DATA").val()=='bldgrsdns'){
					var url = '${ctx}/address/b04cud02addrconfig!shbldgrsdnss.action';
				}else if($("#TYPE_DATA").val()=='organization'){
					var url = '${ctx}/address/b04cud02addrconfig!shorganizations.action';
				}
				
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				jQuery.post(url, params, callajaxAOver, 'json');
			}
			function shFALSE(){
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
				var params = {
						DATA_FLAG:'9',
						TOTAL_ALL_VALUE:tTOTAL_ALL_VALUE
	            };
				if($("#TYPE_DATA").val()=='strt'){
					var url = '${ctx}/address/b04cud02addrconfig!shstreets.action';
				}else if($("#TYPE_DATA").val()=='bldgrsdns'){
					var url = '${ctx}/address/b04cud02addrconfig!shbldgrsdnss.action';
				}else if($("#TYPE_DATA").val()=='organization'){
					var url = '${ctx}/address/b04cud02addrconfig!shorganizations.action';
				}
				
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				jQuery.post(url, params, callajaxAOver, 'json');
			}
			function callajaxAOver(data){
				$('#queryAll').attr('checked',false);
				$.prompt(data.saveMessage,{buttons: {'确定': true}});
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
				ajaxQueryPage(0);
				
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
										投递区部基础数据 >>地址审核
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
													<select class="simple" id="COUNTY_NAMES" style="width:100" onChange="backfresh('fresh');"></select>
												</td>
												
											</tr>
											<tr>
												<td class="listtablehead">
													数据状态
												</td>
												<td class="js_left_txt">
													<s:select cssStyle="width:100"  onchange="ajaxQueryPage(0)" theme="simple" listKey="key" listValue="value" name="TYPE_FLAG" list="#{'0':'全部待审','6':'添加待审','7':'修改待审','8':'删除待审'}"></s:select>
												</td>
												<td class="listtablehead">
													数据类别:
												</td>
												<td class="js_left_txt">
													<s:select cssStyle="width:100"  onchange="ajaxQueryPage(0)"  theme="simple" listKey="key" listValue="value" name="TYPE_DATA" list="#{'strt':'街道','bldgrsdns':'小区','organization':'机构'}"></s:select>
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
													<a href='#' onClick="shTRUE();">审核通过</a>
													&nbsp;
													<a href='#' onClick="shFALSE();">审核拒绝</a>
													&nbsp;
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft" width="50">
													<input id="queryAll" name="chooseName" type="checkbox" onClick="queryAll()"/>
												</td>
												<td align="left" class="listtableheadleft" width="75">
													日志
												</td>
												<td align="left" class="listtableheadleft" width="75">
													数据状态
												</td>
												<td align="left" class="listtableheadleft" width="75">
													数据类别
												</td>
												<td align="left" class="listtableheadleft" width="500">
													名称
												</td>
												<td align="left" class="listtableheadleft1" width="200">
													备注
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
