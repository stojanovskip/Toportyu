function AuthServiceFactory($http){
    return {
        login: function (user) {
            return $http.post('/users/login',user).then(function(response){
                return response.data;
            });
        },
        logout: function () {
            return $http.post('/users/logout').then(function(response){
                return response.data;
            });
        }
    };
}

AuthServiceFactory.install = function(app) {
    app.factory('AuthService', AuthServiceFactory);
};

module.exports = AuthServiceFactory;