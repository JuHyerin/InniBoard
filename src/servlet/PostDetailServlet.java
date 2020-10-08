package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostDao;

@WebServlet("/postDetail")
public class PostDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public PostDetailServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		int postId = Integer.parseInt(request.getParameter("postid"));
		PostDao postDao = new PostDao();
		request.setAttribute("postDetail", postDao.getContentsBtId(postId));
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher;
		dispatcher = context.getRequestDispatcher("/views/postDetail.jsp");
	
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
