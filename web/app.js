(function()
{
	var app = angular.module("myApp",[]);
	app.controller("OrderController", ['$http','$scope',function($http,$scope){
		$scope.orders=[];
		$scope.newOrder ={};
		$scope.testmessage = "asdas";
		$scope.state= "notLoaded";
		$scope.load=function(){

			$http.get('http://localhost:8000/orders').then(function(response){
				$scope.orders = response.data.orderList;
				console.log(response.data);
				$scope.state = "loaded";
			})};

		$scope.send = function(){

			$http.post('http://localhost:8000/orders',$scope.newOrder).then(function(response){
					$scope.testmessage = "success";
					$scope.orders.push($scope.newOrder);
					$scope.newOrder = {};
			}, function(response){
				$scope.testmessage = "fail";
			})
			
		};

		}]);
})();