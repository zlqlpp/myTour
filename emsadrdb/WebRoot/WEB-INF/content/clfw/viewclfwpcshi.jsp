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
		<title>市[揽/投]衔接频次配置</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				viewcity();

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
				var tSEL_CLFWPC_MC=$("#SEL_CLFWPC_MC").val();
				var tRAD_CLFWPC_SX=$("input[name='RAD_CLFWPC_SX'][checked]").val();
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tPROVINCE_ID=$("#PROVINCE_NAMES").val();
				if(isMunicipalities(tPROVINCE_NAME)){
					var tCITY_ID=tPROVINCE_ID;
			    }else{
					var tCITY_ID=$("#CITY_NAMES").val();
				}

				var tSEL_CLFWPCSHENG_SHENGM=$("#SEL_CLFWPCSHENG_SHENGM").val();
				var tSEL_CLFWPCSHI_SHIGM=$("#SEL_CLFWPCSHI_SHIGM").val();
				
				var url = '${ctx}/clfw/b09r02clfwquery!queryclfwpcshi.action';
			    var params = {
			    		CITY_ID:tCITY_ID,
			    		SEL_CLFWPC_MC:tSEL_CLFWPC_MC,
			    		RAD_CLFWPC_SX:tRAD_CLFWPC_SX,
			    		SEL_CLFWPCSHENG_SHENGM:tSEL_CLFWPCSHENG_SHENGM,
			    		SEL_CLFWPCSHI_SHIGM:tSEL_CLFWPCSHI_SHIGM,
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
						var tr="<tr>";
						tr+="<td align=left class=listtablebodyleft1 style='width:300px'>&nbsp;"+resultdata[i].CLFWPCSHENG_SHENGM+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:150px'>&nbsp;"+resultdata[i].CLFWPCSHI_DISTCDSTR+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:150px'>&nbsp;"+resultdata[i].CLFWPC_MC+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].CLFWPC_SXSTR+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:150px'>&nbsp;"+resultdata[i].CLFWPCSHI_SHIGM+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:150px'>&nbsp;"+resultdata[i].CLFWPCSHI_JZSJ+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:150px'>&nbsp;"+resultdata[i].CLFWPCSHI_JZYH+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:150px'>&nbsp;"+resultdata[i].CLFWPCSHI_SM+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:100px'>&nbsp;";

						tr+="<a href='#' onClick='upclfwpcshi(" + resultdata[i].CLFWPCSHI_SEQID + ");'>修改</a>";
						
						tr+="&nbsp;&nbsp;&nbsp;";

						if('${EMS_USER.rulLevel}' == '0' || '${EMS_USER.rulLevel}' == '2'){
						
						tr+="<a href='#' onClick='delclfwpcshi(" + resultdata[i].CLFWPCSHI_SEQID + ");'>删除</a>";

						}
						
						tr+="</td>";
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr style='display:none'><td colspan=12 class=listtablebodyleft2>"+data.page.ajaxUrl+"</td></tr>";
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
		  function upclfwpcshi(tCLFWPCSHI_SEQID){
			  var value = window.showModalDialog("${ctx}/clfw/b09r01clfwview!upclfwpcshi.action?FLAG=UPDATE&SEQID=" + tCLFWPCSHI_SEQID,"Code","dialogWidth:900px;dialogHeight:500px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
		  function addclfwpcshi(){
			  var value = window.showModalDialog("${ctx}/clfw/b09r01clfwview!addclfwpcshi.action?FLAG=ADD","Code","dialogWidth:900px;dialogHeight:500px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
		  function delclfwpcshi(tCLFWPCSHI_SEQID){
			  $.prompt('该操作将删除该频次信息,并且关联删除该频次下的投递部和投递段频次配置信息！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v,m){
						if(v){
							var params = {
									CLFWPCSHI_SEQID:tCLFWPCSHI_SEQID
							};
							var url = '${ctx}/clfw/b09cud02clfwconfig!delclfwpcshi.action';
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
										核心服务>>市[揽/投]衔接频次配置
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
										<table class="listtable" id="viewquery_table"  width="1000">
											<tr>
												<td class="listtablehead" width="150">
													行政区域:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" style="width:100"></select>
												</td>
												
											</tr>
											<tr>
												<td class="listtablehead" width="150">
													频次名称:
												</td>
												<td class="js_left_txt" id="td_common_tempid">
													<input type="text" name="SEL_CLFWPC_MC" style="width: 200px; height:20px;" id="SEL_CLFWPC_MC" class="ac_input" size="200"/>
												</td>
												<td class="listtablehead" width="150">
													频次属性：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value="2" checked="checked">揽收
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value="3">投递
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="150">
													省处理中心频次名称:
												</td>
												<td class="js_left_txt" id="td_common_tempid">
													<input type="text" name="SEL_CLFWPCSHENG_SHENGM" style="width: 200px; height:20px;" id="SEL_CLFWPCSHENG_SHENGM" class="ac_input" size="200"/>
												</td>
												<td class="listtablehead" width="150">
													市衔接频次名称:
												</td>
												<td class="js_left_txt" id="td_privince_content">
													<input type="text" name="SEL_CLFWPCSHI_SHIGM" style="width: 200px; height:20px;" id="SEL_CLFWPCSHI_SHIGM" class="ac_input" size="200"/>
												</td>
												
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
市[揽/投]衔接频次配置说明：
<br>1、揽收频次：在运距和时长允许情况下，为了频次衔接更加紧密，多个揽收频次可同时衔接一个最近的省中心频次,同一揽收频次不能对应同一省处理中心的多个频次。
<br>2、投递频次：对某个省中心频次而言，每个地市只能有1个衔接最紧密的投递频次覆盖的机构范围，作为核心服务进行维护。不能出现1个省中心频次对应某个地市多个投递频次。
													</font>
												</td>
											</tr>
										</table>
									</div>
									<div id="tablelistdiv">
										<table class="listtable" width="1200"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="10">
													市[揽/投]衔接频次配置&nbsp;&nbsp;<a href='#' onClick="addclfwpcshi();">&nbsp;添加配置</a>
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft" style='width:300px'>
													省处理中心频次名称
												</td>
												<td align="left" class="listtableheadleft" style='width:150px'>
													行政区域
												</td>
												<td align="left" class="listtableheadleft" style='width:150px'>
													频次名称
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													频次属性
												</td>
												<td align="left" class="listtableheadleft" style='width:150px'>
													市衔接频次名称
												</td>
												<td align="left" class="listtableheadleft" style='width:150px'>
													邮航揽收截止点
												</td>
												<td align="left" class="listtableheadleft" style='width:150px'>
													是否同邮航频次
												</td>
												<td align="left" class="listtableheadleft" style='width:150px'>
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
