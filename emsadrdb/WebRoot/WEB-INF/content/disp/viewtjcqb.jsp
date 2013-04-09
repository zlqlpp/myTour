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
		<title>全名址信息匹配库统计(按市)</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				//backfresh('fresh');
				if('${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2'){
					$("#PROVINCE_NAMES").prepend("<option selected='selected' value=''>全部</option>");
				}
			})
			
			function ajaxQueryPage(pageNo){
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var tPROVINCE_CODE=$("#PROVINCE_NAMES").val();
				var tIS_TOUDIQU=$("input[name='IS_TOUDIQU'][checked]").val();
				var url = '${ctx}/disp/b07r02dispquery!querytjcqb.action';
			    var params = {
			    				IS_TOUDIQU:tIS_TOUDIQU,
								PROVINCE_CODE:tPROVINCE_CODE,
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
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].CITY_NAME;
						if(resultdata[i].CITY_NAME != '省合计'){
						tr+="&nbsp;<a href='#' onClick='tdDisp(" + '"' + resultdata[i].DISTRICT_CODE + '"' + ',"' + resultdata[i].CITY_NAME + '"' + ");'>投递区</a>";
						}
						tr+="</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].SJ_CODE+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].DT_CODE+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].DT_SJ_CODE+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=150>&nbsp;"+resultdata[i].DT_SJNO_CODE+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].PG_SJ_CODE+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft2  width=150>&nbsp;"+resultdata[i].PG_SJNO_CODE+"</td>";				
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					
					var myDate = new Date();
					var sj = myDate.getFullYear() + '-' + (myDate.getMonth()+1) + '-' + myDate.getDate() + '   ' + myDate.toLocaleTimeString();
					var url="<tr><td colspan=8 class=listtablebodyleft2>" +  sj  + "</td></tr>";
					
					$('#address_table').append(url);
					
					url="<tr><td colspan=8 class=listtablebodyleft2>" + data.page.ajaxUrl   + "</td></tr>";
					
					$('#address_table').append(url);
				}else{
					var url="<tr><td colspan=8 class=listtablebodyleft2>找到 0 条记录</td></tr>";
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
			function tdDisp(tDISTRICT_CODE,tCITY_NAME){
				var value = window.showModalDialog("${ctx}/disp/b07r01dispview!viewtjtddisp.action?FLAG=TDQ&DISTRICT_CODE=" + tDISTRICT_CODE + "&CITY_NAME=" + encodeURI(tCITY_NAME),"Code","dialogWidth:300px;dialogHeight:400px;resizable=1,scrollbars=auto");
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
										统计分析 >>全名址信息匹配库统计(按市)
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
													省份:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME"></s:select>
												</td>
												<td class="listtablehead"  colspan="2">
													投递区配置数量:
													<input type="radio" name="IS_TOUDIQU" id="IS_TOUDIQU" value="" checked="checked">全部
													<input type="radio" name="IS_TOUDIQU" id="IS_TOUDIQU" value="1">1个投递区
													<input type="radio" name="IS_TOUDIQU" id="IS_TOUDIQU" value="2">2个及以上投递区
													<input type="radio" name="IS_TOUDIQU" id="IS_TOUDIQU" value="3">3个及以上投递区
												</td>
											</tr>
										</table>
									</div>
									
									<br />
									<div id="tablelistdiv">
										<table class="listtable" width="900"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="center" colspan="8">
													全名址信息匹配库统计(按市)
												</th>
											</tr>
											<thead>
												<td align="left" class="listtableheadleft" width="100">
													省份
												</td>
												<td align="left" class="listtableheadleft" width="100">
													地市
												</td>
												<td align="left" class="listtableheadleft" width="100">
													地址数
												</td>
												<td align="left" class="listtableheadleft" width="100">
													投递区配置数
												</td>
												<td align="left" class="listtableheadleft" width="100">
													投递区使用数
												</td>
												<td align="left" class="listtableheadleft" width="150">
													未配置投递区地址数
												</td>
												<td align="left" class="listtableheadleft" width="100">
													投递段使用数
												</td>
												<td align="left" class="listtableheadleft1" width="150">
													未配置投递段地址数
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
