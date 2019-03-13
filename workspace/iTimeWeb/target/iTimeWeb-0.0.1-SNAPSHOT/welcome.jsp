<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Home Page</title>
<link rel="stylesheet" href="js/jquery-ui.css">
<link rel="stylesheet" href="css/style.css">

<script src="js/jquery-1.11.1.js"></script>
<script src="js/jquery-ui.js"></script>

<script>
$(function(){
	document.getElementById("edit").addEventListener("click", function() {
		  var fields = document.querySelectorAll("table input[type='text']");
		  for (var i = 0; i < fields.length; i++) {
		    fields[i].readOnly = false;
		  }

		  document.getElementById("save").style.display = "inline-block";
		});

		document.getElementById("save").addEventListener("click", function() {
		  var data = {};
		  data.name = document.getElementById("name").value;
		  data.hours = document.getElementById("hours").value;
		  // window.localStorage.formData = JSON.stringify(data);
		  // localStorage will not work in this snippet editor
		  // uncomment it in your code

		  var fields = document.querySelectorAll("table input[type='text']");
		  for (var i = 0; i < fields.length; i++) {
		    fields[i].readOnly = true;
		  }

		  document.getElementById("save").style.display = "none";
		});	
});
</script>
</head>

<body>
<div class="wrapper">
	<div class="container">
		<%
			String user = (String) session.getAttribute("user");
			if (user != null) {
		%>
		<h3>
			Welcome
			<%
				out.print(user);
			%>
			</h3>
			<a href="LoginController?param=logout">Logout</a>
			<form>
			  <table>
			    <tr>
			      <td>Project Name:</td>
			      <td><input type="text" id="name" value="Project Name" readonly /></td>
			    </tr>
			    <tr>
			      <td>% Hours:</td>
			      <td><input type="text" id="hours" value="Percent Hours" readonly /></td>
			    </tr>
			  </table>
			  <input type="button" id="edit" value="Edit" />
			  <input type="button" id="save" value="Save" />
			</form>
			
			<%
				} else {
			%>
			<h3>Your don't have permission to access this page</h3>
			<%
				}
			%>
	</div>
</div>
</body>
</html>