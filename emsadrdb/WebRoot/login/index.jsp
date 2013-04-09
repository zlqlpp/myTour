<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<title>管理中心</title>
</head>
<frameset rows="64,*"  frameborder="NO" border="0" framespacing="0">
	<frame src="admin_top.jsp" noresize="noresize"  name="topFrame" scrolling="no" marginwidth="0" marginheight="0"/>
  	<frameset cols="195,12,*" id="frame">
	<frame src="left.jsp" name="leftFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="yes"/>
	<frame src="mid.jsp" name="midFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="no"/>
	<frame src="${ctx}/address/b04r01addrview!viewgonggao.action" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" bordercolor="#9FF8CB" />
  </frameset>
</frameset><noframes></noframes>
</html>
