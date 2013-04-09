<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<html>
	<head>
		<title>区信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var messageLoading="<img src='${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif'/>";
			function getWindowHeight(id){
				var t=document.getElementById(id).offsetHeight+document.body.scrollTop;
				return t;
			}
			function resource_add(){
				var str="<tr id='add_new'>";
				str+="<td class=listtablebodyleft1>&nbsp;</td>";
				str+="<td class=listtablebodyleft1>&nbsp;</td>";
				str+="<td class=listtablebodyleft1><input type=text value='' size=30></td>";
				str+="<td class=listtablebodyleft1><input type=text value='' size=80></td>";
				str+="<td class=listtablebodyleft1><input type=button value='保存' onclick=resource_save('')>&nbsp;<input type=button value='取消' onclick=resource_remove('add_new')></td></tr>";
				$('#tr_operator').before(str);
				$('#tr_operator td:last').html('&nbsp;');
			}
			function resource_remove(id){
				$('#'+id).remove();
				$('#tr_operator td:last').html('<input type=button value=添加 onclick=resource_add()>');
			}
			function resource_save(id){
				if(id==''){
					//添加
					var t=getWindowHeight("add_new");
					var td=$('#add_new').children('td');
					var rsName=$.trim(td.eq(2).children().eq(0).attr('value'));
					var rsStr=$.trim(td.eq(3).children().eq(0).attr('value'));
					if(rsName=='' || rsStr==''){
						$.prompt('资源名和资源串不能为空',{top:t});
						return false;
					}else{
						var params = {
										rsName: rsName,
										rsStr: rsStr
					             	 };
						var url = '${ctx}/pmn/a01cud01!save.action';
						$.blockUI({ message: messageLoading,css:{width:'200', border:'0'}});
						jQuery.post(url, params, callback_resource_addSave, 'json');
					}
				}else{
					//修改
					var td=$('#alter'+id).children('td');
					var t=getWindowHeight("alter"+id);
					var rsName=$.trim(td.eq(2).children().eq(0).attr('value'));
					var rsStr=$.trim(td.eq(3).children().eq(0).attr('value'));
					if(rsName=='' || rsStr==''){
						$.prompt('资源名和资源串不能为空',{top:t});
						return false;
					}else{
						var params = {
										rsPkId: id,
								  		rsName: rsName,
										rsStr: rsStr
					             	  };
						var url = '${ctx}/pmn/a01cud01!save.action';
						$.blockUI({ message: messageLoading,css:{width:'200', border:'0'}});
						jQuery.post(url, params, callback_resource_save, 'json');
					}
					
				}
			}
			function callback_resource_save(data){
				var td=$('#alter'+data.model.rsPkId).children('td');
				td.eq(0).html("<input type=checkbox value="+data.model.rsPkId+">");
				td.eq(2).html(data.model.rsPkId);
				td.eq(2).html(data.model.rsName);
				td.eq(3).html(data.model.rsStr);
				td.eq(4).html("<input type=button value='修改' onclick=resource_alter('"+data.model.rsPkId+"')>"+data.saveMessage);
				$('#tr_operator td:last').html('<input type=button value=添加 onclick=resource_add()>');
				$.unblockUI();
				$("#saveMessage").fadeOut(4000,function(){$("#saveMessage").remove()}); 
			}
			function callback_resource_addSave(data){
				$("#add_new").remove();
				var t=$("#resource_table_list tbody").children('tr').length-1;
				var str="<tr id=alter"+data.model.rsPkId+">";
				str+="<td class=listtablebodyleft1><input type=checkbox value="+data.model.rsPkId+"></td>";
				str+="<td class=listtablebodyleft1>"+data.model.rsPkId+"</td>";
				str+="<td class=listtablebodyleft1>"+data.model.rsName+"</td>";
				str+="<td class=listtablebodyleft1>"+data.model.rsStr+"</td>";
				str+="<td class=listtablebodyleft1><input type=button value='修改' onclick=resource_alter('"+data.model.rsPkId+"')>"+data.saveMessage+"</td></tr>";
				$("#resource_table_list tbody tr:eq("+t+")").before(str);
				$('#tr_operator td:last').html('<input type=button value=添加 onclick=resource_add()>');
				$.unblockUI();
				$("#saveMessage").fadeOut(4000,function(){$("#saveMessage").remove()});  
			}
			function resource_alter(id){
				var v2=$('#alter'+id).children('td').eq(2);
				var v2p=$.trim(v2.html());
				var v3=$('#alter'+id).children('td').eq(3);
				var v3p=$.trim(v3.html());
				v2.html("<input type=text value="+$.trim(v2.html())+" size=30>");
				v3.html("<input type=text value="+$.trim(v3.html())+" size=80>");
				var v4=$('#alter'+id).children('td').eq(4);
				v4.html("<input type=button value='保存' onclick=resource_save('"+id+"')>&nbsp;<input type=button value='取消' onclick=resource_cancel('"+id+"','"+v2p+"','"+v3p+"')>");
			}
			function resource_cancel(id,rsName,rsStr){
				var v2=$('#alter'+id).children('td').eq(2);
				v2.html(rsName);
				var v3=$('#alter'+id).children('td').eq(3);
				v3.html(rsStr);
				var v4=$('#alter'+id).children('td').eq(4);
				v4.html("<input type=button value='修改' onclick=resource_alter('"+id+"')>");
			}
			function resource_delete(){
				var c=$('#resource_table_list tbody input:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
				    cv = cv+','+$(this).val();
				});
				if(cv!=''){
					$.prompt('确认删除资源么',{ buttons: { '删除': true, '取消': false },callback: function resource_delete_confirm(v,m,f){
						if(v){
							if(cv!=''){
								var params = {
												rsPkIds:cv
							             	 };
								var url = '${ctx}/pmn/a01cud01!delete.action';
								$.blockUI({ message: messageLoading,css:{width:'200', border:'0'}});
								jQuery.post(url, params, callback_resource_delete_confirm, 'json');
							}
						}
							
					}});
				}else{
					$.prompt('请选择要删除的资源');
				}			
			}
			function callback_resource_delete_confirm(data){
				var d=data.rsPkIds;
				var strs= new Array();
				strs=d.split(",");
				for (i=0;i<strs.length ;i++ )    
				   {    
					   $('#alter'+strs[i]).remove();
				   } 			      
				$.unblockUI();
			}
		</script>
	</head>
	<body>
		<s:form namespace="/pmn" action="a01r01" id="ec">
			<table width="1030" border="0" cellpadding="0" cellspacing="0"
				align="left">
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
										资源信息
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
					<td valign="middle" background="${ctx}/images/mail_leftbg.gif">
						&nbsp;
					</td>
					<td valign="top" bgcolor="#F7F8F9" align="center">
						<table>
							<tr>
								<td>
									<div id="querydiv">
										<table class="listtable" id="viewquery_table">
										</table>
									</div>
									<br />
									<div id="tablelistdiv">
										<table class="listtable" width="960"
											id="resource_table_list">
											<tr>
												<th align="left" colspan="5">
													资源信息
												</th>
											</tr>
											<tr>
												<td align="left" class=listtableheadleft width="50">
													&nbsp;
												</td>
												<td align="left" class=listtableheadleft width="50">
													资源ID
												</td>
												<td align="left" class=listtableheadleft width="200">
													资源名
												</td>
												<td align="left" class=listtableheadleft width="500">
													资源串
												</td>
												<td align="left" class=listtableheadleft>
													操作
												</td>
											</tr>
											<s:iterator id="ele" value="resources">
												<tr id="alter${ele.rsPkId}">
													<td align="left" class=listtablebodyleft1>
														<input type="checkbox" value="${ele.rsPkId }" />
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.rsPkId }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.rsName }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.rsStr }
													</td>
													<td align="left" class=listtablebodyleft1>
														<input type="button" value="修改"
															onclick="resource_alter('${ele.rsPkId}')" />
													</td>
												</tr>
											</s:iterator>
											<tr id='tr_operator'>
												<td align="left" class=listtablebodyleft1 colspan="4">
													<input type="button" value="删除"
														onclick="resource_delete()" />
												</td>
												<td align="left" class=listtablebodyleft1>
													<input type="button" value="添加" onclick="resource_add()" />
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td height="30">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
					<td valign="middle" background="${ctx}/images/mail_rightbg.gif">
						&nbsp;
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
		</s:form>
	</body>
</html>
