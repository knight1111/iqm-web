<div id="content" class="container">
	<div>
		<ol class="breadcrumb">
			<li><a href="../dashboard">Dashboard</a></li>
			<li class="active">Gateway</li>
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

<div class="modal fade" id="view" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="panel panel-primary" style="padding: 10px;">
			<div>
				<input type="hidden" value="" id="userId" />
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
				</form>
			</div>

			<div class="modal-footer" style="padding: 8px;">
				<button type="button" class="btn btn-small" data-dismiss="modal" aria-hidden="true">Close</button>
				<input type="button" value="Save" id="auth_ok" class="btn btn-default btn-small">
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
			<div class="modal-body">Do you really want to delete this
				user?</div>
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

