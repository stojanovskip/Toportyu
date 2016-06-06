var path = require("path");
module.exports = {
    entry: path.join(__dirname, '../web/js/app.js'),
    debug: true,
    resolve: {
        root: [
            path.join(__dirname, '../web')
        ],
        modulesDirectories: [path.join(__dirname, "node_modules")]
    },
    resolveLoader: {
        modulesDirectories: [path.join(__dirname, "node_modules")]
    },
    output: {
        filename: 'bundle.js',
        path: path.join(__dirname, '../web/target'),
        publicPath: "/target/"
    }
};