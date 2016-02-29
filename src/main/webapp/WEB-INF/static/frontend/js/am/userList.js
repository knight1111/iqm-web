var columnUnMax = 20;
var oTable = null;

$(document)
		.ready(
				function() {

					var currRow = null;
					var message = function(title, content, autoClose) {
						$.confirm({
							title : title,
							content : content,
							confirmButton : false,
							autoClose : autoClose,
							backgroundDismiss : true
						});
					};

					oTable = $("#user_table")
							.DataTable(
									{
										"bJQueryUI" : false,
										"bPaginate" : true,
										"bFilter" : true,
										"bLengthChange" : true,
										"aLengthMenu" : [ 10, 15, 20 ],
										"iDisplayLength" : 10,
										"bSort" : false,
										// "aaSorting": [[1, "desc"]],
										"bInfo" : true,
										"bWidth" : true,
										"bScrollCollapse" : true,
										"sPaginationType" : "full_numbers",
										"bProcessing" : true,
										"bServerSide" : true,
										"bDestroy" : true,
										"bSortCellsTop" : true,
										"sDom" : '<"toolbar">frtip',// 'T<"clear">lfrtip',
										"oTableTools" : {
											"sRowSelect" : "single"
										},
										"sAjaxSource" : "listUser",
										"showRowNumber" : true,
										"aoColumns" : [												
												{
													"mData" : "username",
													"sClass" : 'left',
													"sWidth" : "8px"
												},
												{
													"mData" : "role",
													"sClass" : 'left',
													"sWidth" : "50px",
													"mRender" : function(data) {
														if (data != null) {
															data = data.role;
														}
														return data;
													}
												},
												{
													"mData" : "username",
													"sClass" : 'left',
													"sWidth" : "140px",
													"mRender" : function(data) {
														if (data != null) {
															data = data.length > columnUnMax ? data
																	.substr(0,
																			columnUnMax)
																	+ '...'
																	: data;
														}
														return data;
													}
												},
												{
													"mData" : "name",
													"sWidth" : "180px",
													"sClass" : 'left'
												},
												{
													"mData" : "email",
													"sClass" : 'left'
												},
												{
													"mData" : "salt",
													"sWidth" : "100px",
													"sClass" : 'left'
												},
												{
													"mData" : "locked",
													"sWidth" : "50px",
													"sClass" : 'left',
													"mRender" : function(data) {
														if (data != null
																&& data == '0') {
															data = 'No';
														} else {
															data = 'Yes';
														}
														return data;
													}
												},
												{
													"mData" : "id",
													"sWidth" : "120px",
													"mRender" : function(data) {
														var str = '<input type="button" class="btn btn-default btn-xs update-button" value="Update"/>'
																+ '&nbsp;&nbsp;&nbsp;&nbsp;'
																+ '<input type="button" class="btn btn-default btn-xs delete-button" value="Delete"/>';
														return str;
													}
												} ],
										"fnRowCallback" : function(nRow, aData,
												iDisplayIndex,
												iDisplayIndexFull) {
											var index = iDisplayIndexFull + 1;// set
											// rowNumber
											$('td:eq(0)', nRow).html(index);
											return nRow;
										},
										"fnServerData" : function(sSource,
												aoData, fnCallback) {
											aoData
													.push({
														"name" : "iCurrentPage",
														"value" : this
																.fnPagingInfo().iPage + 1
													});
											$.ajax({
												"type" : 'post',
												"url" : sSource,
												"dataType" : "json",
												"data" : {
													aoData : JSON
															.stringify(aoData)
												},
												"success" : function(resp) {
													fnCallback(resp);
												}
											});

										}
									});

					$('#user_table_filter input').unbind();
					$('#user_table_filter input').bind('keyup', function(e) {
						if (e.keyCode == 13) {
							oTable.search(this.value).draw();
						}
					});

					$("div.toolbar")
							.html(
									'<input type="button" id="new_user" value="Create User" class="btn btn-default"/>');

					$('#user_table tbody')
							.on(
									'click',
									'input.delete-button',
									function() {
										currRow = oTable.row($(this).parents(
												'tr'));
										var data = currRow.data();
										$('#delete_user_id').val(data['id']);
										$
												.confirm({
													title : 'Delete Confirmation',
													content : 'Do you really want to delete this user?',
													confirm : function() {
														$
																.ajax(
																		{
																			url : "delete/"
																					+ $(
																							'#delete_user_id')
																							.val(),
																			dataType : "json",
																			type : "DELETE"
																		})
																.done(
																		function(
																				data) {
																			if (data.isSuccess == 'S') {
																				message(
																						false,
																						'Delete user successfully!',
																						'cancel|3000');
																				currRow
																						.remove()
																						.draw(
																								false);
																			} else {
																				message(
																						'Error Message',
																						data.errorMessage,
																						'cancel|8000');
																			}
																		})
																.fail(
																		function(
																				jqXHR,
																				textStatus,
																				errorThrown) {
																			message(
																					textStatus,
																					errorThrown,
																					'cancel|8000');
																		});
													}
												});
									}).on(
									'click',
									'input.update-button',
									function() {
										currRow = oTable.row($(this).parents(
												'tr'));
										var data = currRow.data();
										showtip(data['id']);
									});

					$('#new_user').bind('click', function(e) {
						showtip(null);
					});
				});

function showtip(id, frameSrc, otitle, cssobj, cssifm) {
	if (id) {
		frameSrc = "form?id=" + id;
	} else {
		frameSrc = "form";
	}
	otitle = "Form";
	$("#user_iframe").attr("src", frameSrc);
	$('#user_modal').modal({
		show : true,
		backdrop : 'static'
	});
	var _scrollHeight = $(document).scrollTop();
	var wHeight = $(window).height();
	var this_height;
	if (cssobj && cssobj["height"])
		this_height = cssobj["height"];
	else
		this_height = "520";
	var this_top = (wHeight - this_height) / 2 + _scrollHeight + "px";
	var this_top = (wHeight - this_height) / 2 + "px";

	var mycss = cssobj || {
		"width" : "820px",
		"height" : "520px",
		"top" : this_top
	};
	var myifmcss = cssifm || {};// iframe样式
	$('#user_modal .modal-dialog').css(mycss).find('.modal-content')
			.css({
				height : '100%',
				width : '100%'
			}).find('h4').html(otitle || "").end().find('.modal-body').css({
				height : '85%'
			}).find("#user_iframe").css(myifmcss);
}

function reloadDatatables() {
	oTable.ajax.reload(null, false);
};
