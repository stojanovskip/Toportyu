function TripController($scope, TripService, CurrentState) {
    $scope.trips = [];
    $scope.newTrip = {
        name: null,
        id: null
    };
    $scope.selectedTrip = null;
    $scope.showNewTrip = false;
    $scope.showOrders = false;

    $scope.loadTrips = function () {
        TripService.getTrips().then(function (trips) {
            $scope.trips = trips;
            if ($scope.selectedTrip !== null) {
                $scope.selectedTrip = $scope.trips[$scope.trips.length - 1];
            }
        });
    };
    $scope.saveTrip = function () {
        if ($scope.newTrip.name !== null) {
            $scope.newOrder.user = CurrentState.getCurrentUser();
            TripService.saveTrip($scope.newTrip).then(function (savedTrip) {
                $scope.trips.push(savedTrip);
                $scope.selectedTrip = savedTrip;
                $scope.newTrip = {};
                $scope.onChangeTrip();
            });
        }
    };
    $scope.$watch(function () {
        return CurrentState.getCurrentUser();
    }, function (currentUser) {
        if (currentUser !== null) {
            $scope.showOrders = true;
        }
    });
    $scope.onChangeTrip = function () {
        CurrentState.setCurrentTrip($scope.selectedTrip);
    };
    $scope.loadTrips();

    $scope.addPressed = function () {
        $scope.showNewTrip = true;
    };
}

TripController.install = function (app) {
    app.controller('TripController', TripController);
};

module.exports = TripController;
