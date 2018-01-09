angular.module('todoapp').controller('TodoCtrl',
    ['todoservice','$scope', function(todoservice, $scope) {

  $scope.items = [];
 
  $scope.addItem = function () {
	  var item = {'description' : $scope.desc,
			  	   'status' : false
			  	  };
	  todoservice.createItem(item)
      .then(
          function (response) {
              listItems();
              $scope.desc = '';
          },
          function (errResponse) {
          }
      );
    
  };
  
    $scope.clearCompleted = function () {
    	 todoservice.deleteItems()
         .then(
             function (response){
                 listItems();
             },
             function(errResponse){
             }
         );
    };
    
    $scope.updateItem  = function(item){
        todoservice.updateItem(item)
            .then(
                function (response){
                    listItems();
                },
                function(errResponse){
                }
            );
    }
    
    $scope.loadPage = function(){
    	listItems();
    }
    
    function listItems(){
    	todoservice.loadAllItems().then(
            function (response) {
            	$scope.items = todoservice.getAllItems();
            },
            function (errResponse) {
            	
            }
        );
    }
}]);