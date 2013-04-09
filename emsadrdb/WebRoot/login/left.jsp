<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
  <title> ZTREE DEMO </title>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
  <link rel="stylesheet" href="zTreeStyle/zTreeStyle.css" type="text/css"/>
  <style>
	body {
		background-color: #F8F9FA;
		margin:0; padding:0;
		text-align: center;
	}
	div, p, table, th, td {
		list-style:none; 
		margin:0; padding:0; 
		color:#333; font-size:12px; 
		font-family:dotum, Verdana, Arial, Helvetica, AppleGothic, sans-serif;
	}
  </style>
  <script type="text/javascript" src="jquery-1.4.2.js"></script>
  <script type="text/javascript" src="jquery-ztree-2.5.js"></script>

  <script language="JavaScript">
	var zTree;
	
	var demoIframe;
	
	var setting = {
		isSimpleData: true,
		treeNodeKey: "id",
		treeNodeParentKey: "pId",
		showLine: true,
		root:{ 
			isRoot:true,
			nodes:[]
		}
	};
	
	zNodes =[
		<c:if test="${EMS_USER.rulLevel==0}">
		{ id:1, pId:0, name:"全名址信息维护", open:false},
		{ id:11, pId:1, name:"基础信息维护", open:false},
			{ id:111, pId:11, name:"投递区信息维护", "url":"${ctx}/wh/b01r01!view.action", "target":"main"},
			{ id:112, pId:11, name:"经转维护", "url":"${ctx}/disp/b07r01dispview!viewdisps.action", "target":"main"},
			{ id:113, pId:11, name:"行政批区", "url":"${ctx}/disp/b07r01dispview!viewwhtrans.action", "target":"main"},
			{ id:114, pId:11, name:"行政区域", "url":"${ctx}/disp/b07r01dispview!viewdistrict.action", "target":"main"},

		{ id:12, pId:1, name:"地址信息维护", open:false},
			{ id:121, pId:12, name:"地址维护", "url":"${ctx}/address/b04r01addrview!viewstreets.action", "target":"main"},
			{ id:122, pId:12, name:"地址审核", "url":"${ctx}/address/b04r01addrview!shdata.action", "target":"main"},
			{ id:123, pId:12, name:"前后缀信息维护", "url":"${ctx}/address/b04r01addrview!viewqhzuis.action", "target":"main"},
			{ id:124, pId:12, name:"地址检核", "url":"${ctx}/address/b04r01addrview!viewtwstr.action", "target":"main"},
			{ id:125, pId:12, name:"地址匹配", "url":"${ctx}/address/b04r01addrview!viewjnadtpg.action", "target":"main"},
			
		{ id:13, pId:1, name:"投递区部段配置", open:false},
			{ id:131, pId:13, name:"邮编批量配置", "url":"${ctx}/adr/c03r01!view.action", "target":"main"},
			{ id:132, pId:13, name:"行政区批量配置", "url":"${ctx}/adr/c05r01!view.action", "target":"main"},
			{ id:133, pId:13, name:"本市地址配置", "url":"${ctx}/adr/c04r01!view.action", "target":"main"},
			{ id:134, pId:13, name:"无门牌小区配置", "url":"${ctx}/adr/c13r01!view.action?flag=1", "target":"main"},
			{ id:135, pId:13, name:"本市批段地址审核", "url":"${ctx}/adr/c09r01!view.action", "target":"main"},
			{ id:136, pId:13, name:"批段地址审核配置", "url":"${ctx}/adr/c12r01!view.action", "target":"main"},

		{ id:14, pId:1, name:"差异数据维护", open:false},
			{ id:140, pId:14, name:"地址重复号段", "url":"${ctx}/adr/c08r01!viewCheckStreet.action", "target":"main"},
			{ id:141, pId:14, name:"门牌号段缺失", "url":"${ctx}/address/b04r01addrview!viewstreetsdium.action", "target":"main"},
			{ id:142, pId:14, name:"存疑街道名称信息", "url":"${ctx}/address/b04r01addrview!viewstreetsqfhdb.action", "target":"main"},
			{ id:143, pId:14, name:"存疑机构名称信息", "url":"${ctx}/address/b04r01addrview!vieworganluan.action", "target":"main"},
			{ id:144, pId:14, name:"存疑小区名称信息", "url":"${ctx}/address/b04r01addrview!viewbldgrsdnsluan.action", "target":"main"},
			{ id:145, pId:14, name:"地址缺失机构信息", "url":"${ctx}/address/b04r01addrview!vieworgandius.action", "target":"main"},
			{ id:146, pId:14, name:"地址缺失小区信息", "url":"${ctx}/address/b04r01addrview!viewbldgrsdnsdius.action", "target":"main"},
			
	{ id:2, pId:0, name:"进口局批区批段", open:false},
			{ id:21, pId:2, name:"进口局批区批段(当日)", "url":"${ctx}/address/b04r01addrview!viewrgpqpd.action", "target":"main"},
			{ id:22, pId:2, name:"进口局批区批段(历史)", "url":"${ctx}/address/b04r01addrview!viewrgpqpdj.action", "target":"main"},
			{ id:23, pId:2, name:"进口局批区批段(南京)", "url":"${ctx}/address/b04r01addrview!viewrgpqpdn.action", "target":"main"},
			{ id:24, pId:2, name:"反馈确认", "url":"${ctx}/address/b04r01addrview!viewfkqr.action", "target":"main"},
			
	{ id:3, pId:0, name:"全名址信息查询", open:false},
		{ id:31, pId:3, name:"地址配置查询", "url":"${ctx}/adr/c10r01!view.action", "target":"main"},
		{ id:32, pId:3, name:"快速综合查询", "url":"${ctx}/adr/c11r01!view.action", "target":"main"},
		{ id:34, pId:3, name:"收容地址查询(按出口统计)", "url":"${ctx}/address/b04r01addrview!viewcksr.action", "target":"main"},
		{ id:35, pId:3, name:"批区批段全数据查询", "url":"${ctx}/address/b04r01addrview!viewzshf.action", "target":"main"},

	{ id:4, pId:0, name:"统计分析", open:false},
		
		{ id:41, pId:4, name:"全名址信息匹配率统计(按省)", "url":"${ctx}/disp/b07r01dispview!viewtjprb.action", "target":"main"},
		{ id:42, pId:4, name:"全名址信息匹配率统计(按市)", "url":"${ctx}/disp/b07r01dispview!viewtjcrb.action", "target":"main"},
		{ id:43, pId:4, name:"全名址信息匹配库统计(按省)", "url":"${ctx}/disp/b07r01dispview!viewtjpqb.action", "target":"main"},
		{ id:44, pId:4, name:"全名址信息匹配库统计(按市)", "url":"${ctx}/disp/b07r01dispview!viewtjcqb.action", "target":"main"},
		{ id:45, pId:4, name:"投递区部段信息统计", "url":"${ctx}/disp/b07r01dispview!viewtjdtqb.action", "target":"main"},
		{ id:46, pId:4, name:"投递区覆盖范围统计", "url":"${ctx}/disp/b07r01dispview!viewtjtdqqb.action", "target":"main"},
		{ id:47, pId:4, name:"批区批段维护率(按省)", "url":"${ctx}/disp/b07r01dispview!viewtjpwhrb.action", "target":"main"},
		{ id:48, pId:4, name:"全名址投递区信息量统计", "url":"${ctx}/disp/b07r01dispview!viewtjdt.action", "target":"main"},
		
		
			{ id:5, pId:0, name:"权限管理", open:false},
			{ id:51, pId:5, name:"资源管理", "url":"${ctx}/pmn/a01r01!enter.action", "target":"main"},
			{ id:52, pId:5, name:"权限管理", "url":"${ctx}/pmn/a02r01!enter.action", "target":"main"},
			{ id:56, pId:5, name:"初始化缓存", "url":"${ctx}/init/init.action", "target":"main"},
			{ id:53, pId:5, name:"用户管理", "url":"${ctx}/pmn/a03r01!view.action", "target":"main"},
			{ id:54, pId:5, name:"我的信息", "url":"${ctx}/pmn/a04r01!view.action", "target":"main"},
			{ id:55, pId:5, name:"修改密码", "url":"${ctx}/pmn/a04r01!editPasswd.action", "target":"main"},
			{ id:6, pId:0, name:"公告信息", open:false}
			
		</c:if>	
		
		<c:if test="${EMS_USER.rulLevel==2}">
		
			{ id:1, pId:0, name:"全名址信息维护", open:false},
				{ id:11, pId:1, name:"基础信息维护", open:false},
					{ id:111, pId:11, name:"投递区信息维护", "url":"${ctx}/wh/b01r01!view.action", "target":"main"},
					{ id:111, pId:11, name:"揽投部直封集散设置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwtabnjjs.action", "target":"main"},
					{ id:112, pId:11, name:"经转维护", "url":"${ctx}/disp/b07r01dispview!viewdisps.action", "target":"main"},
					{ id:113, pId:11, name:"行政批区", "url":"${ctx}/disp/b07r01dispview!viewwhtrans.action", "target":"main"},
					{ id:114, pId:11, name:"行政区域", "url":"${ctx}/disp/b07r01dispview!viewdistrict.action", "target":"main"},

				{ id:12, pId:1, name:"地址信息维护", open:false},
					{ id:121, pId:12, name:"地址维护", "url":"${ctx}/address/b04r01addrview!viewstreets.action", "target":"main"},
					{ id:122, pId:12, name:"地址审核", "url":"${ctx}/address/b04r01addrview!shdata.action", "target":"main"},
					{ id:123, pId:12, name:"前后缀信息维护", "url":"${ctx}/address/b04r01addrview!viewqhzuis.action", "target":"main"},
					{ id:124, pId:12, name:"地址检核", "url":"${ctx}/address/b04r01addrview!viewtwstr.action", "target":"main"},
					
				{ id:13, pId:1, name:"投递区部段配置", open:false},
					{ id:131, pId:13, name:"邮编批量配置", "url":"${ctx}/adr/c03r01!view.action", "target":"main"},
					{ id:132, pId:13, name:"行政区批量配置", "url":"${ctx}/adr/c05r01!view.action", "target":"main"},
					{ id:133, pId:13, name:"本市地址配置", "url":"${ctx}/adr/c04r01!view.action", "target":"main"},
					{ id:134, pId:13, name:"无门牌小区配置", "url":"${ctx}/adr/c13r01!view.action?flag=1", "target":"main"},
					{ id:135, pId:13, name:"本市批段地址审核", "url":"${ctx}/adr/c09r01!view.action", "target":"main"},
					{ id:136, pId:13, name:"批段地址审核配置", "url":"${ctx}/adr/c12r01!view.action", "target":"main"},

				{ id:14, pId:1, name:"差异数据维护", open:false},
					{ id:140, pId:14, name:"地址重复号段", "url":"${ctx}/adr/c08r01!viewCheckStreet.action", "target":"main"},
					{ id:141, pId:14, name:"门牌号段缺失", "url":"${ctx}/address/b04r01addrview!viewstreetsdium.action", "target":"main"},
					{ id:142, pId:14, name:"存疑街道名称信息", "url":"${ctx}/address/b04r01addrview!viewstreetsqfhdb.action", "target":"main"},
					{ id:143, pId:14, name:"存疑机构名称信息", "url":"${ctx}/address/b04r01addrview!vieworganluan.action", "target":"main"},
					{ id:144, pId:14, name:"存疑小区名称信息", "url":"${ctx}/address/b04r01addrview!viewbldgrsdnsluan.action", "target":"main"},
					{ id:145, pId:14, name:"地址缺失机构信息", "url":"${ctx}/address/b04r01addrview!vieworgandius.action", "target":"main"},
					{ id:146, pId:14, name:"地址缺失小区信息", "url":"${ctx}/address/b04r01addrview!viewbldgrsdnsdius.action", "target":"main"},
					
			{ id:2, pId:0, name:"进口局批区批段", open:false},
					
					{ id:21, pId:2, name:"进口局批区批段(当日)", "url":"${ctx}/address/b04r01addrview!viewrgpqpd.action", "target":"main"},
					{ id:22, pId:2, name:"进口局批区批段(历史)", "url":"${ctx}/address/b04r01addrview!viewrgpqpdj.action", "target":"main"},
					{ id:23, pId:2, name:"进口局批区批段(南京)", "url":"${ctx}/address/b04r01addrview!viewrgpqpdn.action", "target":"main"},
					{ id:24, pId:2, name:"进口局批区批段(南京历史)", "url":"${ctx}/address/b04r01addrview!viewrgpqpdnls.action", "target":"main"},
					{ id:25, pId:2, name:"反馈确认", "url":"${ctx}/address/b04r01addrview!viewfkqr.action", "target":"main"},
					
			{ id:3, pId:0, name:"全名址信息查询", open:false},
				{ id:31, pId:3, name:"地址配置查询", "url":"${ctx}/adr/c10r01!view.action", "target":"main"},
				{ id:32, pId:3, name:"快速综合查询", "url":"${ctx}/adr/c11r01!view.action", "target":"main"},
				{ id:34, pId:3, name:"收容地址查询(按出口统计)", "url":"${ctx}/address/b04r01addrview!viewcksr.action", "target":"main"},
				{ id:35, pId:3, name:"批区批段全数据查询", "url":"${ctx}/address/b04r01addrview!viewzshf.action", "target":"main"},
				{ id:36, pId:3, name:"邮件号码详情查询", "url":"${ctx}/disp/b07r01dispview!viewitemnoxqcx.action", "target":"main"},

			{ id:4, pId:0, name:"统计分析", open:false},
				
				{ id:41, pId:4, name:"全名址信息匹配率统计(按省)", "url":"${ctx}/disp/b07r01dispview!viewtjprb.action", "target":"main"},
				{ id:42, pId:4, name:"全名址信息匹配率统计(按市)", "url":"${ctx}/disp/b07r01dispview!viewtjcrb.action", "target":"main"},
				{ id:43, pId:4, name:"全名址信息匹配库统计(按省)", "url":"${ctx}/disp/b07r01dispview!viewtjpqb.action", "target":"main"},
				{ id:44, pId:4, name:"全名址信息匹配库统计(按市)", "url":"${ctx}/disp/b07r01dispview!viewtjcqb.action", "target":"main"},
				{ id:45, pId:4, name:"投递区维护统计", "url":"${ctx}/disp/b07r01dispview!viewtjtdqwhtj.action", "target":"main"},
				{ id:45, pId:4, name:"投递部维护统计", "url":"${ctx}/disp/b07r01dispview!viewtjtdbwhtj.action", "target":"main"},
				{ id:45, pId:4, name:"投递区部段信息统计", "url":"${ctx}/disp/b07r01dispview!viewtjdtqb.action", "target":"main"},
				{ id:46, pId:4, name:"投递区覆盖范围统计", "url":"${ctx}/disp/b07r01dispview!viewtjtdqqb.action", "target":"main"},
				{ id:47, pId:4, name:"批区批段维护率(原有按省)", "url":"${ctx}/disp/b07r01dispview!viewtjpwhrb.action", "target":"main"},
				{ id:47, pId:4, name:"批区批段维护率(南京按省)", "url":"${ctx}/disp/b07r01dispview!viewtjpwhrbdn.action", "target":"main"},
				{ id:47, pId:4, name:"批区批段维护量(南京按省)", "url":"${ctx}/disp/b07r01dispview!viewtjpwhrbopdn.action", "target":"main"},
				{ id:48, pId:4, name:"全名址投递区信息量统计", "url":"${ctx}/disp/b07r01dispview!viewtjdt.action", "target":"main"},
				
				
			{ id:5, pId:0, name:"权限管理", open:false},
				{ id:53, pId:5, name:"用户管理", "url":"${ctx}/pmn/a03r01!view.action", "target":"main"},
				{ id:53, pId:5, name:"补录用户管理", "url":"${ctx}/pmn/a05r01!view.action", "target":"main"},
				{ id:53, pId:5, name:"补录中心管理", "url":"${ctx}/pmn/a06r01!view.action", "target":"main"},
				{ id:53, pId:5, name:"补录中心查询", "url":"${ctx}/pmn/a07r01!view.action", "target":"main"},
				{ id:54, pId:5, name:"我的信息", "url":"${ctx}/pmn/a04r01!view.action", "target":"main"},
				{ id:55, pId:5, name:"修改密码", "url":"${ctx}/pmn/a04r01!editPasswd.action", "target":"main"},

			{ id:6, pId:0, name:"标准特快核心范围维护", open:false},
				{ id:61, pId:6, name:"频次配置(总部)", open:false},
					{ id:611, pId:61, name:"频次配置(总部)", "url":"${ctx}/clfw/b09r01clfwview!viewclfwpc.action", "target":"main"},
				{ id:62, pId:6, name:"频次配置(省)", open:false},
					{ id:621, pId:62, name:"省处理中心频次配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwpcsheng.action", "target":"main"},
					{ id:622, pId:62, name:"市[揽/投]衔接频次配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwpcshi.action", "target":"main"},
					{ id:623, pId:62, name:"投递部频次配置审核", "url":"${ctx}/clfw/b09r01clfwview!viewclfwtdbsh.action", "target":"main"},
				{ id:63, pId:6, name:"频次配置(市)", open:false},
					{ id:631, pId:63, name:"投递部属性配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwtdbcq.action", "target":"main"},
					{ id:632, pId:63, name:"投递部频次配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwtdb.action", "target":"main"},
					{ id:633, pId:63, name:"支局频次配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwresorgpz.action", "target":"main"},
				{ id:64, pId:6, name:"频次配置查询", open:false},
					{ id:641, pId:64, name:"揽投部频次配置查询", "url":"${ctx}/clfw/b09r01clfwview!viewclfwtdbtj.action", "target":"main"},
					{ id:642, pId:64, name:"机构代码频次配置查询", "url":"${ctx}/clfw/b09r01clfwview!viewclfwjgdm.action", "target":"main"},
					{ id:643, pId:64, name:"地址数据查询", "url":"${ctx}/clfw/b09r01clfwview!viewclfwsjcx.action", "target":"main"},
				{ id:65, pId:6, name:"核心服务范围配置", open:false},
					{ id:651, pId:65, name:"核心服务范围配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwkffwpz.action", "target":"main"},
					{ id:652, pId:65, name:"核心服务范围查询", "url":"${ctx}/clfw/b09r01clfwview!viewclfwkffwcx.action", "target":"main"},

			{ id:10, pId:0, name:"经济快递核心范围维护", open:false},
				{ id:101, pId:10, name:"频次配置(总部)", open:false},
					{ id:1011, pId:101, name:"频次配置(总部)", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxzbpc.action", "target":"main"},
				{ id:102, pId:10, name:"频次配置(省)", open:false},
					{ id:1021, pId:102, name:"地市频次配置", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxsjspc.action", "target":"main"},
				{ id:103, pId:10, name:"频次配置(市)", open:false},
					{ id:1031, pId:103, name:"专业机构配置", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzypc.action", "target":"main"},
					{ id:1031, pId:103, name:"代理机构配置", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzjpc.action", "target":"main"},
				{ id:104, pId:10, name:"频次配置查询", open:false},
					{ id:1041, pId:104, name:"专业机构配置查询", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzypccx.action", "target":"main"},
					{ id:1042, pId:104, name:"代理机构配置查询", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzjpccx.action", "target":"main"},
				{ id:105, pId:10, name:"频次配置统计分析", open:false},
					{ id:1051, pId:105, name:"专业机构配置统计", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzypctj.action", "target":"main"},
					{ id:1052, pId:105, name:"代理机构配置统计", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzjpctj.action", "target":"main"},
					//{ id:1051, pId:105, name:"经济频次专业机构未配置明细", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzypcmx.action", "target":"main"},
				
			{ id:9, pId:0, name:"准班准点统计", open:false},
					{ id:91, pId:9, name:"准班准点填报", "url":"${ctx}/clfw/b09r01clfwview!viewclfwylxxbt.action", "target":"main"},
					{ id:92, pId:9, name:"准班准点统计(组开局)", "url":"${ctx}/clfw/b09r01clfwview!viewtjclfwylxxbtalc.action", "target":"main"},
					{ id:93, pId:9, name:"准班准点明细", "url":"${ctx}/clfw/b09r01clfwview!viewtjclfwylxxbt.action", "target":"main"},
					{ id:93, pId:9, name:"民航航班执行情况填报", "url":"${ctx}/clfw/b09r01clfwview!viewclfwhkwh.action", "target":"main"},
					{ id:93, pId:9, name:"民航航班执行情况统计", "url":"${ctx}/clfw/b09r01clfwview!viewtjclfwhkwhal.action", "target":"main"},
					{ id:93, pId:9, name:"民航航班执行情况明细", "url":"${ctx}/clfw/b09r01clfwview!viewtjclfwhbwhalmx.action", "target":"main"},

					{ id:93, pId:9, name:"民航路单填报数据", "url":"${ctx}/clfw/b09r01clfwview!viewclfwhkldwh.action", "target":"main"},
					{ id:93, pId:9, name:"民航路单填报统计", "url":"${ctx}/clfw/b09r01clfwview!viewclfwhkldwhtj.action", "target":"main"},
					{ id:93, pId:9, name:"民航路单填报明细", "url":"${ctx}/clfw/b09r01clfwview!viewclfwhkldwhmx.action", "target":"main"},
			{ id:7, pId:0, name:"公告信息", open:false}

			
			
		</c:if>
		
		<c:if test="${EMS_USER.rulLevel==5}">
		
			{ id:1, pId:0, name:"全名址信息维护", open:false},
				{ id:11, pId:1, name:"基础信息维护", open:false},
					{ id:111, pId:11, name:"投递区信息维护", "url":"${ctx}/wh/b01r03!view.action", "target":"main"},
					{ id:112, pId:11, name:"经转维护", "url":"${ctx}/disp/b07r01dispview!viewdisps.action", "target":"main"},
					{ id:113, pId:11, name:"行政批区", "url":"${ctx}/disp/b07r01dispview!viewwhtrans.action", "target":"main"},
					{ id:114, pId:11, name:"行政区域", "url":"${ctx}/disp/b07r01dispview!viewdistrict.action", "target":"main"},
				{ id:12, pId:1, name:"地址信息维护", open:false},
					{ id:121, pId:12, name:"地址检核", "url":"${ctx}/address/b04r01addrview!viewtwstr.action", "target":"main"},

				{ id:14, pId:1, name:"差异数据维护", open:false},
					{ id:140, pId:14, name:"地址重复号段", "url":"${ctx}/adr/c08r01!viewCheckStreet.action", "target":"main"},
					{ id:141, pId:14, name:"门牌号段缺失", "url":"${ctx}/address/b04r01addrview!viewstreetsdium.action", "target":"main"},
					{ id:142, pId:14, name:"存疑街道名称信息", "url":"${ctx}/address/b04r01addrview!viewstreetsqfhdb.action", "target":"main"},
					{ id:143, pId:14, name:"存疑机构名称信息", "url":"${ctx}/address/b04r01addrview!vieworganluan.action", "target":"main"},
					{ id:144, pId:14, name:"存疑小区名称信息", "url":"${ctx}/address/b04r01addrview!viewbldgrsdnsluan.action", "target":"main"},
					{ id:145, pId:14, name:"地址缺失机构信息", "url":"${ctx}/address/b04r01addrview!vieworgandius.action", "target":"main"},
					{ id:146, pId:14, name:"地址缺失小区信息", "url":"${ctx}/address/b04r01addrview!viewbldgrsdnsdius.action", "target":"main"},
				
					
			{ id:2, pId:0, name:"进口局批区批段", open:false},
					
			{ id:21, pId:2, name:"进口局批区批段(当日)", "url":"${ctx}/address/b04r01addrview!viewrgpqpd.action", "target":"main"},
			{ id:22, pId:2, name:"进口局批区批段(历史)", "url":"${ctx}/address/b04r01addrview!viewrgpqpdj.action", "target":"main"},
			
			{ id:3, pId:0, name:"全名址信息查询", open:false},
				{ id:31, pId:3, name:"地址配置查询", "url":"${ctx}/adr/c10r01!view.action", "target":"main"},
				{ id:32, pId:3, name:"快速综合查询", "url":"${ctx}/adr/c11r01!view.action", "target":"main"},
				{ id:34, pId:3, name:"收容地址查询(按出口统计)", "url":"${ctx}/address/b04r01addrview!viewcksr.action", "target":"main"},
				{ id:35, pId:3, name:"批区批段全数据查询", "url":"${ctx}/address/b04r01addrview!viewzshf.action", "target":"main"},

			{ id:4, pId:0, name:"统计分析", open:false},
				
				{ id:42, pId:4, name:"全名址信息匹配率统计(按市)", "url":"${ctx}/disp/b07r01dispview!viewtjcrb.action", "target":"main"},
				{ id:44, pId:4, name:"全名址信息匹配库统计(按市)", "url":"${ctx}/disp/b07r01dispview!viewtjcqb.action", "target":"main"},
				{ id:45, pId:4, name:"投递区部段信息统计", "url":"${ctx}/disp/b07r01dispview!viewtjdtqb.action", "target":"main"},
				{ id:46, pId:4, name:"投递区覆盖范围统计", "url":"${ctx}/disp/b07r01dispview!viewtjtdqqb.action", "target":"main"},
				{ id:47, pId:4, name:"批区批段维护率(按省)", "url":"${ctx}/disp/b07r01dispview!viewtjpwhrb.action", "target":"main"},
				
			{ id:5, pId:0, name:"权限管理", open:false},
				{ id:53, pId:5, name:"用户管理", "url":"${ctx}/pmn/a03r01!view.action", "target":"main"},
				{ id:53, pId:5, name:"补录用户管理", "url":"${ctx}/pmn/a05r01!view.action", "target":"main"},
				{ id:53, pId:5, name:"补录中心管理", "url":"${ctx}/pmn/a06r01!view.action", "target":"main"},
				{ id:53, pId:5, name:"补录中心查询", "url":"${ctx}/pmn/a07r01!view.action", "target":"main"},
				{ id:54, pId:5, name:"我的信息", "url":"${ctx}/pmn/a04r01!view.action", "target":"main"},
				{ id:55, pId:5, name:"修改密码", "url":"${ctx}/pmn/a04r01!editPasswd.action", "target":"main"},

				{ id:6, pId:0, name:"核心服务", open:false},
				{ id:61, pId:6, name:"频次配置(总部)", open:false},
					{ id:611, pId:61, name:"频次配置(总部)", "url":"${ctx}/clfw/b09r01clfwview!viewclfwpc.action", "target":"main"},
				{ id:62, pId:6, name:"频次配置(省)", open:false},
					{ id:622, pId:62, name:"省处理中心频次配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwpcsheng.action", "target":"main"},
					{ id:623, pId:62, name:"市[揽/投]衔接频次配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwpcshi.action", "target":"main"},
				{ id:63, pId:6, name:"频次配置(市)", open:false},
					{ id:632, pId:63, name:"投递部属性配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwtdbcq.action", "target":"main"},
					{ id:633, pId:63, name:"投递部频次配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwtdb.action", "target":"main"},
				{ id:64, pId:6, name:"信息查询", open:false},
					{ id:641, pId:64, name:"服务范围查询", "url":"${ctx}/clfw/b09r01clfwview!viewclfwtdbtj.action", "target":"main"},
					{ id:642, pId:64, name:"地址数据查询", "url":"${ctx}/clfw/b09r01clfwview!viewclfwsjcx.action", "target":"main"},
				{ id:65, pId:6, name:"核心服务范围配置", open:false},
					{ id:651, pId:65, name:"核心服务范围配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwkffwpz.action", "target":"main"},
					{ id:652, pId:65, name:"核心服务范围查询", "url":"${ctx}/clfw/b09r01clfwview!viewclfwkffwcx.action", "target":"main"},

					{ id:10, pId:0, name:"经济快递核心范围维护", open:false},
					{ id:102, pId:10, name:"频次配置(省)", open:false},
						{ id:1021, pId:102, name:"地市频次配置", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxsjspc.action", "target":"main"},
					{ id:103, pId:10, name:"频次配置(市)", open:false},
						{ id:1031, pId:103, name:"专业机构配置", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzypc.action", "target":"main"},
						{ id:1031, pId:103, name:"代理机构配置", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzjpc.action", "target":"main"},
					{ id:104, pId:10, name:"频次配置查询", open:false},
						{ id:1041, pId:104, name:"专业机构配置查询", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzypccx.action", "target":"main"},
						{ id:1042, pId:104, name:"代理机构配置查询", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzjpccx.action", "target":"main"},
					{ id:105, pId:10, name:"频次配置统计分析", open:false},
						{ id:1051, pId:105, name:"专业机构配置统计", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzypctj.action", "target":"main"},
						{ id:1052, pId:105, name:"代理机构配置统计", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzjpctj.action", "target":"main"},
					
					{ id:9, pId:0, name:"准班准点统计", open:false},
					{ id:91, pId:9, name:"准班准点填报", "url":"${ctx}/clfw/b09r01clfwview!viewclfwylxxbt.action", "target":"main"},
					{ id:92, pId:9, name:"准班准点统计(组开局)", "url":"${ctx}/clfw/b09r01clfwview!viewtjclfwylxxbtalc.action", "target":"main"},
					{ id:93, pId:9, name:"准班准点明细", "url":"${ctx}/clfw/b09r01clfwview!viewtjclfwylxxbt.action", "target":"main"},
					{ id:93, pId:9, name:"民航航班执行情况填报", "url":"${ctx}/clfw/b09r01clfwview!viewclfwhkwh.action", "target":"main"},
					{ id:93, pId:9, name:"民航航班执行情况统计", "url":"${ctx}/clfw/b09r01clfwview!viewtjclfwhkwhal.action", "target":"main"},
					
			{ id:7, pId:0, name:"公告信息", open:false}
			
		</c:if>
		
		<c:if test="${EMS_USER.rulLevel==10}">
				{ id:1, pId:0, name:"全名址信息维护", open:false},
				{ id:11, pId:1, name:"基础信息维护", open:false},
					{ id:111, pId:11, name:"投递区信息维护", "url":"${ctx}/wh/b01r03!view.action", "target":"main"},
					{ id:112, pId:11, name:"经转维护", "url":"${ctx}/disp/b07r01dispview!viewdisps.action", "target":"main"},
					{ id:113, pId:11, name:"行政批区", "url":"${ctx}/disp/b07r01dispview!viewwhtrans.action", "target":"main"},
					{ id:114, pId:11, name:"行政区域", "url":"${ctx}/disp/b07r01dispview!viewdistrict.action", "target":"main"},
				{ id:12, pId:1, name:"地址信息维护", open:false},
				{ id:121, pId:12, name:"地址维护", "url":"${ctx}/address/b04r01addrview!viewstreets.action", "target":"main"},
					{ id:121, pId:12, name:"地址检核", "url":"${ctx}/address/b04r01addrview!viewtwstr.action", "target":"main"},
		
				{ id:14, pId:1, name:"差异数据维护", open:false},
					{ id:140, pId:14, name:"地址重复号段", "url":"${ctx}/adr/c08r01!viewCheckStreet.action", "target":"main"},
					{ id:141, pId:14, name:"门牌号段缺失", "url":"${ctx}/address/b04r01addrview!viewstreetsdium.action", "target":"main"},
					{ id:142, pId:14, name:"存疑街道名称信息", "url":"${ctx}/address/b04r01addrview!viewstreetsqfhdb.action", "target":"main"},
					{ id:143, pId:14, name:"存疑机构名称信息", "url":"${ctx}/address/b04r01addrview!vieworganluan.action", "target":"main"},
					{ id:144, pId:14, name:"存疑小区名称信息", "url":"${ctx}/address/b04r01addrview!viewbldgrsdnsluan.action", "target":"main"},
					{ id:145, pId:14, name:"地址缺失机构信息", "url":"${ctx}/address/b04r01addrview!vieworgandius.action", "target":"main"},
					{ id:146, pId:14, name:"地址缺失小区信息", "url":"${ctx}/address/b04r01addrview!viewbldgrsdnsdius.action", "target":"main"},
				
					
			{ id:2, pId:0, name:"进口局批区批段", open:false},
					
			{ id:21, pId:2, name:"进口局批区批段(当日)", "url":"${ctx}/address/b04r01addrview!viewrgpqpd.action", "target":"main"},
			{ id:22, pId:2, name:"进口局批区批段(历史)", "url":"${ctx}/address/b04r01addrview!viewrgpqpdj.action", "target":"main"},
			
			{ id:3, pId:0, name:"全名址信息查询", open:false},
				{ id:31, pId:3, name:"地址配置查询", "url":"${ctx}/adr/c10r01!view.action", "target":"main"},
				{ id:32, pId:3, name:"快速综合查询", "url":"${ctx}/adr/c11r01!view.action", "target":"main"},
				{ id:34, pId:3, name:"收容地址查询(按出口统计)", "url":"${ctx}/address/b04r01addrview!viewcksr.action", "target":"main"},
				{ id:35, pId:3, name:"批区批段全数据查询", "url":"${ctx}/address/b04r01addrview!viewzshf.action", "target":"main"},
		
			{ id:4, pId:0, name:"统计分析", open:false},
				
				{ id:42, pId:4, name:"全名址信息匹配率统计(按市)", "url":"${ctx}/disp/b07r01dispview!viewtjcrb.action", "target":"main"},
				{ id:44, pId:4, name:"全名址信息匹配库统计(按市)", "url":"${ctx}/disp/b07r01dispview!viewtjcqb.action", "target":"main"},
				{ id:45, pId:4, name:"投递区部段信息统计", "url":"${ctx}/disp/b07r01dispview!viewtjdtqb.action", "target":"main"},
				{ id:46, pId:4, name:"投递区覆盖范围统计", "url":"${ctx}/disp/b07r01dispview!viewtjtdqqb.action", "target":"main"},
				{ id:47, pId:4, name:"批区批段维护率(按省)", "url":"${ctx}/disp/b07r01dispview!viewtjpwhrb.action", "target":"main"},
				
			{ id:5, pId:0, name:"权限管理", open:false},
				{ id:53, pId:5, name:"用户管理", "url":"${ctx}/pmn/a03r01!view.action", "target":"main"},
				{ id:53, pId:5, name:"补录用户管理", "url":"${ctx}/pmn/a05r01!view.action", "target":"main"},
				{ id:53, pId:5, name:"补录中心管理", "url":"${ctx}/pmn/a06r01!view.action", "target":"main"},
				{ id:53, pId:5, name:"补录中心查询", "url":"${ctx}/pmn/a07r01!view.action", "target":"main"},
				{ id:54, pId:5, name:"我的信息", "url":"${ctx}/pmn/a04r01!view.action", "target":"main"},
				{ id:55, pId:5, name:"修改密码", "url":"${ctx}/pmn/a04r01!editPasswd.action", "target":"main"},
		
				{ id:6, pId:0, name:"核心服务", open:false},
				{ id:61, pId:6, name:"频次配置(总部)", open:false},
					{ id:611, pId:61, name:"频次配置(总部)", "url":"${ctx}/clfw/b09r01clfwview!viewclfwpc.action", "target":"main"},
				{ id:62, pId:6, name:"频次配置(省)", open:false},
					{ id:622, pId:62, name:"省处理中心频次配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwpcsheng.action", "target":"main"},
					{ id:623, pId:62, name:"市[揽/投]衔接频次配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwpcshi.action", "target":"main"},
				{ id:63, pId:6, name:"频次配置(市)", open:false},
					{ id:632, pId:63, name:"投递部属性配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwtdbcq.action", "target":"main"},
					{ id:633, pId:63, name:"投递部频次配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwtdb.action", "target":"main"},
				{ id:64, pId:6, name:"信息查询", open:false},
					{ id:641, pId:64, name:"服务范围查询", "url":"${ctx}/clfw/b09r01clfwview!viewclfwtdbtj.action", "target":"main"},
					{ id:642, pId:64, name:"地址数据查询", "url":"${ctx}/clfw/b09r01clfwview!viewclfwsjcx.action", "target":"main"},
				{ id:65, pId:6, name:"核心服务范围配置", open:false},
					{ id:651, pId:65, name:"核心服务范围配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwkffwpz.action", "target":"main"},
					{ id:652, pId:65, name:"核心服务范围查询", "url":"${ctx}/clfw/b09r01clfwview!viewclfwkffwcx.action", "target":"main"},

					{ id:10, pId:0, name:"经济快递核心范围维护", open:false},

				<c:if test="${EMS_USER.usCityOffice=='110000'}">
					{ id:102, pId:10, name:"频次配置(省)", open:false},
					{ id:1021, pId:102, name:"地市频次配置", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxsjspc.action", "target":"main"},
				</c:if>
				<c:if test="${EMS_USER.usCityOffice=='120000'}">
					{ id:102, pId:10, name:"频次配置(省)", open:false},
					{ id:1021, pId:102, name:"地市频次配置", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxsjspc.action", "target":"main"},
				</c:if>
				<c:if test="${EMS_USER.usCityOffice=='310000'}">
					{ id:102, pId:10, name:"频次配置(省)", open:false},
					{ id:1021, pId:102, name:"地市频次配置", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxsjspc.action", "target":"main"},
				</c:if>
				<c:if test="${EMS_USER.usCityOffice=='500000'}">
					{ id:102, pId:10, name:"频次配置(省)", open:false},
					{ id:1021, pId:102, name:"地市频次配置", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxsjspc.action", "target":"main"},
				</c:if>

					
					{ id:103, pId:10, name:"频次配置(市)", open:false},
						{ id:1031, pId:103, name:"专业机构配置", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzypc.action", "target":"main"},
						{ id:1031, pId:103, name:"代理机构配置", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzjpc.action", "target":"main"},
					{ id:104, pId:10, name:"频次配置查询", open:false},
						{ id:1041, pId:104, name:"专业机构配置查询", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzypccx.action", "target":"main"},
						{ id:1042, pId:104, name:"代理机构配置查询", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzjpccx.action", "target":"main"},
					{ id:105, pId:10, name:"频次配置统计分析", open:false},
						{ id:1051, pId:105, name:"专业机构配置统计", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzypctj.action", "target":"main"},
						{ id:1052, pId:105, name:"代理机构配置统计", "url":"${ctx}/jjsx/b10r01jjsxview!viewjjsxqxzjpctj.action", "target":"main"},
				

					{ id:9, pId:0, name:"准班准点统计", open:false},
					{ id:91, pId:9, name:"准班准点填报", "url":"${ctx}/clfw/b09r01clfwview!viewclfwylxxbt.action", "target":"main"},
					{ id:92, pId:9, name:"准班准点统计(组开局)", "url":"${ctx}/clfw/b09r01clfwview!viewtjclfwylxxbtalc.action", "target":"main"},
					{ id:93, pId:9, name:"准班准点明细", "url":"${ctx}/clfw/b09r01clfwview!viewtjclfwylxxbt.action", "target":"main"},
					{ id:93, pId:9, name:"民航航班执行情况填报", "url":"${ctx}/clfw/b09r01clfwview!viewclfwhkwh.action", "target":"main"},
					{ id:93, pId:9, name:"民航航班执行情况统计", "url":"${ctx}/clfw/b09r01clfwview!viewtjclfwhkwhal.action", "target":"main"},
					
			{ id:7, pId:0, name:"公告信息", open:false}
		</c:if>

		<c:if test="${EMS_USER.rulLevel==20}">
		{ id:1, pId:0, name:"全名址信息维护", open:false},
			{ id:11, pId:1, name:"基础信息维护", open:false},
				{ id:114, pId:11, name:"投递段信息维护", "url":"${ctx}/wh/b03r01!view.action", "target":"main"},
				
			{ id:12, pId:1, name:"地址信息维护", open:false},
			
				{ id:121, pId:12, name:"地址维护", "url":"${ctx}/address/b04r01addrview!viewstreets.action", "target":"main"},
				{ id:124, pId:12, name:"地址检核", "url":"${ctx}/address/b04r01addrview!viewtwstr.action", "target":"main"},
				
			{ id:13, pId:1, name:"投递段配置", open:false},
				{ id:131, pId:13, name:"本部地址配置", "url":"${ctx}/adr/c08r01!view.action", "target":"main"},
				{ id:132, pId:13, name:"本市地址配置", "url":"${ctx}/adr/c04r01!view.action", "target":"main"},
				{ id:134, pId:13, name:"无门牌小区配置", "url":"${ctx}/adr/c13r01!viewDepartment.action", "target":"main"},
				{ id:136, pId:13, name:"批段地址审核查询", "url":"${ctx}/adr/c09r03!view.action", "target":"main"},

			{ id:14, pId:1, name:"差异数据维护", open:false},
				{ id:140, pId:14, name:"地址重复号段", "url":"${ctx}/adr/c08r01!viewCheckStreet.action", "target":"main"},
				{ id:141, pId:14, name:"门牌号段缺失", "url":"${ctx}/address/b04r01addrview!viewstreetsdium.action", "target":"main"},
				{ id:142, pId:14, name:"存疑街道名称信息", "url":"${ctx}/address/b04r01addrview!viewstreetsqfhdb.action", "target":"main"},
				{ id:143, pId:14, name:"存疑机构名称信息", "url":"${ctx}/address/b04r01addrview!vieworganluan.action", "target":"main"},
				{ id:144, pId:14, name:"存疑小区名称信息", "url":"${ctx}/address/b04r01addrview!viewbldgrsdnsluan.action", "target":"main"},
				{ id:145, pId:14, name:"地址缺失机构信息", "url":"${ctx}/address/b04r01addrview!vieworgandius.action", "target":"main"},
				{ id:146, pId:14, name:"地址缺失小区信息", "url":"${ctx}/address/b04r01addrview!viewbldgrsdnsdius.action", "target":"main"},
				
		{ id:2, pId:0, name:"进口局批区批段", open:false},
				
		{ id:21, pId:2, name:"进口局批区批段(当日)", "url":"${ctx}/address/b04r01addrview!viewrgpqpd.action", "target":"main"},
		{ id:22, pId:2, name:"进口局批区批段(历史)", "url":"${ctx}/address/b04r01addrview!viewrgpqpdj.action", "target":"main"},
				
		{ id:3, pId:0, name:"全名址信息查询", open:false},
			//{ id:31, pId:3, name:"地址配置查询", "url":"${ctx}/adr/c10r01!view.action", "target":"main"},
			{ id:32, pId:3, name:"快速综合查询", "url":"${ctx}/adr/c11r01!view.action", "target":"main"},
			{ id:35, pId:3, name:"批区批段全数据查询", "url":"${ctx}/address/b04r01addrview!viewzshf.action", "target":"main"},
			
		{ id:5, pId:0, name:"权限管理", open:false},
		
			{ id:53, pId:5, name:"用户管理", "url":"${ctx}/pmn/a03r01!view.action", "target":"main"},
			{ id:54, pId:5, name:"我的信息", "url":"${ctx}/pmn/a04r01!view.action", "target":"main"},
			{ id:55, pId:5, name:"修改密码", "url":"${ctx}/pmn/a04r01!editPasswd.action", "target":"main"},

			{ id:6, pId:0, name:"核心服务", open:false},
			{ id:61, pId:6, name:"频次配置(总部)", open:false},
				{ id:611, pId:61, name:"频次配置(总部)", "url":"${ctx}/clfw/b09r01clfwview!viewclfwpc.action", "target":"main"},
			{ id:62, pId:6, name:"频次配置(省)", open:false},
				{ id:622, pId:62, name:"省处理中心频次配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwpcsheng.action", "target":"main"},
				{ id:623, pId:62, name:"市[揽/投]衔接频次配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwpcshi.action", "target":"main"},
			{ id:63, pId:6, name:"频次配置(市)", open:false},
				{ id:632, pId:63, name:"投递部属性配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwtdbcq.action", "target":"main"},
				{ id:633, pId:63, name:"投递部频次配置", "url":"${ctx}/clfw/b09r01clfwview!viewclfwtdb.action", "target":"main"},
			{ id:64, pId:6, name:"信息查询", open:false},
				{ id:641, pId:64, name:"服务范围查询", "url":"${ctx}/clfw/b09r01clfwview!viewclfwtdbtj.action", "target":"main"},
				{ id:642, pId:64, name:"地址数据查询", "url":"${ctx}/clfw/b09r01clfwview!viewclfwsjcx.action", "target":"main"},

		{ id:7, pId:0, name:"公告信息", open:false}
		</c:if>
		
		<c:if test="${EMS_USER.rulLevel==60 && EMS_USER.isManager == 1}">
		{ id:1, pId:0, name:"权限管理", open:true},
		{ id:11, pId:5, name:"补录用户管理", "url":"${ctx}/pmn/a05r01!viewnormal.action", "target":"main"}	
		</c:if>
		
	];


	$(document).ready(function(){
		
		setting.expandSpeed = ($.browser.msie && parseInt($.browser.version)<=6)?"":"fast";
		
		zTree = $("#tree").zTree(setting, zNodes);
		var nodes = zTree.getNodes();
		
		//zTree.selectNode(nodes[2].nodes[0].nodes[0]);
	
		demoIframe = $("#main");

		if('${EMS_USER.usLoginId}'.substr(0,5)=='sjtb_'){
			window.location.href="left_sjtb.jsp";
		}

	});

	function loadReady() {
		var h = demoIframe.contents().find("body").height();
		if (h < 600) h = 600;
		demoIframe.height(h);
	}

	function click(){ 
		if(event.button==2){ 
			//alert('欢迎您');
		} 
	} 

	document.onmousedown=click;
			
			
  </script>
 </head>

<body>
<table border=0 align=left>
	<tr id='tr_tree'>
		<TD width=230px align=left valign=top style="BORDER-RIGHT: #999999 1px dashed">
			<ul id="tree" class="tree" style="width:230px; overflow:auto;"></ul>
		</TD>
	</tr>
</table>

 </body>
</html>
