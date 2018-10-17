<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
		  	<div class="col-9 offset-1">
		  		<h2 class="text-center" style="margin-bottom: 2%;">Répondez aux questions : </h2>
				<div style="margin: 2%;">
					<form method="GET" action="/QCM/epreuve/listeQuestions">
						<button type="submit" class="btn btn-primary card-link">
			 			<i class="fas fa-list-ul"></i> Voir l'ensemble des questions
			 		</button>
					</form>
				</div>
				<div style="margin: 2%;">
					<form method="GET" action="/QCM/epreuve/finTest">
						<button type="submit" class="btn btn-primary card-link">
			 			<i class="fas fa-kiwi-bird"></i> Terminer le test
			 		</button>
					</form>
				</div>
	
					<div class="card text-center" style="margin-bottom:5%; padding: 1em;">
			    		<h5 class="card-title">${question.getQuestion().getEnonce() }</h5>
					  	<div class="card-body">
					  		<div class="container">
 								<form method="POST" action="/QCM/epreuve/questions">
									<input type="hidden" name="questionId" value="${question.getQuestion().getIdQuestion()}">
							  		<div class="row">
									  	<c:forEach var="proposition" items="${question.getQuestion().getPropositions()}" varStatus="loop">
									   		<div class="col">
						   			  			<div class="form-group form-check">
							   			  			<c:if test="${loop.index == 0}">
						   			  					<c:if test="${proposition.getChecked()}">
			  												<input type="hidden" class="form-check-input" name="checkbox0" value="${proposition.getIdProposition()}" id="checkbox">	
			  												<input type="checkbox" class="form-check-input" name="checkbox0delete" checked value="${proposition.getIdProposition()}" id="checkbox">
											    			<label class="form-check-label" for="exampleCheck1"><c:out value="${proposition.getEnonce()}" ></c:out></label>
						    							</c:if>
						   			  					<c:if test="${!proposition.getChecked()}">
			  												<input type="checkbox" class="form-check-input" name="checkbox0" value="${proposition.getIdProposition()}" id="checkbox">
											    			<label class="form-check-label" for="exampleCheck1"><c:out value="${proposition.getEnonce()}" ></c:out></label>
						    							</c:if>
						    						</c:if>
   				   			  						<c:if test="${loop.index == 1}">
						   			  					<c:if test="${proposition.getChecked()}">
			  												<input type="hidden" class="form-check-input" name="checkbox1" value="${proposition.getIdProposition()}" id="checkbox">	
												    		<input type="checkbox" class="form-check-input" name="checkbox1delete"  checked id="checkbox">
												    		<label class="form-check-label" for="exampleCheck1"><c:out value="${proposition.getEnonce()}" ></c:out></label>
						    							</c:if>
						   			  					<c:if test="${!proposition.getChecked()}">
												    		<input type="checkbox" class="form-check-input" name="checkbox1" value="${proposition.getIdProposition()}" id="checkbox">
												    		<label class="form-check-label" for="exampleCheck1"><c:out value="${proposition.getEnonce()}" ></c:out></label>
						    							</c:if>
						    						</c:if>
    								   			  	<c:if test="${loop.index == 2}">
						   			  					<c:if test="${proposition.getChecked()}">
			  												<input type="hidden" class="form-check-input" name="checkbox2" value="${proposition.getIdProposition()}" id="checkbox">	
												    		<input type="checkbox" class="form-check-input" name="checkbox2delete"  checked id="checkbox">
												    		<label class="form-check-label" for="exampleCheck1"><c:out value="${proposition.getEnonce()}" ></c:out></label>
							    						</c:if>
						    							<c:if test="${!proposition.getChecked()}">
												    		<input type="checkbox" class="form-check-input" name="checkbox2" value="${proposition.getIdProposition()}" id="checkbox">
												    		<label class="form-check-label" for="exampleCheck1"><c:out value="${proposition.getEnonce()}" ></c:out></label>
							    						</c:if>
						    						</c:if>
								  				</div>	
									   		</div>
								   		</c:forEach>
							   		</div>
							   		
							   		<div class="row">
								   		<div class="col">
										  	<a class="btn btn-link btn-lg" style="margin-top: 2%;">
										  		<button type="submit" class="btn btn-outline-success card-link">
										  			<i style="margin-right: 10px;" class="fas fa-check"></i> Valider
										  		</button>
									  		</a>
								   		</div>
								  	</div>
						  		</form>
						  	</div>
						  	
						  	<div style="margin-top: 2%;">
							  	<a class="btn btn-link btn-lg card-link">
	  								<form method="GET" action="/QCM/epreuve/questions">
										<input type="hidden" name="decrementNumQuestion" value="1">
								  		<button type="submit" class="btn btn-outline-primary card-link">
								  			Question précédente <i style="margin-left: 10px;" class="fas fa-arrow-left"></i>
								  		</button>
							  		</form>
						  		</a>
	  						  	<c:if test="${question.getEstMarque()}">
	  						  		<a class="btn btn-link btn-lg card-link">
		  								<form method="POST" action="/QCM/epreuve/questions">
											<input type="hidden" name="questionId" value="${question.getQuestion().getIdQuestion()}">
											<input type="hidden" name="removeFlag" value="1">
									  		<button type="submit" class="btn btn-danger card-link">
									  			<i class="fas fa-flag"></i>
									  		</button>
								  		</form>
									</a>
						  		</c:if>
						  		<c:if test="${!question.getEstMarque()}"> 
	  		 						<a class="btn btn-link btn-lg card-link">
		  								<form method=POST action="/QCM/epreuve/questions">
											<input type="hidden" name="questionId" value="${question.getQuestion().getIdQuestion()}">
											<input type="hidden" name="addFlag" value="1">
									  		<button type="submit" class="btn btn-outline-danger card-link">
									  			<i class="far fa-flag"></i>
									  		</button>
								  		</form>
							  		</a>
 						  		</c:if>
	  							<a class="btn btn-link btn-lg card-link">
							  		<form method="GET" action="/QCM/epreuve/questions">
										<input type="hidden" name="incrementNumQuestion" value="1">
								  		<button type="submit" class="btn btn-outline-primary card-link">
								  			<i style="margin-right: 10px;" class="fas fa-arrow-right"></i>  Question suivante
								  		</button>
							  		</form>
						  		</a>
				  			</div>
				  		</div>
					</div>
			</div>
			<div class="col-1">
				<div class="card" >
					<ul class="list-group">
						<c:forEach var="question" items="${questions}">
							<form method="GET" action="/QCM/epreuve/questions">
								<input type="hidden" name="numQuestion" value="${question.getNumOrdre()}">
								<button type="submit" class="btn btn-link">
								  <li class="list-group-item d-flex justify-content-between align-items-center">
							  			<div class="alert alert-info" role="alert" style="margin: 0;">
								 		 	${question.getNumOrdre()}
										    <c:if test="${question.getEstMarque()}">
		   								   		<span class="badge badge-primary badge-pill">
				    							<i class="fas fa-flag"></i>
				    							</span>
										    </c:if>
								    	</div>
					  				</li>
							  	</button>
				  			</form>
					  	</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>