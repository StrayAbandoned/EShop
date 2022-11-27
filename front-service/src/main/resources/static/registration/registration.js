angular.module('app').controller('regController', function ($scope, $http, $localStorage, $location) {
    $scope.registrate = function (){
        $http.post('http://localhost:5555/auth/registration', $scope.reguser).then(function (response){
            if (response.data.token){
                $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                $localStorage.springUser = {username: $scope.reguser.username, token: response.data.token};
                $localStorage.reguser = null;
                $location.path('/')
            }

        });
    }

});