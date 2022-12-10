package board;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateBoardContentSerlvet
 */
@WebServlet("/updateBoard")
public class UpdateBoardContentSerlvet extends HttpServlet {
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
		System.out.println("UpdateBoardContentServlet 호출");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		Board board = new Board();

		int bno = Integer.parseInt(request.getParameter("bno"));
		
		board.setBno(bno);
		board.setBtype(request.getParameter("btype"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setId(request.getParameter("id"));
		
		System.out.println("변경된 게시물 내용 저장");
		System.out.println("변경 내용: "+board);
		
		BoardRepositoryDB boardRepositoryDB = new BoardRepositoryDB();
		
		int result = boardRepositoryDB.updateBoardConent(board);
		
		if(result == 1) {
			System.out.println("게시물 수정 완료");
			
			request.setAttribute("board", board);
			 RequestDispatcher dispatcher = request.getRequestDispatcher("boardList");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("board/boardList.jsp");
			dispatcher.forward(request, response);
			
			out.print("<script>");
	        out.print("alert('정보 수정이 완료되었습니다.');");
	        out.print("</script>");
		}else {
			out.print("<script>");
	        out.print("alert('게시물 수정이 완료되지못했습니다. 다시 시도해주세요.');");
	        out.print("location.href='main.jsp';");
	        out.print("</script>");
		}
		
		out.close();
	}
}
