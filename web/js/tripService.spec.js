var tripServiceFactory = require("./tripService");

describe("tripService", function() {

	var tripService;
	var $httpMock = {
		get: function() {}
	};

	beforeEach(function() {
		spyOn($httpMock, 'get');
		tripService = tripServiceFactory($httpMock);
	});

	it(".getTrips should return the result of $http.get", function() {
		var result = {};
		$httpMock.get.and.returnValue(result);
		expect(tripService.getTrips()).toBe(result);
	});

	it(".getTrips should call $http.get", function() {
		tripService.getTrips();
		expect($httpMock.get.calls.count()).toBe(1);
	});

	it(".getTrips should call '/trips' on $http service", function() {
		tripService.getTrips();
		expect($httpMock.get.calls.argsFor(0)).toEqual(['/trips']);
	});

});
