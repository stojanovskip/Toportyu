var currentStateFactory = require("./currentState");

describe("currentState", function() {

	var currentState;

	beforeEach(function() {
		currentState = currentStateFactory();
	});

	it("should succed", function() {
		expect(true).toBe(true);
	});

	it("should store current trip", function() {
		currentState.setCurrentTrip(1234);
		expect(currentState.getCurrentTrip()).toBe(1234);
	});

	it(".getCurrentTrip should return null initially", function() {
		expect(currentState.getCurrentTrip()).toBe(null);
	});

});
