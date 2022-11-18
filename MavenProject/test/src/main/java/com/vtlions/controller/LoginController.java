package com.vtlions.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vtlions.model.User;

import itea.web07.DBOperator;

public class LoginController extends HttpServlet {

	DBOperator dbOperator;

	private static final long serialVersionUID = -9071757192018689176L;
	public static final String USER_ACCESS_GRANTED = "user";

	RequestDispatcher dispatcher = null;

//	String formLogin = "<html><center><form action='' method='post'>"
//			+ "<table border='0'><tr>"
//			+ "<td width='100'>Login</td>"
//			+ "<td ><input type='text' name='login' /></td></tr>"
//			+ "<tr><td>Password</td>"
//			+ "<td><input type='password' name='password' /></td></tr>"
//			+ "<tr>"
//			+ "<td align='center' colspan='2'><input type='submit' value='Send' /></td>"
//			+ "</tr></table></form></center></html>";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.getAttribute(USER_ACCESS_GRANTED);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String key = request.getParameter("key");

		if (key != null) {
			session.invalidate();

		}

		String url = "WEB-INF/views/loginform.jsp";
		session = request.getSession(true);
		if (session.getAttribute(USER_ACCESS_GRANTED) != null) {

			request.setAttribute("user", dbOperator.getUser());
			url = "WEB-INF/views/loginsuccesspage.jsp";

		}
		dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		dbOperator = new DBOperator(out);

		if (login != null) {

			try {
				if (dbOperator.checkUserCredentials(login, password)) {

					HttpSession session = request.getSession();
					session.setAttribute(USER_ACCESS_GRANTED, dbOperator.getUser());
					System.out.println();
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/loginsuccesspage.jsp");
					dispatcher.forward(request, response);

					// out.write(Menu.MENU_START + Menu.MENU_LOGOUT + Menu.MENU_SECRET +
					// Menu.MENU_END);

				} else {

					request.setAttribute("message", "Access denied");
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/loginform.jsp");
					dispatcher.forward(request, response);

				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
