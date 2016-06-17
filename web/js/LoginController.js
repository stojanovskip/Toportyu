function LoginController($scope, authenticationService, eventHandler) {

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
        return authenticationService.login($scope.user).then(function (member) {
            if (member.username !== null) {
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
        return authenticationService.logout().then(function () {
            $scope.loginButtonVisible = true;
            $scope.logoutButtonVisible = false;
        });
    };

    eventHandler.on('authentication_required', function () {
        $scope.inPopup();
    });

    $scope.inPopup = function () {
        $scope.popupState = true;
    };
    $scope.closePopup = function () {
        $scope.popupState = false;
    };

    authenticationService.getCurrentUser().then(function (member) {
        if (member.username !== null) {
            $scope.user = member;
            $scope.loginButtonVisible = false;
            $scope.logoutButtonVisible = true;
        } else {
            $scope.loginButtonVisible = true;
            $scope.logoutButtonVisible = false;
        }
    });

}
LoginController.install = function (app) {
    app.controller('loginController', LoginController);
};
module.exports = LoginController;
