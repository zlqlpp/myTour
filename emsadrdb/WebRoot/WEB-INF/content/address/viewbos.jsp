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
		<title>小区/机构信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				$("#RSDNBLDG_NAME").val('${RSDNBLDG_NAME}');
				$("#ORG_NAME").val('${ORG_NAME}');
				$("#TOTAL_DIUMENPAI").val('${TOTAL_DIUMENPAI}');
				backfresh('fresh');
			})
			function ajaxQueryPage(pageNo){
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var url = '${ctx}/address/b04r02addrquery!querybos.action';
				var tTOTAL_DIUMENPAI=$("#TOTAL_DIUMENPAI").val();
				var tORG_NAME=$("#ORG_NAME").val();
				var tRSDNBLDG_NAME=$("#RSDNBLDG_NAME").val();
			    var params = {
							  ALL_DATA_FLAG:'${ALL_DATA_FLAG}',
							  ORG_NAME:tORG_NAME,
							  RSDNBLDG_NAME:tRSDNBLDG_NAME,
							  TOTAL_DIUMENPAI:tTOTAL_DIUMENPAI,
			                  DIST_CD:'${DIST_CD}',
			                  STRT_ID:'${STRT_ID}',
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
						if(getRulUsProvinceOfficeTF('${EMS_USER.rulLevel}','${EMS_USER.usCityOffice}',resultdata[i].DIST_CD)){
							if(resultdata[i].BO_FLAG=='X'){
							tr+="<td align=left class=listtablebodyleft1 width=150>";
							tr+=" <a href='#' onClick='xxBldgrsdns("+ resultdata[i].DIST_CD + "," + resultdata[i].RSDNBLDG_ID + ");'>详细</a>  <a href='#' onClick='viewLogsbyidx(" + resultdata[i].DIST_CD + "," + resultdata[i].RSDNBLDG_ID + ");'>日志</a> <a href='#' onClick='upBldgrsdns("+ resultdata[i].DIST_CD + "," + resultdata[i].RSDNBLDG_ID + ");'>修改</a> <a href='#' onClick='delBldgrsdns(" + resultdata[i].STRT_ID + "," + resultdata[i].DIST_CD + "," + resultdata[i].RSDNBLDG_ID + "," + '"' + resultdata[i].RSDNBLDG_NAME + '"' + ");'>删除</a>";
							if(getUsWebRule('${EMS_USER.rulLevel}') && getUsWebAudit(resultdata[i].DATA_FLAG)){
								tr+=" <a href='#' onClick='shBldgrsdns("+ resultdata[i].DIST_CD + "," + resultdata[i].RSDNBLDG_ID + ");'>审核</a>";
							}
							tr+=" </td>";
							}
							else if(resultdata[i].BO_FLAG=='J'){
							tr+="<td align=left class=listtablebodyleft1 width=150>";
							tr+=" <a href='#' onClick='xxOrganization("+ resultdata[i].DIST_CD + "," + resultdata[i].ORG_ID + ");'>详细</a>  <a href='#' onClick='viewLogsbyidj(" + resultdata[i].DIST_CD + "," + resultdata[i].ORG_ID + ");'>日志</a> <a href='#' onClick='upOrganization("+ resultdata[i].DIST_CD + "," + resultdata[i].ORG_ID + ");'>修改</a> <a href='#' onClick='delOrganization(" + resultdata[i].STRT_ID + "," +  resultdata[i].DIST_CD + "," + resultdata[i].ORG_ID + "," +  '"' + resultdata[i].ORG_NAME + '"' + ");'>删除</a>";
							if(getUsWebRule('${EMS_USER.rulLevel}') && getUsWebAudit(resultdata[i].DATA_FLAG)){
								tr+=" <a href='#' onClick='shOrganization("+ resultdata[i].DIST_CD + "," + resultdata[i].ORG_ID + ");'>审核</a>";
							}
							tr+=" </td>";
							}
							else{
								tr+="<td align=left class=listtablebodyleft1 width=150>&nbsp;</td>";
							}
						}else{
							tr+="<td align=left class=listtablebodyleft1 width=150>&nbsp;</td>";
						}
						tr+="<td align=left class=listtablebodyleft1 width=75>&nbsp;"+resultdata[i].DATA_FLAG_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 width=100>&nbsp;"+resultdata[i].TOTAL_BLDG_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 width=100>&nbsp;"+resultdata[i].RSDNBLDG_FLAG_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 width=200>&nbsp;"+resultdata[i].RSDNBLDG_NAME+"</td>";			
						tr+="<td align=left class=listtablebodyleft2 width=200>&nbsp;"+resultdata[i].ORG_NAME+"</td>";				
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
			function shBldgrsdns(tDIST_CD,tRSDNBLDG_ID){
				value = window.showModalDialog("${ctx}/address/b04cud01addrview!shbldgrsdns.action?FLAG=SH&DIST_CD=" + tDIST_CD + "&RSDNBLDG_ID=" + tRSDNBLDG_ID+ encodeURI("&TOTAL_DISTRICT_NAME=${TOTAL_DISTRICT_NAME}"),"Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			function xxBldgrsdns(tDIST_CD,tRSDNBLDG_ID){
				value = window.showModalDialog("${ctx}/address/b04r01addrview!xxbldgrsdns.action?FLAG=XX&DIST_CD=" + tDIST_CD + "&RSDNBLDG_ID=" + tRSDNBLDG_ID+ encodeURI("&TOTAL_DISTRICT_NAME=${TOTAL_DISTRICT_NAME}"),"Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");
			}
			function upBldgrsdns(tDIST_CD,tRSDNBLDG_ID){
				value = window.showModalDialog("${ctx}/address/b04cud01addrview!upbldgrsdns.action?FLAG=UPDATE&DIST_CD=" + tDIST_CD + "&RSDNBLDG_ID=" + tRSDNBLDG_ID + encodeURI("&TOTAL_DISTRICT_NAME=${TOTAL_DISTRICT_NAME}&TOTAL_STREET_NAME=${TOTAL_STREET_NAME}"),"Code","dialogWidth:600px;dialogHeight:600px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			function delBldgrsdns(tSTRT_ID,tDIST_CD,tRSDNBLDG_ID,tRSDNBLDG_NAME){
				var txt = '该操作将删除此小区！确认执行操作？' +
							'<br>备注：<input type="text" id="NOTE" name="NOTE"/>';
				$.prompt(txt,{
					buttons: {'确定': true, '取消': false },
					callback: function(v,m){
						var tNOTE= m.find('#NOTE').val();
						if(v){
							var params = {
									NOTE:tNOTE,
									STRT_ID:tSTRT_ID,
									DIST_CD:tDIST_CD,
									RSDNBLDG_ID:tRSDNBLDG_ID,
									RSDNBLDG_NAME:tRSDNBLDG_NAME,
									TOTAL_STREET_NAME:'${TOTAL_STREET_NAME}'
							};
							var url = '${ctx}/address/b04cud02addrconfig!delbldgrsdns.action';
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
			function backQuery(flag){
				window.returnValue=flag;
				window.opener =null;
				window.close();
			}
			function addBldgrsdns(){
				var value = window.showModalDialog("${ctx}/address/b04cud01addrview!addbldgrsdns.action?FLAG=ADD&DIST_CD=${DIST_CD}&STRT_ID=${STRT_ID}" + encodeURI("&TOTAL_DISTRICT_NAME=${TOTAL_DISTRICT_NAME}&TOTAL_STREET_NAME=${TOTAL_STREET_NAME}"),"Code","dialogWidth:600px;dialogHeight:600px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			
			function viewLogsbyidx(tDIST_CD,tRSDNBLDG_ID){
				var value = window.showModalDialog("${ctx}/address/b04r01addrview!viewlogsbyid.action?DIST_CD="+ tDIST_CD + "&RSDNBLDG_ID=" + tRSDNBLDG_ID  + encodeURI("&TOTAL_DISTRICT_NAME=${TOTAL_DISTRICT_NAME}&TOTAL_STREET_NAME=${TOTAL_STREET_NAME}"),"Code","dialogWidth:950px;dialogHeight:600px;resizable=1,scrollbars=auto");
			}
			function shOrganization(tDIST_CD,tORG_ID){
				value = window.showModalDialog("${ctx}/address/b04cud01addrview!shorganization.action?FLAG=SH&DIST_CD=" + tDIST_CD + "&ORG_ID=" + tORG_ID+ encodeURI("&TOTAL_DISTRICT_NAME=${TOTAL_DISTRICT_NAME}"),"Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			function xxOrganization(tDIST_CD,tORG_ID){
				value = window.showModalDialog("${ctx}/address/b04r01addrview!xxorganization.action?FLAG=XX&DIST_CD=" + tDIST_CD + "&ORG_ID=" + tORG_ID+ encodeURI("&TOTAL_DISTRICT_NAME=${TOTAL_DISTRICT_NAME}"),"Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");
			}
			function upOrganization(tDIST_CD,tORG_ID){
				value = window.showModalDialog("${ctx}/address/b04cud01addrview!uporganization.action?FLAG=UPDATE&DIST_CD=" + tDIST_CD + "&ORG_ID=" + tORG_ID  + encodeURI("&TOTAL_DISTRICT_NAME=${TOTAL_DISTRICT_NAME}&TOTAL_STREET_NAME=${TOTAL_STREET_NAME}"),"Code","dialogWidth:600px;dialogHeight:600px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			function delOrganization(tSTRT_ID,tDIST_CD,tORG_ID,tORG_NAME){
				var txt = '该操作将删除此机构！确认执行操作？' +
							'<br>备注：<input type="text" id="NOTE" name="NOTE"/>';
				$.prompt(txt,{
					buttons: {'确定': true, '取消': false },
					callback: function(v,m){
						var tNOTE= m.find('#NOTE').val();
						if(v){
							var params = {
									NOTE:tNOTE,
									STRT_ID:tSTRT_ID,
									DIST_CD:tDIST_CD,
									ORG_ID:tORG_ID,
									ORG_NAME:tORG_NAME,
									TOTAL_STREET_NAME:'${TOTAL_STREET_NAME}'
							};
							var url = '${ctx}/address/b04cud02addrconfig!delorganization.action';
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
			function addOrganization(){
				var value = window.showModalDialog("${ctx}/address/b04cud01addrview!addorganization.action?FLAG=ADD&DIST_CD=${DIST_CD}&STRT_ID=${STRT_ID}&DORPLT_ID=${DORPLT_ID}" + encodeURI("&TOTAL_DISTRICT_NAME=${TOTAL_DISTRICT_NAME}&TOTAL_STREET_NAME=${TOTAL_STREET_NAME}"),"Code","dialogWidth:600px;dialogHeight:600px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			function viewLogsbyidj(tDIST_CD,tORG_ID){
				var value = window.showModalDialog("${ctx}/address/b04r01addrview!viewlogsbyid.action?DIST_CD="+ tDIST_CD + "&ORG_ID=" + tORG_ID  + encodeURI("&TOTAL_DISTRICT_NAME=${TOTAL_DISTRICT_NAME}&TOTAL_STREET_NAME=${TOTAL_STREET_NAME}"),"Code","dialogWidth:950px;dialogHeight:600px;resizable=1,scrollbars=auto");
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
										小区/机构查询
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<a id="query" href='#' onClick="ajaxQueryPage(0)" style="font-size:12px">查询</a>
								<a id="queryclear" href='#' style="font-size:12px">重置</a>
								<a id="queryback" href='#' onClick="backQuery('nofresh')" style="font-size:12px">返回</a>
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
												<td class="listtablehead" colspan="4">
													&nbsp;${TOTAL_DISTRICT_NAME}${TOTAL_STREET_NAME}
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													门牌名称:
												</td>
												<td class="js_left_txt" id="td_tpdistrict_content" colspan="3">
													<input type="text" name="TOTAL_DIUMENPAI" style="width: 300px; height:20px;" id="TOTAL_DIUMENPAI" class="ac_input" size="300"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													小区名称:
												</td>
												<td class="js_left_txt" id="td_tpdistrict_content">
													<input type="text" name="RSDNBLDG_NAME" style="width: 300px; height:20px;" id="RSDNBLDG_NAME" class="ac_input" size="300"/>
												</td>
												<td class="listtablehead">
													机构名称:
												</td>
												<td class="js_left_txt" id="td_tpdistrict_content">
													<input type="text" name="ORG_NAME" style="width: 300px; height:20px;" id="ORG_NAME" class="ac_input" size="300"/>
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
													小区/机构信息
												</th>
												<th align="right" colspan="5">
													<a href='#' onClick="addBldgrsdns();">新增小区</a>
													<a href='#' onClick="addOrganization();">新增机构</a>
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft" width="150">
													操作
												</td>
												<td align="left" class="listtableheadleft" width="75">
													数据状态
												</td>
												<td align="left" class="listtableheadleft" width="100">
													门牌名称
												</td>
												<td align="left" class="listtableheadleft" width="100">
													类别
												</td>
												<td align="left" class="listtableheadleft" width="200">
													小区名称
												</td>
												<td align="left" class="listtableheadleft1" width="200">
													机构名称
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
<div id="nodata" style="display:none">
<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />
</div>
<div id="yesdata">
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
