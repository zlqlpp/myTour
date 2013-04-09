<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/disp/disp.jsp"%>
<html>

	<head>
		<title>经转关系信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				backfresh('fresh');
			})
			
			function ajaxQueryPage(pageNo){
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tDISP_OFFICE_NAME=$("#DISP_OFFICE_NAME").val();
				var tTRANS_NAME=$("#TRANS_NAME").val();
				var url = '${ctx}/disp/b07r02dispquery!querydisps.action';
			    var params = {
								PROVINCE_NAME:tPROVINCE_NAME,
								DISP_OFFICE_NAME:tDISP_OFFICE_NAME,
								TRANS_NAME:tTRANS_NAME,
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

						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].PROVINCE_NAME+"</td>";
						
						tr+="<td valign=top align=left class=listtablebodyleft1  width=200>&nbsp;"+resultdata[i].DISP_OFFICE_NAME;
						//if(getUsWebRuleGJ('${EMS_USER.rulLevel}')){
						tr+=" <a href='#' onClick='delDisp(" + '"' + resultdata[i].DISP_OFFICE_CODE + '"' + ");'>撤销</a>";
						//}
						tr+=" <a href='#' onClick='upDisp(" + '"' + resultdata[i].DISP_OFFICE_CODE + '"' + ");'>修改</a>  <a href='#' onClick='zhDisp(" + '"' + resultdata[i].DISP_OFFICE_CODE + '"' + ',"' + resultdata[i].DISP_OFFICE_NAME + '"' + ");'>调整</a> <a href='#' onClick='tdDisp(" + '"' + resultdata[i].DISP_OFFICE_CODE + '"' + ',"' + resultdata[i].DISP_OFFICE_NAME + '"' + ");'>投递区</a></td>";
						
					//<a href='#' onClick='delDisp(" + '"' + resultdata[i].DISP_OFFICE_CODE + '"' + ");'>撤销</a>
						
						tr+="<td align=left class=listtablebodyleft2  width=500>";
						
						tr+=ftotaltransname(resultdata[i].TOTAL_WH,resultdata[i].TOTAL_TRANS_NAME,resultdata[i].DISP_OFFICE_CODE,resultdata[i].PROVINCE_NAME,resultdata[i].TOTAL_TRANS_CHSNAME,resultdata[i].TOTAL_TRANS_CITY_CHSNAME);
						
						tr+="&nbsp</td>";
						
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr><td colspan=5 class=listtablebodyleft2>"+data.page.ajaxUrl+"</td></tr>";
					$('#address_table').append(url);
					
				}else{
					var url="<tr><td colspan=5 class=listtablebodyleft2>找到 0 条记录</td></tr>";
					$('#address_table').append(url);
				}
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
			}
			function ftotaltransname(tTOTAL_WH,tTOTAL_TRANS_NAME,tDISP_OFFICE_CODE,tDISP_OFFICE_NAME,tTOTAL_TRANS_CHSNAME,tTOTAL_TRANS_CITY_CHSNAME){
				var tts = "";
				tts += "<a href='#' onClick='addTrans(" + '"' + tDISP_OFFICE_CODE + '"' + ")'>添加</a>   <a href='#' onClick='viewTrans(" + '"' + tDISP_OFFICE_CODE + '"' + ',"' + tDISP_OFFICE_NAME + '"' + ")'>详细</a>";
				
				if(tTOTAL_TRANS_NAME.length>1){
					if(tTOTAL_WH == null || tTOTAL_WH.indexOf('|')<1){
						tTOTAL_WH = '没有在行政批区中设置投递区或者有多个投递区|null';
					}else{
						tTOTAL_WH += ',没有在行政批区中设置投递区或者有多个投递区|null';
					}
					if(tTOTAL_TRANS_CITY_CHSNAME == null || tTOTAL_TRANS_CITY_CHSNAME.indexOf('|')<1){
						tTOTAL_TRANS_CITY_CHSNAME = '本市|000000';
					}else{
						tTOTAL_TRANS_CITY_CHSNAME = '本市|000000,' + tTOTAL_TRANS_CITY_CHSNAME;
					}
					var wstrs = tTOTAL_WH.split(',');
					var tstrs = tTOTAL_TRANS_NAME.split(',');
					var tstrschs = tTOTAL_TRANS_CHSNAME.split(',');
					var tstrschscity = tTOTAL_TRANS_CITY_CHSNAME.split(',');
					for(j=0;j<wstrs.length;j++){
						var ttsf = "";
						var ttss = "";
						var ttsx = "";
						var brnum =3;
						var ws =0;
						var sf =0;
						var ss =0;
						var sx =0;
						var schs="";
						for(n=0;n<tstrschscity.length;n++){	
							for(i=0;i<tstrs.length;i++){
								if((tstrs[i].split('|')[2] == '1' && tstrschscity[n].split('|')[1] == '000000')
									|| (tstrs[i].split('|')[1].substr(0,4) == tstrschscity[n].split('|')[1].substr(0,4))){
									schs = tstrs[i].split('|')[0];
									if(tstrs[i].split('|')[3].length<1){
										tstrs[i] = tstrs[i] + "null";
									}
									if(tstrs[i].split('|')[3] == wstrs[j].split('|')[1]){
										ws = 1;
										if(tstrs[i].split('|')[2] == '3'){
											for(m=0;m<tstrschs.length;m++){
												if(tstrschs[m].split('|')[1] == tstrs[i].split('|')[1]){
													schs = tstrschs[m].split('|')[0];
													break;
												}
											}
											if(getUsWebRuleGJ('${EMS_USER.rulLevel}')){
											ttsx += "**" + schs +  "<a style='margin-right:10px' href='#' onClick='zhTrans(" + '"' + tstrs[i].split('|')[1] + '"' + ',"' + schs + '"'  + ',"' + tDISP_OFFICE_CODE + '"' + ");'>↔</a>";
											}else{
											ttsx += "**" + schs +  "<a style='margin-right:10px' href='#' );'>&nbsp;</a>";
											}
											sx = sx + 1;
											if(sx%brnum == 0){
											ttsx += "<br>";
											}
										}
										else if(tstrs[i].split('|')[2] == '2'){
											for(m=0;m<tstrschs.length;m++){
												if(tstrschs[m].split('|')[1] == tstrs[i].split('|')[1]){
													schs = tstrschs[m].split('|')[0];
													break;
												}
											}
											ttss += "*" + schs +  "<a style='margin-right:10px' href='#' onClick='zhTrans(" + '"' + tstrs[i].split('|')[1] + '"' + ',"' + schs + '"' + ',"' + tDISP_OFFICE_CODE + '"' + ");'>↔</a>";
											ss = ss + 1;
											if(ss%brnum == 0){
											ttss += "<br>";
											}
										}else if(tstrs[i].split('|')[2] == '1'){
											ttsf += tstrs[i].split('|')[0] +  "<a style='margin-right:10px' href='#' onClick='zhTrans(" + '"' + tstrs[i].split('|')[1] + '"' + ',"' + tstrs[i].split('|')[0] + '"' + ',"' + tDISP_OFFICE_CODE + '"' + ");'>↔</a>";
											sf = sf + 1;
											if(sf%brnum == 0){
											ttsf += "<br>";
											}
										}	
									}
								}
							}
						}
						if(ws>0){
							tts = tts + "<br><font color='red'>" + wstrs[j].split('|')[0] + "</font>" ;
							if(sf>0){
								tts = tts + "<br>" + ttsf;
							}
							if(ss>0){
								tts = tts + "<br>" + ttss;
							}
							if(sx>0){
								tts = tts + "<br>" + ttsx;
							}
						}
					}
					
				}
				return tts;
			}
			function backfresh(value){
				if(value == "fresh") {
					ajaxQueryPage(0);
				}
			}
			function addDisp(){
				var value = window.showModalDialog("${ctx}/disp/b07cud01dispview!adddisp.action?FLAG=ADD","Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			function upDisp(tDISP_OFFICE_CODE){
				var value = window.showModalDialog("${ctx}/disp/b07cud01dispview!updisp.action?FLAG=UPDATE&DISP_OFFICE_CODE=" + tDISP_OFFICE_CODE,"Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			function zhDisp(tDISP_OFFICE_CODE,tDISP_OFFICE_NAME){
				var value = window.showModalDialog("${ctx}/disp/b07cud01dispview!zhdisp.action?FLAG=ZHUAN&DISP_OFFICE_CODE=" + tDISP_OFFICE_CODE + "&DISP_OFFICE_NAME=" + encodeURI(tDISP_OFFICE_NAME),"Code","dialogWidth:600px;dialogHeight:200px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			function tdDisp(tDISP_OFFICE_CODE,tDISP_OFFICE_NAME){
				var value = window.showModalDialog("${ctx}/disp/b07r01dispview!viewtddisp.action?FLAG=TDQ&DISP_OFFICE_CODE=" + tDISP_OFFICE_CODE + "&DISP_OFFICE_NAME=" + encodeURI(tDISP_OFFICE_NAME),"Code","dialogWidth:300px;dialogHeight:400px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			function addTrans(tDISP_OFFICE_CODE){
				var value = window.showModalDialog("${ctx}/disp/b07cud01dispview!addtrans.action?FLAG=ADD&DISP_OFFICE_CODE=" + tDISP_OFFICE_CODE,"Code","dialogWidth:600px;dialogHeight:200px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			function delDisp(tDISP_OFFICE_CODE){
				$.prompt('该操作将撤销经转局！确认执行操作？',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
								DISP_OFFICE_CODE:tDISP_OFFICE_CODE
							};
							var url = '${ctx}/disp/b07cud02dispconfig!deldisp.action';
							if(!(bro.msie && bro.version >=8)){
								$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
							}else{
								$("#nodata").show();
								$("#yesdata").hide();
							}
							jQuery.post(url, params, callajaxdelTran, 'json');
						}
					}
				});
			}
			function delTrans(tTRANS_CODE){
				$.prompt('该操作将撤销经转范围！确认执行操作？',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
								TRANS_CODE:tTRANS_CODE
							};
							var url = '${ctx}/disp/b07cud02dispconfig!deltrans.action';
							if(!(bro.msie && bro.version >=8)){
								$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
							}else{
								$("#nodata").show();
								$("#yesdata").hide();
							}
							jQuery.post(url, params, callajaxdelTran, 'json');
						}
					}
				});
			}
			function zhTrans(tTRANS_CODE,tTRANS_NAME,tDISP_OFFICE_CODE){
				var value = window.showModalDialog("${ctx}/disp/b07cud01dispview!zhtrans.action?FLAG=ZHUAN&TRANS_CODE=" + tTRANS_CODE +"&TRANS_NAME=" + encodeURI(tTRANS_NAME) + "&DISP_OFFICE_CODE=" + tDISP_OFFICE_CODE,"Code","dialogWidth:600px;dialogHeight:200px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			function viewTrans(tDISP_OFFICE_CODE,tDISP_OFFICE_NAME){
				var value = window.showModalDialog("${ctx}/disp/b07r01dispview!viewtrans.action?DISP_OFFICE_CODE="+ tDISP_OFFICE_CODE + "&DISP_OFFICE_NAME=" + encodeURI(tDISP_OFFICE_NAME) + "&TRANS_NAME=" + encodeURI($("#TRANS_NAME").val()),"Code","dialogWidth:950px;dialogHeight:600px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
			function callajaxdelTran(data){
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
										投递区部基础数据 >>经转维护
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
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onChange="backfresh('fresh');"></s:select>
												</td>
												
											</tr>
											<tr>
												<td class="listtablehead">
													经转局:
												</td>
												<td class="js_left_txt" id="td_tpdistrict_content" colspan="3">
													<input type="text" name="DISP_OFFICE_NAME" style="width: 300px; height:20px;" id="DISP_OFFICE_NAME" class="ac_input" size="300"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													经转范围:
												</td>
												<td class="js_left_txt" id="td_city_content">
													<input type="text" name="TRANS_NAME" style="width: 300px; height:20px;" id="TRANS_NAME" class="ac_input" size="300"/>
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
													经转关系信息
												</th>
												<th align="right" colspan="4">
													<a href='#' onClick="addDisp();">新增经转局</a>
													&nbsp;
												</thead>
											</tr>
											<thead>
												<td align="left" class="listtableheadleft" width="100">
													行政区域
												</td>
												<td align="left" class="listtableheadleft" width="200">
													经转局名
												</td>
												<td align="left" class="listtableheadleft1" width="500">
													经转范围
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
