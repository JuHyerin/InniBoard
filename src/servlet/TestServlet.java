package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbConnection.DataBase;

@WebServlet("/")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataBase db;
	public TestServlet() {
		super();
		this.db = new DataBase();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		db.connectDatabase();
		//response.getWriter().print(db.getConnectMsg());
		
		request.setAttribute("posts", db.getAllPost());
		
		db.disconnectDataBase();

		ServletContext context = getServletContext();
		RequestDispatcher dispatcher;
		dispatcher = context.getRequestDispatcher("/views/index.jsp");
		
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
