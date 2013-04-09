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
		<title>频次配置(总部)</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				$("#fliphelpcon").hide();
				$("#fliphelpct").click(function(){
					$("#fliphelpcon").slideToggle("fast");
				});
			})
			function ajaxQueryPage(pageNo){
				if(!(bro.msie && bro.version >=8)){
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				}else{
					$("#nodata").show();
					$("#yesdata").hide();
				}
				var tSEL_CLFWPC_MC=$("#SEL_CLFWPC_MC").val();
				var tRAD_CLFWPC_SX=$("input[name='RAD_CLFWPC_SX'][checked]").val();
				var url = '${ctx}/clfw/b09r02clfwquery!queryclfwpc.action';
			    var params = {
			    		SEL_CLFWPC_MC:tSEL_CLFWPC_MC,
			    		RAD_CLFWPC_SX:tRAD_CLFWPC_SX,
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
						
						tr+="<td align=left class=listtablebodyleft1 style='width:200px'>&nbsp;"+resultdata[i].CLFWPC_MC+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:200px'>&nbsp;"+resultdata[i].CLFWPC_SXSTR+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:200px'>&nbsp;"+resultdata[i].CLFWPC_SM+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:100px'>";
						
						tr+="<a href='#' onClick='upclfwpc(" + resultdata[i].CLFWPC_SEQID + ");'>修改</a>";

						tr+="&nbsp;&nbsp;&nbsp;";
							
						tr+="<a href='#' onClick='delclfwpc(" + resultdata[i].CLFWPC_SEQID + ");'>删除</a>";
						
						tr+="</td>";
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr style='display:none'><td colspan=7 class=listtablebodyleft2>"+data.page.ajaxUrl+"</td></tr>";
					$('#address_table').append(url);
				}else{
					var url="<tr><td colspan=7 class=listtablebodyleft2>找到 0 条记录</td></tr>";
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
		  function addclfwpc(){
				var value = window.showModalDialog("${ctx}/clfw/b09r01clfwview!addclfwpc.action?FLAG=ADD","Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
		  function upclfwpc(tCLFWPC_SEQID){
			  var value = window.showModalDialog("${ctx}/clfw/b09r01clfwview!upclfwpc.action?FLAG=UPDATE&SEQID=" + tCLFWPC_SEQID,"Code","dialogWidth:600px;dialogHeight:500px;resizable=1,scrollbars=auto");
				backfresh(value);
			}
		  function delclfwpc(tCLFWPC_SEQID){
			  $.prompt('该操作将删除该频次信息,并且关联删除该频次下的省，市，投递部和投递段频次配置信息！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v,m){
						if(v){
							var params = {
								CLFWPC_SEQID:tCLFWPC_SEQID
							};
							var url = '${ctx}/clfw/b09cud02clfwconfig!delclfwpc.action';
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
										核心服务>>频次配置(总部)
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
													频次名称:
												</td>
												<td class="js_left_txt" id="td_common_tempid">
													<input type="text" name="SEL_CLFWPC_MC" style="width: 200px; height:20px;" id="SEL_CLFWPC_MC" class="ac_input" size="200"/>
												</td>
												<td class="listtablehead">
													频次属性：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value="1"  checked="checked">省中心/直达
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value="2">揽收
													<input type="radio" name="RAD_CLFWPC_SX" id="RAD_CLFWPC_SX" value="3">投递
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
										<table class="listtable" width="100%"
											id="address_table" cellpadding="0" cellspacing="0">
											<thead>
												<th align="left"  colspan="7">
													频次配置(总部)&nbsp;&nbsp;<a href='#' onClick="addclfwpc();">&nbsp;添加频次</a>
												</th>
											</thead>
											<thead>
												<td align="left" class="listtableheadleft" style='width:200px'>
													频次名称
												</td>
												<td align="left" class="listtableheadleft" style='width:200px'>
													频次属性
												</td>
												<td align="left" class="listtableheadleft1" style='width:200px'>
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
