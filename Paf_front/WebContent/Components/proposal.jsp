<%@page import="com.proposal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<style > p.solid {border-style: solid;} </style>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/proposal.js"></script>
<meta charset="ISO-8859-1">
<title>Proposal Management</title>
</head>
<body>
	<div class="container"><div class="row"><div class="col-6">
		<h1>Proposal Management</h1>
<p class="solid">
			<form id="formproposal" name="formproposal">
	Code:
	<input id="pcode" name="code" type="text" class="form-control form-control-sm"><br>

	Title:
	<input id="title" name="title" type="text" class="form-control form-control-sm"><br> 

	ExpBudget:
	<input id="expBudget" name="expBudget" type="text" class="form-control form-control-sm"><br>

	Description:
	<input id="description" name="description" type="text" class="form-control form-control-sm"><br>

<input id="btnCreateproposal" name="btnCreateproposal" type="button" value="Save" class="btn btn-primary">
      			<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
			</form>
	
<div id="alertSuccess" class = "alert alert-success"></div>
<div id ="alertError" class =" alert alert-danger"></div>

<br>
<div id="divProposalGrid"> 
<%
proposal proposalObj = new proposal();
out.print(proposalObj.readproposal());
%>
</div>
</div>
   
   
   </div>


</div>
</div>
</body>
</html>
