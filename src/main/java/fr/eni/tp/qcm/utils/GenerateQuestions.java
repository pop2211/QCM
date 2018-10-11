package fr.eni.tp.qcm.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.eni.tp.qcm.bll.factory.ManagerFactory;
import fr.eni.tp.qcm.bll.manager.QuestionManager;
import fr.eni.tp.qcm.bll.manager.SectionTestManager;
import fr.eni.tp.qcm.bll.manager.ThemeManager;
import fr.eni.tp.qcm.bo.Question;
import fr.eni.tp.qcm.bo.SectionTest;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public class GenerateQuestions {
    private static SectionTestManager sectionThemeManager = ManagerFactory.sectionThemeManager();
    private static QuestionManager questionManager = ManagerFactory.questionManager();

    private ThemeManager themeManager = ManagerFactory.themeManager();

		public void generate() {
		//themeManager.findOne(sectiontest.getTest());
		//Fluch sur le taleau des question pour chaques theme de section theme
		//Prendre le nombre de questions, les enregister dans question tirage

		Integer testId = 1;
		try {
			//find all sections test with the test id
			List<SectionTest> sectionTest = sectionThemeManager.selectByIdTest(testId);
			List<Integer> listquestions =  new ArrayList<Integer>();
			List<Integer> finalList =  new ArrayList<Integer>();

			//loop over sections
			for (int i = 0; i < sectionTest.size(); i++) {
				//find all questions of the id theme
				List<Question> questions =  questionManager.findByIdTheme(sectionTest.get(i).getTheme().getIdTheme());
				if(!questions.isEmpty()) {
					//loop over list of questions that match and create an new array with questions id
					for(int j = 0; j < questions.size(); j++) {
						listquestions.add(questions.get(j).getIdQuestion());
					}
					
					//shuffle the array
					Collections.shuffle(listquestions);
					int nbQuestions = sectionTest.get(i).getNbQuestionsATrier();
					//If the list is not enough huge, it take the size of the list instead of the number of questions
					if(listquestions.size() < nbQuestions ) {
						nbQuestions = listquestions.size();
					}
					
					for(int k = 0; k < nbQuestions; k++) {
						finalList.add(listquestions.get(k));
					}
				}
			}
			
			//Save final list into questions generated
			System.out.println(finalList);
			
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		} catch (ManagerException e) {
			e.printStackTrace();
		}
	}
}