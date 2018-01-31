
var ajaxResponse = '';

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
		var result = false;
		$.ajax({
   			url: this.baseUrl+submitUrl,
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: dataJson,
            async: !1,
            success: function (data) {
            	result = data.status;
            },
            error: function (xhr, status, error) {
                console.log("token has been expired." + xhr.responseText);
                result = status;
            }
        });
		
		return result;
	};
	
	this.getAjax = function(submitUrl,dataJson){
		var result = false;
		$.ajax({
   			url: this.baseUrl+submitUrl,
            type: "GET",
            dataType: "json",
            async: !1,
            contentType: "application/json; charset=utf-8",
            success: function (data) {
            	result = data;
            },
            error: function (xhr, status, error) {
                console.log("token has been expired." + xhr.responseText);
                result = status;
            }
        });
		return result;
	};
	
};