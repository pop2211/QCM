package fr.eni.tp.qcm.bo;

public class ReponseTirage {

	private Question question;
	private Proposition proposition;
	private Epreuve epreuve;

	public ReponseTirage() {
		super();
	}

	public ReponseTirage(Question question, Proposition proposition, Epreuve epreuve) {
		super();
		this.question = question;
		this.proposition = proposition;
		this.epreuve = epreuve;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Proposition getProposition() {
		return proposition;
	}

	public void setProposition(Proposition proposition) {
		this.proposition = proposition;
	}

	public Epreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}

}
