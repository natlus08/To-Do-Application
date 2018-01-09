angular.module('todoapp').controller('TodoCtrl',
		['todoservice', '$scope', function(todoservice, $scope) {

			$scope.items = [];

			$scope.addItem = function() {
				var item = {
						'description' : $scope.desc,
						'status' : false
				};
				todoservice.createItem(item).then(function(response) {
					$scope.items.push(response);
					$scope.desc = '';
				}, function(errResponse) {
				
				});

			};

			$scope.clearCompleted = function() {
				todoservice.deleteItems().then(function(response) {
					$scope.items = _.filter($scope.items, function(item) {
						return !item.status;
					});
				}, function(errResponse) {
				
				});
			};

			$scope.updateItem = function(item) {
				todoservice.updateItem(item).then(function(response) {

				}, function(errResponse) {
				
				});
			}

			$scope.loadPage = function() {
				listItems();
			}

			function listItems() {
				todoservice.loadAllItems().then(function(response) {
					var resp = todoservice.getAllItems();
					if (resp === '') {
						$scope.items = [];
					} else {
						$scope.items = resp;
					}
				}, function(errResponse) {

				});
			}
		}]);