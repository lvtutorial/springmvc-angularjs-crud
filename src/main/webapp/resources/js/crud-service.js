//service
//mainApp.service('AppService', function(){
mainApp.service('AppService', ['$http', function($http){
	
	this.newCustomer = function() {
		var customer = {id:null,username:'',fullname:'',email:''};
		return customer;
	}
	
	this.getCustomers_test = function() {
		var customers = [
			{id:1, username: 'lvtutorial', fullname: 'Tommy', email: 'tommy@lvtutorial.com'},
			{id:2, username: 'username1', fullname: 'michael', email: 'michael@lvtutorial.com'}
		];
		//console.log('customer updated with id ');
		return customers;
	}
	
	this.getCustomers = function(){
        return $http({method: 'GET', url: 'customers', headers:'Accept:application/json'
        	//,params: { Id: JSON.stringify(id) } //if any
        }).then( function(response){
        	//alert(response.data); //response.data is data of customers
        	return response.data
        },
        function(errResponse){
            console.error(errResponse);
            return errResponse;
        });
    }
	
	//create/update/delete with action url
	this.actionCustomer = function(customer, action){
		//alert(action);
        return $http({method: 'POST', url: action, headers:'Accept:application/json', data:customer
        	//,params: { Id: JSON.stringify(id) } //if any
        }).then( function(response){
        	//alert(response.data); //response.data is data of customers
        	return response.data
        },
        function(errResponse){
            console.error(errResponse);
            return errResponse;
        });
    }
	
	this.createCustomerTest = function(customer){
        return $http({method: 'POST', url: 'create_customer_test', headers:'Accept:application/json', data:customer
        	//,params: { Id: JSON.stringify(id) } //if any
        }).then( function(response){
        	//alert(response.data.id); //response.data is data of customers
        	return response.data
        },
        function(errResponse){
            console.error(errResponse);
            return errResponse;
        });
    }	
		
}]);

