package fr.eni.tp.qcm.ihm.controler.resultat;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.tp.qcm.bll.factory.ManagerFactory;
import fr.eni.tp.qcm.bll.manager.EpreuveManager;
import fr.eni.tp.qcm.bo.Epreuve;
import fr.eni.tp.qcm.bo.Utilisateur;
import fr.eni.tp.qcm.utils.AppConstants;
import fr.eni.tp.web.common.HttpStatus;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public class ResultatControler extends HttpServlet {
	
	private EpreuveManager epreuveManager = ManagerFactory.epreuveManager();
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		List<Epreuve> epreuves = null;
		try {
			HttpSession session = request.getSession();
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("sessionUtilisateur");
			
			epreuves = epreuveManager.findByUtilAndStatut(utilisateur.getIdUtilisateur(), AppConstants.ETAT_EPREUVE_TERMINE);
            request.setAttribute("epreuves", epreuves);
            request.getRequestDispatcher("/resultatsJSP").forward(request, response);
               
        } catch (ManagerException e) {
        	e.printStackTrace();
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (ElementNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
