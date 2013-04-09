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
		<title>投递段配置</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		var bro=$.browser;
			$(document).ready(function(){
				var bro=$.browser;
				$(document).ready(function(){
					backfresh('fresh');
				})
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
				var tCLFWTDB_SEQID=$("#CLFWTDB_SEQID").val();
				var tSEL_PG_NAME=$("#SEL_PG_NAME").val();
				var tSEL_CLFWTDD_CLSX=$("input[name='SEL_CLFWTDD_CLSX'][checked]").val();
				var url = '${ctx}/clfw/b09r02clfwquery!queryclfwtdd.action';
			    var params = {
					    		CLFWTDB_SEQID:tCLFWTDB_SEQID,
					    		SEL_PG_NAME:tSEL_PG_NAME,
					    		SEL_CLFWTDD_CLSX:tSEL_CLFWTDD_CLSX,
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
						var tr="<tr id='tr_query" + resultdata[i].PG_PK_CODE + "' )>";
						tr+=" <td align=right class=listtablebodyleft1 style='width:30px'><input type='checkbox' onclick=xuanze('tr_query" + resultdata[i].PG_PK_CODE + "','rlCheckBox" + resultdata[i].PG_PK_CODE + "') id='rlCheckBox" + resultdata[i].PG_PK_CODE + "'  name='rlCheckBox' value='"+resultdata[i].PG_PK_CODE + "'>";
						tr+="</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].PG_NAME+"</td>";
						tr+="<td align=left class=listtablebodyleft1 style='width:100px'>&nbsp;"+resultdata[i].CLFWTDD_CLSXSTR+"</td>";
						tr+="<td align=left class=listtablebodyleft2 style='width:100px'>&nbsp;"+resultdata[i].OPE_REMARK+"</td>";
						tr+="</tr>";
						$('#address_table').append(tr);
					}
					var url="<tr ><td colspan=8 class=listtablebodyleft2>"+data.page.ajaxUrl+"</td></tr>";
					$('#address_table').append(url);
				}else{
					var url="<tr><td colspan=8 class=listtablebodyleft2>找到 0 条记录</td></tr>";
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
		  function bjclfwtdd(){
				var c=$('#address_table tbody input[name=rlCheckBox]:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
					cv = cv+','+$(this).val();
				});
				if(cv==''){
					$.prompt('请选择投递段信息',{buttons: {'确定': true}});
					return false;
				}
				var tTOTAL_ALL_VALUE=cv.substr(1,cv.length);
				var tCLFWTDD_CLSX=$("input[name='CLFWTDD_CLSX'][checked]").val();
				var tCLFWTDB_SEQID=$("#CLFWTDB_SEQID").val();
				$.prompt('该操作将修改投递段信息！',{
					buttons: {'确定': true, '取消': false },
					callback: function(v){
						if(v){
							var params = {
									PG_PK_CODE:tTOTAL_ALL_VALUE,
									CLFWTDD_CLSX:tCLFWTDD_CLSX,
									CLFWTDB_SEQID:tCLFWTDB_SEQID
							};
							var url = '${ctx}/clfw/b09cud02clfwconfig!bjclfwtdd.action';
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
			<table width="650" border="0" cellpadding="0" cellspacing="0" align="left">
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
										核心服务>>投递段配置
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
										<table class="listtable" id="viewquery_table"  width="650">
											<tr>
												<td class="listtablehead" colspan="4">
													频次：${clfwBean.CLFWPC_MC}&nbsp;&nbsp;&nbsp;揽投部：${clfwBean.DM_NAME}
													<input id="CLFWTDB_SEQID" type="hidden" value="${clfwBean.CLFWTDB_SEQID}"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="100">
													投递段名称:
												</td>
												<td class="js_left_txt" id="td_common_tempid">
													<input type="text" name="SEL_PG_NAME" style="width: 200px; height:20px;" id="SEL_PG_NAME" class="ac_input" size="200"/>
												</td>
												<td class="listtablehead">
													服务属性：
												</td>
												<td class="js_left_txt">
													<input type="radio" name="SEL_CLFWTDD_CLSX" id="SEL_CLFWTDD_CLSX" value=""  checked="checked">全部
													<input type="radio" name="SEL_CLFWTDD_CLSX" id="SEL_CLFWTDD_CLSX" value="1">全是
													<input type="radio" name="SEL_CLFWTDD_CLSX" id="SEL_CLFWTDD_CLSX" value="0">全否
													<input type="radio" name="SEL_CLFWTDD_CLSX" id="SEL_CLFWTDD_CLSX" value="2">未配置
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
												<th align="left"  colspan="8">
													投递段配置
												</th>
											</thead>
											<thead>
												<th align="left"  colspan="8">
													服务属性：
													<input type="radio" name="CLFWTDD_CLSX" id="CLFWTDD_CLSX" value="1"  checked="checked">全是
													<input type="radio" name="CLFWTDD_CLSX" id="CLFWTDD_CLSX" value="0">全否
													<a href='#' onClick="bjclfwtdd();">&nbsp;调整配置</a>
												</th>
											</thead>
											<thead>
												<td align="right" class="listtableheadleft" style='width:30px'>
													<input id="queryAll" name="chooseName" type="checkbox" onClick="queryAll()"/>
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													投递段名称
												</td>
												<td align="left" class="listtableheadleft" style='width:100px'>
													服务属性
												</td>
												<td align="left" class="listtableheadleft1" style='width:100px'>
													备注
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
