
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class checkPwd
 */
@WebServlet("/checkPwd")
public class CheckPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Member member = new Member();
		member.setId(request.getParameter("id"));
		member.setPwd(request.getParameter("pwd"));

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		MemberRepositoryDB  memberRepositoryDB = new MemberRepositoryDB();
		boolean result = memberRepositoryDB.isExisted(member);
		out.print("<script>");
		if(result == true) {
//			out.println("location.href='myPage'");
			response.sendRedirect("myPage");
		}else {
			out.println("alert('비밀번호가 일치하지 않습니다!');");
			out.println("history.back();");
		}
		out.println("</script>");
		out.close();
		
		return;
	}
}
