package fr.eni.tp.qcm.bo;

public class Theme {
	
	private Integer idTheme;
	private String libelleTheme;
	
	public Theme(Integer idTheme, String libelleTheme) {
		super();
		this.idTheme = idTheme;
		this.libelleTheme = libelleTheme;
	}
	
	public Theme() {
	}

	public Integer getIdTheme() {
		return idTheme;
	}

	public void setIdTheme(Integer idTheme) {
		this.idTheme = idTheme;
	}

	public String getLibelleTheme() {
		return libelleTheme;
	}

	public void setLibelleTheme(String libelleTheme) {
		this.libelleTheme = libelleTheme;
	}
	
	

}
