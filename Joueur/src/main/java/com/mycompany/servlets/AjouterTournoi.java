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
import com.mycompany.beans.Tournoi;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.JoueurDaoImpl;
import com.mycompany.dao.TournoiDaoImpl;

@WebServlet("/AjouterTournoi")
public class AjouterTournoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TournoiDaoImpl tournoiDao;
       

    public AjouterTournoi() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		if(session.getAttribute("connectedUser")==null) {
			response.sendRedirect("/Joueur/login");
			return;
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutertournoi.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Nom= request.getParameter("Nom");
		String Code= request.getParameter("Code");
		Tournoi tournoi=new Tournoi();
		try {
			tournoi.setNom(Nom);
			tournoi.setCode(Code);
			tournoiDao.ajouter(tournoi);
			response.sendRedirect("/Joueur/Listtournoi");
		}catch(BeanException | DaoException e) {
			request.setAttribute("erreur", e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutertournoi.jsp").forward(request, response);
		}
	}
	
	public void init() throws ServletException {
		DaoFactory daoFactory= DaoFactory.getInstance();
		tournoiDao=new TournoiDaoImpl(daoFactory);
	}

}
