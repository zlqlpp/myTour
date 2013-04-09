<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/address/addr.jsp"%>
<%@ include file="/scripts/My97DatePicker/My97DatePicker.jsp"%>
<html>

	<head>
		<title>进口局批区批段-直连南京(历史)</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var tpageNo=1;
		var bro=$.browser;
			$(document).ready(function(){
				$("#DT_PK_CODE").append("<option selected='selected' value=''>请选择</option>");
				$("#DM_PK_CODE").append("<option selected='selected' value=''>请选择</option>");
				$("#PG_PK_CODE").append("<option selected='selected' value=''>请选择</option>");
				viewcity();
				viewcityg();
				yqls();
			})
			//展示市
			function viewcity(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var url = '${ctx}/address/b04r03addroption!citys.action';
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
					if('${EMS_USER.usCityOffice}'.substr(4,2)=='00' || '${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2' || '${EMS_USER.rulLevel}' == '5'){
						$("#COUNTY_NAMES").prepend("<option selected='selected' value=''>请选择</option>");
					}
			    	for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
					//backfresh('fresh');
					viewDT();
			    }else{
					$('#CITY_NAMES option').remove();
					if('${EMS_USER.rulLevel}'=='0' || '${EMS_USER.rulLevel}'=='2' || '${EMS_USER.rulLevel}'=='5'){
						$("#CITY_NAMES").prepend("<option selected='selected' value=''>请选择</option>");
					}
					for(var i=0;i<d.length;i++){
						if('${EMS_USER.usCityOffice}'==d[i].DISTRICT_CODE){
							$("#CITY_NAMES").append("<option selected='selected' value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
						}else{
							$("#CITY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
						}
					}
					viewcounty();
			    }
			}
			
			function viewcounty(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 			
				if(!isMunicipalities(tPROVINCE_NAME)){
					var tCITY_NAME=$("#CITY_NAMES").find("option:selected").text(); 
					if(tCITY_NAME!='请选择'){
						var url = '${ctx}/address/b04r03addroption!countys.action';
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
				//if('${EMS_USER.usCityOffice}'.substr(4,2)=='00' || '${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2' || '${EMS_USER.rulLevel}' == '5' || '${EMS_USER.rulLevel}' == '10'){
						$("#COUNTY_NAMES").prepend("<option selected='selected' value=''>请选择</option>");
				//}
				for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
				}
				//backfresh('fresh');
				viewDT();
			}

			//展示市
			function viewcityg(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMESG").find("option:selected").text(); 
				if('${EMS_USER.rulLevel}' == '10' || '${EMS_USER.rulLevel}' == '15' || '${EMS_USER.rulLevel}' == '20'){
					var url = '${ctx}/address/b04r03addroption!citysno.action';
				}else{
					var url = '${ctx}/address/b04r03addroption!citysnull.action';
				}
				var params = {
					PROVINCE_NAME: tPROVINCE_NAME
				 };
				if(isMunicipalities(tPROVINCE_NAME)){
			    	$('#CITY_NAMESG').hide();
					$('#CITY_NAMESG option').remove();
			    }else{
					$('#CITY_NAMESG').show();
				}
				jQuery.post(url, params, callbackcityg, 'json');
			}
			function callbackcityg(data){
				var d=data.districtBeans;
				var tPROVINCE_NAME=$("#PROVINCE_NAMESG").find("option:selected").text(); 
				if(isMunicipalities(tPROVINCE_NAME)){
					$('#COUNTY_NAMESG option').remove();
					$("#COUNTY_NAMESG").prepend("<option selected='selected' value=''>请选择</option>");
			    	for(var i=0;i<d.length;i++){
						$("#COUNTY_NAMESG").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
			    }else{
					$('#CITY_NAMESG option').remove();
					$("#CITY_NAMESG").prepend("<option selected='selected' value=''>请选择</option>");
					for(var i=0;i<d.length;i++){
						$("#CITY_NAMESG").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
					viewcountyg();
			    }
			}
			
			function viewcountyg(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMESG").find("option:selected").text(); 			
				if(!isMunicipalities(tPROVINCE_NAME)){
					var tCITY_NAME=$("#CITY_NAMESG").find("option:selected").text(); 
					if(tCITY_NAME!='请选择'){
						var url = '${ctx}/address/b04r03addroption!countysnull.action';
						var params = {
							CITY_NAME: tCITY_NAME
						 };
						jQuery.post(url, params, callbackcountyg, 'json');
					}
				}
			}
			function callbackcountyg(data){
				$('#COUNTY_NAMESG option').remove();
				var d=data.districtBeans;
				$("#COUNTY_NAMESG").prepend("<option selected='selected' value=''>请选择</option>");
				for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMESG").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
				}
			}
			
			function ajaxQueryPage(pageNo){
				$("input[name='PK_REMARK']").get(0).checked = true;
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
				var tITEMNO=$("#ITEMNO").val();
				var tREC_ALLADDR=$("#REC_ALLADDR").val();
				var tISDT=$("input[name='ISDT'][checked]").val(); 
				var tISPG=$("input[name='ISPG'][checked]").val(); 
				var tIS_DISTRI=$("input[name='IS_DISTRI'][checked]").val(); 
				var tISSR_FLAG=$("input[name='ISSR_FLAG'][checked]").val(); 
				var tISPOSTSEG=$("input[name='ISPOSTSEG'][checked]").val(); 
				var tIS_YOUDIZHI=$("input[name='IS_YOUDIZHI'][checked]").val(); 
				var tISWH=$("input[name='ISWH'][checked]").val(); 
				var tISPOSTDISTTYPE=$("input[name='ISPOSTDISTTYPE'][checked]").val(); 
				var tITEM_DATESS=$('#ITEM_DATESS').val();
				var tITEM_DATESE=$('#ITEM_DATESE').val();
				tITEM_DATESS = tITEM_DATESS.replace(" ","").replace("-","").replace("-","");
				tITEM_DATESE = tITEM_DATESE.replace(" ","").replace("-","").replace("-","");
				var url = '${ctx}/address/b04r02addrquery!queryrgpqpdn.action';
			    var params = {
			    				ISPOSTDISTTYPE:tISPOSTDISTTYPE,
					    		ITEM_DATESS:tITEM_DATESS,
								ITEM_DATESE:tITEM_DATESE,
								PROVINCE_ID:tPROVINCE_ID,
								CITY_ID:tCITY_ID,
								COUNTY_ID:tCOUNTY_ID,
								ITEMNO:tITEMNO,
								REC_ALLADDR:tREC_ALLADDR,
								ISDT:tISDT,
								ISPG:tISPG,
								ISWH:tISWH,
								IS_DISTRI:tIS_DISTRI,
								ISSR_FLAG:tISSR_FLAG,
								ISPOSTSEG:tISPOSTSEG,
								IS_YOUDIZHI:tIS_YOUDIZHI,
			                  	pageNo:pageNo
			                 };
			    jQuery.post(url, params, callbackajaxQueryPage, 'json');
			}
			function callbackajaxQueryPage(data){
				//添加table
				addquerytable(data);
			}
			function addquerytable(data){
				$('#ISWHQUERY').val($("input[name='ISWH'][checked]").val());
				var resultdata=data.page.result;
				tpageNo=data.page.pageNo;
				$('#address_table_list tbody').remove();
				$('#address_table_foot tbody').remove();
				if(resultdata.length>0){
					for(var i=0;i<resultdata.length;i++){
						var tr="<tr id='tr_query" + resultdata[i].SEQID + "' onclick=xuanze('tr_query" + resultdata[i].SEQID + "','rlCheckBox" + resultdata[i].SEQID + "')>";
						tr+=" <td align=right class=listtablebodyleft1 style='width:30px'><input type='checkbox' onclick=xuanze('tr_query" + resultdata[i].SEQID + "','rlCheckBox" + resultdata[i].SEQID + "') id='rlCheckBox" + resultdata[i].SEQID + "'  name='rlCheckBox' value='"+resultdata[i].SEQID + "'>";
						tr+="</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].COLLECT_DATE+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].ITEMNO+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:200px'>";
						
						if(resultdata[i].ADDR_FLAG == 1){
							//tr+="<a href='#' onClick='upRgming(" + resultdata[i].SEQID + ");'>批注</a>&nbsp;<br>";
							tr+="街道地址: " + resultdata[i].REC_ALLADDR;
						}else{
							//tr+="<a href='#' onClick='upRgming(" + resultdata[i].SEQID + ");'>批注</a>&nbsp;<br>";
							tr+="街道地址: " + resultdata[i].REC_STREET;
							tr+="<br>机构地址: " + resultdata[i].REC_ORG;
						}
						tr+="</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].ORG_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].TOTAL_DISTRICT_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].POSTDIST_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].POSTSEG_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].DT_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].DM_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].PG_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:100px'>&nbsp;"+resultdata[i].PK_REMARK+"</td>";
						tr+="</tr>";
						$('#address_table_list').append(tr);
					}
					var url="<tr><td colspan=12 class=listtablebodyleft2>"
					+data.page.ajaxUrl+	
					"<input type='text' name='pagenum' style='width:50px;height:20px;' id='pagenum' class='ac_input'/><a id='pagego' href='#' onClick='pagecx(" + data.page.totalCount + "," + data.page.pageSize + ")' style='font-size:12px'>GO</a>"+
					"</td></tr>";
					
					$('#address_table_foot').append(url);
				}else{
					var url="<tr><td colspan=12 class=listtablebodyleft2>找到 0 条记录</td></tr>";
					$('#address_table_foot').append(url);
				}
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
			}
			function xuanze(st,id){
				if($("#" + id).attr("checked")){
					$("#" + st).css("background","#FBFBFF");
					$("#" + id).attr("checked",false);
				}else{
					$("#" + st).css("background","#FEEBD0");
					$("#" + id).attr("checked",true);
				}
			}
			function upRgming(tSEQID,tADDR_FLAG,tREC_ALLADDRSTREET){
				var value = window.showModalDialog("${ctx}/address/b04cud01addrview!uprgming.action?FLAG=UPDATE&SEQID=" + tSEQID,"Code","dialogWidth:600px;dialogHeight:900px;resizable=1,scrollbars=auto");				
				backfresh(value);
			}
			function backfresh(value){
				if(value == "fresh") {
					ajaxQueryPage(tpageNo);
				}
			}
			function queryAll(){
				var n = $("input[name=chooseName]:checked").length; 
				 if(n==0){ 
					 $("#address_table_list tbody input[name=rlCheckBox]").each(function(){
			                $(this).attr('checked',false);
			            }); 
				 }else{ 
					$("#address_table_list tbody input[name=rlCheckBox]").each(function(){
				                $(this).attr('checked',true);
				           });
				 } 
			}
			function viewDT(){
				var tCITY_CODE=$("#PROVINCE_NAMES").val();	
				if(isMunicipalitiesID(tCITY_CODE.substr(0,2))){
					var url = '${ctx}/address/b04r03addroption!dts.action';
				}else{
					if($("#CITY_NAMES").val().length>0){
						tCITY_CODE=$("#CITY_NAMES").val();
					}
					var url = '${ctx}/address/b04r03addroption!dtsfromdisp.action';
				}
				var params = {
					CITY_CODE: tCITY_CODE
				 };
				jQuery.post(url, params, callbackDT, 'json');
			}
			function callbackDT(data){
				$('#DT_PK_CODE option').remove();
				var d=data.cpBeans;
				$("#DT_PK_CODE").append("<option value=''>请选择</option>"); 
				if(d.length>0){
					for(var i=0;i<d.length;i++){
						$("#DT_PK_CODE").append("<option value='" + d[i].DT_PK_CODE + "'>" + d[i].DT_NAME + "</option>");
					}
					if(d.length==1){
						$("#DT_PK_CODE").val(d[0].DT_PK_CODE);
					}
					if($("#DT_PK_CODE").val().length>1){
						viewDM();
					}else{
						$('#DM_PK_CODE option').remove();
						$("#DM_PK_CODE").append("<option value=''>请选择</option>"); 
						$('#PG_PK_CODE option').remove();
						$("#PG_PK_CODE").append("<option value=''>请选择</option>"); 
					}
				}
			}
			
			function viewDM(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				if(isMunicipalities(tPROVINCE_NAME)){
					var tCITY_ID=tPROVINCE_ID;
			    }else{
					var tCITY_ID=$("#CITY_NAMES").val();
				}
				var tDT_PK_CODE=$("#DT_PK_CODE").val();
				if(tDT_PK_CODE.length>0){
					var url = '${ctx}/address/b04r03addroption!dms.action';
					var params = {
						CITY_CODE: tCITY_ID,
						DT_PK_CODE: tDT_PK_CODE
					 };
					jQuery.post(url, params, callbackDM, 'json');
				}else{
					$('#DM_PK_CODE option').remove();
					$("#DM_PK_CODE").append("<option value=''>请选择</option>"); 
					$('#PG_PK_CODE option').remove();
					$("#PG_PK_CODE").append("<option value=''>请选择</option>"); 
				}
			}
			function callbackDM(data){
				$('#DM_PK_CODE option').remove();
				var d=data.dmBeans;
				$("#DM_PK_CODE").append("<option value=''>请选择</option>"); 
				if(d.length>0){
					for(var i=0;i<d.length;i++){
						$("#DM_PK_CODE").append("<option value='" + d[i].DM_PK_CODE + "'>" + d[i].DM_NAME + "</option>");
					}
					if(d.length==1){
						$("#DM_PK_CODE").val(d[0].DM_PK_CODE);
					}
					if($("#DM_PK_CODE").val().length>1){
						viewPG();
					}else{
						$('#PG_PK_CODE option').remove();
						$("#PG_PK_CODE").append("<option value=''>请选择</option>"); 
					}
				}
			}
			
			function viewPG(){
				var tDM_PK_CODE=$("#DM_PK_CODE").val();
				if(tDM_PK_CODE.length>0){
					var url = '${ctx}/address/b04r03addroption!pgs.action';
					var params = {
						DM_PK_CODE: tDM_PK_CODE
					 };
					jQuery.post(url, params, callbackPG, 'json');
				}else{
					$('#PG_PK_CODE option').remove();
					$("#PG_PK_CODE").append("<option value=''>请选择</option>"); 
				}
			}
			function callbackPG(data){
				$('#PG_PK_CODE option').remove();
				var d=data.pgBeans;
				$("#PG_PK_CODE").append("<option value=''>请选择</option>"); 
				if(d.length>0){
					for(var i=0;i<d.length;i++){
						$("#PG_PK_CODE").append("<option value='" + d[i].PG_PK_CODE + "'>" + d[i].PG_NAME + "</option>"); 
					}
					if(d.length==1){
						$("#PG_PK_CODE").val(d[0].PG_PK_CODE);
					}
				}
			}
			function gaoji(){
				if($('#gaoji').val()=='高级'){
					$('#gaoji').val('普通');
					$("#gaojitr0").show();
					$("#gaojitr1").show();
					$("#gaojitr2").show();
					$("#gaojitr3").show();
					$("#gaojitr4").show();
				}else{
					$('#gaoji').val('高级');
					$("#gaojitr0").hide();
					$("#gaojitr1").hide();
					$("#gaojitr2").hide();
					$("#gaojitr3").hide();
					$("#gaojitr4").hide();
				}
			}
			function yqls(){
				var mys = new Date();
				//mys = mys.getTime()-1000*60*60*24;
				var myDate = new Date();
				myDate.setTime(mys);     
				var tMonth = myDate.getMonth()+1;
				if(tMonth<10){
					tMonth = '0' + tMonth;
				}
				var tDate = myDate.getDate();
				if(tDate<10){
					tDate = '0' + tDate;
				}
				var sj = myDate.getFullYear() + '-' + tMonth + '-' + tDate + ' ' + '00';
				$('#ITEM_DATESS').val('');
				$('#ITEM_DATESE').val(sj);
			}
			function uprgpqpd(){
				var c=$('#address_table_list tbody input[name=rlCheckBox]:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
					cv = cv+','+$(this).val();
				});
				if(cv==''){
					$.prompt('请选择收寄地址',{buttons: {'确定': true}});
					return false;
				}
				var tTOTAL_ALL_VALUE=cv.substr(1,cv.length);
				var tDT_PK_CODE=$("#DT_PK_CODE").val();
				var tDM_PK_CODE=$("#DM_PK_CODE").val();
				var tPG_PK_CODE=$("#PG_PK_CODE").val();
				var tPK_REMARK=$("input[name='PK_REMARK'][checked]").val(); 
				if(tDT_PK_CODE.length<1 && tPK_REMARK.length<1){
					$.prompt('请选择配置信息',{buttons: {'确定': true}});
					return false;
				}

				$.prompt('该操作将修改匹配信息！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									TOTAL_ALL_VALUE:tTOTAL_ALL_VALUE,
									DT_PK_CODE:tDT_PK_CODE,
									DM_PK_CODE:tDM_PK_CODE,
									PG_PK_CODE:tPG_PK_CODE,
									PK_REMARK:tPK_REMARK
							};
							var url = '${ctx}/address/b04cud02addrconfig!uprgpqpdn.action';
							if(!(bro.msie && bro.version >=8)){
								$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
							}else{
								$("#nodata").show();
								$("#yesdata").hide();
							}
							jQuery.post(url, params, callajaxAdd, 'json');
							$("#KXDT").val("查询投递区");
							$("#DT_PK_CODE").val("");
							$('#DM_PK_CODE option').remove();
							$("#DM_PK_CODE").append("<option value=''>请选择</option>"); 
							$('#PG_PK_CODE option').remove();
							$("#PG_PK_CODE").append("<option value=''>请选择</option>"); 
							$("#PK_REMARK").val("");
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
			function kxtdq(){
				var kxz = $("#KXDT").val();
				if(kxz.length > 0 && kxz != '查询投递区'){
					$("select[name='DT_PK_CODE'] option").each(function(i){        //循环拼装被选中项的值
						if($(this).text().indexOf(kxz)>-1){
							$("#DT_PK_CODE").val($(this).val());
							viewDM();
							return false;
						}
					});
				}else{
					$("#DT_PK_CODE").val("");
					$('#DM_PK_CODE option').remove();
					$("#DM_PK_CODE").append("<option value=''>请选择</option>"); 
					$('#PG_PK_CODE option').remove();
					$("#PG_PK_CODE").append("<option value=''>请选择</option>"); 
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
										进口局批区批段-直连南京(历史)<label onClick="gaoji()">.</label>
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
												<td class="listtablehead" width="100">
													行政区域:
												</td>
												<td class="listtablehead" id="td_privince_content">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty()" style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES" style="width:100"></select>
												</td>
												<td class="listtablehead" width="100">
													未匹配投递区/段:
												</td>
												<td class="listtablehead" id="td_tpdistrict_content">
													<input type="radio" name="ISSR_FLAG" id="ISSR_FLAG" value="1"  checked="checked">未匹配投递区
													<input disabled type="radio" name="ISSR_FLAG" id="ISSR_FLAG" value="0">未匹配投递段
												</td>
												
											</tr>

											<tr>
												<td class="listtablehead">
													收寄开始时间:
												</td>
												<td class="listtablehead" id="td_privince_content">
													<input type="text" name="ITEM_DATESS" style="width: 200px; height:20px;" id="ITEM_DATESS" class="ac_input" size="300"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH'})"/>
												</td>
												<td class="listtablehead">
													收寄结束时间:
												</td>
												<td class="listtablehead">
													
													 <input readonly type="text" name="ITEM_DATESE" style="width: 200px; height:20px;" id="ITEM_DATESE" class="ac_input" size="300"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH'})"/>
													
													<!-- 
													<input readonly type="text" name="ITEM_DATESE" style="width: 200px; height:20px;" id="ITEM_DATESE" class="ac_input" size="300"/>
													-->
												</td>
											</tr>

											<tr id="gaojitr0" style="display:none">
												<td class="listtablehead">
													邮件号码:
												</td>
												<td class="listtablehead" id="td_tpdistrict_content">
													<input type="text" name="ITEMNO" style="width: 300px; height:20px;" id="ITEMNO" class="ac_input" size="300"/>
												</td>
												<td class="listtablehead">
													邮件地址:
												</td>
												<td class="listtablehead" id="td_tpdistrict_content">
													<input type="text" name="REC_ALLADDR" style="width: 300px; height:20px;" id="REC_ALLADDR" class="ac_input" size="300"/>
												</td>
											</tr>
											<tr  id="gaojitr1" style="display:none">
												<td class="listtablehead">
													人工匹配投递区:
												</td>
												<td class="listtablehead" id="td_tpdistrict_content">
													<input type="radio" name="ISDT" id="ISDT" value="" checked="checked">全部
													<input disabled type="radio" name="ISDT" id="ISDT" value="1">存在
													<input disabled type="radio" name="ISDT" id="ISDT" value="0">不存在
												</td>
												<td class="listtablehead">
													人工匹配投递段:
												</td>
												<td class="listtablehead" id="td_tpdistrict_content">
													<input type="radio" name="ISPG" id="ISPG" value="" checked="checked">全部
													<input disabled type="radio" name="ISPG" id="ISPG" value="1">存在
													<input disabled type="radio" name="ISPG" id="ISPG" value="0">不存在
												</td>
											</tr>
											<tr id="gaojitr2" style="display:none">
												<td class="listtablehead">
													参与南京集散:
												</td>
												<td class="listtablehead" id="td_tpdistrict_content">
													<input disabled type="radio" name="IS_DISTRI" id="IS_DISTRI" value="">全部
													<input type="radio" name="IS_DISTRI" id="IS_DISTRI" value="1" checked="checked">参与
													<input disabled type="radio" name="IS_DISTRI" id="IS_DISTRI" value="0">不参与
												</td>
										<td class="listtablehead">
													有/无地址:
												</td>
												<td class="listtablehead" id="td_tpdistrict_content" colspan="3">
													<input disabled type="radio" name="IS_YOUDIZHI" id="IS_YOUDIZHI" value="">全部
													<input type="radio" name="IS_YOUDIZHI" id="IS_YOUDIZHI" value="1" checked="checked">有
													<input disabledt type="radio" name="IS_YOUDIZHI" id="IS_YOUDIZHI" value="0">没有
												</td>
											</tr>
											<tr id="gaojitr3" style="display:none">
												<td class="listtablehead">
													处理级别:
												</td>
												<td class="listtablehead" id="td_tpdistrict_content">
													<input type="radio" name="ISPOSTDISTTYPE" id="ISPOSTDISTTYPE" value="" checked="checked">全部
													<input type="radio" name="ISPOSTDISTTYPE" id="ISPOSTDISTTYPE" value="3">缺号
													<input type="radio" name="ISPOSTDISTTYPE" id="ISPOSTDISTTYPE" value="2">无效地址
													<input type="radio" name="ISPOSTDISTTYPE" id="ISPOSTDISTTYPE" value="1">其它
												</td>
												<td class="listtablehead">
													处理类型:
												</td>
												<td class="listtablehead" id="td_tpdistrict_content" colspan="3">
													<input type="hidden" name="ISWHQUERY" id="ISWHQUERY" value="">
													<input type="radio" name="ISWH" id="ISWH" value="">全部
													<input type="radio" name="ISWH" id="ISWH" value="1">已处理
													<input type="radio" name="ISWH" id="ISWH" value="0" checked="checked">未处理
												</td>
											</tr>
											<tr>
												<td class="listtablehead"  colspan="4" align="right">
													
													<input id="query" type="button" value="查询" onClick="ajaxQueryPage(0)" />
													<input id="queryclear" type="button" value="重置" onClick="ajaxQueryPage(0)" />
													<input id="gaoji" type="button" value="高级" onClick="gaoji()"/>
												</td>
											</tr>
										</table>
									</div>
									<div id="tablelistdiv">
										<table class="listtable" width="1500"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="12">
													进口局批区批段
												</th>
											</thead>
											<thead>
												<th align="left"  colspan="2">
													配置到投递区-部-段：
												</th>
												<th align="left"  colspan="10">
													<input onkeyup="kxtdq()" type="text" name="KXDT" id="KXDT" value="查询投递区" style="width: 100px; height:20px;color:#FF0000" onFocus="this.select();">
													<select class="simple" id="DT_PK_CODE" name="DT_PK_CODE"  onChange="viewDM()"  style="width:200"></select>
													<select class="simple" id="DM_PK_CODE"  onChange="viewPG();"  style="width:100"></select>
													<select class="simple" id="PG_PK_CODE"  style="width:100"></select>
													&nbsp;备注：
													<input type="radio" name="PK_REMARK" id="PK_REMARK" value=""  checked="checked">无
													<input type="radio" name="PK_REMARK" id="PK_REMARK" value="2">虚假/错误地址
													<input type="radio" name="PK_REMARK" id="PK_REMARK" value="3">地址不完整/不规范
													<a href='#' onClick="uprgpqpd();">&nbsp;提交</a>
												</th>
											</thead>
											<thead>
												<td align="right" class="listtableheadleft" style='width:30px'>
													<input id="queryAll" name="chooseName" type="checkbox" onClick="queryAll()"/>
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													收寄日期时间
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													邮件号码
												</td>
												<td align="left" class="listtableheadleft1" style='width:200px'>
													寄达地址
												</td>
												<td align="left" class="listtableheadleft1" style='width:100px'>
													收寄局
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													寄达省市
												</td>
												<td align="left" class="listtableheadleft1" style='width:100px'>
													系统匹配投递区
												</td>
												<td align="left" class="listtableheadleft1" style='width:100px'>
													系统匹配投递段
												</td>
												<td align="left" class="listtableheadleft1" style='width:100px'>
													人工匹配投递区
												</td>
												<td align="left" class="listtableheadleft1" style='width:100px'>
													人工匹配投递部
												</td>
												<td align="left" class="listtableheadleft1" style='width:100px'>
													人工匹配投递段
												</td>
												<td align="left" class="listtableheadleft1" style='width:100px'>
													备注
												</td>
											</thead>
										</table>
										<div style='height:250px;overflow:auto;overflow-x:hidden;'>
<!--writing-mode:tb-rl;-->
										<table class="listtable" width="1500" id="address_table_list" cellpadding="0" cellspacing="0">
											
										</table>
										</div>

										<table class="listtable" width="1500" id="address_table_foot" cellpadding="0" cellspacing="0">
											
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
