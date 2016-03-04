// Application module
var crudApp = angular.module('crudApp', [])
 .directive('pwCheck', function() {
	return {
		require : 'ngModel',
		link : function(scope, elem, attrs, ctrl) {
			var firstPassword = '#' + attrs.pwCheck;
			$(elem).add(firstPassword).on('keyup', function() {
				scope.$apply(function() {
					var pv = elem.val();
					if (pv != '') {
						var v = pv === $(firstPassword).val();
						ctrl.$setValidity('pwcheck', v);
					}
				});
			});
		}
	}
});
//.directive('pwCheck', function() {
//	return {
//		require : 'ngModel',
//		link : function(scope, elem, attrs, ctrl) {
//
//			var me = attrs.ngModel;
//			var matchTo = attrs.pwCheck;
//
//			scope.$watch('[me, matchTo]', function(value) {
//				console.log(scope['user']['confirmPassword']);
//				console.log(scope['user']['newPassword']);
//				ctrl.$setValidity('pwcheck', scope['user']['newPassword'] === scope['user']['confirmPassword']);
//			});
//
//		}
//	}
//});

crudApp
		.config(function($httpProvider) {
			$httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
			$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

			// Override $http service's default transformRequest
			$httpProvider.defaults.transformRequest = [ function(data) {
				/**
				 * The workhorse; converts an object to x-www-form-urlencoded
				 * serialization.
				 * 
				 * @param {Object}
				 *            obj
				 * @return {String}
				 */
				var param = function(obj) {
					var query = '';
					var name, value, fullSubName, subName, subValue, innerObj, i;

					for (name in obj) {
						value = obj[name];

						if (value instanceof Array) {
							for (i = 0; i < value.length; ++i) {
								subValue = value[i];
								fullSubName = name + '[' + i + ']';
								innerObj = {};
								innerObj[fullSubName] = subValue;
								query += param(innerObj) + '&';
							}
						} else if (value instanceof Object) {
							for (subName in value) {
								subValue = value[subName];
								fullSubName = name + '[' + subName + ']';
								innerObj = {};
								innerObj[fullSubName] = subValue;
								query += param(innerObj) + '&';
							}
						} else if (value !== undefined && value !== null) {
							query += encodeURIComponent(name) + '='
									+ encodeURIComponent(value) + '&';
						}
					}

					return query.length ? query.substr(0, query.length - 1)
							: query;
				};

				return angular.isObject(data)
						&& String(data) !== '[object File]' ? param(data)
						: data;
			} ];
		});

crudApp
		.controller(
				"crudController",
				[
						'$scope',
						'$http',
						function($scope, $http) {

							// Function to get employee details from the
							// database

							// getRoles();

							$scope.roles = [];

							$scope.getUsers = function() {
								// Sending request to EmpDetails.php files
								$http.post('user/listAllUser').success(
										function(data) {
											// Stored the returned data into
											// scope
											$scope.details = data;
										});
							}

							$scope.getUsers();

							$scope.firstLoadRoleTab = true;
							$scope.getRoles = function() {
								// Sending request to EmpDetails.php files
								if ($scope.firstLoadRoleTab) {
									$http.post('role/listAllRole').success(
											function(data) {
												// Stored the returned data into
												// scope
												$scope.roles = data;
											});
								}
								$scope.firstLoadRoleTab = false;
							}

							$scope.currentUser = {};
							// Setting default value of gender
							$scope.user = {
								'locked' : '0',
								'role' : 'common'
							};

							// Function to add toggle behaviour to form
							$scope.formToggle = function() {
								$('#empDiv').modal('show');
							}
							$scope.editInfo = function(info) {
								$scope.currentUser = info;
								// $('#empForm').slideUp();
								// $('#editForm').slideToggle();
								$('#editDiv').modal('show');
							}

							$scope.insertInfo = function(info) {
								if (info) {
									info['role.role'] = info.role;
									info.role = undefined;
								}
								console.log(info);
								$http.post('user/save', info).success(
										function(data) {
											if (data.isSuccess == 'S') {
												$scope.getUsers();
												$('#empDiv').modal('hide');
											}
										});
							}

							$scope.updateInfo = function(info) {
								$http.post('user/save', {
									"id" : info.id,
									"name" : info.name,
									"email" : info.email,
									"locked" : info.locked
								}).success(function(data) {
									if (data.isSuccess == 'S') {
										$scope.getUsers();
										$('#editDiv').modal('hide');
									}
								});
							}

							$scope.deleteInfo = function(info) {
								var isConfirmDelete = confirm('Are you sure to delete this record?');
								if (isConfirmDelete) {
									$http({
										method : 'DELETE',
										url : 'user/delete/' + info.id
									}).success(function(data) {
										if (data.isSuccess == 'S') {
											$scope.getUsers();
										}
									}).error(function(data) {
										console.log(data);
										alert('Unable to delete');
									});
								} else {
									return false;
								}
							}

						} ]);
