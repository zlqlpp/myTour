<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/clfw/clfw.jsp"%>
<%@ include file="/scripts/My97DatePicker/My97DatePicker.jsp"%>
<html>

	<head>
		<title>民航路单填报数据填报明细</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){

				$("#address_tablechu").show();
				$("#address_tablejin").hide();

				if('${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2'){
					$("#PROVINCE_NAMES").prepend("<option selected='selected' value=''>全国</option>"); 
					$("#PROVINCE_NAMES").val('');

					$("#queryexport").show();
				}else{
					$("#queryexport").hide();
				}

				viewcity();

				var myDate = new Date();
				
				var tMonth = myDate.getMonth()+1;
				
				if(tMonth<10){
					tMonth = '0' + tMonth;
				}

				var tDate = myDate.getDate();
				if(tDate<10){
					tDate = '0' + tDate;
				}
				var sj = myDate.getFullYear() + '' + tMonth + '' + tDate;
				
				$("#ITEM_DATESS").val(sj);
				$("#ITEM_DATESE").val(sj);
				
				$("#fliphelpcon").hide();
				$("#fliphelpct").click(function(){
					$("#fliphelpcon").slideToggle("fast");
				});
			})


			//展示市
			function viewcity(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var url = '${ctx}/clfw/b09r03clfwoption!citys.action';
				var params = {
					PROVINCE_NAME: tPROVINCE_NAME
				 };
				if(isMunicipalities(tPROVINCE_NAME)){
			    	$('#CITY_NAMES').hide();
					$('#CITY_NAMES option').remove();
			    }else{
					$('#CITY_NAMES').show();
				}
				jQuery.post(url, params, callbackcity, 'json');
			}
			function callbackcity(data){
				var d=data.districtBeans;
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				if(isMunicipalities(tPROVINCE_NAME)){
					$('#COUNTY_NAMES option').remove();
					$("#COUNTY_NAMES").prepend("<option selected='selected' value=''>全市</option>");
			    	for(var i=0;i<d.length;i++){
						$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
			    }else{
					$('#CITY_NAMES option').remove();

					if('${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2' || '${EMS_USER.rulLevel}' == '5'){
						$("#CITY_NAMES").prepend("<option selected='selected' value=''>全省</option>");
					}
						
					for(var i=0;i<d.length;i++){
						$("#CITY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
					viewcounty();
			    }
			}
			
			function viewcounty(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 			
				if(!isMunicipalities(tPROVINCE_NAME)){
					var tCITY_NAME=$("#CITY_NAMES").find("option:selected").text(); 
					if(tCITY_NAME!='请选择'){
						var url = '${ctx}/clfw/b09r03clfwoption!countys.action';
						var params = {
							CITY_NAME: tCITY_NAME
						 };
						jQuery.post(url, params, callbackcounty, 'json');
					}
				}
			}
			
			function callbackcounty(data){
				$('#COUNTY_NAMES option').remove();
				var d=data.districtBeans;
				$("#COUNTY_NAMES").prepend("<option selected='selected' value=''>全市</option>");
				for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
				}
			}
			
			function ajaxQueryPage(pageNo){
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				if(isMunicipalities(tPROVINCE_NAME)){
					var tCITY_ID="";
			    	var tCOUNTY_ID=$("#COUNTY_NAMES").val();
			    }else{
					var tCITY_ID=$("#CITY_NAMES").val();
					var tCOUNTY_ID=$("#COUNTY_NAMES").val();
				}
				var tITEM_DATESS=$('#ITEM_DATESS').val();
				if(tITEM_DATESS==''){
					$.prompt('请输入开始日期',{buttons: {'确定': true}});
					return false;
				}
				var tITEM_DATESE=$('#ITEM_DATESE').val();
				if(tITEM_DATESE==''){
					$.prompt('请输入结束日期',{buttons: {'确定': true}});
					return false;
				}

				var tCLFW_HBXXGL_CLSX=$("input[name='CLFW_HBXXGL_CLSX'][checked]").val();

				if(tCLFW_HBXXGL_CLSX == '0'){

					$("#address_tablechu").show();
					$("#address_tablejin").hide();
					
				}else{
					
					$("#address_tablechu").hide();
					$("#address_tablejin").show();
					
				}

				var url = '${ctx}/clfw/b09r02clfwquery!queryclfwhkldwhmx.action';
			    var params = {
			    		PROVINCE_ID:tPROVINCE_ID,
						CITY_ID:tCITY_ID,
						COUNTY_ID:tCOUNTY_ID,
						ITEM_DATESS:tITEM_DATESS,
						ITEM_DATESE:tITEM_DATESE,
						CLFW_HBXXGL_CLSX:tCLFW_HBXXGL_CLSX,
			            pageNo:pageNo
			                 };
			    jQuery.post(url, params, callbackajaxQueryPage, 'json');
			}
			function callbackajaxQueryPage(data){
				addquerytable(data);
			}
			function addquerytable(data){
				
				var resultdata=data.page.result;

				var tr=" ";
				
				$('#address_tablechu tbody').remove();
				
				$('#address_tablejin tbody').remove();
						
				if(resultdata.length>0){
					
					for(var i=0;i<resultdata.length;i++){

						if(resultdata[i].CLFW_HBXXGL_XSBZ=='0'){

							tr="<tr>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXYL_SFJIDSTR+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_FYRQSJ+"</td>";
							//tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXYL_YLDM+"</td>";
							//tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_LDHM+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:150'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_HBCC+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXYL_ZDJIDSTR+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_ZBS+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_AJTHZBS+"</td>";
							tr+="<td align=left class=listtablebodyleft2 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_LXZBS+"</td>";
							var tCLFW_HBXXGL_CLSXSTR = resultdata[i].CLFW_HBXXGL_CLSXSTR;
							if(tCLFW_HBXXGL_CLSXSTR.substr(0,1) != '1'){
								tCLFW_HBXXGL_CLSXSTR = "<font color='red'>" + tCLFW_HBXXGL_CLSXSTR + "</font>";
							}
							tr+="<td valign=top align=left class=listtablebodyleft2  width=150>&nbsp;"+tCLFW_HBXXGL_CLSXSTR+"</td>";
							
							tr+="</tr>";
							$('#address_tablechu').append(tr);
						}
						else if	(resultdata[i].CLFW_HBXXGL_XSBZ=='1'){
							
							tr="<tr>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXYL_ZDJIDSTR+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_FYRQSJ+"</td>";
							//tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXYL_YLDM+"</td>";
							//tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_LDHM+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:150'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_HBCC+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXYL_SFJIDSTR+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_ZBS+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_AJTHZBS+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_LXZBS+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:120'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_SSZBS+"</td>";
							var tCLFW_HBXXGL_CLSXSTR = resultdata[i].CLFW_HBXXGL_CLSXSTR;
							if(tCLFW_HBXXGL_CLSXSTR.substr(0,1) != '1'){
								tCLFW_HBXXGL_CLSXSTR = "<font color='red'>" + tCLFW_HBXXGL_CLSXSTR + "</font>";
							}
							tr+="<td valign=top align=left class=listtablebodyleft2  width=150>&nbsp;"+tCLFW_HBXXGL_CLSXSTR+"</td>";
							
							tr+="</tr>";

							$('#address_tablejin').append(tr);
						}
						
						
					}
					var url="<tr><td colspan=20 class=listtablebodyleft2>"+data.page.ajaxUrl+
					"<input type='text' name='pagenum' style='width:50px;height:20px;' id='pagenum' class='ac_input'/><a id='pagego' href='#' onClick='pagecx(" + data.page.totalCount + "," + data.page.pageSize + ")' style='font-size:12px'>GO</a>"+
					"</td></tr>";
					$('#address_tablechu').append(url);
					$('#address_tablejin').append(url);
				}else{
					var url="<tr><td colspan=20 class=listtablebodyleft2>找到 0 条记录</td></tr>";
					$('#address_tablechu').append(url);
					$('#address_tablejin').append(url);
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
				
				$("#exporttxt").text("数据导出中...");
				
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				
				if(isMunicipalities(tPROVINCE_NAME)){
					var tCITY_ID="";
			    	var tCOUNTY_ID=$("#COUNTY_NAMES").val();
			    }else{
					var tCITY_ID=$("#CITY_NAMES").val();
					var tCOUNTY_ID=$("#COUNTY_NAMES").val();
				}
				
				var tITEM_DATESS=$('#ITEM_DATESS').val();
				if(tITEM_DATESS==''){
					$.prompt('请输入开始日期',{buttons: {'确定': true}});
					return false;
				}
				var tITEM_DATESE=$('#ITEM_DATESE').val();
				if(tITEM_DATESE==''){
					$.prompt('请输入结束日期',{buttons: {'确定': true}});
					return false;
				}
				
				var tEXPORT_XXSTR='民航路单填报数据填报明细';
				
				var LINENAMESTR = '进出港,始发局,出港日期,航班号,终到局,总包数,安检退总包数,拉下总包数,实际接收总包数,核算信息';

				var url = '${ctx}/clfw/b09r02clfwquery!queryexportall.action';
	
				var params = {
							  EXREPORT:"民航路单填报数据填报明细#出港日期:" + tITEM_DATESS + "-" + tITEM_DATESE,
							  EXDATA:tPROVINCE_ID + " , " + tCITY_ID + " , " + tCOUNTY_ID + " , " + tITEM_DATESS + " , " + tITEM_DATESE,//逗号间隔需要加空格
							  LINEKEY:"expclfwhkldwhmx",
							  LINEMAIN:"expclfwhkldwhmx,数据表,民航路单填报数据填报明细",
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
					$("#exporttxt").html("   <a href='" + data.EXPORTALLPATH + "'>数据导出完毕,请点击下载!<a/>");
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
										数据填报>>民航路单填报数据填报明细
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
												<td class="listtablehead" width="150">
													行政区域:
												</td>
												<td class="js_left_txt" id="td_privince_content"  colspan="3">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onChange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty()" style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES" style="width:100"></select>
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													进/出港：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="CLFW_HBXXGL_CLSX" id="CLFW_HBXXGL_CLSX" value="0"  checked="checked">出港
													<input type="radio" name="CLFW_HBXXGL_CLSX" id="CLFW_HBXXGL_CLSX" value="1">进港
												</td>
												<td class="listtablehead" id="td_tpdistrict_content">
													开始时间:
													<input type="text" name="ITEM_DATESS" style="width: 100px; height:20px;" id="ITEM_DATESS" class="ac_input" size="100" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"/><font color="#FF0000">*</font>
												</td>
												<td class="listtablehead" id="td_tpdistrict_content">
													结束时间:
													<input type="text" name="ITEM_DATESE" style="width: 100px; height:20px;" id="ITEM_DATESE" class="ac_input" size="100" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"/><font color="#FF0000">*</font>
												</td>
											</tr>
											<tr  id="tr_exptjclfwhbwhalmx">
												<td colspan="4" class="listtablehead">
												<label id="cxsjs" style="color:#FF0000"></label>
												<label id="cxsje" style="color:#FF0000"></label>
												<label id="exporttxt" style="color:#FF0000"></label>
												</td>
											</tr>
											<tr>
												<td class="listtablehead"  colspan="4" align="right">
													<input id="query" type="button" value="查询" onClick="ajaxQueryPage(0)" />
													<input id="queryexport" onClick="ajaxQueryPageexport(0)" type="button" value="导出">
													<input id="queryclear" type="button" value="重置" onClick="ajaxQueryPage(0)" />
													<input id="fliphelpct" type="button" value="帮助"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead"  colspan="4">
													<font id="fliphelpcon" color="red">
														*无帮助信息
													</font>
												</td>
											</tr>
										</table>
									</div>
									<div id="tablelistdiv">
										<table class="listtable" width="1200"
											id="address_tablechu" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="20">
													民航路单填报数据填报(出港)
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft1" style='width:100'>
													始发局
												</td>
												<td align="left" class="listtableheadleft" style='width:100'>
													出港日期
												</td>
												<td align="left" class="listtableheadleft1" style='width:150'>
													航班号
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													终到局
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													总包数
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													安检退总包数
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													拉下总包数
												</td>
												<td align="left" class="listtableheadleft1" style='width:150'>
													核算信息
												</td>
											</thead>
										</table>
										<br>
										<table class="listtable" width="1200"
											id="address_tablejin" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="20">
													民航路单填报数据填报(进港)
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft1" style='width:100'>
													进港局
												</td>
												<td align="left" class="listtableheadleft" style='width:100'>
													出港日期
												</td>
												<td align="left" class="listtableheadleft1" style='width:150'>
													航班号
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													出港局
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													总包数
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													安检退总包数
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													拉下总包数
												</td>
												<td align="left" class="listtableheadleft1" style='width:120'>
													实际接收总包数
												</td>
												<td align="left" class="listtableheadleft1" style='width:150'>
													核算信息
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
