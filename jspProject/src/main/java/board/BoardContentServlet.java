package board;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class viewBoardServlet
 */
@WebServlet("/boardContent")
public class BoardContentServlet extends HttpServlet {
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
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardRepositoryDB boardRepositoryDB = new BoardRepositoryDB();
		boardRepositoryDB.updateReadCount(bno);
		
		Board board = boardRepositoryDB.getBoardContent(bno);
		request.setAttribute("board",board);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("board/boardContent.jsp");
		System.out.println("게시물 상세보기 성공");
		dispatcher.forward(request, response);
		
	}

}
