$(document).ready(function(){
	
	var notify = new NotificationManagement();
	var formManagement = new FormManagement();
	
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
		 
		 obj = { 'email' : $('#loginEmail').val() , 'password' : $('#loginPassword').val()};
		 console.log(JSON.stringify(obj));
		 status = formManagement.submitAjax(JSON.stringify(obj),"auth/login-ajax");
		 console.log("check"+JSON.stringify(status));
		 notify.sayInfo("Login",2);
     });
});