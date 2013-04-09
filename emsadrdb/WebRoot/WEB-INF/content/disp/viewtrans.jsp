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
		<title>经转范围信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				$("#TRANS_NAME").val('${TRANS_NAME}');
				backfresh('fresh');
			})
			function ajaxQueryPage(pageNo){
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var url = '${ctx}/disp/b07r02dispquery!querytrans.action';
				var tTRANS_NAME=$("#TRANS_NAME").val();
			    var params = {
							  TRANS_NAME:tTRANS_NAME,
			                  DISP_OFFICE_CODE:'${DISP_OFFICE_CODE}',
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
						if(getUsWebRuleGJ('${EMS_USER.rulLevel}') || resultdata[i].FLAG != '3'){
						tr+="<td valign=top align=left class=listtablebodyleft1  width=77>&nbsp;<a href='#' onClick='zhTrans(" + '"' + resultdata[i].TRANS_CODE + '"' + ',"' + resultdata[i].TRANS_NAME + '"'  + ',"' + resultdata[i].DISP_OFFICE_CODE + '"' + ");'>调整</a></td>";
						}else{
						tr+="<td valign=top align=left class=listtablebodyleft1  width=77><a href='#' >&nbsp;</a></td>";
						}
						tr+="<td align=left class=listtablebodyleft1 width=150>&nbsp;"+resultdata[i].PROVINCE_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 width=150>&nbsp;"+resultdata[i].FLAG_NAME+"</td>";	
						tr+="<td align=left class=listtablebodyleft2 width=150>&nbsp;"+resultdata[i].TRANS_NAME+"</td>";				
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr><td colspan=4 class=listtablebodyleft2>"+data.page.ajaxUrl+"</td></tr>";
					$('#address_table').append(url);
				}else{
					var url="<tr><td colspan=4 class=listtablebodyleft2>找到 0 条记录</td></tr>";
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
			function backQuery(flag){
				window.returnValue=flag;
				window.opener =null;
				window.close();
			}		
			function addTrans(tDISP_OFFICE_CODE){
				var value = window.showModalDialog("${ctx}/disp/b07cud01dispview!addtrans.action?FLAG=ADD&DISP_OFFICE_CODE=" + tDISP_OFFICE_CODE,"Code","dialogWidth:600px;dialogHeight:200px;resizable=1,scrollbars=auto");
				backfresh(value);
			}	
			function delTrans(tTRANS_CODE){
				$.prompt('该操作将删除经转范围！确认执行操作？',{
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
			function callajaxdelTran(data){
				$.prompt(data.saveMessage,{buttons: {'确定': true}});
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
				backfresh('fresh');
			}
			function zhTrans(tTRANS_CODE,tTRANS_NAME,tDISP_OFFICE_CODE){
				var value = window.showModalDialog("${ctx}/disp/b07cud01dispview!zhtrans.action?FLAG=ZHUAN&TRANS_CODE=" + tTRANS_CODE +"&TRANS_NAME=" + encodeURI(tTRANS_NAME) + "&DISP_OFFICE_CODE=" + tDISP_OFFICE_CODE,"Code","dialogWidth:600px;dialogHeight:200px;resizable=1,scrollbars=auto");
				backfresh(value);
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
										经转范围查询
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<a id="query" href='#' onClick="ajaxQueryPage(0)" style="font-size:12px">查询</a>
								<a id="queryclear" href='#' style="font-size:12px">重置</a>
								<a id="queryback" href='#' onClick="backQuery('fresh')" style="font-size:12px">返回</a>
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
												<td class="listtablehead" colspan="2">
													&nbsp;${DISP_OFFICE_NAME}
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													经转范围名称:
												</td>
												<td class="js_left_txt" id="td_tpdistrict_content">
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
													经转范围信息
												</th>
												<th align="right" colspan="4">
													<a href='#' onClick="addTrans('${DISP_OFFICE_CODE}');">新增经转范围</a>
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft" width="75">
													操作
												</td>
												<td align="left" class="listtableheadleft" width="150">
													行政区域
												</td>
												<td align="left" class="listtableheadleft" width="150">
													本市/外市/外省
												</td>
												<td align="left" class="listtableheadleft1" width="150">
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
