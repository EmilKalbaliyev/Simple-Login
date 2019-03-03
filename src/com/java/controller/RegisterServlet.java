package com.java.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.db.JdbcUtilityWEB;
import com.java.model.Message;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	//HashMap <String, String> map = new HashMap <String, String>();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Message msg1 = new Message();
		Message msg2 = new Message();
		Message msg3 = new Message();
		Message msg4 = new Message();
		Message msg5 = new Message();
		
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		msg1.ErrorHandling("firstname",fname);
		msg2.ErrorHandling("lastname",lname);
		msg3.ErrorHandling("username",username);
		msg4.ErrorHandling("email",email);
		msg5.ErrorHandling("pass",pass);
		
		if (msg1.getMsg().equals("") && msg2.getMsg().equals("") && msg3.getMsg().equals("") && msg4.getMsg().equals("")&&msg5.getMsg().equals("")) {
			try {
				Connection con = JdbcUtilityWEB.getConn();
				//Statement s = con.createStatement();
				String query = "INSERT INTO user(username,password,email,firstname,lastname) values(?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, username);
				ps.setString(2, pass);
				ps.setString(3, email);
				ps.setString(4, fname);
				ps.setString(5, lname);
				int i = ps.executeUpdate();
				if (i > 0) {
					// System.out.println(lname);
					msg1.setMsg("Dear " + fname + ", you successfully registered in our website.");
					request.setAttribute("msg", msg1);
					RequestDispatcher rd = request.getRequestDispatcher("registered.jsp");
					rd.forward(request, response);
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
		}else{
			
				request.setAttribute("fname", msg1);
				request.setAttribute("lname", msg2);
				request.setAttribute("uname", msg3);
				request.setAttribute("email", msg4);
				request.setAttribute("pass", msg5);
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				rd.forward(request, response);
			
		}
			

	}
	/*for(Map.Entry<String, String> entry : map.entrySet()) {
	 String key = entry.getKey();
	 
	 if(key.equals("null")){
		 Message msg1 = new Message();
		 msg1.setMsg(entry.getValue());
		 request.setAttribute("null", msg1);
	 } else
	 if(key.equals("fname")){
		 Message msg2 = new Message();
		 msg2.setMsg(entry.getValue());
		 request.setAttribute("fname", msg2);
	 }else
	 if(key.equals("lname")){
		 Message msg3 = new Message();
		 msg3.setMsg(entry.getValue());
		 request.setAttribute("lname", msg3);
	 } else
	 if(key.equals("uname")){
		 Message msg4 = new Message();
		 msg4.setMsg(entry.getValue());
		 request.setAttribute("uname", msg4);
	 } else
	 if(key.equals("pass")){
		 Message msg5 = new Message();
		 msg5.setMsg(entry.getValue());
		 request.setAttribute("pass", msg5);
	 }else
	 if(key.equals("email")){
		 Message msg6 = new Message();
		 msg6.setMsg(entry.getValue());
		 request.setAttribute("email", msg6);
	 }
}

RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
rd.forward(request, response);
}*/

	/*private boolean isValide(String fname, String lname, String username, String email, String pass) {
		int flag = 0;

		if (fname == null || fname.isEmpty() || lname == null || lname.isEmpty() || username == null
				|| username.isEmpty() || email == null || email.isEmpty()|| pass ==null || pass.isEmpty()) {
			flag=1;
			String s = "Null or Empty value is not allowed";
			map.put("null", s);
		}
		if (!fname.matches("[a-zA-Z]+\\.?")) {
			flag = 1;
			String s = "Name must be in English letters";
			map.put("fname", s);
			
		}
		if(!lname.matches("[a-zA-Z]+\\.?")){
			flag = 1;
			String s = "LastName must be in English letters";
			map.put("lname", s);
			
		}
		if (!username.matches("^([a-zA-Z0-9_]).{3,}$")) {
			flag = 1;
			String s = "username must be at least 3 char long containing letters, digits and underscores";
			map.put("uname", s);
		}
		 if(!pass.matches("^(?=.*[0-9]).{5,}$|(?=.*[A-Z]).{5,}$")){
				flag=1;
				String s = "Password must be at least 5 chars";
				map.put("pass", s);
			}
		 
		 if(!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			 flag=1;
			 String s= "Invalid Email type";
			 map.put("email", s);
		 }
		 
	if(flag==0) return true;
	else return false;
	}
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
