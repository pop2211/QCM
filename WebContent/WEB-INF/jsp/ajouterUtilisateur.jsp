<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href='/QCM/css/style.css'>
<title>Ajouter un utilisateur</title>
<jsp:include page="/WEB-INF/jsp/commons/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/commons/header.jsp">
		<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
	</jsp:include>
	<form method="post" action="/QCM/ajouterUtilisateur">
		<div class="container">
			<c:if test="${sessionScope.sessionUtilisateur.profil.libelleProfil == 'responsable' || sessionScope.sessionUtilisateur.profil.libelleProfil == 'formateur'}">		
				<h1 class="titre">Ajouter un candidat</h1>
			</c:if>	
			<c:if test="${sessionScope.sessionUtilisateur.profil.libelleProfil == 'administrateur'}">
				<h1 class="titre">Ajouter un utilisateur</h1>
			</c:if>
			<div class="form-group row">
  				<label for="example-text-input">Nom</label>
    			<input class="form-control" type="text" id="nomUtilisateur" name="nomUtilisateur">
			</div>
			<div class="form-group row">
  				<label for="example-text-input">Prénom</label>
    			<input class="form-control" type="text" id="prenomUtilisateur" name ="prenomUtilisateur">
			</div>
			<div class="form-group row">
  				<label for="example-email-input">Email</label>
    			<input class="form-control" type="email" id="emailUtilisateur" name="emailUtilisateur">
			</div>
			<div class="form-group row">
  				<label for="example-password-input">Mot de passe</label>
    			<input class="form-control" type="password" id="mdpUtilisateur" name="mdpUtilisateur">
			</div>
			
			<c:if test="${sessionScope.sessionUtilisateur.profil.libelleProfil == 'responsable' || sessionScope.sessionUtilisateur.profil.libelleProfil == 'formateur'}">
				<div class="form-group">
    				<label id="lblPromotionCandidat" for="exampleFormControlSelect1">Promotion</label>
    				<select class="form-control" id="idPromotionCandidat" name="idPromotionCandidat">
    					<option value="0">Aucune</option>
    					<c:forEach items="${promotions}" var="promotion">
			      			<option value="${promotion.idPromotion}"> ${promotion.libellePromotion}</option>
			      		</c:forEach>
			    </select>
  			</div>	
			</c:if>
			
			<c:if test="${sessionScope.sessionUtilisateur.profil.libelleProfil == 'administrateur'}">
				<div class="form-group">
	    			<label for="exampleFormControlSelect1">Profil</label>
	    			<select class="form-control" id="idProfil" name="idProfil">
	    				<c:forEach items="${profils}" var="profil">
				      		<option value="${profil.idProfil}">${profil.libelleProfil}</option>
				      		</c:forEach>
				    </select>
  				</div>
  				<div class="form-group">
    				<label id="lblPromotion" for="exampleFormControlSelect1">Promotion</label>
    				<select class="form-control" id="idPromotion" name="idPromotion">
    					<option value="0">Aucune</option>
    					<c:forEach items="${promotions}" var="promotion">
			      			<option value="${promotion.idPromotion}"> ${promotion.libellePromotion}</option>
			      		</c:forEach>
			    	</select>
  				</div>	
			</c:if>
					
  				
  			<div class="row justify-content-center">
				<div class="col-2">
					<button type="submit" class="btn btn-primary style_bouton">Ajouter</button>	
				</div>
			</div>
		</div>
	</form>
</body>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js" /></script>
<script type="text/javascript">
$(document).ready(function() {
 
	$('#lblPromotion').hide(); // on cache le champ par défaut
    $('#idPromotion').hide(); 
        
    $('select[name="idProfil"]').change(function() { // lorsqu'on change de valeur dans la liste
    var valeur = $(this).val(); // valeur sélectionnée
     
        if(valeur != '') { // si non vide
            if(valeur == 4) { // si 4 : eleve
            	$('#lblPromotion').show();
                $('#idPromotion').show();
            } else {
            	$('#lblPromotion').hide();
                $('#idPromotion').hide();           
            }
        }
    });
 
});
</script>




</html>