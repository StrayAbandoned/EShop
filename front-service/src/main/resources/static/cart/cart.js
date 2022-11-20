angular.module('app').controller('cartController', function ($scope, $http, $location, $localStorage) {

    $scope.loadCart = function (){
        $http.get('http://localhost:5555/cart/api/v1/cart').then(function (response){
            $scope.cart = response.data;
        })
    }

    $scope.clearCart = function (){
        $http.get('http://localhost:5555/cart/api/v1/cart/clear').then(function (response){
            $scope.loadCart();
        })
    }


    $scope.deleteFromCart = function (productId){
        $http.get('http://localhost:5555/cart/api/v1/cart/delete/'+ productId).then(function (response){
            $scope.loadCart();
        })
    }

    $scope.createOrder = function () {
        $http.post('http://localhost:5555/core/api/v1/orders')
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.addOrder = function (){
        $http.get('http://localhost:5555/core/api/v1/order').then(function (response){
            $scope.clearCart();
        })
    }
    $scope.loadCart();


});