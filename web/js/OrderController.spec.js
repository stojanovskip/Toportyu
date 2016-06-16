var OrderController = require('./OrderController');

describe('orderController', function () {
    var orderController;
    var $scope, orderService, currentState, authenticationService, eventHandler;

    beforeEach(function () {
        $scope = {
            $watch: function () {
            }
        };
        orderService = {
            ordersByTrip: function () {
                return Promise.resolve([1, 2, 3]);
            },
            saveOrder: function () {
                return Promise.resolve([1, 2, 3]);
            }
        };
        currentState = {
            getCurrentTrip: function () {
            },
            setCurrentTrip: function () {
            }
        };
        authenticationService = {
            ensureUser: function () {
                return Promise.resolve();
            }
        };
        eventHandler = {
            on: function () {
            }
        };

        spyOn(orderService, 'ordersByTrip').and.callThrough();
        spyOn(orderService, 'saveOrder').and.callThrough();
        spyOn(authenticationService, 'ensureUser').and.callThrough();
        spyOn(eventHandler, 'on').and.callThrough();

        orderController = OrderController($scope, orderService, currentState, authenticationService, eventHandler);
    });

    it('should check initial state of showOrders', function () {
        expect($scope.showOrders).toBe(false);
    });

    it('should check initial state of showInputOrder', function () {
        expect($scope.showInputOrder).toBe(false);
    });

    it('should listen to event after_logout', function () {
        expect(eventHandler.on).toHaveBeenCalledWith('after_logout', jasmine.any(Function));
    });

    it('should listen to event after_login', function () {
        expect(eventHandler.on).toHaveBeenCalledWith('after_login', jasmine.any(Function));
    });

    it('should call orderService.ordersByTrip', function () {
        $scope.getOrders();
        expect(orderService.ordersByTrip).toHaveBeenCalled();
    });

    it('should update $scope.orders', function (done) {
        Promise.resolve($scope.getOrders()).then(function () {
            expect($scope.orders.length).not.toBe(0);
            done();
        });
    });

    it('should not call orderService.saveOrder when no new order', function () {
        $scope.saveOrder();
        expect(orderService.saveOrder).not.toHaveBeenCalled();
    });

    it('should call orderService.saveOrder', function (done) {
        $scope.newOrder = {content: 'test', cost: 100};
        authenticationService.ensureUser.and.returnValue(Promise.resolve());
        Promise.resolve($scope.saveOrder()).then(function () {
            expect(orderService.saveOrder).toHaveBeenCalled();
            done();
        });
    });

    it('saveOrder should push saved order to $scope.orders', function (done) {
        $scope.newOrder = {content: 'test', cost: 100};
        authenticationService.ensureUser.and.returnValue(Promise.resolve());
        Promise.resolve($scope.saveOrder()).then(function () {
            Promise.resolve(orderService.saveOrder()).then(function () {
                expect($scope.orders.length).toBe(1);
                done();
            });
        });
    });

});