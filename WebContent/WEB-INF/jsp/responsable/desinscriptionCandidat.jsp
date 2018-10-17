<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href='/QCM/css/style.css'>
<title>Désinscription d'un candidat</title>
<jsp:include page="/WEB-INF/jsp/commons/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/commons/header.jsp">
		<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
	</jsp:include>
	<form method="post" action="/QCM/responsable/desinscriptionCandidat">
		<div class="container">		
			<h1 class="titre">Désinscription d'un candidat à un test</h1>
  			<div class="form-group">
    			<label for="exampleFormControlSelect1">Tests</label>
    			<select class="form-control" id="exampleFormControlSelect1" name="idTest">
    				<c:forEach items="${tests}" var="test">
			      		<option value="${test.idTest}">${test.libelleTest}</option>
			      		</c:forEach>
			    </select>
  			</div>
  			<div class="form-group">
    			<label for="exampleFormControlSelect1">Candidats</label>
    			<select class="form-control" id="exampleFormControlSelect1" name="idUtilisateur">
    				<c:forEach items="${utilisateurs}" var="utilisateur">
			      		<option value="${utilisateur.idUtilisateur}"> ${utilisateur.prenomUtilisateur} ${utilisateur.nomUtilisateur}</option>
			      	</c:forEach>
			    </select>
  			</div>		
  			<div class="row justify-content-center">
				<div class="col-2">
					<button type="submit" class="btn btn-primary style_bouton">Désinscrire</button>	
				</div>
			</div>
		</div>
	</form>
</body>
</html>