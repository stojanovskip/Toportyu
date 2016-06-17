function AuthenticationServiceFactory($http, eventHandler) {

    return {
        login: function (user) {
            return $http.post('/api/users/login', {
                username: user.username,
                password: user.password
            }).then(function (res) {
                eventHandler.broadcast('after_login');
                return res.data;
            });
        },
        logout: function () {
            return $http.post('/api/users/logout').then(function () {
                eventHandler.broadcast('after_logout');
            });
        },
        getCurrentUser: function () {
            return $http.get('/api/users/current').then(function (res) {
                return res.data;
            });
        },
        ensureUser: function () {
            return this.getCurrentUser().then(function (user) {
                if (user.username !== null) {
                    return user;
                }
                else {
                    eventHandler.broadcast('authentication_required');
                    return null;
                }
            });
        }
    };
}
AuthenticationServiceFactory.install = function (app) {
    app.factory('authenticationService', AuthenticationServiceFactory);
};
module.exports = AuthenticationServiceFactory;