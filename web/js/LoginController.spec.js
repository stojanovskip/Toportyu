var LoginController = require('./LoginController');

describe('LoginController', function () {
    var loginController;
    var $scope, authenticationService, eventHandler;

    beforeEach(function () {
        $scope = {
            inPopup: function () {
            }
        };
        authenticationService = {
            login: function () {
                return Promise.resolve({ username: 'name' });
            },
            logout: function () {
                return Promise.resolve();
            },
            getCurrentUser: function () {
                return Promise.resolve();
            },
            addOrders: function () {
                return Promise.resolve();
            }
        };
        eventHandler = {
            on: function () {}
        };

        spyOn(authenticationService, 'login').and.callThrough();
        spyOn(authenticationService, 'logout').and.callThrough();
        spyOn(authenticationService, 'addOrders').and.callThrough();
        spyOn(authenticationService, 'getCurrentUser').and.callThrough();
        spyOn(eventHandler, 'on').and.callThrough();

        loginController = LoginController($scope, authenticationService, eventHandler);
    });

    it('should check initial state of isLoginFailed', function () {
        expect($scope.isLoginFailed).toBe(false);
    });
    
    it('should check initial state of popupState', function () {
        expect($scope.popupState).toBe(false);
    });

    it('should check loginButtonVisible', function () {
        expect($scope.loginButtonVisible).toBe(true);
    });

    it('should check logoutButtonVisible', function () {
        expect($scope.logoutButtonVisible).toBe(false);
    });

    it('should listen to event authentication_required' , function () {
        expect(eventHandler.on).toHaveBeenCalledWith('authentication_required', jasmine.any(Function));
    });
    it('should call AuthenticationService.getCurrentUser() on initialization', function () {
        expect(authenticationService.getCurrentUser).toHaveBeenCalled();
    });

    it('should call authenticationService.login correctly', function (done) {
        $scope.login().then(function () {
            expect(authenticationService.login).toHaveBeenCalledWith({
                username:'',
                password:''
            });
            done();
        });
    });

    it('should show warning message if user is not accepted ', function (done) {
        authenticationService.login.and.returnValue(Promise.resolve({username: null}));
        Promise.resolve($scope.login()).then(function () {
            expect($scope.isLoginFailed).toBe(true);
            done();
        });
    });

    it('should be able to login after failing login', function (done) {
        authenticationService.login.and.returnValue(Promise.resolve({username: null}));
        Promise.resolve($scope.login()).then(function () {
            authenticationService.login.and.returnValue(Promise.resolve({username: 'name'}));
            Promise.resolve($scope.login()).then(function () {
                expect($scope.isLoginFailed).toBe(false);
                done();
            });
        });
    });


});