function TripServiceFactory($http) {
    return {
        getTrips: function () {
            return $http.get('/trips').then(function(response){
                return response.data;
            });
        },
        saveTrip: function (newTrip) {
            return $http.post('/trips', newTrip).then(function(response){
                return response.data;
            });
        }
    };
}

TripServiceFactory.install = function (app) {
    app.factory('tripService', TripServiceFactory);
};

module.exports = TripServiceFactory;