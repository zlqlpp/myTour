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
				var tpdistrictId=${tpdistrict.pdistrictId};
				$("#"+provinceId).addClass("curr");
				$("#city_"+cityId).addClass("curr");
				$("#tpdistrict_"+tpdistrictId).addClass("curr");
				$('#cityId').attr('value',cityId);
				$('#tpdistrictId').attr('value',tpdistrictId);
			})
			function make_citys(citys,cityId){
				var str="";
				var href_function_name="javascript:view_disricts";
				for(var i=0;i<citys.length; i++ ){
					str+="<a id=city_"+citys[i].districtCode+" href="+href_function_name+"('"+citys[i].districtCode+"')>"+citys[i].cityName+"</a>&nbsp;&nbsp;";	
				}
				$('#td_city_content').empty();
				$('#td_city_content').addClass("js_left_txt");
				$('#td_city_content').append(str);
				$("#city_"+cityId).addClass("curr");
			}
			function make_tpdistrict(tpds,tpdistrictId){
				var str="";
				var href_function_name="javascript:view_postSegs";
				for(var i=0;i<tpds.length;i++){
					str+="<a id=tpdistrict_"+tpds[i].pdistrictId+" href="+href_function_name+"('"+tpds[i].pdistrictId+"')>"+tpds[i].name+"</a>&nbsp;&nbsp;";	
				}
				$('#td_tpdistrict_content').empty();
				$('#td_tpdistrict_content').addClass("js_left_txt");
				$('#td_tpdistrict_content').append(str);
				$("#tpdistrict_"+tpdistrictId).addClass("curr");
			}
			function make_tpostSeg(tps,tpostSegId){
				var str="";
				var href_function_name="javascript:view_address";
				for(var i=0;i<tps.length;i++){
					str+="<a id=tpostSeg_"+tps[i].postsegId+" href="+href_function_name+"('"+tps[i].postsegId+"')>"+tps[i].postsegName+"</a>&nbsp;&nbsp;";	
				}
				$('#td_tpostSeg_content').empty();
				$('#td_tpostSeg_content').addClass("js_left_txt");
				$('#td_tpostSeg_content').append(str);
				//$("#tpostSeg_"+tpostSegId).addClass("curr");
			}
			function make_tpostSeg_option(tps){
				var s="";
				s+="<select id=tpostSeg_option_operator >";
				for(var i=0;i<tps.length;i++){
					s+="<option value="+tps[i].postsegId+">"+tps[i].postsegName+"</option>";
				}
				s+="</select>";
				return s;
			}
			function addtable(tstrts,tpostSegs,url){
				$('#address_table_list').remove();
				var t="<table id=address_table_list class=listtable width=960><tr><th colspan=9 id=tablelistthcontent align=left>名址信息</th></tr></table>";
				$('#tablelistdiv').append(t);
				var tr="<tr><td class=listtableheadleft>&nbsp;</td><td class=listtableheadleft>街道号</td><td class=listtableheadleft>行政区</td>";
				tr+="<td class=listtableheadleft>街道1段名称 </td><td class=listtableheadleft>街道2段名称</td>";
				tr+="<td class=listtableheadleft>街道3段名称 </td><td class=listtableheadleft>街道4段名称</td>";
				tr+="<td class=listtableheadleft>街道5段名称 </td><td class=listtableheadleft>分段信息</td>";
				tr+="</tr>";
				$('#address_table_list').append(tr);
				if(tstrts.length>0){
					for(var i=0;i<tstrts.length;i++){
						var td="<tr id=strt_list_"+tstrts[i].strtId+">";
						td+="<td class=listtablebodyleft1><input type=checkbox name=strtCheckBox value="+tstrts[i].strtId+"></td>";
						td+="<td class=listtablebodyleft1><img id=td_"+tstrts[i].strtId+"_img src=${ctx}/images/mini_icons_098.gif style=cursor:hand onclick=javascript:view_r_postSeg('"+tstrts[i].strtId+"')></img>"+tstrts[i].strtId+"</td>";
						td+="<td class=listtablebodyleft1>"+tstrts[i].distCd+"</td>";
						td+="<td class=listtablebodyleft1>"+tstrts[i].strt1Name+"</td>";
						td+="<td class=listtablebodyleft1>"+tstrts[i].strt2Name+"</td>";
						td+="<td class=listtablebodyleft1>"+tstrts[i].strt3Name+"</td>";
						td+="<td class=listtablebodyleft1>"+tstrts[i].strt4Name+"</td>";
						td+="<td class=listtablebodyleft1>"+tstrts[i].strt5Name+"</td>";
						td+="<td class=listtablebodyleft1>"+tstrts[i].statFlagName+"</td>";
						td+="</tr>";
						$('#address_table_list').append(td);
					}
					var footer="<tr id='tr_operator_page'><td align=left class=listtablebodyleft1 colspan=9>"+url+"</td></tr>";
					$('#address_table_list').append(footer);
					var footer_operator="<tr id='tr_operator'><td align=left class=listtablebodyleft1 colspan=9>"+make_tpostSeg_option(tpostSegs)+"<input type=button value=添加到该段 onclick=strtPostSegBatchSave()></td></tr>";
					$('#address_table_list').append(footer_operator);
				}
			}
			function view_city(provincename,provinceid){
			    var params = {
			    				provinceId: provinceid
			                 };
			    $("#td_privince_content a").removeClass(); 
			    $('#'+provinceid).addClass("curr");
			    var url="${ctx}/match/querydpa!province.action";
			    jQuery.post(url, params, callback_view_city, 'json');
			}
			function callback_view_city(data){
				make_citys(data.citys,data.cityId);
				make_tpdistrict(data.tpdistricts,data.tpdistrictId);
				addtable(data.page.result,data.tpostSegs,data.page.ajaxUrl);
			}
			function view_disricts(cityId){
			    var params = {
			    				cityId: cityId
			                 };
			    $("#td_city_content a").removeClass(); 
			    $('#city_'+cityId).addClass("curr");
			    var url="${ctx}/match/querydpa!city.action";
			    jQuery.post(url, params, callback_view_disricts, 'json');
			}
			function callback_view_disricts(data){
				make_tpdistrict(data.tpdistricts,data.tpdistrictId);
				addtable(data.page.result,data.tpostSegs,data.page.ajaxUrl);
			}
			function view_postSegs(tpdistrictId){
			    var params = {
			    			tpdistrictId: tpdistrictId
			                 };
			    $("#td_tpdistrict_content a").removeClass(); 
			    $('#tpdistrict_'+tpdistrictId).addClass("curr");
			    var url="${ctx}/match/querydpa!tpdistrict.action";
			    jQuery.post(url, params, callback_view_postSegs, 'json');
			}
			function callback_view_postSegs(data){
				addtable(data.page.result,data.tpostSegs,data.page.ajaxUrl);
			}
			//批量增加地址到段
			function strtPostSegBatchSave(){
				var c=$('#address_table_list tbody input[name=strtCheckBox]:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
				    cv = cv+','+$(this).val();
				});
				if(cv==''){
					$.prompt('请选择地址');
					return false;
				}
				if($("#tpostSeg_option_operator").attr("value")==''){
					$.prompt('请选择段');
					return false;
				}
				var q=$("#tpostSeg_option_operator").attr("value");
				var params = {
						postSegId:q,
						strtIds:cv
	             	 };
				var url = '${ctx}/address/saverpostseg!strtToPostBatchSave.action';
				$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
				jQuery.post(url, params, callbackStrtPostSegBatchSave, 'json');
			}
			function callbackStrtPostSegBatchSave(data){
				$('#tr_operator td:last').append(data.saveMessage);
				$.unblockUI();
				$("#saveMessage").fadeOut(8000,function(){$("#saveMessage").remove()}); 
			}
			function ajaxQueryPage(pageNo){
				var provinceId=$("#td_privince_content a[class=curr]").attr('id'); 
				var cityId=$("#td_city_content a[class=curr]").attr('id').substr(5); 
				var tpdistrictId=$("#td_tpdistrict_content a[class=curr]").attr('id').substr(11); 
				var params = {
								provinceId: provinceId,
								cityId: cityId,
								tpdistrictId: tpdistrictId,
								pageNo: pageNo
			                 };
                var url="${ctx}/match/querydpa!ajaxPageQuery.action";
			    jQuery.post(url, params, callback_ajaxQueryPage, 'json');
			}
			function callback_ajaxQueryPage(data){
				addtable(data.page.result,data.tpostSegs,data.page.ajaxUrl);
			}
			function make_select_tpostSeg(strtId,tpostSegs,tpostSegId,repostSegId){
				var s="";
				if(strtId==''){
					s="<select id=tpostSeg_option >";
				}else{
					if(tpostSegId=='' && repostSegId==''){
						s="<select id=tpostSeg_option_new"+strtId+">";
					}else{
						s="<select id=tpostSeg_option_"+repostSegId+">";
					}
				}
				for(var i=0;i<tpostSegs.length;i++){
					if(tpostSegId==tpostSegs[i].postsegId){
						s+="<option selected=selected value="+tpostSegs[i].postsegId+">"+tpostSegs[i].postsegName+"</option>";
					}else{
						s+="<option value="+tpostSegs[i].postsegId+">"+tpostSegs[i].postsegName+"</option>";
					}
				}
				s+="</select>";
				return s;
			}
			function makeup_select_flag(strtId,rpostSegId,flagName){
				var s="";
				if(rpostSegId==''){
					//新增
					s+="<select id=rpostseg_option_new"+strtId+" >";
					s+="<option value=4>不区分单双号</option>";
					s+="<option value=5>单号</option>";
					s+="<option value=6>双号</option>";
					s+="</select>";
				}else{
					s+="<select id=rpostseg_option__alter"+rpostSegId+" >";
					if(flagName=='不区分单双号'){
						s+="<option value=4 selected=selected>不区分单双号</option>";
						s+="<option value=5 >单号</option>";
						s+="<option value=6>双号</option>";
					}else if(flagName=='单号'){
						s+="<option value=4>不区分单双号</option>";
						s+="<option value=5 selected=selected>单号</option>";
						s+="<option value=6>双号</option>";
					}else{
						s+="<option value=4>不区分单双号</option>";
						s+="<option value=5>单号</option>";
						s+="<option value=6 selected=selected>双号</option>";
					}
					s+="</select>";
				}
				return s;
			}
			function alter_select_tpostSeg_options(strtId,tpdistrictId,rpostSegId){
				var url = '${ctx}/address/querytpostseg!queryTpostSegsByTpdistrct.action';
			    var params = {
			    			pdistrictId: tpdistrictId
			                 };
			    jQuery.post(url, params, function callback_alter_select_tpostSeg_options(data){
					var tds=data.tpostSegs;//所有区
					var s="";
					for(var i=0;i<tds.length;i++){
						s+="<option value="+tds[i].postsegId+">"+tds[i].postsegName+"</option>";
					}
					if(strtId==''){
						$('#tpostSeg_option').html(s);
					}else{
						if(rpostSegId==''){
							$('#tpostSeg_option_new'+strtId).html(s);
						}else{
							$('#tpostSeg_option_'+rpostSegId).html(s);
						}
					}
					
				}, 'json');
			}
			function view_r_postSeg(strtId){
				if($('#tr_r_postSeg_'+strtId).length>0){
					$('#tr_r_postSeg_'+strtId).remove();
					$('#td_'+strtId+'_img').attr('src','${ctx}/images/mini_icons_098.gif');
				}else{
					var url = '${ctx}/address/queryrpostseg!rpostSegs.action';
				    var params = {
				    				strtId: strtId
				                 };
				    jQuery.post(url, params, callback_view_r_postSeg, 'json');
				}
			}
			function callback_view_r_postSeg(data){
				$('#td_'+data.strtId+'_img').attr('src','${ctx}/images/mini_icons_093.gif');
				$('#strt_list_'+data.strtId).after("<tr id=tr_r_postSeg_"+data.strtId+"><td colspan=9 id=td_r_postSeg_"+data.strtId+"><table id=subTable"+data.strtId+"></table></td></tr>");
				$('#subTable'+data.strtId).append("<tr><td width=50>&nbsp;</td><td id=subTable_td_"+data.strtId+"></td></tr></tr>");
				$('#subTable_td_'+data.strtId).append("<table id=r_postSegTable"+data.strtId+" width=880></table>");
				$('#r_postSegTable'+data.strtId).append("<thead><tr><td align=left id=rpostSegTable_th_"+data.strtId+" colspan=6 class=listtableth>地址段匹配信息</td></tr></thead>");
				$('#r_postSegTable'+data.strtId).append("<tr><td class=listtableheadleft width=100>&nbsp;</td><td class=listtableheadleft width=300>段</td><td class=listtableheadleft width=100>开始号码</td><td width=100 class=listtableheadleft>结束号码</td><td width=100 class=listtableheadleft>标志</td><td class=listtableheadleft>操作</td></tr>");
				var rpostsegs=data.rpostSegs;
				for(var i=0;i<rpostsegs.length;i++){
					$('#r_postSegTable'+data.strtId).append("<tr id=tr_r_postSeg_data_"+rpostsegs[i].id+"><td class=listtablebodyleft1><input type=checkbox value="+rpostsegs[i].id+"></td><td class=listtablebodyleft1>"+rpostsegs[i].postsegName+"</td><td class=listtablebodyleft1>"+rpostsegs[i].startNum+"</td><td class=listtablebodyleft1>"+rpostsegs[i].endNum+"</td><td class=listtablebodyleft1>"+rpostsegs[i].flagName+"</td><td class=listtablebodyleft1><input type=button value='修改' onclick=alter_r_postSeg('"+data.strtId+"','"+rpostsegs[i].id+"')></td></tr>");
				}
				$('#r_postSegTable'+data.strtId).append("<tr id=tr_r_postSeg_oprator_"+data.strtId+"><td class=listtablebodyleft1><input type=button value=删除 onclick=delete_r_postSeg('"+data.strtId+"')></td><td class=listtablebodyleft1 colspan=4>&nbsp;</td><td class=listtablebodyleft1><input type=button value=添加 onclick=add_r_postSeg('"+data.strtId+"')></td></tr>");
			}
			function alter_r_postSeg(strtId,rpostSegId){
				var url = '${ctx}/match/querydpa01!queryPostSegByDistrictId.action';
				var tpdistrictId=$("#td_tpdistrict_content a[class=curr]").attr('id').substr(11); 
			    var params = {
					    		districtId: tpdistrictId,
			                  	strtId: strtId,
			                  	rpostSegId: rpostSegId
			                 };
			    jQuery.post(url, params, callback_alter_r_postSeg, 'json');
				
			}
			function callback_alter_r_postSeg(data){
				var tr_data=$('#tr_r_postSeg_data_'+data.rpostSegId);
				var tr_td_data=tr_data.children();
				var td1value=tr_td_data.eq(1).html();
				tr_td_data.eq(1).html(make_select_tpostSeg(data.strtId,data.postSegs,data.postsegId,data.rpostSegId));
				var td2value=tr_td_data.eq(2).html();
				tr_td_data.eq(2).html("<input type=text name=endNum value="+td2value+">");
				var td3value=tr_td_data.eq(3).html();
				tr_td_data.eq(3).html("<input type=text name=postfix value="+td3value+">");
				var td4value=tr_td_data.eq(4).html();
				tr_td_data.eq(4).html(makeup_select_flag(data.strtId,data.rpostSegId,$.trim(td4value)));
				tr_td_data.eq(5).html("<input type=button  value='保存' onclick=save_r_postSeg("+data.strtId+","+data.rpostSegId+")>&nbsp;<input type=button  value='取消' onclick=cancel_r_postSeg("+data.strtId+","+data.rpostSegId+",'"+td1value+"','"+td2value+"','"+td3value+"','"+td4value+"')>")
			}
			function cancel_r_postSeg(strtId,rpostSegId,postSegName,startNum,endNum,flagName){
				var v1=$('#tr_r_postSeg_data_'+rpostSegId).children('td').eq(1);
				v1.html(postSegName);
				var v2=$('#tr_r_postSeg_data_'+rpostSegId).children('td').eq(2);
				v2.html(startNum);
				var v3=$('#tr_r_postSeg_data_'+rpostSegId).children('td').eq(3);
				v3.html(endNum);
				var v4=$('#tr_r_postSeg_data_'+rpostSegId).children('td').eq(4);
				v4.html(flagName);
				var v5=$('#tr_r_postSeg_data_'+rpostSegId).children('td').eq(5);
				v5.html("<input type=button value='修改' onclick=alter_r_postSeg("+strtId+","+rpostSegId+")>");
			}
			function add_r_postSeg(strtId){
				var url = '${ctx}/match/querydpa01!queryPostSegByDistrictId.action';
				var tpdistrictId=$("#td_tpdistrict_content a[class=curr]").attr('id').substr(11); 
			    var params = {
			    				districtId: tpdistrictId,
			                  	strtId: strtId
			                 };
			    jQuery.post(url, params, callback_add_r_postSeg, 'json');	
			}
			function callback_add_r_postSeg(data){
				$('#tr_r_postSeg_oprator_'+data.strtId).before("<tr id=rpostSeg_add_new_"+data.strtId+"><td class=listtablebodyleft1>&nbsp;</td><td class=listtablebodyleft1>"+make_select_tpostSeg(data.strtId,data.postSegs,'','')+"</td><td class=listtablebodyleft1><input type=text value=''></td><td class=listtablebodyleft1><input type=text value=''></td><td class=listtablebodyleft1>"+makeup_select_flag(data.strtId,'','')+"</td><td class=listtablebodyleft1><input type=button value='保存' onclick=save_r_postSeg("+data.strtId+",'')>&nbsp;<input type=button value='取消' onclick=remove_r_postSeg("+data.strtId+")></td></tr>");
				$('#tr_r_postSeg_oprator_'+data.strtId+' td:last').html('&nbsp;');
			}
			function remove_r_postSeg(strtId){
				$('#rpostSeg_add_new_'+strtId).remove();
				$('#tr_r_postSeg_oprator_'+strtId+' td:last').html('<input type=button value=添加 onclick=add_r_postSeg('+strtId+')>');
			}
			function validate_save_r_postSeg(strtId,postSegId,startNum,endNum){
				var t=document.getElementById("r_postSegTable"+strtId).offsetHeight+document.body.scrollTop;
				if(postSegId==''){
					$.prompt('请选择段信息',{top:t});
					return false;
				}
				if(startNum!='' && endNum!=''){
					if(parseInt(startNum)>parseInt(endNum)){
						$.prompt('开始号码不能大于结束号码',{top:t});
						return false;
					}
				}
				var r=new RegExp("^[0-9]*[1-9][0-9]*$");
				if(startNum!=''){
					if(!r.test(startNum)){
						$.prompt('开始号码必须是正整数',{top:t});
						return false;
					}
				}
				if(endNum!=''){
					if(!r.test(endNum)){
						$.prompt('结束号码必须是正整数',{top:t});
						return false;
					}
				}
				return true;
				
			}
			function save_r_postSeg(strtId,rpostSegId){
				if(rpostSegId==''){
					//添加
					var td=$('#rpostSeg_add_new_'+strtId).children('td');
					var postSegId=$('#tpostSeg_option_new'+strtId).attr('value');
					var startNum=$.trim(td.eq(2).children().eq(0).attr('value'));
					var endNum=$.trim(td.eq(3).children().eq(0).attr('value'));
					var flag=$.trim(td.eq(4).children().eq(0).attr('value'));
					var params = {
							postSegId: postSegId,
							startNum: startNum,
							endNum: endNum,
							flag: flag,
							strtId: strtId
				             };
		            if(validate_save_r_postSeg(strtId,postSegId,startNum,endNum)){
		            	var url = '${ctx}/address/saverpostseg!strtToPostSave.action';
						$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
						jQuery.post(url, params, callback_addSave_r_postSeg, 'json');
		            }
				}else{
					//修改
					var td=$('#tr_r_postSeg_data_'+rpostSegId).children('td');
					var postSegId=$('#tpostSeg_option_'+rpostSegId).attr('value');
					var startNum=$.trim(td.eq(2).children().eq(0).attr('value'));
					var endNum=$.trim(td.eq(3).children().eq(0).attr('value'));
					var flag=$.trim(td.eq(4).children().eq(0).attr('value'));
					var params = {
							postSegId: postSegId,
							startNum: startNum,
							endNum: endNum,
							flag: flag,
							strtId: strtId,
							id: rpostSegId
				             };
					if(validate_save_r_postSeg(strtId,postSegId,startNum,endNum)){
						var url = '${ctx}/address/saverpostseg!strtToPostSave.action';
						$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
						jQuery.post(url, params, callback_save_r_postSeg, 'json');
					}
				}
			}
			function callback_save_r_postSeg(data){
				if(data.saveNum==2){//成功
					var td=$('#tr_r_postSeg_data_'+data.model.id).children('td');
					td.eq(0).html("<input type=checkbox value="+data.model.id+">&nbsp;");
					td.eq(1).html(data.model.postsegName);
					td.eq(2).html(data.model.startNum);
					td.eq(3).html(data.model.endNum);
					td.eq(4).html(data.model.flagName);
					td.eq(5).html("<input type=button value='修改' onclick=alter_r_postSeg('"+data.strtId+"','"+data.model.id+"')>"+data.saveMessage);
					$('#tr_postSeg_oprator_'+data.strtId+' td:last').html('<input type=button value=添加 onclick=add_r_postSeg('+data.strtId+')>');
					$.unblockUI();
					$("#saveMessage").fadeOut(4000,function(){$("#saveMessage").remove()}); 
				}else{
					var td=$('#tr_r_postSeg_data_'+data.model.id).children('td');
					td.eq(5).html(td.eq(5).html()+data.saveMessage)
					$.unblockUI();
					$("#saveMessage").fadeOut(4000,function(){$("#saveMessage").remove()}); 
				}
				
			}
			function callback_addSave_r_postSeg(data){
				if(data.saveNum==2){//成功
					$("#rpostSeg_add_new_"+data.strtId).remove();
					var t=$("#r_postSegTable"+data.strtId+" tbody").children('tr').length-1;
					$("#r_postSegTable"+data.strtId+" tbody tr:eq("+t+")").before("<tr id=tr_r_postSeg_data_"+data.model.id+"><td class=listtablebodyleft1><input type=checkbox value="+data.model.id+">&nbsp;</td><td class=listtablebodyleft1>"+data.model.postsegName+"</td><td class=listtablebodyleft1>"+data.model.startNum+"</td><td class=listtablebodyleft1>"+data.model.endNum+"</td><td class=listtablebodyleft1>"+data.model.flagName+"</td><td class=listtablebodyleft1><input type=button value='修改' onclick=alter_r_postSeg('"+data.model.strtId+"','"+data.model.id+"')>"+data.saveMessage+"</td></tr>");
					$('#tr_r_postSeg_oprator_'+data.strtId+' td:last').html('<input type=button value=添加 onclick=add_r_postSeg('+data.strtId+')>');
					$.unblockUI();
					$("#saveMessage").fadeOut(4000,function(){$("#saveMessage").remove()}); 
				}else{
					$("#rpostSeg_add_new_"+data.strtId).remove();
					$('#tr_r_postSeg_oprator_'+data.strtId+' td:last').html('<input type=button value=添加 onclick=add_r_postSeg('+data.strtId+')>'+data.saveMessage);
					$.unblockUI();
					$("#saveMessage").fadeOut(4000,function(){$("#saveMessage").remove()}); 
				}
				
			}

			function delete_r_postSeg(strtId){
				var c=$('#r_postSegTable'+strtId+' tbody input:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
				    cv = cv+','+$(this).val();
				});
				var t=document.getElementById("r_postSegTable"+strtId).offsetHeight+document.body.scrollTop;
				if(cv!=''){
					$.prompt('确认要删除地址段匹配信息么',{ buttons: { '删除': true, '取消': false },top:t,callback: function rpostSeg_delete_confirm(v,m,f){
						if(v){
							if(cv!=''){
								var params = {
												strtId: strtId,
												ids: cv
							             	 };
								var url = '${ctx}/address/saverpostseg!rpostSegDel.action';
								$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
								jQuery.post(url, params, callback_rpostSeg_delete_confirm, 'json');
							}
						}
					}});
				}else{
					$.prompt('请选择要删除的地址段匹配信息',{top:t});
				}			
			}
			function callback_rpostSeg_delete_confirm(data){
				if(data.delNum!=0){
					var d=data.ids;
					var strs= new Array();
					strs=d.split(",");
					for (i=0;i<strs.length ;i++ )    
				    {    
					    $('#tr_r_postSeg_data_'+strs[i]).remove();
				    } 			      
				}
				$.unblockUI();
			}
			
		</script>
	</head>
	<body>
		<s:form namespace="/basedata" action="tpdistrict" id="ec">
			<input type="hidden" name="cityId" id="cityId"/>
			<input type="hidden" name="tpdistrictId" id="tpdistrictId"/>
			<input type="hidden" name="tpostSegId" id="tpostSegId"/>
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
					<td valign="middle" background="${ctx}/images/mail_leftbg.gif">&nbsp;
						
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
											<tr>
												<td class="listtablehead" width="10">
													区：
												</td>
												<td class="js_left_txt" id="td_tpdistrict_content">
													<s:iterator id="eles" value="tpdistricts" var="tpd">
														<a href="javascript:view_postSegs('${tpd.pdistrictId}')" id='tpdistrict_${tpd.pdistrictId}'>${tpd.name}</a>&nbsp;&nbsp;
													</s:iterator>
												</td>
											</tr>
										</table>
									</div>
									<br />
									<div id="tablelistdiv">
										<table class="listtable" width="960"
											id="address_table_list">
											<tr>
												<th align="left" colspan="9">
													名址信息
												</th>
											</tr>
											<tr>
												<td align="left" class=listtableheadleft width="40">&nbsp;
													
												</td>
												<td align="left" class=listtableheadleft width="100">
													街道号
												</td>
												<td align="left" class=listtableheadleft width="40">
													行政区
												</td>
												<td align="left" class=listtableheadleft>
													街道1段名称
												</td>
												<td align="left" class=listtableheadleft>
													街道2段名称
												</td>
												<td align="left" class=listtableheadleft>
													街道3段名称
												</td>
												<td align="left" class=listtableheadleft>
													街道4段名称
												</td>
												<td align="left" class=listtableheadleft>
													街道5段名称
												</td>
												<td align="left" class=listtableheadleft>
													分段信息
												</td>
											</tr>
											<s:iterator id="ele" value="tstrtsPage.result">
												<tr id="strt_list_${ele.strtId}">
													<td align="left" class=listtablebodyleft1>
														<input type="checkbox" name="strtCheckBox" value="${ele.strtId}"/>
													</td>
													<td align="left" class=listtablebodyleft1>
														<img id="td_${ele.strtId}_img" src="${ctx}/images/mini_icons_098.gif" style="cursor:hand" onClick="javascript:view_r_postSeg('${ele.strtId}')"></img>${ele.strtId }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.distCd }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.strt1Name }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.strt2Name }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.strt3Name }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.strt4Name }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.strt5Name }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.statFlagName }
													</td>
												</tr>
											</s:iterator>
											<tr id='tr_operator_page'>
												<td align="left" class=listtablebodyleft1 colspan="9">
													${tstrtsPage.ajaxUrl }
												</td>
											</tr>
											<tr id='tr_operator'>
												<td align="left" class=listtablebodyleft1 colspan="9">
													<s:select theme="simple" list="tpostSegs" id="tpostSeg_option_operator" listKey="postsegId" listValue="postsegName">
													</s:select>
													<input type="button" value="添加到该段" onClick="strtPostSegBatchSave()"/>
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td height="30">&nbsp;
									
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
		</s:form>
	</body>
</html>
