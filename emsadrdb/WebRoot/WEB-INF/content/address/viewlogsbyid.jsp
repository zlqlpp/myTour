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
		<title>日志信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				backfresh('fresh');
			})
			function backfresh(value){
				if(value == "fresh") {
					ajaxQueryPage(0);
				}
			}
			function ajaxQueryPage(pageNo){
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var url = '${ctx}/address/b04r02addrquery!querylogsbyid.action';
				var tALL_NAME=$("#ALL_NAME").val();
			    var params = {
							  ALL_NAME:tALL_NAME,
			                  DIST_CD:'${DIST_CD}',
			                  STRT_ID:'${STRT_ID}',
							  RSDNBLDG_ID:'${RSDNBLDG_ID}',
							  ORG_ID:'${ORG_ID}',
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
						tr+="<td align=left class=listtablebodyleft1 width=180>&nbsp;"+resultdata[i].STRT_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 width=180>&nbsp;"+resultdata[i].RSDNBLDG_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 width=180>&nbsp;"+resultdata[i].ORG_NAME+"</td>";				
						tr+="<td align=left class=listtablebodyleft1 width=80>&nbsp;"+resultdata[i].DATA_FLAG_NAME+"</td>";	
						tr+="<td align=left class=listtablebodyleft1 width=150>&nbsp;"+resultdata[i].DATA_DATE+"</td>";	
						tr+="<td align=left class=listtablebodyleft2 width=150>&nbsp;"+resultdata[i].DATA_USER_NAME+"</td>";							
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
			function backQuery(flag){
				window.returnValue=flag;
				window.opener =null;
				window.close();
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
										日志查询
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
												<td class="listtablehead" colspan="2">
													&nbsp;${TOTAL_DISTRICT_NAME}
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													日志名称(街道/小区/机构):
												</td>
												<td class="js_left_txt" id="td_tpdistrict_content">
													<input type="text" name="ALL_NAME" style="width: 300px; height:20px;" id="ALL_NAME" class="ac_input" size="300"/>
												</td>
											</tr>
										</table>
									</div>
									<br />
									<div id="tablelistdiv">
										<table class="listtable" width="900" id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left" colspan="6">
													日志信息
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft" width="180">
													街道名称
												</td>
												<td align="left" class="listtableheadleft" width="180">
													小区名称
												</td>
												<td align="left" class="listtableheadleft" width="180">
													机构名称
												</td>
												<td align="left" class="listtableheadleft" width="80">
													数据状态
												</td>
												<td align="left" class="listtableheadleft" width="150">
													提交时间
												</td>
												<td align="left" class="listtableheadleft1" width="150">
													提交用户
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
	//document.oncontextmenu = function(){
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
