<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<input type="button" value="查看服务器" onclick="creatXHR();"/>
<script type="text/javascript">
	function creatXHR(){
		var xmlhttprequest;
		if(window.XMLHttpRequest){
			xmlhttprequest=new XMLHttpRequest();
		}else{
			xmlhttprequest=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttprequest.open("get","http://vs.5eplay.com/?mod=vs&action=ajax&op=vslist&vsquery=game:1&page=0&_=1363915104445",true);
		xmlhttprequest.send(null);
		//if(xmlhttprequest.status>=200 && xmlhttprequest.status<300){
			
		//}
		alert(xmlhttprequest.responseText);
	}
	//EventUtil.addHandler("",);
	
</script>
</body>
</html>