package com.mycompany.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import com.mycompany.beans.User;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.UserDaoImpl;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDaoImpl userdaoipml;

    public Login() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("txtLogin");
		String password = request.getParameter("txtPassword");
		User user=userdaoipml.isValidLogin(login, password);
		HttpSession session= request.getSession();
		session.setAttribute("connectedUser", user);
		if(user != null) {
			response.sendRedirect("/Joueur/Listjoueur");
			
		}else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
	}
	
	@Override
	public void init() throws ServletException {
		DaoFactory daofactory=DaoFactory.getInstance();
		userdaoipml=new UserDaoImpl(daofactory);
	}
		

}
