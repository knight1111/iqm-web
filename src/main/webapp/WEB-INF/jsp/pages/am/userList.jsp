<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<div id="content" class="container">
	<div>
		<ol class="breadcrumb">
			<li><a href="${pageContext.request.contextPath}/index/dashboard">Dashboard</a></li>
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

<div class="modal fade" id="NoPermissionModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">User Form</div>
			<div class="modal-body">
				<iframe id="NoPermissionIframe" width="100%" height="100%"
					frameborder="0"></iframe>
			</div>
		</div>
	</div>
</div>

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

<script src="../static/frontend/js/am/userList.js"
	type="text/javascript" charset="utf-8"></script>
