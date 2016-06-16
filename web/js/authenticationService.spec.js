var AuthenticationServiceFactory = require('./AuthenticationService');

describe('AuthenticationService', function () {
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
    var $q = {};

    beforeEach(function(){
        spyOn(eventHandler, 'broadcast').and.callThrough();
        spyOn(eventHandler, 'on').and.callThrough();
        spyOn($httpMock, 'get').and.returnValue(Promise.resolve({}));
        spyOn($httpMock, 'post').and.returnValue(Promise.resolve({}));
        authenticationService = AuthenticationServiceFactory($httpMock,$q, eventHandler);
    });

    it('should call get $http correctly', function (done) {
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

    it('should broadcast something', function (done) {
        //getUser.and.returnValue(Promise.resolve({username: 'asdasd'}));
        Promise.resolve(authenticationService.ensureUser).then(function(){
            expect(eventHandler.broadcast).toHaveBeenCalledWith('authentication_required');
            done();
        });
    });
    it('should call removeOrders', function (done) {
        Promise.resolve(authenticationService.addOrders).then(function(){
            expect(eventHandler.broadcast).toHaveBeenCalledWith('after_login');
            done();
        });
    });
    it('should call removeOrders', function (done) {
        Promise.resolve(authenticationService.removeOrders).then(function(){
            expect(eventHandler.broadcast).toHaveBeenCalledWith('after_logout');
            done();
        });
    });
    it('logout should call post of $http correctly', function (done) {
        authenticationService.logout().then(function () {
            expect($httpMock.post).toHaveBeenCalledWith('/api/users/logout');
            done();
        });
    });
    it('get of $http should propagate error', function (done) {
        $httpMock.get.and.returnValue(Promise.reject('error'));
        authenticationService.getCurrentUser().then(function (result) {
            done.fail('The $http.get promise should have been rejected');
        }, function (error) {
            done();
        });
    });

    it('post of $http should propagate error', function (done) {
        $httpMock.post.and.returnValue(Promise.reject('error'));
        authenticationService.logout().then(function (result) {
            done.fail('The post of $http promise should have been rejected');
        }, function (error) {
            done();
        });
    });
});