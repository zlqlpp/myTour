
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
<title>管理页面</title>
<script language="javascript">
<!--
var displayBar=true;
function switchBar(obj){
	if (displayBar)
	{
		var f=parent.document.getElementById("frame");
		f.cols="0,12,*";
		//parent.frame.cols="0,*";
		displayBar=false;
		obj.title="打开左边管理菜单";
	}
	else{
		var f=parent.document.getElementById("frame");
		f.cols="195,12,*";
		//parent.frame.cols="195,*";
		displayBar=true;
		obj.title="关闭左边管理菜单";
	}
}
//-->
</script>
</head>
<body leftmargin="0" topmargin="0">
<table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
<tr>
<td align="center" valign="top">
<img onClick="switchBar(this)" alt="关闭左边管理菜单" src="${ctx}/images/on-of.gif" width="12" height="14" border="0" />
</td>
</tr>
</table>
</body>
</html>
