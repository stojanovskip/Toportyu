(function()
{
	var app = angular.module("myApp",[]);
	app.controller("OrderController", ['$http','$scope','$timeout',function($http,$scope,$timeout){
		$scope.orders=[];
		$scope.newOrder ={};
		$scope.counter = 0;
		$scope.state= "notLoaded";
		$scope.load=function(){
			$timeout(function(){
				$http.get('/orders').then(function(response){
				$scope.orders = response.data.orderList;
				console.log(response.data);
				$scope.state = "loaded";
				$scope.counter++;
				$scope.load();
			})},1000)};

		$scope.send = function(){
			$http.post('/orders',$scope.newOrder).then(function(response){
					$scope.orders.push($scope.newOrder);
					$scope.newOrder = {};
			}, function(response){
			})};

		}]);
})();