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
		 
		 form = ("#loginForm").serialize();
		 
		 status = formManagement.submitAjax(form,"auth/login-ajax");
		 alert(status);
		 notify.sayInfo("Login",2);
     });
});