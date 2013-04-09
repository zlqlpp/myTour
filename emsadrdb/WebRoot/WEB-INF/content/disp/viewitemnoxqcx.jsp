<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/disp/disp.jsp"%>
<%@ include file="/scripts/My97DatePicker/My97DatePicker.jsp"%>
<html>

	<head>
		<title>邮件号码详情查询</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				
			})
			
			function ajaxQueryPage(pageNo){
				var tITEMNO=$("#ITEMNO").val();
				if(tITEMNO.length<1){
					$.prompt('请输入邮件号码',{buttons: {'确定': true}});
					return false;
				}
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var url = '${ctx}/disp/b07r02dispquery!queryitemnoxqcx.action';
			    var params = {
			    				ITEMNO:tITEMNO,
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
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].ITEMNO+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].REC_PROVCITY+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].COLLECT_OFFICE+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].COLLECT_DATE+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].INSERTTIME+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].REC_ALLADDR+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].IS_DISTRI+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].DT_NAME+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].MATCH_DATETIME+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft2  width=100>&nbsp;"+resultdata[i].SEND_FLAG+"</td>";
						tr+="</tr>";
						$('#address_table').append(tr);
						
					}

					url="<tr><td colspan=11 class=listtablebodyleft2>找到" + resultdata.length + "条记录</td></tr>";
					$('#address_table').append(url);
					
				}
				else{
					var url="<tr><td colspan=11 class=listtablebodyleft2>找到 0 条记录</td>";
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
										全名址信息查询 >>邮件号码详情查询
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
										<table class="listtable" id="viewquery_table">
											<tr>
												<td class="listtablehead"   colspan="4">
													邮件号码(多个邮件号用','分割【ES156276826CS,ES157260990CS】):
												</td>
											</tr>
											<tr>
												<td class="listtablehead" id="td_tpdistrict_content"  colspan="4">
													<textarea cols="100" id="ITEMNO" name="ITEMNO" rows="5" onkeydown="value=value.toUpperCase()"></textarea>
												</td>
											</tr>
											<tr>
												<td class="listtablehead"  colspan="4" align="right">
													<input id="query" type="button" value="查询" onClick="ajaxQueryPage(0)" />
													<input id="queryclear" type="button" value="重置" onClick="ajaxQueryPage(0)" />
												</td>
											</tr>
										</table>
									</div>
									
									<br />
									<div id="tablelistdiv">
										<table class="listtable" width="1000"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="center" colspan="11">
													邮件号码详情查询
												</th>
											</tr>
											<thead>
												<td align="left" class="listtablebodyleft1" width="100">
													邮件号
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													寄达地
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													收寄机构
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													收寄时间
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													上传时间
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													名址信息
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													集散标志
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													匹配结果
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													发送时间
												</td>
												<td align="left" class="listtablebodyleft2" width="100">
													发送标志
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
