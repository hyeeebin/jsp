import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doRequest(request, response);
	}
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("loginServlet 호출");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//PrintWriter out = response.getWriter();
		
		//멤버객체에 이름도 세션에 추가해서 뽑아서 사용할 수 있다. 
		Member member = new Member();
		member.setId(request.getParameter("id"));
		member.setPwd(request.getParameter("pwd"));
		
		MemberRepositoryDB memberRepositoryDB = new MemberRepositoryDB();
		boolean result = memberRepositoryDB.isExisted(member);
		
		if(result == true ) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogon", true);
			session.setAttribute("login.id", member.getId());
			session.setAttribute("login.pwd", member.getPwd());
			session.setAttribute("member", member);
			
			session.setMaxInactiveInterval(600);
			
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("memberLoginFail.jsp");
			dispatcher.forward(request, response);
		}
		
	}
}


