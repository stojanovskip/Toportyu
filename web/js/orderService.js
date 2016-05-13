var angular = require("angular");
var app = angular.module("myApp");
app.factory("orderService", function ($http) {
    return {
        ordersByTrip: function (selectedTrip) {
            return $http.get("/trips/" + selectedTrip.id.toString() + "/orders")
            	.then(function(response) {
            		return response.data;
            	});
        },
        saveOrder: function (newOrder) {
            return $http.post("/orders", newOrder);
        }
    };
});