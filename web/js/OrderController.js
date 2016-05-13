function OrderController($scope, orderService, currentState) {
    $scope.orders = [];
    $scope.newOrder = {};
    $scope.showOrders = false;

    $scope.getOrders = function () {
        return orderService.ordersByTrip($scope.selectedTrip).then(function(orders) {
            $scope.orders = orders;
        });
    };

    $scope.saveOrder = function () {
        if($scope.newOrder.cost != null&&$scope.newOrder.content!=null){
            $scope.newOrder.trip = $scope.selectedTrip;
            return orderService.saveOrder($scope.newOrder).then(function () {
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
}

OrderController.install = function(app) {
    app.controller("OrderController", OrderController);
};

module.exports = OrderController;
