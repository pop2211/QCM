package fr.eni.tp.qcm.bo;

public class QuestionTirage {

	private Boolean estMarque;
	private int numOrdre;
	private Epreuve epreuve;
	private Question question;
	
	public QuestionTirage() {
		super();
	}

	public QuestionTirage(Boolean estMarque, int numOrdre, Epreuve epreuve, Question question) {
		super();
		this.estMarque = estMarque;
		this.numOrdre = numOrdre;
		this.epreuve = epreuve;
		this.question = question;
	}

	public Boolean getEstMarque() {
		return estMarque;
	}

	public void setEstMarque(Boolean estMarque) {
		this.estMarque = estMarque;
	}

	public int getNumOrdre() {
		return numOrdre;
	}

	public void setNumOrdre(int numOrdre) {
		this.numOrdre = numOrdre;
	}

	public Epreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	
}
