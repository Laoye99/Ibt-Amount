package com.xadmin.ibtamt.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.ibtamt.bean.User;
import com.xadmin.ibtamt.dao.UserDao;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		userDao = new UserDao();
	}
	

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

		switch (action) {
		    case "/new":
		        showNewForm(request, response);
		        break;

		    case "/insert":
		        try {
		            insertUser(request, response);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        break;

		    case "/delete":
		        try {
		            deleteUser(request, response);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        break;

		    case "/edit":
		        try {
		            showEditForm(request, response);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } catch (ServletException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        break;

		    case "/update":
		        try {
		            updateUser(request, response);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        break;

		    default:
		        try {
		            listUser(request, response);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } catch (ServletException e) {
		            e.printStackTrace();
		        }
		        break;
		}
		}

		private void showNewForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("amt-form.jsp");
			dispatcher.forward(request, response);
		
	}
		private void insertUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			String fsp = request.getParameter("fsp");
			String lowerBound = request.getParameter("lower_bound");
			float lower_bound = Float.parseFloat(lowerBound);
			String upperBound = request.getParameter("upper_bound");
			float upper_bound = Float.parseFloat(upperBound);
			User newUser = new User(fsp, lower_bound, upper_bound);
			userDao.insertUser(newUser);
			response.sendRedirect("list");
		}
		
		private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int acsn = Integer.parseInt(request.getParameter("acsn"));
			userDao.deleteUser(acsn);
			response.sendRedirect("list");

		}
		
		private void showEditForm(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, ServletException, IOException {
			int acsn = Integer.parseInt(request.getParameter("acsn"));
			User existingUser;
			try {
			existingUser= userDao.selectUser(acsn);
			RequestDispatcher dispatcher = request.getRequestDispatcher("amt-form.jsp");
			request.setAttribute("user", existingUser);
			dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		    int acsn = Integer.parseInt(request.getParameter("acsn"));
		    String fsp = request.getParameter("fsp");
		    float lowerBound = Float.parseFloat(request.getParameter("lower_bound"));
		    float upperBound = Float.parseFloat(request.getParameter("upper_bound"));

		    User book = new User(acsn, fsp, lowerBound, upperBound);
		    userDao.updateUser(book);

		    response.sendRedirect("list");
		}

		
		private void listUser(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			
			try {
			List<User> listUser = userDao.selectAllUsers();
			request.setAttribute("listUser", listUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("amt-list.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		}
