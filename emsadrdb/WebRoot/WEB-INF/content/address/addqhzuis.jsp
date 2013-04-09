<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/address/addr.jsp"%>
<html>

	<head>
		<title>前后缀编辑</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			var flag='Add';
			$(document).ready(function(){
				if('${qhzuiBean.DIST_CD}' != ''){
					$("input[name='FIX_FLAG'][value=${qhzuiBean.FIX_FLAG}]").attr("checked",'checked');
					$("#PROVINCE_NAMES").val('${qhzuiBean.DIST_CD}'.substr(0,2) + '0000');
					$("#upquery").show();
					$("#addquery").hide();
				}else{
					$("#upquery").hide();
					$("#addquery").show();
				}
				viewcity();
				getPYf();
			})
			//展示市
			function viewcity(){
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				var tRulUsCityOffice= null;
				if(isMunicipalities(tPROVINCE_NAME)){
			    	$('#CITY_NAMES').hide();
					$('#CITY_NAMES option').remove();
			    }else{
					$('#CITY_NAMES').show();
				}
				var url = '${ctx}/address/b04r03addroption!citys.action';
				var params = {
					PROVINCE_NAME: tPROVINCE_NAME
				 };
				jQuery.post(url, params, callbackcity, 'json');
			}
			function callbackcity(data){
				var d=data.districtBeans;
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				if(!isMunicipalities(tPROVINCE_NAME)){
					$('#CITY_NAMES option').remove();
					for(var i=0;i<d.length;i++){
						if('${qhzuiBean.DIST_CD}' != '' && '${qhzuiBean.DIST_CD}'.substr(0,4) + '00' == d[i].DISTRICT_CODE){
							$("#CITY_NAMES").append("<option selected='selected' value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
						}else{
							$("#CITY_NAMES").append("<option value='" + d[i].DISTRICT_CODE + "'>" + d[i].CITY_NAME + "</option>"); 
						}
					}

			    }
			}
			function ajaxAddUpAlert(tflag){
				flag = tflag;
				var tDIST_CD=$("#PROVINCE_NAMES").val(); 
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				if(!isMunicipalities(tPROVINCE_NAME)){
					tDIST_CD=$("#CITY_NAMES").val(); 
				}
				var tFIX=$('#FIX').val().replace(new RegExp(' ', 'g'), '');
				if(tFIX==''){
					$.prompt('请填入前后缀',{buttons: {'确定': true}});
					return false;
				}
				if(flag=='Add'){
					ajaxAddUp(flag);
				}else{
					$.prompt('该操作将修改此前后缀！确认执行操作？',{
						buttons: {'确定': true, '取消': false },
						callback: function(v){
							if(v){
								ajaxAddUp(flag);
							}
						}
					});
				}
			}
			function ajaxAddUp(flag){
				var tDIST_CD=$("#PROVINCE_NAMES").val(); 
				var tPROVINCE_NAME=$("#PROVINCE_NAMES").find("option:selected").text(); 
				if(!isMunicipalities(tPROVINCE_NAME)){
					tDIST_CD=$("#CITY_NAMES").val(); 
				}
				var tQIANZUI_ID=$("#QIANZUI_ID").val(); 
				var tFIX=$("#FIX").val().replace(new RegExp(' ', 'g'), '');
				var tFIX_ABBR=$("#FIX_ABBR").val().replace(new RegExp(' ', 'g'), '');
				var tFIX_XZ=$("#FIX_XZ").val().replace(new RegExp(' ', 'g'), '');
				var tFIX_FLAG=$("input[name='FIX_FLAG'][checked]").val(); 
				var tFIX_SMP=$("#FIX_SMP").val().replace(new RegExp(' ', 'g'), '');
				var params = {
						DIST_CD:tDIST_CD,
						QIANZUI_ID:tQIANZUI_ID,
						FIX:tFIX,
						FIX_ABBR:tFIX_ABBR,
						FIX_XZ:tFIX_XZ,
						FIX_FLAG:tFIX_FLAG,
						FIX_SMP:tFIX_SMP
	            };
				if(flag=='Add'){
					var url = '${ctx}/address/b04cud02addrconfig!addqhzuis.action';
				}else{
					var url = '${ctx}/address/b04cud02addrconfig!upqhzuis.action';
				}
				$.blockUI({ message: '<img src="${ctx}/widgets/jquery/blockui/images/loadingAnimation.gif" />',css:{width:'200', border:'0'}});
			    jQuery.post(url, params, callajaxAdd, 'json');
			}
			function callajaxAdd(data){
				$.prompt(data.saveMessage,{buttons: {'确定': true}});
				$.unblockUI();
			}
			function backQuery(flag){
				window.returnValue=flag;
				window.opener =null;
				window.close();
			}
			function getPYf(){
				var tFIX=$('#FIX').val();
				$('#FIX_SMP').val(getPY(tFIX));
			}
		</script>
	</head>
	<body>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" align="left">
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
										前后缀编辑
									</div>
								</td>
								<td height="31">
								<div class="titlebt_unback" align="right">
								<input id="QIANZUI_ID" type="hidden" value="${qhzuiBean.QIANZUI_ID}"/>
								<a id="upquery" href='#' onClick="ajaxAddUpAlert('Up')"  style="font-size:12px">修改</a>
								<a id="addquery" href='#' onClick="ajaxAddUpAlert('Add')"  style="font-size:12px">新增</a>
								
								<a id="queryback" href='#' onClick="backQuery('fresh')"  style="font-size:12px">返回</a>
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
										<table class="listtable" id="viewquery_table"  width="100%">
											<tr>
												<td class="listtablehead" width="80">
													行政区域:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<s:select theme="simple" list="provinces" id="PROVINCE_NAMES" listKey="DISTRICT_CODE" listValue="PROVINCE_NAME" onchange="viewcity()"></s:select>
													<select class="simple" id="CITY_NAMES" style="width:100"></select>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													前后缀类型:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
													<input type="radio" name="FIX_FLAG" id="FIX_FLAG" value="0" checked="checked">前缀<label id="jddss" style="color:#FF0000"></label>
													<input type="radio" name="FIX_FLAG" id="FIX_FLAG" value="1">后缀<label id="xqdss" style="color:#FF0000"></label>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													前后缀名:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="FIX" style="width: 300px; height:20px;" id="FIX" class="ac_input" size="300" value="${qhzuiBean.FIX}" onFocus="this.select();" onKeyUp="getPYf()"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													前后缀别名:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="FIX_ABBR" style="width: 300px; height:20px;" id="FIX_ABBR" class="ac_input" size="300" value="${qhzuiBean.FIX_ABBR}" onFocus="this.select();"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													前后缀简拼:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="FIX_SMP" style="width: 300px; height:20px;" id="FIX_SMP" class="ac_input" size="300" value="${qhzuiBean.FIX_SMP}" onFocus="this.select();"/>
												</td>
											</tr>
											<tr>
												<td class="listtablehead" width="80">
													前后缀限制:
												</td>
												<td class="js_left_txt" id="td_privince_content" colspan="3">
												<input type="text" name="FIX_XZ" style="width: 300px; height:20px;" id="FIX_XZ" class="ac_input" size="300" value="${qhzuiBean.FIX_XZ}" onFocus="this.select();"/>
												</td>
											</tr>
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
	</body>
</html>
