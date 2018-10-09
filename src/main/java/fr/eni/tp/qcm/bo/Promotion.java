package fr.eni.tp.qcm.bo;

public class Promotion {
	
	private Integer idPromotion;
	private String libellePromotion;
	
	public Promotion(Integer idPromotion, String libellePromotion) {
		super();
		this.idPromotion = idPromotion;
		this.libellePromotion = libellePromotion;
	}
	
	public Promotion() {
		
	}

	public Integer getIdPromotion() {
		return idPromotion;
	}

	public void setIdPromotion(Integer idPromotion) {
		this.idPromotion = idPromotion;
	}

	public String getLibellePromotion() {
		return libellePromotion;
	}

	public void setLibellePromotion(String libellePromotion) {
		this.libellePromotion = libellePromotion;
	}
}
