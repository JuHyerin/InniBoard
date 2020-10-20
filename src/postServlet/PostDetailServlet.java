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
import util.StringUtil;

@WebServlet("/postDetail")
public class PostDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public PostDetailServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String listPage = request.getParameter("page");//���� �˻������� ������
		String option = request.getParameter("search_option");
		String word = request.getParameter("search_word");
		if(listPage==null || listPage.length()==0 || listPage.equals("0")) {
			listPage = "1"; //�ʱ�ȭ
		}
		request.setAttribute("page", listPage);//detail.jsp�� ��� ��ư url�� ���� param
		request.setAttribute("option", option);
		request.setAttribute("word", word);
		
		int postId = Integer.parseInt(request.getParameter("postid"));
		
		PostDao postDao = new PostDao();
		request.setAttribute("postDetail", postDao.getContentsById(postId));
		
		//comment page parameter �Ҵ�
		String pageParam = request.getParameter("commentpage");
		int page;
		if(pageParam==null || pageParam.length()==0 || pageParam.equals("0")) {
			pageParam = "1"; //�ʱ�ȭ
		}
		page = Integer.parseInt(pageParam);
		int blockSize = 3;
		int pageSize = 5;
		
		CommentDao commentDao = new CommentDao();
		int commentSize = commentDao.countAllComment(postId);
		if(commentSize>0) {//��� ���� ��쿡�� ����¡�� ->request��ü:paging,commnets
			Paging paging = new Paging(page, pageSize, blockSize);//���������� ����¡��ü ����
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
