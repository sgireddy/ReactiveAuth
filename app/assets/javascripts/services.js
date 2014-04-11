angular.module('exampleServices', ['ngResource'])

    .factory('User', function ($resource) {
        return $resource('/users/:userId', {}, {
            query: { method: 'GET' }
        });
    })

    .factory('Authentication', function ($resource) {
        return $resource('/authenticate/userpass', {});
    });