package fr.eni.tp.qcm.ihm.controler.epreuve;

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
import fr.eni.tp.qcm.bll.manager.TestManager;
import fr.eni.tp.qcm.bo.Test;
import fr.eni.tp.qcm.ihm.controler.EpreuveControler;
import fr.eni.tp.web.common.HttpStatus;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;

/**
 * Servlet implementation class DetailTestControler
 */
public class DetailTestControler extends HttpServlet {
	private TestManager testManager = ManagerFactory.testManager();
	private static final Logger LOGGER = LoggerFactory.getLogger(EpreuveControler.class);
	private static final long serialVersionUID = 1L;  
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String testIdParam = request.getParameter("testId");
		String epreuveId= request.getParameter("epreuveId");
		
		 HttpSession session = request.getSession();
		 session.setAttribute("testId", testIdParam);
		 session.setAttribute("epreuveId", epreuveId);
		 session.setAttribute("numQuestion", "0");

		request.setAttribute("epreuveId", epreuveId);
		try {
			Test test = testManager.findOne(Integer.valueOf(testIdParam));
            request.setAttribute("test", test);
            request.getRequestDispatcher("/epreuve/detailTestJSP").forward(request, response);
               
        } catch (ManagerException | NumberFormatException | ElementNotFoundException e) {
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
