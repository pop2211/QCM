<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href='/QCM/css/style.css'>
<title>Incription d'un candidat</title>
<jsp:include page="/WEB-INF/jsp/commons/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/commons/header.jsp">
		<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
	</jsp:include>
	<form method="post" action="/QCM/responsable/inscriptionPromotion">
		<div class="container">		
			<h1 class="titre">Inscription d'une promotion à un test</h1>
  			<div class="form-group">
    			<label for="exampleFormControlSelect1">Tests</label>
    			<select class="form-control" id="idTest" name="idTest">
    				<c:forEach items="${tests}" var="test">
			      		<option value="${test.idTest}">${test.libelleTest}</option>
			      		</c:forEach>
			    </select>
  			</div>
  			<div class="form-group row">
  				<div class="col-9">
	  				<label for="example-date-input">Date de début de validité</label>
	    			<input class="form-control" type="date" name="dateDebutValidite" id="dateDebutValidite">
    			</div>
    			<div class="col-3">
    				<label for="example-date-input">Heure</label>
    				<input class="form-control" type="time" name="heureDebut" id="example-time-input">
  				</div>
			</div>
			<div class="form-group row">
				<div class="col-9">
	  				<label for="example-date-input">Date de fin de validité</label>
	    			<input class="form-control" type="date" name="dateFinValidite" id="dateFinValidite">
    			</div>
    			<div class="col-3">
    				<label for="example-date-input">Heure</label>
    				<input class="form-control" type="time" name="heureFin" id="example-time-input">
  				</div>
			</div>
  			<div class="form-group">
    			<label for="exampleFormControlSelect1">Promotions</label>
    			<select class="form-control" id="idPromotion" name="idPromotion">
    				<c:forEach items="${promotions}" var="promotion">
			      		<option value="${promotion.idPromotion}"> ${promotion.libellePromotion}</option>
			      	</c:forEach>
			    </select>
  			</div>		
  			<div class="row justify-content-center">
				<div class="col-2">
					<button type="submit" class="btn btn-primary style_bouton">Inscrire</button>	
				</div>
			</div>
		</div>
	</form>
</body>
</html>