var angular = require("angular");
var app = angular.module("myApp");
app.controller("TripController", function ($scope, tripService, currentState) {
    $scope.trips = [];
    $scope.newTrip = {};
    $scope.selectedTrip = null;
    $scope.showNewTrip = false;
    
    $scope.loadTrips = function () {
        tripService.getTrips().then(function (response) {
            $scope.trips = response.data;
            if ($scope.selectedTrip != null) {
                $scope.selectedTrip = $scope.trips[$scope.trips.length - 1];
            }
        });
    };
    $scope.saveTrip = function () {
        if ($scope.newTrip.name != null) {
            tripService.saveTrip($scope.newTrip).then(function (response) {
                $scope.trips.push(response.data);
                $scope.selectedTrip = response.data;
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
});
