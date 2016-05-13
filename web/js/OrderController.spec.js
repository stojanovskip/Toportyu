var OrderController = require("./OrderController");

describe("OrderController", function() {

	var orderController;
	var $scope, orderService, currentState;

	beforeEach(function() {
		$scope = {
			$watch: function() {}
		};
		orderService = {
			ordersByTrip: function() {
				return Promise.resolve([1,2,3]);
			}
		};
		currentState = {};
		spyOn(orderService, "ordersByTrip").and.callThrough();
		orderController = new OrderController($scope, orderService, currentState);
	});

	it("getOrders should call orderService.ordersByTrip", function() {
		$scope.getOrders();
		expect(orderService.ordersByTrip).toHaveBeenCalled();
	});

	it("getOrders should put the result in the scope", function(done) {
		Promise.resolve($scope.getOrders()).then(function() {
			expect($scope.orders.length).not.toBe(0);
			done();
		});
	});

});
