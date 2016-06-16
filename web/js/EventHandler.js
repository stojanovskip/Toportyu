EventHandlerFactory = function ($rootScope) {
    return {
        broadcast: function (message, state) {
            return $rootScope.$broadcast(message, state);
        },
        on: function (message, callback) {
            return $rootScope.$on(message, callback);
        }
    };
};

EventHandlerFactory.install = function (app) {
    app.factory('EventHandler', EventHandlerFactory);
};
module.exports = EventHandlerFactory;
