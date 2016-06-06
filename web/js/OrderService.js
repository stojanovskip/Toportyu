OrderServiceFactory = function ($http) {
    return {
        ordersByTrip: function (selectedTrip) {
            return $http.get('/api/trips/' + selectedTrip.id.toString() + '/orders').then(function(response)
            {return response.data});
        },
        saveOrder: function (newOrder) {
            return $http.post('/api/orders', newOrder).then(function(response){
                return response.data;
            });
        }
    };
};

OrderServiceFactory.install = function (app) {
    app.factory('OrderService', OrderServiceFactory);
};

module.exports = OrderServiceFactory;
