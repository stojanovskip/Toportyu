function User() {
	this.name = "my name";
}

var Users = {
	create: function() {
		return new User();
	}
};

module.exports = Users;
