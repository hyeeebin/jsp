package board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class RemoveBoardContent
 */
@WebServlet("/removeBoardContent")
public class RemoveBoardContent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardRepositoryDB boardRepositoryDB = new BoardRepositoryDB();

		int result = boardRepositoryDB.delete(bno);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (result == 1) {
			out.write("<script>");
			out.write("alert('게시물 삭제가 완료되었습니다. 게시물 목록으로 이동합니다.');");
			out.write("location.href='boardList';");
			out.write("</script>");
		}else {
			out.write("<script>");
            out.write("alert('게시물 삭제가 취소되었습니다.');");
            out.write("history.back();");			
            out.write("</script>");
		}
		out.close();
	}

}
