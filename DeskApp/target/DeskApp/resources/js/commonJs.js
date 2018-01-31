
function NotificationManagement(){
    
	function notifyCall(notifyMessage,showTimeInSecond,classNameType){
		
		$.notify.addStyle(classNameType, {
			  html: 
				  "<div style='width:300px;'><div class='alert alert-"+classNameType+"' role='alert'>"+notifyMessage+"</div></div>"
			});
		
		$.notify(notifyMessage, {
			 clickToHide: true,
			 autoHide: true,
			 autoHideDelay: showTimeInSecond*1000,
			 //success danger warning info 
			 style: classNameType
		 });
    };
	
	this.saySuccess = function(notifyMessage,showTimeInSecond){
    	notifyCall(notifyMessage,showTimeInSecond,"success");
    };
    
    this.sayError = function(notifyMessage,showTimeInSecond){
    	notifyCall(notifyMessage,showTimeInSecond,"danger");
    };
    
    this.sayInfo = function(notifyMessage,showTimeInSecond){
    	notifyCall(notifyMessage,showTimeInSecond,"info");
    };
    
    this.sayWarning = function(notifyMessage,showTimeInSecond){
    	notifyCall(notifyMessage,showTimeInSecond,"warning");
    };
};

function FormManagement(){
	this.baseUrl = '/DeskApp/';
	
	this.formSubmit = function(formId,submitUrl){
		form=$('#'+formId);
    	form.attr('action',this.baseUrl+submitUrl).trigger('submit');
	};
	
	this.submitAjax = function(dataJson,submitUrl){
		
		$.ajax({
   			url: this.baseUrl+submitUrl,
            type: "POST",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: dataJson,
            success: function (data) {
            	return 200;
            },
            error: function (xhr, status, error) {
                console.log("token has been expired." + xhr.responseText);
                return status;
            }
        });
	};
	
	this.getAjax = function(submitUrl,dataJson){
		
		$.ajax({
   			url: this.baseUrl+submitUrl,
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
            	return data;
            },
            error: function (xhr, status, error) {
                console.log("token has been expired." + xhr.responseText);
                return status;
            }
        });
	};
	
};