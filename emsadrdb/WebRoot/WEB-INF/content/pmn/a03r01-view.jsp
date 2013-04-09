<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<html>
	<head><title>用户区信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			$(document).ready(function(){
				if($("#city_select").children().length==2){
					$("#city_select").children().eq(0).remove();
				}
			})
			//判断是否是直辖市
			function isMunicipalities(name){
				if(name=='110000'||name=='310000'||name=='120000'||name=='500000'){
					return true;
				}else{
					return false;
				}
			}
			function citys_select(){
				var provinceId=$('#province_select').attr('value');
				var params = {
								usProvinceOffice: provinceId
				             };
				var url = '${ctx}/pmn/a03r02!citys.action';
				if(provinceId!=''){
					jQuery.post(url, params, callback_citys_select, 'json');
				}else{
					var s="<option value=''>全部</option>";
					$('#city_select').html(s);
					$('#district_select').html(s);
					$('#department_select').html(s);
					$('#postseg_select').html(s);
				}
			}
			function callback_citys_select(data){
				var s="<option value=''>全部</option>";
				$('#district_select').html(s);
				$('#department_select').html(s);
				$('#postseg_select').html(s);
				var citys=data.citys;
				if(citys!=null){
					for(var i=0;i<citys.length;i++){
						s+="<option value="+citys[i].districtCode+">"+citys[i].cityName+"</option>";
					}
					$('#city_select').html(s);
				}else{
					$('#city_select').html(s);
				}
				if(isMunicipalities(data.usProvinceOffice)){
					$('#city_select').children().eq(0).remove();
					var districts=data.districts;
					var ss="<option value=''>全部</option>";
					if(districts!=null){
						for(var i=0;i<districts.length;i++){
							ss+="<option value="+districts[i].dtPkCode+">"+districts[i].dtName+"</option>";
						}
						$('#district_select').html(ss);
					}else{
						$('#district_select').html(ss);
					}
				}
			}
			function districts_select(){
				var cityId=$('#city_select').attr('value');
				var params = {
								cityId: cityId
				             };
				var url = '${ctx}/pmn/a03r02!districts.action';
				if(cityId!=''){
					jQuery.post(url, params, callback_districts_select, 'json');
				}else{
					var s="<option value=''>全部</option>";
					$('#district_select').html(s);
					$('#department_select').html(s);
					$('#postseg_select').html(s);
				}
				
			}
			function callback_districts_select(data){
				var s="<option value=''>全部</option>";
				$('#department_select').html(s);
				$('#postseg_select').html(s);
				var districts=data.districts;
				if(districts!=null){
					for(var i=0;i<districts.length;i++){
						s+="<option value="+districts[i].dtPkCode+">"+districts[i].dtName+"</option>";
					}
					$('#district_select').html(s);
				}else{
					$('#district_select').html(s);
				}
			}
			function departments_select(){
				var districtId=$('#district_select').attr('value');
				var params = {
								districtId: districtId
				             };
				var url = '${ctx}/pmn/a03r02!departments.action';
				if(districtId!=''){
					jQuery.post(url, params, callback_departments_select, 'json');
				}else{
					var s="<option value=''>全部</option>";
					$('#department_select').html(s);
					$('#postseg_select').html(s);
				}
				
			}
			function callback_departments_select(data){
				var s="<option value=''>全部</option>";
				var departments=data.departments;
				if(departments!=null){
					for(var i=0;i<departments.length;i++){
						s+="<option value="+departments[i].dmPkCode+">"+departments[i].dmName+"</option>";
					}
					$('#department_select').html(s);
				}else{
					$('#department_select').html(s);
				}
				var ss="<option value=''>全部</option>";
				var postsegs=data.postsegs;
				if(postsegs!=null){
					for(var i=0;i<postsegs.length;i++){
						ss+="<option value="+postsegs[i].pgPkCode+">"+postsegs[i].pgName+"</option>";
					}
					$('#postseg_select').html(ss);
				}else{
					$('#postseg_select').html(ss);
				}
			}
			function postsegs_select(){
				var departmentId=$('#department_select').attr('value');
				var params = {
								departmentId: departmentId
				             };
				var url = '${ctx}/pmn/a03r02!postsegs.action';
				if(departmentId!=''){
					jQuery.post(url, params, callback_postsegs_select, 'json');
				}else{
					var s="<option value=''>全部</option>";
					$('#postseg_select').html(s);
				}
				
			}
			function callback_postsegs_select(data){
				var ss="<option value=''>全部</option>";
				var postsegs=data.postsegs;
				if(postsegs!=null){
					for(var i=0;i<postsegs.length;i++){
						ss+="<option value="+postsegs[i].pgPkCode+">"+postsegs[i].pgName+"</option>";
					}
					$('#postseg_select').html(ss);
				}else{
					$('#postseg_select').html(ss);
				}
			}
			function user_add(){
				window.location.href="${ctx}/pmn/a03cud01!edit.action";
			}
		</script>
	</head>
	<body>
		<s:form namespace="/pmn" action="a03r01!query.action" id="ec">
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
										用户管理
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
										<table class="listtable" id="viewquery_table" width="960">
											<tr>
												<td align="right" class=listtablebodyleft1 width="100">用户级别：</td>
												<td align="left" class=listtablebodyleft1 colspan="2"><s:select name="rulLevel" headerKey="" headerValue="全部" theme="simple" list="levelRoles" listKey="rulLevel" listValue="rulName">
													</s:select></td>
											</tr>
											<tr>
												<td align="right" class=listtablebodyleft1>用户所属：</td>
												<td align="left" class=listtablebodyleft1>
													<s:select name="usCountryOffice" theme="simple" list="#{'中国':'000000'}" listKey="value" listValue="key">
													</s:select>
													<s:select id="province_select"  name="usProvinceOffice" onchange="citys_select()" headerKey="" headerValue="全部" theme="simple" list="provinces" listKey="districtCode" listValue="provinceName">
													</s:select>
													<s:select id="city_select"  name="usCityOffice" cssStyle="width:150" onchange="districts_select()" headerKey="" headerValue="全部" theme="simple" list="citys" listKey="districtCode" listValue="cityName" >
													</s:select>
													<s:select id="district_select" name="usDistrictOffice" cssStyle="width:150" onchange="departments_select()" headerKey="" headerValue="全部" theme="simple" list="districts" listKey="dtPkCode" listValue="dtName" >
													</s:select>
													<s:select id="department_select" name="usDepartmentOffice" cssStyle="width:150" headerKey="" headerValue="全部" theme="simple" list="departments" listKey="dmPkCode" listValue="dmName" onchange="postsegs_select()">
													</s:select>
													<s:select id="postseg_select" name="usPostsegOffice" headerKey="" headerValue="全部" theme="simple" list="postsegs" listKey="pgPkCode" listValue="pgName" >
													</s:select>
												</td>
												<td align="right" class=listtablebodyleft1><input type="submit" value="查询"/></td>
											</tr>
										</table>
									</div>
									<br />
									<div id="tablelistdiv">
										<table class="listtable" width="960"
											id="resource_table_list">
											<tr>
												<th align="left" colspan="6">
													用户信息
												</th>
											</tr>
											<tr>
												<td align="left" class=listtableheadleft width="100">
													登录名
												</td>
												<td align="left" class=listtableheadleft width="100">
													真实姓名
												</td>
												<td align="left" class=listtableheadleft width="100">
													电话
												</td>
												<td align="left" class=listtableheadleft width="400">
													所属单位
												</td>
												<td align="left" class=listtableheadleft width="80">
													级别
												</td>
												<td align="left" class=listtableheadleft>
													操作
												</td>
											</tr>
											<s:iterator id="ele" value="page.result">
												<tr>
													<td align="left" class=listtablebodyleft1>
														${ele.usLoginId }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.usName }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.usMobile }&nbsp;&nbsp;${ele.usPhone }
													</td>
													<td align="left" class=listtablebodyleft1>
														${ele.transNames }
													</td>
													<td align="left" class=listtablebodyleft1>
														<c:if test="${ele.rulLevel==2}">国家管理员</c:if>
														<c:if test="${ele.rulLevel==5}">省级管理员</c:if>
														<c:if test="${ele.rulLevel==10}">市级管理员</c:if>
														<c:if test="${ele.rulLevel==15}">投递区管理员</c:if>
														<c:if test="${ele.rulLevel==20}">投递部管理员</c:if>
														<c:if test="${ele.rulLevel==25}">投递段管理员</c:if>
													</td>
													<td align="left" class=listtablebodyleft1>
														<a href="${ctx}/pmn/a02cud01!edit.action?rePkId=${ele.usPkId}">查看</a>
														&nbsp;&nbsp;
														<a href="${ctx}/pmn/a02cud01!delete.action?rePkId=${ele.usPkId}">删除</a>
													</td>
												</tr>
											</s:iterator>
											<tr id='tr_operator'>
												<td align="left" class=listtablebodyleft1 colspan="5">
													&nbsp;
												</td>
												<td align="left" class=listtablebodyleft1>
													<input type="button" value="添加" onclick="user_add()" />
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
