<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>资源管理</title>
		<%@ include file="/common/meta.jsp"%>
		<script src="${ctx}/widgets/jquery/jquery.js" type="text/javascript"></script>
		<%@ include file="/widgets/jquery/corner/corner.jsp"%>
		<link href="${ctx}/styles/css.css" type="text/css" rel="stylesheet">
		<script>
			$(document).ready(function(){
				$(".query").corner();
			}); 
		</script>
	</head>

	<body>
		<div id="container">
			<div id="header">
			</div>
			<div id="mainer">
				<div class="query">
				资源管理
				<s:if test="page.totalCount!=0">
						<table id="sjTable">
							<thead>
								<tr>
									<th>
										<b>&nbsp;</b>
									</th>
									<th>
										<b>资源ID</b>
									</th>
									<th>
										<b>资源名</b>
									</th>
									<th>
										<b>类型</b>
									</th>
									<th>
										<b>修改</b>
									</th>
									<th>
										<b>删除</b>
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="page.result" status="u">
									<tr id="${u.index+1}">
										<td>
											<img src="<c:url value="/images/icon/16x16/mini_icons_098.gif"/>" onclick="viewDetail(${u.index+1},${resId})"/>
										</td>
										<td>
											${resId}&nbsp;
										</td>
										<td>
											${name}
										</td>
										<td>
											${resType}&nbsp;
										</td>
										<td>
											<a
												href="${ctx}/admin/resource/resource!edit.action?id=${resId}"><img
													src="<c:url value="/images/icon/16x16/modify.gif"/>"
													border="0" /> </a>
										</td>
										<td>
											<a style="cursor: hand" onclick="deleteConfirm(${resId})"><img
													src="<c:url value="/images/icon/16x16/delete.gif"/>"
													border="0" /> </a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</s:if>
				</div>
			</div>
			<div id="footer">
			</div>
		</div>
	</body>
</html>
