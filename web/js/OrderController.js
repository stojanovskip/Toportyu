function OrderController ($scope, OrderService, CurrentState) {
    $scope.orders = [];
    $scope.newOrder = {};
    $scope.showOrders = false;
    $scope.loggedIn = false;

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
            $scope.newOrder.user = CurrentState.getCurrentUser();
            return OrderService.saveOrder($scope.newOrder).then(function (respondedOrder) {
                $scope.orders.push(respondedOrder);
                $scope.newOrder = {};
            });
        }
    };
    $scope.$watch(function () {
        return CurrentState.getCurrentUser();
    }, function (currentUser) {
        if (currentUser !== null) {
            $scope.getOrders();
            $scope.showOrders = true;
        }
    });

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
