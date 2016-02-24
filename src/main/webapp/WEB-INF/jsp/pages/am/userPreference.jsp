<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="content" class="container">
	<div>
		<ol class="breadcrumb">
			<li><a href="${pageContext.request.contextPath}/index/dashboard">Dashboard</a></li>
			<li class="active">User Preference</li>
		</ol>
	</div>

	<div>
		<ul class="nav nav-tabs" role="tablist" id="myTabs">
			<li role="presentation" class="active"><a href="#userPreference"
				aria-controls="userPreference" role="tab" data-toggle="tab">User
					Preference</a></li>
			<li role="presentation"><a href="#resetPassword"
				aria-controls="resetPassword" role="tab" data-toggle="tab">Reset
					Password</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content" style="padding-bottom: 10px;">
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
				<form:form id="inputForm" modelAttribute="user" action="save"
					method="post" class="form-horizontal">
					<form:hidden path="id" />

					<div class="form-group">
						<label class="col-sm-2 control-label">Username: </label>
						<div class="col-sm-6">
							<form:input path="username" id="username" name="username"
								htmlEscape="false" maxlength="50" class="required form-control"
								placeholder="Username" readonly="true" />
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
						<label class="col-sm-2 control-label">Salt: </label>
						<div class="col-sm-6">
							<form:input path="salt" id="salt" name="salt"
								htmlEscape="false" maxlength="50" class="form-control"
								placeholder="Salt" readonly="true" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">Submit</button>
						</div>
					</div>
				</form:form>
			</div>
			<div role="tabpanel" class="tab-pane padding-top" id="resetPassword">
				<div>
					<div class="alert alert-success hidden" role="alert" id="rpInfo">
						<strong>Reset password successfully!</strong>
					</div>
					<div class="alert alert-danger hidden" role="alert" id="rpAlert">
						<strong>Reset password Failed!</strong> <span></span>
					</div>
				</div>
				<!-- div class="control-group">
						<label class="control-label">Password:</label>
						<div class="controls">
							<input id="newPassword" name="newPassword" type="password"
								value="" maxlength="50" minlength="3"
								class="${empty user.id?'required':''}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Confirm password:</label>
						<div class="controls">
							<input id="confirmNewPassword" name="confirmNewPassword"
								type="password" value="" maxlength="50" minlength="3"
								equalTo="#newPassword" />
						</div>
					</div-->
				<form class="form-horizontal" id="pwdForm" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="id" value="" />
					<div class="form-group">
						<label class="col-sm-2 control-label">Old password: </label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="oldPwd"
								name="oldPwd" placeholder="Old Password" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">New password: </label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="newPwd"
								name="newPwd" placeholder="New Password" />
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
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">Submit</button>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>
</div>

<script src="../static/opensource/jquery/jquery.validate.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../static/opensource/jquery/additional-methods.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../static/frontend/js/am/userPreference.js"
	type="text/javascript" charset="utf-8"></script>
