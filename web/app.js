(function()
{
	var app = angular.module("myApp",[]);
	app.controller("UserController", ['$http',function($http){
	var store = this;
	this.users=[];
	this.loaded=false;

	this.load=function(){
	$http.get('http://jsonplaceholder.typicode.com/posts').success(function(data){
		store.users = data;
		console.log(data);
		this.loaded=true;
	})};	
	}]);
})();