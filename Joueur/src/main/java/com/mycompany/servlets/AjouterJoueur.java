package com.mycompany.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.mycompany.beans.BeanException;
import com.mycompany.beans.DaoException;
import com.mycompany.beans.Joueur;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.JoueurDaoImpl;

@WebServlet("/AjouterJoueur")
public class AjouterJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private JoueurDaoImpl joueurDao;

    public AjouterJoueur() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		if(session.getAttribute("connectedUser")==null) {
			response.sendRedirect("/Joueur/login");
			return;
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterjoueur.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Nom= request.getParameter("Nom");
		String Prenom= request.getParameter("Prenom");
		String Sexe= request.getParameter("Sexe");
		Joueur joueur=new Joueur();
		try {
			joueur.setNom(Nom);
			joueur.setPrenom(Prenom);
			joueur.setSexe(Sexe);
			joueurDao.ajouter(joueur);
			response.sendRedirect("/Joueur/Listjoueur");
		}catch(BeanException | DaoException e) {
			request.setAttribute("erreur", e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterjoueur.jsp").forward(request, response);
		}
	}
	
	public void init() throws ServletException {
		DaoFactory daoFactory= DaoFactory.getInstance();
		joueurDao=new JoueurDaoImpl(daoFactory);
	}
}