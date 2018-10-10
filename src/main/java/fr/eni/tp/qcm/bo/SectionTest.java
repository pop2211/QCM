package fr.eni.tp.qcm.bo;

public class SectionTest {
	private Integer nbQuestionsATrier;
	private Test test;
	private Theme theme;
	
	public SectionTest() {
		super();
	}
	
	public SectionTest(Integer nbQuestionsATrier, Test test, Theme theme) {
		super();
		this.nbQuestionsATrier = nbQuestionsATrier;
		this.test = test;
		this.theme = theme;
	}

	public Integer getNbQuestionsATrier() {
		return nbQuestionsATrier;
	}

	public void setNbQuestionsATrier(Integer nbQuestionsATrier) {
		this.nbQuestionsATrier = nbQuestionsATrier;
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
