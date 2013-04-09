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
		<title>进口未匹配投递区（段）邮件名址维护详情(按省)</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<style type="text/css"> 
			<!--
			.selflisttable {
				border-color: #bfc4ca #bfc4ca #bfc4ca #bfc4ca;
				font-family: Arial, Helvetica, sans-serif;
				border-style: solid solid solid solid;
				border-width: 0px 0px 0px 0px;
			}
			.selflisttableth {
				font-size: 12px;
				line-height: 25px;
				border-style: solid solid solid solid;
				border-width: 1px 1px 1px 1px;
				background-color: #DDDDFF;
			}
			
			.selflisttablebodyleft1{
				font-size: 12px;
				line-height: 20px;
				border-left-width: 1px;
				border-left-style: solid;
				border-bottom-width: 1px;
				border-bottom-style: solid;
				padding: 2;
				margin: 1;
			}	
			.selflisttablebodyleft2{
				font-size: 12px;
				line-height: 20px;
				border-left-width: 1px;
				border-left-style: solid;
				border-right-width: 1px;
				border-right-style: solid;
				border-bottom-width: 1px;
				border-bottom-style: solid;
				padding: 2;
				margin: 1;
			}		 
			-->
		</style>

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
				var tIS_TOUDIQU=$("input[name='IS_TOUDIQU'][checked]").val();
				var tIS_CUSPEO=$("input[name='IS_CUSPEO'][checked]").val();
				var tIS_DISTRI=$("input[name='IS_DISTRI'][checked]").val();
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var url = '${ctx}/disp/b07r02dispquery!querytjpwhrbopdn.action';
			    var params = {
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
				if(resultdata.length>1){
					
					var vPG_SJ_CODELVQ = 100;
					var vPG_SJ_CODELVQB = 100;
					var vPROVINCE_NAME = '';
					var vTDQNUM = 0;
					
					for(var i=0;i<resultdata.length;i++){
						var tr="<tr id='tr_query'>";
						
						var tIS_TOUDIQU=$("input[name='IS_TOUDIQU'][checked]").val();
						if(tIS_TOUDIQU == 1){
							vPROVINCE_NAME = resultdata[i].PROVINCE_NAME;
							vPROVINCE_NAME = vPROVINCE_NAME.substr(0,2);
							if(vPROVINCE_NAME == '内蒙'){vPROVINCE_NAME = '内蒙古';}
							if(vPROVINCE_NAME == '黑龙'){vPROVINCE_NAME = '黑龙江';}
						}else{
							vPROVINCE_NAME = resultdata[i].CITY_NAME;
						}
						
						tr+="<td valign=top align=left class=selflisttablebodyleft1  width=150>"+vPROVINCE_NAME+"</td>";

						tr+="<td valign=top align=left class=selflisttablebodyleft1  width=200>"+resultdata[i].NUM+"</td>";
						//r+="<td valign=top align=left class=selflisttablebodyleft1  width=100>"+resultdata[i].SR_COUNT+"</td>";

						tr+="<td valign=top align=left class=selflisttablebodyleft1  width=250>"+resultdata[i].REMEAKTJ+"</td>";

						tr+="<td valign=top align=left class=selflisttablebodyleft1  width=200>"+resultdata[i].ADDR_SR_COUNT+"</td>";
						
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					
					var myDate = new Date();
					var sj = myDate.getFullYear() + '-' + (myDate.getMonth()+1) + '-' + myDate.getDate() + '   ' + myDate.toLocaleTimeString();
					var url="<tr><td colspan=11 class=selflisttablebodyleft2>" +  sj  + "</td></tr>";
					
					$('#address_table').append(url);
					
					url="<tr><td colspan=11 class=selflisttablebodyleft2>" + data.page.ajaxUrl  + "</td></tr>";
					
					$('#address_table').append(url);
					
				}else{
					var url="<tr><td colspan=11 class=selflisttablebodyleft2>找到 0 条记录</td>";
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
										统计分析 >>进口未匹配投递区（段）邮件名址维护详情(按省)
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
												<td class="listtablehead" colspan="2">
													开始时间:
													<input type="text" name="ITEM_DATESS" style="width: 100px; height:20px;" id="ITEM_DATESS" class="ac_input" size="300"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/><font color="#FF0000">*</font>
												</td>
												<td class="listtablehead" colspan="2">
													结束时间:
													<input type="text" name="ITEM_DATESE" style="width: 100px; height:20px;" id="ITEM_DATESE" class="ac_input" size="300"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/><font color="#FF0000">*</font>
												</td>
											</tr>
											<tr id="viewquery_table">
												<td class="listtablehead"  colspan="4">
													统计范围:
													<input type="radio" name="IS_TOUDIQU" id="IS_TOUDIQU" value="1" checked="checked">按省统计
													<input type="radio" name="IS_TOUDIQU" id="IS_TOUDIQU" value="2">按城市统计
													<input type="radio" name="IS_TOUDIQU" id="IS_TOUDIQU" value="3">配置多个投递区(≧2)
													<input type="radio" name="IS_TOUDIQU" id="IS_TOUDIQU" value="4">使用多个投递区(≧2)
												</td>
											</tr>
											<tr id="viewquery_table">
												<td class="listtablehead"  colspan="4">
													收寄属性:
													<input type="radio" name="IS_CUSPEO" id="IS_CUSPEO" value="" checked="checked">全部
													<input type="radio" name="IS_CUSPEO" id="IS_CUSPEO" value="1">速递专业揽收
													<input type="radio" name="IS_CUSPEO" id="IS_CUSPEO" value="2">电子化支局
												</td>
												<td class="listtablehead"  colspan="2" style="display:none">
													集散/非集散:
													<input disabled type="radio" name="IS_DISTRI" id="IS_DISTRI" value="">全部
													<input type="radio" name="IS_DISTRI" id="IS_DISTRI" value="1"  checked="checked">集散
													<input disabled type="radio" name="IS_DISTRI" id="IS_DISTRI" value="2">非集散
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
										<table class="selflisttable" width="1000"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th class="selflisttableth" align="center" colspan="11">
													进口未匹配投递区（段）邮件名址维护详情(按省)
												</th>
											</tr>
											<thead>
												<td align="left" class="selflisttablebodyleft1" width="150">
													地市
												</td>
												<td align="left" class="selflisttablebodyleft1" width="100">
													投递区维护数量
												</td>
												<td align="left" class="selflisttablebodyleft1" width="100">
													邮件维护状态
												</td>
												<td align="left" class="selflisttablebodyleft2" width="100">
													邮件维护数
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
