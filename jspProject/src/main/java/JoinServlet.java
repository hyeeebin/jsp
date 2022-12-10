
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class joinServlet
 */
@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Member member;

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
		PrintWriter out = response.getWriter();
		try {
			
			member.setId(request.getParameter("id"));

			String pwd1 = request.getParameter("pwd");
			String pwd2 = request.getParameter("pwd2");

			member.setPwd(pwd1);
			member.setName(request.getParameter("name"));
			member.setPhone(request.getParameter("phone"));
			member.setEmail(request.getParameter("email"));

			if (pwd1.equals(pwd2)) {
				response.setContentType("text/html; charset=utf-8");
				out.println("<script>");
				out.println("alert('회원가입이 완료되었습니다. 로그인을 시도해주세요.');");
				out.println("</script>");
				memberRepositoryDB.insertMember(member);
				System.out.println("신규등록 성공");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			} else {
				// out.println("비밀번호가 일치하지않습니다. 다시 시도해주세요.");

				out.print("<script>");
				out.println("alert('비밀번호가 일치하지않습니다. 다시 시도해주세요.');");
				out.println("history.back();");
				out.println("</script>");
			}

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
