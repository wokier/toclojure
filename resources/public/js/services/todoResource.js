"use strict";

todomvc.factory('todoResource', function ($resource) {
	return $resource('api/todos', {}, {
		getTodos : {
			method: 'GET',
			isArray: true
		}  ,
		updateTodos : {
			method: 'POST',
			isArray: true
		}
	});
});