package fr.eni.tp.qcm.bo;

public class Question {
	
	private Integer idQuestion;
	private String enonce;
	private String media;
	private Integer points;
	
	
	public Question(Integer idQuestion, String enonce, String media, Integer points) {
		super();
		this.idQuestion = idQuestion;
		this.enonce = enonce;
		this.media = media;
		this.points = points;
	}
	
	public Question() {
	}

	public Integer getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(Integer idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getEnonce() {
		return enonce;
	}

	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
	
	
	
	

}
