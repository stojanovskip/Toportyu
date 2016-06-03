var TripController = require('./TripController');

describe('TripController', function () {
    var tripController;
    var $scope, tripService, currentState;
    beforeEach(function () {
        $scope = {
            $watch: function () {}
        };
        tripService = {
            getTrips: function () {
                return Promise.resolve([1, 2, 3,]);
            },
            saveTrip: function () {
                return Promise.resolve([1, 2, 3]);
            }
        };
        currentState = {
            getCurrentUser : function(){},
            setCurrentUser : function(){}

        };
        $scope.newOrder = {};
        spyOn(tripService, 'getTrips').and.callThrough();
        spyOn(tripService, 'saveTrip').and.callThrough();

        tripController = TripController($scope, tripService, currentState);
    });

    it('should call tripService.getTrips', function () {
        $scope.loadTrips();
        expect(tripService.getTrips).toHaveBeenCalled();
    });

    it('should update $scope.trips', function () {
        Promise.resolve($scope.loadTrips()).then(function () {
            expect($scope.orders.length).not.toBe(0);
        });
    });

    it('should not call tripService.saveTrip when no new trip', function () {
        $scope.saveTrip();
        expect(tripService.saveTrip).not.toHaveBeenCalled();
    });

    it('should call tripService.saveTrip', function () {
        $scope.newTrip.name = 'test';
        $scope.saveTrip();
        expect(tripService.saveTrip).toHaveBeenCalled();
    });

    it('saveTrip should push saved trip to $scope.trip', function () {
        Promise.resolve($scope.saveTrip()).then(function () {
            expect($scope.trips.length).not.toBe(0);
        });
    });


});