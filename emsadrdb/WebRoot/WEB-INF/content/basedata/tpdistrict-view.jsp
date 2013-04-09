<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="net.sf.ehcache.Element"%>
<%@page import="com.cpst.emsadrdb.entity.address.OrgDistrict"%>
<%@page import="com.cpst.emsadrdb.entity.address.Tpdistrict"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<html>
	<head>
		<title>区信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			$(document).ready(function(){
				var provinceId="${province.districtCode}";
				var cityId="${city.districtCode}";
				$("#"+provinceId).addClass("curr");
				$("#city_"+cityId).addClass("curr");
				$('#cityId').attr('value',cityId);
			})
			function view_city(provincename,provinceid){
			    var params = {
			    				provinceId: provinceid
			                 };
			    $("#td_privince_content a").removeClass(); 
			    $('#'+provinceid).addClass("curr");
			    $('#hiddenprovinceid').val($('#'+provinceid).text());
			    var url="${ctx}/basedata/querytpdistrict!province.action";
			    jQuery.post(url, params, callback_view_city, 'json');
			}
			function callback_view_city(data){
				var str="";
				var href_function_name="javascript:view_disricts";
				var d=data.citys;
				for(var i=0;i<d.length; i++ ){
					str+="<a id=city_"+d[i].districtCode+" href="+href_function_name+"('"+d[i].districtCode+"')>"+d[i].cityName+"</a>&nbsp;&nbsp;";	
				}
				$('#td_city_content').empty();
				$('#td_city_content').addClass("js_left_txt");
				$('#td_city_content').append(str);
				$("#city_"+data.city.districtCode).addClass("curr");
				addtable(data);
				var footer="<tr id='tr_operator'><td align=left class=listtablebodyleft1 colspan=4><input type=button value='删除' onclick=tpdistrict_delete() /></td><td align=left class=listtablebodyleft1><input type=button value=添加 onclick=tpdistrict_add() /></td></tr>";
				$('#tpdistrict_table_list').append(footer);
				$('#cityId').attr('value',data.city.districtCode);
			}
			function view_disricts(cityId){
			    var params = {
			    				cityId: cityId
			                 };
			    $("#td_city_content a").removeClass(); 
			    $('#city_'+cityId).addClass("curr");
			    var url="${ctx}/basedata/querytpdistrict!city.action";
			    jQuery.post(url, params, callback_view_disricts, 'json');
			}
			function callback_view_disricts(data){
				$("#city_"+data.cityId).addClass("curr");
				addtable(data);
				var footer="<tr id='tr_operator'><td align=left class=listtablebodyleft1 colspan=5><input type=button value='删除' onclick=tpdistrict_delete() /></td><td align=left class=listtablebodyleft1><input type=button value=添加 onclick=tpdistrict_add() /></td></tr>";
				$('#tpdistrict_table_list').append(footer);
			}
			function addtable(data){
				$('#tpdistrict_table_list').remove();
				var t="<table id=tpdistrict_table_list class=listtable width=960><tr><th colspan=6 id=tablelistthcontent align=left>区信息</th></tr></table>";
				$('#tablelistdiv').append(t);
				var tr="<tr><td class=listtableheadleft width=100>&nbsp;</td><td class=listtableheadleft width=200>区名</td><td class=listtableheadleft width=200>别名</td>";
				tr+="<td class=listtableheadleft width=100>是否有部 </td>";
				tr+="<td class=listtableheadleft width=100>所属市 </td><td class=listtableheadleft>操作</td>";
				tr+="</tr>";
				$('#tpdistrict_table_list').append(tr);
				if(data.tpdistricts.length>0){
					var resultdata=data.tpdistricts;
					for(var i=0;i<resultdata.length;i++){
						var td="<tr id=alter"+resultdata[i].pdistrictId+"><td class=listtablebodyleft1><input type=checkbox name=strtCheckBox value="+resultdata[i].pdistrictId+"><img id=td_"+resultdata[i].pdistrictId+"_img src=${ctx}/images/mini_icons_098.gif style=cursor:hand onclick=javascript:view_subs('"+resultdata[i].pdistrictId+"')></img></td>";
						td+="<td class=listtablebodyleft1>"+resultdata[i].name+"</td>";
						td+="<td class=listtablebodyleft1>"+resultdata[i].aliasName+"</td>";
						td+="<td class=listtablebodyleft1>"+resultdata[i].haveDepartmentName+"</td>";
						td+="<td class=listtablebodyleft1>"+data.city.cityName+"</td>";
						td+="<td class=listtablebodyleft1><input type=button value=修改 onclick=tpdistrict_alter('"+resultdata[i].pdistrictId+"','"+resultdata[i].pdistrictId+"')></td>";
						td+="</tr>";
						$('#tpdistrict_table_list').append(td);
					}
				}
			}
			function makeup_tpdistrict_select(pdistrictId,deliveryValue){
				var s="";
				if(pdistrictId==''){
					//新增
					s+="<select id=tpdistrict_option_new >";
					s+="<option value=0>没有</option>";
					s+="<option value=1>有</option>";
					s+="</select>";
				}else{
					s+="<select id=tpdistrict_option_alter"+pdistrictId+" >";
					if(deliveryValue=='有'){
						s+="<option value=1 selected=selected>"+deliveryValue+"</option>";
						s+="<option value=0 >没有</option>";
					}else{
						s+="<option value=0 selected=selected>"+deliveryValue+"</option>";
						s+="<option value=1 >有</option>";
					}
					s+="</select>";
				}
				return s;
			}
			function tpdistrict_alter(pdistrictId){
				if($('#tpdistrict_subs_'+pdistrictId).length>0){
					$('#td_'+pdistrictId+'_img').attr('src','${ctx}/images/mini_icons_098.gif');
					$('#tpdistrict_subs_'+pdistrictId).remove();
				}
				var v1=$('#alter'+pdistrictId).children('td').eq(1);
				var v1p=$.trim(v1.html());
				var v2=$('#alter'+pdistrictId).children('td').eq(2);
				var v2p=$.trim(v2.html());
				var v3=$('#alter'+pdistrictId).children('td').eq(3);
				var v3p=$.trim(v3.html());
				v1.html("<input type=text value="+$.trim(v1.html())+">");
				v2.html("<input type=text value="+$.trim(v2.html())+">");
				v3.html(makeup_tpdistrict_select(pdistrictId,v3p));
				var v5=$('#alter'+pdistrictId).children('td').eq(5);
				v5.html("<input type=button value='保存' onclick=tpdistrict_save('"+pdistrictId+"')>&nbsp;<input type=button value='取消' onclick=tpdistrict_cancel('"+pdistrictId+"','"+v1p+"','"+v2p+"','"+v3p+"')>");
			}
			
			function tpdistrict_cancel(id,name,aliasName,departmentName){
				var v1=$('#alter'+id).children('td').eq(1);
				v1.html(name);
				var v2=$('#alter'+id).children('td').eq(2);
				v2.html(aliasName);
				var v3=$('#alter'+id).children('td').eq(3);
				v3.html(departmentName);
				var v5=$('#alter'+id).children('td').eq(5);
				v5.html("<input type=button value='修改' onclick=tpdistrict_alter('"+id+"')>");
			}
			function tpdistrict_add(){
				var params = {
							cityId:$('#cityId').attr('value')
						};
				var url = '${ctx}/basedata/querytpdistrict!queryCityName.action';
				jQuery.post(url, params, callback_tpdistrict_add, 'json');
			}
			function callback_tpdistrict_add(data){
				$('#tr_operator').before("<tr id='add_new'><td class=listtablebodyleft1>&nbsp;</td><td class=listtablebodyleft1><input type=text value=''></td><td class=listtablebodyleft1><input type=text value=''></td><td class=listtablebodyleft1>"+makeup_tpdistrict_select('','')+"</td><td class=listtablebodyleft1>"+data.city.cityName+"</td><td class=listtablebodyleft1><input type=button value='保存' onclick=tpdistrict_save('')>&nbsp;<input type=button value='取消' onclick=tpdistrict_remove('add_new')></td></tr>");
				$('#tr_operator td:last').html('&nbsp;');
			}
			function tpdistrict_remove(id){
				$('#'+id).remove();
				$('#tr_operator td:last').html('<input type=button value=添加 onclick=tpdistrict_add()>');
			}
			function tpdistrict_save(id){
				if(id==''){
					//添加
					var td=$('#add_new').children('td');
					var t=document.getElementById("add_new").offsetHeight+document.body.scrollTop;
					var name=$.trim(td.eq(1).children().eq(0).attr('value'));
					var aliasName=$.trim(td.eq(2).children().eq(0).attr('value'));
					var haveDepartment=$.trim(td.eq(3).children().eq(0).attr('value'));
					if(name==''){
						$.prompt('请输入区名',{top:t});
						return false;
					}else{
						var params = {
								  name:name,
								  aliasName: aliasName,
								  haveDepartment :haveDepartment,
								  cityId:$('#cityId').attr('value')
					             };
						var url = '${ctx}/basedata/savetpdistrict!save.action';
						$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
						jQuery.post(url, params, callback_tpdistrict_addSave, 'json');
					}
				}else{
					//修改
					var td=$('#alter'+id).children('td');
					var t=document.getElementById("alter"+id).offsetHeight+document.body.scrollTop;
					var name=$.trim(td.eq(1).children().eq(0).attr('value'));
					var aliasName=$.trim(td.eq(2).children().eq(0).attr('value'));
					var haveDepartment=$.trim(td.eq(3).children().eq(0).attr('value'));
					if(name==''){
						$.prompt('请输入区名',{top:t});
						return false;
					}else{
						var params = {
								  pdistrictId: id,
								  name:name,
								  aliasName: aliasName,
								  haveDepartment :haveDepartment,
								  cityId:$('#cityId').attr('value')
					             };
						var url = '${ctx}/basedata/savetpdistrict!save.action';
						$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
						jQuery.post(url, params, callback_tpdistrict_save, 'json');
					}
					
				}
			}
			function callback_tpdistrict_save(data){
				var td=$('#alter'+data.model.pdistrictId).children('td');
				td.eq(0).html("<input type=checkbox value="+data.model.pdistrictId+">&nbsp;<img id=td_"+data.model.pdistrictId+"_img src=${ctx}/images/mini_icons_098.gif style=cursor:hand onclick=javascript:view_subs('"+data.model.pdistrictId+"')></img>");
				td.eq(1).html(data.model.name);
				td.eq(2).html(data.model.aliasName);
				td.eq(3).html(data.model.haveDepartmentName);
				td.eq(4).html(data.cityName);
				td.eq(5).html("<input type=button value='修改' onclick=tpdistrict_alter('"+data.model.pdistrictId+"','"+data.cityId+"')>"+data.saveMessage);
				$('#tr_operator td:last').html('<input type=button value=添加 onclick=tpdistrict_add()>');
				$.unblockUI();
				$("#saveMessage").fadeOut(4000,function(){$("#saveMessage").remove()}); 
			}
			function callback_tpdistrict_addSave(data){
				$("#add_new").remove();
				var t=$("#tpdistrict_table_list tbody").children('tr').length-1;
				$("#tpdistrict_table_list tbody tr:eq("+t+")").before("<tr id=alter"+data.model.pdistrictId+"><td class=listtablebodyleft1><input type=checkbox value="+data.model.pdistrictId+">&nbsp;<img id=td_"+data.pdistrictId+"_img src=${ctx}/images/mini_icons_098.gif style=cursor:hand onclick=javascript:view_subs('"+data.model.pdistrictId+"')></img></td><td class=listtablebodyleft1>"+data.model.name+"</td><td class=listtablebodyleft1>"+data.model.aliasName+"</td><td class=listtablebodyleft1>"+data.model.haveDepartmentName+"</td><td class=listtablebodyleft1>"+data.cityName+"</td><td class=listtablebodyleft1><input type=button value='修改' onclick=tpdistrict_alter('"+data.model.pdistrictId+"','"+data.cityId+"')>"+data.saveMessage+"</td></tr>");
				$('#tr_operator td:last').html('<input type=button value=添加 onclick=tpdistrict_add()>');
				$.unblockUI();
				$("#saveMessage").fadeOut(4000,function(){$("#saveMessage").remove()});  
			}
			function tpdistrict_delete(){
				var c=$('#tpdistrict_table_list tbody input:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
				    cv = cv+','+$(this).val();
				});
				if(cv!=''){
					$.prompt('如果删除该区，该区下面的部信息、段信息、区和邮编匹配信息、以及段信息和名址匹配信息都会丢失',{ buttons: { '删除': true, '取消': false },callback: tpdistrict_delete_confirm});
				}else{
					$.prompt('请选择要删除的区');
				}			
			}
			function tpdistrict_delete_confirm(v,m,f){
				if(v){
					var c=$('#tpdistrict_table_list tbody input:checked');
					var cv='';              //定义变量
					c.each(function(i){        //循环拼装被选中项的值
					    cv = cv+','+$(this).val();
					});
					if(cv!=''){
						var params = {
										pdistrictIds:cv
					             	 };
						var url = '${ctx}/basedata/savetpdistrict!delete.action';
						$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
						jQuery.post(url, params, callback_tpdistrict_delete_confirm, 'json');
					}
				}
					
			}
			function callback_tpdistrict_delete_confirm(data){
				if(data.delNum!=0){
					var d=data.pdistrictIds;
					var strs= new Array();
					strs=d.split(",");
					for (i=0;i<strs.length ;i++ )    
				    {    
					    if($('#tpdistrict_subs_'+strs[i]).length>0){
					    	$('#tpdistrict_subs_'+strs[i]).remove();
					    }
					    $('#alter'+strs[i]).remove();
				    } 			      
				}
				$.unblockUI();
			}
			function add_trsubtable(pdistrictId,haveDepartment){
				$('#alter'+pdistrictId).after("<tr id=tpdistrict_subs_"+pdistrictId+"><td colspan=7><table id=subTable"+pdistrictId+"></table></td></tr>");
				$('#subTable'+pdistrictId).append("<tr><td width=50>&nbsp;</td><td id=subTable_td_"+pdistrictId+"></td></tr></tr>");
				if(haveDepartment=='1'){
					$('#subTable_td_'+pdistrictId).append("<table id=departmentTable"+pdistrictId+" width=870></table>");	
				}
				$('#subTable_td_'+pdistrictId).append("<table id=postSegTable"+pdistrictId+" width=870></table>");
				$('#subTable_td_'+pdistrictId).append("<table id=postCdTable"+pdistrictId+" width=870></table>");
			}
			function add_departmentTable(pdistrictId,departments){
				var department=$('#departmentTable'+pdistrictId);
				department.append("<thead><tr><td align=left id=departmentTable_th_"+pdistrictId+" colspan=7 class=listtableth>该区部信息</td></tr></thead>");
				department.append("<tr><td class=listtableheadleft width=100>&nbsp;</td><td class=listtableheadleft width=100>部名</td><td class=listtableheadleft width=300>别名</td><td class=listtableheadleft>操作</td></tr>");
				for(var i=0;i<departments.length;i++){
					department.append("<tr id=tr_department_data_"+departments[i].departmentId+"><td class=listtablebodyleft1><input type=checkbox value="+departments[i].departmentId+"></td><td class=listtablebodyleft1>"+departments[i].name+"</td><td class=listtablebodyleft1>"+departments[i].aliasName+"</td><td class=listtablebodyleft1><input type=button value='修改' onclick=department_alter('"+departments[i].departmentId+"','"+pdistrictId+"')></td></tr>");
				}
				department.append("<tr id=tr_department_oprator_"+pdistrictId+"><td class=listtablebodyleft1><input type=button value=删除 onclick=department_delete("+pdistrictId+")></td><td class=listtablebodyleft1 colspan=2>&nbsp;</td><td class=listtablebodyleft1><input type=button value=添加 onclick=department_add("+pdistrictId+")></td></tr>");
			}
			function add_postSegTable(pdistrictId,postSegs){
				var postSeg=$('#postSegTable'+pdistrictId);
				postSeg.append("<thead><tr><td align=left id=departmentTable_th_"+pdistrictId+" colspan=7 class=listtableth>该区段信息</td></tr></thead>");
				postSeg.append("<tr><td class=listtableheadleft width=100>&nbsp;</td><td class=listtableheadleft width=100>段名</td><td class=listtableheadleft width=100>别名</td><td class=listtableheadleft width=100>是否是投递段</td><td class=listtableheadleft width=100>是否是揽收段</td><td class=listtableheadleft width=100>所属部</td><td class=listtableheadleft>操作</td></tr>");
				for(var i=0;i<postSegs.length;i++){
					postSeg.append("<tr id=tr_postSeg_data_"+postSegs[i].postsegId+"><td class=listtablebodyleft1><input type=checkbox value="+postSegs[i].postsegId+"></td><td class=listtablebodyleft1>"+postSegs[i].postsegName+"</td><td class=listtablebodyleft1>"+postSegs[i].aliasName+"</td><td class=listtablebodyleft1>"+postSegs[i].deliveryName+"</td><td class=listtablebodyleft1>"+postSegs[i].receiveName+"</td><td class=listtablebodyleft1>"+postSegs[i].departmentName+"</td><td class=listtablebodyleft1><input type=button value='修改' onclick=tpostSeg_alter('"+pdistrictId+"','"+postSegs[i].postsegId+"')></td></tr>");
				}
				postSeg.append("<tr id=tr_postSeg_oprator_"+pdistrictId+"><td class=listtablebodyleft1><input type=button value=删除 onclick=tpostSeg_delete("+pdistrictId+")></td><td class=listtablebodyleft1 colspan=5>&nbsp;</td><td class=listtablebodyleft1><input type=button value=添加 onclick=tpostSeg_add("+pdistrictId+")></td></tr>");
			}
			function add_postCdTable(pdistrictId,postCds){
				var postCd=$('#postCdTable'+pdistrictId);
				postCd.append("<thead><tr><td align=left id=postCdTable_th_"+pdistrictId+" colspan=7 class=listtableth>该区邮编信息</td></tr></thead>");
				postCd.append("<tr><td class=listtableheadleft width=100>&nbsp;</td><td class=listtableheadleft width=100>邮编</td><td class=listtableheadleft>操作</td></tr>");
				for(var i=0;i<postCds.length;i++){
					postCd.append("<tr id=tr_postCd_data_"+postCds[i].postcdId+"><td class=listtablebodyleft1><input type=checkbox value="+postCds[i].postcdId+"></td><td class=listtablebodyleft1>"+postCds[i].postCode+"</td><td class=listtablebodyleft1><input type=button value='修改' onclick=tpostCd_alter('"+postCds[i].postcdId+"')></td></tr>");
				}
				postCd.append("<tr id=tr_postCd_oprator_"+pdistrictId+"><td class=listtablebodyleft1><input type=button value=删除 onclick=tpostCd_delete("+pdistrictId+")></td><td class=listtablebodyleft1>&nbsp;</td><td class=listtablebodyleft1><input type=button value=添加 onclick=tpostCd_add("+pdistrictId+")></td></tr>");
			}
			function view_subs(tpdistrictId){
				if($('#tpdistrict_subs_'+tpdistrictId).length>0){
					$('#tpdistrict_subs_'+tpdistrictId).remove();
					$('#td_'+tpdistrictId+'_img').attr('src','${ctx}/images/mini_icons_098.gif');
				}else{
					var url = '${ctx}/basedata/querytpdistrict!subs.action';
				    var params = {
				    			tpdistrictId:tpdistrictId
				                 };
				    jQuery.post(url, params, callback_view_subs, 'json');
				}
			}
			function callback_view_subs(data){
				$('#td_'+data.tpdistrictId+'_img').attr('src','${ctx}/images/mini_icons_093.gif');
				add_trsubtable(data.tpdistrictId,data.tpdistrict.haveDepartment);
				if(data.tpdistrict.haveDepartment=='1'){
					add_departmentTable(data.tpdistrictId,data.tdepartments);
				}
				add_postSegTable(data.tpdistrictId,data.tpostSegs);
				add_postCdTable(data.tpdistrictId,data.tpostCds);
			}

			function department_add(pdistrictId){
				$('#tr_department_oprator_'+pdistrictId).before("<tr id=department_add_new_"+pdistrictId+"><td class=listtablebodyleft1>&nbsp;</td><td class=listtablebodyleft1><input type=text value=''></td><td class=listtablebodyleft1><input type=text value=''></td><td class=listtablebodyleft1><input type=button value='保存' onclick=department_save('','"+pdistrictId+"')>&nbsp;<input type=button value='取消' onclick=department_remove("+pdistrictId+")></td></tr>");
				$('#tr_department_oprator_'+pdistrictId+' td:last').html('&nbsp;');
			}
			function department_remove(pdistrictId){
				$('#department_add_new_'+pdistrictId).remove();
				$('#tr_department_oprator_'+pdistrictId+' td:last').html('<input type=button value=添加 onclick=department_add('+pdistrictId+')>');
			}
			function department_save(id,pdistrictId){
				if(id==''){
					//添加
					var t=document.getElementById("department_add_new_"+pdistrictId).offsetHeight+document.body.scrollTop;
					var td=$('#department_add_new_'+pdistrictId).children('td');
					var name=$.trim(td.eq(1).children().eq(0).attr('value'));
					var aliasName=$.trim(td.eq(2).children().eq(0).attr('value'));
					if(name==''){
						$.prompt('部名不能为空',{top:t});
						return false;
					}
					var params = {
								name: name,
								aliasName: aliasName,
							  	pdistrictId:pdistrictId
				             };
					var url = '${ctx}/basedata/savetdepartment!save.action';
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
					jQuery.post(url, params, callback_department_addSave, 'json');
					
				}else{
					//修改
					var t=document.getElementById("tr_department_data_"+id).offsetHeight+document.body.scrollTop;
					var td=$('#tr_department_data_'+id).children('td');
					var name=$.trim(td.eq(1).children().eq(0).attr('value'));
					var aliasName=$.trim(td.eq(2).children().eq(0).attr('value'));
					if(name==''){
						$.prompt('段名不能为空',{top:t});
						return false;
					}
					var params = {
								departmentId: id,
								name: name,
								aliasName: aliasName,
								pdistrictId: pdistrictId
				             };
					var url = '${ctx}/basedata/savetdepartment!save.action';
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
					jQuery.post(url, params, callback_department_save, 'json');
				}
			}
			function callback_department_save(data){
				var td=$('#tr_department_data_'+data.model.departmentId).children('td');
				td.eq(0).html("<input type=checkbox value="+data.model.departmentId+"/>&nbsp;");
				td.eq(1).html(data.model.name);
				td.eq(2).html(data.model.aliasName);
				td.eq(3).html("<input type=button value='修改' onclick=department_alter('"+data.model.departmentId+"','"+data.pdistrictId+"')>"+data.saveMessage);
				$('#tr_department_oprator_'+data.pdistrictId+' td:last').html('<input type=button value=添加 onclick=department_add('+data.pdistrictId+')>');
				$.unblockUI();
				$("#tdepartmentSaveMessage").fadeOut(4000,function(){$("#tdepartmentSaveMessage").remove()}); 
			}
			function callback_department_addSave(data){
				$("#department_add_new_"+data.pdistrictId).remove();
				var t=$("#departmentTable"+data.pdistrictId+" tbody").children('tr').length-1;
				$("#departmentTable"+data.pdistrictId+" tbody tr:eq("+t+")").before("<tr id=tr_department_data_"+data.model.departmentId+"><td class=listtablebodyleft1><input type=checkbox value="+data.model.departmentId+">&nbsp;</td><td class=listtablebodyleft1>"+data.model.name+"</td><td class=listtablebodyleft1>"+data.model.aliasName+"</td><td class=listtablebodyleft1><input type=button value='修改' onclick=department_alter('"+data.model.departmentId+"','"+data.pdistrictId+"')>"+data.saveMessage+"</td></tr>");
				$('#tr_department_oprator_'+data.pdistrictId+' td:last').html('<input type=button value=添加 onclick=department_add('+data.pdistrictId+')>');
				$.unblockUI();
				$("#tdepartmentSaveMessage").fadeOut(4000,function(){$("#tdepartmentSaveMessage").remove()}); 
			}
			function department_alter(departmentId,pdistrictId){
				var v1=$('#tr_department_data_'+departmentId).children('td').eq(1);
				var v1p=$.trim(v1.html());
				v1.html("<input type=text value="+$.trim(v1.html())+">");
				var v2=$('#tr_department_data_'+departmentId).children('td').eq(2);
				var v2p=$.trim(v2.html());
				v2.html("<input type=text value="+$.trim(v2.html())+">");
				var v3=$('#tr_department_data_'+departmentId).children('td').eq(3);
				v3.html("<input type=button value='保存' onclick=department_save('"+departmentId+"','"+pdistrictId+"')>&nbsp;<input type=button value='取消' onclick=department_cancel('"+departmentId+"','"+v1p+"','"+v2p+"','"+pdistrictId+"')>");
			}
			function department_cancel(departmentId,name,aliasName,pdistrictId){
				var v1=$('#tr_department_data_'+departmentId).children('td').eq(1);
				v1.html(name);
				var v2=$('#tr_department_data_'+departmentId).children('td').eq(2);
				v2.html(aliasName);
				var v3=$('#tr_department_data_'+departmentId).children('td').eq(3);
				v3.html("<input type=button value='修改' onclick=department_alter('"+departmentId+"','"+pdistrictId+"')>");
			}
			function department_delete(pdistrictId){
				var t=document.getElementById("departmentTable"+pdistrictId).offsetHeight+document.body.scrollTop;
				var c=$('#departmentTable'+pdistrictId+' tbody input:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
				    cv = cv+','+$(this).val();
				});
				if(cv!=''){
					$.prompt('确认删除么',{ buttons: { '删除': true, '取消': false },top:t,callback: function department_delete_confirm(v,m,f){
						if(v){
							if(cv!=''){
								var params = {
												departmentIds:cv
							             	 };
								var url = '${ctx}/basedata/savetdepartment!delete.action';
								$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
								jQuery.post(url, params, callback_department_delete_confirm, 'json');
							}
						}
							
					}});
				}else{
					$.prompt('请选择要删除的段',{top:t});
				}			
			}
			function callback_department_delete_confirm(data){
				if(data.delNum!=0){
					var d=data.departmentIds;
					var strs= new Array();
					strs=d.split(",");
					for (i=0;i<strs.length ;i++ )    
				    {    
					    $('#tr_department_data_'+strs[i]).remove();
				    } 			      
				}
				$.unblockUI();
			}
			
			function makeup_delivery_select(pdistrictId,tpostSegId,deliveryValue){
				var s="";
				if(tpostSegId==''){
					//新增
					s+="<select id=tpostseg_option_delivery_new"+tpostSegId+" >";
					s+="<option value=1>是</option>";
					s+="<option value=0>否</option>";
					s+="</select>";
				}else{
					s+="<select id=tpostseg_option_delivery_alter"+tpostSegId+" >";
					if(deliveryValue=='是'){
						s+="<option value=1 selected=selected>是</option>";
						s+="<option value=0 >否</option>";
					}else{
						s+="<option value=1 >是</option>";
						s+="<option value=0 selected=selected>否</option>";
					}
					s+="</select>";
				}
				return s;
			}
			function makeup_receive_select(pdistrictId,tpostSegId,receiveValue){
				var s="";
				if(tpostSegId==''){
					//新增
					s+="<select id=tpostseg_option_receive_new"+pdistrictId+" >";
					s+="<option value=1>是</option>";
					s+="<option value=0>否</option>";
					s+="</select>";
				}else{
					s+="<select id=tpostseg_option_receive_alter"+tpostSegId+" >";
					if(receiveValue=='是'){
						s+="<option value=1 selected=selected>是</option>";
						s+="<option value=0 >否</option>";
					}else{
						s+="<option value=1 >是</option>";
						s+="<option value=0 selected=selected>否</option>";
					}
					s+="</select>";
				}
				return s;
			}
			function makeup_department_select(pdistrictId,tpostSegId,departments,flag,departmentValue){
				var s="";
				if(tpostSegId==''){
					//新增
					s+="<select id=tpostseg_option_department_new"+pdistrictId+" >";
					if(flag=='0'){
						s+="<option value=''>无</option>";
					}else{
						s+="<option value=''>无</option>";
						if(departments!=null){
							for(var i=0;i<departments.length;i++){
								s+="<option value="+departments[i].departmentId+">"+departments[i].name+"</option>";
							}
						}
					}
					s+="</select>";
				}else{
					s+="<select id=tpostseg_option_department_alter"+tpostSegId+" >";
					if(flag=='0'){
						s+="<option value=''>无</option>";
					}else{
						s+="<option value=''>无</option>";
						if(departments!=null){
							for(var i=0;i<departments.length;i++){
								if(departmentValue==departments[i].name){
									s+="<option value="+departments[i].departmentId+" selected=selected>"+departments[i].name+"</option>";
								}else{
									s+="<option value="+departments[i].departmentId+">"+departments[i].name+"</option>";
								}
							}
						}
					}
					
					s+="</select>";
				}
				return s;
			}
			function tpostSeg_alter(pdistrictId,tpostSegId){
				var params = {
						tpostSegId :tpostSegId,
					  	pdistrictId: pdistrictId
		             };
				var url = '${ctx}/basedata/querytpdistrict01!queryTdepartment.action';
				jQuery.post(url, params, callback_tpostSeg_alter, 'json');
			}
			function callback_tpostSeg_alter(data){
				var v1=$('#tr_postSeg_data_'+data.tpostSegId).children('td').eq(1);
				var v1p=$.trim(v1.html());
				v1.html("<input type=text value="+$.trim(v1.html())+">");
				var v2=$('#tr_postSeg_data_'+data.tpostSegId).children('td').eq(2);
				var v2p=$.trim(v2.html());
				v2.html("<input type=text value="+$.trim(v2.html())+">");
				var v3=$('#tr_postSeg_data_'+data.tpostSegId).children('td').eq(3);
				var v3p=$.trim(v3.html());
				v3.html(makeup_delivery_select('',data.tpostSegId,v3p));
				var v4=$('#tr_postSeg_data_'+data.tpostSegId).children('td').eq(4);
				var v4p=$.trim(v4.html());
				v4.html(makeup_receive_select('',data.tpostSegId,v4p));
				var v5=$('#tr_postSeg_data_'+data.tpostSegId).children('td').eq(5);
				var v5p=$.trim(v5.html());
				v5.html(makeup_department_select(data.pdistrictId,data.tpostSegId,data.tdepartments,data.tpdistrict,v5p));
				var v6=$('#tr_postSeg_data_'+data.tpostSegId).children('td').eq(6);
				v6.html("<input type=button value='保存' onclick=tpostSeg_save('"+data.tpostSegId+"',"+data.pdistrictId+")>&nbsp;<input type=button value='取消' onclick=tpostSeg_cancel('"+data.pdistrictId+"','"+data.tpostSegId+"','"+v1p+"','"+v2p+"','"+v3p+"','"+v4p+"','"+v5p+"')>");
			}
			function tpostSeg_cancel(pdistrictId,tpostSegId,tpostSegName,aliasName,deliveryName,receiveName,departmentName){
				var v1=$('#tr_postSeg_data_'+tpostSegId).children('td').eq(1);
				v1.html(tpostSegName);
				var v2=$('#tr_postSeg_data_'+tpostSegId).children('td').eq(2);
				v2.html(aliasName);
				var v3=$('#tr_postSeg_data_'+tpostSegId).children('td').eq(3);
				v3.html(deliveryName);
				var v4=$('#tr_postSeg_data_'+tpostSegId).children('td').eq(4);
				v4.html(receiveName);
				var v5=$('#tr_postSeg_data_'+tpostSegId).children('td').eq(5);
				v5.html(departmentName);
				var v6=$('#tr_postSeg_data_'+tpostSegId).children('td').eq(6);
				v6.html("<input type=button value='修改' onclick=tpostSeg_alter('"+pdistrictId+"','"+tpostSegId+"')>");
			}
			function tpostSeg_save(id,pdistrictId){
				if(id==''){
					//添加
					var t=document.getElementById("tpostSeg_add_new_"+pdistrictId).offsetHeight+document.body.scrollTop;
					var td=$('#tpostSeg_add_new_'+pdistrictId).children('td');
					var name=$.trim(td.eq(1).children().eq(0).attr('value'));
					var aliasName=$.trim(td.eq(2).children().eq(0).attr('value'));
					if(name==''){
						$.prompt('段名不能为空',{top:t});
						return false;
					}
					var isDelivery=$.trim(td.eq(3).children().eq(0).attr('value'));
					var isReceive=$.trim(td.eq(4).children().eq(0).attr('value'));
					var departmentId=$.trim(td.eq(5).children().eq(0).attr('value'));
					var params = {
								postsegName:name,
								aliasName: aliasName,
							  	pdistrictId:pdistrictId,
							  	isDelivery:isDelivery,
							  	isReceive:isReceive,
							  	departmentId: departmentId
				             };
					var url = '${ctx}/basedata/savetpostseg!save.action';
					if(isDelivery=='0' && isReceive=='0'){
						$.prompt('段不能既不是投递段，也不是揽收段',{top:t});
						return false;
					}else{
						$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
						jQuery.post(url, params, callback_tpostSeg_addSave, 'json');
					}
					
					
				}else{
					//修改
					var t=document.getElementById("tr_postSeg_data_"+id).offsetHeight+document.body.scrollTop;
					var td=$('#tr_postSeg_data_'+id).children('td');
					var name=$.trim(td.eq(1).children().eq(0).attr('value'));
					var aliasName=$.trim(td.eq(2).children().eq(0).attr('value'));
					if(name==''){
						$.prompt('段名不能为空',{top:t});
						return false;
					}
					var isDelivery=$.trim(td.eq(3).children().eq(0).attr('value'));
					var isReceive=$.trim(td.eq(4).children().eq(0).attr('value'));
					var departmentId=$.trim(td.eq(5).children().eq(0).attr('value'));
					var params = {
								postsegId: id,
								postsegName: name,
								aliasName: aliasName,
								pdistrictId:pdistrictId,
								isDelivery:isDelivery,
							  	isReceive:isReceive,
							  	departmentId: departmentId
				             };
					var url = '${ctx}/basedata/savetpostseg!save.action';
					if(isDelivery=='0' && isReceive=='0'){
						$.prompt('段不能既不是投递段，也不是揽收段',{top:t});
						return false;
					}else{
						$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
						jQuery.post(url, params, callback_tpostSeg_save, 'json');
					}
				}
			}
			function callback_tpostSeg_save(data){
				var td=$('#tr_postSeg_data_'+data.model.postsegId).children('td');
				td.eq(0).html("<input type=checkbox value="+data.model.postsegId+">&nbsp;");
				td.eq(1).html(data.model.postsegName);
				td.eq(2).html(data.model.aliasName);
				td.eq(3).html(data.model.deliveryName);
				td.eq(4).html(data.model.receiveName);
				td.eq(5).html(data.departmentName);
				td.eq(6).html("<input type=button value='修改' onclick=tpostSeg_alter('"+data.pdistrictId+"','"+data.model.postsegId+"')>"+data.saveMessage);
				$('#tr_postSeg_oprator_'+data.pdistrictId+' td:last').html('<input type=button value=添加 onclick=tpostSeg_add('+data.pdistrictId+')>');
				$.unblockUI();
				$("#tpostSegSaveMessage").fadeOut(4000,function(){$("#tpostSegSaveMessage").remove()}); 
			}
			function callback_tpostSeg_addSave(data){
				if(data.delNum==-1){
					$("#tpostSeg_add_new_"+data.pdistrictId).remove();
					$('#tr_postSeg_oprator_'+data.pdistrictId+' td:last').html('<input type=button value=添加 onclick=tpostSeg_add('+data.pdistrictId+')>'+data.saveMessage);
				}else{
					$("#tpostSeg_add_new_"+data.pdistrictId).remove();
					var t=$("#postSegTable"+data.pdistrictId+" tbody").children('tr').length-1;
					$("#postSegTable"+data.pdistrictId+" tbody tr:eq("+t+")").before("<tr id=tr_postSeg_data_"+data.model.postsegId+"><td class=listtablebodyleft1><input type=checkbox value="+data.model.postsegId+">&nbsp;</td><td class=listtablebodyleft1>"+data.model.postsegName+"</td><td class=listtablebodyleft1>"+data.model.aliasName+"</td><td class=listtablebodyleft1>"+data.model.deliveryName+"</td><td class=listtablebodyleft1>"+data.model.receiveName+"</td><td class=listtablebodyleft1>"+data.departmentName+"</td><td class=listtablebodyleft1><input type=button value='修改' onclick=tpostSeg_alter('"+data.pdistrictId+"','"+data.model.postsegId+"')>"+data.saveMessage+"</td></tr>");
					$('#tr_postSeg_oprator_'+data.pdistrictId+' td:last').html('<input type=button value=添加 onclick=tpostSeg_add('+data.pdistrictId+')>');
				}
				$.unblockUI();
				$("#tpostSegSaveMessage").fadeOut(4000,function(){$("#tpostSegSaveMessage").remove()}); 
			}
			function tpostSeg_add(pdistrictId){
				var params = {
					  	pdistrictId: pdistrictId
		             };
				var url = '${ctx}/basedata/querytpdistrict01!queryTdepartment.action';
				jQuery.post(url, params, callback_tpostSeg_add, 'json');
			}
			function callback_tpostSeg_add(data){
				$('#tr_postSeg_oprator_'+data.pdistrictId).before("<tr id=tpostSeg_add_new_"+data.pdistrictId+"><td class=listtablebodyleft1>&nbsp;</td><td class=listtablebodyleft1><input type=text value=''></td><td class=listtablebodyleft1><input type=text value=''></td><td class=listtablebodyleft1>"+makeup_delivery_select(data.pdistrictId,'','')+"</td><td class=listtablebodyleft1>"+makeup_receive_select(data.pdistrictId,'','')+"</td><td class=listtablebodyleft1>"+makeup_department_select(data.pdistrictId,'',data.tdepartments,data.tpdistrict,'')+"</td><td class=listtablebodyleft1><input type=button value='保存' onclick=tpostSeg_save('','"+data.pdistrictId+"')>&nbsp;<input type=button value='取消' onclick=tpostSeg_remove("+data.pdistrictId+")></td></tr>");
				$('#tr_postSeg_oprator_'+data.pdistrictId+' td:last').html('&nbsp;');
			}
			function tpostSeg_remove(pdistrictId){
				$('#tpostSeg_add_new_'+pdistrictId).remove();
				$('#tr_postSeg_oprator_'+pdistrictId+' td:last').html('<input type=button value=添加 onclick=tpostSeg_add('+pdistrictId+')>');
			}
			function tpostSeg_delete(pdistrictId){
				var c=$('#postSegTable'+pdistrictId+' tbody input:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
				    cv = cv+','+$(this).val();
				});
				if(cv!=''){
					$.prompt('如果删除该段，该段信息以及段信息和名址匹配信息都会丢失',{ buttons: { '删除': true, '取消': false },callback: function tpostSeg_delete_confirm(v,m,f){
						if(v){
							if(cv!=''){
								var params = {
												postsegIds:cv
							             	 };
								var url = '${ctx}/basedata/savetpostseg!delete.action';
								$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
								jQuery.post(url, params, callback_tpostSeg_delete_confirm, 'json');
							}
						}
							
					}});
				}else{
					var t=document.getElementById("postSegTable"+pdistrictId).offsetHeight+document.body.scrollTop;
					$.prompt('请选择要删除的段',{top:t});
				}			
			}
			function callback_tpostSeg_delete_confirm(data){
				if(data.delNum!=0){
					var d=data.postsegIds;
					var strs= new Array();
					strs=d.split(",");
					for (i=0;i<strs.length ;i++ )    
				    {    
					    $('#tr_postSeg_data_'+strs[i]).remove();
				    } 			      
				}
				$.unblockUI();
			}

			function tpostCd_add(pdistrictId){
				$('#tr_postCd_oprator_'+pdistrictId).before("<tr id=tpostCd_add_new_"+pdistrictId+"><td class=listtablebodyleft1>&nbsp;</td><td class=listtablebodyleft1><input type=text value=''></td><td class=listtablebodyleft1><input type=button value='保存' onclick=tpostCd_save('','"+pdistrictId+"')>&nbsp;<input type=button value='取消' onclick=tpostCd_remove("+pdistrictId+")></td></tr>");
				$('#tr_postCd_oprator_'+pdistrictId+' td:last').html('&nbsp;');
			}
			function tpostCd_remove(pdistrictId){
				$('#tpostCd_add_new_'+pdistrictId).remove();
				$('#tr_postCd_oprator_'+pdistrictId+' td:last').html('<input type=button value=添加 onclick=tpostCd_add('+pdistrictId+')>');
			}
			function tpostCd_save(id,pdistrictId){
				if(id==''){
					//添加
					var t=document.getElementById("tpostCd_add_new_"+pdistrictId).offsetHeight+document.body.scrollTop;
					var td=$('#tpostCd_add_new_'+pdistrictId).children('td');
					var postCode=$.trim(td.eq(1).children().eq(0).attr('value'));
					if(postCode==''){
						$.prompt('邮编不能为空',{top:t});
						return false;
					}
					var params = {
							  	pdistrictId:pdistrictId,
							  	postCode: postCode
				             };
					var url = '${ctx}/basedata/savetpostcd!save.action';
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
					jQuery.post(url, params, callback_tpostCd_addSave, 'json');
				}else{
					//修改
					var t=document.getElementById("tr_postCd_data_"+id).offsetHeight+document.body.scrollTop;
					var td=$('#tr_postCd_data_'+id).children('td');
					var postCode=$.trim(td.eq(1).children().eq(0).attr('value'));
					if(postCode==''){
						$.prompt('段名不能为空',{top:t});
						return false;
					}
					var params = {
								postcdId: id,
								postCode: postCode,
								pdistrictId: pdistrictId
				             };
					var url = '${ctx}/basedata/savetpostcd!save.action';
					$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
					jQuery.post(url, params, callback_tpostCd_save, 'json');
				}
			}
			function callback_tpostCd_save(data){
				var td=$('#tr_postCd_data_'+data.model.postcdId).children('td');
				td.eq(0).html("<input type=checkbox value="+data.model.postcdId+"/>&nbsp;");
				td.eq(1).html(data.model.postCode);
				td.eq(2).html("<input type=button value='修改' onclick=tpostCd_alter('"+data.model.postcdId+"')>"+data.saveMessage);
				$('#tr_postCd_oprator_'+data.pdistrictId+' td:last').html('<input type=button value=添加 onclick=tpostCd_add('+data.pdistrictId+')>');
				$.unblockUI();
				$("#tpostCdSaveMessage").fadeOut(4000,function(){$("#tpostCdSaveMessage").remove()}); 
			}
			function callback_tpostCd_addSave(data){
				$("#tpostCd_add_new_"+data.pdistrictId).remove();
				var t=$("#postCdTable"+data.pdistrictId+" tbody").children('tr').length-1;
				$("#postCdTable"+data.pdistrictId+" tbody tr:eq("+t+")").before("<tr id=tr_postCd_data_"+data.model.postcdId+"><td class=listtablebodyleft1><input type=checkbox value="+data.model.postcdId+">&nbsp;</td><td class=listtablebodyleft1>"+data.model.postCode+"</td><td class=listtablebodyleft1><input type=button value='修改' onclick=tpostCd_alter('"+data.model.postcdId+"')>"+data.saveMessage+"</td></tr>");
				$('#tr_postCd_oprator_'+data.pdistrictId+' td:last').html('<input type=button value=添加 onclick=tpostCd_add('+data.pdistrictId+')>');
				$.unblockUI();
				$("#tpostCdSaveMessage").fadeOut(4000,function(){$("#tpostCdSaveMessage").remove()}); 
			}
			function tpostCd_alter(tpostCdId){
				var v1=$('#tr_postCd_data_'+tpostCdId).children('td').eq(1);
				var v1p=$.trim(v1.html());
				v1.html("<input type=text value="+$.trim(v1.html())+">");
				var pdistrictId=$('#tr_postCd_data_'+tpostCdId).parent().parent().attr('id');
				pdistrictId=pdistrictId.substring(11);
				var v2=$('#tr_postCd_data_'+tpostCdId).children('td').eq(2);
				v2.html("<input type=button value='保存' onclick=tpostCd_save('"+tpostCdId+"',"+pdistrictId+")>&nbsp;<input type=button value='取消' onclick=tpostCd_cancel('"+tpostCdId+"','"+v1p+"')>");
			}
			function tpostCd_cancel(tpostCdId,postCode){
				var v1=$('#tr_postCd_data_'+tpostCdId).children('td').eq(1);
				v1.html(postCode);
				var v2=$('#tr_postCd_data_'+tpostCdId).children('td').eq(2);
				v2.html("<input type=button value='修改' onclick=tpostCd_alter('"+tpostCdId+"')>");
			}
			function tpostCd_delete(pdistrictId){
				var c=$('#postCdTable'+pdistrictId+' tbody input:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
				    cv = cv+','+$(this).val();
				});
				if(cv!=''){
					$.prompt('确定要删除么',{ buttons: { '删除': true, '取消': false },callback: function tpostCd_delete_confirm(v,m,f){
						if(v){
							if(cv!=''){
								var params = {
												postcdIds:cv
							             	 };
								var url = '${ctx}/basedata/savetpostcd!delete.action';
								$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
								jQuery.post(url, params, callback_tpostCd_delete_confirm, 'json');
							}
						}
							
					}});
				}else{
					var t=document.getElementById("postCdTable"+pdistrictId).offsetHeight+document.body.scrollTop;
					$.prompt('请选择要删除的邮编',{top:t});
				}			
			}
			function callback_tpostCd_delete_confirm(data){
				if(data.delNum!=0){
					var d=data.postcdIds;
					var strs= new Array();
					strs=d.split(",");
					for (i=0;i<strs.length ;i++ )    
				    {    
					    $('#tr_postCd_data_'+strs[i]).remove();
				    } 			      
				}
				$.unblockUI();
			}
		</script>
	</head>
	<body>
		<s:form namespace="/basedata" action="tpdistrict" id="ec">
			<input type="hidden" name="cityId" id="cityId"/>
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
										区信息
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
											<tr>
												<td class="listtablehead" width="10">
													省：
												</td>
												<td class="js_left_txt" id="td_privince_content">
													<a href="javascript:view_city('北京市','110000')" id="110000">北京</a>&nbsp;
													<a href="javascript:view_city('天津市','120000')" id="120000">天津</a>&nbsp;
													<a href="javascript:view_city('河北省','130000')" id="130000">河北</a>&nbsp;
													<a href="javascript:view_city('山西省','140000')" id="140000">山西</a>&nbsp;
													<a href="javascript:view_city('内蒙古自治区','150000')"
														id="150000">内蒙</a>&nbsp;
													<a href="javascript:view_city('辽宁省','210000')" id="210000">辽宁</a>&nbsp;
													<a href="javascript:view_city('吉林省','220000')" id="220000">吉林</a>&nbsp;
													<a href="javascript:view_city('黑龙江省','230000')" id="230000">黑龙江</a>&nbsp;
													<a href="javascript:view_city('上海市','310000')" id="310000">上海</a>&nbsp;
													<a href="javascript:view_city('江苏省','320000')" id="320000">江苏</a>&nbsp;
													<a href="javascript:view_city('浙江省','330000')" id="330000">浙江</a>&nbsp;
													<a href="javascript:view_city('安徽省','340000')" id="340000">安徽</a>&nbsp;
													<a href="javascript:view_city('福建省','350000')" id="350000">福建</a>&nbsp;
													<a href="javascript:view_city('江西省','360000')" id="360000">江西</a>&nbsp;
													<a href="javascript:view_city('山东省','370000')" id="370000">山东</a>&nbsp;
													<a href="javascript:view_city('河南省','410000')" id="410000">河南</a>&nbsp;
													<a href="javascript:view_city('湖北省','420000')" id="420000">湖北</a>&nbsp;
													<a href="javascript:view_city('湖南省','430000')" id="430000">湖南</a>&nbsp;
													<a href="javascript:view_city('广东省','440000')" id="440000">广东</a>&nbsp;
													<a href="javascript:view_city('广西壮族自治区','450000')"
														id="450000">广西</a>&nbsp;
													<a href="javascript:view_city('海南省','460000')" id="460000">海南</a>&nbsp;
													<a href="javascript:view_city('重庆市','500000')" id="500000">重庆</a>&nbsp;
													<a href="javascript:view_city('四川省','510000')" id="510000">四川</a>&nbsp;
													<a href="javascript:view_city('贵州省','520000')" id="520000">贵州</a>&nbsp;
													<a href="javascript:view_city('云南省','530000')" id="530000">云南</a>&nbsp;
													<a href="javascript:view_city('西藏自治区','540000')" id="540000">西藏</a>&nbsp;
													<a href="javascript:view_city('陕西省','610000')" id="610000">陕西</a>&nbsp;
													<a href="javascript:view_city('甘肃省','620000')" id="620000">甘肃</a>&nbsp;
													<a href="javascript:view_city('青海省','630000')" id="630000">青海</a>&nbsp;
													<a href="javascript:view_city('宁夏回族自治区','640000')"
														id="640000">宁夏</a>&nbsp;
													<a href="javascript:view_city('新疆维吾尔自治区','650000')"
														id="650000">新疆</a>&nbsp;
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="10">
													市：
												</td>
												<td class="js_left_txt" id="td_city_content">
													<s:iterator id="eles" value="citys" var="city">
														<a href="javascript:view_disricts('${city.districtCode }')" id='city_${city.districtCode}'>${city.cityName}</a>&nbsp;&nbsp;
													</s:iterator>
												</td>
											</tr>
										</table>
									</div>
									<br />
									<div id="tablelistdiv">
										<table class="listtable" width="960"
											id="tpdistrict_table_list">
											<tr>
												<th align="left" colspan="6">
													区信息
												</th>
											</tr>
											<tr>
												<td align="left" class=listtableheadleft width="100">
													&nbsp;
												</td>
												<td align="left" class=listtableheadleft width="200">
													区名
												</td>
												<td align="left" class=listtableheadleft width="200">
													别名
												</td>
												<td align="left" class=listtableheadleft width="100">
													是否有部
												</td>
												<td align="left" class=listtableheadleft width="100">
													所属市
												</td>
												<td align="left" class=listtableheadleft>
													操作
												</td>
											</tr>
											<s:iterator id="ele" value="tpdistricts">
												<tr id="alter${ele.pdistrictId}">
													<td align="left" class=listtablebodyleft1>
														<input type="checkbox" value="${ele.pdistrictId }" />
														<img id="td_${ele.pdistrictId}_img" src="${ctx}/images/mini_icons_098.gif" style="cursor:hand" onclick="javascript:view_subs('${ele.pdistrictId}')"></img>
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.name }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.aliasName }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.haveDepartmentName }
													</td>
													<td align="left" class=listtablebodyleft1>
														<c:set var="cityId"
															value="${ele.orgDistrict.districtCode}"></c:set>
														<%
																String cityId=(String)pageContext.getAttribute("cityId");
																Element a=((Cache)request.getAttribute("orgDistrictCacheBean")).get(cityId);
																if(a!=null){
																	OrgDistrict orgDistrict=(OrgDistrict)(a.getValue());
																	out.println(orgDistrict.getCityName());
																}else{
																	out.println(cityId);
																}
																
														%>
														<c:remove var="cityId" />
													</td>
													<td align="left" class=listtablebodyleft1>
														<input type="button" value="修改"
															onclick="tpdistrict_alter('${ele.pdistrictId}')" />
													</td>
												</tr>
											</s:iterator>
											<tr id='tr_operator'>
												<td align="left" class=listtablebodyleft1 colspan="5">
													<input type="button" value="删除"
														onclick="tpdistrict_delete()" />
												</td>
												<td align="left" class=listtablebodyleft1>
													<input type="button" value="添加" onclick="tpdistrict_add()" />
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
