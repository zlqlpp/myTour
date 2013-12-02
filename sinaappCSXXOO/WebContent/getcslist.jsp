<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>
<div id="myDiv">原数据</div>
<input type="button" value="更新数据" onclick="getData()" />
<script type="text/javascript">
$.ajax({
	  //url: 'http://vs.5eplay.com/?mod=vs&action=ajax&op=vslist&vsquery=game:1&page=0&_=1363915104445',
	 url:'http://www.baidu.com',
			  success: function(data) {
	   // $('.result').html(data);
	    alert('Load was performed.');
	  }
	});
</script>
</body>
</html>