var userValidator = function() {
	var userId = $('#user_id').val();
	var isNew = (userId == null || userId == '');

	var handleSubmit = function(message) {
		$('#user_form')
				.validate(
						{
							errorElement : 'span',
							errorClass : 'help-block-iqm',
							focusInvalid : false,
							rules : {
								username : {
									required : true,
									maxlength : 100
								},
								salt : {
									required : true,
									maxlength : 100
								},
								name : {
									required : true,
									maxlength : 45
								},
								email : {
									email : true,
									maxlength : 45
								},
								'role.role' : {
									required : true
								},
								newPassword : {
									required : isNew,
									minlength : 6,
									maxlength : 16
								},
								confirmNewPwd : {
									required : isNew,
									equalTo : "#new_pwd"
								}
							},
							messages : {
								username : {
									required : "Username is required.",
									maxlength : "Username can't be more than 100 bytes."
								},
								salt : {
									required : "Salt is required.",
									maxlength : "Salt can't be more than 100 bytes."
								},
								name : {
									required : "Real Name is required.",
									maxlength : "Real Name can't be more than 45 bytes."
								},
								email : {
									email : "Please enter a valid email address.",
									maxlength : "Email can't be more than 45 bytes."
								},
								'role.role' : {
									required : "Role is required."
								},
								newPassword : {
									required : "Password is required.",
									minlength : "Password can't be less than 6 bytes.",
									maxlength : "Password can't be more than 16 bytes."
								},
								confirmNewPwd : {
									required : "Please re-type password.",
									equalTo : "Please enter the same password."
								}
							},

							highlight : function(element) {
								$(element).closest('.form-group').addClass(
										'has-error');
							},

							success : function(label) {
								label.closest('.form-group').removeClass(
										'has-error');
								label.remove();
							},

							errorPlacement : function(error, element) {
								element.parent('div').append(error);
							},

							submitHandler : function(form) {
								// form.submit();
								$
										.ajax({
											type : 'post',
											url : 'save',
											data : $(form).serialize(),
											dataType : 'json'
										})
										.done(
												function(data) {
													if (data.isSuccess == 'S') {
														var parent = window.parent;
														var m = parent
																.jQuery('#user_modal');
														m.modal('hide');

														parent
																.reloadDatatables();
													} else {
														message(
																'Error Message',
																data.errorMessage,
																'cancel|8000');
													}
												}).fail(
												function(jqXHR, textStatus,
														errorThrown) {
													message(textStatus,
															errorThrown,
															'cancel|8000');
												});
								return false;
							}
						});

		$('#user_form input').keypress(function(e) {
			if (e.which == 13) {
				var uf = $('#user_form');
				if (uf.validate().form()) {
					uf.submit();
				}
				return false;
			}
		});
	}
	return {
		init : function(message) {
			handleSubmit(message);
		}
	};

}();

$(document).ready(function() {

	var message = function(title, content, autoClose) {
		$.confirm({
			title : title,
			content : content,
			confirmButton : false,
			autoClose : autoClose,
			backgroundDismiss : true
		});
	};

	userValidator.init(message);

	$("#close_button").click(function() {
		var parent = window.parent;
		var m = parent.jQuery('#user_modal');
		m.modal('hide');
	});
});
