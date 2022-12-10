

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class searchPwdServlet
 */
@WebServlet("/searchPwd")
public class SearchPwdServlet extends HttpServlet {
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
		
		String id = request.getParameter("id");
		String phone = request.getParameter("phone");
		
		String pwd = new MemberRepositoryDB().findPwd(id, phone);
		System.out.println("searchPwdServlet 호출");
		if(pwd != null) {
			request.setAttribute("pwd", pwd);
			System.out.println(pwd);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("findPwdResult.jsp");
			dispatcher.forward(request, response);
		}else {
			String page="login.jsp";
			out.write("<script>");
		    out.write("alert('비밀번호 찾기 실패, 로그인 페이지로 이동합니다.');");
		    out.write("</script>");
		    response.sendRedirect(page);
		}
	}
}
