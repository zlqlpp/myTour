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
		<title>行政划区信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				viewcity();
			})
			function ajaxQueryPage(pageNo){
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var url = '${ctx}/disp/b07r02dispquery!querywhtrans.action';
				var tDISP_OFFICE_CODE=$("#DISP_OFFICE_CODE").val();
				var tTRANS_NAME=$("#TRANS_NAME").val();
				if(tDISP_OFFICE_CODE != null && tDISP_OFFICE_CODE != ''){
					var params = {
								  DISP_OFFICE_CODE:tDISP_OFFICE_CODE,
								  TRANS_NAME:tTRANS_NAME,
								  pageNo:pageNo
								 };
					jQuery.post(url, params, callbackajaxQueryPage, 'json');
				}else{
					$.prompt('本省不存在经转局，请维护经转局，再行政批区！',{buttons: {'确定': true}});
				}
			}
			//展示市
			function viewcity(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tRulUsCityOffice= null;
				var url = '${ctx}/disp/b07r03dispoption!disppds.action';
				var params = {
					PROVINCE_NAME: tPROVINCE_NAME
				 };
				jQuery.post(url, params, callbackcity, 'json');
			}
			function callbackcity(data){
				var d=data.dispBeans;
				$('#DISP_OFFICE_CODE option').remove();
				for(var i=0;i<d.length;i++){
					$("#DISP_OFFICE_CODE").append("<option value='" + d[i].DISP_OFFICE_CODE + "'>" + d[i].DISP_OFFICE_NAME + "</option>"); 
				}
				viewDT();
			}
			//展示市
			function viewDT(){
				backfresh('fresh');
				var tDISP_OFFICE_CODE=$("#DISP_OFFICE_CODE").val();
				var tRulUsCityOffice= null;
				var url = '${ctx}/disp/b07r03dispoption!dispdwhs.action';
				var params = {
					DISP_OFFICE_CODE: tDISP_OFFICE_CODE
				 };
				jQuery.post(url, params, callbackdt, 'json');
			}
			function callbackdt(data){
				var d=data.dispBeans;
				$('#DT_PK_CODE option').remove();
				$("#DT_PK_CODE").prepend("<option selected='selected' value=''>请选择</option>");
				for(var i=0;i<d.length;i++){
					$("#DT_PK_CODE").append("<option value='" + d[i].DT_PK_CODE + "'>" + d[i].DT_NAME + "</option>"); 
				}
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
						tr+="<td align=left class=listtablebodyleft1 width=50><input type='checkbox' name='rlCheckBox' value='"+resultdata[i].TRANS_CODE + "'></td>";
						tr+="<td align=left class=listtablebodyleft1 width=100>&nbsp;"+resultdata[i].PROVINCE_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 width=100>&nbsp;"+resultdata[i].DISP_OFFICE_NAME+"</td>";	
						tr+="<td align=left class=listtablebodyleft1 width=100>&nbsp;"+resultdata[i].FLAG_NAME+"</td>";	
						tr+="<td align=left class=listtablebodyleft2 width=100>&nbsp;"+resultdata[i].TRANS_NAME+"</td>";	
						tr+="<td align=left class=listtablebodyleft2 width=300>&nbsp;"+resultdata[i].DT_NAME+"</td>";			
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
			function backQuery(flag){
				window.returnValue=flag;
				window.opener =null;
				window.close();
			}		
			function queryAll(){
				var n = $("input[name=chooseName]:checked").length; 
				 if(n==0){ 
					 $("#address_table tbody input[name=rlCheckBox]").each(function(){
			                $(this).attr('checked',false);
			            }); 
				 }else{ 
					$("#address_table tbody input[name=rlCheckBox]").each(function(){
				                $(this).attr('checked',true);
				           });
				 } 
			}
			function addWhtrans(){
				var c=$('#address_table tbody input[name=rlCheckBox]:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
					cv = cv+','+$(this).val();
				});
				if(cv==''){
					$.prompt('请选择经转范围',{buttons: {'确定': true}});
					return false;
				}
				var tTOTAL_ALL_VALUE=cv.substr(1,cv.length);
				var tDT_PK_CODE=$("#DT_PK_CODE").val();
				/*if(tDT_PK_CODE==null){
					$.prompt('请选择投递区',{buttons: {'确定': true}});
					return false;
				}*/
				if(tDT_PK_CODE.length<1){
					$.prompt('请选择投递区',{buttons: {'确定': true}});
					return false;
				}
				$.prompt('该操作将调整所选经转范围的所有投递区！请慎重考虑后执行？',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									TOTAL_ALL_VALUE:tTOTAL_ALL_VALUE,
									DT_PK_CODE:tDT_PK_CODE
							};
							var url = '${ctx}/disp/b07cud02dispconfig!dttrans.action';
							if(!(bro.msie && bro.version >=8)){
								$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
							}else{
								$("#nodata").show();
								$("#yesdata").hide();
							}
							jQuery.post(url, params, callajaxAdd, 'json');
						}
					}
				});
			}
			function callajaxAdd(data){
				$('#queryAll').attr('checked',false);
				$.prompt(data.saveMessage,{buttons: {'确定': true}});
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
				backfresh("fresh");
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
										投递区部基础数据 >>行政划区
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
												<td class="listtablehead">
													经转局名称:
												</td>
												<td class="js_left_txt" id="td_tpdistrict_content">
													<s:select theme="simple" list="dispp" id="PROVINCE_NAMES" listKey="PROVINCE_CODE" listValue="PROVINCE_NAME" onChange="viewcity()"></s:select>
													<select class="simple" id="DISP_OFFICE_CODE"  style="width:150" onChange="viewDT()"></select>
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
													行政划区信息
												</th>
												<th align="right" colspan="5">
													&nbsp;配置到投递区：
													<select class="simple" id="DT_PK_CODE"  style="width:300"></select>
													<a href='#' onClick="addWhtrans();">&nbsp;提交配置</a>
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft" width="75">
													<input id="queryAll" name="chooseName" type="checkbox" onClick="queryAll()"/>
												</td>
												<td align="left" class="listtableheadleft" width="100">
													行政区域
												</td>
												<td align="left" class="listtableheadleft" width="100">
													经转局
												</td>
												<td align="left" class="listtableheadleft" width="100">
													本市/外市/外省
												</td>
												<td align="left" class="listtableheadleft1" width="100">
													经转范围
												</td>
												<td align="left" class="listtableheadleft1" width="300">
													投递区
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
