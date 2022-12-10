

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class updateMemberServlet
 */
@WebServlet("/update")
public class UpdateMemberServlet extends HttpServlet {
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
		
		System.out.println("updateMemberServlet 호출");
		
		Member member = new Member();
		member.setId(request.getParameter("id"));
		member.setPwd(request.getParameter("pwd"));
		member.setName(request.getParameter("name"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		
		System.out.println("변경된 회원 정보 저장");
		System.out.println("변경 내용: "+member);
		
		MemberRepositoryDB memberRepositoryDB = new MemberRepositoryDB();
		
		
		PrintWriter out = response.getWriter();
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		int result = memberRepositoryDB.updateMember(member);
		
		if(result == 1) {
			System.out.println("결과(수정성공 1/회원정보없음 0 or -1) : "+ result);
			System.out.println("회원 정보 수정 완료");
	
			out.print("<script>");
	        out.print("alert('정보 수정이 완료되었습니다.');");
	        out.print("location.href='myPage';");
	        out.print("</script>");
	        
		}else {
			System.out.println("결과(수정성공 1/회원정보없음 0 or -1) : "+ result);
			System.out.println("회원 정보 수정 실패");
			
			out.print("<script>");
	        out.print("alert('정보 수정이 완료되지못했습니다. 다시 시도해주세요.');");
	        out.print("location.href='main.jsp';");
	        out.print("</script>");
		}
				
        out.close();
	}
}
