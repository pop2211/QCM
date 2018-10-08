package fr.eni.tp.qcm.bo;

public class Profil {
	
	private Integer idProfil;
	private String libelleProfil;
	
	public Profil(Integer idProfil, String libelleProfil) {
		this.idProfil = idProfil;
		this.libelleProfil = libelleProfil;
	}
	
	public Profil() {
		
	}

	public Integer getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(Integer idProfil) {
		this.idProfil = idProfil;
	}

	public String getLibelleProfil() {
		return libelleProfil;
	}

	public void setLibelleProfil(String libelleProfil) {
		this.libelleProfil = libelleProfil;
	}
}
