<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="JSONArray" scope="request" class="org.json.JSONArray"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Yahoo Finance System</title>
<link href='http://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Orbitron' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Unkempt:400,700' rel='stylesheet' type='text/css'>

<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src= "http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular-resource.min.js"></script>
<!-- angularJS Ajax call: http call -->
<title>Transaction Service Test</title>
<style type="text/css"> 
.yahoo {
	color: green;
	font-size: 36px;
	letter-spacing: 3px;
	font-family: 'Orbitron', sans-serif;
	font-weight: 700;
}

.finance {
	color: #D00000;
	font-size: 36px;
	letter-spacing: 3px;
	font-family: 'Orbitron', sans-serif;
	font-weight: 700;
}

h3 {
	color: #27A0C4;
	text-align:center;
	font-family: 'Unkempt', cursive;
	font-weight: 700;
	letter-spacing: 1px;
}

table {
	margin-left:auto; 
    margin-right:auto;
} 
</style>
<script>
var module = angular.module("mainModule", []);
module.controller("bsController", function ($scope, $http) {
	
		$scope.request = {
			code: "",
			amount: null
		};
		$scope.welcomeMsg = "Please enter the StockCode and Amount";
		$scope.canShow = false;
	    
		$scope.buyRequest = function (request, resultVarName) {
				
		    var pa = {
		       	code: request.code,
		       	amount: request.amount
		    };
	  		var msg="";
	    	request.code = "goog";
	   		$http({
	   			method: "POST",
	   			url: "rest/buySub",
	   			params: pa,
	   			data: msg,
	   			headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	   		}).success(function (data, status, headers, config) {
	   			$scope[resultVarName] = data;
	   			request.code = "";
	   			request.amount = 0;
	   			$scope.welcomeMsg = data; 
	     	}).error(function (data, status, headers, config) {
	       		$scope[resultVarName] = "SUBMIT ERROR";
	     	});
	 	};	

   		
	$scope.sellRequest = function (request, resultVarName) {
     			
	    var pa = {
	       	code: request.code,
	       	amount: request.amount
	    };
  		    
   		$http({
   			method: "POST",
   			url: "rest/sellSub",
   			params: pa,
   			data:pa,
   			headers: {'Content-Type': 'application/x-www-form-urlencoded'}
   		}).success(function (data, status, headers, config) {
   			$scope[resultVarName] = data;
			request.code = "";
			request.amount = null;
			$scope.welcomeMsg = data; 
     	}) .error(function (data, status, headers, config) {
       		$scope[resultVarName] = "SUBMIT ERROR";
     	});
   	};
});	


module.controller("ShowTransactionController",function($scope,$http){
	$scope.requests = $.parseJSON('${Requests}');
});
</script>


</head>
<body ng-app="mainModule">


<!--NAVI BAR  -->
<div class="navbar navbar-default navbar-static-top">
	<div class="container">
		<a class="navbar-header" href="/YahooFinanceProject/login.html"><img src="images/dollar2.jpg" height="50" width="50"></a> 
		<a class="navbar-header" class="navbar-header" href="/YahooFinanceProject/login.html"> 
			<span class="yahoo">YAHOO</span> <span class="finance">FINANCE</span>
		</a>
		
		<button class="navbar-toggle" data-toggle= "collapse" data-target= ".navHeaderCollapse">
			<span class= "icon-bar"></span>
			<span class= "icon-bar"></span>
			<span class= "icon-bar"></span>
		</button>
		
		<div class= "collapse navbar-collapse navHeaderCollapse">
			<ul id= "menu" class= "nav navbar-nav navbar-right">
				<li><a href = "/YahooFinanceProject/main.html">Portfolio</a></li>
				<li class= "active"><a href = "/YahooFinanceProject/transaction.html">Transaction</a></li>
				<li><a href = "/YahooFinanceProject/marketdata.html">Market data</a></li>
				<li><a href = "/YahooFinanceProject/history.html">History</a></li>
				<li><a href="<c:url value='/j_spring_security_logout'/>">Logout</a></li>
			</ul>
		</div>
	</div>
</div>


<!-- All request Transactions  -->
<div ng-controller="ShowTransactionController">
	<h3>Transaction Submitted</h3>
	<div class="table-responsive"> 
		<table id="stockList" border="1" style="width: 800px" class="table table-striped table-bordered table-hover table-responsive">
			<tr>
				<th>Index</th>
				<th>Stock Name</th>
				<th>Stock Code</th>
				<th>Requested Price</th>
				<th>Amount</th>
				<th>Requested Time</th>
			</tr>
	
			<tr ng-repeat="request in requests">
				<td>{{ $index + 1 }}</td>
				<td>{{request.stockName}}</td>
				<td>{{request.stockCode}}</td>
				<td>{{request.unitprice|number : 2}}</td>
				<td>
					<b ng-if="request.amount>0" style="color:green">{{request.amount}}</b>
					<b ng-if="request.amount<0" style="color:red">{{request.amount}}</b>
				</td>
				<td>{{request.ts}}</td>
			</tr>
		</table>
	</div>
</div>


<button id="buy" data-toggle="modal" data-target="#myModal1" type="submit" class="btn btn-primary btn-lg">Buy</button>
<button id="sell" display = "inline" data-toggle="modal" data-target="#myModal2" type="submit" class="btn btn-primary btn-lg">Sell</button>

<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
      	<!-- modal header -->
        	<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal">
        			<span aria-hidden="true">&times;</span>
        		</button>
          		<h4 class="modal-title" id="myModalLabel2">Selling out</h4>
        	</div>
        	<!-- modal body -->
        	<div class="modal-body" ng-controller="bsController">
        		
          		<form class="form-horizontal" ng-submit="sellRequest(request, 'ajaxResult')" novalidate>
          			<div class = "panel panel-default">
          				<div class = "panel-body bg-primary">{{welcomeMsg}}</div>
          			</div>
          			
          			<!-- Username -->

          			<div class="form-group">
             				<label for="StockCode" class="col-sm-2 control-label">Stock Code:</label>
             				<span class="glyphicon glyphicon-asterisk"></span>
             				<div class="col-sm-6">
               				<input type="text" class="form-control"
               					id="code2" ng-model="request.code" 
               					placeholder="StockCode" name="code2" required>
               				
             				</div>
          				</div> 
          
           			<div class="alert" style="display:none;" id="userExist2">
        				<p>The user already exists</p>
           			</div>
         
         				<div class="form-group">
             				<label for="amount" class="col-sm-2 control-label">Amount:</label>
             				<span class="glyphicon glyphicon-asterisk"></span>
             				<div class="col-sm-6">
               				<input type="number" class="form-control" 
               					ng-model="request.amount" 
               					id="amount2" 
               					placeholder="Amount" name ="amount" required >
 	            			</div>
   	        		</div>
   	        		<div class="well well-sm">
          				<strong>
          					<span class="glyphicon glyphicon-asterisk"></span>
          					Required Field
          				</strong>
          			</div>

       	    		<div class="form-group">
           	  			<div class="col-sm-offset-2 col-sm-10">
               				<button type="submit" class="btn btn-default" >Submit</button>
             				</div>
           			</div>
         			</form>
       		</div>
		</div>
	</div>
</div>


<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
      	<!-- modal header -->
        	<div class="modal-header">
          		<button type="button" class="close" data-dismiss="modal">
        			<span aria-hidden="true">&times;</span>
        		</button>
          		<h4 class="modal-title" id="myModalLabel">Buying In</h4>
        	</div>
        	<!-- modal body -->
        	<div class="modal-body" ng-controller="bsController">
          		<form class="form-horizontal" ng-submit="buyRequest(request, 'ajaxResult')" novalidate>
          			<div class = "panel panel-default">
          				<div class = "panel-body bg-primary">{{welcomeMsg}}</div>
          			</div>
          			
          			<!-- Username -->
          			<div class="form-group">
             				<label for="StockCode" class="col-sm-2 control-label">Stock Code:</label>
             				<span class="glyphicon glyphicon-asterisk"></span>
             				<div class="col-sm-6">
               				<input type="text" class="form-control"
               					id="code" ng-model="request.code" 
               					placeholder="StockCode" name="code" required>
             				</div>
          				</div> 
           
           			<div class="alert" style="display:none;" id="userExist">
        				<p>The user already exists</p>
           			</div>
         
         				<div class="form-group">
             				<label for="amount" class="col-sm-2 control-label">Amount:</label>
             				<span class="glyphicon glyphicon-asterisk"></span>
             				<div class="col-sm-6">
               				<input type="number" class="form-control" 
               					ng-model="request.amount" 
               					id="amount" 
               					placeholder="Amount" name ="amount" required >
 	            			</div>
   	        		</div>
   	        		<div class="well well-sm">
          				<strong>
          					<span class="glyphicon glyphicon-asterisk"></span>
          					Required Field
          				</strong>
          			</div>

       	    		<div class="form-group">
           	  			<div class="c2ol-sm-offset-2 col-sm-10">
               				<button type="submit" class="btn btn-default" >Submit</button>
             				</div>
           			</div>
         			</form>
       		</div>
		</div>
	</div>
</div>

</body>
</html>