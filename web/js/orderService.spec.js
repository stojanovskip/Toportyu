var OrderServiceFactory = require('./OrderService');

describe('OrderService', function(){

    var OrderService;

    var $httpMock = {
        get:function () {},
        post:function () {}
    };

    beforeEach(function (){
        spyOn($httpMock, 'get').and.returnValue(Promise.resolve({}));
        spyOn($httpMock, 'post').and.returnValue(Promise.resolve({}));
        OrderService = OrderServiceFactory($httpMock);
    });

    it('should call correctly get of $http', function(done){
        var testTrip = { id:1, name:'testTrip' };
        OrderService.ordersByTrip(testTrip).then(function(){
            expect($httpMock.get).toHaveBeenCalledWith('/trips/' + testTrip.id.toString() + '/orders');
            done();
        });
    });

    it('should call correctly post of $http', function(done){
        OrderService.saveOrder().then(function(){
            expect($httpMock.post).toHaveBeenCalledWith('/orders', undefined);
            done();
        });
    });

    it('get of $http should return the received data', function(done){
        var orders = new Object();
        var testTrip = { id:1, name:'testTrip' };
        $httpMock.get.and.returnValue(Promise.resolve({
           data: orders
        }));
        OrderService.ordersByTrip(testTrip).then(function(result){
            expect(result).toBe(orders);
            done();
        });
    });

    it('get of $http should propagate error', function(done){
        var testTrip = { id:1, name:'testTrip' };
        $httpMock.get.and.returnValue(Promise.reject('error'));
        OrderService.ordersByTrip(testTrip).then(function(result){
            done.fail('The $http.get promise should have been rejected');
        }, function(error) {
            done();
        });
    });

    it('post of $http should propagate error', function(done){
        $httpMock.post.and.returnValue(Promise.reject('error'));
        OrderService.saveOrder().then(function(result){
            done.fail('The post of $http promise should have been rejected');
        }, function(error) {
            done();
        });
    });
});