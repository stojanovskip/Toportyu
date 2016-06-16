var AuthenticationServiceFactory = require('./AuthenticationService');

describe('authenticationService', function () {
    var authenticationService;
    var $httpMock = {
        get: function () {
        },
        post: function () {
        }
    };
    var eventHandler = {
        broadcast: function () {
        },
        on: function () {
        }
    };

    beforeEach(function () {
        spyOn(eventHandler, 'broadcast').and.callThrough();
        spyOn(eventHandler, 'on').and.callThrough();
        spyOn($httpMock, 'get').and.returnValue(Promise.resolve({}));
        spyOn($httpMock, 'post').and.returnValue(Promise.resolve({}));
        authenticationService = AuthenticationServiceFactory($httpMock, eventHandler);
    });

    it('should call method getCurrentUser correctly', function (done) {
        authenticationService.getCurrentUser().then(function () {
            expect($httpMock.get).toHaveBeenCalledWith('/api/users/current');
            done();
        });
    });

    it('login should call get $http correctly', function (done) {
        var user = {username: 'testUser', password: 'testPass'};
        authenticationService.login(user).then(function () {
            expect($httpMock.post).toHaveBeenCalledWith('/api/users/login', user);
            done();
        });
    });

    it('logout should call post of $http correctly', function (done) {
        authenticationService.logout().then(function () {
            expect($httpMock.post).toHaveBeenCalledWith('/api/users/logout');
            done();
        });
    });

    it('should broadcast authentication_required', function (done) {
        var user = {username: null};
        spyOn(authenticationService, 'getCurrentUser').and.returnValue(Promise.resolve(user));
        authenticationService.ensureUser().then(function () {
            expect(eventHandler.broadcast).toHaveBeenCalledWith('authentication_required');
            done();
        });
    });

    it('should call after_login in login() function', function (done) {
        var user = {username: 'testUser', password: 'testPass'};
        authenticationService.login(user).then(function () {
            expect(eventHandler.broadcast).toHaveBeenCalledWith('after_login');
            done();
        });
    });

    it('should call after_logout in logout() function', function (done) {
        authenticationService.logout().then(function () {
            expect(eventHandler.broadcast).toHaveBeenCalledWith('after_logout');
            done();
        });
    });

});