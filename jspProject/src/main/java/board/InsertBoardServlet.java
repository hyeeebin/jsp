package board;

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
 * Servlet implementation class InsertBoardServlet
 */ 
@WebServlet("/insertBoard")
public class InsertBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Board board = new Board();
		BoardRepositoryDB boardRepositoryDB = new BoardRepositoryDB();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String id = (String) session.getAttribute("login.id");
		
		try {
			board.setId(id);
			
			String title = request.getParameter("title");
			board.setTitle(title);
			
			board.setContent(request.getParameter("content"));
			board.setBtype(request.getParameter("btype"));
			if(board != null) {
				System.out.println("게시물 등록 성공");
				response.setContentType("text/html; charset=utf-8");
				out.println("<script>");
				out.println("alert('게시물이 등록되었습니다. 일반게시판 목록 페이지로 이동합니다.');");
				out.println("</script>");
				boardRepositoryDB.insertBoard(board);
				RequestDispatcher dispatcher = request.getRequestDispatcher("boardList");
				dispatcher.forward(request, response);

				
			}else {
				out.println("<script>");
				out.println("alert('제목을 입력하세요.');");
				out.println("history.back();");
				out.println("</script>");
			}
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
