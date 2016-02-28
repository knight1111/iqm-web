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
		<ul class="nav nav-tabs" role="tablist" id="user_tabs">
			<li role="presentation" class="active"><a
				href="#user_preference" aria-controls="user_preference" role="tab"
				data-toggle="tab">User Preference</a></li>
			<li role="presentation"><a href="#reset_password"
				aria-controls="reset_password" role="tab" data-toggle="tab">Reset
					Password</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content" style="padding-bottom: 10px;">
			<div role="tabpanel" class="tab-pane active padding-top"
				id="user_preference">
				<form:form id="user_form" modelAttribute="user" action=""
					method="post" class="form-horizontal">
					<form:hidden path="id" />
					<div class="form-group">
						<label class="col-sm-2 control-label">Username: </label>
						<div class="col-sm-6">
							<form:input path="username" id="username" name="username"
								htmlEscape="false" class="form-control" placeholder="Username"
								readonly="true" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Real Name: </label>
						<div class="col-sm-6">
							<form:input path="name" id="name" name="name" htmlEscape="false"
								class="form-control" placeholder="Real Name" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Email: </label>
						<div class="col-sm-6">
							<form:input path="email" id="email" name="email"
								htmlEscape="false" class="form-control" placeholder="Email" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">Submit</button>
						</div>
					</div>
				</form:form>
			</div>
			<div role="tabpanel" class="tab-pane padding-top" id="reset_password">
				<form class="form-horizontal" id="pwd_form" method="post"
					enctype="multipart/form-data">
					<div class="form-group">
						<label class="col-sm-2 control-label">Old password: </label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="old_pwd"
								name="oldPwd" placeholder="Old Password" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">New password: </label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="new_pwd"
								name="newPwd" placeholder="New Password" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">Confirm Password: </label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="confirm_new_pwd"
								name="confirmNewPwd" placeholder="Confirm New Password" />
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
