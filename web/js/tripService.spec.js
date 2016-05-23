var TripServiceFactory = require('./tripService');

describe('tripService', function () {
    var TripService;
    var $httpMock = {
        get: function(){}
    };
    beforeEach(function () {
        spyOn($httpMock, 'get');
        TripService = TripServiceFactory($httpMock);
    });

    it('should call get of $http',function(){
        Promise.resolve(TripService.getTrips).then(function(done){
            expect($httpMock.get).toHaveBeenCalled();
            done();
        });
    });

});