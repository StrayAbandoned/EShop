angular.module('app', ['ngStorage']).controller('IndexController', function ($scope, $http, $localStorage){
    if ($localStorage.springUser){
        try {
            let jwt = $localStorage.springUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime()/1000);
            if (currentTime>payload.exp){
                console.log("Token has expired");
                delete $localStorage.springUser;
                $http.defaults.headers.common.Authorization = '';

            }
        } catch (e){

        }
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springUser.token;
    }

    $scope.tryToAuth = function (){
       $http.post('http://localhost:8082/market/auth', $scope.user)
           .then(function successCallback(response) {
               if (response.data.token) {
                   $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                   $localStorage.springUser = {username: $scope.user.username, token: response.data.token};
                   $scope.user.username = null;
                   $scope.user.password = null;
               }
           }, function errorCallback(response){
           });
   }


   $scope.tryToLogout = function (){
       $scope.clearUser();
       $scope.user = null;
   }

   $scope.clearUser = function (){
       delete $localStorage.springUser;
       $http.defaults.headers.common.Authorization = '';
   }
   $scope.isLoggedIn = function (){
       if ($localStorage.springUser){
           return true;
       } else {
           return false;
       }
   }

    $scope.loadProducts = function (){
       $http.get('http://localhost:8082/market/api/v1/products').then(function (response){
           $scope.productsList = response.data;
       });
   }


   $scope.showProductInfo = function (productId) {
       $http.get('http://localhost:8082/market/api/v1/products/'+ productId).then(function (response){
           alert(response.data.title);
       })
   }
   $scope.deleteProduct = function (productId) {
       $http.delete('http://localhost:8082/market/api/v1/products/'+ productId).then(function (response){
           $scope.loadProducts();
       })
   }

   $scope.loadCart = function (){
       $http.get('http://localhost:8083/market-cart/api/v1/cart').then(function (response){
           $scope.cart = response.data;
       })
   }
   $scope.addToCart = function (productId){
       $http.get('http://localhost:8083/market-cart/api/v1/cart/add/'+ productId).then(function (response){
           $scope.loadCart();
       })
   }

   $scope.deleteFromCart = function (productId){
       $http.get('http://localhost:8083/market-cart/api/v1/cart/delete/'+ productId).then(function (response){
           $scope.loadCart();
       })
   }
    $scope.clearCart = function (){
        $http.get('http://localhost:8083/market-cart/api/v1/cart/clear').then(function (response){
            $scope.loadCart();
        })
    }
    $scope.addOrder = function (){
        $http.get('http://localhost:8082/market/api/v1/order').then(function (response){
            $scope.clearCart();
        })
    }

    $scope.loadProducts();
   $scope.loadCart();



});