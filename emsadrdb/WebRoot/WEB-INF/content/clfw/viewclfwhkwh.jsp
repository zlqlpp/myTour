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
		<title>民航航班执行情况填报</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){

				if('${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2'){
					$("#PROVINCE_NAMES").prepend("<option selected='selected' value=''>全国</option>"); 
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

				$("#CLFW_HBXXGL_CSRQCG").val(sj);

				$("#CLFW_HBXXGL_CSRQJG").val(sj);
				
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

				var url = '${ctx}/clfw/b09r02clfwquery!queryclfwhkwh.action';
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

							tr="<tr id='tr_query" + resultdata[i].CLFW_HBXXGL_SEQID + "' )>";
							tr+=" <td align=right class=listtablebodyleft1 style='width:30px'><input type='checkbox' onclick=xuanze('tr_query" + resultdata[i].CLFW_HBXXGL_SEQID + "','rlCheckBox" + resultdata[i].CLFW_HBXXGL_SEQID + "') id='rlCheckBox" + resultdata[i].CLFW_HBXXGL_SEQID + "'  name='rlCheckBox' value='"+resultdata[i].CLFW_HBXXGL_SEQID + "'>";
							tr+="</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].CLFW_HBXXGL_SFJIDSTR+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].CLFW_HBXXGL_CSRQ+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].CLFW_HBXXGL_HBH+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].CLFW_HBXXGL_ZDJIDSTR+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].CLFW_HBXXGL_ZBS+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].CLFW_HBXXGL_ZL+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].CLFW_HBXXGL_LDLSH+"</td>";
							tr+="<td align=left class=listtablebodyleft2 style='width:100px'>&nbsp;";

							tr+="<a href='#' onClick='upclfwhkwhcg(" + resultdata[i].CLFW_HBXXGL_SEQID + "," + '"UPDATE"' + ");'>修改</a>";

						
							tr+="&nbsp;<a href='#' onClick='delclfwhkwh(" + resultdata[i].CLFW_HBXXGL_SEQID + ");'>删除</a>";
							
							tr+="</td>";
							tr+="</tr>";
							$('#address_tablechu').append(tr);
						}
						else if	(resultdata[i].CLFW_HBXXGL_XSBZ=='1'){
							
							tr="<tr id='tr_query" + resultdata[i].CLFW_HBXXGL_SEQID + "' )>";
							tr+=" <td align=right class=listtablebodyleft1 style='width:30px'><input type='checkbox' onclick=xuanze('tr_query" + resultdata[i].CLFW_HBXXGL_SEQID + "','rlCheckBox" + resultdata[i].CLFW_HBXXGL_SEQID + "') id='rlCheckBox" + resultdata[i].CLFW_HBXXGL_SEQID + "'  name='rlCheckBox' value='"+resultdata[i].CLFW_HBXXGL_SEQID + "'>";
							tr+="</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].CLFW_HBXXGL_SFJIDSTR+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].CLFW_HBXXGL_CSRQ+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].CLFW_HBXXGL_HBH+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].CLFW_HBXXGL_ZDJIDSTR+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].CLFW_HBXXGL_LDZBS+"</td>";
							tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].CLFW_HBXXGL_SSZBS+"</td>";
							tr+="<td align=left class=listtablebodyleft2 style='width:100px'>&nbsp;";
							
							tr+="<a href='#' onClick='upclfwhkwhjg(" + resultdata[i].CLFW_HBXXGL_SEQID + "," + '"UPDATE"' + ");'>修改</a>";

							tr+="&nbsp;<a href='#' onClick='delclfwhkwh(" + resultdata[i].CLFW_HBXXGL_SEQID + ");'>删除</a>";
							
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
		  function addclfwhkwhcg(){
				var value = window.showModalDialog("${ctx}/clfw/b09r01clfwview!addclfwhkwhcg.action?FLAG=ADD","Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
		  function addclfwhkwhjg(){
				var value = window.showModalDialog("${ctx}/clfw/b09r01clfwview!addclfwhkwhjg.action?FLAG=ADD","Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
		  function upclfwhkwhcg(tCLFW_HBXXGL_SEQID,tFlag){
			  var value = window.showModalDialog("${ctx}/clfw/b09r01clfwview!upclfwhkwhcg.action?FLAG=" + tFlag + "&SEQID=" + tCLFW_HBXXGL_SEQID,"Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
		  function upclfwhkwhjg(tCLFW_HBXXGL_SEQID,tFlag){
			  var value = window.showModalDialog("${ctx}/clfw/b09r01clfwview!upclfwhkwhjg.action?FLAG=" + tFlag + "&SEQID=" + tCLFW_HBXXGL_SEQID,"Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
		  function delclfwhkwh(tCLFW_HBXXGL_SEQID){
			  $.prompt('该操作将删除进出港信息！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v,m){
						if(v){
							var params = {
									CLFW_HBXXGL_SEQID:tCLFW_HBXXGL_SEQID
							};
							var url = '${ctx}/clfw/b09cud02clfwconfig!delclfwhkwh.action';
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
		  function bjclfwhkwhcg(){
				var c=$('#address_tablechu tbody input[name=rlCheckBox]:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
					cv = cv+','+$(this).val();
				});
				if(cv==''){
					$.prompt('请选择要复制航班信息',{buttons: {'确定': true}});
					return false;
				}
				var tTOTAL_ALL_VALUE=cv.substr(1,cv.length);
				var tCLFW_HBXXGL_CSRQ=$('#CLFW_HBXXGL_CSRQCG').val();	
				if(tCLFW_HBXXGL_CSRQ==''){
					$.prompt('请配置复制出港日期',{buttons: {'确定': true}});
					return false;
				}
				$.prompt('该操作将复制所选航班信息到'+tCLFW_HBXXGL_CSRQ,{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									CLFW_HBXXGL_SEQID:tTOTAL_ALL_VALUE,
									CLFW_HBXXGL_CSRQ:tCLFW_HBXXGL_CSRQ
							};
							var url = '${ctx}/clfw/b09cud02clfwconfig!bjclfwwhkwhcg.action';
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


		  function bjclfwhkwhjg(){
				var c=$('#address_tablejin tbody input[name=rlCheckBox]:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
					cv = cv+','+$(this).val();
				});
				if(cv==''){
					$.prompt('请选择要复制航班信息',{buttons: {'确定': true}});
					return false;
				}
				var tTOTAL_ALL_VALUE=cv.substr(1,cv.length);
				var tCLFW_HBXXGL_CSRQ=$('#CLFW_HBXXGL_CSRQJG').val();	
				if(tCLFW_HBXXGL_CSRQ==''){
					$.prompt('请配置复制进港日期',{buttons: {'确定': true}});
					return false;
				}
				$.prompt('该操作将复制所选航班信息到'+tCLFW_HBXXGL_CSRQ,{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									CLFW_HBXXGL_SEQID:tTOTAL_ALL_VALUE,
									CLFW_HBXXGL_CSRQ:tCLFW_HBXXGL_CSRQ
							};
							var url = '${ctx}/clfw/b09cud02clfwconfig!bjclfwwhkwhjg.action';
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
			
		  function xuanze(st,id){
				if($("#" + id).attr("checked")){
					$("#" + st).css("background","#FEEBD0");
				}else{
					$("#" + st).css("background","#FBFBFF");
				}
			}
		  function queryAll(objname){
				var n = $("input[name=chooseName]:checked").length; 
				 if(n==0){ 
					 $("#"+objname+" tbody input[name=rlCheckBox]").each(function(){
			                $(this).attr('checked',false);
			            }); 
					 $("#"+objname+" tbody tr").each(function(){
						 $(this).css("background","#FBFBFF");
			            }); 
				 }else{ 
					$("#"+objname+" tbody input[name=rlCheckBox]").each(function(){
				                $(this).attr('checked',true);
				           });
					$("#"+objname+" tbody tr").each(function(){
								$(this).css("background","#FEEBD0");
			            });
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
										数据填报>>民航航班执行情况填报
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
												<td class="listtablehead"  align="left"   colspan="2">
												<a href='#' onClick="addclfwhkwhcg();">&nbsp;添加出港信息</a>
												&nbsp;&nbsp;&nbsp;
												<a href='#' onClick="addclfwhkwhjg();">&nbsp;添加进港信息</a>
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
														*【复制数据】为节省添加前一天同样航班全部维护时间，可以查询前一天数据，然后复制到当天
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
													民航航班执行情况填报(出港)
												</th>
												<thead>
												<th align="left"  colspan="12">
													复制日期：
													<input type="text" name="CLFW_HBXXGL_CSRQCG" style="width: 100px; height:20px;" id="CLFW_HBXXGL_CSRQCG" class="ac_input" size="100"  onClick="WdatePicker({dateFmt:'yyyyMMdd'})"/>
													<a href='#' onClick="bjclfwhkwhcg();">&nbsp;复制出港数据</a>
												</th>
											</thead>
											</thead>
											<thead>
												<td align="right" class="listtableheadleft" style='width:30px'>
													<input id="queryAll" name="chooseName" type="checkbox" onClick="queryAll('address_tablechu')"/>
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													始发局
												</td>
												<td align="left" class="listtableheadleft" style='width:100'>
													出港日期
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													航班号
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													终到局
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													总包数
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													重量（公斤）
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													路单流水号
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
													民航航班执行情况填报(进港)
												</th>
											</thead>
											<thead>
												<th align="left"  colspan="12">
													复制日期：
													<input type="text" name="CLFW_HBXXGL_CSRQJG" style="width: 100px; height:20px;" id="CLFW_HBXXGL_CSRQJG" class="ac_input" size="100"  onClick="WdatePicker({dateFmt:'yyyyMMdd'})"/>
													<a href='#' onClick="bjclfwhkwhjg();">&nbsp;复制进港数据</a>
												</th>
											</thead>
											<thead>
												<td align="right" class="listtableheadleft" style='width:30px'>
													<input id="queryAll" name="chooseName" type="checkbox" onClick="queryAll('address_tablejin')"/>
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													始发局
												</td>
												<td align="left" class="listtableheadleft" style='width:100'>
													出港日期
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													航班号
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													终到局
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													路单总包数
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													实收总包数
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
