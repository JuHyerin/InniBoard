package commentServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentDao;


@WebServlet("/createComment")
public class CommentCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CommentCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession userSession = request.getSession();
		String userId = (String)userSession.getAttribute("userId");
		String comment = request.getParameter("comment");
		String postIdParm = request.getParameter("postId");
		int postId = Integer.parseInt(postIdParm);
		
		CommentDao commentDao = new CommentDao();
		commentDao.insertComment(postId, userId, comment);
		
		
		response.sendRedirect(request.getContextPath()+"/postDetail?postid="+postId);
		
	}

}
