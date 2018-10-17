package fr.eni.tp.qcm.ihm.controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.tp.qcm.bll.factory.ManagerFactory;
import fr.eni.tp.qcm.bll.manager.EpreuveManager;
import fr.eni.tp.qcm.bll.manager.ProfilManager;
import fr.eni.tp.qcm.bll.manager.PromotionManager;
import fr.eni.tp.qcm.bll.manager.TestManager;
import fr.eni.tp.qcm.bll.manager.UtilisateurManager;
import fr.eni.tp.qcm.bo.Profil;
import fr.eni.tp.qcm.bo.Promotion;
import fr.eni.tp.qcm.bo.Test;
import fr.eni.tp.qcm.bo.Utilisateur;
import fr.eni.tp.web.common.HttpStatus;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.exception.FunctionalException;

/**
 * Servlet implementation class AjouterUtilisateurControler
 */
@WebServlet("/AjouterUtilisateurControler")
public class AjouterUtilisateurControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PromotionManager promotionManager = ManagerFactory.promotionManager();
	private ProfilManager profilManager = ManagerFactory.profilManager();
	private UtilisateurManager utilisateurManager = ManagerFactory.utilisateurManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterUtilisateurControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Profil> profils = null;
		List<Promotion> promotions = null;
		try {
			profils = profilManager.findAll();
			promotions = promotionManager.findAll();
            request.setAttribute("profils", profils);
            request.setAttribute("promotions", promotions);
            request.getRequestDispatcher("/ajouterUtilisateurJSP").forward(request, response);
               
        } catch (ManagerException e) {
        	e.printStackTrace();
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomUtilisateur = request.getParameter("nomUtilisateur");
		String prenomUtilisateur = request.getParameter("prenomUtilisateur");
		String emailUtilisateur = request.getParameter("emailUtilisateur");
		String mdpUtilisateur = request.getParameter("mdpUtilisateur");
		String idProfil = request.getParameter("idProfil");
		String idPromotion = request.getParameter("idPromotion");
		String idPromotionCandidat = request.getParameter("idPromotionCandidat");
		
		try {
			HttpSession session = request.getSession();
			Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("sessionUtilisateur");			
		
			if(utilisateurConnecte.getProfil().getLibelleProfil().equals("administrateur")){
				//si le choix de la promotion est aucune
				if(Integer.valueOf(idPromotion) == 0){
					idPromotion = null;
				}
				Profil profil = profilManager.findOne(Integer.valueOf(idProfil));
				Utilisateur utilisateur = null;
				if(idPromotion != null){
					Promotion promotion = promotionManager.findOne(Integer.valueOf(idPromotion));
					utilisateur = new Utilisateur(profil, promotion, nomUtilisateur, prenomUtilisateur, emailUtilisateur, mdpUtilisateur);
				} else{
					utilisateur = new Utilisateur(profil, null, nomUtilisateur, prenomUtilisateur, emailUtilisateur, mdpUtilisateur);
				}
				utilisateurManager.saveOne(utilisateur);
				
				request.setAttribute("ajout","Ajout de l'utilisateur réussit");
				request.getRequestDispatcher("/accueil").forward(request, response);
			}
			else if(utilisateurConnecte.getProfil().getLibelleProfil().equals("responsable") || utilisateurConnecte.getProfil().getLibelleProfil().equals("formateur")){
				//si le choix de la promotion est aucune
				if(Integer.valueOf(idPromotionCandidat) == 0){
					idPromotionCandidat = null;
				}
				Utilisateur utilisateurCandidat = null;
				Profil profilCandidat = profilManager.findOne(4);
				if(idPromotionCandidat != null){
					Promotion promotionCandidat = promotionManager.findOne(Integer.valueOf(idPromotionCandidat));
					utilisateurCandidat = new Utilisateur(profilCandidat, promotionCandidat, nomUtilisateur, prenomUtilisateur, emailUtilisateur, mdpUtilisateur);
				}else{
					utilisateurCandidat = new Utilisateur(profilCandidat, null, nomUtilisateur, prenomUtilisateur, emailUtilisateur, mdpUtilisateur);
				}
					utilisateurManager.saveOne(utilisateurCandidat);
				
				request.setAttribute("ajout","Ajout de l'utilisateur réussit");
				request.getRequestDispatcher("/accueil").forward(request, response);
			}

			
		} catch (NumberFormatException | ElementNotFoundException | ManagerException e) {
			e.printStackTrace();
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		} catch (FunctionalException e) {
			e.printStackTrace();
		}
		
		
	}

}
