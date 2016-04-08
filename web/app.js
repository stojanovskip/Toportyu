(function()
{
	var app = angular.module("myApp",[]);
	app.controller("OrderController", ['$http','$scope',function($http,$scope){
		$scope.orders=[];
		$scope.buttonText= "Load";
		$scope.orderToSend="";		
		$scope.load=function(){

			 var promise = $http.get('http://localhost:8000/orders').then(function(response){
				$scope.orders = response.data.orderList;
				console.log(response.data);
				$scope.buttonText = "Refresh";
			})};
		
		}]);
})();