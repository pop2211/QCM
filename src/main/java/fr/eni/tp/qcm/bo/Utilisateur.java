package fr.eni.tp.qcm.bo;

public class Utilisateur {
	
	private Integer idUtilisateur;
	private Profil profil;
	private Promotion promotion;
	private String nomUtilisateur;
	private String prenomUtilisateur; 
	private String email; 
	private String password;

	public Utilisateur() {
	
	}
	
	public Utilisateur(String login, String mdp) {
		this.email = login;
		this.password = mdp;
	}

	public Utilisateur(Integer idUtilisateur, Profil profil, Promotion promotion, String nomUtilisateur, String prenomUtilisateur, String email, String password) {
		this.idUtilisateur = idUtilisateur;
		this.profil = profil;
		this.promotion = promotion;
		this.nomUtilisateur = nomUtilisateur;
		this.prenomUtilisateur = prenomUtilisateur;
		this.email = email;
		this.password = password;
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getPrenomUtilisateur() {
		return prenomUtilisateur;
	}

	public void setPrenomUtilisateur(String prenomUtilisateur) {
		this.prenomUtilisateur = prenomUtilisateur;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
