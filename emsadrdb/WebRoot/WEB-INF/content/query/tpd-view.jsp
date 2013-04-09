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
				var tpostSegId=${tpostSeg.postsegId}
				$("#"+provinceId).addClass("curr");
				$("#city_"+cityId).addClass("curr");
				$("#tpdistrict_"+tpdistrictId).addClass("curr");
				//$("#tpostSeg_"+tpostSegId).addClass("curr");
				$('#cityId').attr('value',cityId);
				$('#tpdistrictId').attr('value',tpdistrictId);
				$('#tpostSegId').attr('value',tpostSegId);
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
			function addtable(tstrts,url){
				$('#address_table_list').remove();
				var t="<table id=address_table_list class=listtable width=960><tr><th colspan=8 id=tablelistthcontent align=left>名址信息</th></tr></table>";
				$('#tablelistdiv').append(t);
				var tr="<tr><td class=listtableheadleft>街道号</td><td class=listtableheadleft>行政区</td>";
				tr+="<td class=listtableheadleft>街道1段名称 </td><td class=listtableheadleft>街道2段名称</td>";
				tr+="<td class=listtableheadleft>街道3段名称 </td><td class=listtableheadleft>街道4段名称</td>";
				tr+="<td class=listtableheadleft>街道5段名称 </td><td class=listtableheadleft>有效段数</td>";
				tr+="</tr>";
				$('#address_table_list').append(tr);
				if(tstrts.length>0){
					for(var i=0;i<tstrts.length;i++){
						var td="<tr id=alter"+tstrts[i].strtId+">";
						td+="<td class=listtablebodyleft1>"+tstrts[i].strtId+"</td>";
						td+="<td class=listtablebodyleft1>"+tstrts[i].distCd+"</td>";
						td+="<td class=listtablebodyleft1>"+tstrts[i].strt1Name+"</td>";
						td+="<td class=listtablebodyleft1>"+tstrts[i].strt2Name+"</td>";
						td+="<td class=listtablebodyleft1>"+tstrts[i].strt3Name+"</td>";
						td+="<td class=listtablebodyleft1>"+tstrts[i].strt4Name+"</td>";
						td+="<td class=listtablebodyleft1>"+tstrts[i].strt5Name+"</td>";
						td+="<td class=listtablebodyleft1>"+tstrts[i].segNum+"</td>";
						td+="</tr>";
						$('#address_table_list').append(td);
					}
					var footer="<tr id='tr_operator'><td align=left class=listtablebodyleft1 colspan=8>"+url+"</td></tr>";
					$('#address_table_list').append(footer);
				}
			}
			function view_city(provincename,provinceid){
			    var params = {
			    				provinceId: provinceid
			                 };
			    $("#td_privince_content a").removeClass(); 
			    $('#'+provinceid).addClass("curr");
			    var url="${ctx}/query/querytpd!province.action";
			    jQuery.post(url, params, callback_view_city, 'json');
			}
			function callback_view_city(data){
				make_citys(data.citys,data.cityId);
				make_tpdistrict(data.tpdistricts,data.tpdistrictId);
				make_tpostSeg(data.tpostSegs,data.tpostSegId);
				addtable(data.page.result,data.page.ajaxUrl);
			}
			function view_disricts(cityId){
			    var params = {
			    				cityId: cityId
			                 };
			    $("#td_city_content a").removeClass(); 
			    $('#city_'+cityId).addClass("curr");
			    var url="${ctx}/query/querytpd!city.action";
			    jQuery.post(url, params, callback_view_disricts, 'json');
			}
			function callback_view_disricts(data){
				make_tpdistrict(data.tpdistricts,data.tpdistrictId);
				make_tpostSeg(data.tpostSegs,data.tpostSegId);
				addtable(data.page.result,data.page.ajaxUrl);
			}
			function view_postSegs(tpdistrictId){
			    var params = {
			    			tpdistrictId: tpdistrictId
			                 };
			    $("#td_tpdistrict_content a").removeClass(); 
			    $('#tpdistrict_'+tpdistrictId).addClass("curr");
			    var url="${ctx}/query/querytpd!tpdistrict.action";
			    jQuery.post(url, params, callback_view_postSegs, 'json');
			}
			function callback_view_postSegs(data){
				make_tpostSeg(data.tpostSegs,data.tpostSegId);
				addtable(data.page.result,data.page.ajaxUrl);
			}
			function view_address(tpostSegId){
			    var params = {
			    				tpostSegId: tpostSegId
			                 };
			    $("#td_tpostSeg_content a").removeClass(); 
			    $('#tpostSeg_'+tpostSegId).addClass("curr");
			    var url="${ctx}/query/querytpd!tpostSeg.action";
			    jQuery.post(url, params, callback_view_address, 'json');
			}
			function callback_view_address(data){
				addtable(data.page.result,data.page.ajaxUrl);
			}
			function ajaxQueryPage(pageNo){
				var provinceId=$("#td_privince_content a[class=curr]").attr('id'); 
				var cityId=$("#td_city_content a[class=curr]").attr('id').substr(5); 
				var tpdistrictId=$("#td_tpdistrict_content a[class=curr]").attr('id').substr(11); 
				var tpostSegId=$("#td_tpostSeg_content a[class=curr]"); 
				if(tpostSegId.length!=0){
					tpostSegId=tpostSegId.attr('id').substr(9);
				}else{
					tpostSegId='';
				}
				var params = {
								provinceId: provinceId,
								cityId: cityId,
								tpdistrictId: tpdistrictId,
								tpostSegId: tpostSegId,
								pageNo: pageNo
			                 };
                var url="${ctx}/query/querytpd!ajaxPageQuery.action";
			    jQuery.post(url, params, callback_ajaxQueryPage, 'json');
			}
			function callback_ajaxQueryPage(data){
				addtable(data.page.result,data.page.ajaxUrl);
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
											<tr>
												<td class="listtablehead" width="10">
													段：
												</td>
												<td class="js_left_txt" id="td_tpostSeg_content">
													<s:iterator id="eles" value="tpostSegs" var="tps">
														<a href="javascript:view_address('${tps.postsegId }')" id='tpostSeg_${tps.postsegId}'>${tps.postsegName}</a>&nbsp;&nbsp;
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
												<th align="left" colspan="8">
													名址信息
												</th>
											</tr>
											<tr>
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
													有效段数
												</td>
											</tr>
											<s:iterator id="ele" value="rpostSegPage.result">
												<tr id="rpostSeg_list_${ele.strtId}">
													<td align="left" class=listtablebodyleft1>
														${ele.strtId }
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
														${ele.segNum }
													</td>
												</tr>
											</s:iterator>
											<tr id='tr_operator'>
												<td align="left" class=listtablebodyleft1 colspan="8">
													${rpostSegPage.ajaxUrl }
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
