package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();
		//로그인 여부 확인용 변수
		String userId = (String)userSession.getAttribute("userId");
		Boolean loginCheck = (Boolean)userSession.getAttribute("loginCheck");
		
		//request get
		ResultSet postData = (ResultSet)request.getAttribute("postData");//수정 전 데이터
		String prevPage = request.getHeader("Referer");
		String nextPage = (String)userSession.getAttribute("nextPage");
		
		//request set
		userSession.setAttribute("prevPage", prevPage);
		request.setAttribute("postData", postData);
		
		//dispatcher
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher;
		
		//로그인 상태일 경우 바로 응답
		if(loginCheck!=null && loginCheck) {
			dispatcher = context.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
			return;
		}
		//로그일 상태가 아닐 경우 로그인 페이지로 이동
		else if(isEmpty(userId) || loginCheck==false || loginCheck==null) {
			dispatcher = context.getRequestDispatcher("/views/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		////로그인: 성공->이전페이지, 실패->로그인페이지
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession userSession = request.getSession();
		//loginForm의 폼데이터, db 접근용 변수
		String idParam = request.getParameter("id");
		String pwdParam = request.getParameter("pwd");
		
		//dispatcher
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher;
		
		//db 접근하여 user정보 일치 여부 확인 (일치->if, 불일치->elseif)
		UserDao userDao = new UserDao();
		ResultSet user = userDao.getUserById(idParam, pwdParam);
		String userId=null;
		String userPwd=null;
		try {
			user.next();
			userId = user.getString("id");
			userPwd = user.getString("pwd");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//request get
		String prevPage = (String)userSession.getAttribute("prevPage");//성공 후 이동할 이전페이지
		ResultSet postData = (ResultSet)request.getAttribute("postData");//수정 전 데이터
		
		//request set
		request.setAttribute("user", user); //회원정보->req
		request.setAttribute("postData", postData);//수정되기 전 데이터->req
		
		if(idParam.equals(userId) && pwdParam.equals(userPwd)) {//로그인 성공(user!=null는 오류)
			userSession.setAttribute("loginCheck", true); //로그인상태->세션
			userSession.setAttribute("userId",userId);
			response.sendRedirect(prevPage);//이전페이지로 이동
		}
		else if(isEmpty(idParam) || isEmpty(pwdParam) //로그인 실패 
				|| !idParam.equals(userId) || !pwdParam.equals(userPwd)) {//(user==null은 오류)
			System.out.println("로그인 실패");
			dispatcher = context.getRequestDispatcher("/views/login.jsp");
			dispatcher.forward(request, response);//기존 req,res 정보 유지
		}
	} 
	
	private boolean isEmpty(String str){
        if(str == null || str.trim().equals("")){
           return true;
          }
          return false;
    }
}

