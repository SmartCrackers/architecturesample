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
    
    $(".showForgotPassword").click(function() {
    	$("#loginBox").hide();
   	 	$("#signUpBox").hide();
    	$("#forgotPasswordBox").show();
	 }); 
	 
	 $('#loginBtn').on('click', function () {
		 
		 var loginCredintial = { 'email' : $('#loginEmail').val() , 'password' : $('#loginPassword').val() };
		 
		 console.log(JSON.stringify(loginCredintial));
		 
		 var status = formService.submitAjax(JSON.stringify(loginCredintial),'auth/login');
		 
		 console.log(status);
		 notifyService.sayInfo("Login",2);
     });
});