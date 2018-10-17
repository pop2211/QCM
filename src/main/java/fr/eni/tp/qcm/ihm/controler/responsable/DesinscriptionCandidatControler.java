package fr.eni.tp.qcm.ihm.controler.responsable;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.tp.qcm.bll.factory.ManagerFactory;
import fr.eni.tp.qcm.bll.manager.EpreuveManager;
import fr.eni.tp.qcm.bll.manager.TestManager;
import fr.eni.tp.qcm.bll.manager.UtilisateurManager;
import fr.eni.tp.qcm.bo.Epreuve;
import fr.eni.tp.qcm.bo.Test;
import fr.eni.tp.qcm.bo.Utilisateur;
import fr.eni.tp.web.common.HttpStatus;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;

/**
 * Servlet implementation class DesinscriptionCandidatControler
 */
public class DesinscriptionCandidatControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TestManager testManager = ManagerFactory.testManager();
	private EpreuveManager epreuveManager = ManagerFactory.epreuveManager();
	private UtilisateurManager utilisateurManager = ManagerFactory.utilisateurManager();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Test> tests = null;
		List<Utilisateur> utilisateurs = null;
		try {
			tests = testManager.findAll();
			utilisateurs = utilisateurManager.findAllCandidat();
            request.setAttribute("tests", tests);
            request.setAttribute("utilisateurs", utilisateurs);
            request.getRequestDispatcher("/responsable/desinscriptionCandidatJSP").forward(request, response);
               
        } catch (ManagerException e) {
        	e.printStackTrace();
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idTest = request.getParameter("idTest");
		String idUtilisateur = request.getParameter("idUtilisateur");
			
		try {
			if(idTest != null && idUtilisateur != null){
				Epreuve epreuve = epreuveManager.findByIdTestIdUtilisateur(Integer.valueOf(idTest), Integer.valueOf(idUtilisateur));
				epreuveManager.deleteOne(epreuve.getIdEpreuve());
				request.setAttribute("desinscription","Désinscription du candidat réussite");
				request.getRequestDispatcher("/accueil").forward(request, response);
			}
		} catch (NumberFormatException | ElementNotFoundException | ManagerException e) {
			e.printStackTrace();
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
