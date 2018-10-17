package fr.eni.tp.qcm.utils;

public class AppConstants {
	 
	public static final String MODEL_INFO_MESSAGE = "infoMessage";
	public static final String MODEL_ERROR_MESSAGE = "errorMessage";
	public static final String UTF8 = "UTF-8";
	
	public static final String ETAT_EPREUVE_EN_ATTENTE = "EA";
	public static final String ETAT_EPREUVE_TERMINE = "T";
	public static final String ETAT_EPREUVE_EN_COURS = "EC";
	
	public enum Profil {
		ADMINISTRATEUR("Administrateur"),
		RESPONSABLE("Responsable"),
		FORMATEUR("Formateur"),
		ELEVE("Eleve");
	
		private String name = "";
		
		//Constructeur
		Profil(String name){
			this.name = name;
		}
		
		public String toString(){
			return name;
		}
	}
	
	public enum StatutEpreuve {
		ATTENTE("EA"),
		TERMINE("T"),
		EN_COURS("EC");
	
		private String name = "";
		
		//Constructeur
		StatutEpreuve(String name){
			this.name = name;
		}
		
		public String toString(){
			return name;
		}
	}
	
	public enum niveau{
		ACQUIS("A"),
		EN_COURS_ACQUISITION("ECA"),
		NON_ACQUIS("NA");
	
		private String name = "";
		
		//Constructeur
		niveau(String name){
			this.name = name;
		}
		
		public String toString(){
			return name;
		}
	}
		
			
}
