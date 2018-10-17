package fr.eni.tp.qcm.bo;

import java.sql.Time;

public class Test {

	private Integer idTest;
	private String libelleTest;
	private String description;
	private Time duree; 
	private int seuilHaut;
	private int SeuilBas;
	
	public Test(Integer idTest, String libelleTest, String description, Time duree, int seuilHaut, int seuilBas) {
		this.idTest = idTest;
		this.libelleTest = libelleTest;
		this.description = description;
		this.duree = duree;
		this.seuilHaut = seuilHaut;
		SeuilBas = seuilBas;
	}
	
	public Test(String libelleTest, String description, Time duree, int seuilHaut, int seuilBas) {
		this.libelleTest = libelleTest;
		this.description = description;
		this.duree = duree;
		this.seuilHaut = seuilHaut;
		SeuilBas = seuilBas;
	}
	
	public Test() {
		
	}

	public Integer getIdTest() {
		return idTest;
	}

	public void setIdTest(Integer idTest) {
		this.idTest = idTest;
	}

	public String getLibelleTest() {
		return libelleTest;
	}

	public void setLibelleTest(String libelleTest) {
		this.libelleTest = libelleTest;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Time getDuree() {
		return duree;
	}

	public void setDuree(Time duree) {
		this.duree = duree;
	}

	public int getSeuilHaut() {
		return seuilHaut;
	}

	public void setSeuilHaut(int seuilHaut) {
		this.seuilHaut = seuilHaut;
	}

	public int getSeuilBas() {
		return SeuilBas;
	}

	public void setSeuilBas(int seuilBas) {
		SeuilBas = seuilBas;
	}
	
}
