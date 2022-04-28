package com.mycompany.beans;

public class Tournoi {
	private int id;
	private String nom;
	private String code;
	
	public Tournoi() {
		super();
	}
	public Tournoi(int id, String nom, String code) {
		super();
		this.id = id;
		this.nom = nom;
		this.code = code;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) throws BeanException{
		if(code.length()>2) {
			throw new BeanException("Le nom est trop grand");
		}else this.code = code;
	}
	
	
	
}
