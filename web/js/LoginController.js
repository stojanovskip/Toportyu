function LoginController($scope, AuthenticationService, EventHandler) {

    $scope.user = {
        username: '',
        password: ''
    };

    $scope.isLoginFailed = false;
    $scope.popupState = false;

    $scope.loginButtonVisible = true;
    $scope.logoutButtonVisible = false;

    $scope.login = function () {
        $scope.isLoginFailed = false;
        return AuthenticationService.login($scope.user).then(function (res) {
            if (res.username !== null) {
                AuthenticationService.addOrders().then(function () {
                });
                $scope.loginButtonVisible = false;
                $scope.logoutButtonVisible = true;
                $scope.closePopup();
            }
            else {
                $scope.isLoginFailed = true;
            }
        });
    };

    $scope.logout = function () {
        return AuthenticationService.logout().then(function () {
            $scope.loginButtonVisible = true;
            $scope.logoutButtonVisible = false;
            AuthenticationService.removeOrders().then(function () {
            });
        });
    };

    EventHandler.on('authentication_required', function () {
        $scope.inPopup();
    });

    $scope.inPopup = function () {
        $scope.popupState = true;
    };
    $scope.closePopup = function () {
        $scope.popupState = false;
    };

    AuthenticationService.getCurrentUser().then(function (newUser) {
        if (newUser.username !== null) {
            $scope.user = newUser;
            $scope.loginButtonVisible = false;
            $scope.logoutButtonVisible = true;
        }
        else {
            $scope.loginButtonVisible = true;
            $scope.logoutButtonVisible = false;
        }
    });
    
}
LoginController.install = function (app) {
    app.controller('LoginController', LoginController);
};
module.exports = LoginController;
