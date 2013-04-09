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
	
	zNodes  =[
	  		<c:if test="${EMS_USER.rulLevel==5}">
			{ id:1, pId:0, name:"权限管理", open:false},
			{ id:11, pId:1, name:"我的信息", "url":"${ctx}/pmn/a04r01!view.action", "target":"main"},
			{ id:12, pId:1, name:"修改密码", "url":"${ctx}/pmn/a04r01!editPasswd.action", "target":"main"},
			{ id:8, pId:0, name:"数据填报", open:false},
			{ id:81, pId:8, name:"准班准点统计", open:false},
				{ id:811, pId:81, name:"准班准点填报", "url":"${ctx}/clfw/b09r01clfwview!viewclfwylxxbt.action", "target":"main"},
				{ id:812, pId:81, name:"准班准点统计(组开局)", "url":"${ctx}/clfw/b09r01clfwview!viewtjclfwylxxbtalc.action", "target":"main"},
				{ id:813, pId:81, name:"准班准点明细", "url":"${ctx}/clfw/b09r01clfwview!viewtjclfwylxxbt.action", "target":"main"},
			{ id:82, pId:8, name:"民航航班执行情况统计", open:false},
				{ id:821, pId:82, name:"民航航班执行情况填报", "url":"${ctx}/clfw/b09r01clfwview!viewclfwhkwh.action", "target":"main"},
				{ id:822, pId:82, name:"民航航班执行情况统计", "url":"${ctx}/clfw/b09r01clfwview!viewtjclfwhkwhal.action", "target":"main"}
			</c:if>
			<c:if test="${EMS_USER.rulLevel==10}">
			{ id:1, pId:0, name:"权限管理", open:false},
			{ id:11, pId:1, name:"我的信息", "url":"${ctx}/pmn/a04r01!view.action", "target":"main"},
			{ id:12, pId:1, name:"修改密码", "url":"${ctx}/pmn/a04r01!editPasswd.action", "target":"main"},
			{ id:8, pId:0, name:"数据填报", open:false},
			{ id:81, pId:8, name:"准班准点统计", open:false},
				{ id:811, pId:81, name:"准班准点填报", "url":"${ctx}/clfw/b09r01clfwview!viewclfwylxxbt.action", "target":"main"},
				{ id:812, pId:81, name:"准班准点统计(组开局)", "url":"${ctx}/clfw/b09r01clfwview!viewtjclfwylxxbtalc.action", "target":"main"},
				{ id:813, pId:81, name:"准班准点明细", "url":"${ctx}/clfw/b09r01clfwview!viewtjclfwylxxbt.action", "target":"main"},
			{ id:82, pId:8, name:"民航航班执行情况统计", open:false},
				{ id:821, pId:82, name:"民航航班执行情况填报", "url":"${ctx}/clfw/b09r01clfwview!viewclfwhkwh.action", "target":"main"},
				{ id:822, pId:82, name:"民航航班执行情况统计", "url":"${ctx}/clfw/b09r01clfwview!viewtjclfwhkwhal.action", "target":"main"}
			</c:if>
		 		
		 	];



	$(document).ready(function(){
		
		setting.expandSpeed = ($.browser.msie && parseInt($.browser.version)<=6)?"":"fast";
		
		zTree = $("#tree").zTree(setting, zNodes);
		var nodes = zTree.getNodes();
		
		//zTree.selectNode(nodes[2].nodes[0].nodes[0]);

		demoIframe = $("#main");

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
