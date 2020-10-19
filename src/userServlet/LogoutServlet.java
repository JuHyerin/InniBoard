package userServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();
		String prevPage = request.getHeader("Referer");//이전페이지
		//String nextPage = (String)userSession.getAttribute("nextPage");
		System.out.println(prevPage);
		if((Boolean)userSession.getAttribute("loginCheck") || userSession.getAttribute("loginCheck")!=null) {
			//로그인 상태->로그아웃
			userSession.invalidate();
			//userSession.setAttribute("loginCheck", false); //로그아웃상태->세션
			//userSession.setAttribute("userId",null);
		}
		response.sendRedirect(prevPage);//이전페이지로 이동
		//response.sendRedirect(request.getContextPath() + nextPage); //detail에서 로그아웃하면 디테일로 돌아가기
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
