package fr.eni.tp.qcm.bo;

public class Test {

	private Integer idTest;
	private String libelleTest;
	private String description;
	private int duree; // a convertir ?
	private int seuilHaut;
	private int SeuilBas;
	
	public Test(Integer idTest, String libelleTest, String description, int duree, int seuilHaut, int seuilBas) {
		this.idTest = idTest;
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

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
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
