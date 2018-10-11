package fr.eni.tp.qcm.ihm.controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.tp.qcm.bll.factory.ManagerFactory;
import fr.eni.tp.qcm.bll.manager.UtilisateurManager;
import fr.eni.tp.qcm.bo.Utilisateur;
import fr.eni.tp.web.common.HttpStatus;
import fr.eni.tp.web.common.bll.exception.ManagerException;


public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager = ManagerFactory.utilisateurManager();
	private static final Logger LOGGER = LoggerFactory.getLogger(Connexion.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("inputEmail");
		String password = request.getParameter("inputPassword");
		
		try {
			Utilisateur util = utilisateurManager.Connexion(email, password);
			if(util != null) {
				request.getRequestDispatcher("/accueil").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/connexion").forward(request, response);
			}
			
		} catch (ManagerException e) {
			e.printStackTrace();
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR, "Email ou password invalide");
		}
		
	}

}
