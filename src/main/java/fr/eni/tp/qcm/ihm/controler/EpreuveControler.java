package fr.eni.tp.qcm.ihm.controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.tp.qcm.bll.factory.ManagerFactory;
import fr.eni.tp.qcm.bll.manager.EpreuveManager;
import fr.eni.tp.qcm.bo.Epreuve;
import fr.eni.tp.web.common.HttpStatus;
import fr.eni.tp.web.common.bll.exception.ManagerException;

/**
 * Servlet implementation class EpreuveController
 */
public class EpreuveControler extends HttpServlet {
	
	private EpreuveManager epreuveManager = ManagerFactory.epreuveManager();
	private static final Logger LOGGER = LoggerFactory.getLogger(EpreuveControler.class);
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		List<Epreuve> epreuves = null;
		try {
			epreuves = epreuveManager.findAll();
            request.setAttribute("epreuves", epreuves);
            request.getRequestDispatcher("/consulterResultat").forward(request, response);
               
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
