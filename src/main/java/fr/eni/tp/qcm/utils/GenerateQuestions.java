package fr.eni.tp.qcm.utils;

import java.util.List;

import fr.eni.tp.qcm.bll.factory.ManagerFactory;
import fr.eni.tp.qcm.bll.manager.SectionTestManager;
import fr.eni.tp.qcm.bll.manager.ThemeManager;
import fr.eni.tp.qcm.bo.SectionTest;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;

public class GenerateQuestions {
    private static SectionTestManager sectionThemeManager = ManagerFactory.sectionThemeManager();

    private ThemeManager themeManager = ManagerFactory.themeManager();

		public void generate(SectionTest sectiontest) {
			
		//themeManager.findOne(sectiontest.getTest());
		//Fluch sur le taleau des question pour chaques theme de section theme
		//Prendre le nombre de questions, les supprimer du tab tempo et les enregister dans question tirage

		int testId = 1;
		try {
			List<SectionTest> sectionTest = sectionThemeManager.selectByIdTest(testId);
			for (int i = 0; i < sectionTest.size(); i++) {
				
				System.out.println(sectionTest.get(i));
			}
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		} catch (ManagerException e) {
			e.printStackTrace();
		}
	}
}