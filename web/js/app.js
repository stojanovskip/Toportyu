require('style!raw!sass!../scss/style.scss');

var angular = require('angular');
var app = angular.module('KerkyraApp',[]);

require('./OrderController.js').install(app);
require('./orderService.js').install(app);
require('./TripController.js').install(app);
require('./tripService.js').install(app);
require('./currentState.js').install(app);