function CurrentStateFactory() {
    var currentTrip = null;
    var currentUser = null;
    return {
        getCurrentTrip: function () {
            return currentTrip;
        },
        setCurrentTrip: function (selectedTrip) {
            currentTrip = selectedTrip;
        },
        getCurrentUser: function () {
            return currentUser;
        },
        setCurrentUser: function (newUser) {
            currentUser = newUser;
        }
    };
}

CurrentStateFactory.install = function (app) {
    app.factory('CurrentState', CurrentStateFactory);
};

module.exports = CurrentStateFactory;
