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
		<title>准班准点填报</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){

				viewcity();

				$("#SEL_CLFWYLXXB_CSRQ").val('${SEL_CLFWYLXXB_CSRQ}');
				
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
					$("#COUNTY_NAMES").prepend("<option selected='selected' value=''>请选择</option>");
			    	for(var i=0;i<d.length;i++){
						$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
			    }else{
					$('#CITY_NAMES option').remove();
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
				$("#COUNTY_NAMES").prepend("<option selected='selected' value=''>请选择</option>");
				for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
				}
			}
			function ajaxQueryPage(pageNo){
				
				var tSEL_CLFWYLXXB_CSRQ=$("#SEL_CLFWYLXXB_CSRQ").val();

				if(tSEL_CLFWYLXXB_CSRQ=='' || tSEL_CLFWYLXXB_CSRQ.length<8 || tSEL_CLFWYLXXB_CSRQ.length>8){
					$.prompt('请选择填写日期',{buttons: {'确定': true}});
					return false;
				}
				
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}

				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				var tCITY_ID=$("#COUNTY_NAMES").val();
				if(!(tCITY_ID.length>0)){
					if(isMunicipalities(tPROVINCE_NAME)){
						tCITY_ID=tPROVINCE_ID;
				    }else{
						tCITY_ID=$("#CITY_NAMES").val();
					}
				}
				var url = '${ctx}/clfw/b09r02clfwquery!queryclfwylxxbt.action';
			    var params = {
			    		CITY_ID:tCITY_ID,
			    		SEL_CLFWYLXXB_CSRQ:tSEL_CLFWYLXXB_CSRQ,
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

				$('#address_tablechu_ls tbody').remove();
				
				$('#address_tablejin_ls tbody').remove();
				
				if(resultdata.length>0){
					
					for(var i=0;i<resultdata.length;i++){
						
						
						if(resultdata[i].CLFWYLXXB_XSBZ=='1'){
							
							tr="<tr id=tr_CLFWYLXXBZ" + i + ">";

							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFWYLXXB_CSRQ+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:150'>&nbsp;"+resultdata[i].CLFWYLXXBZ_YLMC+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>";
								
							tr+='<input type="text" id="CLFWYLXXB_LSH' + resultdata[i].CLFWYLXXB_SEQID + '" class="ac_input" value="' + resultdata[i].CLFWYLXXB_LSH + '" onFocus="this.select();"/>';
							
							tr+="</td>";

							tr+="<td align=left class=listtablebodyleft1 style='width:100'>";
							
							tr+='<input type="text" id="CLFWYLXXB_CPH' + resultdata[i].CLFWYLXXB_SEQID + '" class="ac_input" value="' + resultdata[i].CLFWYLXXB_CPH + '" onFocus="this.select();"  onkeyup="value=value.toUpperCase()"/>';

							tr+="</td>";

							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFWYLXXB_SFJID_STR+"</td>";

							tr+="<td align=left class=listtablebodyleft1 style='width:100'>";
							
							tr+='<input type="text" id="CLFWYLXXB_SJKCSJ' + resultdata[i].CLFWYLXXB_SEQID + '" class="ac_input" value="' + resultdata[i].CLFWYLXXB_SJKCSJ + '" onFocus="this.select();"/>';

							tr+="</td>";


							tr+="<td align=left class=listtablebodyleft1 style='width:80'>";
							
							tr+='<select class="simple" id="CLFWYLXXB_SFJGHSM' + resultdata[i].CLFWYLXXB_SEQID + '"  style="width:100">';

							if(resultdata[i].CLFWYLXXB_SFJGHSM == '1'){
								tr+="<option selected='selected' value='1'>正常发班</option>";
								tr+="<option value='2'>邮航落地晚点连带汽运晚点</option>";
								tr+="<option value='3'>本日非发班日期</option>";
							}else if(resultdata[i].CLFWYLXXB_SFJGHSM == '2'){
								tr+="<option value='1'>正常发班</option>";
								tr+="<option selected='selected' value='2'>邮航落地晚点连带汽运晚点</option>";
								tr+="<option value='3'>本日非发班日期</option>";
							}else if(resultdata[i].CLFWYLXXB_SFJGHSM == '3'){
								tr+="<option value='1'>正常发班</option>";
								tr+="<option value='2'>邮航落地晚点连带汽运晚点</option>";
								tr+="<option selected='selected' value='3'>本日非发班日期</option>";
							}

							tr+="</select>";
							
							tr+="</td>";

							tr+="<td align=left class=listtablebodyleft2 style='width:100'>&nbsp;";

							if(resultdata[i].CLFWYLXXB_SJKCSJ == ''){
						
							tr+="<a href='#' id='aCLFWYLXXB_CPH" + resultdata[i].CLFWYLXXB_SEQID + "' onClick='upclfwylxxbt(" + resultdata[i].CLFWYLXXB_SEQID + "," + resultdata[i].CLFWYLXXB_XSBZ + ");'>确认信息</a>";

							}
							
							tr+="</td>";
							
							tr+="</tr>";

							$('#address_tablechu').append(tr);

						}else if(resultdata[i].CLFWYLXXB_XSBZ=='3'){
							
							tr="<tr id=tr_CLFWYLXXBZ" + i + ">";

							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFWYLXXB_CSRQ+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:150'>&nbsp;"+resultdata[i].CLFWYLXXBZ_YLMC+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>";
								
							tr+='<input type="text" id="CLFWYLXXB_LSH' + resultdata[i].CLFWYLXXB_SEQID + '" class="ac_input" value="' + resultdata[i].CLFWYLXXB_LSH + '" onFocus="this.select();"/>';
							
							tr+="</td>";

							tr+="<td align=left class=listtablebodyleft1 style='width:100'>";
							
							tr+='<input type="text" id="CLFWYLXXB_CPH' + resultdata[i].CLFWYLXXB_SEQID + '" class="ac_input" value="' + resultdata[i].CLFWYLXXB_CPH + '" onFocus="this.select();"  onkeyup="value=value.toUpperCase()"/>';

							tr+="</td>";

							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFWYLXXB_SFJID_STR+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>";
							
							tr+='<input type="text" id="CLFWYLXXB_SJKCSJ' + resultdata[i].CLFWYLXXB_SEQID + '" class="ac_input" value="' + resultdata[i].CLFWYLXXB_SJKCSJ + '" onFocus="this.select();"/>';

							tr+="</td>";

							tr+="<td align=left class=listtablebodyleft1 style='width:80'>";
							
							tr+='<select class="simple" id="CLFWYLXXB_SFJGHSM' + resultdata[i].CLFWYLXXB_SEQID + '"  style="width:100">';

							if(resultdata[i].CLFWYLXXB_SFJGHSM == '1'){
								tr+="<option selected='selected' value='1'>正常发班</option>";
								tr+="<option value='2'>邮航落地晚点连带汽运晚点</option>";
								tr+="<option value='3'>本日非发班日期</option>";
							}else if(resultdata[i].CLFWYLXXB_SFJGHSM == '2'){
								tr+="<option value='1'>正常发班</option>";
								tr+="<option selected='selected' value='2'>邮航落地晚点连带汽运晚点</option>";
								tr+="<option value='3'>本日非发班日期</option>";
							}else if(resultdata[i].CLFWYLXXB_SFJGHSM == '3'){
								tr+="<option value='1'>正常发班</option>";
								tr+="<option value='2'>邮航落地晚点连带汽运晚点</option>";
								tr+="<option selected='selected' value='3'>本日非发班日期</option>";
							}

							tr+="</select>";
							
							tr+="</td>";

							tr+="<td align=left class=listtablebodyleft2 style='width:100'>&nbsp;";

							tr+="<a href='#' id='aCLFWYLXXB_CPH" + resultdata[i].CLFWYLXXB_SEQID + "'  onClick='upclfwylxxbt(" + resultdata[i].CLFWYLXXB_SEQID + "," + resultdata[i].CLFWYLXXB_XSBZ + ");'>确认信息</a>";
							
							tr+="</td>";
							
							tr+="</tr>";

							$('#address_tablechu_ls').append(tr);

						}else if(resultdata[i].CLFWYLXXB_XSBZ=='2'){

							tr="<tr id=tr_CLFWYLXXBZ" + i + ">";

							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFWYLXXB_CSRQ+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:150'>&nbsp;"+resultdata[i].CLFWYLXXBZ_YLMC+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFWYLXXB_LSH+"</td>";

							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFWYLXXB_CPH+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFWYLXXB_SFJID_STR+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFWYLXXB_SJKCSJ+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFWYLXXB_ZDJID_STR+"</td>";

							tr+="<td align=left class=listtablebodyleft1 style='width:100'>";
							
							tr+='<input type="text" id="CLFWYLXXB_SJDDSJ' + resultdata[i].CLFWYLXXB_SEQID + '" class="ac_input" value="' + resultdata[i].CLFWYLXXB_SJDDSJ + '" onFocus="this.select();"/>';

							tr+="</td>";

							tr+="<td align=left class=listtablebodyleft2 style='width:100px'>&nbsp;";

							if(resultdata[i].CLFWYLXXB_SJDDSJ == ''){
						
							tr+="<a href='#' id='aCLFWYLXXB_CPH" + resultdata[i].CLFWYLXXB_SEQID + "'  onClick='upclfwylxxbt(" + resultdata[i].CLFWYLXXB_SEQID + "," + resultdata[i].CLFWYLXXB_XSBZ + ");'>确认信息</a>";

							}
							
							tr+="</td>";
							
							tr+="</tr>";

							$('#address_tablejin').append(tr);
							
						}else if(resultdata[i].CLFWYLXXB_XSBZ=='4'){

							tr="<tr id=tr_CLFWYLXXBZ" + i + ">";

							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFWYLXXB_CSRQ+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:150'>&nbsp;"+resultdata[i].CLFWYLXXBZ_YLMC+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFWYLXXB_LSH+"</td>";

							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFWYLXXB_CPH+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFWYLXXB_SFJID_STR+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFWYLXXB_SJKCSJ+"</td>";
							
							tr+="<td align=left class=listtablebodyleft1 style='width:100'>&nbsp;"+resultdata[i].CLFWYLXXB_ZDJID_STR+"</td>";

							tr+="<td align=left class=listtablebodyleft1 style='width:100'>";
							
							tr+='<input type="text" id="CLFWYLXXB_SJDDSJ' + resultdata[i].CLFWYLXXB_SEQID + '" class="ac_input" value="' + resultdata[i].CLFWYLXXB_SJDDSJ + '" onFocus="this.select();"/>';

							tr+="</td>";

							tr+="<td align=left class=listtablebodyleft2 style='width:100px'>&nbsp;";
						
							tr+="<a href='#' id='aCLFWYLXXB_CPH" + resultdata[i].CLFWYLXXB_SEQID + "'  onClick='upclfwylxxbt(" + resultdata[i].CLFWYLXXB_SEQID + "," + resultdata[i].CLFWYLXXB_XSBZ + ");'>确认信息</a>";
							
							tr+="</td>";
							
							tr+="</tr>";

							$('#address_tablejin_ls').append(tr);
							
						}
						
					}
					
					var url="<tr style='display:none'><td colspan=20 class=listtablebodyleft2>"+data.page.ajaxUrl+"</td></tr>";
					
				}else{
					var url="<tr><td colspan=20 class=listtablebodyleft2>找到 0 条记录</td></tr>";
					
					$('#address_tablechu').append(url);

					$('#address_tablejin').append(url);

					$('#address_tablechu_ls').append(url);

					$('#address_tablejin_ls').append(url);
					
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
		  function upclfwylxxbt(tCLFWYLXXB_SEQID,tCLFWYLXXB_XSBZ){

			  	var regEx = /[^0-9]+/gi;

				if(tCLFWYLXXB_XSBZ == '1' || tCLFWYLXXB_XSBZ == '3'){
					
					var tCLFWYLXXB_LSH=$('#CLFWYLXXB_LSH'+tCLFWYLXXB_SEQID).val();
				  	
					if(tCLFWYLXXB_LSH==''){
						$.prompt('流水号不能为空',{buttons: {'确定': true}});
						return false;
					}else if(tCLFWYLXXB_LSH.length>50){
						$.prompt('流水号字符大于50',{buttons: {'确定': true}});
						return false;
					}

					var tCLFWYLXXB_CPH=$('#CLFWYLXXB_CPH'+tCLFWYLXXB_SEQID).val();
				  	
					if(tCLFWYLXXB_CPH==''){
						$.prompt('车牌号不能为空',{buttons: {'确定': true}});
						return false;
					}else if(tCLFWYLXXB_CPH.length>50){
						$.prompt('车牌号字符大于50',{buttons: {'确定': true}});
						return false;
					}
					
					var tCLFWYLXXB_SJKCSJ=$('#CLFWYLXXB_SJKCSJ'+tCLFWYLXXB_SEQID).val();

					var tCLFWYLXXB_SFJGHSM=$('#CLFWYLXXB_SFJGHSM'+tCLFWYLXXB_SEQID).val();

					var tCLFWYLXXB_SJDDSJ='';

					if(tCLFWYLXXB_SJKCSJ==''){
						$.prompt('实际发车时间不能为空',{buttons: {'确定': true}});
						return false;
					}else if(tCLFWYLXXB_SJKCSJ.length!=4){
						$.prompt('实际发车时间字符不等于4',{buttons: {'确定': true}});
						return false;
					}else if(!(tCLFWYLXXB_SJKCSJ.substr(0,2) >= '00' && tCLFWYLXXB_SJKCSJ.substr(0,2) <= '23')){
						$.prompt('实际发车时间小时填写不对',{buttons: {'确定': true}});
						return false;
					}else if(!(tCLFWYLXXB_SJKCSJ.substr(2,2) >= '00' && tCLFWYLXXB_SJKCSJ.substr(2,2) <= '59')){
						$.prompt('实际发车时间分钟填写不对',{buttons: {'确定': true}});
						return false;
					}else if(regEx.test(tCLFWYLXXB_SJKCSJ)){
						$.prompt('请填入四位数字',{buttons: {'确定': true}});
						return false;
					}
	
						
				}else if(tCLFWYLXXB_XSBZ == '2' || tCLFWYLXXB_XSBZ == '4'){
					var tCLFWYLXXB_LSH='';
					var tCLFWYLXXB_CPH='';
					var tCLFWYLXXB_SJKCSJ='';

					tCLFWYLXXB_SJKCSJSM='1';
					
					var tCLFWYLXXB_SFJGHSM='1';

					var tCLFWYLXXB_SJDDSJ=$('#CLFWYLXXB_SJDDSJ'+tCLFWYLXXB_SEQID).val();
				  	
					if(tCLFWYLXXB_SJDDSJ==''){
						$.prompt('实际发车时间不能为空',{buttons: {'确定': true}});
						return false;
					}else if(tCLFWYLXXB_SJDDSJ.length!=4){
						$.prompt('实际发车时间字符不等于4',{buttons: {'确定': true}});
						return false;
					}else if(!(tCLFWYLXXB_SJDDSJ.substr(0,2) >= '00' && tCLFWYLXXB_SJDDSJ.substr(0,2) <= '23')){
						$.prompt('实际发车时间小时填写不对',{buttons: {'确定': true}});
						return false;
					}else if(!(tCLFWYLXXB_SJDDSJ.substr(2,2) >= '00' && tCLFWYLXXB_SJDDSJ.substr(2,2) <= '59')){
						$.prompt('实际发车时间分钟填写不对',{buttons: {'确定': true}});
						return false;
					}else if(regEx.test(tCLFWYLXXB_SJDDSJ)){
						$.prompt('请填入四位数字',{buttons: {'确定': true}});
						return false;
					}
				}

				
		  		var params = {
		  			CLFWYLXXB_SFJGHSM:tCLFWYLXXB_SFJGHSM,
		  			CLFWYLXXB_LSH:tCLFWYLXXB_LSH,
		  			CLFWYLXXB_CPH:tCLFWYLXXB_CPH,
		  			CLFWYLXXB_SJKCSJ:tCLFWYLXXB_SJKCSJ,
		  			CLFWYLXXB_SJDDSJ:tCLFWYLXXB_SJDDSJ,
	  				CLFWYLXXB_SEQID:tCLFWYLXXB_SEQID,
	  				CLFWYLXXB_XSBZ:tCLFWYLXXB_XSBZ
				};
				var url = '${ctx}/clfw/b09cud02clfwconfig!upclfwylxxbt.action';
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				 $('#aCLFWYLXXB_CPH'+tCLFWYLXXB_SEQID).hide();
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
										准班准点统计>>准班准点填报
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
												<td class="js_left_txt" id="td_privince_content">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty()" style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES" style="width:100"></select>
												</td>
												<td class="listtablehead" width="100">
													上报日期:
												</td>
												<td class="js_left_txt" id="td_common_tempid">
													<input disabled type="text" name="SEL_CLFWYLXXB_CSRQ" style="width: 100px; height:20px;" id="SEL_CLFWYLXXB_CSRQ" class="ac_input" size="100"/>
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
														*确认信息后不能修改，请慎重填报
														<br>*【实际开出时间】【实际到达时间】填写如【1010】四位数字
														<br>*数据填报在【备注】的下可以选择【正常发班】【邮航落地晚点连带汽运晚点】【本日非发班日期】,没有发车则所有数据填入【0000】
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
													始发开行实际时刻信息填报(当日)
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft1" style='width:100'>
													应填日期
												</td>
												<td align="left" class="listtableheadleft" style='width:150'>
													邮路名称
												</td>
												<td align="left" class="listtableheadleft" style='width:100'>
													流水号
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													车牌号
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													始发局
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													实际开出时间
												</td>
												<td align="left" class="listtableheadleft1" style='width:80'>
													备注
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													操作
												</td>
											</thead>
										</table>
										<br>
										<table class="listtable" width="1200"
											id="address_tablechu_ls" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="20">
													始发开行实际时刻信息填报(历史)
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft1" style='width:100'>
													应填日期
												</td>
												<td align="left" class="listtableheadleft" style='width:150'>
													邮路名称
												</td>
												<td align="left" class="listtableheadleft" style='width:100'>
													流水号
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													车牌号
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													始发局
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													实际开出时间
												</td>
												<td align="left" class="listtableheadleft1" style='width:80'>
													备注
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
													终到（经停）实际时刻信息填报(当日)
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft1" style='width:100'>
													应填日期
												</td>
												<td align="left" class="listtableheadleft" style='width:150'>
													邮路名称
												</td>
												<td align="left" class="listtableheadleft" style='width:100'>
													流水号
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													车牌号
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													始发局
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													实际开出时间
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													终到（经停）局
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													实际到达时间
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													操作
												</td>
											</thead>
										</table>
										<br>
										<table class="listtable" width="1200"
											id="address_tablejin_ls" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="20">
													终到（经停）实际时刻信息填报(历史)
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft1" style='width:100'>
													应填日期
												</td>
												<td align="left" class="listtableheadleft" style='width:150'>
													邮路名称
												</td>
												<td align="left" class="listtableheadleft" style='width:100'>
													流水号
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													车牌号
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													始发局
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													实际开出时间
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													终到（经停）局
												</td>
												<td align="left" class="listtableheadleft1" style='width:100'>
													实际到达时间
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
