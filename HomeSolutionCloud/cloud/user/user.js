var Response = {
    InternalServerError: "Some error occurred. Please try again.",
    NotFound: "Requested resource was not found",
    EmptyParameters: "ome parameters empty!",
    SaveSuccess: "Notification successfully sent!",
    AuthenticationError: "Authentication problem!",

};


exports.signUp = function(params) {
	Parse.Cloud.useMasterKey();
	
	if(!params || !params.phone || !params.success || !params.error) {
        params.error(Response.EmptyParameters);
    } else {
    			//create varification code
    	  		var verificationCode = Math.floor(Math.random() * 9000) + 1000;

    	  		//search user if exist
    	  		var query = new Parse.Query(Parse.User);
			   query.equalTo('username', params.phone);
			   query.first({
			   	 success: function(user) {

			   	 	if(user){
			   	 		
					   	 		 user.set("verificationCode", verificationCode);
					   	 		 //params.success("user save again");
					   	 		 user.save(null, {
					   	 		  	success: function(usersavedagain) {
					   	 		  		params.success("user save again");
					   	 		  	},error:function(error){
					   	 		  			console.log("user saved again error " + error.message);
					   	 		  			params.error(Response.InternalServerError);
					   	 		  	}
					   	 		  });
					   	 		 
			   	 	} else {
					   	 		var user = new Parse.User();
					            user.set("username", params.phone);
					            user.set("password", Math.random().toString(36).slice(-8));
					            user.set("phone", params.phone);
					            user.set("verificationCode", verificationCode);
					            user.signUp(null, {
								  success: function(message) {
								    // Hooray! Let them use the app now.
								    user.set("token", user._sessionToken);

				                    var user_acl = new Parse.ACL();
				                    // give write access to the current user
				                    user_acl.setWriteAccess( Parse.User.current(), true);
				                    // give public read access
				                    user_acl.setPublicReadAccess(true);
				                    user.setACL(user_acl);
								    params.success(Response.SaveSuccess);
								  },
								  error: function(user, error) {
								    // Show the error message somewhere and let the user try again.
								   	params.error(error.message);
								  }
								});
			   	 	}
			   	 },error: function(message){
			   	 		params.error(Response.AuthenticationError);
			   	 }
			   });


    	       
    }
};