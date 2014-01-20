<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.google.appengine.api.datastore.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Répertoire</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/signin.css">
</head>
<body>
	<h2 align="center">Veuillez choisir le répertoire dans lequel vous voulez ajouter
		votre contact.</h2>
		<form class="form-signin" role="form" action="resultat" method="post">
				
					<input type="text" class="form-control" 
						placeholder="Nom" name="nom"></br>			
				
					<input type="text" class="form-control" 
						placeholder="Prénom" required  name="prenom"></br>
					<input type="tel" class="form-control" 
						placeholder="Numéro" required  name="numero"></br>
				
					<input type="email" class="form-control"
						placeholder="Email" required  name="email"></br>
				
					<input type="text" class="form-control" 
						placeholder="Adresse"  name="adresse"></br>
				
					<select class="form-control" name="repertoire"></br>
						<option value="professionel">Professionel</option>
						<option value="particulier">Particulier</option>
					</select></br>
				
			<button class="btn btn-lg btn-primary btn-block" type="submit" >Ajouter</button>
			
			
		</form>

	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
			<%
			List<Entity> results = (List<Entity>) request
					.getAttribute("results");
		%>
		<h2>Repertoire <%= request.getParameter("repertoire") %></h2>
	<table class="table table-hover">
		<tr>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Numéro</th>
			<th>Email</th>
			<th>Adresse</th>
		</tr>


		<%
			for(Entity result : results) {
		%>

		<tr>
			<td><%=result.getProperty("nom").toString()%></td>
			<td><%=result.getProperty("prenom").toString()%></td>
			<td><%=result.getProperty("numero").toString()%></td>
			<td><%=result.getProperty("email").toString()%></td>
			<td><%=result.getProperty("adresse").toString()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
</body>
</html>