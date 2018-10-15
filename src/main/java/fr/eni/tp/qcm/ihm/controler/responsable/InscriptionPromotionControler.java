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
import fr.eni.tp.qcm.bll.manager.PromotionManager;
import fr.eni.tp.qcm.bll.manager.TestManager;
import fr.eni.tp.qcm.bll.manager.UtilisateurManager;
import fr.eni.tp.qcm.bo.Epreuve;
import fr.eni.tp.qcm.bo.Promotion;
import fr.eni.tp.qcm.bo.Test;
import fr.eni.tp.qcm.bo.Utilisateur;
import fr.eni.tp.qcm.ihm.controler.EpreuveControler;
import fr.eni.tp.web.common.HttpStatus;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.exception.FunctionalException;

/**
 * Servlet implementation class InscriptionPromotionControler
 */
public class InscriptionPromotionControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TestManager testManager = ManagerFactory.testManager();
	private EpreuveManager epreuveManager = ManagerFactory.epreuveManager();
	private static final Logger LOGGER = LoggerFactory.getLogger(EpreuveControler.class);
	private UtilisateurManager utilisateurManager = ManagerFactory.utilisateurManager();
	private PromotionManager promotionManager = ManagerFactory.promotionManager();
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Test> tests = null;
		List<Promotion> promotions = null;
		try {
			tests = testManager.findAll();
			promotions = promotionManager.findAll();
            request.setAttribute("tests", tests);
            request.setAttribute("promotions", promotions);
            request.getRequestDispatcher("/responsable/inscriptionPromotionJSP").forward(request, response);
               
        } catch (ManagerException e) {
        	e.printStackTrace();
        	LOGGER.error("Technical error", e);
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idTest = request.getParameter("idTest");
		String idPromotion = request.getParameter("idPromotion");
		String dateDebutValidite = request.getParameter("dateDebutValidite");
		String dateFinValidite = request.getParameter("dateFinValidite");
		String heureDebut = request.getParameter("heureDebut");
		String heureFin = request.getParameter("heureFin");
			
		try {
			Test test = testManager.findOne(Integer.valueOf(idTest));
			if(test != null && idPromotion != null){
				List<Utilisateur> utilisateurs = utilisateurManager.findByIdPromotion(Integer.valueOf(idPromotion));
				String dateDebut = dateDebutValidite + " " + heureDebut + ":00";
				String dateFin = dateFinValidite + " " + heureFin + ":00";
				
				for(Utilisateur utilisateur : utilisateurs){
					Epreuve epreuve = new Epreuve(test,dateDebut, dateFin, null, "EA",0,0,utilisateur);
					epreuveManager.saveOne(epreuve);
				}											
				request.setAttribute("inscriptionPromotion","Inscription de la promotion réussite");
				request.getRequestDispatcher("/accueil" ).forward(request, response);
			}
		} catch (NumberFormatException | ElementNotFoundException | ManagerException e) {
			e.printStackTrace();
			LOGGER.error("Technical error", e);
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		} catch (FunctionalException e) {
			e.printStackTrace();
		}
	}

}
