package commentServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;


@WebServlet("/deleteComment")
public class deleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public deleteCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commentId = Integer.parseInt(request.getParameter("commentid"));
		
		CommentDao commentDao = new CommentDao();
		commentDao.deleteCommentById(commentId);
		
		String prevPage = request.getHeader("Referer");//이전페이지
		response.sendRedirect(prevPage);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
