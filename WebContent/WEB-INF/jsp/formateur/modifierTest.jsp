<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href='/QCM/css/style.css'>
<title>Modifier un test</title>
<jsp:include page="/WEB-INF/jsp/commons/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/commons/header.jsp">
		<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
	</jsp:include>
	<form method="post" action="/QCM/formateur/modifierTest">
		<div class="container">
			<h1 class="titre">Modifier un test</h1>

			<div class="form-group row">
  				<label for="example-text-input">Nom</label>
    			<input class="form-control" type="text" value="${test.libelleTest}" id="libelleTest" name="libelleTest">
			</div>
			<div class="form-group row">
  				<label for="example-text-input">Description</label>
    			<input class="form-control" type="text" value="${test.description}" id="descriptionTest" name="descriptionTest">
			</div>
			<div class="form-group row">
  				<label for="example-time-input">Durée</label>
    			<input class="form-control" type="time" value="${test.duree}" id="dureeTest" name="dureeTest">
			</div>
			<div class="form-group row">
  				<label for="example-number-input">Seuil Haut</label>
    			<input class="form-control" type="number" value="${test.seuilHaut}" id="seuilHaut" name="seuilHaut">
			</div>	
			<div class="form-group row">
  				<label for="example-number-input">Seuil Bas</label>
    			<input class="form-control" type="number" value="${test.seuilBas}" id="seuilBas" name="seuilBas">
			</div>	
			
			<input type="hidden" name="testId" value="${test.idTest}">	
  				
  			<div class="row justify-content-center">
				<div class="col-2">
					<button type="submit" class="btn btn-primary style_bouton">Modifier</button>	
				</div>
			</div>
		</div>
	</form>
</body>
</html>