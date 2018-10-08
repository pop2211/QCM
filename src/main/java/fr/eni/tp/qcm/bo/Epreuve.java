package fr.eni.tp.qcm.bo;

import java.util.Date;

public class Epreuve {
	
	private Integer idEpreuve;
	private Test test;
	private Date dateDebutValidite;
	private Date dateFinValidite;
	private Integer tempsEcoule; // a convertir lors de l'impl
	private String etat;
	private Integer noteObtenue;
	private Integer niveauObtenu;
	
	public Epreuve(Integer idEpreuve, Test test, Date dateDebutValidite, Date dateFinValidite, Integer tempsEcoule, String etat, Integer noteObtenue, Integer niveauObtenu) {
		this.idEpreuve = idEpreuve;
		this.test = test;
		this.dateDebutValidite = dateDebutValidite;
		this.dateFinValidite = dateFinValidite;
		this.tempsEcoule = tempsEcoule;
		this.etat = etat;
		this.noteObtenue = noteObtenue;
		this.niveauObtenu = niveauObtenu;
	}
	
	public Epreuve() {
		
	}

	public Integer getIdEpreuve() {
		return idEpreuve;
	}

	public void setIdEpreuve(Integer idEpreuve) {
		this.idEpreuve = idEpreuve;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Date getDateDebutValidite() {
		return dateDebutValidite;
	}

	public void setDateDebutValidite(Date dateDebutValidite) {
		this.dateDebutValidite = dateDebutValidite;
	}

	public Date getDateFinValidite() {
		return dateFinValidite;
	}

	public void setDateFinValidite(Date dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}

	public Integer getTempsEcoule() {
		return tempsEcoule;
	}

	public void setTempsEcoule(Integer tempsEcoule) {
		this.tempsEcoule = tempsEcoule;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Integer getNoteObtenue() {
		return noteObtenue;
	}

	public void setNoteObtenue(Integer noteObtenue) {
		this.noteObtenue = noteObtenue;
	}

	public Integer getNiveauObtenu() {
		return niveauObtenu;
	}

	public void setNiveauObtenu(Integer niveauObtenu) {
		this.niveauObtenu = niveauObtenu;
	}
}
