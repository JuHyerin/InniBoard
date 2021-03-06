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
import util.Paging;

@WebServlet("/")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		//page parameter �Ҵ�
		String pageParam = request.getParameter("page");
		int page;
		if(pageParam==null || pageParam.length()==0 || pageParam.equals("0")) {
			pageParam = "1"; //�ʱ�ȭ
		}
		page = Integer.parseInt(pageParam);
	
		PostDao postDao = new PostDao();
	
		Paging paging = new Paging(page,3,3);//���������� ����¡��ü ����
		paging.setTotalData(postDao.countAllPost()); //����¡��ü ����
	
		//set request
		request.setAttribute("paging", paging);
		request.setAttribute("posts", postDao.getPagedPost(paging.getFirstData(), paging.getPageSize()));
		request.setAttribute("contentPage", "/views/contents/postList.jsp");
	
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
