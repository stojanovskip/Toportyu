CurrentStateFactory = function () {
    var currentTrip = null;
    return {
        getCurrentTrip: function () {
            return currentTrip;
        },
        setCurrentTrip: function (selectedTrip) {
            currentTrip = selectedTrip;
        }
    };
};

CurrentStateFactory.install = function (app) {
    app.factory('currentState', CurrentStateFactory);
};

module.exports = CurrentStateFactory;
