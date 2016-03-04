<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script
	src="${pageContext.request.contextPath}/static/opensource/angularjs/angular.min.js"
	type="text/javascript"></script>

<div id="content" class="container" ng-app="crudApp">

	<div ng-controller="crudController">
		<ul class="nav nav-tabs" role="tablist" id="am_tabs">
			<li role="presentation" class="active"><a href="#user_div"
				aria-controls="user_div" role="tab" data-toggle="tab">User List</a></li>
			<li role="presentation"><a href="#role_div"
				aria-controls="role_div" role="tab" data-toggle="tab"
				ng-click="getRoles()">Role List</a></li>
			<li role="presentation"><a href="#permission_div"
				aria-controls="permission_div" role="tab" data-toggle="tab">Permission
					List</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content" style="padding-bottom: 10px;">
			<div role="tabpanel" class="tab-pane active padding-top"
				id="user_div">
				<div class="form-inline">
					<div class="toolbar">
						<div class="alert alert-default navbar-brand search-box">
							<button class="btn btn-primary" ng-click="formToggle()">
								Add User <span class="glyphicon glyphicon-plus"
									aria-hidden="true"></span>
							</button>
						</div>
						<!--div class="alert alert-default input-group search-box">
					<span class="input-group-btn"> <input type="text"
						class="form-control"
						placeholder="Search User"
						ng-model="search_query">
					</span>
				</div-->
					</div>

					<table class="table table-striped dataTable no-footer">
						<tr>
							<th>No</th>
							<th>Username</th>
							<th>Real Name</th>
							<th>Email</th>
							<th>Locked</th>
							<th></th>
						</tr>
						<tr ng-repeat="detail in details| filter:search_query">
							<td><span>{{ $index + 1 }}</span></td>
							<td>{{detail.username}}</td>
							<td>{{detail.name}}</td>
							<td>{{detail.email}}</td>
							<td>{{detail.locked}}</td>
							<td>
								<button class="btn btn-warning" ng-click="editInfo(detail)"
									title="Edit">
									<span class="glyphicon glyphicon-edit"></span>
								</button>
								<button class="btn btn-danger" ng-click="deleteInfo(detail)"
									title="Delete">
									<span class="glyphicon glyphicon-trash"></span>
								</button>
							</td>
						</tr>
					</table>
				</div>

			</div>
			<div role="tabpanel" class="tab-pane padding-top" id="role_div">

				<div class="form-inline">
					<div class="toolbar">
						<div class="alert alert-default navbar-brand search-box">
							<button class="btn btn-primary" ng-click="formToggle()">
								Add Role <span class="glyphicon glyphicon-plus"
									aria-hidden="true"></span>
							</button>
						</div>
						<!--div class="alert alert-default input-group search-box">
					<span class="input-group-btn"> <input type="text"
						class="form-control"
						placeholder="Search User"
						ng-model="search_query">
					</span>
				</div-->
					</div>

					<table class="table table-striped dataTable no-footer">
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Description</th>
							<th>Available</th>
							<th></th>
						</tr>
						<tr ng-repeat="role in roles| filter:search_query">
							<td><span>{{role.id}}</span></td>
							<td>{{role.role}}</td>
							<td>{{role.description}}</td>
							<td>{{role.available}}</td>
							<td>
								<button class="btn btn-warning" ng-click="editRole(role)"
									title="Edit">
									<span class="glyphicon glyphicon-edit"></span>
								</button>
								<button class="btn btn-danger" ng-click="deleteRole(role)"
									title="Delete">
									<span class="glyphicon glyphicon-trash"></span>
								</button>
							</td>
						</tr>
					</table>
				</div>

			</div>
			<div role="tabpanel" class="tab-pane padding-top" id="permission_div">

				<div class="form-inline">
					<div class="toolbar">
						<div class="alert alert-default navbar-brand search-box">
							<button class="btn btn-primary" ng-click="formToggle()">
								Add Permission <span class="glyphicon glyphicon-plus"
									aria-hidden="true"></span>
							</button>
						</div>
						<!--div class="alert alert-default input-group search-box">
					<span class="input-group-btn"> <input type="text"
						class="form-control"
						placeholder="Search User"
						ng-model="search_query">
					</span>
				</div-->
					</div>

					<table class="table table-striped dataTable no-footer">
						<tr>
							<th>Id</th>
							<th>Username</th>
							<th>Real Name</th>
							<th>Email</th>
							<th>Locked</th>
							<th></th>
							<th></th>
						</tr>
						<tr ng-repeat="detail in details| filter:search_query">
							<td><span>{{detail.id}}</span></td>
							<td>{{detail.username}}</td>
							<td>{{detail.name}}</td>
							<td>{{detail.email}}</td>
							<td>{{detail.locked}}</td>
							<td>
								<button class="btn btn-warning" ng-click="editInfo(detail)"
									title="Edit">
									<span class="glyphicon glyphicon-edit"></span>
								</button>
							</td>
							<td>
								<button class="btn btn-danger" ng-click="deleteInfo(detail)"
									title="Delete">
									<span class="glyphicon glyphicon-trash"></span>
								</button>
							</td>
						</tr>
					</table>
				</div>

			</div>
		</div>

		<div class="col-md-6 col-md-offset-3">
			<div ng-include src="'templates/form.html'"></div>
			<div ng-include src="'templates/editForm.html'"></div>
		</div>
	</div>
</div>



<!-- Include controller -->
<script
	src="${pageContext.request.contextPath}/static/frontend/js/angular/angular-script.js"
	type="text/javascript"></script>
