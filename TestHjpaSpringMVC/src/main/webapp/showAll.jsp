<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/demo/demo.css">
<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>

	<script type="text/javascript">
		$(function() {
			var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
		})
	</script>
</head>
<body style="text-algin: center">

	<h2>维权信息详情页</h2>
	<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
	</div>
	<div style="margin: 10px 0;"></div>
	<table id="dg" title="列表 "
		style="width:1300px; height: 350px"
		data-options="rownumbers:true,singleSelect:true,pagination:true,url:'<%=path %>/wq/showAll.do',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'wangwang',width:60">旺旺ID</th>
				<th data-options="field:'address',width:60,">实体店地址</th>
				<th data-options="field:'createStoreTime',width:80">创店时间</th>
				<th data-options="field:'name',width:400">商品名称</th>
				
				<th data-options="field:'price',width:100">商品价格</th>
				<th data-options="field:'sellCount',width:80">销售量</th>
				<th data-options="field:'storeLevel',width:80">店铺级别</th>
				<th data-options="field:'stroeURL',width:240">店铺地址</th>
				<th data-options="field:'imgURL',width:80">商品图片地址</th>
				
			</tr>
		</thead>
	</table>
	<br/>

        <div class="easyui-panel" title="New Topic" style="width:400px">
        <div style="padding:10px 0 10px 60px">
        <form id="ff" method="post" action="<%=path %>/wq//downLoadByDate.do">
            <table>
                <tr>
                    <td>开始时间:</td>
                    <td><input id="startTime" name="startTime" class="easyui-datebox"></input></td>
                </tr>
                <tr>
                    <td>结束时间:</td>
                    <td><input id="endTime" name="endTime" class="easyui-datebox"></input></td>
                </tr>
              
                
              
            </table>
        </form>
        </div>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">下载</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
        </div>
    </div>
    <script>
        function submitForm(){
            $('#ff').submit();
        }
        function clearForm(){
            $('#ff').form('clear');
        }
    </script>
        
 

</body>
</html>