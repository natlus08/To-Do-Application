'use strict';

angular.module('todoapp').factory('todoservice',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                getMessage: getMessage,
                resetMessage: resetMessage,
                createItem: createItem,
                updateItem: updateItem,
                deleteItem: deleteItem,
                loadAllItems: loadAllItems,
                getAllItems: getAllItems,
                getItem: getItem
            };

            return factory;
            
            function getMessage(){
            	return $localStorage.message;
            }
            
            function resetMessage(){
            	$localStorage.message = '';
            }
            
            function createItem(item) {
                var deferred = $q.defer();
                $http.post(urls.API+'createitem', item)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateItem(item) {
                var deferred = $q.defer();
                $http.put(urls.API+'updateitem', item)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function deleteItem(id) {
                var deferred = $q.defer();
                $http.delete(urls.API+'deleteitem/' + id)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function loadAllItems() {
                var deferred = $q.defer();
                $http.get(urls.API+'getallitems')
                    .then(
                        function (response) {
                            $localStorage.items = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function getAllItems(){
                return $localStorage.items;
            }
            
            function getItem(id) {
                var deferred = $q.defer();
                $http.get(urls.API+'getitem/' + id)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
        }
    ]);