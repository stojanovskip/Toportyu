 var express = require('express');
  var proxy = require('express-http-proxy');
  var app = express();
  
  app.use(express.static('../web'));
  
  app.use('/', proxy('localhost:8000'));
 
 app.listen(3000);