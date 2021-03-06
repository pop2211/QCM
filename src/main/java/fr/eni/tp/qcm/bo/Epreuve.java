package fr.eni.tp.qcm.bo;

import java.sql.Time;
import java.util.Date;

public class Epreuve {
	
	private Integer idEpreuve;
	private Test test;
	private String dateDebutValidite;
	private String dateFinValidite;
	private Time tempsEcoule;
	private String etat;
	private Float noteObtenue;
	private String niveauObtenu;
	private Utilisateur utilisateur;
	
	public Epreuve(Test test, String dateDebutValidite, String dateFinValidite, Time tempsEcoule, String etat, Float noteObtenue, String niveauObtenu, Utilisateur utilisateur) {
		this.test = test;
		this.dateDebutValidite = dateDebutValidite;
		this.dateFinValidite = dateFinValidite;
		this.tempsEcoule = tempsEcoule;
		this.etat = etat;
		this.noteObtenue = noteObtenue;
		this.niveauObtenu = niveauObtenu;
		this.utilisateur = utilisateur;
	}
	
	public Epreuve() {
		
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
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

	public String getDateDebutValidite() {
		return dateDebutValidite;
	}

	public void setDateDebutValidite(String dateDebutValidite) {
		this.dateDebutValidite = dateDebutValidite;
	}

	public String getDateFinValidite() {
		return dateFinValidite;
	}

	public void setDateFinValidite(String dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}

	public Time getTempsEcoule() {
		return tempsEcoule;
	}

	public void setTempsEcoule(Time tempsEcoule) {
		this.tempsEcoule = tempsEcoule;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Float getNoteObtenue() {
		return noteObtenue;
	}

	public void setNoteObtenue(Float noteObtenue) {
		this.noteObtenue = noteObtenue;
	}

	public String getNiveauObtenu() {
		return niveauObtenu;
	}

	public void setNiveauObtenu(String niveauObtenu) {
		this.niveauObtenu = niveauObtenu;
	}
}
