<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="center">
	<div class="padding-top">
		<form:form id="user_form" modelAttribute="user" action=""
			method="post" class="form-horizontal">
			<form:hidden path="id" id="user_id" />

			<div class="form-group">
				<label class="col-sm-2 control-label">Username: </label>
				<div class="col-sm-10">
					<form:input path="username" id="username" name="username"
						htmlEscape="false" class="form-control-iqm" placeholder="Username"
						readonly="${empty user.username ? false : true}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Salt: </label>
				<div class="col-sm-10">
					<form:input path="salt" id="salt" name="salt" htmlEscape="false"
						class="form-control-iqm" placeholder="Salt"
						readonly="${empty user.salt ? false : true}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Real Name: </label>
				<div class="col-sm-10">
					<form:input path="name" id="name" name="name" htmlEscape="false"
						class="form-control-iqm" placeholder="Real Name" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Email: </label>
				<div class="col-sm-10">
					<form:input path="email" id="email" name="email" htmlEscape="false"
						class="form-control-iqm" placeholder="Email" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Locked: </label>
				<div class="col-sm-10">
					<form:radiobutton path="locked" value="0" />
					false
					<form:radiobutton path="locked" value="1" />
					true
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">New password: </label>
				<div class="col-sm-10">
					<form:password path="newPassword" id="new_pwd" name="newPassword"
						class="form-control-iqm" placeholder="New Password" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Confirm Password: </label>
				<div class="col-sm-10">
					<input type="password" class="form-control-iqm" id="confirm_new_pwd"
						name="confirmNewPwd" placeholder="Confirm New Password" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Role: </label>
				<div class="col-sm-10">
					<form:radiobutton path="role.role" value="admin" name="role.role"/>
					Admin
					<form:radiobutton path="role.role" value="common" name="role.role" />
					Common
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="button" id="close_button"
						class="btn btn-default btn-close">Close</button>
				</div>
			</div>
		</form:form>
	</div>
</div>

<script src="../static/opensource/jquery/jquery.validate.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../static/opensource/jquery/additional-methods.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../static/frontend/js/am/userForm.js"
	type="text/javascript" charset="utf-8"></script>
