package fr.eni.tp.qcm.utils;

import fr.eni.tp.qcm.bll.factory.ManagerFactory;
import fr.eni.tp.qcm.bll.manager.ThemeManager;
import fr.eni.tp.qcm.bo.SectionTest;
import fr.eni.tp.qcm.bo.Theme;

public class GenerateQuestions {

    private ThemeManager themeManager = ManagerFactory.themeManager();

	public String generate (SectionTest sectiontest) {
		
		//themeManager.findOne(sectiontest.getTest());
		//Fluch sur le taleau des question pour chaques theme de section theme
		//Prendre le nombre de questions, les supprimer du tab tempo et les enregister dans question tirage
		return null;
	}
}
