var angular = require("angular");
var app = angular.module("myApp");
app.factory("currentState", function () {
    var currentTrip = null;
    return {
        getCurrentTrip: function () {
            return currentTrip;
        },
        setCurrentTrip: function (selectedTrip) {
            currentTrip = selectedTrip;
        }
    };
});