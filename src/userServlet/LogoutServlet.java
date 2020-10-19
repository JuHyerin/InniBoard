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
		String prevPage = request.getHeader("Referer");//����������
		//String nextPage = (String)userSession.getAttribute("nextPage");
		System.out.println(prevPage);
		if((Boolean)userSession.getAttribute("loginCheck") || userSession.getAttribute("loginCheck")!=null) {
			//�α��� ����->�α׾ƿ�
			userSession.invalidate();
			//userSession.setAttribute("loginCheck", false); //�α׾ƿ�����->����
			//userSession.setAttribute("userId",null);
		}
		response.sendRedirect(prevPage);//������������ �̵�
		//response.sendRedirect(request.getContextPath() + nextPage); //detail���� �α׾ƿ��ϸ� �����Ϸ� ���ư���
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
