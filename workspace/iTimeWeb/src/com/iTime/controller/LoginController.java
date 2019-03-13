package com.iTime.controller;

import java.io.IOException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iTime.dao.UserDAO;
import com.iTime.dao.UserDAOImpl;
import com.iTime.model.User;

public class LoginController extends HttpServlet {

	private static final long serialVersionUID = -4602272917509602701L;
	private static final Logger logger = Logger.getLogger(LoginController.class);

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		String error;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		logger.debug(email);
		
		System.out.println("Email == " + email);
		
		RequestDispatcher rdObj = null;
		
		User user = new User();
		user.setEmail(email); 
		user.setPassword(password);
		
		HttpSession session = request.getSession();
		UserDAO userDAO = new UserDAOImpl();

		User userDB = userDAO.loginUser(user);
//
		if (userDB.getUserName() == null) {
			error = "Invalid Email or password";
			session.setAttribute("error", error);
			//response.sendRedirect("index.jsp");
			
			 rdObj = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
             rdObj.include(request, response);
		} else {
			session.setAttribute("user", userDB.getFirstName());
			session.removeAttribute("error");
		/*	response.sendRedirect("/WEB-INF/jsp/PercentHours.jsp");*/
			
			 rdObj = request.getRequestDispatcher("/WEB-INF/jsp/PercentHours.jsp");
             rdObj.include(request, response);
	}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if ("logout".equalsIgnoreCase(request.getParameter("param"))) {
			HttpSession session = request.getSession();
			if(session != null){
			session.invalidate();
			}
			response.sendRedirect("index.jsp");
		}
	}
}