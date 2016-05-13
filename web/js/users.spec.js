var Users = require("./users");

describe("Users.create", function() {
	it("should return an object", function() {
		expect(typeof Users.create()).toBe("object");
	});
	it("should return a user with name", function() {
		expect(typeof Users.create().name).toBe("string");
	});
});
