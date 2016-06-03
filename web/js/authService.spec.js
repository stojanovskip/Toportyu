var AuthServiceFactory = require('./AuthService');

describe('AuthService', function (){

    var AuthService;
    var $httpMock;

    beforeEach(function () {
        $httpMock = {
            get:function () { },
            post:function () { }
        };

        spyOn($httpMock, 'get').and.returnValue(Promise.resolve({}));
        spyOn($httpMock, 'post').and.returnValue(Promise.resolve({}));

        AuthService = AuthServiceFactory($httpMock);
    });

    it('should call post of $http correctly for login', function(done){
        AuthService.login().then(function(){
            expect($httpMock.post).toHaveBeenCalledWith('/users/login',undefined);
            done();
        });
    });

    it('should call post of $http correctly for logout', function(done){
        AuthService.logout().then(function(){
            expect($httpMock.post).toHaveBeenCalledWith('/users/logout');
            done();
        });
    });

    it('post of $http should return the received data', function(done){
        var user = new Object();
        $httpMock.post.and.returnValue(Promise.resolve({
            data: user
        }));
        AuthService.login().then(function(result){
            expect(result).toBe(user);
            done();
        });
    });
    

    it('post of $http should propagate error', function(done){
        $httpMock.post.and.returnValue(Promise.reject('error'));
        AuthService.login().then(function(result){
            done.fail('The post of $http promise should have been rejected');
        }, function(error) {
            done();
        });
    });

});
