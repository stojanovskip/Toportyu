var path = require("path");
module.exports = {
    entry: '../web/js/app.js',
    debug: true,
    resolve: {
        root: [
            path.join(__dirname, '../web')
        ],
        modulesDirectories: [path.join(__dirname, "../webserver/node_modules")]
    },
    resolveLoader: {
        modulesDirectories: [path.join(__dirname, "../webserver/node_modules")]
    },
    output: {
        filename: 'bundle.js',
        path: '../web/target/',
        publicPath: "/target/"
    }
};