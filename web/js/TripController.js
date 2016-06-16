function TripController($scope, tripService, currentState, authenticationService) {
    $scope.trips = [];
    $scope.newTrip = {
        name: null,
        id: null
    };

    $scope.selectedTrip = null;
    $scope.showNewTrip = false;

    $scope.loadTrips = function () {
        return tripService.getTrips().then(function (trips) {
            $scope.trips = trips;

            if ($scope.selectedTrip !== null) {
                $scope.selectedTrip = trips[trips.length - 1];
            }
        });
    };
    $scope.saveTrip = function () {
        return authenticationService.ensureUser().then(function (res) {
            if ($scope.newTrip.name !== null) {
                tripService.saveTrip($scope.newTrip).then(function (savedTrip) {
                    $scope.trips.push(savedTrip);
                    $scope.selectedTrip = savedTrip;
                    $scope.newTrip = {};
                    $scope.onChangeTrip();
                });
            }
        });
    };

    $scope.onChangeTrip = function () {
        currentState.setCurrentTrip($scope.selectedTrip);
    };
    $scope.loadTrips();

    $scope.addPressed = function () {
        $scope.showNewTrip = true;
    };
}

TripController.install = function (app) {
    app.controller('tripController', TripController);
};

module.exports = TripController;
