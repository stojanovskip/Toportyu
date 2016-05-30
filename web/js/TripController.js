function TripController($scope, tripService, currentState) {
    $scope.trips = [];
    $scope.newTrip = {};
    $scope.selectedTrip = null;
    $scope.showNewTrip = false;

    $scope.loadTrips = function () {
        tripService.getTrips().then(function (trips) {
            $scope.trips = trips;
            if ($scope.selectedTrip !== null) {
                $scope.selectedTrip = $scope.trips[$scope.trips.length - 1];
            }
        });
    };
    $scope.saveTrip = function () {
        if ($scope.newTrip.name !== undefined) {
            tripService.saveTrip($scope.newTrip).then(function (savedTrip) {
                $scope.trips.push(savedTrip);
                $scope.selectedTrip = savedTrip;
                $scope.newTrip = {};
                $scope.onChangeTrip();
            });
        }
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
    app.controller('TripController', TripController);
};

module.exports = TripController;