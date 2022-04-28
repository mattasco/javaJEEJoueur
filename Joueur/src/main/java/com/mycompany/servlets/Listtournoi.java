package com.mycompany.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.JoueurDaoImpl;
import com.mycompany.dao.TournoiDao;
import com.mycompany.dao.TournoiDaoImpl;

@WebServlet("/Listtournoi")
public class Listtournoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TournoiDao tournoiDao;
       

    public Listtournoi() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session=request.getSession(true);
		if(session.getAttribute("connectedUser")==null) {
			response.sendRedirect("/Joueur/login");
			return;
		}
		request.setAttribute("tournois", tournoiDao.lister());
		this.getServletContext().getRequestDispatcher("/WEB-INF/listtournoi.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		if(request.getParameter("action").equals("Rechercher")) {
			if(tournoiDao.rechercher(request.getParameter("txttournoi")).size()==0){
				request.setAttribute("nboccurence", 0);
			}
			request.setAttribute("tournois", tournoiDao.rechercher(request.getParameter("txttournoi")));
			this.getServletContext().getRequestDispatcher("/WEB-INF/listtournoi.jsp").forward(request, response);
		}else if(request.getParameter("action1").equals("Deconnexion")) {
			session.setAttribute("connectedUser", null);
			response.sendRedirect("/Joueur/login");
			return;
		}
	}
	
	public void init() throws ServletException {
		DaoFactory daoFactory= DaoFactory.getInstance();
		tournoiDao=new TournoiDaoImpl(daoFactory);
	}

}
