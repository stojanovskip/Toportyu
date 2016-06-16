function AuthenticationServiceFactory($http, $q, EventHandler) {
    var Promise = $q;

    function getUser() {
        return $http.get('/api/users/current').then(function (res) {
            return res.data;
        });
    }

    return {
        login: function (user) {
            return $http.post('/api/users/login', {
                username: user.username,
                password: user.password
            }).then(function (res) {
                return res.data;
            });
        },
        logout: function () {
            return $http.post('/api/users/logout').then(function () {
            });
        },
        getCurrentUser: function () {
            return getUser();
        },
        ensureUser: function () {
            return getUser().then(function (user) {
                if (user.username !== null) {
                    return user;
                }
                else {
                    return new Promise(function () {
                        EventHandler.broadcast('authentication_required');
                    });
                }
            });
        },
        removeOrders: function () {
            return new Promise(function () {
                EventHandler.broadcast('after_logout');
            });
        },
        addOrders: function () {
            return new Promise(function () {
                EventHandler.broadcast('after_login');
            });
        }
    };
}
AuthenticationServiceFactory.install = function (app) {
    app.factory('AuthenticationService', AuthenticationServiceFactory);
};
module.exports = AuthenticationServiceFactory;