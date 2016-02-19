<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<h1>用户列表</h1>
<h2>权限列表</h2>
<shiro:authenticated>用户已经登录显示此内容</shiro:authenticated>
<br />
<shiro:hasRole name="manager">manager角色登录显示此内容</shiro:hasRole>
<br />
<shiro:hasRole name="admin">admin角色登录显示此内容</shiro:hasRole>
<br />
<shiro:hasRole name="common">common角色登录显示此内容</shiro:hasRole>
<br />
<shiro:hasAnyRoles name="manager,admin">**manager or admin 角色用户登录显示此内容**</shiro:hasAnyRoles>
<br />
<shiro:principal />
-显示当前登录用户名
<br />
<shiro:hasPermission name="add">add权限用户显示此内容</shiro:hasPermission>
<br />
<shiro:hasPermission name="user:query">query权限用户显示此内容<shiro:principal />
</shiro:hasPermission>
<br />
<shiro:lacksPermission name="user:del"> 不具有user:del权限的用户显示此内容 </shiro:lacksPermission>
<br />