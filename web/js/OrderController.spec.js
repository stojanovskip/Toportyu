var OrderController = require('./OrderController');

describe('OrderController', function () {
    var orderController;
    var $scope, orderService, currentState;

    beforeEach(function () {
        $scope = {
            $watch: function () {
            }
        };
        orderService = {
            ordersByTrip: function () {
                return Promise.resolve([1, 2, 3,]);
            },
            saveOrder : function() {
                return Promise.resolve([1,2,3]);
            }
        };
        currentState = {
            getCurrentTrip : function(){},
            setCurrentTrip : function(){}
        };

        spyOn(orderService, 'ordersByTrip').and.callThrough();
        spyOn(orderService, 'saveOrder').and.callThrough();

        orderController = OrderController($scope, orderService, currentState);
    });

    it('should call orderService.ordersByTrip', function () {
        $scope.getOrders();
        expect(orderService.ordersByTrip).toHaveBeenCalled();
    });

    it('should update $scope.orders', function (done) {
        Promise.resolve($scope.getOrders()).then(function(){
            expect($scope.orders.length).not.toBe(0);
            done();
        });
    });

    it('should not call orderService.saveOrder when no new order', function () {
        $scope.saveOrder();
        expect(orderService.saveOrder).not.toHaveBeenCalled();
    });

    it('should call orderService.saveOrder', function () {
        $scope.newOrder = {content: 'test', cost: 100};
        $scope.saveOrder();
        expect(orderService.saveOrder).toHaveBeenCalled();
    });
    it('saveOrder should push saved order to $scope.orders', function (done) {
        $scope.newOrder.cost = 100;
        $scope.newOrder.content = 'test';
        Promise.resolve($scope.saveOrder()).then(function () {
            expect($scope.orders.length).toBe(1);
            done();
        });
    });

});