<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/My97DatePicker/My97DatePicker.jsp"%>
<html>

	<head>
		<title>公告信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				//backfresh("fresh");
			})
			function ajaxQueryPage(pageNo){
				
				var url = '${ctx}/address/b04r02addrquery!querygonggao.action';

				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				
			    var params = {
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
						tr+="<td align=left class=listtablebodyleft1  width=150>&nbsp;"+resultdata[i].GZCX_EXZT+"</td>";
						tr+="<td align=left class=listtablebodyleft1  width=300>&nbsp;"+resultdata[i].GZCX_EXCX+"</td>";
						if(resultdata[i].GZCX_FLAG==2){
							tr+="<td align=left class=listtablebodyleft2  width=100>&nbsp;<a href='#' onClick='kanXX(" + resultdata[i].GZCX_ID + ");'>详细</a></td>";
						}else{
							tr+="<td align=left class=listtablebodyleft2  width=100>&nbsp;</td>";
						}
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr><td colspan=3 class=listtablebodyleft2>"+data.page.ajaxUrl+"</td></tr>";
					$('#address_table').append(url);
				}else{
					var url="<tr><td colspan=3 class=listtablebodyleft2>找到 0 条记录</td></tr>";
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
			function kanXX(tGZCX_ID){
				value = window.showModalDialog("${ctx}/query/b04r01queryview!viewxx.action?FLAG=VIEW&GZCX_ID=" + tGZCX_ID,"Code","dialogWidth:1000px;dialogHeight:800px;resizable=1,scrollbars=auto");
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
										 公告信息
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<a id="query" href='#' onClick="ajaxQueryPage(0)" style="font-size:12px">刷新</a>
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
									<div id="tablelistdiv">
										<table class="listtable" width="900"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="3">
													公告信息
												</th>
											</thead>
											<thead>
												<th align="left"  colspan="3">
													(2012.6.29 更新公告)<br>
													 为适应参与南京集散邮件及时维护的要求，进口局批区批段（南京）功能已经上线，请各进口局用户即日起通过该模块进行收寄当日未匹配投递区邮件的及时维护，当日未维护的邮件可以在进口局批区批段（南京历史）进行维护。不参与南京集散的邮件暂时不用进行人工批区批段的操作。
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft" width="150">
													主题
												</td>
												<td align="left" class="listtableheadleft1" width="300">
													内容
												</td>
												<td align="left" class="listtableheadleft1" width="100">
													操作
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
						<img src="${ctx}/images/buttom_left2.gif" width="17" height="17" /></td>
					<td background="${ctx}/images/buttom_bgs.gif">
						<img src="${ctx}/images/buttom_bgs.gif" width="17" height="17"></td>
					<td valign="bottom" background="${ctx}/images/mail_rightbg.gif">
						<img src="${ctx}/images/buttom_right2.gif" width="16" height="17" /></td>
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
