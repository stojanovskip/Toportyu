function OrderController ($scope, OrderService, CurrentState) {
    $scope.orders = [];
    $scope.newOrder = {};
    $scope.showOrders = false;

    $scope.getOrders = function () {
        if (CurrentState.getCurrentTrip() !== null) {
            return OrderService.ordersByTrip(CurrentState.getCurrentTrip()).then(function (orders) {
                $scope.orders = orders;
            });
        }
    };

    $scope.saveOrder = function () {
        if ($scope.newOrder.cost !== undefined && $scope.newOrder.content !== undefined) {
            $scope.newOrder.trip = CurrentState.getCurrentTrip();
            return OrderService.saveOrder($scope.newOrder).then(function (respondedOrder) {
                $scope.orders.push(respondedOrder);
                $scope.newOrder = {};
            });
        }
    };

    $scope.$watch(function () {
        return CurrentState.getCurrentTrip();
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
