(function()
{
	var app = angular.module("myApp",[]);
	app.controller("OrderController", ['$http','$scope',function($http,$scope){
		$scope.orders=[];
		$scope.newOrder ={};
		$scope.toShowOrders = false;
		
		$scope.trips=[];
		$scope.newTrip={};
		$scope.toShowTrips = false;
		$scope.selectedTrip = null;
		
		$scope.counter = 0;
		$scope.state= "notLoaded";
		
		$scope.loadOrder=function(selectedTrip){
			$http({method: 'GET', url: '/orders/byTrip/' + selectedTrip.id.toString(), headers: {
				'CurrentLength': $scope.orders.length}
			})
			.then(function(response){
				$scope.orders = response.data;
				console.log(response.data);
				$scope.state = "loaded";
				$scope.counter++;
			})			
			$scope.toShowOrders = true;
		};
			
		$scope.showTrips = function(){
			$scope.toShowTrips = true;
		};
		
		$scope.sendOrder= function(newOrder,selectedTrip){
			$scope.newOrder.trip = $scope.selectedTrip;
			if (newOrder.content != null && newOrder.cost != null)
					$http.post('/orders',$scope.newOrder).then(function(response){
						$scope.orders.push($scope.newOrder);
						$scope.newOrder = {};
					}, function(response){
					})};

	    $scope.sendTrip = function(){
			if ($scope.newTrip.name != null)
			$http.post('/trips',$scope.newTrip).then(function(response){
						$scope.trips.push($scope.newTrip);
						$scope.selectedTrip = $scope.newTrip;
						$scope.newTrip = {};
						$scope.loadTrips();
						$scope.orders = [];
					}, function(response){
					})
					$scope.toShowOrders = true;
					$scope.toShowTrips = false;					
					if ($scope.selectedTrip!=null)
					document.getElementById("type").remove();
					};		
					
		$scope.loadTrips=function(){
			$http({method: 'GET', url: '/trips', headers: {'CurrentLength': $scope.trips.length}})
			.then(function(response){
				$scope.trips = response.data;
				if ($scope.selectedTrip!=null)
				$scope.selectedTrip = $scope.trips[$scope.trips.length-1];
				console.log(response.data);
			})
			};	
			
		}]);
})();