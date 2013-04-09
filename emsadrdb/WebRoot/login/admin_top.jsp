
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<title>管理页面</title>
<script language=JavaScript>
function logout(){
	if (confirm("您确定要退出吗？"))
	top.location = "${ctx}/index.jsp";
	return false;
}
</script>
<script language=JavaScript1.2>
function showsubmenu(sid) {
	var whichEl = eval("submenu" + sid);
	var menuTitle = eval("menuTitle" + sid);
	if (whichEl.style.display == "none"){
		eval("submenu" + sid + ".style.display=\"\";");
	}else{
		eval("submenu" + sid + ".style.display=\"none\";");
	}
}
</script>
<!-- <meta http-equiv="refresh" content="60"> -->
<script language=JavaScript1.2>
function showsubmenu(sid) {
	var whichEl = eval("submenu" + sid);
	var menuTitle = eval("menuTitle" + sid);
	if (whichEl.style.display == "none"){
		eval("submenu" + sid + ".style.display=\"\";");
	}else{
		eval("submenu" + sid + ".style.display=\"none\";");
	}
}
</script>
<script language="javascript">
<!--
var displayBar=true;
function switchBar(obj){
	if (displayBar)
	{
		var f=parent.document.getElementById("frame");
		f.cols="0,*";
		//parent.frame.cols="0,*";
		displayBar=false;
		obj.title="打开左边管理菜单";
	}
	else{
		var f=parent.document.getElementById("frame");
		f.cols="195,*";
		//parent.frame.cols="195,*";
		displayBar=true;
		obj.title="关闭左边管理菜单";
	}
}
//-->
</script>
<base target="main">
<link href="${ctx}/images/skin.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0">
<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" class="admin_topbg">
  <tr>
    <td width="900" height="64"><img src="${ctx }/images/logo.gif"></td>
    <td width="100%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
	  	<td width="50%">&nbsp;</td>
        <td width="100%" height="38" valign="bottom"><font style="font-size:12px">管理员：<b></b> 您好,感谢登陆使用！</font></td>
        <td width="22%" valign="bottom"><a href="#" target="_self" onClick="logout();"><img src="${ctx}/images/out.gif" alt="安全退出" width="46" height="20" border="0"></a></td>
      </tr>
      <tr>
        <td height="19" colspan="3">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
