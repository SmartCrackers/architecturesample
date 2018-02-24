$(document).ready(function(){
	
	var notifyService = new NotifyService();
	var formService = new FormService();
	
    $(".showSignIn").click(function() {
    	 $("#signUpBox").hide();
    	 $("#forgotPasswordBox").hide();
    	 $("#loginBox").show();
	 });
    
    $(".showSignUp").click(function() {
    	$("#forgotPasswordBox").hide();
	   	$("#loginBox").hide();
	   	$("#signUpBox").show();
	 });
    
    $("#signUpUser").click(function() {
    	var signUpObj = { "userName" : $("#userNameSignUp").val() , "email" : $("#emailSignUp").val(), "password" : $("#passwordSignUp").val()};
		console.log(JSON.stringify(signUpObj));
		
		var status = formService.submitAjax(JSON.stringify(signUpObj),'auth/signup');
		console.log(JSON.stringify(status));
		if(status == 200 ){
			$("#forgotPasswordBox").hide();
	   	 	$("#signUpBox").hide();
	   	 	$("#loginBox").show();
	   	 notifyService.saySuccess("Signup Success",10);
		 }else{
			notifyService.sayError("Server Internal Error",10);
		 }
	 });
    
    $(".showForgotPassword").click(function() {
    	$("#loginBox").hide();
   	 	$("#signUpBox").hide();
    	$("#forgotPasswordBox").show();
	 }); 
	 
	 $('#loginBtn').on('click', function () {
		 
		 var loginCredintial = { 'userName' : $('#loginUserName').val() , 'password' : $('#loginPassword').val() };
		 
		 console.log(JSON.stringify(loginCredintial));
		 
		 var status = formService.submitAjax(JSON.stringify(loginCredintial),'auth/login');
		 
		 var baseurl = formService.locationOrigin+formService.baseUrl;
		 if(status == 200 || status == 49 ){
			 if(status == 200){
				 console.log(baseurl);
				 // TODO on login success baseUrl
				 window.location.href = baseurl+"auth/logged-in-index";
			 }
			 if(status == 49){
				 // TODO on bad credintial
				 notifyService.sayError("Bad Credintial",10);
			 }
		 }else{
			 // TODO on server internal error
			 notifyService.sayError("Server Internal Error",10);
		 }
		 
		// notifyService.sayInfo("Login",2);
     });
});