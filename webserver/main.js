 var express = require('express');
 var proxy = require('express-http-proxy');
 var app = express();
 var PropertiesReader = require('properties-reader');
 var properties = PropertiesReader('./resources/port.config');
 var serverurl = properties.get('serverurl');
 var webport = properties.get('webport');

 app.use(express.static('../web'));
 
 app.use('/', proxy(serverurl));
 
 app.listen(webport);