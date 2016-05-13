var angular = require("angular");
var app = angular.module("myApp", []);
app.controller("OrderController", function ($scope, orderService, currentState) {
    $scope.orders = [];
    $scope.newOrder = {};
    $scope.showOrders = false;

    $scope.getOrders = function () {
        orderService.ordersByTrip($scope.selectedTrip).then(function (response) {
            $scope.orders = response.data;
            console.log($scope.orders);
        });
    };

    $scope.saveOrder = function () {
    	if($scope.newOrder.cost != null&&$scope.newOrder.content!=null){
            $scope.newOrder.trip = $scope.selectedTrip;
            orderService.saveOrder($scope.newOrder).then(function () {
                $scope.orders.push($scope.newOrder);
                $scope.newOrder = {};
            })
        }
    };

    $scope.$watch(function () {
        return currentState.getCurrentTrip();
    }, function (selectedTrip) {
        $scope.selectedTrip = currentState.getCurrentTrip();
        if (selectedTrip != null) {
            $scope.getOrders();
            $scope.showOrders = true;
        }
    });
});
