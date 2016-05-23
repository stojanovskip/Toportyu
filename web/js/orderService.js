OrderServiceFactory = function ($http) {
    return {
        ordersByTrip: function (selectedTrip) {
            return $http.get('/trips/' + selectedTrip.id.toString() + '/orders').then(function(response)
            {return response.data});
        },
        saveOrder: function (newOrder) {
            return $http.post('/orders', newOrder).then(function(response){
                return response.data;
            });
        }
    };
};

OrderServiceFactory.install = function (app) {
    app.factory('orderService', OrderServiceFactory);
};

module.exports = OrderServiceFactory;