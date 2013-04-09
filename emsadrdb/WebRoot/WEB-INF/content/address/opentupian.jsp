<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/widgets/jquery/jquery.jsp"%>
<%@ include file="/widgets/jquery/autocomplete/autocomplete.jsp"%>
<%@ include file="/widgets/jquery/blockui/blockUI.jsp"%>
<%@ include file="/widgets/jquery/impromptu/impromptu.jsp"%>
<%@ include file="/scripts/address/jsmd5.jsp"%>
<%@ include file="/scripts/address/addr.jsp"%>
<html>

	<head>
		<title>图片显示</title>
		<link href="${ctx}/images/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
		<style type="text/css">  
			div { 
			background:no-repeat scroll;
			height:800px;
			width:1600px;
			overflow:auto;
			} 
		</style> 
		<script type="text/javascript">
			$(document).ready(function(){
				var titemno = '${SEQID}';
				var urlsrcen1 = "aHR0cDovLzEwLjMuMzIuNjYvZW1zX3BtL3NlcnZpY2UvU2NhbkltYWdlU2VhcmNoQWN0aW9uLmRvP21ldGhvZD1zaG93SW1hZ2UmYmlsbE5vPQ==";
				var urlsrcen3 = "JmltZ1R5cGU9aW1nU3JjJm5hbWU9YWRyZGImcGFzc3dvcmQ9ODNFRTY3OEQ3N0FFQUQ0NUE2RTUwM0YzOTAxNEI3RjY=";
				var urlsrcenall = utf8to16(base64decode(urlsrcen1)) + titemno + utf8to16(base64decode(urlsrcen3));
				//if(IsExist(urlsrcenall)){
					//$(".labimage").hide();
					$(".divimage").show();
					$(".divimage").css('background-image',"url('" + urlsrcenall + "')");
					$(".labimage").text("详情单图像加载完毕(空白表示没有查询到详情单图像信息)");
				/*
				}else{
					$(".labimage").show();
					$(".labimage").text("没有查询到详情单图像");
					$(".divimage").hide();
				}
				$(".labimagets").hide();
				*/
			})
			/*
			function IsExist(url) 
			{ 
				x = new ActiveXObject("Microsoft.XMLHTTP");
				x.open("HEAD",url,false);
				x.send();
				if(x.responseBody==null){
					return false;
				}else{
					return true;
				}
			} 		
			*/	
		</script>
	</head>
	<body>
			<label class="labimagets" style="color:red"  style="display:none">因为使用"Microsoft.XMLHTTP"组件,判断详情单图像是否存在,所以查看详情单图像的时候出现安全提示，选择【是】即可</label>
			<label class="labimage" style="color:red">详情单图像加载中...</label>
			<div class="divimage" style="display:none">&nbsp;</div>
	</body>
</html>
