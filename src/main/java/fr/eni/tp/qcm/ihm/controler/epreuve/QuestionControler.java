package fr.eni.tp.qcm.ihm.controler.epreuve;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.tp.qcm.bll.factory.ManagerFactory;
import fr.eni.tp.qcm.bll.manager.PropositionManager;
import fr.eni.tp.qcm.bll.manager.QuestionTirageManager;
import fr.eni.tp.qcm.bo.Proposition;
import fr.eni.tp.qcm.bo.Question;
import fr.eni.tp.qcm.bo.QuestionTirage;
import fr.eni.tp.qcm.bo.Utilisateur;
import fr.eni.tp.qcm.utils.GenerateQuestions;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;

/**
 * Servlet implementation class QuestionController
 */
public class QuestionControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private QuestionTirageManager questionTirageManager = ManagerFactory.questionTirageManager();
    private PropositionManager propositionManager = ManagerFactory.propositionManager();
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
		
		System.out.println("get");		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String idTest = request.getParameter("idTest");
		String idEpreuve = request.getParameter("idEpreuve");
		request.setAttribute("idTest", request.getParameter("idTest"));
		request.setAttribute("idEpreuve", request.getParameter("idEpreuve"));
		String numQuestion = request.getParameter("numQuestion");
		System.out.println(request.getParameter("numQuestion"));

		
		
		
		try {
			List<QuestionTirage> questionTirage = questionTirageManager.findAllByEpreuve(Integer.valueOf(idEpreuve));
			if(questionTirage.isEmpty()) {
				generateQuestions.generate(Integer.valueOf(idTest), Integer.valueOf(idEpreuve));
			}
			if(numQuestion == null) {
				numQuestion = "0";
			}
			List<Proposition> propositions = null;
			for(int i = 0; i < questionTirage.size(); i++) {
				propositions = propositionManager.findByQuestion((questionTirage.get(i).getQuestion().getIdQuestion()));
				questionTirage.get(i).getQuestion().setPropositions(propositions);
			}
			QuestionTirage currentQuestion = questionTirage.get(Integer.parseInt(numQuestion));

			request.setAttribute("question", currentQuestion);
			request.setAttribute("questions", questionTirage);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		} catch (ManagerException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/epreuve/questionsJSP").forward(request, response);
	}

}
