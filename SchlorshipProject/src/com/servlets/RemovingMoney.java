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
 * Servlet implementation class RemovingMoney
 */
@WebServlet("/RemovingMoney")
public class RemovingMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		DataBaseAccess db=new DataBaseAccess();
		int rollno=Integer.parseInt(request.getParameter("rollno"));
		double amount=Double.parseDouble(request.getParameter("amount"));
		
		try
		{
			int status=db.removeMoney(rollno,amount);
			
			if(status>0)
			{
				out.println("<h2>Amount has been removed</h2>");
			}
			else
			{
				out.println("<h2>Amount has not been removed out of some issue</h2>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}


