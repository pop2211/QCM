package fr.eni.tp.qcm.bo;

public class Proposition {
	
	private Integer idProposition;
	private String enonce;
	private boolean estBonne;
	
	public Proposition(Integer idProposition, String enonce, boolean estBonne) {
		super();
		this.idProposition = idProposition;
		this.enonce = enonce;
		this.estBonne = estBonne;
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
	
	

}
