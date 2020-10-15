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
		//�α��� ���� Ȯ�ο� ����
		String userId = (String)userSession.getAttribute("userId");
		Boolean loginCheck = (Boolean)userSession.getAttribute("loginCheck");
		
		//request get
		ResultSet postData = (ResultSet)request.getAttribute("postData");//���� �� ������
		String prevPage = request.getHeader("Referer");
		String nextPage = (String)userSession.getAttribute("nextPage");
		
		//request set
		userSession.setAttribute("prevPage", prevPage);
		request.setAttribute("postData", postData);
		
		//dispatcher
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher;
		
		//�α��� ������ ��� �ٷ� ����
		if(loginCheck!=null && loginCheck) {
			dispatcher = context.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
			return;
		}
		//�α��� ���°� �ƴ� ��� �α��� �������� �̵�
		else if(isEmpty(userId) || loginCheck==false || loginCheck==null) {
			dispatcher = context.getRequestDispatcher("/views/login.jsp");
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
		
		//db �����Ͽ� user���� ��ġ ���� Ȯ�� (��ġ->if, ����ġ->elseif)
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
		String prevPage = (String)userSession.getAttribute("prevPage");//���� �� �̵��� ����������
		ResultSet postData = (ResultSet)request.getAttribute("postData");//���� �� ������
		
		//request set
		request.setAttribute("user", user); //ȸ������->req
		request.setAttribute("postData", postData);//�����Ǳ� �� ������->req
		
		if(idParam.equals(userId) && pwdParam.equals(userPwd)) {//�α��� ����(user!=null�� ����)
			userSession.setAttribute("loginCheck", true); //�α��λ���->����
			userSession.setAttribute("userId",userId);
			response.sendRedirect(prevPage);//������������ �̵�
		}
		else if(isEmpty(idParam) || isEmpty(pwdParam) //�α��� ���� 
				|| !idParam.equals(userId) || !pwdParam.equals(userPwd)) {//(user==null�� ����)
			System.out.println("�α��� ����");
			dispatcher = context.getRequestDispatcher("/views/login.jsp");
			dispatcher.forward(request, response);//���� req,res ���� ����
		}
	} 
	
	private boolean isEmpty(String str){
        if(str == null || str.trim().equals("")){
           return true;
          }
          return false;
    }
}

