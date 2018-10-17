package fr.eni.tp.qcm.ihm.controler.formateur;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eni.tp.qcm.bll.factory.ManagerFactory;
import fr.eni.tp.qcm.bll.manager.TestManager;
import fr.eni.tp.qcm.bo.Test;
import fr.eni.tp.qcm.ihm.controler.EpreuveControler;
import fr.eni.tp.web.common.HttpStatus;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.exception.FunctionalException;

/**
 * Servlet implementation class AjouterTestControler
 */
@WebServlet("/AjouterTestControler")
public class AjouterTestControler extends HttpServlet {
	private TestManager testManager = ManagerFactory.testManager();
	private static final Logger LOGGER = LoggerFactory.getLogger(EpreuveControler.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterTestControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/formateur/ajouterTestJSP").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String libelleTest = request.getParameter("libelleTest");
		String description = request.getParameter("descriptionTest");
		String duree = request.getParameter("dureeTest");
		String seuilHaut = request.getParameter("seuilHaut");
		String seuilBas = request.getParameter("seuilBas");	
	
		try {
			String dureeTest = duree + ":00";
			Test test = new Test(libelleTest,description,Time.valueOf(dureeTest),Integer.valueOf(seuilHaut),Integer.valueOf(seuilBas));
			testManager.saveOne(test);
			request.setAttribute("ajoutTest","Ajout du test réussit");
			request.getRequestDispatcher("/accueil" ).forward(request, response);
		} catch (ManagerException e) {
			e.printStackTrace();
        	LOGGER.error("Technical error", e);
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		} catch (FunctionalException e) {
			e.printStackTrace();
		}
	
	}

}
