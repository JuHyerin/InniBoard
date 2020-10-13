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

import dao.UserDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String idParam = request.getParameter("id");
		String pwdParam = request.getParameter("pwd");
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher;
		
		UserDao userDao = new UserDao();
		ResultSet user = userDao.getUserById(idParam);
		request.setAttribute("user", user);
		String pwd=null;
		if(user==null) {
			System.out.println("존재하지 않는 회원입니다.");
			dispatcher = context.getRequestDispatcher("/");
			dispatcher.forward(request, response);
			return;
		}
		
		else if(pwd==null || pwd.equals(pwdParam)) {
			try {
				user.next();
				pwd = user.getString("pwd");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("비밀번호를 확인해주세요.");
			dispatcher = context.getRequestDispatcher("/");
			dispatcher.forward(request, response);
			return;
		}
		dispatcher = context.getRequestDispatcher("/views/postForm.jsp");
		dispatcher.forward(request, response);
		
	}

}

