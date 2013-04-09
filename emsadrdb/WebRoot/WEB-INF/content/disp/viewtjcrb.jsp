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
		<title>全名址信息匹配率(按市)</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				var myDate = new Date(new Date() - 24*3600*1000);
				
				var tMonth = myDate.getMonth()+1;
				if(tMonth<10){
					tMonth = '0' + tMonth;
				}
				 
				var tDate = myDate.getDate();
				if(tDate<10){
					tDate = '0' + tDate;
				}
				var sj = myDate.getFullYear() + '-' + tMonth + '-' + tDate;
				
				$("#ITEM_DATESS").val(sj);
				$("#ITEM_DATESE").val(sj);
				//backfresh('fresh');
			})
			
			function ajaxQueryPage(pageNo){
				var tPROVINCE_CODE=$("#PROVINCE_NAMES").val();
				var tITEM_DATESS=$('#ITEM_DATESS').val();
				tITEM_DATESS=tITEM_DATESS.replace('-','').replace('-','');
				if(tITEM_DATESS==''){
					$.prompt('请输入开始日期',{buttons: {'确定': true}});
					return false;
				}
				var tITEM_DATESE=$('#ITEM_DATESE').val();
				tITEM_DATESE=tITEM_DATESE.replace('-','').replace('-','');
				if(tITEM_DATESE==''){
					$.prompt('请输入结束日期',{buttons: {'确定': true}});
					return false;
				}
				var tIS_CUSPEO=$("input[name='IS_CUSPEO'][checked]").val();
				var tIS_DISTRI=$("input[name='IS_DISTRI'][checked]").val();
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var url = '${ctx}/disp/b07r02dispquery!querytjcrb.action';
			    var params = {
								PROVINCE_CODE:tPROVINCE_CODE,
								ITEM_DATESS:tITEM_DATESS,
								ITEM_DATESE:tITEM_DATESE,
								IS_CUSPEO:tIS_CUSPEO,
								IS_DISTRI:tIS_DISTRI,
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
					var vDT_SJ_CODELV = 100;
					var vDT_SJ_CODELVDZ = 100;
					var vPG_SJ_CODELVQ = 100;
					var vSRDIPQ_COUNTLVQ = 100;
					var vPROVINCE_NAME = '';
					var vTDQNUM = 0;
					for(var i=0;i<resultdata.length;i++){
						var tr="<tr id='tr_query'>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].CITY_NAME+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].NUM+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].TOTAL_COUNT+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].DISTRI_COUNT+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].ADDR_COUNT+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].TDQ_COUNT+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].ADDRTDQ_COUNT+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].NOADDRTDQ_COUNT+"</td>";

						/*
						vDT_SJ_CODELV = resultdata[i].TDQ_COUNT / resultdata[i].TOTAL_COUNT;
						vDT_SJ_CODELV = Math.round ( vDT_SJ_CODELV * 10000 ) / 100;
						
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+vDT_SJ_CODELV+"%</td>";
								
						vDT_SJ_CODELVDZ = resultdata[i].ADDRTDQ_COUNT / resultdata[i].ADDR_COUNT;
						vDT_SJ_CODELVDZ = Math.round ( vDT_SJ_CODELVDZ * 10000 ) / 100;
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+vDT_SJ_CODELVDZ+"%</td>";
						*/
						
						vPG_SJ_CODELVQ = resultdata[i].TDQ_COUNT / ( parseInt(resultdata[i].ADDR_COUNT) + parseInt(resultdata[i].NOADDRTDQ_COUNT) );
						vPG_SJ_CODELVQ = Math.round ( vPG_SJ_CODELVQ * 10000 ) / 100;
						tr+="<td valign=top align=left class=listtablebodyleft2  width=100>"+vPG_SJ_CODELVQ+"%</td>";

						tr+="<td valign=top align=left class=listtablebodyleft2  width=100>"+resultdata[i].SRDIPQ_COUNT+"</td>";

						vSRDIPQ_COUNTLVQ = (parseInt(resultdata[i].TDQ_COUNT) + parseInt(resultdata[i].SRDIPQ_COUNT)) / ( parseInt(resultdata[i].ADDR_COUNT) + parseInt(resultdata[i].NOADDRTDQ_COUNT) );
						vSRDIPQ_COUNTLVQ = Math.round ( vSRDIPQ_COUNTLVQ * 10000 ) / 100;
						tr+="<td valign=top align=left class=listtablebodyleft2  width=100>"+vSRDIPQ_COUNTLVQ+"%</td>";
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					
					var myDate = new Date();
					var sj = myDate.getFullYear() + '-' + (myDate.getMonth()+1) + '-' + myDate.getDate() + '   ' + myDate.toLocaleTimeString();
					var url="<tr><td colspan=20 class=listtablebodyleft2>" +  sj  + "</td></tr>";
					
					$('#address_table').append(url);
					
					url="<tr><td colspan=20 class=listtablebodyleft2>" + data.page.ajaxUrl  + "</td></tr>";
					
					$('#address_table').append(url);
					
				}else{
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
										统计分析 >>全名址信息匹配率(按市)
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
												<td class="listtablehead" width="80">
													省份:
												</td>
												<td class="listtablehead" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME"></s:select>
												</td>
												
											</tr>
											<tr>
												<td class="listtablehead">
													开始时间:
												</td>
												<td class="listtablehead" id="td_tpdistrict_content">
													<input type="text" name="ITEM_DATESS" style="width: 100px; height:20px;" id="ITEM_DATESS" class="ac_input" size="300" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/><font color="#FF0000">*</font>
												</td>
												<td class="listtablehead">
													结束时间:
												</td>
												<td class="listtablehead" id="td_tpdistrict_content">
													<input type="text" name="ITEM_DATESE" style="width: 100px; height:20px;" id="ITEM_DATESE" class="ac_input" size="300" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/><font color="#FF0000">*</font>
												</td>
											</tr>
											<tr id="viewquery_table">
												<td class="listtablehead"  colspan="2">
													收寄属性:
													<input type="radio" name="IS_CUSPEO" id="IS_CUSPEO" value="" checked="checked">全部
													<input type="radio" name="IS_CUSPEO" id="IS_CUSPEO" value="1">速递专业揽收
													<input type="radio" name="IS_CUSPEO" id="IS_CUSPEO" value="2">电子化支局
												</td>
												<td class="listtablehead"  colspan="2">
													集散/非集散:
													<input type="radio" name="IS_DISTRI" id="IS_DISTRI" value="" checked="checked">全部
													<input type="radio" name="IS_DISTRI" id="IS_DISTRI" value="1">集散
													<input type="radio" name="IS_DISTRI" id="IS_DISTRI" value="2">非集散
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
												<th align="center" colspan="20">
													全名址信息匹配率(按市)
												</th>
											</tr>
											<thead>
												<td align="left" class="listtablebodyleft1" width="100">
													地市
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													投递区维护数量
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													国内进口邮件总量
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													参加南京集散的进口邮件总量
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													含收件人地址的名址数量
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													投递区匹配成功邮件总量
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													利用名址匹配投递区邮件总量
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													利用寄达地直接匹配投递区邮件总量
												</td>
												<!--
												<td align="left" class="listtablebodyleft1" width="100">
													投递区匹配成功率（按进口邮件总量计）
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													投递区匹配成功率（按地址统计）
												</td>
												  -->
												<td align="left" class="listtablebodyleft2" width="100">
													投递区匹配成功率
												</td>
												<td align="left" class="listtablebodyleft2" width="100">
													进口局人工分拣正确投递区量
												</td>
												<td align="left" class="listtablebodyleft2" width="100">
													投递区匹配成功率(二次)
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
