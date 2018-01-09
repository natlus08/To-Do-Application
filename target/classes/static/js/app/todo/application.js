var todoapp = angular.module('todoapp',['ngStorage']);

todoapp.constant('urls', {
    BASE: 'http://localhost:8080/todo',
    API : 'http://localhost:8080/todo/api/'
});