angular.module('app').controller('cartController', function ($scope, $http, $location, $localStorage) {

    $scope.loadCart = function (){
        $http.get('http://localhost:5555/cart/api/v1/cart/'+$localStorage.springGuestCartId).then(function (response){
            $scope.cart = response.data;
        })
    }

    $scope.clearCart = function (){
        $http.get('http://localhost:5555/cart/api/v1/cart/'+$localStorage.springGuestCartId+'/clear').then(function (response){
            $scope.loadCart();
        })
    }


    $scope.deleteFromCart = function (productId){
        $http.get('http://localhost:5555/cart/api/v1/cart/'+$localStorage.springGuestCartId+'/delete/'+ productId).then(function (response){
            $scope.loadCart();
        })
    }


    $scope.addOrder = function (){
        $http.post('http://localhost:5555/core/api/v1/orders').then(function (response){
            $scope.clearCart();
        })
    }
    $scope.loadCart();


});