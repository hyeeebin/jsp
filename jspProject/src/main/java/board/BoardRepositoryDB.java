package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardRepositoryDB {
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	public void open() {
		try {

			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/projectDB");
			if (dataSource != null) {
				conn = dataSource.getConnection();
			} else {

			}

			/*
			 * //오라클 // Class.forName("oracle.jdbc.OracleDriver"); //
			 * System.out.println("JDBC 드라이버 로딩 성공"); // conn =
			 * DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","user1",
			 * "passwd");
			 * 
			 * //마리아 //Class.forName("org.mariadb.jdbc.Driver");
			 * //System.out.println("JDBC 드라이버 로딩 성공"); //conn =
			 * DriverManager.getConnection("jdbc:mariadb://localhost:3306/test_db","root",
			 * "passwd");
			 */

			System.out.println("DB서버에 연결됨");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void close() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	//게시판 종류가 일반게시판
	public List<Board> boardList() {
		List<Board> list = new ArrayList<>();
		
		try {
			open();
			
			pstmt = conn.prepareStatement("select * from board where btype='일반게시판' order by bno desc");
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board(
					rs.getInt("BNO"),
					rs.getString("BTYPE"),
					rs.getString("TITLE"),
					rs.getString("CONTENT"),
					rs.getString("ID"),
					rs.getDate("REGDATE"),
					rs.getInt("READCOUNT"));
				list.add(board);
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}

	//게시판 종류 2가지 공지사항, Q&A 목록 조회하는 쿼리문 만들기
	
	
	public synchronized void insertBoard(Board board) throws Exception {
		try {
			open();
			String btype = board.getBtype();
			String id = board.getId();
			String title = board.getTitle();
			String content = board.getContent();
			
			pstmt = conn.prepareStatement("insert into board (btype,title,content,id) values(?,?,?,?)");
			
			pstmt.setString(1, btype);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setString(4, id);
			
			pstmt.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void updateReadCount(int bno) {
		try {
			open();
			
			pstmt = conn.prepareStatement("update board set readcount= readcount+1 where bno=?");
			
			pstmt.setInt(1, bno);
			
			pstmt.executeUpdate();
			
			System.out.println("조회수 1 증가 완료");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	//게시물 상세보기
	public Board getBoardContent(int bno) {
		Board board = null;
		try {
			open();
			
			pstmt = conn.prepareStatement("select * from board where bno=?");
			
			pstmt.setInt(1, bno);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
			    board = new Board();
				
				board.setBno(rs.getInt("bno"));
				board.setBtype(rs.getString("btype"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setId(rs.getString("id"));
				board.setRegDate(rs.getDate("regDate"));
				board.setReadCount(rs.getInt("readCount"));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	
	//게시물 수정
	public int updateBoardConent(Board board) {
		int result = -1;
		
		try {
			open();
			
			pstmt = conn.prepareStatement("update board set btype=?, title=? , content=? , id=? where bno=?");
			
			pstmt.setString(1, board.getBtype());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getId());
			pstmt.setInt(5, board.getBno());
			
			pstmt.executeUpdate();
			
			result = 1;
			System.out.println("게시물 수정 완료");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
		
	}
	
	public int delete(int bno) {
		int result = -1;
		
		try {
			open();
			
			pstmt = conn.prepareStatement("delete from board where bno=?");
			pstmt.setInt(1, bno);
			
			pstmt.executeUpdate();
			System.out.println("게시물 삭제 완료");
			result = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}

}
