package com.java.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.db.JdbcUtilityWEB;
import com.java.model.Message;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int flag = 0;
		String uname = request.getParameter("username");
		String pass = request.getParameter("pass");
		Message m1 = new Message();
		Message m2 = new Message();
		m1.ErrorHandling("username", uname);
		m2.ErrorHandling("pass", pass);
		if (m1.getMsg().equals("") && m2.getMsg().equals("")) {
			try {
				Connection con = JdbcUtilityWEB.getConn();
				Statement s = con.createStatement();
				String query = "SELECT * FROM user " + "WHERE username=?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, uname);
				ResultSet resultSet = ps.executeQuery();
				if (!resultSet.isBeforeFirst()) {
					m1.setMsg("No username exists such as " + uname);
					request.setAttribute("uname", uname);
				} else {

					while (resultSet.next()) {
						if (!resultSet.getString("password").equals(pass)) {
							m2.setMsg("Wrong Password!");
							request.setAttribute("uname", uname);
						} else {
							flag = 1;
							String nn = resultSet.getString("firstname");
							m1.setMsg("Dear " + nn + ", you successfully logged in.");
						}
					}
				}

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					JdbcUtilityWEB.releaseConn();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("msg1", m1);
		request.setAttribute("msg2", m2);
		if (flag == 0) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		if (flag == 1) {
			RequestDispatcher rd = request.getRequestDispatcher("logged.jsp");
			rd.forward(request, response);
		}

	}

	/*
	 * if(uname ==null || uname.isEmpty()){ m1.setMsg("Name cannot be empty!");
	 * } if(!uname.matches("^([a-zA-Z0-9_]).{3,}$") ){ m1.
	 * setMsg("username must be at least 3 char long containing letters, digits and underscores"
	 * ); } if(!pass.matches("^(?=.*[0-9]).{5,}$|(?=.*[A-Z]).{5,}$")){
	 * m2.setMsg("Password must be at least 5 chars"); } else{ flag=1; try {
	 * Connection con = JdbcUtilityWEB.getConn(); Statement s =
	 * con.createStatement(); String query = "SELECT * FROM user " +
	 * "WHERE username=? AND password=?"; //removing sql is good; create
	 * interface with a variable PreparedStatement ps =
	 * con.prepareStatement(query); ps.setString(1, uname); ps.setString(2,
	 * pass); ResultSet resultSet = ps.executeQuery(); if(resultSet.getString(1)
	 * != null && resultSet.getString(2)==null){ HttpSession session=
	 * request.getSession(); m2.setMsg("Wrong Password");
	 * session.setAttribute("uname", uname); }else
	 * if(!resultSet.isBeforeFirst()){
	 * m1.setMsg("No user is found with given username and password"); }
	 * 
	 * while(resultSet.next()){ String nn = resultSet.getString("firstname");
	 * m1.setMsg("Dear "+nn+", you successfully logged in");
	 * 
	 * }
	 * 
	 * } catch (ClassNotFoundException | SQLException e ) { e.printStackTrace();
	 * } finally{ try{ JdbcUtilityWEB.releaseConn();
	 * 
	 * }catch (SQLException e) { e.printStackTrace(); } } }
	 * request.setAttribute("msg", m1); request.setAttribute("msg2", m2);
	 * if(flag==0){ RequestDispatcher rd =
	 * request.getRequestDispatcher("index.jsp"); rd.forward(request, response);
	 * } if(flag==1){ RequestDispatcher rd =
	 * request.getRequestDispatcher("logged.jsp"); rd.forward(request,
	 * response); }
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}