package fr.eni.tp.qcm.ihm.controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.tp.qcm.utils.GenerateQuestions;

/**
 * Servlet implementation class QuestionController
 */
public class QuestionControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GenerateQuestions generateQuestions = new GenerateQuestions();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("salut");
		Integer idTest = 1;
		Integer idEpreuve = 1;

		generateQuestions.generate(idTest, idEpreuve);
		//generateQuestions.generate();
		//request.getRequestDispatcher("/question").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
