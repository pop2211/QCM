<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Questions</title>
<jsp:include page="/WEB-INF/jsp/commons/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">

</head>
<body>

<jsp:include page="/WEB-INF/jsp/commons/header.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
<div style="margin-top: 2%;">
		  			
  	<div class="container-fluid">
	  	<div class="row">
		  	<div class="col-8 offset-2">
		  		<h2 class="text-center" style="margin-bottom: 2%;">Terminer le Test ? </h2>
				<div style="margin: 2%;">
				</div>
					<div class="card text-center" style="margin-top:10%; padding: 1em; min-height: 200px;">
			    		<h5 class="card-title">${question.getQuestion().getEnonce() }</h5>
					  	<div class="card-body">
					  		<div class="container">
 								<form method="POST" action="/QCM/epreuve/questions">
							  		<div class="row">
									 	
									 	<div class="col">
					 						<form method="GET" action="/QCM/epreuve/questions">
												<button type="submit" class="btn btn-lg btn-outline-primary card-link">
									 				<i class="fas fa-kiwi-bird"></i> Retourner au test
								 				</button>
											</form>
									 	</div>
									 	
									 	<div class="col">
										 	<form method="GET" action="/QCM/resultat">
												<button type="submit" class="btn btn-lg btn-outline-success card-link">
									 				<i class="fas fa-dragon"></i> Mettre fin au test
								 				</button>
											</form>
									 	</div>
							   		</div>
						  		</form>
						  	</div>
				  		</div>
					</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>