(function()
{
	var app = angular.module("myApp",[]);
	app.controller("OrderController", ['$http',function($http){
		var store = this;
		this.orders=[];
		this.buttonText= "Load";
		this.load=function(){
			$http.get('http://localhost:8000/orders').success(function(data){
				store.orders = data.orderList;
				console.log(data);
				store.buttonText = "Refresh";
			})};	
		}]);
})();