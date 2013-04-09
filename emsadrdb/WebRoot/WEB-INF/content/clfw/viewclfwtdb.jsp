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
		<title>揽投部频次配置</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				viewcity();
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
				var tSEL_DM_NAME=$("#SEL_DM_NAME").val();
				var tSEL_CLFWTDB_CLSX=$("input[name='SEL_CLFWTDB_CLSX'][checked]").val();
				var tRAD_CLFWPC_SX=$("input[name='RAD_CLFWPC_SX'][checked]").val();
				var tSEL_CLFWPCSHENG_SHENGM=$("#SEL_CLFWPCSHENG_SHENGM").val();
				var tSEL_CLFWPCSHI_SHIGM=$("#SEL_CLFWPCSHI_SHIGM").val();
				var url = '${ctx}/clfw/b09r02clfwquery!queryclfwtdb.action';
			    var params = {
			    				PROVINCE_ID:tPROVINCE_ID,
								CITY_ID:tCITY_ID,
								SEL_DM_NAME:tSEL_DM_NAME,
			    				SEL_CLFWTDB_CLSX:tSEL_CLFWTDB_CLSX,
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
						var tr="<tr id='tr_query" + resultdata[i].CLFWTDB_SEQID + "' )>";
						tr+=" <td align=right class=listtablebodyleft1 style='width:30px'><input type='checkbox' onclick=xuanze('tr_query" + resultdata[i].CLFWTDB_SEQID + "','rlCheckBox" + resultdata[i].CLFWTDB_SEQID + "') id='rlCheckBox" + resultdata[i].CLFWTDB_SEQID + "'  name='rlCheckBox' value='"+resultdata[i].CLFWTDB_SEQID + "'>";
						tr+="</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:120px'>&nbsp;"+resultdata[i].CLFWTDB_DISTCDSTR+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:200px'>&nbsp;"+resultdata[i].CLFWPCSHENG_SHENGM+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:200px'>&nbsp;"+resultdata[i].CLFWPCSHI_SHIGM+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:80px'>&nbsp;"+resultdata[i].CLFWTDB_SJYXSC+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:80px'>&nbsp;"+resultdata[i].CLFWTDB_LSLZB+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:120px'>&nbsp;"+resultdata[i].DM_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].OFFICE_CODE+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:80px'>&nbsp;"+resultdata[i].CLFWTDB_CLSXSTR+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:80px'>&nbsp;"+resultdata[i].CLFWTDB_SHSXSTR+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:120px'>&nbsp;"+resultdata[i].CLFWTDB_SHCZSTR+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:65px'>";
						tr+="<a href='#' onClick='upclfwtdb(" + resultdata[i].CLFWTDB_SEQID + ");'>修改属性</a><br>";
						if(resultdata[i].CLFWTDB_CLSXSTR == 'C-部分'){
							//tr+="<a href='#' onClick='viewclfwtdd(" + resultdata[i].CLFWTDB_SEQID + ");'>投递段维护</a>";
						}
						tr+="</td>";
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr ><td colspan=12 class=listtablebodyleft2>"+data.page.ajaxUrl+"</td></tr>";
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
		  function addclfwtdb(){
				var value = window.showModalDialog("${ctx}/clfw/b09r01clfwview!addclfwtdb.action?FLAG=ADD","Code","dialogWidth:600px;dialogHeight:750px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
		  function delclfwtdb(){
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
				$.prompt('该操作将删除揽投部频次配置信息,并且关联取消段频次配置！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									CLFWTDB_SEQID:tTOTAL_ALL_VALUE
							};
							var url = '${ctx}/clfw/b09cud02clfwconfig!delclfwtdb.action';
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
		  function bjclfwtdb(){
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
				var tCLFWTDB_CLSX=$("input[name='CLFWTDB_CLSX'][checked]").val();
				$.prompt('该操作将修改揽投部频次配置信息！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									CLFWTDB_SEQID:tTOTAL_ALL_VALUE,
									CLFWTDB_CLSX:tCLFWTDB_CLSX
							};
							var url = '${ctx}/clfw/b09cud02clfwconfig!bjclfwtdb.action';
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
		  function upclfwtdb(tCLFWTDB_SEQID){
			  var value = window.showModalDialog("${ctx}/clfw/b09r01clfwview!upcommclfwtdb.action?FLAG=UPDATE&SEQID=" + tCLFWTDB_SEQID,"Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
		  function viewclfwtdd(tCLFWTDB_SEQID){
				var value = window.showModalDialog("${ctx}/clfw/b09r01clfwview!viewclfwtdd.action?SEQID="+ tCLFWTDB_SEQID,"Code","dialogWidth:700px;dialogHeight:700px;resizable=1,scrollbars=auto");
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
										核心服务>>揽投部频次配置
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
												<td class="listtablehead" width="80">
													行政区域:
												</td>
												<td class="js_left_txt" id="td_privince_content">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity();"></s:select>
													<select class="simple" id="CITY_NAMES" style="width:100"></select>
												</td>
												<td class="listtablehead" width="150">
													省处理中心频次名称:
												</td>
												<td class="js_left_txt" id="td_common_tempid">
													<input type="text" name="SEL_CLFWPCSHENG_SHENGM" style="width: 200px; height:20px;" id="SEL_CLFWPCSHENG_SHENGM" class="ac_input" size="200"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="150">
													市衔接频次名称:
												</td>
												<td class="js_left_txt" id="td_privince_content">
													<input type="text" name="SEL_CLFWPCSHI_SHIGM" style="width: 200px; height:20px;" id="SEL_CLFWPCSHI_SHIGM" class="ac_input" size="200"/>
												</td>
												<td class="listtablehead" width="150">
													频次属性：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value=""  checked="checked">全部
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value="2">揽收
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value="3">投递
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="100">
													揽投部名称:
												</td>
												<td class="js_left_txt" id="td_common_tempid">
													<input type="text" name="SEL_DM_NAME" style="width: 200px; height:20px;" id="SEL_DM_NAME" class="ac_input" size="200"/>
													</td>
												<td class="listtablehead">
													服务属性：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="SEL_CLFWTDB_CLSX" id="SEL_CLFWTDB_CLSX" value=""  checked="checked">全部
													<input type="radio" name="SEL_CLFWTDB_CLSX" id="SEL_CLFWTDB_CLSX" value="1">全是
													<input style="display:none" type="radio" name="SEL_CLFWTDB_CLSX" id="SEL_CLFWTDB_CLSX" value="0"><label style="display:none">全否</label>
													<input style="display:none" type="radio" name="SEL_CLFWTDB_CLSX" id="SEL_CLFWTDB_CLSX" value="2"><label style="display:none">部分</label>
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
														无帮助介绍
													</font>
												</td>
											</tr>
										</table>
									</div>
									<div id="tablelistdiv">
										<table class="listtable" width="1300"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="12">
													揽投部频次配置&nbsp;&nbsp;<a href='#' onClick="addclfwtdb();">&nbsp;添加配置</a>
												</th>
											</thead>
											<thead>
												<th align="left"  colspan="12">
													&nbsp;&nbsp;<a href='#' onClick="delclfwtdb();">&nbsp;删除配置</a>
													&nbsp;&nbsp;&nbsp;
													服务属性：
													<input type="radio" name="CLFWTDB_CLSX" id="CLFWTDB_CLSX" value="1"  checked="checked">全是
													<input style="display:none" type="radio" name="CLFWTDB_CLSX" id="CLFWTDB_CLSX" value="0"><label style="display:none">全否</label>
													<input style="display:none" type="radio" name="CLFWTDB_CLSX" id="CLFWTDB_CLSX" value="2"><label style="display:none">部分</label>
													<a href='#' onClick="bjclfwtdb();">&nbsp;调整配置</a>
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
													省处理中心频次名称
												</td>
												<td align="left" class="listtableheadleft" style='width:200px'>
													市运行频次名称
												</td>
												<td align="left" class="listtableheadleft" style='width:80px'>
													运行时长
												</td>
												<td align="left" class="listtableheadleft" style='width:80px'>
													揽收量占比
												</td>
												<td align="left" class="listtableheadleft" style='width:120px'>
													揽投部名称
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													机构代码
												</td>
												<td align="left" class="listtableheadleft" style='width:80px'>
													服务属性
												</td>
												<td align="left" class="listtableheadleft" style='width:80px'>
													审核属性
												</td>
												<td align="left" class="listtableheadleft" style='width:120px'>
													待审核操作
												</td>
												<td align="left" class="listtableheadleft1" style='width:65px'>
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
