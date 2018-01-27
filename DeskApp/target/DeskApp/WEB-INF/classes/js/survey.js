$(document).ready(function(){
	
	$('#plantId').on('change', function() {
           var value = this.value;
           
           $.ajax({
               url : "/vedanta-web/email/getVendorDetails/"+value,
               type : "GET",
               success : function(data) {
            	   showVendorData(data);
               },
               error : function(xhr, status, error) {
                   
               }
           });
       })
       
       $('#vendorId').on('change', function() {
           var value = this.value;
           
          $.ajax({
               url : "/vedanta-web/email/getContractDetails/"+value,
               type : "GET",
               success : function(data) {
            	   showContractData(data);
               },
               error : function(xhr, status, error) {
                   
               }
           });
       })
       
          $('#contractId').on('change', function() {
           var value = this.value;
           
           $.ajax({
        	   url : "/vedanta-web/email/getSurveyDetails/"+value,
        	   type : "GET",
        	   success : function(data) {
        		   showSurveyData(data);
        	   },
        	   error : function(xhr, status, error) {

        	   }
           });
       })
          
   
       
        function showVendorData(data) {
 			$('#vendorId').html("");
 			 $('#vendorId').append("<option value=''>Select vendor </option>"); 
 			for(var i=0;i<data.length;i++){
 					$('#vendorId').append("<option value=\"" + data[i].id + "\">" + data[i].name+ "</option>");
 			}
	   	}
       
       function showContractData(data) {
 			$('#contractId').html("");
 			 $('#contractId').append("<option value=''>Select contract </option>"); 
 			for(var i=0;i<data.length;i++){
 					$('#contractId').append("<option value=\"" + data[i].id + "\">" + data[i].number+ "</option>");
 			}
	   	}
       
       function showSurveyData(data) {
			$('#formId').html("");
			 $('#formId').append("<option value=''>Select survey </option>"); 
			for(var i=0;i<data.length;i++){
					$('#formId').append("<option value=\"" + data[i].id + "\">" + data[i].name+ "</option>");
			}
	   	}
      /* $('.selectize').selectpicker({
 	      liveSearch: true,
 	      maxOptions: 1
 	    });*/
});

