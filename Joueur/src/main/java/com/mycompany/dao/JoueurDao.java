package com.mycompany.dao;

import java.util.List;

import com.mycompany.beans.DaoException;
import com.mycompany.beans.Joueur;

public interface JoueurDao {
	void ajouter(Joueur joueur)throws DaoException;
	List<Joueur> lister();
	Joueur lecture(int id);
	void modifier(Joueur joueur) throws DaoException;
	void supprimer(int id);
	List<Joueur> rechercher (String nom);
	List<Joueur> rechercherVainqueurs (String nom);
	List<Joueur> rechercherFinalistes (String nom);
}