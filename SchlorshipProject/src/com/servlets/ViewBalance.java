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
 * Servlet implementation class ViewBalance
 */
@WebServlet("/ViewBalance")
public class ViewBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int balance=0;
		PrintWriter out=response.getWriter();
		DataBaseAccess db=new DataBaseAccess();
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("studentID");
		try {
			balance=db.transaction(0,username);
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			}
		out.println("<h2>Email : "+username+"</h2>");
		out.println("<h2>Balance : "+balance+"</h2>");
		
	}

}
