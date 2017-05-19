var express   = require('express'),
    httpProxy = require('http-proxy'),
    app       = express();

var proxy = new httpProxy.createProxyServer();
app.use('/delta', express.static('/', {index: false}));
app.use("/delta/js", express.static(__dirname + "/app/js"));
app.use("/delta/scripts", express.static(__dirname + "/app/scripts"));
app.use("/delta/styles", express.static(__dirname + "/app/styles"));
app.use("/delta/views", express.static(__dirname + "/app/views"));
app.use("/delta/bower_components", express.static(__dirname + "/bower_components"));

app.all("/api/*", function(req, res){ 
	proxy.web(req, res, { target: 'http://localhost:9000' });
});

app.get("/delta/", function(req, res, next) {
	res.sendFile(__dirname + '/app/index.html');
});


var server = app.listen(3000, function () {

	  var host = server.address().address;
	  var port = server.address().port;

	  console.log('Running http://%s:%s', host, port);

	});