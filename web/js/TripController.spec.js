var TripController = require('./TripController');

describe('tripController', function () {
    var tripController;
    var $scope, tripService, currentState, authenticationService;

    beforeEach(function () {
        $scope = {
            $watch: function () {
            }
        };
        tripService = {
            getTrips: function () {
                return Promise.resolve([1, 2, 3]);
            },
            saveTrip: function () {
                return Promise.resolve([1, 2, 3]);
            }
        };
        authenticationService = {
            ensureUser: function () {
                return Promise.resolve();
            }
        };
        currentState = {};

        spyOn(tripService, 'getTrips').and.callThrough();
        spyOn(tripService, 'saveTrip').and.callThrough();
        spyOn(authenticationService, 'ensureUser').and.callThrough();

        tripController = TripController($scope, tripService, currentState, authenticationService);
    });

    it('should check initial state of selectedTrip', function () {
        expect($scope.selectedTrip).toBe(null);
    });

    it('should check initial state of showNewTrip', function () {
        expect($scope.showNewTrip).toBe(false);
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

    it('should call tripService.saveTrip', function (done) {
        $scope.newTrip.name = 'test';
        Promise.resolve($scope.saveTrip()).then(function () {
            authenticationService.ensureUser.and.returnValue(Promise.resolve());
            expect(tripService.saveTrip).toHaveBeenCalled();
            done();
        });
    });

    it('should fail to add trip without authentication', function (done) {
        $scope.newTrip.name = 'test';
        authenticationService.ensureUser.and.returnValue(Promise.reject());
        $scope.saveTrip().then(function () {
        }, function () {
            expect($scope.trips.length).toBe(3);
            done();
        });
    });

    it('saveTrip should push saved trip to $scope.trip', function (done) {
        $scope.newTrip.name = 'test';
        Promise.resolve($scope.saveTrip()).then(function () {
            authenticationService.ensureUser.and.returnValue(Promise.resolve());
            tripService.saveTrip($scope.newTrip).then(function () {
                expect($scope.trips.length).toBe(4);
                done();
            });
        });
    });


});