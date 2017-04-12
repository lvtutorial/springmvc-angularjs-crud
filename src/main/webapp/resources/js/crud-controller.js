//Controller	
mainApp.controller('AppController', function($scope, AppService) {
	var thisCtrl = this; //if use this then will not keep variable
	//thisCtrl.id = AppService.getMaxCustomerId(); //next id customer will be 3

	thisCtrl.customer = AppService.newCustomer();
	getAllCustomers();
		
	 
    function getAllCustomers(){
    	loadingOverley();
    	setTimeout(function () {
	  		// Something you want delayed.			
	    	AppService.getCustomers().then(
	    	function (cus) {
	    		//alert(cus.data);
	    		thisCtrl.customers = cus; 
	    		removeLoading();
	        }, 
	        function (errResponse) {
	        	console.error(errResponse);
	        	thisCtrl.error = errResponse;
	        	removeLoading();
	        });
    	}, 3000);
    }
    
    function actionCustomer(customer, action){
    	AppService.actionCustomer(customer, action).then(
        function (cus) {
    		//alert(cus.data); 
        	//always get list customers
    		thisCtrl.customers = cus; 
        }, 
        function (errResponse) {
        	console.error(errResponse);
        	thisCtrl.error = errResponse;
        });

    }
    	
    //button submit form
    thisCtrl.submit = function() {
		if(thisCtrl.customer.id == null){
			console.log('Saving New customer', thisCtrl.customer);    
			//do it in server and get result
			actionCustomer(thisCtrl.customer, 'create_customer');
		}else{
			console.log('Edit New customer', thisCtrl.customer);  
			actionCustomer(thisCtrl.customer, 'update_customer');
		}
		thisCtrl.reset();
	};
	
	thisCtrl.edit = function(id){
		for(var i = 0; i < thisCtrl.customers.length; i++){
			if(thisCtrl.customers[i].id === id) {
				thisCtrl.customer = angular.copy(thisCtrl.customers[i]);
				break;
			}
		}
	}
	   
	thisCtrl.remove = function(id){	
		
		for(var i = 0; i < thisCtrl.customers.length; i++){
			if(thisCtrl.customers[i].id === id) {
				actionCustomer(thisCtrl.customers[i], 'delete_customer');
				//thisCtrl.customers.splice(i,1);
				if(thisCtrl.customer.id === id){ //reset it to not show on form
					thisCtrl.reset();
				}
				break;
			}
		}
	}
	
    thisCtrl.reset = function(){
		thisCtrl.customer={id:null, username:'', fullname:'', email:''};
		$scope.myForm.$setPristine(); //reset Form
	}
       
    function loadingOverley() {
    	
    	/*setTimeout(function () {
	  		// Something you want delayed.			
	  	}, 3000);
    	*/
    	var over = '<div id="overlay">' +
        // '<img id="loading" src="...">' +
 			'<span id="loading">Loading</span>' +
             '</div>';
         $(over).appendTo('body');
    		
    }
	function removeLoading() {	
		$('#overlay').remove();
	}
   
});