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
		//로그아웃->이전페이지(이전페이지가 로그인폼인 경우, 지정해둔 다음페이지로 이동)
		
		HttpSession userSession = request.getSession();
		String prevPage = request.getHeader("Referer");//이전페이지
		String nextPage = (String)userSession.getAttribute("nextPage");//이전페이지가 로그인폼인 경우 대비
		System.out.println(prevPage);
		if((Boolean)userSession.getAttribute("loginCheck") || userSession.getAttribute("loginCheck")!=null) {
			//로그인 상태->로그아웃
			userSession.invalidate();
			//userSession.setAttribute("loginCheck", false); //로그아웃상태->세션
			//userSession.setAttribute("userId",null);
		}
		if(prevPage.equals("http://localhost:8080/InniBoard/login")) {//이전페이지가 로그인 폼인 경우 다음 페이지로 돌아가기
			response.sendRedirect(request.getContextPath() + nextPage); //detail에서 로그아웃하면 디테일로 돌아가기
			return;
		}
		response.sendRedirect(prevPage);//이전페이지로 이동
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
