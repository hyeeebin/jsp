
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberListServelt
 */
@WebServlet("/memberList")
public class MemberListServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberRepositoryDB memberRepositoryDB = new MemberRepositoryDB();
		List<Member> list = memberRepositoryDB.memberList();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String html = """
			<!DOCTYPE html>
			<html>
			<head>
			<meta charset="UTF-8">
			<title>회원목록</title>
			</head>
			<body>
			<a href='insertMemberForm.html'>회원등록</a>
			<table border='1'>
				<thead>
					<tr>
						<th>아이디</th>
						<th>비번</th>
						<th>이름</th>
						<th>전화번호</th>
						<th>이메일</th>
					</tr>
				</thead>
				<tbody>
							""";
					for (Member member : list) {
						html += "<tr>";
						html += "<td>" + member.getId() + "</td>";
						html += "<td>" + member.getPwd() + "</td>";
						html += "<td>" + member.getName() + "</td>";
						html += "<td>" + member.getPhone() + "</td>";
						html += "<td>" + member.getEmail() + "</td>";
						html += "</tr>";
					}
					
				html += """
				</tbody>
			</table>				
							"""; 
		out.println(html);
	}

}
