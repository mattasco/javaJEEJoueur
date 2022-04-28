package com.mycompany.dao;

import java.util.List;

import com.mycompany.beans.DaoException;
import com.mycompany.beans.Tournoi;

public interface TournoiDao {
	void ajouter(Tournoi tournoi)throws DaoException;
	List<Tournoi> lister();
	Tournoi lecture(int id);
	void modifier(Tournoi tournoi) throws DaoException;
	void supprimer(int id);
	List<Tournoi> rechercher (String nom);
}
