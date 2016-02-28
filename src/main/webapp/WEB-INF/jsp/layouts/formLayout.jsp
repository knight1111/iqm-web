<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><tiles:getAsString name="title" /></title>
<link
	href="${pageContext.request.contextPath}/static/opensource/bootstrap/3.2.0/css/bootstrap.min.css"
	type="text/css" charset="UTF-8" rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/static/frontend/css/main.css"
	type="text/css" charset="UTF-8" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/opensource/datatables/1.10.11/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/opensource/jquery-confirm/2.5.0/jquery-confirm.css">
<script
	src="${pageContext.request.contextPath}/static/opensource/jquery/jquery-2.1.1.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/static/opensource/bootstrap/3.2.0/js/bootstrap.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/static/opensource/jquery-confirm/2.5.0/jquery-confirm.min.js"
	type="text/javascript"></script>
</head>
<body style="overflow: hidden; background-color: #fff;">
	<tiles:insertAttribute name="content" />
</body>
</html>