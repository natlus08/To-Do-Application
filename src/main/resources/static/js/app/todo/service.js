'use strict';

angular.module('todoapp').factory('todoservice',
    ['$localStorage', '$http', '$q', '$location',
        function ($localStorage, $http, $q, $location) {
    	
    		var apiContextPath = $location.absUrl()+'/api/';

            var factory = {
                createItem: createItem,
                updateItem: updateItem,
                deleteItems: deleteItems,
                loadAllItems: loadAllItems,
                getAllItems: getAllItems,
            };

            return factory;
            
            function createItem(item) {
                var deferred = $q.defer();
                $http.post(apiContextPath+'createitem', item)
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
                $http.put(apiContextPath+'updateitem', item)
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
            
            function deleteItems() {
                var deferred = $q.defer();
                $http.delete(apiContextPath+'deleteitems')
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
                $http.get(apiContextPath+'getallitems')
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
        }
    ]);