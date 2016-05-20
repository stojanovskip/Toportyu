var angular = require("angular");
var app = angular.module("KerkyraApp");
app.factory("tripService", function ($http) {
    return {
        getTrips: function () {
            return $http.get("/trips");
        },
        saveTrip: function (newTrip) {
            return $http.post("/trips", newTrip);
        }
    };
});