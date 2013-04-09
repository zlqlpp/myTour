<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/jjsx/jjsx.jsp"%>
<%@ include file="/scripts/My97DatePicker/My97DatePicker.jsp"%>
<html>

	<head>
		<title>经济频次到专业机构查询</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				
				viewcity();
				
				$("#fliphelpct").click(function(){
					$("#fliphelpcon").slideToggle("fast");
				});

				$("#fliphelpcon").hide();
				
			})
			//展示市
			function viewcity(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var url = '${ctx}/jjsx/b10r03jjsxoption!citysyt.action';
				var params = {
					PROVINCE_NAME: tPROVINCE_NAME
				 };
				jQuery.post(url, params, callbackcity, 'json');
			}
			function callbackcity(data){
				var d=data.districtBeans;
				$('#CITY_NAMES option').remove();
				for(var i=0;i<d.length;i++){
					$("#CITY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
				}
				viewcounty();
			}
			function viewcounty(){
				var tCITY_NAME=$("#CITY_NAMES").find("option:selected").text(); 
				var tCITY_CODE=$("#CITY_NAMES").val(); 
				if(tCITY_NAME.length>0){
					var url = '${ctx}/jjsx/b10r03jjsxoption!countysyt.action';
					var params = {
						CITY_CODE: tCITY_CODE,
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
				removecounty();
			}
			function removecounty(){
				$('#address_table tbody').remove();
			}

			function ajaxQueryPage(pageNo){
				
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}

				var tCOUNTY_ID=$("#COUNTY_NAMES").val();

				var tRANGE=$("#RANGE").val();

				var tPCMC=$("#PCMC").val();
				var tPCSX=$("input[name='PCSX'][checked]").val();

				var tCQUSXXUS=$("input[name='CQUSXXUS'][checked]").val();
				
				var tHXFLAG=$("input[name='SELHXFLAG'][checked]").val();
				
				var tORG_CODE=$("#ORG_CODE").val();
				
				var url = '${ctx}/jjsx/b10r02jjsxquery!queryjjsxsqxzyzjpccx.action';
			    var params = {
								COUNTY_ID:tCOUNTY_ID,
								ORG_CODE:tORG_CODE,
								RANGE:tRANGE,
								PCMC:tPCMC,
					    		PCSX:tPCSX,
								CQUSXXUS:tCQUSXXUS,
								HXFLAG:tHXFLAG,
			                  	pageNo:pageNo
			                 };
			    jQuery.post(url, params, callbackajaxQueryPage, 'json');
			}
			function callbackajaxQueryPage(data){
				addquerytable(data);
			}
			function addquerytable(data){
				var resultdata=data.page.result;
				$('#address_table tbody').remove();
				if(resultdata.length>0){
					for(var i=0;i<resultdata.length;i++){
						var tr="<tr id='tr_query' >";
						tr+="<td align=left class=listtablebodyleft1 style='width:120px'>&nbsp;"+resultdata[i].DISTNAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:200px'>&nbsp;"+resultdata[i].ALLPCMC+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:200px'>&nbsp;"+resultdata[i].ORG_CNAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].ORG_CODE+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:100px'>&nbsp;"+resultdata[i].PCJZSJ+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:100px'>&nbsp;"+resultdata[i].PCSJJG+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:100px'>&nbsp;"+resultdata[i].README+"</td>";
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr><td colspan=20 class=listtablebodyleft2>"+data.page.ajaxUrl+"</td></tr>";
					$('#address_table').append(url);
				}else{
					var url="<tr><td colspan=20 class=listtablebodyleft2>找到 0 条记录</td></tr>";
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
										经济时限服务>>经济频次到专业机构配置查询
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
												<td class="listtablehead" width="180">
													行政区域:
												</td>
												<td class="js_left_txt" id="td_privince_content"  colspan="3">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty();" style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES" style="width:100" onChange="removecounty();"></select>
													<input type="hidden" name="RANGE" id="RANGE" class="ac_input" value='1'/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="100">
													频次名称:
												</td>
												<td class="js_left_txt" id="td_common_tempid">
													<input type="text" name="PCMC" style="width: 200px; height:20px;" id="PCMC" class="ac_input" size="200"/>
												</td>
												<td class="listtablehead">
													频次类别：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="PCSX" id="PCSX" value=""  checked="checked">全部
													<input type="radio" name="PCSX" id="PCSX" value="0">揽收
													<input type="radio" name="PCSX" id="PCSX" value="1" >投递
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													频次范围：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="CQUSXXUS" id="CQUSXXUS" value=""  checked="checked">全部
													<input type="radio" name="CQUSXXUS" id="CQUSXXUS" value="1">城区
													<input type="radio" name="CQUSXXUS" id="CQUSXXUS" value="0" >辖县
												</td>
												<td class="listtablehead">
													核心/非核心：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="SELHXFLAG" id="SELHXFLAG" value=""  checked="checked">全部
													<input type="radio" name="SELHXFLAG" id="SELHXFLAG" value="1">核心
													<input type="radio" name="SELHXFLAG" id="SELHXFLAG" value="0" >非核心
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="180">
													机构代码:
												</td>
												<td class="js_left_txt" id="td_privince_content"  colspan="3">
													<input type="text" name="ORG_CODE" style="width: 600px; height:20px;" id="ORG_CODE" class="ac_input" size="600"/>
												</td>
											</tr>
											<tr>
											<tr>
												
											</tr>
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
														*无帮助信息
													</font>
												</td>
											</tr>
										</table>
									</div>
									<div id="tablelistdiv">
										<table class="listtable" width="100%"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="12">
													经济频次到机构代码配置查询
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft" style='width:120px'>
													行政区域
												</td>
												<td align="left" class="listtableheadleft" style='width:200px'>
													频次名称
												</td>
												<td align="left" class="listtableheadleft" style='width:200px'>
													机构名称
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													机构代码
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													截止时间
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													间隔日期
												</td>
												<td align="left" class="listtableheadleft1" style='width:100px'>
													说明
												</td>
											</thead>
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
