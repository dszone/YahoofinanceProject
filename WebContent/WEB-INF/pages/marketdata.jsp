<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Market data</title>
<link href='http://fonts.googleapis.com/css?family=Orbitron' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Unkempt:400,700' rel='stylesheet' type='text/css'>
<link href="css/bootstrap.css" rel="stylesheet">

<style type="text/css">
h3 {
	color: #27A0C4;
	text-align:center;
	font-family: 'Unkempt', cursive;
	font-weight: 700;
	letter-spacing: 1px;
}

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

table {
	margin-left:auto; 
    margin-right:auto;
} 

</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src= "http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular-resource.min.js"></script><!-- angularJS Ajax call: http call -->
<script src="js/bootstrap.js"></script>
<script>
	angular.module("mainModule", []).controller("mainController", function($scope, $interval, $http) {
		// Initialization
		$scope.stocksArray = $.parseJSON('${Requests}');
		$interval(function() {
			$http({
				method: "GET",
				url: "rest/market", 
			}).success(function(data) {
				$scope.stocksArray = data;
			}).error(function(data) {
				alert("AJAX Error!");
			});
		}, 2000);
	});	
</script>
</head>
<body ng-app="mainModule">
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
				<li><a href = "/YahooFinanceProject/transaction.html">Transaction</a></li>
				<li class= "active"><a href = "/YahooFinanceProject/marketdata.html">Market data</a></li>
				<li><a href = "/YahooFinanceProject/history.html">History</a></li>
				<li><a href="<c:url value='/j_spring_security_logout'/>">Logout</a></li>
			</ul>
		</div>
	</div>
</div>

<div ng-controller="mainController">
	<h3>Real Time Market Data</h3>
	<div class="table-responsive"> 
		<table id="stockList" border="1" style="width: 500px" class="table table-striped table-bordered table-hover table-responsive">
			<tr>
				<th>Stock Code</th>
				<th>Stock Name</th>
				<th>Current Price</th>
				<th>Price Change</th>
			</tr>
	
			<tr ng-repeat="stock in stocksArray">
				<td>{{stock.scode}}</td>
				<td>{{stock.stockName}}</td>
				<td>{{stock.currentPrice}}</td>
				<td>
					<b ng-if="stock.pricechange>0" style="color:green">{{stock.pricechange}}</b>
					<b ng-if="stock.pricechange<0" style="color:red">{{stock.pricechange}}</b>
					<b ng-if="stock.pricechange==0" style="color:black">{{stock.pricechange}}</b>
				</td>
			</tr>
		</table>
	</div>
</div>
	
</body>
</html>