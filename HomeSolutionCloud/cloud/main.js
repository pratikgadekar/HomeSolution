
var user = require('cloud/user/user.js');





Parse.Cloud.define("hello", function(request, response) {
  response.success("Hello world!");
});


Parse.Cloud.define("signupUser", function(request, response) {
    console.log("Parameters received : " + JSON.stringify(request.params));
    user.signUp({
        phone: request.params.phone,
        success: function(message) {
            response.success(message);
        },
        error: function(message) {
            response.error(message);
        }
    });
});