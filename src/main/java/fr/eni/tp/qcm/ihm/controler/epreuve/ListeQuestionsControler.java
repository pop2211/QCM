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
import fr.eni.tp.qcm.bll.manager.ReponseTirageManager;
import fr.eni.tp.qcm.bo.Proposition;
import fr.eni.tp.qcm.bo.QuestionTirage;
import fr.eni.tp.qcm.bo.ReponseTirage;
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
	private ReponseTirageManager reponseTirageManager = ManagerFactory.reponseTirageManager();

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
			List<Proposition> propositions = null;
			List<ReponseTirage> reponseTirage = null;

			
			for(int i = 0; i < questionTirage.size(); i++) {
				reponseTirage = reponseTirageManager.findAllByQuestionAndEpreuve(questionTirage.get(i).getQuestion().getIdQuestion(), Integer.valueOf(epreuveId));
				
				propositions = propositionManager.findByQuestion((questionTirage.get(i).getQuestion().getIdQuestion()));
				for(int j = 0; j < propositions.size() ; j++) {
					for(int k = 0; k < reponseTirage.size(); k++) {
						if(propositions.get(j).getIdProposition() == reponseTirage.get(k).getProposition().getIdProposition()) {
							propositions.get(j).setChecked(true);;
						}
					}
				}
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
