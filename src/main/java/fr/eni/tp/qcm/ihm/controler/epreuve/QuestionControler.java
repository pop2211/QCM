package fr.eni.tp.qcm.ihm.controler.epreuve;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.tp.qcm.bll.factory.ManagerFactory;
import fr.eni.tp.qcm.bll.manager.EpreuveManager;
import fr.eni.tp.qcm.bll.manager.PropositionManager;
import fr.eni.tp.qcm.bll.manager.QuestionManager;
import fr.eni.tp.qcm.bll.manager.QuestionTirageManager;
import fr.eni.tp.qcm.bll.manager.ReponseTirageManager;
import fr.eni.tp.qcm.bo.Epreuve;
import fr.eni.tp.qcm.bo.Proposition;
import fr.eni.tp.qcm.bo.Question;
import fr.eni.tp.qcm.bo.QuestionTirage;
import fr.eni.tp.qcm.bo.ReponseTirage;
import fr.eni.tp.qcm.bo.Utilisateur;
import fr.eni.tp.qcm.utils.GenerateQuestions;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.exception.FunctionalException;

/**
 * Servlet implementation class QuestionController
 */
public class QuestionControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private QuestionTirageManager questionTirageManager = ManagerFactory.questionTirageManager();
    private PropositionManager propositionManager = ManagerFactory.propositionManager();
	private GenerateQuestions generateQuestions = new GenerateQuestions();
	private ReponseTirageManager reponseTirageManager = ManagerFactory.reponseTirageManager();
	private EpreuveManager epreuveManager = ManagerFactory.epreuveManager();
	private QuestionManager questionManager = ManagerFactory.questionManager();
	
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
		HttpSession session = request.getSession();

		String numQuestion =(String) session.getAttribute("numQuestion");

		String changeNumQuestion = request.getParameter("numQuestion");
		String incrementNumQuestion = request.getParameter("incrementNumQuestion");
		String decrementNumQuestion = request.getParameter("decrementNumQuestion");

		if(changeNumQuestion != null) {
			session.setAttribute("numQuestion", (String) changeNumQuestion);
			numQuestion =(String) session.getAttribute("numQuestion");

		}
		
		String epreuveId = (String) session.getAttribute("epreuveId");
		String testId = (String) session.getAttribute("testId");

		
		try {
			List<QuestionTirage> questionTirage = questionTirageManager.findAllByEpreuve(Integer.valueOf(epreuveId));
			if(questionTirage.isEmpty()) {
				generateQuestions.generate(Integer.valueOf(testId), Integer.valueOf(epreuveId));
			}
			List<Proposition> propositions = null;
			for(int i = 0; i < questionTirage.size(); i++) {
				propositions = propositionManager.findByQuestion((questionTirage.get(i).getQuestion().getIdQuestion()));
				questionTirage.get(i).getQuestion().setPropositions(propositions);
			}

			if(incrementNumQuestion != null) {
				numQuestion =(String) session.getAttribute("numQuestion");
				Integer intNum = Integer.valueOf(numQuestion);
				if( intNum < questionTirage.size()-1) {
					intNum++;
					session.setAttribute("numQuestion", String.valueOf(intNum));
				}else{
					session.setAttribute("numQuestion", "0");	
				}
				numQuestion =(String) session.getAttribute("numQuestion");
			}

			if(decrementNumQuestion != null) {
				numQuestion =(String) session.getAttribute("numQuestion");
				Integer intNum = Integer.valueOf(numQuestion);
				if( intNum > 0) {
					intNum--;
					session.setAttribute("numQuestion", String.valueOf(intNum));
				}else{
					session.setAttribute("numQuestion", String.valueOf(questionTirage.size()-1));	
				}
				numQuestion =(String) session.getAttribute("numQuestion");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		String proposition0 = request.getParameter("checkbox0");
		String proposition1 = request.getParameter("checkBox1");
		String proposition2 = request.getParameter("checkBox2");
		String questionId = request.getParameter("questionId");
		String epreuveId = (String) session.getAttribute("epreuveId");
		
		
		if(questionId != null) {
			System.out.println(proposition0);
			System.out.println(proposition1);
			System.out.println(proposition2);
			if(proposition0 != null) {
				Epreuve epreuve;
				Proposition proposition;
				Question question;
				try {
					epreuve = epreuveManager.findOne(Integer.valueOf(epreuveId));

					proposition = propositionManager.findOne(Integer.valueOf(proposition0));
					question = questionManager.findOne(Integer.valueOf(questionId));
					
					ReponseTirage reponseTirage = new ReponseTirage();
					reponseTirage.setEpreuve(epreuve);
					reponseTirage.setProposition(proposition);
					reponseTirage.setQuestion(question);
					reponseTirageManager.saveOne(reponseTirage);

				} catch (NumberFormatException | ElementNotFoundException | ManagerException e) {
					e.printStackTrace();
				} catch (FunctionalException e) {
					e.printStackTrace();
				}
			}
			
			if(proposition1 != null) {
				Epreuve epreuve;
				Proposition proposition;
				Question question;
				try {
					epreuve = epreuveManager.findOne(Integer.valueOf(epreuveId));

					proposition = propositionManager.findOne(Integer.valueOf(proposition1));
					question = questionManager.findOne(Integer.valueOf(questionId));
					
					ReponseTirage reponseTirage = new ReponseTirage();
					reponseTirage.setEpreuve(epreuve);
					reponseTirage.setProposition(proposition);
					reponseTirage.setQuestion(question);
					reponseTirageManager.saveOne(reponseTirage);

				} catch (NumberFormatException | ElementNotFoundException | ManagerException e) {
					e.printStackTrace();
				} catch (FunctionalException e) {
					e.printStackTrace();
				}
			}
			
			if(proposition2 != null) {
				Epreuve epreuve;
				Proposition proposition;
				Question question;
				try {
					epreuve = epreuveManager.findOne(Integer.valueOf(epreuveId));

					proposition = propositionManager.findOne(Integer.valueOf(proposition2));
					question = questionManager.findOne(Integer.valueOf(questionId));
					
					ReponseTirage reponseTirage = new ReponseTirage();
					reponseTirage.setEpreuve(epreuve);
					reponseTirage.setProposition(proposition);
					reponseTirage.setQuestion(question);
					reponseTirageManager.saveOne(reponseTirage);

				} catch (NumberFormatException | ElementNotFoundException | ManagerException e) {
					e.printStackTrace();
				} catch (FunctionalException e) {
					e.printStackTrace();
				}
			}
		}
		
		doGet(request, response);
	}

}
