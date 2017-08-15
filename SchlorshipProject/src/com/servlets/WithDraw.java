package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAL.DataBaseAccess;

/**
 * Servlet implementation class WithDraw
 */
@WebServlet("/WithDraw")
public class WithDraw extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		DataBaseAccess db=new DataBaseAccess();
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("studentID");
		int amount =Integer.parseInt(request.getParameter("amount"));
		try {
			int balance=db.transaction(amount,username);
			if(balance>0)
			{
				response.sendRedirect("Successfullwithdraw.html");
			}
			else if(balance<0)
			{
				out.println("<h2>You dont have enough balance in your account</h2><br>");
			}
			else if(balance==-1)
			{
				out.println("<h2>Some thing went wrong</h2>");
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
