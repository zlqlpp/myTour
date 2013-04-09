<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@page import="net.sf.ehcache.Cache"%>
<%@page import="net.sf.ehcache.Element"%>
<%@page import="com.cpst.emsadrdb.entity.address.OrgDistrict"%>
<%@page import="com.cpst.emsadrdb.entity.address.Tpdistrict"%>
<html>

	<head>
		<title>段信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		function makeup_select(data){
			var s="";
			var s1="";
			var s2="";
			if(data.postsegId==null){//如果是新增
				s+="<select id='tpostseg_option_province_new' onchange=onchange_option_province('')>";
				var d=data.orgDistricts;
				for(var i=0;i<d.length;i++){
					s+="<option value="+d[i].districtCode+">"+d[i].provinceName+"</option>";
				}
				s+="</select>";
				s1+="<select id=tpostseg_option_city_new onchange=onchange_option_city('')>";
				var citys=data.orgDistrictCitys;
				for(var i=0;i<citys.length;i++){
					s1+="<option value="+citys[i].districtCode+">"+citys[i].cityName+"</option>";
				}
				s1+="</select>";
				s2+="<select id=tpostseg_option_district_new>";
				var tpdistricts=data.tpdistricts;
				for(var i=0;i<tpdistricts.length;i++){
					s2+="<option value="+tpdistricts[i].pdistrictId+">"+tpdistricts[i].ame+"</option>";
				}
				s2+="</select>";
			}else{//修改
				var v3=$('#alter'+data.postsegId).children('td').eq(3);
				var v3p=$.trim(v3.html());
				s="<select id=tpostseg_option_province_"+data.postsegId+" onchange=onchange_option_province('"+data.postsegId+"')>";
				var d=data.orgDistricts;//所有省
				for(var i=0;i<d.length;i++){
					if(d[i].provinceName==data.province.provinceName){
						s+="<option selected=selected value="+d[i].districtCode+">"+d[i].provinceName+"</option>";
					}else{
						s+="<option value="+d[i].districtCode+">"+d[i].provinceName+"</option>";
					}
				}
				s+="</select>";
				s1="<select id=tpostseg_option_city_"+data.postsegId+">";
				var citys=data.orgDistrictCitys;//所有市
				for(var i=0;i<citys.length;i++){
					if(citys[i].cityName==data.city.cityName){
						s1+="<option selected=selected value="+citys[i].districtCode+">"+citys[i].cityName+"</option>";
					}else{
						s1+="<option value="+citys[i].districtCode+">"+citys[i].cityName+"</option>";
					}
				}
				s1+="</select>";
				s2+="<select id=tpostseg_option_district_"+data.postsegId+">";
				var tpdistricts=data.tpdistricts;//所有区
				for(var i=0;i<tpdistricts.length;i++){
					if(tpdistricts[i].pdistrictId==data.pdistrictId){
						s2+="<option selected=selected value="+tpdistricts[i].pdistrictId+">"+tpdistricts[i].name+"</option>";
					}else{
						s2+="<option value="+tpdistricts[i].pdistrictId+">"+tpdistricts[i].name+"</option>";
					}
				}
				s2+="</select>";
			}
			return s+s1+s2;
		}
			function tpostseg_alter(postsegId,pdistrictId){
				var params = {
						postsegId:postsegId,
						pdistrictId:pdistrictId
						};
				var url = '${ctx}/basedata/querytpostseg!province.action';
				jQuery.post(url, params, callback_tpostseg_alter, 'json');
			}
			
			function callback_tpostseg_alter(data){
				var v2=$('#alter'+data.postsegId).children('td').eq(2);
				var v2p=$.trim(v2.html());
				v2.html("<input type=text value='"+v2p+"'>");
				var v3=$('#alter'+data.postsegId).children('td').eq(3);
				var v3p=$.trim(v3.html());
				v3.html(makeup_select(data));
				var v4=$('#alter'+data.postsegId).children('td').eq(4);
				v4.html("<input type=button value='保存' onclick=tpostseg_save('"+data.postsegId+"')>&nbsp;<input type=button value='取消' onclick=tpostseg_cancel('"+data.postsegId+"','"+v2p+"','"+v3p+"','"+data.pdistrictId+"')>");
			}
			function tpostseg_cancel(id,name,districtName,districtId){
				var v2=$('#alter'+id).children('td').eq(2);
				v2.html(name);
				var v3=$('#alter'+id).children('td').eq(3);
				v3.html(districtName);
				var v4=$('#alter'+id).children('td').eq(4);
				v4.html("<input type=button value='修改' onclick=tpostseg_alter('"+id+"','"+districtId+"')>");
			}
			function tpostseg_add(){
				var params = {};
				var url = '${ctx}/basedata/querytpostseg!province.action';
				jQuery.post(url, params, callback_tpostseg_add, 'json');
			}
			function callback_tpostseg_add(data){
				$('#tr_operator').before("<tr id='add_new'><td class=listtablebodyleft1>&nbsp;</td><td class=listtablebodyleft1>&nbsp;</td><td class=listtablebodyleft1><input type=text value=''></td><td class=listtablebodyleft1>"+makeup_select(data)+"</td><td class=listtablebodyleft1><input type=button value='保存' onclick=tpostseg_save('')>&nbsp;<input type=button value='取消' onclick=tpostseg_remove('add_new')></td></tr>");
				$('#tr_operator td:last').html('&nbsp;');
				//alert($('#tpostseg_table_list tbody').html());
			}
			function tpostseg_remove(id){
				$('#'+id).remove();
				$('#tr_operator td:last').html('<input type=button value=添加 onclick=tpostseg_add()>');
			}
			function tpostseg_save(id){
				if(id==''){
					//添加
					var td=$('#add_new').children('td');
					var postsegName=$.trim(td.eq(2).children().eq(0).attr('value'));
					var params = {
							  postsegName:postsegName,
							  pdistrictId:$('#tpostseg_option_district_new').attr('value')
				             };
					var url = '${ctx}/basedata/savetpostseg!save.action';
					jQuery.post(url, params, callback_tpostseg_addSave, 'json');
					
				}else{
					//修改
					var td=$('#alter'+id).children('td');
					var postsegName=$.trim(td.eq(2).children().eq(0).attr('value'));
					var params = {
							  postsegId:id,
							  postsegName:postsegName,
							  pdistrictId:$('#tpostseg_option_district_'+id).attr('value')
				             };
					var url = '${ctx}/basedata/savetpostseg!save.action';
					jQuery.post(url, params, callback_tpostseg_save, 'json');
				}
			}
			function callback_tpostseg_save(data){
				var td=$('#alter'+data.model.postsegId).children('td');
				td.eq(0).html("<input type=checkbox value="+data.pdistrictId+"/>");
				td.eq(1).html(data.model.postsegId);
				td.eq(2).html(data.model.postsegName);
				td.eq(3).html(data.districtName);
				td.eq(4).html("<input type=button value='修改' onclick=tpostseg_alter('"+data.model.postsegId+"','"+data.pdistrictId+"')>");
				$('#tr_operator td:last').html('<input type=button value=添加 onclick=tpostseg_add()>');
			}
			function callback_tpostseg_addSave(data){
				$("#add_new").remove();
				var t=$("#tpostseg_table_list tbody").children('tr').length-2;
				$("#tpostseg_table_list tbody tr:eq("+t+")").before("<tr id=alter"+data.model.postsegId+"><td class=listtablebodyleft1><input type=checkbox value="+data.model.postsegId+"></td><td class=listtablebodyleft1>"+data.model.postsegId+"</td><td class=listtablebodyleft1>"+data.model.postsegName+"</td><td class=listtablebodyleft1>"+data.districtName+"</td><td class=listtablebodyleft1><input type=button value='修改' onclick=tpostseg_alter('"+data.model.postsegId+"','"+data.pdistrictId+"')></td></tr>");
				$('#tr_operator td:last').html('<input type=button value=添加 onclick=tpostseg_add()>');
			}
			function onchange_option_province(postsegId){
				var districtCode='';
				if(postsegId==''){
					//新增
					districtCode=$('#tpostseg_option_province_new').attr('value');
				}else{
					districtCode=$('#tpostseg_option_province_'+postsegId).attr('value');
				}
				var params = {
						postsegId:postsegId,
						districtCode:districtCode
						};
				var url = '${ctx}/basedata/querytpostseg!city.action';
				jQuery.post(url, params, callback_onchange_option_province, 'json');
			}
			function callback_onchange_option_province(data){
				var d=data.orgDistrictCitys;
				s="";
				for(var i=0;i<d.length;i++){
					s+="<option value="+d[i].districtCode+">"+d[i].cityName+"</option>";
				}
				var districts=data.tpdistricts;
				s1="";
				for(var i=0;i<districts.length;i++){
					s1+="<option value="+districts[i].pdistrictId+">"+districts[i].name+"</option>";
				}
				if(data.postsegId==null){
					$('#tpostseg_option_city_new').html(s);
					$('#tpostseg_option_district_new').html(s1);
				}else{
					$('#tpostseg_option_city_'+data.postsegId).html(s);
					$('#tpostseg_option_district_'+data.postsegId).html(s1);
				}
			}
			function onchange_option_city(postsegId){
				var districtCode='';
				if(postsegId==''){
					//新增
					districtCode=$('#tpostseg_option_province_new').attr('value');
				}else{
					districtCode=$('#tpostseg_option_province_'+postsegId).attr('value');
				}
				var params = {
						postsegId:postsegId,
						districtCode:districtCode
						};
				var url = '${ctx}/basedata/querytpostseg!district.action';
				jQuery.post(url, params, callback_onchange_option_city, 'json');
			}
			function callback_onchange_option_city(data){
				var d=data.tpdistricts;
				s="";
				for(var i=0;i<d.length;i++){
					s+="<option value="+d[i].pdistrictId+">"+d[i].name+"</option>";
				}
				if(data.postsegId==null){
					$('#tpostseg_option_district_new').html(s);
				}else{
					$('#tpostseg_option_district_'+data.postsegId).html(s);
				}
			}
			function tpostseg_delete(){
				var c=$('#tpostseg_table_list tbody input:checked');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
				    cv = cv+','+$(this).val();
				});
				if(cv!=''){
					var params = {
									postsegIds:cv
				             	 };
					var url = '${ctx}/basedata/savetpostseg!delete.action';
					jQuery.post(url, params, callback_tpostseg_delete, 'json');
				}			
			}

			function callback_tpostseg_delete(data){
				if(data.delNum!=0){
					var d=data.postsegIds;
					var strs= new Array();
					strs=d.split(",");
					for (i=0;i<strs.length ;i++ )    
				    {    
					    $('#alter'+strs[i]).remove();
				    } 			      
				}
			}
		</script>
	</head>

	<body>
		<s:form namespace="/basedata" action="tpostseg" id="ec">
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
										段信息
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

									</div>
									<br />
									<div id="tablelistdiv">
										<table class="listtable" width="900" id="tpostseg_table_list">
											<tr>
												<th align="left" colspan="5">
													段信息
												</th>
											</tr>
											<tr>
												<td align="left" class=listtableheadleft width="100">
													&nbsp;
												</td>
												<td align="left" class=listtableheadleft width="100">
													段ID
												</td>
												<td align="left" class=listtableheadleft width="300">
													段名字
												</td>
												<td align="left" class=listtableheadleft width="300">
													所属区
												</td>
												<td align="left" class=listtableheadleft>
													操作
												</td>
											</tr>
											<s:iterator id="ele" value="page.result">
												<tr id="alter${ele.postsegId}">
													<td align="left" class=listtablebodyleft1>
														<input type="checkbox" value="${ele.postsegId }"/>
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.postsegId }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.postsegName }
													</td>
													<td align="left" class=listtablebodyleft1>
														<c:set var="pdistrictId" value="${ele.tpdistrict.pdistrictId}"></c:set>
														<%
																Long pdistrictId=(Long)pageContext.getAttribute("pdistrictId");
																Element a=((Cache)request.getAttribute("tpdistrictCacheBean")).get(pdistrictId);
																if(a!=null){
																	Tpdistrict tpdistrict=(Tpdistrict)(a.getValue());
																	out.println(tpdistrict.getName());
																}else{
																	out.println(pdistrictId);
																}
																
														%>
														<c:remove var="cityId"/>
													</td>
													<td align="left" class=listtablebodyleft1>
														<input type="button" value="修改" onclick="tpostseg_alter('${ele.postsegId}','${ele.tpdistrict.pdistrictId}')"/>
													</td>
												</tr>
											</s:iterator>
											<tr id='tr_operator'>
													<td align="left" class=listtablebodyleft1 colspan="4">
														<input type="button" value="删除" onclick="tpostseg_delete()"/>
													</td>
													<td align="left" class=listtablebodyleft1>
														<input type="button" value="添加" onclick="tpostseg_add()"/>
													</td>
											</tr>
											<tr>
												<td colspan="5" align="left" class=listtablebodyleft1>
													${page.url}
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
