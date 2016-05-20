require("style!raw!sass!../scss/style.scss");

var angular = require("angular");
var app = angular.module("Kerkyra",[]);

require("./OrderController.js");
require("./orderService.js");
require("./TripController.js");
require("./tripService.js");
require("./currentState.js");