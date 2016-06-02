var TripServiceFactory = require('./TripService');

describe('TripService', function (){

    var TripService;
    var $httpMock;

    beforeEach(function () {
        $httpMock = {
            get:function () { },
            post:function () { }
        };

        spyOn($httpMock, 'get').and.returnValue(Promise.resolve({}));
        spyOn($httpMock, 'post').and.returnValue(Promise.resolve({}));

        TripService = TripServiceFactory($httpMock);
    });

    it('should call get of $http correctly', function(done){
       TripService.getTrips().then(function(){
            expect($httpMock.get).toHaveBeenCalledWith('/trips');
            done();
       });
    });

    it('should call post of $http correctly', function(done) {
       TripService.saveTrip().then(function(){
            expect($httpMock.post).toHaveBeenCalledWith('/trips', undefined);
            done();
       });
    });

    it('get of $http should return the received data', function(done){
       var trips = new Object();
       $httpMock.get.and.returnValue(Promise.resolve({
         data: trips
       }));
       TripService.getTrips().then(function(result){
            expect(result).toBe(trips);
            done();
       });
    });

    it('get of $http should propagate error', function(done){
       $httpMock.get.and.returnValue(Promise.reject('error'));
       TripService.getTrips().then(function(result){
            done.fail('The $http.get promise should have been rejected');
       }, function(error) {
            done();
       });
    });

     it('post of $http should propagate error', function(done){
           $httpMock.post.and.returnValue(Promise.reject('error'));
           TripService.saveTrip().then(function(result){
                done.fail('The post of $http promise should have been rejected');
           }, function(error) {
                done();
           });
     });

});
