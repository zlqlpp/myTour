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
		<title>民航路单填报数据填报</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){

				if('${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2'){
					//$("#aQRCGXX").hide();
					//$("#aQRJGXX").hide();
	
					$("#aQXCGXX").show();
					$("#aQXJGXX").show();
				}else{
					$("#aQRCGXX").show();
					$("#aQRJGXX").show();
	
					$("#aQXCGXX").hide();
					$("#aQXJGXX").hide();
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
				
				//$("#fliphelpcon").hide();
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

				var url = '${ctx}/clfw/b09r02clfwquery!queryclfwhkldwh.action';
			    var params = {
			    		PROVINCE_ID:tPROVINCE_ID,
						CITY_ID:tCITY_ID,
						COUNTY_ID:tCOUNTY_ID,
						ITEM_DATESS:tITEM_DATESS,
						ITEM_DATESE:tITEM_DATESE,
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
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>";
							tr+="<input name='inputCLFWPC_SEQID0' type='hidden' value='" + resultdata[i].CLFW_HBXXLDBW_SEQID + "' />";
							tr+="&nbsp;"+resultdata[i].CLFW_HBXXYL_SFJIDSTR+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_FYRQSJ+"</td>";
							//tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXYL_YLDM+"</td>";
							//tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_LDHM+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:150'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_HBCC+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXYL_ZDJIDSTR+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_ZBS+"</td>";

							tr+="<td align=left class=listtablebodyleft1 style='width:150'>";
							
							tr+='<input type="text" id="CLFW_HBXXLDBW_AJTHZBS' + resultdata[i].CLFW_HBXXLDBW_SEQID + '" class="ac_input" value="' + resultdata[i].CLFW_HBXXLDBW_AJTHZBS + '" onFocus="this.select();"/>';
							
							tr+="</td>";

							tr+="<td align=left class=listtablebodyleft1 style='width:150'>";
							
							tr+='<input type="text" id="CLFW_HBXXLDBW_LXZBS' + resultdata[i].CLFW_HBXXLDBW_SEQID + '" class="ac_input" value="' + resultdata[i].CLFW_HBXXLDBW_LXZBS + '" onFocus="this.select();"/>';
							
							tr+="</td>";
							
							tr+="<td align=left class=listtablebodyleft2 style='width:100'>&nbsp;";

							if(	resultdata[i].CLFW_HBXXLDBW_SFJGH == '1' ){
								
								tr+="信息已确认";
								
							}else{
								
								tr+="<a href='#' id='aCLFWHBXXLDBW" + resultdata[i].CLFW_HBXXLDBW_SEQID + "' onClick='upclfwhkldwh(" + resultdata[i].CLFW_HBXXLDBW_SEQID + "," + resultdata[i].CLFW_HBXXGL_XSBZ + ");'>";
								
								if(	resultdata[i].OPE_INSERTTIME.length >0 ){
									tr+="修改信息";
								}else{
									tr+="维护信息";	
								}
								
							}
							
							tr+="</a>";

							
							tr+="</td>";
							
							tr+="</tr>";
							$('#address_tablechu').append(tr);
						}
						else if	(resultdata[i].CLFW_HBXXGL_XSBZ=='1'){
							
							tr="<tr>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>";
							tr+="<input name='inputCLFWPC_SEQID1' type='hidden' value='" + resultdata[i].CLFW_HBXXLDBW_SEQID + "' />";
							tr+="&nbsp;"+resultdata[i].CLFW_HBXXYL_SFJIDSTR+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_FYRQSJ+"</td>";
							//tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXYL_YLDM+"</td>";
							//tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_LDHM+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:150'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_HBCC+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXYL_ZDJIDSTR+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_ZBS+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:150'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_AJTHZBS+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:150'>&nbsp;"+resultdata[i].CLFW_HBXXLDBW_LXZBS+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>";
							
							tr+='<input type="text" id="CLFW_HBXXLDBW_SSZBS' + resultdata[i].CLFW_HBXXLDBW_SEQID + '" class="ac_input" value="' + resultdata[i].CLFW_HBXXLDBW_SSZBS + '" onFocus="this.select();"/>';
							
							tr+="</td>";

							tr+="<td align=left class=listtablebodyleft2 style='width:100'>&nbsp;";

							if(	resultdata[i].CLFW_HBXXLDBW_ZDJGH == '1' ){
								
								tr+="信息已确认";
								
							}else{
								
								tr+="<a href='#' id='aCLFWHBXXLDBW" + resultdata[i].CLFW_HBXXLDBW_SEQID + "' onClick='upclfwhkldwh(" + resultdata[i].CLFW_HBXXLDBW_SEQID + "," + resultdata[i].CLFW_HBXXGL_XSBZ + ");'>";
								
								if(	resultdata[i].ZDJOPE_INSERTTIME.length >0 ){
									tr+="修改信息";
								}else{
									tr+="维护信息";	
								}
								
							}

							tr+="</a>";

							
							tr+="</td>";

							tr+="</tr>";

							$('#address_tablejin').append(tr);
						}
						
						
					}
					var url="<tr  style='display:none'><td colspan=20 class=listtablebodyleft2>"+data.page.ajaxUrl+"</td></tr>";
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
			function upclfwhkldwh(tCLFW_HBXXLDBW_SEQID,tCLFW_HBXXGL_XSBZ){

				var regEx = /[^0-9]+/gi;

				if(tCLFW_HBXXGL_XSBZ == '0'){

					var tCLFW_HBXXLDBW_AJTHZBS=$('#CLFW_HBXXLDBW_AJTHZBS'+tCLFW_HBXXLDBW_SEQID).val();
					var tCLFW_HBXXLDBW_LXZBS=$('#CLFW_HBXXLDBW_LXZBS'+tCLFW_HBXXLDBW_SEQID).val();
				  	
					if(tCLFW_HBXXLDBW_AJTHZBS.length>10){
						$.prompt('安检退回总包字符大于10',{buttons: {'确定': true}});
						return false;
					}

					if(tCLFW_HBXXLDBW_AJTHZBS.length>0 && regEx.test(tCLFW_HBXXLDBW_AJTHZBS)){
						$.prompt('安检退回总包请填入数字',{buttons: {'确定': true}});
						return false;
					}

					if(tCLFW_HBXXLDBW_LXZBS.length>10){
						$.prompt('拉下总包字符大于10',{buttons: {'确定': true}});
						return false;
					}

					if(tCLFW_HBXXLDBW_LXZBS.length>0 && regEx.test(tCLFW_HBXXLDBW_LXZBS)){
						$.prompt('拉下总包请填入数字',{buttons: {'确定': true}});
						return false;
					}
	
						
				}else if(tCLFW_HBXXGL_XSBZ == '1'){
					
					var tCLFW_HBXXLDBW_SSZBS=$('#CLFW_HBXXLDBW_SSZBS'+tCLFW_HBXXLDBW_SEQID).val();
				  	
					if(tCLFW_HBXXLDBW_SSZBS.length>10){
						$.prompt('实接总包字符大于10',{buttons: {'确定': true}});
						return false;
					}

					if(tCLFW_HBXXLDBW_SSZBS.length>0 && regEx.test(tCLFW_HBXXLDBW_SSZBS)){
						$.prompt('实接总包请填入数字',{buttons: {'确定': true}});
						return false;
					}
				}

				
		  		var params = {
		  			CLFW_HBXXLDBW_SEQID:tCLFW_HBXXLDBW_SEQID,
		  			CLFW_HBXXGL_XSBZ:tCLFW_HBXXGL_XSBZ,
		  			CLFW_HBXXLDBW_AJTHZBS:tCLFW_HBXXLDBW_AJTHZBS,
		  			CLFW_HBXXLDBW_LXZBS:tCLFW_HBXXLDBW_LXZBS,
		  			CLFW_HBXXLDBW_SSZBS:tCLFW_HBXXLDBW_SSZBS
				};
				var url = '${ctx}/clfw/b09cud02clfwconfig!upclfwhkldwh.action';
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				jQuery.post(url, params, callajaxAdd, 'json');
			}
		  function callajaxAdd(data){
				$('#queryAll').attr('checked',false);
				//$.prompt(data.saveMessage,{buttons: {'确定': true}});
				$("#label_shuju").text(data.saveMessage);
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
				//backfresh("fresh");
			}

		  function bjclfwhkldwh(tCLFW_HBXXGL_XSBZ){
				var c=''
				if(tCLFW_HBXXGL_XSBZ == '1' || tCLFW_HBXXGL_XSBZ == '3'){
					c = $('#address_tablechu tbody input[name=inputCLFWPC_SEQID0]');
				}else if(tCLFW_HBXXGL_XSBZ == '2' || tCLFW_HBXXGL_XSBZ == '4'){
					c = $('#address_tablejin tbody input[name=inputCLFWPC_SEQID1]');
				}
				var cv=''; //定义变量
				c.each(function(i){        //循环拼装被选中项的值
					cv = cv+','+$(this).val();
				});
				if(cv==''){
					$.prompt('请选择航路信息',{top: 1,buttons: {'确定': true}});
					return false;
				}
				var tTOTAL_ALL_VALUE=cv.substr(1,cv.length);
				$.prompt('该操作将确认航路配置信息,确认后不能修改！',{
					top: 1,
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									CLFW_HBXXLDBW_SEQID:tTOTAL_ALL_VALUE,
									CLFW_HBXXGL_XSBZ:tCLFW_HBXXGL_XSBZ
							};

							var url = '${ctx}/clfw/b09cud02clfwconfig!bjupclfwhkldwh.action';
							
							if(!(bro.msie && bro.version >=8)){
								$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
							}else{
								$("#nodata").show();
								$("#yesdata").hide();
							}
							jQuery.post(url, params, callajaxbjAdd, 'json');
						}
					}
				});
			}
		  function callajaxbjAdd(data){
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
										数据填报>>民航路单填报数据填报
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
													开始时间:
												</td>
												<td class="js_left_txt" id="td_tpdistrict_content">
													<input type="text" name="ITEM_DATESS" style="width: 100px; height:20px;" id="ITEM_DATESS" class="ac_input" size="100" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"/><font color="#FF0000">*</font>
												</td>
												<td class="listtablehead">
													结束时间:
												</td>
												<td class="js_left_txt" id="td_tpdistrict_content">
													<input type="text" name="ITEM_DATESE" style="width: 100px; height:20px;" id="ITEM_DATESE" class="ac_input" size="100" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"/><font color="#FF0000">*</font>
												</td>
											</tr>
											<tr>
												<td class="listtablehead"  colspan="2" align="left">
													<label id="label_shuju" style="color:#FF0000"></label>
												</td>
												<td class="listtablehead"  colspan="2" align="right">
													<input id="query" type="button" value="查询" onClick="ajaxQueryPage(0)" />
													<input id="queryclear" type="button" value="重置" onClick="ajaxQueryPage(0)" />
													<input id="fliphelpct" type="button" value="帮助"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead"  colspan="4">
													<font id="fliphelpcon" color="red">
														*【民航路单填报数据】是在接收网运路单信息基础上，改进了【民航航班执行情况填报】操作方法，在路单信息的上填报数据即可
														<br>*确认航路配置信息,确认后不能修改,只有总部管理员才能解锁
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
												<th id="th_ghxuanzhe" align="left"  colspan="15">
													<a id="aQRCGXX" href='#' onClick="bjclfwhkldwh(1);">&nbsp;确认出港信息</a>
													&nbsp;&nbsp;
													<a id="aQXCGXX" href='#' onClick="bjclfwhkldwh(3);">&nbsp;解锁出港信息</a>
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
												<td align="left" class="listtableheadleft1" style='width:150'>
													安检退回总包数(选填)
												</td>
												<td align="left" class="listtableheadleft1" style='width:150'>
													民航拉下总包数(选填)
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													操作
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
												<th id="th_ghxuanzhe" align="left"  colspan="15">
													<a id="aQRJGXX" href='#' onClick="bjclfwhkldwh(2);">&nbsp;确认进港信息</a>
													&nbsp;&nbsp;
													<a id="aQXJGXX" href='#' onClick="bjclfwhkldwh(4);">&nbsp;解锁进港信息</a>
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
												<td align="left" class="listtableheadleft1" style='width:150'>
													安检退回总包数
												</td>
												<td align="left" class="listtableheadleft1" style='width:150'>
													民航拉下总包数
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													实际接收总包数
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													操作
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
