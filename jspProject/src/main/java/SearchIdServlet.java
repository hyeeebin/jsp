
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class searchId
 */
@WebServlet("/searchId")
public class SearchIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
//		Member member = new Member();
		String name = request.getParameter("name");
		String phone =request.getParameter("phone");
//		
//		member.setName(name);
//		member.setPhone(phone);
		
//		MemberRepositoryDB memberRepositoryDB = new MemberRepositoryDB();
//		String result = memberRepositoryDB.findId(name, phone);
		
		String id = new MemberRepositoryDB().findId(name, phone);
		System.out.println("searchIdServlet 호출");
		if(id != null) {
			request.setAttribute("id", id);
			System.out.println(id);
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("findIdResult.jsp");
			dispatcher.forward(request, response);
		}else {
			String page="login.jsp";
			out.write("<script>");
		    out.write("alert('아이디 찾기 실패, 로그인 페이지로 이동합니다.');");
		    out.write("</script>");
		    response.sendRedirect(page);
		}
		
	}
}
