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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//검색 파라미터 get
		String option = request.getParameter("search_option");
		String word = request.getParameter("search_word");
		
		if(word==null || word.length()==0) {//검색어 없으면 시작화면(전체데이터출력)
			response.sendRedirect(request.getContextPath()+"/");
			return;
		}
		System.out.println(option+word);
		//page parameter 할당
		String pageParam = request.getParameter("page");
		int page;
		if(pageParam==null || pageParam.length()==0 || pageParam.equals("0")) {
			pageParam = "1"; //초기화
		}
		page = Integer.parseInt(pageParam);
		
		PostDao postDao = new PostDao();
		
		Paging paging = new Paging(page,3,3);//현재페이지 페이징객체 생성
		paging.setTotalData(postDao.countSearchedPost(option, word)); //페이징객체 설정
		
		//set request
		request.setAttribute("paging", paging);
		request.setAttribute("posts", postDao.getPagedSearchedPost(option, word, paging.getFirstData(), paging.getPageSize()));
		request.setAttribute("option", option);
		request.setAttribute("word", word);
		request.setAttribute("contentPage", "/views/contents/postList.jsp");
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher;
		dispatcher = context.getRequestDispatcher("/views/index.jsp");

		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
