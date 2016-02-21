<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<div id="content" class="container">
	<div>
		<ol class="breadcrumb">
			<li><a href="../index/dashboard">Dashboard</a></li>
			<li class="active">User Management</li>
		</ol>
	</div>

	<table id="user_table" class="table table-striped">
		<thead>
			<tr>
				<th>No</th>
				<th>Username</th>
				<th>Salt</th>
				<th>Locked</th>
				<th>Actions</th>
			</tr>
		</thead>
	</table>
</div>

<!--div class="modal fade" id="view" role="dialog" aria-hidden="true">
	<div class="modal-dialog"-->
		<div class="panel panel-primary" style="padding: 10px;">
			<div>
				<form:form id="inputForm" modelAttribute="user" action="save"
					method="post" class="form-horizontal">
					<form:input path="id" />
					<div class="control-group">
						<label class="control-label">Username:</label>
						<div class="controls">
							<form:input path="username" htmlEscape="false" maxlength="50"
								class="required username" readonly="true"/>
						</div>
					</div>
					<div class="control-group">
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
					</div>
					<div class="control-group">
						<label class="control-label">Locked:</label>
						<div class="controls">
							<form:radiobutton path="locked" value="0"/>false
        					<form:radiobutton path="locked" value="1"/>true
						</div>
					</div>
					<div class="form-actions">
						<input id="btnSubmit" class="btn btn-primary" type="submit"
							value="Save" />&nbsp; <input id="btnCancel" class="btn"
							type="button" value="返 回" onclick="history.go(-1)" />
					</div>
				</form:form>
				<!--input type="hidden" value="" id="userId" />
				<form class="form-horizontal" id="authForm">
					<div class="row form-group">
						<div class="col-md-8">
							<label class="control-label control-label-width">Username : </label>
							<span class="form-control-static" id="unSpan"></span>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-md-8">
							<label class="control-label control-label-width">Display Name
								: </label> <span class="form-control-static" id="dnSpan"></span>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-md-8">
							<label class="control-label control-label-width">Role : </label> 
							<span class="form-control-static">
								<input type="radio" name="roles" value="common" /> Common
								<input type="radio" name="roles" value="admin" /> Admin
							</span>
						</div>						
					</div>
				</form-->
			</div>

			<!--div class="modal-footer" style="padding: 8px;">
				<button type="button" class="btn btn-small" data-dismiss="modal"
					aria-hidden="true">Close</button>
				<input type="button" value="Save" id="auth_ok"
					class="btn btn-default btn-small">
			</div-->
		</div>
	<!--/div>
</div-->

<div class="modal fade" id="deleteConfirm" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="modal-title">Delete Confirm</h4>
			</div>
			<div class="modal-body">Do you really want to delete this user?</div>
			<input type="hidden" id="currId" />
			<div class="modal-footer" style="padding: 8px;">
				<button type="button" class="btn btn-small" data-dismiss="modal">Cancel</button>
				<button type="button" id="delete_ok"
					class="btn btn-default btn-small">OK</button>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript" charset="utf8"
	src="../static/opensource/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="../static/opensource/datatables/1.10.11/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="../static/opensource/datatables/1.10.11/js/api/fnPagingInfo.js"></script>

<script src="../static/frontend/js/am/listUsers.js"
	type="text/javascript" charset="utf-8"></script>
