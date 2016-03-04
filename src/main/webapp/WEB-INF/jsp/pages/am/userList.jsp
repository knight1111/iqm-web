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

	<div>
		<ul class="nav nav-tabs" role="tablist" id="am_tabs">
			<li role="presentation" class="active"><a href="#user_div"
				aria-controls="user_div" role="tab" data-toggle="tab">User List</a></li>
			<li role="presentation"><a href="#role_div"
				aria-controls="role_div" role="tab" data-toggle="tab">Role List</a></li>
			<li role="presentation"><a href="#permission_div"
				aria-controls="permission_div" role="tab" data-toggle="tab">Permission
					List</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content" style="padding-bottom: 10px;">
			<div role="tabpanel" class="tab-pane active padding-top"
				id="user_div">

				<table id="user_table" class="table table-striped">
					<thead>
						<tr>
							<th>No</th>
							<th>Role</th>
							<th>Username</th>
							<th>Real Name</th>
							<th>Email</th>
							<th>Salt</th>
							<th>Locked</th>
							<th>Actions</th>
						</tr>
					</thead>
				</table>

			</div>
			<div role="tabpanel" class="tab-pane padding-top" id="role_div">
				2222</div>
			<div role="tabpanel" class="tab-pane padding-top" id="permission_div">
				333</div>

		</div>
	</div>

	<div class="modal fade" id="user_modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">User Form</div>
				<div class="modal-body">
					<iframe id="user_iframe" width="100%" height="100%" frameborder="0"></iframe>
				</div>
			</div>
		</div>
	</div>

	<input type="hidden" id="delete_user_id" />
</div>


<script type="text/javascript" charset="utf8"
	src="../static/opensource/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="../static/opensource/datatables/1.10.11/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="../static/opensource/datatables/1.10.11/js/api/fnPagingInfo.js"></script>

<script src="../static/frontend/js/am/userList.js"
	type="text/javascript" charset="utf-8"></script>