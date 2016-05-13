function tripServiceFactory($http) {
    return {
        getTrips: function () {
            return $http.get("/trips");
        },
        saveTrip: function (newTrip) {
            return $http.post("/trips", newTrip);
        }
    };
}

tripServiceFactory.install = function(app) {
	app.factory("tripService", tripServiceFactory);
};

module.exports = tripServiceFactory;
