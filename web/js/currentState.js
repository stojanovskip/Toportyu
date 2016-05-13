function currentStateFactory() {
    var currentTrip = null;
    return {
        getCurrentTrip: function () {
            return currentTrip;
        },
        setCurrentTrip: function (selectedTrip) {
            currentTrip = selectedTrip;
        }
    };
}

currentStateFactory.install = function(app) {
	app.factory("currentState", currentStateFactory);
};

module.exports = currentStateFactory;
