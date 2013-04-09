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
		<title>揽投部直封集散设置</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){

				if('${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2'){
					$("#theadPZGN").show();
				}else{
					$("#theadPZGN").hide();
				}
				
				$("#th_addclfwresorgpz").hide();
				
				viewcity();
				
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
				if(!isMunicipalities(tPROVINCE_NAME)){
					$('#CITY_NAMES option').remove();
					for(var i=0;i<d.length;i++){
						$("#CITY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
					}
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
					var tCITY_ID='';
			    }else{
					var tCITY_ID=$("#CITY_NAMES").val();
				}

				var tRAD_CLFWPC_QY=$("input[name='RAD_CLFWPC_QY'][checked]").val();
				if(tRAD_CLFWPC_QY == '1'){
					$("#th_delclfwresorgpz").show();
					$("#th_addclfwresorgpz").hide();
				}else if(tRAD_CLFWPC_QY == '0'){
					$("#th_delclfwresorgpz").hide();
					$("#th_addclfwresorgpz").show();
				}
				
				var url = '${ctx}/clfw/b09r02clfwquery!queryclfwtabnjjs.action';
			    var params = {
			    				PROVINCE_ID:tPROVINCE_ID,
								CITY_ID:tCITY_ID,
					    		RAD_CLFWPC_QY:tRAD_CLFWPC_QY,
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
				if(resultdata.length>0){
					for(var i=0;i<resultdata.length;i++){
						var tr="<tr id='tr_query" + resultdata[i].CLFWTDBNJJS_SEQID + "' )>";
						tr+=" <td align=right class=listtablebodyleft1 style='width:30px'><input type='checkbox' onclick=xuanze('tr_query" + resultdata[i].CLFWTDBNJJS_SEQID + "') id='rlCheckBox" + resultdata[i].CLFWTDBNJJS_SEQID + "'  name='rlCheckBox' value='"+resultdata[i].CLFWTDBNJJS_SEQID + "'>";
						tr+="</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:120px'>&nbsp;"+resultdata[i].CLFWTDB_DISTCDSTR+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:200px'>&nbsp;"+resultdata[i].DM_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].OFFICE_CODE+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:100px'>&nbsp;"+resultdata[i].CLFWTDBNJJS_SFNJ+"</td>";
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr ><td colspan=12 class=listtablebodyleft2>"+data.page.ajaxUrl+
					"<input type='text' name='pagenum' style='width:50px;height:20px;' id='pagenum' class='ac_input'/><a id='pagego' href='#' onClick='pagecx(" + data.page.totalCount + "," + data.page.pageSize + ")' style='font-size:12px'>GO</a>"+
					"</td></tr>";
					$('#address_table').append(url);
				}else{
					var url="<tr><td colspan=12 class=listtablebodyleft2>找到 0 条记录</td></tr>";
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
		  function delclfwtabnjjs(){
				var c=$('#address_table tbody input[name=rlCheckBox]:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
					cv = cv+','+$(this).val();
				});
				if(cv==''){
					$.prompt('请选择揽投部信息',{buttons: {'确定': true}});
					return false;
				}
				var tTOTAL_ALL_VALUE=cv.substr(1,cv.length);
				$.prompt('该操作将删除揽投部直封集散配置信息！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									CLFWTDBNJJS_SEQID:tTOTAL_ALL_VALUE
							};
							var url = '${ctx}/clfw/b09cud02clfwconfig!delclfwtabnjjs.action';
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
		  function addclfwtabnjjs(){
				var c=$('#address_table tbody input[name=rlCheckBox]:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
					cv = cv+",'"+$(this).val() + "'";
				});
				if(cv==''){
					$.prompt('请选择揽投部信息',{buttons: {'确定': true}});
					return false;
				}
				var tTOTAL_ALL_VALUE=cv.substr(1,cv.length);
				var tCLFWTDBNJJS_SFNJ=$("input[name='CLFWTDBNJJS_SFNJ'][checked]").val();
				$.prompt('该操作将添加揽投部直封集散配置信息！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									CLFWTDBNJJS_SEQID:tTOTAL_ALL_VALUE,
									CLFWTDBNJJS_SFNJ:tCLFWTDBNJJS_SFNJ
							};
							var url = '${ctx}/clfw/b09cud02clfwconfig!addclfwtabnjjs.action';
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
										投递区部段设置>>揽投部直封集散设置
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
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity();"></s:select>
													<select class="simple" id="CITY_NAMES" style="width:100"></select>
												</td>
												<td class="listtablehead">
													配置属性：
												</td>
												<td class="js_left_txt"  colspan="3">
													<input type="radio" name="RAD_CLFWPC_QY" id="RAD_CLFWPC_QY" value="1" checked="checked">已配置
													<input type="radio" name="RAD_CLFWPC_QY" id="RAD_CLFWPC_QY" value="0">可配置
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
														*操作步骤 点击【可配置】选择【揽投部】到【直封集散】中即可
														<br>*2012.09.19日按总部要求,屏蔽配置功能,如有改动请联系总部
													</font>
												</td>
											</tr>
										</table>
									</div>
									<div id="tablelistdiv">
										<table class="listtable" width="100%"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="12">
													揽投部直封集散设置
												</th>
											</thead>
											<thead id="theadPZGN">
												<th align="left"  colspan="2">
													<p id="th_delclfwresorgpz">
													<a href='#' onClick="delclfwtabnjjs();">&nbsp;删除配置</a>
													</p>
												<th  align="left"  colspan="10">
													<p id="th_addclfwresorgpz">
													&nbsp;&nbsp;&nbsp;
													服务属性：
													<input type="radio" name="CLFWTDBNJJS_SFNJ" id="CLFWTDBNJJS_SFNJ" value="1"  checked="checked">参与直封集散
													<a href='#' onClick="addclfwtabnjjs();">&nbsp;添加配置</a>
													</p>
												</th>
											</thead>
											<thead>
												<td align="right" class="listtableheadleft" style='width:30px'>
													<input id="queryAll" name="chooseName" type="checkbox" onClick="queryAll()"/>
												</td>
												<td align="left" class="listtableheadleft" style='width:120px'>
													行政区域
												</td>
												<td align="left" class="listtableheadleft" style='width:200px'>
													揽投部名称
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													揽投部机构代码
												</td>
												<td align="left" class="listtableheadleft1" style='width:80px'>
													服务属性
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
