angular.module('example', ['exampleServices'])

    .config(function($routeProvider) {
        $routeProvider
            .when('/', {controller:ListCtrl, templateUrl: '/assets/views/users/list.html'})
            .when('/detail/:userId', {controller:DetailCtrl, templateUrl: '/assets/views/users/detail.html'})
            .when('/login', {controller:LoginCtrl, templateUrl: '/assets/views/auth/login.html'})
    })

/* *
*
* Controllers
*
* -- ListCtrl manages the calls to listing of users
* -- DetailCtrl manages showing user details
*
* */

 function ListCtrl($scope, $location, User) {
    User.query(
        function(success) {
            $scope.users = success.users
        }, function(error) {
            if(error.status = 401) {
                $location.path("/login")
            }
        }
    )
}

function DetailCtrl($scope, $location, $routeParams, User) {
    User.get({userId: $routeParams.userId},
        function(success) {
            $scope.user = success
        }, function(error) {
            if(error.status = 401) {
                $location.path("/login")
            }
        }
    )
}

function LoginCtrl($scope, $location, Authentication) {
    $scope.authErr = ""
    $scope.authentication = { username: "", password: "" }

    $scope.submit = function() {
        Authentication.save($scope.authentication, function(success) {
            $location.path("/")
        }, function(error) {
            $scope.authErr = error
        })
    }
}