package userServlet;

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
		//�α��� ���� Ȯ�ο� ����
		String userId = (String)userSession.getAttribute("userId");
		Boolean loginCheck = (Boolean)userSession.getAttribute("loginCheck");
		
		//request get
		//ResultSet postData = (ResultSet)request.getAttribute("postData");//���� �� ������
		//String prevPage = request.getHeader("Referer");
		String nextPage = (String)userSession.getAttribute("nextPage");
		
		//request set
		//userSession.setAttribute("prevPage", prevPage);
		if(nextPage==null) {//Ȩ���� �α��� �õ����� ���
			nextPage = "/";
			userSession.setAttribute("nextPage", nextPage);
		}
		
		//dispatcher
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher;
		
		//�α��� ������ ��� �ٷ� ����
		if(loginCheck!=null && loginCheck) {
			request.setAttribute("contentPage", nextPage);
			dispatcher = context.getRequestDispatcher("/views/index.jsp");
			dispatcher.forward(request, response);
			return;
		}
		//�α��� ���°� �ƴ� ��� �α��� �������� �̵�
		else if(isEmpty(userId) || loginCheck==false || loginCheck==null) {
			request.setAttribute("contentPage", "/views/contents/login.jsp");
			dispatcher = context.getRequestDispatcher("/views/index.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		////�α���: ����->����������, ����->�α���������
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession userSession = request.getSession();
		//loginForm�� ��������, db ���ٿ� ����
		String idParam = request.getParameter("id");
		String pwdParam = request.getParameter("pwd");

		//dispatcher
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher;
		
		if(isEmpty(idParam) || isEmpty(pwdParam)) {//��ĭ����
			System.out.println("��ĭ ����");
			userSession.setAttribute("loginCheck", false);
			request.setAttribute("contentPage", "/views/contents/login.jsp");
			dispatcher = context.getRequestDispatcher("/views/index.jsp");
			dispatcher.forward(request, response);//���� req,res ���� ����
			return;
		}
		
		//db �����Ͽ� user���� ��ġ ���� Ȯ�� 
		UserDao userDao = new UserDao();
		ResultSet user = userDao.getUserById(idParam, pwdParam);
		String userId=null;
		String userPwd=null;
		
		try {
			Boolean check = user.next();
			if(!check) {//user==null�� Ȯ���ϸ� �ȵ�.
				System.out.println("�α��� ����");
				userSession.setAttribute("loginCheck", false);
				request.setAttribute("contentPage", "/views/contents/login.jsp");
				dispatcher = context.getRequestDispatcher("/views/index.jsp");
				dispatcher.forward(request, response);//���� req,res ���� ����
				return;
			}

			else if(check){
				//�α��� ����(user!=null�� ����)
				userId = user.getString("id");
				//userPwd = user.getString("pwd");
				
				userSession.setAttribute("loginCheck", true); //�α��λ���->����
				userSession.setAttribute("userId",userId);
				
				String nextPage = (String)userSession.getAttribute("nextPage");//���� �� �̵��� ����������
				   
				request.setAttribute("contentPage", nextPage);
				dispatcher = context.getRequestDispatcher("/views/index.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(userId+userPwd);
		
		
		
	} 
	
	private boolean isEmpty(String str){
        if(str == null || str.trim().equals("")){
           return true;
          }
          return false;
    }
}

