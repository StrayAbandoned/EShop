angular.module('app', []).controller('IndexController', function ($scope, $http){
   $scope.loadProducts = function (){
       $http.get('http://localhost:8082/market/api/v1/products').then(function (response){
           $scope.productsList = response.data;
       })
   }

   $scope.showProductInfo = function (productId) {
       $http.get('http://localhost:8082/market/api/v1/products/'+ productId).then(function (response){
           alert(response.data.title);
       })
   }
   $scope.deleteProduct = function (productId) {
       $http.delete('http://localhost:8082/market/api/v1/products/'+ productId).then(function (response){
           $scope.loadProducts();
       });
   }

   $scope.loadCart = function (){
       $http.get('http://localhost:8082/market/api/v1/cart').then(function (response){
           $scope.cart = response.data;
       });
   }
   $scope.addToCart = function (productId){
       $http.get('http://localhost:8082/market/api/v1/cart/add/'+ productId).then(function (response){
           $scope.loadCart();
       })
   }

   $scope.deleteFromCart = function (productId){
       $http.get('http://localhost:8082/market/api/v1/cart/delete/'+ productId).then(function (response){
           $scope.loadCart();
       })
   }

    $scope.loadProducts();
   $scope.loadCart();



});