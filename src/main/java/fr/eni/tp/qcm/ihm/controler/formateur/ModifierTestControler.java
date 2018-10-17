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
import fr.eni.tp.web.common.HttpStatus;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.exception.FunctionalException;

/**
 * Servlet implementation class ModifierTestControler
 */
public class ModifierTestControler extends HttpServlet {
	private TestManager testManager = ManagerFactory.testManager();
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idTest = request.getParameter("testId");
		try {
			Test test = testManager.findOne(Integer.valueOf(idTest));
			request.setAttribute("test", test);
			request.getRequestDispatcher("/formateur/modifierTestJSP").forward(request, response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		} catch (ManagerException e) {
			e.printStackTrace();
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idTest = request.getParameter("testId");
		String libelleTest = request.getParameter("libelleTest");
		String description = request.getParameter("descriptionTest");
		String duree = request.getParameter("dureeTest");
		String seuilHaut = request.getParameter("seuilHaut");
		String seuilBas = request.getParameter("seuilBas");
		
		try {
			String dureeTest = duree + ":00";
			Test test = testManager.findOne(Integer.valueOf(idTest));
			test.setLibelleTest(libelleTest);
			test.setDescription(description);
			test.setDuree(Time.valueOf(dureeTest));
			test.setSeuilHaut(Integer.valueOf(seuilHaut));
			test.setSeuilBas(Integer.valueOf(seuilBas));
			testManager.saveOne(test);
			request.setAttribute("modification","Modification enregistrée");
			request.getRequestDispatcher("/accueil" ).forward(request, response);
		} catch (NumberFormatException | ElementNotFoundException | ManagerException e) {
			e.printStackTrace();
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		} catch (FunctionalException e) {
			e.printStackTrace();
		}
	
		
	}

}
