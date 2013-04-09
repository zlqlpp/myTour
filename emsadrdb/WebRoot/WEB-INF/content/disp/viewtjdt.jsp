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
		<title>全名址投递区信息量统计</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				if('${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2'){
					$("#PROVINCE_NAMES").append("<option selected='selected' value=''>全国</option>"); 
				}

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
				var tIS_TOUDIQU=$("input[name='IS_TOUDIQU'][checked]").val();
				var tIS_DISTRI=$("input[name='IS_DISTRI'][checked]").val();
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var url = '${ctx}/disp/b07r02dispquery!querytjdt.action';
			    var params = {
								PROVINCE_CODE:tPROVINCE_CODE,
								ITEM_DATESS:tITEM_DATESS,
								ITEM_DATESE:tITEM_DATESE,
								IS_CUSPEO:tIS_CUSPEO,
								IS_TOUDIQU:tIS_TOUDIQU,
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

				//var vCCOUNT = 0;
				//var vNSRCOUNT = 0;
				//var vSRCOUNT = 0;

				if(resultdata.length>0){
					for(var i=0;i<resultdata.length;i++){

						//vCCOUNT += parseInt(resultdata[i].CCOUNT);
						//vNSRCOUNT += parseInt(resultdata[i].NSRCOUNT);
						//vSRCOUNT += parseInt(resultdata[i].SRCOUNT);

						var tr="<tr id='tr_query'>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].PROVINCE_NAME+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=300>"+resultdata[i].DT_NAME+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].NJXZ+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].JDSX+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].CCOUNT+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+resultdata[i].NSRCOUNT+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft2  width=100>"+resultdata[i].SRCOUNT+"</td>";
						tr+="</tr>";
						$('#address_table').append(tr);
						
					}

					/*
					var tr="<tr id='tr_query' style='background:#FEEBD0'>";
					tr+="<td valign=top align=left class=listtablebodyleft1  width=100>合计</td>";
					tr+="<td valign=top align=left class=listtablebodyleft1  width=300>合计</td>";
					tr+="<td valign=top align=left class=listtablebodyleft1  width=100>合计</td>";
					tr+="<td valign=top align=left class=listtablebodyleft1  width=100>合计</td>";
					tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+vCCOUNT+"</td>";
					tr+="<td valign=top align=left class=listtablebodyleft1  width=100>"+vNSRCOUNT+"</td>";
					tr+="<td valign=top align=left class=listtablebodyleft2  width=100>"+vSRCOUNT+"</td>";
					tr+="</tr>";
					$('#address_table').append(tr);
					*/
					
					url="<tr><td colspan=11 class=listtablebodyleft2>" + data.page.ajaxUrl  + "</td></tr>";
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


			function ajaxQueryPageexport(pageNo){

				var myDate=new Date();
				var mysj=myDate.getFullYear() + "-" + (myDate.getMonth()+1) + "-" + myDate.getDate()+ " " + myDate.getHours() + ":" + myDate.getMinutes() + ":" + myDate.getSeconds();
				$("#cxsjs").text('导出开始时间: ' + mysj);
				
				$("#exporttxt").text("   数据导出中...");
				
				var tPROVINCE_CODE=$("#PROVINCE_NAMES").val();
				var tITEM_DATESS=$('#ITEM_DATESS').val();
				if(tITEM_DATESS.length < 10){
					tITEM_DATESS=tITEM_DATESS.replace('-','').replace('-','0');
				}else{
					tITEM_DATESS=tITEM_DATESS.replace('-','').replace('-','');
				}
				if(tITEM_DATESS==''){
					$.prompt('请输入开始日期',{buttons: {'确定': true}});
					return false;
				}
				var tITEM_DATESE=$('#ITEM_DATESE').val();
				if(tITEM_DATESE.length < 10){
					tITEM_DATESE=tITEM_DATESE.replace('-','').replace('-','0');
				}else{
					tITEM_DATESE=tITEM_DATESE.replace('-','').replace('-','');
				}
				if(tITEM_DATESE==''){
					$.prompt('请输入结束日期',{buttons: {'确定': true}});
					return false;
				}
				var tIS_CUSPEO=$("input[name='IS_CUSPEO'][checked]").val();
				var tIS_TOUDIQU=$("input[name='IS_TOUDIQU'][checked]").val();
				var tIS_DISTRI=$("input[name='IS_DISTRI'][checked]").val();

				
				var url = '${ctx}/disp/b07r02dispquery!queryexportmethod.action';
				var tEXPORT_XXSTR='全部';
				var LINENAMESTR = '全部';
				tEXPORT_XXSTR='投递区统计';
				LINENAMESTR = "省份,投递区名称,内件性质,寄达时限,全部邮件量,非收容邮件量,收容邮件量";

				var params = {
							  EXREPORT:"投递区统计#数据表",
							  EXDATA:tIS_DISTRI + " , " + tIS_CUSPEO + " , " + tIS_TOUDIQU + " , " + tPROVINCE_CODE + " , " + tITEM_DATESS + " , " + tITEM_DATESE,//逗号间隔需要加空格
							  LINEKEY:"viewtjdt",
							  LINEMAIN:"viewtjdt,数据表,投递区统计",
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
					$("#exporttxt").html("   <a href='" + data.EXPORTALLPATH + "'>数据导出" + data.pagecount + "条完毕,请点击下载!<a/>");
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
										统计分析 >>全名址投递区信息量统计
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
												<td class="listtablehead" id="td_privince_content">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME"></s:select>
												</td>
												<td class="listtablehead"  colspan="2">
													集散/非集散:
													<input type="radio" name="IS_DISTRI" id="IS_DISTRI" value="" checked="checked">全部
													<input type="radio" name="IS_DISTRI" id="IS_DISTRI" value="1">集散
													<input type="radio" name="IS_DISTRI" id="IS_DISTRI" value="2">非集散
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
													内件性质:
													<input type="radio" name="IS_CUSPEO" id="IS_CUSPEO" value="" checked="checked">全部
													<input type="radio" name="IS_CUSPEO" id="IS_CUSPEO" value="1">文件
													<input type="radio" name="IS_CUSPEO" id="IS_CUSPEO" value="2">物品
													<!--
													<input type="radio" name="IS_CUSPEO" id="IS_CUSPEO" value="3">信函
													-->
												</td>
												<td class="listtablehead"  colspan="2">
													寄达时限:
													<input type="radio" name="IS_TOUDIQU" id="IS_TOUDIQU" value="" checked="checked">全部
													<input type="radio" name="IS_TOUDIQU" id="IS_TOUDIQU" value="1">经济时限
													<input type="radio" name="IS_TOUDIQU" id="IS_TOUDIQU" value="2">标准时限
													<!--
													<input type="radio" name="IS_TOUDIQU" id="IS_TOUDIQU" value="3">限时递
													<input type="radio" name="IS_TOUDIQU" id="IS_TOUDIQU" value="4">次晨达
													<input type="radio" name="IS_TOUDIQU" id="IS_TOUDIQU" value="5">次日递
													<input type="radio" name="IS_TOUDIQU" id="IS_TOUDIQU" value="6">当日递
													-->
												</td>
											</tr>
											<tr>
												<td class="listtablehead" colspan="4">
												<label id="cxsjs" style="color:#FF0000"></label>
												<label id="cxsje" style="color:#FF0000"></label>
												<label id="exporttxt" style="color:#FF0000"></label>
												</td>
											</tr>
											<tr>
												<td class="listtablehead"  colspan="4" align="right">
													<input id="query" type="button" value="查询" onClick="ajaxQueryPage(0)" />
													<input id="queryexport" type="button" value="导出" onClick="ajaxQueryPageexport(0)" />
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
													全名址投递区信息量统计
												</th>
											</tr>
											<thead>
												<td align="left" class="listtablebodyleft1" width="100">
													省份
												</td>
												<td align="left" class="listtablebodyleft1" width="300">
													投递区名称
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													内件性质
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													寄达时限
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													全部邮件量
												</td>
												<td align="left" class="listtablebodyleft1" width="100">
													非收容邮件量
												</td>
												<td align="left" class="listtablebodyleft2" width="100">
													收容邮件量
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
