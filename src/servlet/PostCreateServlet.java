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

import dao.PostDao;


@WebServlet("/create")
public class PostCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public PostCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher;
		
		//회원여부 확인 후 로그인 화면으로 이동
		String user = request.getParameter("user");
		if(user==null || user.equals("")) {
			dispatcher = context.getRequestDispatcher("/views/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		dispatcher = context.getRequestDispatcher("/views/postForm.jsp");
		
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
