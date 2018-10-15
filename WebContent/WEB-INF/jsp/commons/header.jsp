<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" isErrorPage="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href='${param.contextPath}/accueil'>Plateforme ECF</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item">
				<a class="nav-link" href='${param.contextPath}/resultats'>Consulter les résultats</a>
			</li>
			<c:if test="${sessionScope.sessionUtilisateur.profil.libelleProfil == 'eleve'}">
			<li class="nav-item">
				<a class="nav-link" href='${param.contextPath}/epreuves'>Choix du test</a>
			</li>
			</c:if>
			<c:if test="${sessionScope.sessionUtilisateur.profil.libelleProfil == 'responsable' || sessionScope.sessionUtilisateur.profil.libelleProfil == 'administrateur'}">
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Responsable
					</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href='${param.contextPath}/responsable/inscriptionCandidat'>Inscrire un candidat</a>
					<a class="dropdown-item" href='${param.contextPath}/responsable/inscriptionPromotion'>Inscrire une promotion</a>
				</li>
			 </c:if>
		</ul>
		<ul class="nav navbar-nav flex-row justify-content-between ml-auto">
           	<li class="nav-item"><a class="nav-link">${sessionScope.sessionUtilisateur.prenomUtilisateur} ${sessionScope.sessionUtilisateur.nomUtilisateur} ${sessionScope.sessionUtilisateur.profil.libelleProfil}</a></li>
           	<li class="nav-item"><a class="nav-link" href='${param.contextPath}/logout'>Déconnexion</a></li>
         </ul>
	</div>
</nav>
<div>
	
</div>