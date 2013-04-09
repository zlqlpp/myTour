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
		<title>经济频次专业机构配置统计</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){

				if('${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2'){
					$("#PROVINCE_NAMES").prepend("<option selected='selected' value=''>全国</option>"); 
					$("#PROVINCE_NAMES").val('');
					//$("#queryexport").show();
				}else{
					//$("#queryexport").show();
				}
				
				viewcity();
				
				$("#fliphelpct").click(function(){
					$("#fliphelpcon").slideToggle("fast");
				});
				
			})
			//展示市
			function viewcity(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				if(tPROVINCE_NAME != '全国'){
					var url = '${ctx}/jjsx/b10r03jjsxoption!citysyt.action';
					var params = {
						PROVINCE_NAME: tPROVINCE_NAME
					 };
					jQuery.post(url, params, callbackcity, 'json');
				}else{
					$("#CITY_NAMES").prepend("<option selected='selected' value=''>全国</option>"); 
				}
			}
			function callbackcity(data){
				var d=data.districtBeans;
				$('#CITY_NAMES option').remove();
				for(var i=0;i<d.length;i++){
					$("#CITY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
				}
				//$("#CITY_NAMES").prepend("<option selected='selected' value=''>请选择</option>");
			}
			

			function ajaxQueryPage(pageNo){

				$('#queryAll').attr('checked',false);
				
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				
				var tCITY_ID=$("#CITY_NAMES").val();

				var tCITY_NAME=$("#CITY_NAMES").find("option:selected").text(); 

				var tRANGE=$("#RANGE").val();
				
				var url = '${ctx}/jjsx/b10r02jjsxquery!queryjjsxqxzypctj.action';
			    var params = {
			    				CITY_ID: tCITY_ID,
								CITY_NAME: tCITY_NAME,
								RANGE:tRANGE,
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
				
				var tFLAGPZ=$("input[name='FLAGPZ'][checked]").val();

				if(resultdata.length>0){
					for(var i=0;i<resultdata.length;i++){
						var tr="<tr>";
						tr+="<td align=left class=listtablebodyleft1 style='width:200px'>&nbsp;"+resultdata[i].DISTNAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:200px'>&nbsp;"+resultdata[i].ALLPCMC+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].ALLCOUNT+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:150px'>&nbsp;"+resultdata[i].HXCOUNT+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:150px'>&nbsp;"+resultdata[i].HXFCOUNT+"</td>";
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
			function ajaxQueryPageexport(pageNo){
				var myDate=new Date();
				
				var mysj=myDate.getFullYear() + "-" + (myDate.getMonth()+1) + "-" + myDate.getDate()+ " " + myDate.getHours() + ":" + myDate.getMinutes() + ":" + myDate.getSeconds();

				$("#cxsjs").text('导出开始时间: ' + mysj);
				
				$("#exporttxt").text("数据导出中...");
				
				var tCITY_ID=$("#CITY_NAMES").val();

				var tCITY_NAME=$("#CITY_NAMES").find("option:selected").text(); 

				var tRANGE=$("#RANGE").val();
				
				var tEXPORT_XXSTR='经济频次专业机构配置统计';
				
				var LINENAMESTR = '城市,频次名称,机构总数,城区或核心机构总数,辖县/非核心机构总数';

				var url = '${ctx}/jjsx/b10r02jjsxquery!queryexportall.action';
	
				var params = {
							  EXREPORT:"经济频次专业机构配置统计#城市:" + tCITY_NAME,
							  EXDATA:tCITY_ID + " , " + tCITY_NAME + " , " + tRANGE,//逗号间隔需要加空格
							  LINEKEY:"expjjsxqxpctj",
							  LINEMAIN:"expjjsxqxzypctj,数据表,经济频次专业机构配置统计",
							  LINENAME:LINENAMESTR,
							  pageNo:pageNo
							 };
				 
				jQuery.post(url, params, callbackajaxQueryPageexport, 'json');
			}
			function callbackajaxQueryPageexport(data){
				//添加table
				addquerytableexport(data);
			}
			function addquerytableexport(data){
				var myDate=new Date();
				var mysj=myDate.getFullYear() + "-" + (myDate.getMonth()+1) + "-" + myDate.getDate()+ " " + myDate.getHours() + ":" + myDate.getMinutes() + ":" + myDate.getSeconds();
				$("#cxsje").text('   导出结束时间: ' + mysj);
				
				if(data.saveMessage!=null){
					$("#exporttxt").html(data.saveMessage);
				}else{
					$("#exporttxt").html("   <a href='" + data.EXPORTALLPATH + "'>数据导出完毕,请点击下载!<a/>");
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
										经济时限服务>>经济频次专业机构配置统计
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
												<td class="js_left_txt" id="td_privince_content">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" style="width:100"></select>
													<input type="hidden" name="RANGE" id="RANGE" class="ac_input" value='1'/>
												</td>
											</tr>
											<tr  id="tr_exptjclfwhbwhalmx">
												<td colspan="4" class="listtablehead">
												<label id="cxsjs" style="color:#FF0000"></label>
												<label id="cxsje" style="color:#FF0000"></label>
												<label id="exporttxt" style="color:#FF0000"></label>
												</td>
											</tr>
											<tr>
												<td class="listtablehead"  colspan="4" align="right">
													<input id="query" type="button" value="查询" onClick="ajaxQueryPage(0)" />
													<input id="queryclear" type="button" value="重置" onClick="ajaxQueryPage(0)" />
													<input id="queryexport" onClick="ajaxQueryPageexport(0)" type="button" value="导出">
													<input id="fliphelpct" type="button" value="帮助"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead"  colspan="4">
													<font id="fliphelpcon" color="red">
														*无帮助介绍
													</font>
												</td>
											</tr>
										</table>
									</div>
									<div id="tablelistdiv">
										<table class="listtable" width="100%"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="20">
													经济频次专业机构配置统计
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft" style='width:200px'>
													城市
												</td>
												<td align="left" class="listtableheadleft" style='width:200px'>
													频次名称
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													机构总数
												</td>
												<td align="left" class="listtableheadleft" style='width:150px'>
													城区/核心机构总数
												</td>
												<td align="left" class="listtableheadleft1" style='width:150px'>
													辖县/非核心机构总数
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
