package fr.eni.tp.qcm.ihm.controler.formateur;

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
import fr.eni.tp.qcm.bll.manager.TestManager;
import fr.eni.tp.qcm.bo.Test;
import fr.eni.tp.web.common.HttpStatus;
import fr.eni.tp.web.common.bll.exception.ManagerException;

/**
 * Servlet implementation class ConsulterTestsControler
 */
public class ConsulterTestsControler extends HttpServlet {
	private TestManager testManager = ManagerFactory.testManager();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsulterTestsControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Test> tests = null;
		try {
			tests = testManager.findAll();
            request.setAttribute("tests", tests);
            request.getRequestDispatcher("/formateur/consulterTestsJSP").forward(request, response);
               
        } catch (ManagerException e) {
        	e.printStackTrace();
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
