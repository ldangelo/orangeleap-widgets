
var $j = jQuery.noConflict();
		
 function handleReturn(paymentStatus) {
 	if (paymentStatus.processed == false) {
 		handleError("Payment processing failed! Please confirm payment information");
 		showError();
 	} else {
 		handleApproval(paymentStatus.authorizationCode);
 	}
 }

function handleApproval(authCode) {
	$j("div#approval").show();
	$j("<li>Thank your for your donation!</li>").appendTo("ul#approved");
	$j("<li>Your authorization code for this transaction is " + authCode + " </li>").appendTo("ul#approved");
}

function showError() {
	$j("div#globalErrors").show();
}

function hideError() {
	$j("div#globalErrors").hide();
}

function clearError() {
	$j("ul#errors").empty();
}

function handleError(str) {
	$j("<li>" + str + "</li>").appendTo('ul#errors');
}
 		
function validateForm() {
	var isValid = 1;
	if($j("#firstName").attr("value").length <= 0) {
		handleError("First Name is required!");
		isValid = 0;
	}

	if($j("#lastName").attr("value").length <= 0) {
		handleError("Last Name is required!");
		isValid = 0;
	}

	if($j("#email").attr("value").length <= 0) {
		handleError("Email Address is required!");
		isValid = 0;
	}

	if($j("#phone").attr("value").length <= 0) {
		handleError("Billing phone number is required!");
		isValid = 0;
	}

	if($j("#amount").attr("value").length <= 0) {
		handleError("Donation amount is required!");
		isValid = 0;
	}

	if($j("#cardnum").attr("value").length <= 0) {
		handleError("Credit card number is required!");
		isValid = 0;
	}

	if($j("#expmonth").attr("value").length <= 0) {
		handleError("Credit card expiration month is required!");
		isValid = 0;
	}

	if($j("#expyear").attr("value").length <= 0) {
		handleError("Credit card expiration year is required!");
		isValid = 0;
	}

	if (isValid != 1) showError();
			
	return isValid;	
}
 		
 
   $j(function() {
   	 hideError();
   	   
     $j(".saveButton").click(function() {  

		
		clearError();
		hideError();
		
		if (validateForm() == 1){
		
		var pi = {
			name: $j("#firstName").attr("value") + " " + $j("#lastName").attr("value"),
			paymentType: "CC",
			ccNumber: $j("#cardnum").attr("value"),
			ccExpMonth: $j("#expmonth").attr("value"),
			ccExpYear: $j("#expyear").attr("value")
		};
		
		var d = { 
			GUID: $j("#GUID").attr("value"),
			firstName: $j("#firstName").attr("value"),
			lastName: $j("#lastName").attr("value"),
			emailAddress: $j("#email").attr("value"),
			phoneNumber: $j("#phone").attr("value"),
			amount: $j("#amount").attr("value"),
			paymentInfo: pi,
			referrer: document.location.href
		};
		
		donateNow.donate(d,handleReturn);
}
      });  
      
      donateNow.updateViewCount($j("#GUID").attr("value"),document.location.href);
    });  

 
