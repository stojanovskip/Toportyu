function OrderController ($scope, orderService, currentState) {
    $scope.orders = [];
    $scope.newOrder = {};
    $scope.showOrders = false;

    $scope.getOrders = function () {
        if (currentState.getCurrentTrip() !== null) {
            return orderService.ordersByTrip(currentState.getCurrentTrip()).then(function (orders) {
                $scope.orders = orders;
            });
        }
    };

    $scope.saveOrder = function () {
        if ($scope.newOrder.cost !== undefined && $scope.newOrder.content !== undefined) {
            $scope.newOrder.trip = currentState.getCurrentTrip();
            return orderService.saveOrder($scope.newOrder).then(function (respondedOrder) {
                $scope.orders.push(respondedOrder);
                $scope.newOrder = {};
            });
        }
    };

    $scope.$watch(function () {
        return currentState.getCurrentTrip();
    }, function (selectedTrip) {
        if (selectedTrip !== null) {
            $scope.getOrders();
            $scope.showOrders = true;
        }
    });
}

OrderController.install = function (app) {
    app.controller('OrderController', OrderController);
};
module.exports = OrderController;