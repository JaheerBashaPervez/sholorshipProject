package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAL.DataBaseAccess;

/**
 * Servlet implementation class AddingMoney
 */
@WebServlet("/AddingMoney")
public class AddingMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		DataBaseAccess db=new DataBaseAccess();
		int rollno=Integer.parseInt(request.getParameter("rollno"));
		double amount=Double.parseDouble(request.getParameter("amount"));
		
		try
		{
			int status=db.addMoney(rollno,amount);
			
			if(status>0)
			{
				out.println("addmoneySuccess.html");
			}
			else
			{
				out.println("<h2>Amount has not been added out of some issue</h2>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
