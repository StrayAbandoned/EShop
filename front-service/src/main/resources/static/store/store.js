angular.module('app').controller('storeController', function ($scope, $http, $localStorage) {
    $scope.loadProducts = function (pageIndex = 1){
        $http({
            url: 'http://localhost:5555/core/api/v1/products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response){
            $scope.productsPage = response.data;
            $scope.productsList = response.data.items;
            $scope.generatePages($scope.productsPage.totalPages);
        });
    };



    $scope.addToCart = function (productId){
        $http.get('http://localhost:5555/cart/api/v1/cart/'+$localStorage.springGuestCartId+'/add/'+ productId).then(function (response){
        })
    }
    $scope.generatePages = function (totalPages){
        out = [];
        for (let i = 0; i < totalPages; i++) {
            out.push(i + 1);
        }
        $scope.pagesList = out;
    }

    $scope.loadProducts();
});