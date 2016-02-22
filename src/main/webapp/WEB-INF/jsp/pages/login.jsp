<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login</title>
<link
	href="${pageContext.request.contextPath}/static/opensource/bootstrap/3.2.0/css/bootstrap.min.css"
	type="text/css" charset="UTF-8" rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/static/frontend/css/main.css"
	type="text/css" charset="UTF-8" rel="stylesheet" />
</head>
<body>
	<div id="top">
		<div id="header" class="container">
			<h1 id="app" class="pull-left">Warrant</h1>
			<h1 id="branding" class="pull-right">Thomson Reuters</h1>
		</div>
		<div id="navbar" class="container"></div>
	</div>
	<div id="center">
		<div id="content" class="container">

			<div class="alert alert-danger ${empty error ? 'hide' : ''}" role="alert" id="alert">${error}</div>
			<form class="form-horizontal" id="loginForm" action="loginAction" method="post"
				enctype="multipart/form-data" role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">Username: </label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="username" value="<shiro:principal/>"
							name="username" placeholder="Username" autofocus/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Password: </label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="password"
							name="password" placeholder="Password" />
					</div>
				</div>
				<!--div class="checkbox">
					<label> <input type="checkbox" value="remember-me" name="rememberMe" value="true">
						
					</label>
				</div-->
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<input class="btn btn-lg btn-primary btn-block" type="submit"
							id="loginButton" value="Log in">
					</div>
				</div>
			</form>

			<!--div class="alert alert-danger hidden" role="alert" id="alert">
				<span id="alertContent">Username or password incorrect!</span>
			</div>
			<form class="form-horizontal" id="loginForm" method="post"
				enctype="multipart/form-data" role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">Username: </label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="username"
							name="username" placeholder="Username" autofocus />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Password: </label>
					<div class="col-sm-6">
						<input type="password" class="form-control" id="password"
							name="password" placeholder="Password" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<input class="btn btn-lg btn-primary btn-block" type="submit"
							id="loginButton" value="Log in">
					</div>
				</div>
			</form-->
		</div>
	</div>

	<div id="bottom">
		<div id="footer" class="container">
			<div id="copyright" class="pull-left">
				<span>2016 Thomson Reuters </span>
			</div>
			<div id="version" class="pull-right">
				<span>Version: 0.0.1</span>
			</div>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/static/opensource/jquery/jquery-2.1.1.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/static/opensource/bootstrap/3.2.0/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/static/opensource/jquery/jquery.validate.min.js"
		type="text/javascript" charset="utf-8"></script>
	<script src="${pageContext.request.contextPath}/static/frontend/js/login.js" type="text/javascript"></script>


</body>