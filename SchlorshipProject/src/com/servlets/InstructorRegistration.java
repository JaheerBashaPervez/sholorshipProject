package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAL.DataBaseAccess;

/**
 * Servlet implementation class InstructorRegistration
 */
@WebServlet("/InstructorRegistration")
public class InstructorRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String email,name,gender,city,password,repassword;
		email=request.getParameter("email");
		name=request.getParameter("name");
		gender=request.getParameter("gender");
		city=request.getParameter("City");
		password=request.getParameter("password");
		repassword=request.getParameter("repassword");
		
		if(password.equals(repassword))
		{
			DataBaseAccess db=new DataBaseAccess();
			try 
			{
				int x=db.instructorRegistration(email,name,gender,city,password);
				if(x>0)
				{
					
					out.print("<h2>value has been registered</h2>");
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
		else
		{
			out.print("<h2>password does not match</h2>");
		}
		
	}

}
