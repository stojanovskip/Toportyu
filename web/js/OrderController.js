function OrderController($scope, OrderService, CurrentState, AuthenticationService, EventHandler) {
    $scope.orders = [];
    $scope.newOrder = {};
    $scope.showOrders = false;
    $scope.showInputOrder = false;

    $scope.getOrders = function () {
        if (CurrentState.getCurrentTrip() !== null) {
            return OrderService.ordersByTrip(CurrentState.getCurrentTrip()).then(function (orders) {
                $scope.orders = orders;
            });
        }
    };

    $scope.saveOrder = function () {
       return AuthenticationService.ensureUser().then(function (res) {
            if ($scope.newOrder.cost !== undefined && $scope.newOrder.content !== undefined) {
                $scope.newOrder.trip = CurrentState.getCurrentTrip();
                return OrderService.saveOrder($scope.newOrder).then(function (respondedOrder) {
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
    EventHandler.on('after_logout', function () {
        $scope.hideOrder();
    });
    EventHandler.on('after_login', function () {
        if (CurrentState.getCurrentTrip() !== null)
        $scope.showOrder();
    });
    
    $scope.$watch(function () {
        return CurrentState.getCurrentTrip();
    }, function (selectedTrip) {
        if (selectedTrip !== null) {
            $scope.getOrders();
            $scope.showOrders = true;
            AuthenticationService.getCurrentUser().then(function (newUser) {
                if (newUser.username !== null) {
                    $scope.showInputOrder = true;
                }
                else {
                    $scope.showInputOrder = false;
                }
            });
        }
    });
}

OrderController.install = function (app) {
    app.controller('OrderController', OrderController);
};
module.exports = OrderController;
