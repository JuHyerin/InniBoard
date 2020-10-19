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

import dao.CommentDao;
import dao.PostDao;
import util.Paging;

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
		request.setAttribute("postDetail", postDao.getContentsById(postId));
		
		/*
		 * CommentDao commentDao = new CommentDao(); request.setAttribute("comments",
		 * commentDao.getCommentByPostid(postId));
		 */
		
		//page parameter �Ҵ�
		String pageParam = request.getParameter("page");
		int page;
		if(pageParam==null || pageParam.length()==0 || pageParam.equals("0")) {
			pageParam = "1"; //�ʱ�ȭ
		}
		page = Integer.parseInt(pageParam);
		
		
		CommentDao commentDao = new CommentDao();
		int commentSize = commentDao.countAllComment();
		if(commentSize>0) {//��� ���� ��쿡�� ����¡�� ->request��ü:paging,commnets
			Paging paging = new Paging(page);//���������� ����¡��ü ����
			paging.setTotalData(commentSize); //����¡��ü ����
			paging.setPageSize(5);
			
			//set request
			request.setAttribute("paging", paging);
			request.setAttribute("comments", commentDao.getPagedComments(postId, paging.getFirstData(), paging.getPageSize()));
		}
		HttpSession userSession = request.getSession();
		userSession.setAttribute("nextPage", "/postDetail?postid="+postId);

		ServletContext context = getServletContext();
		RequestDispatcher dispatcher;
		request.setAttribute("contentPage", "/views/contents/postDetail.jsp");
		dispatcher = context.getRequestDispatcher("/views/index.jsp");
	
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
