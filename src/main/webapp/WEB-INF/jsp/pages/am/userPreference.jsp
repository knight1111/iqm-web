<div id="content" class="container">
	<div>
		<ol class="breadcrumb">
			<li><a href="../index/dashboard">Dashboard</a></li>
			<li class="active">User Preference</li>
		</ol>
	</div>

	<div class="panel panel-primary" style="padding: 10px;">
		<div>
			<input type="hidden" value="" id="userId" />
			<form class="form-horizontal" id="authForm">
				<div class="row form-group">
					<div class="col-md-8">
						<label class="control-label control-label-width">Username
							: </label> <span class="form-control-static" id="unSpan">${user.username}</span>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-md-8">
						<label class="control-label control-label-width">Display
							Name : </label> <span class="form-control-static" id="dnSpan"></span>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-md-8">
						<label class="control-label control-label-width">Role : </label> <span
							class="form-control-static"> <input type="radio"
							name="roles" value="common" /> Common <input type="radio"
							name="roles" value="admin" /> Admin
						</span>
					</div>
				</div>
			</form>
		</div>

		<div class="modal-footer" style="padding: 8px;">
			<button type="button" class="btn btn-small" data-dismiss="modal"
				aria-hidden="true">Close</button>
			<input type="button" value="Save" id="auth_ok"
				class="btn btn-default btn-small">
		</div>
	</div>
</div>

<script type="text/javascript" charset="utf8"
	src="../static/opensource/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="../static/opensource/datatables/1.10.11/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="../static/opensource/datatables/1.10.11/js/api/fnPagingInfo.js"></script>

<!--script src="../static/frontend/js/am/listUsers.js"
	type="text/javascript" charset="utf-8"></script-->
