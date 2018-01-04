'use strict';

angular.module('todoapp').controller('todocontroller',
    ['todoservice', '$scope', function(todoservice, $scope) {

        var self = this;
        self.item = {};
        self.items = [];
        self.prepareDashboard = prepareDashboard;
        self.getMessage = getMessage;
        self.submitToDoForm = submitToDoForm;
        self.editItem = editItem;
        self.deleteItem = deleteItem;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        
        self.onlyIntegers = /^\d+$/;
        
        self.validateToDoForm = false;
        
        self.viewby = '5';

        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.item={};
            $scope.toDoForm.$setPristine();
        }
        
        function prepareDashboard(){
        	listItems();
        }
        
        function getMessage(){
        	self.successMessage = todoservice.getMessage();
        	resetMessage();
        }
        
        function resetMessage(){
        	todoservice.resetMessage();
        }
        
        function submitToDoForm() {
        	self.validateToDoForm = true;
        	if($scope.toDoForm.$valid){
        		self.validateToDoForm = false;
	            if (self.item.id === undefined || self.item.id === null) {
	                createItem(self.item);
	            } else {
	                updateItem(self.item, self.item.id);
	            }
			}
        }
        
        function createItem(item) {
            todoservice.createItem(item)
                .then(
                    function (response) {
                        self.successMessage = 'Item created successfully';
                        self.errorMessage='';
                        self.item={};
                        $scope.toDoForm.$setPristine();
                        listItems();
                    },
                    function (errResponse) {
                        self.errorMessage = 'Error while creating Item : ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateItem(item){
            todoservice.updateItem(item)
                .then(
                    function (response){
                        self.successMessage='Item updated successfully';
                        self.errorMessage='';
                        $scope.toDoForm.$setPristine();
                        listItems();
                    },
                    function(errResponse){
                        self.errorMessage='Error while updating Item - '+errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
        
        function deleteItem(id){
            todoservice.deleteItem(id)
                .then(
                    function (response){
                        self.successMessage='Item deleted successfully';
                        self.errorMessage='';
                        $scope.toDoForm.$setPristine();
                        listItems();
                    },
                    function(errResponse){
                        self.errorMessage='Error while deleting Item - '+errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
        
        function editItem(id) {
            self.successMessage='';
            self.errorMessage='';
            todoservice.getItem(id).then(
                function (item) {
                    self.item = item;
                },
                function (errResponse) {
                	
                }
            );
        }
        
        function listItems(){
        	todoservice.loadAllItems().then(
                function (response) {
                    self.items = todoservice.getAllItems();
                    
                    //pagination parameters
                    self.totalItems = self.items.length;
                    self.currentPage = 1;
                    self.itemsPerPage =  self.viewby;
                    self.maxSize = 5; //Number of pager buttons to show
                },
                function (errResponse) {
                	
                }
            );
        }
        
        //pagination code - begins
        self.setPage = function (pageNo) {
        	 self.currentPage = pageNo;
        };

        self.setItemsPerPage = function(num) {
        	 self.itemsPerPage = num;
        	 self.currentPage = 1; //reset to first paghe
        }
        //pagination code - ends
        
        //sorting - starts
        self.sortType = 'id';
        self.sortReverse = false;
        self.searchWord = ''; 
        //sorting - ends 
    }

    ]);