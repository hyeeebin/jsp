

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class myPageServlet
 */
@WebServlet("/myPage")
public class MyPageServlet extends HttpServlet {
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
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String id = (String) session.getAttribute("login.id");
		
		try {
			Member member = new MemberRepositoryDB().selectUser(id);
			
			response.setContentType("text/html; charset=utf-8");
			if(member != null) {
				request.setAttribute("member", member);
				RequestDispatcher dispatcher = request.getRequestDispatcher("myPage.jsp");
				dispatcher.forward(request, response);
			}else {
				String page="main.jsp";
				out.write("<script>");
			    out.write("alert('마이페이지 이동실패, 메인 페이지로 이동합니다.');");
			    out.write("</script>");
			    response.sendRedirect(page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				

	}

}

