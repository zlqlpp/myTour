<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<html>
	<head>
		<title>用户信息</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			$(document).ready(function(){
				if($("#city_select").children().length==2){
					$("#city_select").children().eq(0).remove();
				}
				if(${rulLevelStr=='5'}){//省
					$('#province_select').attr("disabled",true);
				}
				if(${rulLevelStr=='10'}){//市
					$('#province_select').attr("disabled",true);
					$('#city_select').attr("disabled",true);
				}
				if(${rulLevelStr=='15'}){//区
					$('#province_select').attr("disabled",true);
					$('#city_select').attr("disabled",true);
					$('#district_select').attr("disabled",true);
				}
				var haveCheckedrePkIds="${haveCheckedrePkIds}";
				var usPkIds=haveCheckedrePkIds.split(",");
				var c=$('#user_table_list tbody input[name=roles.rePkId]');
				var cv='';              //定义变量
				c.each(function(i){        //循环拼装被选中项的值
				    cv = cv+','+$(this).val();
				});
				if(cv!=''){
					cv=cv.substr(1);
					var tmp=cv.split(",");
					for (i=0;i<tmp.length;i++)
					{			
						for (j=0;j<usPkIds.length;j++ )
						{
							
							if(usPkIds[j]==tmp[i])
								{	
									$("#checkbox"+tmp[i]).attr("checked","checked");break;
								}
						}
					}
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
				$('#postseg_select').html(s);
				var departments=data.departments;
				if(departments!=null){
					for(var i=0;i<departments.length;i++){
						s+="<option value="+departments[i].dmPkCode+">"+departments[i].dmName+"</option>";
					}
					$('#department_select').html(s);
				}else{
					$('#department_select').html(s);
				}
			}
			function submitMyFrom(){
				 $('#ec').action="a03cud01!save.action";
				 $("#province_select").attr("disabled",false);
				 $("#city_select").attr("disabled",false);
				 $("#district_select").attr("disabled",false);
				 $("#department_select").attr("disabled",false);
				 $('#ec').submit();
			}
		</script>
	</head>
	<body>
		<s:form namespace="/pmn" action="a03cud01!save.action" id="ec">
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
										用户信息
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
											id="user_table_list">
											<tr>
												<th align="left" colspan="4">
													用户信息
												</th>
											</tr>
											<tr>
												<td align="right" class=listtablebodyleft1 width="80">
													用户名：
												</td>
												<td align="left" class=listtablebodyleft1 width="200">
													<input type="text" name="usLoginId" size="40" value="${model.usLoginId}"/>
													<input type="hidden" name="usPkId" value="${model.usPkId}"/>
												</td>
												<td align="right" class=listtablebodyleft1 width="80">
													真实姓名：
												</td>
												<td align="left" class=listtablebodyleft1 >
													<input type="text" name="usName" size="50"  value="${model.usName}"/>
												</td>
											</tr>
											<tr>
												<td align="right" class=listtablebodyleft1>
													电话：
												</td>
												<td align="left" class=listtablebodyleft1>
													<input type="text" name="usPhone" size="40" value="${model.usPhone}"/>
												</td>
												<td align="right" class=listtablebodyleft1>
													手机：
												</td>
												<td align="left" class=listtablebodyleft1 >
													<input type="text" name="usMobile" size="50"  value="${model.usMobile}"/>
												</td>
											</tr>
											<tr>
												<td align="right" class=listtablebodyleft1>
													管理员级别：
												</td>
												<td align="left" class=listtablebodyleft1 colspan="3">
													<s:select  value="model.rulLevel" name="rulLevel" theme="simple" list="roleLevels" listKey="rulLevel" listValue="rulName">
													</s:select>
												</td>
											</tr>
											<tr>
												<td align="right" class=listtablebodyleft1>用户所属：</td>
												<td align="left" class=listtablebodyleft1 colspan="3">
													<s:select name="usCountryOffice" theme="simple" list="#{'中国':'000000'}" listKey="value" listValue="key">
													</s:select>
													<s:select id="province_select" value="provinceId" name="usProvinceOffice" onchange="citys_select()" headerKey="" headerValue="全部" theme="simple" list="provinces" listKey="districtCode" listValue="provinceName">
													</s:select>
													<s:select id="city_select" value="cityId"  name="usCityOffice" cssStyle="width:150" onchange="districts_select()" headerKey="" headerValue="全部" theme="simple" list="citys" listKey="districtCode" listValue="cityName" >
													</s:select>
													<s:select id="district_select" value="districtId" name="usDistrictOffice" cssStyle="width:150" onchange="departments_select()" headerKey="" headerValue="全部" theme="simple" list="districts" listKey="dtPkCode" listValue="dtName" >
													</s:select>
													<s:select id="department_select" value="departmentId" name="usDepartmentOffice" cssStyle="width:150" headerKey="" headerValue="全部" theme="simple" list="departments" listKey="dmPkCode" listValue="dmName" >
													</s:select>
													<s:select id="postseg_select" name="usPostsegOffice" headerKey="" headerValue="全部" theme="simple" list="postsegs" listKey="pgPkCode" listValue="pgName" >
													</s:select>
												</td>
											</tr>
											<s:iterator id="ele" value="levelRoles">
												<tr>
													<td align="right" class=listtablebodyleft1 width="80">
														&nbsp;
													</td>
													<td align="left" class=listtablebodyleft1 colspan="3">
														<input id="checkbox${ele.rePkId}" type="checkbox" name="roles.rePkId"  value="${ele.rePkId}"/>
														&nbsp;
														${ele.reViewName }
													</td>
												</tr>
											</s:iterator>
											<tr id='tr_operator'>
												<td align="left" class=listtablebodyleft1 colspan="3">
													<input type="button" value="返回" onclick="history.go(-1)"/>
												</td>
												<td align="right" class=listtablebodyleft1>
													<input type="button" onclick="submitMyFrom()" value="保存" />
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
