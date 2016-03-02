jQuery.validator.addMethod("notEqual", function(value, element, param) {
	return this.optional(element) || value != $(param).val();
}, "Please specify a different value");

$(document)
		.ready(
				function() {

					var message = function(title, content, autoClose) {
						$.confirm({
							title : title,
							content : content,
							confirmButton : false,
							autoClose : autoClose,
							backgroundDismiss : true
						});
					};

					var upValidator = function() {
						var handleSubmit = function() {
							$('#user_form')
									.validate(
											{
												errorElement : 'span',
												errorClass : 'help-block',
												focusInvalid : false,
												rules : {
													name : {
														required : true,
														maxlength : 45
													},
													email : {
														email : true,
														maxlength : 45
													}
												},
												messages : {
													name : {
														required : "Real Name is required.",
														maxlength : "Real Name can't be more than 45 bytes."
													},
													email : {
														email : "Please enter a valid email address.",
														maxlength : "Email can't be more than 45 bytes."
													}
												},

												highlight : function(element) {
													$(element)
															.closest(
																	'.form-group')
															.addClass(
																	'has-error');
												},

												success : function(label) {
													label
															.closest(
																	'.form-group')
															.removeClass(
																	'has-error');
													label.remove();
												},

												errorPlacement : function(
														error, element) {
													element.parent('div')
															.append(error);
												},

												submitHandler : function(form) {
													// form.submit();
													$
															.ajax(
																	{
																		type : 'post',
																		url : 'save',
																		data : $(
																				form)
																				.serialize(),
																		dataType : 'json'
																	})
															.done(
																	function(
																			data) {
																		if (data.isSuccess == 'S') {
																			message(
																					false,
																					'Update user preference successfully!',
																					'cancel|3000');
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
							init : function() {
								handleSubmit();
							}
						};

					}();

					var rpValidator = function() {
						var handleSubmit = function() {
							$('#pwd_form')
									.validate(
											{
												errorElement : 'span',
												errorClass : 'help-block',
												focusInvalid : false,
												rules : {
													oldPwd : {
														required : true,
														maxlength : 16
													},
													newPwd : {
														required : true,
														notEqual : "#old_pwd",
														minlength : 6,
														maxlength : 16
													},
													confirmNewPwd : {
														required : true,
														equalTo : "#new_pwd"
													}
												},
												messages : {
													oldPwd : {
														required : "Old Password is required.",
														maxlength : "Old Password can't be more than 16 bytes."
													},
													newPwd : {
														required : "New Password is required.",
														notEqual : "Please specify a different password.",
														minlength : "New Password can't be less than 6 bytes.",
														maxlength : "New Password can't be more than 16 bytes."
													},
													confirmNewPwd : {
														required : "Please re-type new password.",
														equalTo : "Please enter the same password."
													}
												},

												highlight : function(element) {
													$(element)
															.closest(
																	'.form-group')
															.addClass(
																	'has-error');
												},

												success : function(label) {
													label
															.closest(
																	'.form-group')
															.removeClass(
																	'has-error');
													label.remove();
												},

												errorPlacement : function(
														error, element) {
													element.parent('div')
															.append(error);
												},

												submitHandler : function(form) {
													// form.submit();
													$
															.ajax(
																	{
																		type : 'post',
																		url : 'savePassword',
																		data : $(
																				form)
																				.serialize(),
																		dataType : 'json'
																	})
															.done(
																	function(
																			data) {
																		if (data.isSuccess == 'S') {
																			// clear
																			// form
																			form
																					.reset();

																			message(
																					false,
																					'Update password successfully!',
																					'cancel|3000');
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
																				data.errorMessage,
																				errorThrown,
																				'cancel|8000');
																	});
													return false;
												}
											});

							$('#pwd_form input').keypress(function(e) {
								if (e.which == 13) {
									var pf = $('#pwd_form');
									if (pf.validate().form()) {
										pf.submit();
									}
									return false;
								}
							});
						}
						return {
							init : function() {
								handleSubmit();
							}
						};

					}();

					$('#user_tabs a').click(function(e) {
						e.preventDefault();
						$(this).tab('show');
					});

					upValidator.init();
					rpValidator.init();
				});
