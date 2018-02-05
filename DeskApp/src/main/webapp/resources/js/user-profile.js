$(document).ready(function(){
	
	var notifyService = new NotifyService();
	var formService = new FormService();
	
	var profileUrl = "../resources/img/smile.png";
	
	$("#userProfileFileUpload").on("change", function(){
		
		var input = document.getElementById("userProfileFileUpload");
		var fReader = new FileReader();
		fReader.readAsDataURL(input.files[0]);
		fReader.onloadend = function(event){
			$("#userProfilePreview").css({
				"background-image": "url("+event.target.result+")" 
			});
		};
		
		$(".profile-pic-add-remove-btn").show();
	});
	
	$("#cancelPicChange").on("click", function(){
		$("#userProfilePreview").css({
			"background-image": "url("+profileUrl+")" 
		});
		$(".profile-pic-add-remove-btn").hide();
	});
	
});