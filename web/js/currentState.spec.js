var currentStateFactory = require('./CurrentState');

describe('currentState', function () {

    var currentState;

    beforeEach(function () {
        currentState = currentStateFactory();
    });

    it('should store added trip', function () {
        currentState.setCurrentTrip('test');
        expect(currentState.getCurrentTrip()).toBe('test');
    });

    it('should return null initially', function () {
        expect(currentState.getCurrentTrip()).toBe(null);
    });

});
