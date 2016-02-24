<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="center">
	<div role="tabpanel" class="tab-pane active padding-top"
		id="userPreference">
		<div>
			<div class="alert alert-success hidden" role="alert" id="upInfo">
				<strong>Update successfully!</strong>
			</div>
			<div class="alert alert-danger hidden" role="alert" id="upAlert">
				<strong>Update Failed!</strong> <span></span>
			</div>
		</div>
		<form:form id="userForm" modelAttribute="user" action=""
			method="post" class="form-horizontal">
			<form:hidden path="id" id="userId"/>

			<div class="form-group">
				<label class="col-sm-2 control-label">Username: </label>
				<div class="col-sm-6">
					<form:input path="username" id="username" name="username" value=""
						htmlEscape="false" maxlength="50" class="required form-control"
						placeholder="Username" readonly="${empty user.username ? false : true}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Salt: </label>
				<div class="col-sm-6">
					<form:input path="salt" id="salt" name="salt" htmlEscape="false"
						maxlength="50" class="form-control" placeholder="Salt"
						readonly="${empty user.salt ? false : true}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Locked: </label>
				<div class="col-sm-6">
					<form:radiobutton path="locked" value="0" />
					false
					<form:radiobutton path="locked" value="1" />
					true
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">New password: </label>
				<div class="col-sm-6">
					<form:password path="newPassword" id="newPwd"
						name="newPwd" htmlEscape="false" class="form-control"
						placeholder="New Password" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Confirm Password: </label>
				<div class="col-sm-6">
					<input type="password" class="form-control" id="confirmNewPwd"
						name=confirmNewPwd placeholder="Confirm New Password" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Role: </label>
				<div class="col-sm-6">
					<form:radiobutton path="role.
					6+9+	" value="0" />
					false
					<form:radiobutton path="locked" value="1" />
					true
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="button" id="closeButton"
						class="btn btn-default btn-close">Close</button>
				</div>
			</div>
		</form:form>

	</div>
</div>
<script>
	$(function(){
		$("#closeButton").click(function(){
			var parent = window.parent;
			var m = parent.jQuery('#NoPermissionModal');//$('#NoPermissionModal', window.parent.document);
			m.modal('hide');

			parent.viewUser();
		});
	});
	
</script>
<script src="../static/opensource/jquery/jquery.validate.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../static/opensource/jquery/additional-methods.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../static/frontend/js/am/userForm.js"
	type="text/javascript" charset="utf-8"></script>
