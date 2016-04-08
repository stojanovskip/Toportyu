(function()
{
	var app = angular.module("myApp",[]);
	app.controller("OrderController", ['$http','$scope',function($http,$scope){
		$scope.orders=[];
		$scope.state= "notLoaded";
		$scope.orderToSend="";		
		$scope.load=function(){

			 $http.get('http://localhost:8000/orders').then(function(response){
				$scope.orders = response.data.orderList;
				console.log(response.data);
				$scope.state = "loaded";
			})};
		
		}]);
})();