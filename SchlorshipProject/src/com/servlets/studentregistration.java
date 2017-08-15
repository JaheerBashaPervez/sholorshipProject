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


@WebServlet("/studentregistration")
public class studentregistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		long rollNo;
		String email,fname,lname,gender,city,password,repassword;
		rollNo=Long.parseLong(request.getParameter("rollno"));
		email=request.getParameter("email");
		fname=request.getParameter("fname");
		lname=request.getParameter("lname");
		gender=request.getParameter("gender");
		city=request.getParameter("City");
		password=request.getParameter("password");
		repassword=request.getParameter("repassword");
		
		if(password.equals(repassword))
		{
			DataBaseAccess db=new DataBaseAccess();
			try 
			{
				int x=db.studentRegistration(rollNo,email,fname,lname,gender,city,password);
				if(x>0)
				{
					
					response.sendRedirect("HomeLoginRegisterSuccess.html");
				}
			} 
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e) 
			{
			e.printStackTrace();
			}
		}
		else
		{
			out.print("HomeLoginfailure.html");
		}
		
		
		
	}

}
