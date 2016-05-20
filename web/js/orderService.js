var angular = require("angular");
var app = angular.module("Kerkyra");
app.factory("orderService", function ($http) {
    return {
        ordersByTrip: function (selectedTrip) {
            return $http.get("/trips/" + selectedTrip.id.toString() + "/orders");
        },
        saveOrder: function (newOrder) {
            return $http.post("/orders", newOrder);
        }
    };
});