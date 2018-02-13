$(document).ready(function(){
	
	var notifyService = new NotifyService();
	var formService = new FormService();
	
	var profileUrl = "/DeskApp/resources/img/smile.png";
	
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
	
	$(".logout-icon").on("click", function(){
		if($('.logut-popup').is(':hidden')){
			$(".logut-popup").show();
		}else{
			$(".logut-popup").hide();
		}
		
	});
});