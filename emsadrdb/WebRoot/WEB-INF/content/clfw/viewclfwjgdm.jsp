<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/clfw/clfw.jsp"%>
<%@ include file="/scripts/My97DatePicker/My97DatePicker.jsp"%>
<html>

	<head>
		<title>机构代码频次配置查询</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				$("#th_addclfwresorgpz").hide();
				
				viewcity();

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
				if(!isMunicipalities(tPROVINCE_NAME)){
					$('#CITY_NAMES option').remove();
					for(var i=0;i<d.length;i++){
						$("#CITY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
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
					var tCITY_ID='';
			    }else{
					var tCITY_ID=$("#CITY_NAMES").val();
				}
				var tSEL_CLFWTDB_CLSX=$("input[name='SEL_CLFWTDB_CLSX'][checked]").val();
				var tRAD_CLFWPC_SX=$("input[name='RAD_CLFWPC_SX'][checked]").val();
				var tSEL_CLFWPCSHENG_SHENGM=$("#SEL_CLFWPCSHENG_SHENGM").val();
				var tSEL_CLFWPCSHI_SHIGM=$("#SEL_CLFWPCSHI_SHIGM").val();
				var tRAD_CLFWPC_QY=$("input[name='RAD_CLFWPC_QY'][checked]").val();
				var url = '${ctx}/clfw/b09r02clfwquery!queryclfwjgdm.action';
			    var params = {
			    				PROVINCE_ID:tPROVINCE_ID,
								CITY_ID:tCITY_ID,
			    				SEL_CLFWTDB_CLSX:tSEL_CLFWTDB_CLSX,
			    				RAD_CLFWPC_SX:tRAD_CLFWPC_SX,
					    		SEL_CLFWPCSHENG_SHENGM:tSEL_CLFWPCSHENG_SHENGM,
					    		SEL_CLFWPCSHI_SHIGM:tSEL_CLFWPCSHI_SHIGM,
					    		RAD_CLFWPC_QY:tRAD_CLFWPC_QY,
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
						var tr="<tr id='tr_query' )>";
						tr+="<td align=left class=listtablebodyleft1 style='width:120'>&nbsp;"+resultdata[i].CLFWRESORGPZ_DISTCDSTR+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:220'>&nbsp;"+resultdata[i].CLFWPCSHENG_SHENGM+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:160'>&nbsp;"+resultdata[i].CLFWPCSHI_SHIGM+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:700'>&nbsp;"+resultdata[i].YTDB.replace(new RegExp("red","gm"),"green")+"</td>";
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr style='display:none' ><td colspan=12 class=listtablebodyleft2>"+data.page.ajaxUrl+"</td></tr>";
					$('#address_table').append(url);
				}else{
					var url="<tr><td colspan=12 class=listtablebodyleft2>找到 0 条记录</td></tr>";
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
										核心服务>>机构代码频次配置查询
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
												<td class="js_left_txt" id="td_privince_content">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity();"></s:select>
													<select class="simple" id="CITY_NAMES" style="width:100"></select>
												</td>
												<td class="listtablehead" width="150">
													省处理中心频次名称:
												</td>
												<td class="js_left_txt" id="td_common_tempid">
													<input type="text" name="SEL_CLFWPCSHENG_SHENGM" style="width: 200px; height:20px;" id="SEL_CLFWPCSHENG_SHENGM" class="ac_input" size="200"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="150">
													市衔接频次名称:
												</td>
												<td class="js_left_txt" id="td_privince_content">
													<input type="text" name="SEL_CLFWPCSHI_SHIGM" style="width: 200px; height:20px;" id="SEL_CLFWPCSHI_SHIGM" class="ac_input" size="200"/>
												</td>
												<td class="listtablehead" width="150">
													频次属性：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value=""  checked="checked">全部
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value="2">揽收
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value="3">投递
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													统计属性：
												</td>
												<td class="js_left_txt"  colspan="3">
													<input type="radio" name="RAD_CLFWPC_QY" id="RAD_CLFWPC_QY" value="1" checked="checked">揽投部
													<input type="radio" name="RAD_CLFWPC_QY" id="RAD_CLFWPC_QY" value="0">支局
												</td>
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
														无帮助介绍
													</font>
												</td>
											</tr>
										</table>
									</div>
									<div id="tablelistdiv">
										<table class="listtable" width="1200"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="12">
													机构代码频次配置查询
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft" style='width:120'>
													行政区域
												</td>
												<td align="left" class="listtableheadleft" style='width:220'>
													省处理中心频次名称
												</td>
												<td align="left" class="listtableheadleft" style='width:160'>
													市运行频次名称
												</td>
												<td align="left" class="listtableheadleft1" style='width:700'>
													机构代码
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
