function AuthController ($scope, AuthService, CurrentState) {
    $scope.loggedIn = false;
    $scope.user = {};
    $scope.user.name = '';
    $scope.user.password = '';
    $scope.login = function () {
         return AuthService.login($scope.user).then(function (user) {
                CurrentState.setCurrentUser(user);
             $scope.loggedIn = true;
         });
    };


    $scope.logout = function () {
        return AuthService.logout().then(function () {
            CurrentState.setCurrentUser(null);
            $scope.loggedIn = false;
        });
    };
}

AuthController.install = function (app) {
    app.controller('AuthController', AuthController);
};
module.exports = AuthController;
