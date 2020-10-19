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
		
		//page parameter 할당
		String pageParam = request.getParameter("page");
		int page;
		if(pageParam==null || pageParam.length()==0 || pageParam.equals("0")) {
			pageParam = "1"; //초기화
		}
		page = Integer.parseInt(pageParam);
	
		PostDao postDao = new PostDao();
	
		Paging paging = new Paging(page);//현재페이지 페이징객체 생성
		paging.setTotalData(postDao.countAllPost()); //페이징객체 설정
	
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
