<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" isErrorPage="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark" role="navigation">
    <div class="container">
        <a class="navbar-brand" href='${param.contextPath}/accueil'>Plateforme ECF</a>
        <div class="collapse navbar-collapse" id="exCollapsingNavbar">
            <ul class="nav navbar-nav">
                <li class="nav-item">
                	<a class="nav-link" href='${param.contextPath}/gestion/consulterResultat'>Consulter les résultats</a>
                </li>
                <li class="nav-item">
                	<a class="nav-link" href='${param.contextPath}/selectionnerTest'>Choix du test</a>
                </li>
            </ul>
            <ul class="nav navbar-nav flex-row justify-content-between ml-auto">
               	<li class="nav-item"><a class="nav-link" href='${param.contextPath}/logout'>Déconnexion</a></li>
            </ul>
        </div>
    </div>
</nav>
	
	<style>

	    button:focus {
		    outline: 0;
		}
		
		.navbar .dropdown-menu .form-control {
		    width: 200px;
		}
	</style>