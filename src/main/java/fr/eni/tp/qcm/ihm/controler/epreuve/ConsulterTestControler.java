package fr.eni.tp.qcm.ihm.controler.epreuve;

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
import fr.eni.tp.qcm.bll.manager.TestManager;
import fr.eni.tp.qcm.bo.Epreuve;
import fr.eni.tp.qcm.bo.Test;
import fr.eni.tp.qcm.bo.Utilisateur;
import fr.eni.tp.qcm.ihm.controler.EpreuveControler;
import fr.eni.tp.web.common.HttpStatus;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;

/**
 * Servlet implementation class ConsulterTestControler
 */
public class ConsulterTestControler extends HttpServlet {
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	private EpreuveManager epreuveManager = ManagerFactory.epreuveManager();
	private static final Logger LOGGER = LoggerFactory.getLogger(EpreuveControler.class);
	private static final long serialVersionUID = 1L;       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute(ATT_SESSION_USER);		
				
		List<Epreuve> epreuves = null;
		try {
			epreuves = epreuveManager.findAllByIdUtilisateur(utilisateur.getIdUtilisateur());
            request.setAttribute("epreuves", epreuves);
            request.getRequestDispatcher("/epreuvesJSP").forward(request, response);
               
        } catch (ManagerException | ElementNotFoundException e) {
        	e.printStackTrace();
        	LOGGER.error("Technical error", e);
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
