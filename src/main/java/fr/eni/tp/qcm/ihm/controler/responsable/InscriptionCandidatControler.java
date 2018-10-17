package fr.eni.tp.qcm.ihm.controler.responsable;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import fr.eni.tp.web.common.exception.FunctionalException;

/**
 * Servlet implementation class InscriptionCandidatControler
 */
public class InscriptionCandidatControler extends HttpServlet {
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
            request.getRequestDispatcher("/responsable/inscriptionCandidatJSP").forward(request, response);
               
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
		String dateDebutValidite = request.getParameter("dateDebutValidite");
		String dateFinValidite = request.getParameter("dateFinValidite");
		String heureDebut = request.getParameter("heureDebut");
		String heureFin = request.getParameter("heureFin");
			
		try {
			Test test = testManager.findOne(Integer.valueOf(idTest));
			Utilisateur utilisateur = utilisateurManager.findOne(Integer.valueOf(idUtilisateur));
			if(test != null && utilisateur != null){
				String dateDebut = dateDebutValidite + " " + heureDebut + ":00";
				String dateFin = dateFinValidite + " " + heureFin + ":00";
				
				Epreuve epreuve = new Epreuve(test, dateDebut, dateFin, null, "EA", 0f, null, utilisateur);
				epreuveManager.saveOne(epreuve);
				request.setAttribute("inscription","Inscription du candidat réussite");
				request.getRequestDispatcher("/accueil" ).forward(request, response);
			}
		} catch (NumberFormatException | ElementNotFoundException | ManagerException e) {
			e.printStackTrace();
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		} catch (FunctionalException e) {
			e.printStackTrace();
		}
		
		
	}

}
