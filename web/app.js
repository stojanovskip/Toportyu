(function()
{
	var app = angular.module("myApp",[]);
	app.controller("OrderController", ['$http','$scope',function($http,$scope){
		$scope.orders=[];
		$scope.newOrder ={};
		$scope.counter = 0;
		$scope.state= "notLoaded";
		$scope.load=function(){
			$http({method: 'GET', url: '/orders', headers: {
				'CurrentLength': $scope.orders.length}
			})
			.then(function(response){
				$scope.orders = response.data.orderList;
				console.log(response.data);
				$scope.state = "loaded";
				$scope.counter++;
			})};

			$scope.send = function(){
				if($scope.inputForm.cost.$valid&&$scope.inputForm.ordertext.$valid)
					$http.post('/orders',$scope.newOrder).then(function(response){
						$scope.orders.push($scope.newOrder);
						$scope.newOrder = {};
					}, function(response){
					})};

			}]);
})();