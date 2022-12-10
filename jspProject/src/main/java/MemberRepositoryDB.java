import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberRepositoryDB {
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

	public List<Member> memberList() {
		List<Member> list = new ArrayList<>();

		try {
			open();

			pstmt = conn.prepareStatement("select * from member order by createdate");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = new Member(
						rs.getString("ID"), 
						rs.getString("PWD"), 
						rs.getString("NAME"),
						rs.getString("PHONE"), 
						rs.getString("EMAIL"), 
						null);
				list.add(member);
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public synchronized void insertMember(Member member) throws Exception {
		try {
			open();

			pstmt = conn.prepareStatement("insert into member (id, pwd, name, phone, email) values(?,?,?,?,?)");

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getEmail());
			pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public synchronized Member selectUser(String id) throws Exception {
		Member member = null;
		try {
			open();

			pstmt = conn.prepareStatement("select * from member where id=?");

			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				member = new Member();
				member.setId(rs.getString("id"));
				member.setPwd(rs.getString("pwd"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	public boolean isExisted(Member member) {
		System.out.println("DB에서 아이디 , 패스워드 확인중");
		boolean result = false;
		String id = member.getId();
		String pwd = member.getPwd();
		try {
			open();

			// decode 함수를 이용하여 id와 pwd 가 테이블에 존재하면 true, 아니면 false 조회
			// 메서드로 전달된 id와 pwd 를 이용해 sql문을 작성한 후 데이터베이스에 조회
			pstmt = conn.prepareStatement(
					"select decode(count(*), 1, 'true', 'false') as result from member where id=? and pwd=?");
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);

			ResultSet rs = pstmt.executeQuery();

			rs.next(); // 커서를 첫 번째 레코드로 위치시킴
			result = Boolean.parseBoolean(rs.getString("result"));
			System.out.println("result = " + result);
			System.out.println("확인 완료");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String findId(String name, String phone) {
		String id = null;

		try {
			open();

			pstmt = conn.prepareStatement("select id from member where name=? and phone=?");

			pstmt.setString(1, name);
			pstmt.setString(2, phone);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getString("id");
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return id;
	}

	public String findPwd(String id, String phone) {
		String pwd = null;

		try {
			open();

			pstmt = conn.prepareStatement("select pwd from member where id =? and phone=?");

			pstmt.setString(1, id);
			pstmt.setString(2, phone);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				pwd = rs.getString("pwd");
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return pwd;
	}

	public int delete(Member member) {
		int result = -1;
		try {
			open();

			pstmt = conn.prepareStatement("select pwd from member where id=?");

			pstmt.setString(1, member.getId());

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("pwd").equals(member.getPwd())) {
					pstmt = conn.prepareStatement("delete from member where id=?");
					pstmt.setString(1, member.getId());

					pstmt.executeUpdate();
					System.out.println("회원 정보 삭제 완료");
					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public int updateMember(Member member) {
		int result = -1;

		try {
			open();

			pstmt = conn.prepareStatement("select * from member where id=?");
			pstmt.setString(1, member.getId());

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("id").equals(member.getId())) {
					pstmt = conn.prepareStatement("update member set pwd=?, name=?, phone=?,email=? where id=?");

					pstmt.setString(1, member.getPwd());
					pstmt.setString(2, member.getName());
					pstmt.setString(3, member.getPhone());
					pstmt.setString(4, member.getEmail());
					pstmt.setString(5, member.getId());

					pstmt.executeUpdate();

					result = 1;
					System.out.println("DB: 회원정보 수정 완료");
				}else {
					result = 0;
				}
				
			}else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}
}
