function MainCtrl($scope) {
    $scope.showModal = false;
    $scope.toggleModal = function() {
        $scope.showModal = !$scope.showModal;
    };
}

MainCtrl.install = function(app) {
    app.controller('MainCtrl', MainCtrl);
};

module.exports = MainCtrl;
