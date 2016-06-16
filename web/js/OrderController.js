function OrderController($scope, orderService, currentState, authenticationService, eventHandler) {
    $scope.orders = [];
    $scope.newOrder = {};
    $scope.showOrders = false;
    $scope.showInputOrder = false;

    $scope.getOrders = function () {
        if (currentState.getCurrentTrip() !== null) {
            return orderService.ordersByTrip(currentState.getCurrentTrip()).then(function (orders) {
                $scope.orders = orders;
            });
        }
    };

    $scope.saveOrder = function () {
        return authenticationService.ensureUser().then(function (res) {
            if ($scope.newOrder.cost !== undefined && $scope.newOrder.content !== undefined) {
                $scope.newOrder.trip = currentState.getCurrentTrip();
                return orderService.saveOrder($scope.newOrder).then(function (respondedOrder) {
                    $scope.orders.push(respondedOrder);
                    $scope.newOrder = {};
                });
            }
        });
    };

    $scope.hideOrder = function () {
        $scope.showInputOrder = false;
    };

    $scope.showOrder = function () {
        $scope.showInputOrder = true;
    };

    eventHandler.on('after_logout', function () {
        $scope.hideOrder();
    });

    eventHandler.on('after_login', function () {
        if (currentState.getCurrentTrip() !== null) $scope.showOrder();
    });

    $scope.$watch(function () {
        return currentState.getCurrentTrip();
    }, function (selectedTrip) {
        if (selectedTrip !== null) {
            $scope.getOrders();
            $scope.showOrders = true;
            authenticationService.getCurrentUser().then(function (newUser) {
                $scope.showInputOrder = newUser.username !== null;
            });
        }
    });
}

OrderController.install = function (app) {
    app.controller('orderController', OrderController);
};
module.exports = OrderController;
