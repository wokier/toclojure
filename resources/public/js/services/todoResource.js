"use strict";

todomvc.factory('todoResource', function ($resource) {
	return $resource('api/todos/:todoId',  {todoId:'@id'}, {
		getTodos : {
			method: 'GET',
			isArray: true
		}  ,
		updateTodos : {
			method: 'PUT',
			isArray: true
		}  ,
		deleteTodo : {
		    method: 'DELETE',
		    isArray: true
		}
	});
});