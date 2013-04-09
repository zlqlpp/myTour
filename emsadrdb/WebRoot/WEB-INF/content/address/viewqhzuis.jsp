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
		<title>前后缀信息</title>
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
				if(!isMunicipalities(tPROVINCE_NAME)){
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
			    }else{
					var tCITY_ID=$("#CITY_NAMES").val();	
				}
				var tFIX=$("#FIX").val();
				var tFIX_FLAGNAME=$("input[name='FIX_FLAGNAME'][checked]").val(); 
				var url = '${ctx}/address/b04r02addrquery!queryqhzuis.action';
			    var params = {
								PROVINCE_ID:tPROVINCE_ID,
								CITY_ID:tCITY_ID,
								FIX:tFIX,
								FIX_FLAGNAME:tFIX_FLAGNAME,
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
						var uscitycode = '${EMS_USER.usCityOffice}';
						if('${EMS_USER.usCityOffice}' == ''){
							uscitycode = '${EMS_USER.usProvinceOffice}';
						}
						tr+="<td align=left class=listtablebodyleft2 width=100>&nbsp;";
						tr+="<a href='#' onClick='upQhzuis(" + resultdata[i].DIST_CD + "," + resultdata[i].QIANZUI_ID + ");'>修改</a> <a href='#' onClick='delQhzuis(" + resultdata[i].DIST_CD + "," +  resultdata[i].QIANZUI_ID + ");'>删除</a</td>";	
						tr+="<td align=left class=listtablebodyleft1  width=200>&nbsp;"+resultdata[i].TOTAL_DISTRICT_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=150>&nbsp;"+resultdata[i].FIX_FLAGNAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=150>&nbsp;"+resultdata[i].FIX+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=150>&nbsp;"+resultdata[i].FIX_ABBR+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=150>&nbsp;"+resultdata[i].FIX_SMP+"</td>";
						tr+="<td align=left class=listtablebodyleft2  width=150>&nbsp;"+resultdata[i].FIX_XZ+"</td>";
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
			function backfresh(value){
				if(value == "fresh") {
					ajaxQueryPage(0);
				}
			}
			function addQhzuis(){
				var value = window.showModalDialog("${ctx}/address/b04cud01addrview!addqhzuis.action?FLAG=ADD","Code","dialogWidth:600px;dialogHeight:600px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			function upQhzuis(tDIST_CD,tQIANZUI_ID){
				value = window.showModalDialog("${ctx}/address/b04cud01addrview!upqhzuis.action?FLAG=UPDATE&DIST_CD=" + tDIST_CD + "&QIANZUI_ID=" + tQIANZUI_ID,"Code","dialogWidth:600px;dialogHeight:600px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			
			function delQhzuis(tDIST_CD,tQIANZUI_ID){
				$.prompt('该操作将删除此前后缀名！确认执行操作？',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									DIST_CD:tDIST_CD,
									QIANZUI_ID:tQIANZUI_ID
							};
							var url = '${ctx}/address/b04cud02addrconfig!delqhzuis.action';
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
										投递区部基础数据 >>前后缀信息
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
													<select class="simple" id="CITY_NAMES" style="width:100"></select>
												</td>
												
											</tr>
											<tr>
												<td class="listtablehead">
													前后缀名:
												</td>
												<td class="js_left_txt" id="td_tpdistrict_content" colspan="3">
													<input type="text" name="FIX" style="width: 300px; height:20px;" id="FIX" class="ac_input" size="300"/>
												</td>
											</tr>
											<tr id="tr_ALL_DATA_FLAG">
												<td class="listtablehead">
													前后缀类型:
												</td>
												<td class="listtablehead" colspan="3">
													<input type="radio" name="FIX_FLAGNAME" value="" checked="checked">全部数据
													<input type="radio" name="FIX_FLAGNAME" value="0">前缀<label id="jddss" style="color:#FF0000"></label>
													<input type="radio" name="FIX_FLAGNAME" value="1">后缀<label id="xqdss" style="color:#FF0000"></label>
												</td>
											</tr>
										</table>
									</div>
									
									<br />
									<div id="tablelistdiv">
										<table class="listtable" width="900"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="4">
													前后缀信息
												</th>
												<th align="right" colspan="3">
													<a href='#' onClick="addQhzuis();">新增前后缀</a>
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft" width="100">
													操作
												</td>
												<td align="left" class="listtableheadleft" width="200">
													行政区
												</td>
												<td align="left" class="listtableheadleft" width="150">
													前后缀类型
												</td>
												<td align="left" class="listtableheadleft" width="150">
													前后缀名		
												</td>
												<td align="left" class="listtableheadleft" width="150">
													前后缀别名
												</td>
												<td align="left" class="listtableheadleft" width="150">
													前后缀简拼
												</td>
												<td align="left" class="listtableheadleft1" width="150">
													前后缀限制
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
