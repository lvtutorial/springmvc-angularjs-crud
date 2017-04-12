<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="url" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello World</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<style>
#overlay {
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    right: 0;
    background: #CCC;
    opacity: 0.8;
    filter: alpha(opacity=80);
}
#loading {
    width: 50px;
    height: 50px;
    position: absolute;
    top: 50%;
    left: 50%;
    margin: -28px 0 0 -25px;
}	
</style>
		
</head>
<body>
	<div ng-app = "mainApp">
      <div ng-controller="AppController as ctrl">
          <div>
              <h2>Create Customer Form</h2>
              <div>
                  <form ng-submit="ctrl.submit()" name="myForm">
                  	  <div>{{ctrl.error}}</div>
                      <input type="hidden" ng-model="ctrl.customer.id" />
					  <div>
						  <label>username</label>
						  <div>
							  <input type="text" ng-model="ctrl.customer.username" id="username" placeholder="Enter your username [min length=5]" required ng-minlength="5" size="50"/>                                  
						  </div>
					  </div>
					  <div>
						  <label>Fullname</label>
						  <div>
							  <input type="text" ng-model="ctrl.customer.fullname" id="fullname" placeholder="Enter your fullname. [This field can empty]" size="50"/>
						  </div>
					  </div>
					  <div>
						  <label>Email</label>
						  <div>
							  <input type="email" ng-model="ctrl.customer.email" id="email" placeholder="Enter your Email" required size="50"/>
						  </div>
					  </div>
					  <div>
						  <input type="submit"  value="{{!ctrl.customer.id ? 'Add' : 'Update'}}" ng-disabled="myForm.$invalid">
						  <button type="button" ng-click="ctrl.reset()" ng-disabled="myForm.$pristine">Reset</button>						  
					  </div>
                  </form>
              </div>
          </div>
          <div>
				<!-- load list customers -->
              <h2>List of Customers</h2>
              <div>
                  <table>
                      <thead>
                          <tr>
                              <th>Id.</th>
                              <th>Username</th>
                              <th>Fullname</th>
                              <th>Email</th>
                              <th width="100">
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="c in ctrl.customers">
                              <td><span ng-bind="c.id"></span></td>
                              <td><span ng-bind="c.username"></span></td>
                              <td><span ng-bind="c.fullname"></span></td>
                              <td><span ng-bind="c.email"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(c.id)">Edit</button>  <button type="button" ng-click="ctrl.remove(c.id)">Remove</button>
							  </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
	  </div>
	</div>

    
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script src="${url}/resources/js/crud-app.js"></script>
	<script src="${url}/resources/js/crud-service.js"></script>  
	<script src="${url}/resources/js/crud-controller.js"></script>
</body>
</html>