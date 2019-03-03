package com.java.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.db.JdbcUtilityWEB;
import com.java.model.Message;
import com.java.model.User;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String []opt = request.getParameterValues("option");
		String text = request.getParameter("text");
		ArrayList<User> user = new ArrayList<User>();
		Message message = new Message();
		/*
			if (text == null) {
				message.setText(opt[0] +" must be specified!");
			} else if (text.isEmpty()) {
				message.setText(opt[0]+" cannot be empty!");
			} else if (opt[0].equals("u_id")&&!text.matches("[0-9]+")) {
				message.setText("ID must contain only numbers!");
			}
			 else if (opt[0].equals("username")&&!text.matches("([a-zA-Z0-9_]+){3,}")) {
				 message.setText("ID must contain only numbers!");
				}
			else if (opt[0].equals("firstname")&&!text.matches("[A-Za-z]+")){
				message.setText("First name must contain only numbers!");
			}
			else if (opt[0].equals("lastname")&&!text.matches("[A-Za-z]+")){
				message.setText("Last name must contain only numbers!");
			}
			else if (opt[0].equals("email")&&!text.matches("^([a-zA-Z0-9._-]){3,}@([a-zA-Z0-9.-]){3,}\\.[a-zA-Z]{2,4}$")){
				message.setText("Not a valid e-mail address");
			}
			else*/
			message.ErrorHandling(opt[0], text);
			if(message.getMsg().equals("")){
				try {
				Connection con = JdbcUtilityWEB.getConn();
				Statement s = con.createStatement();
				String query = "SELECT * FROM user " + "WHERE "+opt[0]+"='" + text+"'";

				ResultSet resultSet = s.executeQuery(query);

				// Check whether there are any results:
				if (!resultSet.isBeforeFirst())
					message.setMsg("No user found with the given "+opt[0]+" : " + text+".");

				while (resultSet.next()) {
					int id = resultSet.getInt("u_id");
					String username = resultSet.getString("username");
					String pass = resultSet.getString("password");
					String email = resultSet.getString("email");
					String firstName = resultSet.getString("firstname");
					String lastName = resultSet.getString("lastname");
					user.add( new User(id,username,pass,email, firstName, lastName));
					request.setAttribute("th","<th>ID</th><th>Username</th><th>Password</th><th>Email</th><th>First Name</th><th>Last Name</th>");
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally{
				try {
					JdbcUtilityWEB.releaseConn();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("msg", message);
		request.setAttribute("user", user);	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
