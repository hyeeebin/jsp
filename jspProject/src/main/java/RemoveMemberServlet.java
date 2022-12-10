
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class removeMember
 */
@WebServlet("/removeMember")
public class RemoveMemberServlet extends HttpServlet {
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
		MemberRepositoryDB memberRepositoryDB = new MemberRepositoryDB();
		try {
			response.setContentType("text/html; charset=utf-8");
			member.setId(request.getParameter("id"));
			member.setPwd(request.getParameter("pwd"));

			int result = memberRepositoryDB.delete(member);

			PrintWriter out = response.getWriter();
			if(1 == result) {
				HttpSession session = request.getSession();
	            session.invalidate();
	            out.write("<script>");
	            out.write("alert('회원 탈퇴가 완료되었습니다. 메인페이지로 이동합니다.');");
	            out.write("location.href='main.jsp';");
	            out.write("</script>");
			}else {
				out.write("<script>");
	            out.write("alert('비밀번호가 일치하지 않습니다!');");
	            out.write("history.back();");			
	            out.write("</script>");
			}
			out.close();

		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

}
