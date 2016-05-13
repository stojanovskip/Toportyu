var path = require("path");

// Karma configuration
// Generated on Fri May 13 2016 15:16:50 GMT+0200 (Central Europe Daylight Time)

module.exports = function(config) {
  config.set({

    // base path that will be used to resolve all patterns (eg. files, exclude)
    basePath: '../web',


    // frameworks to use
    // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
    frameworks: ['jasmine'],


    // list of files / patterns to load in the browser
    files: [
        //path.join(__dirname, "../webserver/node_modules/angular/angular.js"),
        //path.join(__dirname, "../webserver/node_modules/angular-mocks/angular-mocks.js"),
        '**/*.spec.js'
    ],


    // list of files to exclude
    exclude: [
    ],


    // preprocess matching files before serving them to the browser
    // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
    preprocessors: {
        '**/*.js': ['webpack']
    },

    webpack: {
        // karma watches the test entry points
        // (you don't need to specify the entry option)
        // webpack watches dependencies
        resolve: {
            root: [
                path.join(__dirname, '../web')
            ],
            modulesDirectories: [path.join(__dirname, "../webserver/node_modules")]
        },
        resolveLoader: {
            modulesDirectories: [path.join(__dirname, "../webserver/node_modules")]
        }
        // webpack configuration
    },

    // test results reporter to use
    // possible values: 'dots', 'progress'
    // available reporters: https://npmjs.org/browse/keyword/karma-reporter
    reporters: ['progress'],


    // web server port
    port: 9876,


    // enable / disable colors in the output (reporters and logs)
    colors: true,


    // level of logging
    // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
    logLevel: config.LOG_INFO,


    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,


    // start these browsers
    // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
    browsers: ['Chrome'],


    // Continuous Integration mode
    // if true, Karma captures browsers, runs the tests and exits
    singleRun: false,

    // Concurrency level
    // how many browser should be started simultaneous
    concurrency: Infinity
  })
}
