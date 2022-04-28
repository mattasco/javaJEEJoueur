package com.mycompany.beans;

public class Joueur {
	
	private int id;
	private String nom;
	private String prenom;
	private String sexe;
	
	public Joueur() {
		super();
	}
	public Joueur(int id, String nom, String prenom, String sexe) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) throws BeanException{
		if(nom.length()>20) {
			throw new BeanException("Le nom est trop grand");
		}else this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) throws BeanException{
		if(prenom.length()>20) {
			throw new BeanException("Le prenom est trop grand");
		}else this.prenom = prenom;
	}
	
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

}
