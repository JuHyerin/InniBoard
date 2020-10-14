package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PostDao;


@WebServlet("/create")
public class PostCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public PostCreateServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession userSession = request.getSession();		
		userSession.setAttribute("nextPage", "/views/postForm.jsp");
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher;
		dispatcher = context.getRequestDispatcher("/login");	
		dispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * request.setCharacterEncoding("utf-8");
		 * response.setContentType("text/html;charset=utf-8");
		 * 
		 * PostDao postDao = new PostDao();
		 * postDao.insertPost(request.getParameter("title"), "id123",
		 * request.getParameter("contents"));
		 * 
		 * ServletContext context = getServletContext(); RequestDispatcher dispatcher;
		 * dispatcher = context.getRequestDispatcher("/");
		 * 
		 * dispatcher.forward(request, response);
		 */
	}

}
