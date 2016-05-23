var OrderServiceFactory = require('./OrderService');

describe('OrderService',function(){

    var OrderService;

    var $httpMock = {
        get:function () {}
    };

    beforeEach(function(){
        spyOn($httpMock,'get');
        OrderService = OrderServiceFactory($httpMock);
    });

    it('should call get of $http',function(){
       Promise.resolve(OrderService.ordersByTrip).then(function(done){
           expect($httpMock.get).toHaveBeenCalled();
           done();
       });
    });

});