package fr.eni.tp.qcm.bo;

public class Proposition {
	
	private Integer idProposition;
	private String enonce;
	private boolean estBonne;
	private Question question;
	private Boolean checked;
	
	public Proposition(Integer idProposition, String enonce, boolean estBonne, Question question) {
		super();
		this.idProposition = idProposition;
		this.enonce = enonce;
		this.estBonne = estBonne;
		this.question = question;
		this.checked= false;
	}

	public Proposition() {
	}

	public Integer getIdProposition() {
		return idProposition;
	}

	public void setIdProposition(Integer idProposition) {
		this.idProposition = idProposition;
	}

	public String getEnonce() {
		return enonce;
	}

	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	public boolean isEstBonne() {
		return estBonne;
	}

	public void setEstBonne(boolean estBonne) {
		this.estBonne = estBonne;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	
	

}
