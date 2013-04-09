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
		<title>经济频次到市配置</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				
				viewcity();

				vviewcity();
				
				$("#th_addflagpz").hide();

				$('#tdqueryAll').hide();
				
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
				//$("#CITY_NAMES").prepend("<option selected='selected' value=''>请选择</option>");
			}
			
			//展示市
			function vviewcity(){
				var tPROVINCE_NAME=$("#VPROVINCE_NAMES").find("option:selected").text(); 
				var url = '${ctx}/jjsx/b10r03jjsxoption!citysyt.action';
				var params = {
					PROVINCE_NAME: tPROVINCE_NAME
				 };
				jQuery.post(url, params, vcallbackcity, 'json');
			}
			function vcallbackcity(data){
				var d=data.districtBeans;
				$('#VCITY_NAMES option').remove();
				for(var i=0;i<d.length;i++){
					$("#VCITY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
				}
				//$("#VCITY_NAMES").prepend("<option selected='selected' value=''>请选择</option>");
			}
			
			function ajaxQueryPage(pageNo){

				$('#queryAll').attr('checked',false);
				
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				
				var tCITY_ID=$("#CITY_NAMES").val();

				var tFLAGPZ=$("input[name='FLAGPZ'][checked]").val();
				
				if(tFLAGPZ == '1'){
					$('#tdqueryAll').hide();
					$("#th_addflagpz").hide();
				}else if(tFLAGPZ == '0'){
					$('#tdqueryAll').show();
					$("#th_addflagpz").show();
				}

				var tPCSX=$("input[name='PCSX'][checked]").val();

				var tCQUSXXUS=$("input[name='CQUSXXUS'][checked]").val();
				
				var url = '${ctx}/jjsx/b10r02jjsxquery!queryjjsxsjspc.action';
			    var params = {
								CITY_ID:tCITY_ID,
								FLAGPZ:tFLAGPZ,
								PCSX:tPCSX,
								CQUSXXUS:tCQUSXXUS,
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
						if(tFLAGPZ == '0'){
							tr+=" <td align=right class=listtablebodyleft1 style='width:30px'><input type='checkbox' onclick=xuanze('tr_query" + resultdata[i].SEQID + "') id='rlCheckBox" + resultdata[i].SEQID + "'  name='rlCheckBox' value='"+resultdata[i].SEQID + "'>";
							tr+="</td>";
						}
						tr+="<td align=left class=listtablebodyleft1 style='width:120px'>&nbsp;"+resultdata[i].DISTNAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:150px'>&nbsp;"+resultdata[i].ALLPCMC+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].CQUSXXUSSTR+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:120px'>&nbsp;"+resultdata[i].HXJZSJ+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].HXJZJG+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].FHXJZSJ+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:130px'>&nbsp;"+resultdata[i].FHXJZJG+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].README+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:100px'>&nbsp;";

						if(tFLAGPZ == '1'){
							tr+="<a href='#' onClick='updatebean(" + resultdata[i].SEQID + ");'>修改</a>";
							tr+="&nbsp;&nbsp;&nbsp;";
							tr+="<a href='#' onClick='delbean(" + resultdata[i].SEQID + ");'>删除</a>";
						}

						tr+="</td>";
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr style='display:none'><td colspan=20 class=listtablebodyleft2>"+data.page.ajaxUrl+"</td></tr>";
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
		  function delbean(tSEQID){
			  $.prompt('该操作将删除经济频次到市配置信息,并且关联删除该频次下的机构代码频次配置信息！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v,m){
						if(v){
							var params = {
								SEQID:tSEQID
							};
							var url = '${ctx}/jjsx/b10cud02jjsxconfig!deljjsxsjspc.action';
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

				var tCITY_ID=$("#VCITY_NAMES").val();
				var tCITY_NAME=$("#VCITY_NAMES").find("option:selected").text();

				if(tCITY_ID.length<1){
					$.prompt('城市不能为空',{buttons: {'确定': true}});
					return false;
				}
				
				$.prompt('该操作将添加经济频次到市配置信息！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
								SEQIDS:tTOTAL_ALL_VALUE,
								DISTCD:tCITY_ID,
								DISTNAME:tCITY_NAME
							};
							var url = '${ctx}/jjsx/b10cud02jjsxconfig!addjjsxsjspc.action';
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
			  var value = window.showModalDialog("${ctx}/jjsx/b10r01jjsxview!upjjsxsjspc.action?FLAG=UPDATE&SEQID=" + tSEQID,"Code","dialogWidth:600px;dialogHeight:600px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
		  function callajaxDel(data){
				$('#queryAll').attr('checked',false);
				$.prompt(data.saveMessage,{buttons: {'确定': true}});
				$.unblockUI();
				$("#nodata").hide();
				$("#yesdata").show();
				backfresh("fresh");
			}
		  function callajaxAdd(data){
				//$('#queryAll').attr('checked',false);
				$.prompt(data.saveMessage,{buttons: {'确定': true}});
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
										经济时限服务>>经济频次到市配置
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
													<select class="simple" id="CITY_NAMES" style="width:100"></select>
												</td>
												<td class="listtablehead">
													配置状态：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="FLAGPZ" id="FLAGPZ" value="1" checked="checked">已配置
													<input type="radio" name="FLAGPZ" id="FLAGPZ" value="0">可配置
												</td>
											</tr>
											<tr>
												<td class="listtablehead">
													频次类别：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="PCSX" id="PCSX" value=""  checked="checked">全部
													<input type="radio" name="PCSX" id="PCSX" value="0">揽收
													<input type="radio" name="PCSX" id="PCSX" value="1" >投递
												</td>
												<td class="listtablehead">
													频次范围：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="CQUSXXUS" id="CQUSXXUS" value=""  checked="checked">全部
													<input type="radio" name="CQUSXXUS" id="CQUSXXUS" value="1">城区
													<input type="radio" name="CQUSXXUS" id="CQUSXXUS" value="0" >辖县
												</td>
											</tr>
											<tr>
												
											</tr>
											<tr>
												<td class="listtablehead"  colspan="4" align="right">
													<input id="query" type="button" value="查询" onClick="ajaxQueryPage(0)" />
													<input id="queryclear" type="button" value="重置" onClick="ajaxQueryPage(0)" />
													<input id="fliphelpct" type="button" value="帮助"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead"  colspan="4">
													<font id="fliphelpcon" color="red">
														*操作步骤 点击【可配置】选择【频次】到【选择城市】中即可
														<br>*只有给城市配置了频次，市级管理员才能给机构代码选择频次
													</font>
												</td>
											</tr>
										</table>
									</div>
									<div id="tablelistdiv">
										<table class="listtable" width="100%"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="20">
													经济频次到市配置
												</th>
											</thead>
											<thead id="theadPZGN">
												<th  align="left"  colspan="20">
													<p id="th_addflagpz">
													&nbsp;
													选择城市：
													<s:select theme="simple" list="provinces" id="VPROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="vviewcity()"></s:select>
													<select class="simple" id="VCITY_NAMES" style="width:100"></select>
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
												<td align="left" class="listtableheadleft" style='width:150px'>
													频次名称
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													频次范围
												</td>
												<td align="left" class="listtableheadleft" style='width:120px'>
													核心截止时间
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													核心间隔
												</td>
												<td align="left" class="listtableheadleft" style='width:130px'>
													非核心截止时间
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													非核心间隔
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
