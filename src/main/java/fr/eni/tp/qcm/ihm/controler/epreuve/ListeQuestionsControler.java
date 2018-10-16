package fr.eni.tp.qcm.ihm.controler.epreuve;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.tp.qcm.bll.factory.ManagerFactory;
import fr.eni.tp.qcm.bll.manager.PropositionManager;
import fr.eni.tp.qcm.bll.manager.QuestionTirageManager;
import fr.eni.tp.qcm.bo.Proposition;
import fr.eni.tp.qcm.bo.QuestionTirage;
import fr.eni.tp.qcm.utils.GenerateQuestions;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;

/**
 * Servlet implementation class ListeQuestionsControler
 */
public class ListeQuestionsControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GenerateQuestions generateQuestions = new GenerateQuestions();
    private QuestionTirageManager questionTirageManager = ManagerFactory.questionTirageManager();
    private PropositionManager propositionManager = ManagerFactory.propositionManager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeQuestionsControler() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String epreuveId = (String) session.getAttribute("epreuveId");

		try {
			List<QuestionTirage> questionTirage = questionTirageManager.findAllByEpreuve(Integer.valueOf(epreuveId));
//			for(int i = 0; i < questionTirage.size(); i++) {
//				List<Proposition> propositions = propositionManager.findByQuestion((questionTirage.get(i).getQuestion().getIdQuestion()));
//				questionTirage.get(i).getQuestion().setPropositions(propositions);
//	            
//				System.out.println(questionTirage.get(i).getQuestion().getEnonce());
//			}
			List<Proposition> propositions = null;
			for(int i = 0; i < questionTirage.size(); i++) {
				propositions = propositionManager.findByQuestion((questionTirage.get(i).getQuestion().getIdQuestion()));
				questionTirage.get(i).getQuestion().setPropositions(propositions);
			}
			
			request.setAttribute("questions", questionTirage);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		} catch (ManagerException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/epreuve/listeQuestionsJSP").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
