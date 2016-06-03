require('style!raw!sass!../scss/style.scss');

var angular = require('angular');
var app = angular.module('KerkyraApp',[]);

require('./AuthController.js').install(app);
require('./AuthService.js').install(app);
require('./OrderController.js').install(app);
require('./OrderService.js').install(app);
require('./TripController.js').install(app);
require('./TripService.js').install(app);
require('./CurrentState.js').install(app);
