<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href='/QCM/css/style.css'>
<title>Ajouter un test</title>
<jsp:include page="/WEB-INF/jsp/commons/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/commons/header.jsp">
		<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
	</jsp:include>
	<form method="post" action="/QCM/formateur/ajouterTest">
		<div class="container">
			<h1 class="titre">Ajouter un test</h1>

			<div class="form-group row">
  				<label for="example-text-input">Nom</label>
    			<input class="form-control" type="text" id="libelleTest" name="libelleTest">
			</div>
			<div class="form-group row">
  				<label for="example-text-input">Description</label>
    			<input class="form-control" type="text" id="descriptionTest" name="descriptionTest">
			</div>
			<div class="form-group row">
  				<label for="example-time-input">Durée</label>
    			<input class="form-control" type="time" id="dureeTest" name="dureeTest">
			</div>
			<div class="form-group row">
  				<label for="example-number-input">Seuil Haut</label>
    			<input class="form-control" type="number" id="seuilHaut" name="seuilHaut">
			</div>	
			<div class="form-group row">
  				<label for="example-number-input">Seuil Bas</label>
    			<input class="form-control" type="number" id="seuilBas" name="seuilBas">
			</div>	
			
  			<div class="row justify-content-center">
				<div class="col-2">
					<button type="submit" class="btn btn-primary style_bouton">Ajouter</button>	
				</div>
			</div>
		</div>
	</form>
</body>
</html>