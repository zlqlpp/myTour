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
		<title>准班准点明细</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){

				if('${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2'){
					$("#PROVINCE_NAMES").prepend("<option selected='selected' value=''>全国</option>"); 
					$("#PROVINCE_NAMES").val('');
					$("#td_ghxuanzhe").show();
					$("#th_ghxuanzhe").show();

					$("#queryexport").show();
				}else{
					$("#td_ghxuanzhe").hide();
					$("#th_ghxuanzhe").hide();

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
				//backfresh('fresh');

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

				var tCLFWYLXXBZ_YLMC=$("#CLFWYLXXBZ_YLMC").val();
				var tCLFW_ORDERBY=$("#CLFW_ORDERBY").val();
				var tRAD_CLFWPC_SX=$("input[name='RAD_CLFWPC_SX'][checked]").val();
				var tCLFW_HBXXGL_CLSX=$("input[name='CLFW_HBXXGL_CLSX'][checked]").val();
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var url = '${ctx}/clfw/b09r02clfwquery!querytjclfwylxxbt.action';
			    var params = {
					    		PROVINCE_ID:tPROVINCE_ID,
								CITY_ID:tCITY_ID,
								COUNTY_ID:tCOUNTY_ID,
								CLFW_HBXXGL_CLSX:tCLFW_HBXXGL_CLSX,
								ITEM_DATESS:tITEM_DATESS,
								ITEM_DATESE:tITEM_DATESE,
								CLFWYLXXBZ_YLMC:tCLFWYLXXBZ_YLMC,
								CLFW_ORDERBY:tCLFW_ORDERBY,
								RAD_CLFWPC_SX:tRAD_CLFWPC_SX,
								FLAGNUM:320,
			                  	pageNo:pageNo
			                 };
			    jQuery.post(url, params, callbackajaxQueryPage, 'json');
			}
			function callbackajaxQueryPage(data){
				addquerytable(data);
			}
			function addquerytable(data){
				var resultdata=data.page.result;
				$('#address_table_list tbody').remove();
				$('#address_table_foot tbody').remove();
				if(resultdata.length>0){
					for(var i=0;i<resultdata.length;i++){
						
						var tr="<tr id='tr_query" + resultdata[i].CLFWYLXXB_SEQID + "'";

						if(resultdata[i].CLFWYLXXB_SFJGHSM == '2'){
							tr+=" style='background:blue' ";
						}else if(resultdata[i].CLFWYLXXB_SFJGHSM == '3'){
							tr+="  style='background:red' ";
						}


						tr+= " )>";
						
						if('${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2'){
							tr+=" <td align=right class=listtablebodyleft1 style='width:30px'><input type='checkbox' onclick=xuanze('tr_query" + resultdata[i].CLFWYLXXB_SEQID + "','rlCheckBox" + resultdata[i].CLFWYLXXB_SEQID + "') id='rlCheckBox" + resultdata[i].CLFWYLXXB_SEQID + "'  name='rlCheckBox' value='"+resultdata[i].CLFWYLXXB_SEQID + "'>";
							tr+="</td>";
						}
						tr+="<td valign=top align=left class=listtablebodyleft1  width=80>&nbsp;"+resultdata[i].CLFWYLXXB_CSRQ+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft2  width=120>&nbsp;"+resultdata[i].CLFWYLXXB_AYJID_STR+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=160>&nbsp;"+resultdata[i].CLFWYLXXBZ_YLMC+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].CLFWYLXXB_LSH+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=80>&nbsp;"+resultdata[i].CLFWYLXXB_CPH+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=120>&nbsp;"+resultdata[i].CLFWYLXXB_SFJID_STR+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].CLFWYLXXBZ_SJKCSJ+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].CLFWYLXXB_SJKCSJ+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=120>&nbsp;"+resultdata[i].CLFWYLXXB_SFJGHSMSTR+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=80>&nbsp;"+resultdata[i].CLFWYLXXB_SFJGH+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=120>&nbsp;"+resultdata[i].CLFWYLXXB_ZDJID_STR+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft1  width=100>&nbsp;"+resultdata[i].CLFWYLXXBZ_SJDDSJ+"</td>";
						tr+="<td valign=top align=left class=listtablebodyleft2  width=100>&nbsp;"+resultdata[i].CLFWYLXXB_SJDDSJ+"</td>";
						
						tr+="</tr>";
						$('#address_table_list').append(tr);
					}
					
					url="<tr><td colspan=15 class=listtablebodyleft2>" + data.page.ajaxUrl  + "</td></tr>";
					
					$('#address_table_foot').append(url);
					
				}else{
					var url="<tr><td colspan=15 class=listtablebodyleft2>找到 0 条记录</td>";
					$('#address_table_foot').append(url);
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

			function xuanze(st,id){
				if($("#" + id).attr("checked")){
					$("#" + st).css("background","#FEEBD0");
				}else{
					$("#" + st).css("background","#FBFBFF");
				}
			}
		  function queryAll(){
				var n = $("input[name=chooseName]:checked").length; 
				 if(n==0){ 
					 $("#address_table tbody input[name=rlCheckBox]").each(function(){
			                $(this).attr('checked',false);
			            }); 
					 $("#address_table tbody tr").each(function(){
						 $(this).css("background","#FBFBFF");
			            }); 
				 }else{ 
					$("#address_table tbody input[name=rlCheckBox]").each(function(){
				                $(this).attr('checked',true);
				           });
					$("#address_table tbody tr").each(function(){
								$(this).css("background","#FEEBD0");
			            });
				 } 
			}

		  function bjclfwylxx(tCLFWYLXXB_XSBZ){
				var c=$('#address_table_list tbody input[name=rlCheckBox]:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
					cv = cv+','+$(this).val();
				});
				if(cv==''){
					$.prompt('请选择邮路信息',{top: 1,buttons: {'确定': true}});
					return false;
				}
				var tTOTAL_ALL_VALUE=cv.substr(1,cv.length);
				var tCLFWYLXXB_SFJGH=$("#CLFWYLXXB_SFJGH").val();
				var tCLFWYLXXB_ZDJGH=$("#CLFWYLXXB_ZDJGH").val();
				$.prompt('该操作将邮路配置信息！',{
					top: 1,
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									CLFWYLXXB_SEQID:tTOTAL_ALL_VALUE,
									CLFWYLXXB_SFJGH:tCLFWYLXXB_SFJGH,
									CLFWYLXXB_ZDJGH:tCLFWYLXXB_ZDJGH,
									CLFWYLXXB_XSBZ:tCLFWYLXXB_XSBZ
							};

							var url = '${ctx}/clfw/b09cud02clfwconfig!bjclfwylxxclwt.action';
							
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

				var tCLFW_HBXXGL_CLSX=$("input[name='CLFW_HBXXGL_CLSX'][checked]").val();
				
				var tEXPORT_XXSTR='准班准点明细';
				
				var LINENAMESTR = '应填日期,组开局,邮路名称,流水号,车牌号,始发局,计划开出时间,实际开出时间,备注,审核意见,终到(途经)局,计划到达时间,实际到达时间';

				
				var url = '${ctx}/clfw/b09r02clfwquery!queryexportall.action';
	
				var params = {
							  EXREPORT:"准班准点明细",
							  EXDATA:tPROVINCE_ID + " , " + tCITY_ID + " , " + tCOUNTY_ID + " , " + tITEM_DATESS + " , " + tITEM_DATESE + " , " + tCLFW_HBXXGL_CLSX,//逗号间隔需要加空格
							  LINEKEY:"exptjclfwylxxbt",
							  LINEMAIN:"exptjclfwylxxbt,数据表,准班准点明细",
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
										数据填报 >>准班准点明细
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
													行政区域:
												</td>
												<td class="listtablehead" id="td_privince_content"   colspan="3">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onChange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty()" style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES" style="width:100"></select>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													邮路名称:
												</td>
												<td class="js_left_txt" id="td_common_tempid">
													<input type="text" name="CLFWYLXXBZ_YLMC" style="width: 200px; height:20px;" id="CLFWYLXXBZ_YLMC" class="ac_input" size="200"/>
												</td>
												<td class="listtablehead" width="80">
													数据类型:
												</td>
												<td class="listtablehead" id="td_privince_content">
													<input type="radio" name="CLFW_HBXXGL_CLSX" id="CLFW_HBXXGL_CLSX" value="1"  checked="checked">现用
													<input type="radio" name="CLFW_HBXXGL_CLSX" id="CLFW_HBXXGL_CLSX" value="0">历史
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													开始时间:
												</td>
												<td class="listtablehead" id="td_tpdistrict_content">
													<input type="text" name="ITEM_DATESS" style="width: 100px; height:20px;" id="ITEM_DATESS" class="ac_input" size="100" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"/><font color="#FF0000">*</font>
												</td>
												<td class="listtablehead">
													结束时间:
												</td>
												<td class="listtablehead" id="td_tpdistrict_content">
													<input type="text" name="ITEM_DATESE" style="width: 100px; height:20px;" id="ITEM_DATESE" class="ac_input" size="100" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"/><font color="#FF0000">*</font>
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													查询属性：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value=""  checked="checked">全部
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value="1">组开局
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value="2">始发局
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value="3">终到(途经)局
												</td>
												<td class="listtablehead">
													排序方式:
												</td>
												<td class="listtablehead">
												<select class="simple" id="CLFW_ORDERBY" style="width:200">
													<option selected='selected' value='1'>组开局-始发局-终到(途经)局</option>
													<option value='2'>始发局-终到(途经)局-组开局</option>
													<option value='3'>终到(途经)局-始发局-组开局</option>
													</select>
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
														无帮助介绍
													</font>
												</td>
											</tr>
										</table>
									</div>
									
									<br />
									<div id="tablelistdiv">
										<table class="listtable" width="1410"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="center" colspan="15">
													准班准点明细
												</th>
											</thead>
											<thead>
												<th id="th_ghxuanzhe" align="left"  colspan="15">
													<a href='#' onClick="bjclfwylxx(1);">&nbsp;清空发车信息</a>
													&nbsp;&nbsp;
													<a href='#' onClick="bjclfwylxx(2);">&nbsp;清空到达信息</a>
													&nbsp;&nbsp;
													审核意见：
													<select class="simple" id="CLFWYLXXB_SFJGH" style="width:200">
													<option selected='selected' value='1'>正常考核</option>
													<option value='2'>只参与发班不参与准点考核</option>
													<option value='3'>不参与发班,准点考核</option>
													</select>
													<a href='#' onClick="bjclfwylxx(3);">&nbsp;配置审核意见</a>
												</th>
											</thead>
											</tr>
											<thead>
												<td id="td_ghxuanzhe" align="right" class="listtableheadleft" style='width:30px'>
													&nbsp;
												</td>

												<td align="left" class="listtableheadleft1" style='width:80'>
													应填日期
												</td>
												<td align="left" class="listtableheadleft1" style='width:120'>
													组开局
												</td>
												<td align="left" class="listtableheadleft" style='width:160'>
													邮路名称
												</td>
												<td align="left" class="listtableheadleft" style='width:100'>
													流水号
												</td>
												<td align="left" class="listtableheadleft1" style='width:80'>
													车牌号
												</td>
												<td align="left" class="listtableheadleft1" style='width:120'>
													始发局
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													计划开出时间
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													实际开出时间
												</td>
												<td align="left" class="listtableheadleft1" style='width:120'>
													备注
												</td>
												<td align="left" class="listtableheadleft1" style='width:80'>
													审核意见
												</td>
												<td align="left" class="listtableheadleft1" style='width:120'>
													终到(途经)局
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													计划到达时间
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													实际到达时间
												</td>
											</thead>
										</table>
										<div style='height:300px;overflow:auto;overflow-x:hidden;'>
										<table class="listtable" width="1410" id="address_table_list" cellpadding="0" cellspacing="0">
											
										</table>
										</div>
										<table class="listtable" width="1410" id="address_table_foot" cellpadding="0" cellspacing="0">
											
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
