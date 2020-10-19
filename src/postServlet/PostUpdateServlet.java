package postServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PostDao;


@WebServlet("/update")
public class PostUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PostUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//request get
		int postId = Integer.parseInt(request.getParameter("postid"));
		HttpSession userSession = request.getSession();
		
		//request set
		PostDao postDao = new PostDao();
		request.setAttribute("postData", postDao.getContentsById(postId));
		request.setAttribute("contentPage", "/views/contents/postForm.jsp");
		//userSession.setAttribute(name, value);
		//dispatcher
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher;
		dispatcher = context.getRequestDispatcher("/views/index.jsp");	
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int postId = Integer.parseInt(request.getParameter("postid"));
		HttpSession userSession = request.getSession();
		String writer = (String)userSession.getAttribute("userId");
		PostDao postDao = new PostDao();
		if(postId == 0) {//insert
			postDao.insertPost(request.getParameter("title"), writer, request.getParameter("contents"));
		}
		else {//update
			postDao.updatePost(postId, request.getParameter("title"), request.getParameter("contents"));
		}
		response.sendRedirect(request.getContextPath() + "/");
	}

}
