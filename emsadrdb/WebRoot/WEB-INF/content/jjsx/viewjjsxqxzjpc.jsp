<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/jjsx/jjsx.jsp"%>
<%@ include file="/scripts/My97DatePicker/My97DatePicker.jsp"%>
<html>

	<head>
		<title>经济频次到支局机构配置</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				
				viewcity();

				$("#th_delflagpz").show();

				$("#th_addflagpz").hide();

				$("#fliphelpct").click(function(){
					$("#fliphelpcon").slideToggle("fast");
				});
				
			})
			//展示市
			function viewcity(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var url = '${ctx}/jjsx/b10r03jjsxoption!citysyt.action';
				var params = {
					PROVINCE_NAME: tPROVINCE_NAME
				 };
				jQuery.post(url, params, callbackcity, 'json');
			}
			function callbackcity(data){
				var d=data.districtBeans;
				$('#CITY_NAMES option').remove();
				for(var i=0;i<d.length;i++){
					$("#CITY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
				}
				viewcounty();
			}
			function viewcounty(){
				var tCITY_NAME=$("#CITY_NAMES").find("option:selected").text(); 
				var tCITY_CODE=$("#CITY_NAMES").val(); 
				if(tCITY_NAME.length>0){
					var url = '${ctx}/jjsx/b10r03jjsxoption!countysyt.action';
					var params = {
						CITY_CODE: tCITY_CODE,
						CITY_NAME: tCITY_NAME
					 };
					jQuery.post(url, params, callbackcounty, 'json');
				}
			}
			function callbackcounty(data){
				$('#COUNTY_NAMES option').remove();
				var d=data.districtBeans;
				for(var i=0;i<d.length;i++){
					$("#COUNTY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].COUNTY_NAME + "</option>"); 
				}
				removecounty();
				viewspc();
			}
			function removecounty(){
				$('#address_table tbody').remove();
			}

			//市频次
			function viewspc(){
				var tFLAGPZ=$("input[name='FLAGPZ'][checked]").val();
				tCITY_CODE=$("#CITY_NAMES").val(); 
				tCOUNTY_CODE=$("#COUNTY_NAMES").val(); 
				if(tCOUNTY_CODE.length>0){
					var url = '${ctx}/jjsx/b10r03jjsxoption!spcs.action';
					var params = {
						CITY_CODE: tCITY_CODE,
						COUNTY_CODE:tCOUNTY_CODE,
						PCFLAG:'T'
					 };
					jQuery.post(url, params, callbackspc, 'json');
				}
			}
			function callbackspc(data){
				$('#SPCS option').remove();
				var d=data.spcBeans;
				$("#SPCS").prepend("<option selected='selected' value=''>请选择</option>");
				for(var i=0;i<d.length;i++){
					$("#SPCS").append("<option value='" + d[i].SEQID + "'>" + d[i].ALLPCMC + "</option>"); 
				}
			}
			
			function ajaxQueryPage(pageNo){

				$('#queryAll').attr('checked',false);
				
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}

				var tCOUNTY_ID=$("#COUNTY_NAMES").val();

				var tRANGE=$("#RANGE").val();

				var tPCMC=$("#PCMC").val();
				var tPCSX=$("input[name='PCSX'][checked]").val();
				
				var tFLAGPZ=$("input[name='FLAGPZ'][checked]").val();

				var tHXFLAG=$("input[name='SELHXFLAG'][checked]").val();
				
				if(tFLAGPZ == '1'){
					$("#th_delflagpz").show();
					$("#th_addflagpz").hide();
					$("#trcxPCMC").show();
					$("#trcxPCSX").show();
				}else if(tFLAGPZ == '0'){
					$("#th_delflagpz").hide();
					$("#th_addflagpz").show();
					$("#trcxPCMC").hide();
					$("#trcxPCSX").hide();
				}

				var tORG_CODE=$("#ORG_CODE").val();

				var tCQUSXXUS=$("input[name='CQUSXXUS'][checked]").val();
				
				var url = '${ctx}/jjsx/b10r02jjsxquery!queryjjsxsqxzyzjpc.action';
			    var params = {
								COUNTY_ID:tCOUNTY_ID,
								FLAGPZ:tFLAGPZ,
								ORG_CODE:tORG_CODE,
								RANGE:tRANGE,
								PCMC:tPCMC,
					    		PCSX:tPCSX,
								CQUSXXUS:tCQUSXXUS,
								HXFLAG:tHXFLAG,
			                  	pageNo:pageNo
			                 };
			    jQuery.post(url, params, callbackajaxQueryPage, 'json');
			}
			function callbackajaxQueryPage(data){
				addquerytable(data);
			}
			function addquerytable(data){
				var resultdata=data.page.result;
				$('#address_table tbody').remove();
				var tFLAGPZ=$("input[name='FLAGPZ'][checked]").val();
				
				if(resultdata.length>0){
					for(var i=0;i<resultdata.length;i++){
						var tr="<tr id='tr_query" + resultdata[i].SEQID + "' )>";
						tr+=" <td align=right class=listtablebodyleft1 style='width:30px'><input type='checkbox' onclick=xuanze('tr_query" + resultdata[i].SEQID + "') id='rlCheckBox" + resultdata[i].SEQID + "'  name='rlCheckBox' value='"+resultdata[i].SEQID + "'>";
						tr+="</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:120px'>&nbsp;"+resultdata[i].DISTNAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:200px'>&nbsp;"+resultdata[i].ALLPCMC+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:200px'>&nbsp;"+resultdata[i].ORG_CNAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].ORG_CODE+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:100px'>&nbsp;"+resultdata[i].PCJZSJ+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:100px'>&nbsp;"+resultdata[i].PCSJJG+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:100px'>&nbsp;"+resultdata[i].README+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:100px'>&nbsp;";

						if(tFLAGPZ == '1'){
							tr+="<a href='#' onClick='updatebean(" + resultdata[i].SEQID + ");'>修改</a>";
						}

						tr+="</td>";
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr><td colspan=20 class=listtablebodyleft2>"+data.page.ajaxUrl+"</td></tr>";
					$('#address_table').append(url);
				}else{
					var url="<tr><td colspan=20 class=listtablebodyleft2>找到 0 条记录</td></tr>";
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
			function xuanze(st,id){
				if($("#" + id).attr("checked")){
					$("#" + st).css("background","#FEEBD0");
				}else{
					$("#" + st).css("background","#FBFBFF");
				}

				var bflag = true;
				$("#address_table tbody input[name=rlCheckBox]").each(function(){
	                if(!$(this).attr('checked')){
	                	bflag = false;
		            };
	            }); 
	            if(bflag){
	            	 $('#queryAll').attr('checked',true);
			    }else{
			    	 $('#queryAll').attr('checked',false);
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
		  function delbean(){
			  $("#whmsg").text("");
			  var c=$('#address_table tbody input[name=rlCheckBox]:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
					cv = cv+','+$(this).val();
				});
				if(cv==''){
					$.prompt('请选择操作信息',{buttons: {'确定': true}});
					return false;
				}
				var tTOTAL_ALL_VALUE=cv.substr(1,cv.length);
				
				$.prompt('该操作将删除经济频次到支局机构配置信息！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
								SEQIDS:tTOTAL_ALL_VALUE
							};
							var url = '${ctx}/jjsx/b10cud02jjsxconfig!deljjsxqxzyzjpc.action';
							if(!(bro.msie && bro.version >=8)){
								$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
							}else{
								$("#nodata").show();
								$("#yesdata").hide();
							}
							jQuery.post(url, params, callajaxDel, 'json');
						}
					}
				});
			}
		  function addbean(){
			  $("#whmsg").text("");
			  var c=$('#address_table tbody input[name=rlCheckBox]:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
					cv = cv+','+$(this).val();
				});
				if(cv==''){
					$.prompt('请选择操作信息',{buttons: {'确定': true}});
					return false;
				}
				var tTOTAL_ALL_VALUE=cv.substr(1,cv.length);

				var tSPCS=$("#SPCS").val();
				
				if(tSPCS.length<1){
					$.prompt('频次不能为空',{buttons: {'确定': true}});
					return false;
				}

				var tHXFLAG=$("input[name='VALHXFLAG'][checked]").val();
				
				$.prompt('该操作将添加经济频次到支局机构配置信息！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
								SEQIDS:tTOTAL_ALL_VALUE,
								HXFLAG:tHXFLAG,
								SJSPCSEQID:tSPCS
							};
							var url = '${ctx}/jjsx/b10cud02jjsxconfig!addjjsxqxzyzjpc.action';
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
		  function updatebean(tSEQID){
			  var value = window.showModalDialog("${ctx}/jjsx/b10r01jjsxview!upjjsxqxzypc.action?FLAG=UPDATE&SEQID=" + tSEQID,"Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
		  function callajaxAdd(data){
				//$('#queryAll').attr('checked',false);
				//$("#address_table tbody input[name=rlCheckBox]").attr('checked',false);
				//$.prompt(data.saveMessage,{buttons: {'确定': true}});
				$("#whmsg").text(data.saveMessage);
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
				//backfresh("fresh");
			}
		  function callajaxDel(data){
				$('#queryAll').attr('checked',false);
				$("#address_table tbody input[name=rlCheckBox]").attr('checked',false);
				$.prompt(data.saveMessage,{buttons: {'确定': true}});
				$("#whmsg").text(data.saveMessage);
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
										经济时限服务>>经济频次到支局机构配置
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
												<td class="listtablehead" width="180">
													行政区域:
												</td>
												<td class="js_left_txt" id="td_privince_content">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" onChange="viewcounty();" style="width:100"></select>
													<select class="simple" id="COUNTY_NAMES" style="width:100" onChange="removecounty();viewspc();"></select>
													<input type="hidden" name="RANGE" id="RANGE" class="ac_input" value='0'/>
												</td>
												<td class="listtablehead">
													配置状态：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="FLAGPZ" id="FLAGPZ" value="1" checked="checked">已配置
													<input type="radio" name="FLAGPZ" id="FLAGPZ" value="0">可配置
												</td>
											</tr>
											<tr id="trcxPCMC">
												<td class="listtablehead" width="100">
													频次名称:
												</td>
												<td class="js_left_txt" id="td_common_tempid">
													<input type="text" name="PCMC" style="width: 200px; height:20px;" id="PCMC" class="ac_input" size="200"/>
												</td>
												<td class="listtablehead">
													频次类别：
												</td>
												<td class="js_left_txt">
													<input style="display:none" type="radio" name="PCSX" id="PCSX" value="">
													<input style="display:none" type="radio" name="PCSX" id="PCSX" value="0">
													<input type="radio" name="PCSX" id="PCSX" value="1"   checked="checked">投递
												</td>
											</tr>
											<tr id="trcxPCSX">
												<td class="listtablehead">
													频次范围：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="CQUSXXUS" id="CQUSXXUS" value=""  checked="checked">全部
													<input type="radio" name="CQUSXXUS" id="CQUSXXUS" value="1">城区
													<input type="radio" name="CQUSXXUS" id="CQUSXXUS" value="0" >辖县
												</td>
												<td class="listtablehead">
													核心/非核心：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="SELHXFLAG" id="SELHXFLAG" value=""  checked="checked">全部
													<input type="radio" name="SELHXFLAG" id="SELHXFLAG" value="1">核心
													<input type="radio" name="SELHXFLAG" id="SELHXFLAG" value="0" >非核心
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="180">
													机构代码:
												</td>
												<td class="js_left_txt" id="td_privince_content"  colspan="3">
													<input type="text" name="ORG_CODE" style="width: 600px; height:20px;" id="ORG_CODE" class="ac_input" size="600"/>
												</td>
											</tr>
											<tr>
											<tr>
												
											</tr>
											<tr>
												<td class="listtablehead" align="left">
													<label id="whmsg" style="color:'blue'"></label>
												</td>
												<td class="listtablehead"  colspan="3" align="right">
													<label id="whmsg"></label>
													<input id="query" type="button" value="查询" onClick="ajaxQueryPage(0)" />
													<input id="queryclear" type="button" value="重置" onClick="ajaxQueryPage(0)" />
													<input id="fliphelpct" type="button" value="帮助"/>
												</td>
											</tr>
										</table>
									</div>
									<div id="tablelistdiv">
										<table class="listtable" width="100%"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="12">
													经济频次到支局机构配置
												</th>
											</thead>
											<thead id="theadPZGN">
												<th  align="left"  colspan="10">
													<p id="th_delflagpz">
													&nbsp;
													<a href='#' onClick="delbean();">&nbsp;删除配置</a>
													</p>
													<p id="th_addflagpz">
													&nbsp;
													选择频次：
													<select class="simple" id="SPCS" style="width:300"></select>
													核心属性：
													<input type="radio" name="VALHXFLAG" id="VALHXFLAG" value="1" checked="checked">核心区域
													<input type="radio" name="VALHXFLAG" id="VALHXFLAG" value="0">非核心区域
													<a href='#' onClick="addbean();">&nbsp;添加配置</a>
													</p>
												</th>
											</thead>
											<thead>
												<td id="tdqueryAll" align="right" class="listtableheadleft" style='width:30px'>
													<input id="queryAll" name="chooseName" type="checkbox" onClick="queryAll()"/>
												</td>
												<td align="left" class="listtableheadleft" style='width:120px'>
													行政区域
												</td>
												<td align="left" class="listtableheadleft" style='width:200px'>
													频次名称
												</td>
												<td align="left" class="listtableheadleft" style='width:200px'>
													机构名称
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													机构代码
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													截止时间
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													间隔日期
												</td>
												<td align="left" class="listtableheadleft1" style='width:100px'>
													说明
												</td>
												<td align="left" class="listtableheadleft1" style='width:100px'>
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
