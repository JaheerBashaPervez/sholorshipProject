package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAL.DataBaseAccess;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		DataBaseAccess db=new DataBaseAccess();
		boolean status=false;
		String username, password, acctype;
		username = request.getParameter("email");
		password = request.getParameter("password");
		acctype = request.getParameter("acctype");

		try{
		if (acctype.equals("student")) 
		{
				status=db.studentLogin(username, password);
				if(status)
				{
					session.setAttribute("studentID", username);
					response.sendRedirect("student.html");
				}
				else
				{
					response.sendRedirect("HomeLoginfailure.html");
				}
		}
		if (acctype.equals("admin")) 
		{
			status=db.adminLogin(username, password);
			if(status)
			{
				response.sendRedirect("admin.html");
			}
			else
			{
				response.sendRedirect("HomeLoginfailure.html");
			}
		}
		if (acctype.equals("instructor")) 
		{
			status=db.instructorLogin(username, password);
			if(status)
			{
				session.setAttribute("instructorID", username);
				response.sendRedirect("instructor.html");
			}
			else
			{
				response.sendRedirect("HomeLoginfailure.html");
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
