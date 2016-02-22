<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="content" class="container">
	<h1>
		CurrentUser:
		<shiro:principal />
	</h1>

	<h2>Role</h2>
	<div>
		<shiro:hasRole name="admin">is admin</shiro:hasRole>
	</div>
	<div>
		<shiro:hasRole name="common">is common</shiro:hasRole>
	</div>
	<div>
		<shiro:hasAnyRoles name="common,admin">is **admin or common**</shiro:hasAnyRoles>
	</div>

	<h2>Permission</h2>
	<div>
		<shiro:hasPermission name="add">hasPermission - add</shiro:hasPermission>
	</div>
	<div>
		<shiro:hasPermission name="user:query">hasPermission - query <shiro:principal />
		</shiro:hasPermission>
	</div>
	<div>
		<shiro:lacksPermission name="user:del">lacksPermission - user:del </shiro:lacksPermission>
	</div>

</div>
