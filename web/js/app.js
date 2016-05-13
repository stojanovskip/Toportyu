require("style!raw!sass!../scss/style.scss");

var angular = require("angular");
var app = angular.module("myApp", []);

require("./OrderController.js").install(app);
require("./orderService.js");
require("./TripController.js");
require("./tripService.js").install(app);
require("./currentState.js").install(app);
