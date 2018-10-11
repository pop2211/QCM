package fr.eni.tp.qcm.bo;

public class SectionTest {
	private Integer nbQuestionsATirer;
	private Test test;
	private Theme theme;
	
	public SectionTest() {
		super();
	}
	
	public SectionTest(Integer nbQuestionsATirer, Test test, Theme theme) {
		super();
		this.nbQuestionsATirer = nbQuestionsATirer;
		this.test = test;
		this.theme = theme;
	}

	public Integer getNbQuestionsATrier() {
		return nbQuestionsATirer;
	}

	public void setNbQuestionsATirer(Integer nbQuestionsATirer) {
		this.nbQuestionsATirer = nbQuestionsATirer;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
	
	
}
