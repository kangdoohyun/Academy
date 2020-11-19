package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
	        	// =======================================DB 세팅=========================================
				Scanner sc = new Scanner(System.in); 
				// 드라이버 정보
				String driver = "com.mysql.cj.jdbc.Driver";
				// dbms 주소
				String url = "jdbc:mysql://localhost:3306/t1?serverTimezone=UTC";
				
				// 사용자 계정
				String user = "root";
				// 사용자 비밀번호
			    //String pass = "sbs123414";
				Class.forName(driver); // Driver 세팅
				Connection conn = DriverManager.getConnection(url, user, null); // DBMS 선택 -> 담당자(Connection) 부여받음

				
				while(true) {
					
					System.out.print("명령어를 입력해 주세요 : ");
					String cmd = sc.nextLine();
					
					if (cmd.equals("add")) {
						
						System.out.print("제목 : ");
						String title = sc.nextLine();
						System.out.print("내용 : ");
						String body = sc.nextLine();
						
						String sql = "INSERT INTO article SET title = ?, `body` = ?, nickname = '홍길동', hit = 10";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						
						pstmt.setString(1, title);
						pstmt.setString(2, body);
						pstmt.executeUpdate();
						
					}else if (cmd.equals("list")) {
						String sql = "SELECT * FROM article";

						PreparedStatement pstmt = conn.prepareStatement(sql); // PreparedStatment 통해서 sql 세팅

						ResultSet rs = pstmt.executeQuery(); // 조회 결과가 있는 경우

						while (rs.next()) {
							String title = rs.getString("title");
							int id = rs.getInt("id");
							String body = rs.getString("body");
							String nickname = rs.getString("nickname");
							int hit = rs.getInt("hit");
							System.out.println(id + " " + title + " " + body + " " + nickname + " " + hit);
						}
					}else if (cmd.equals("update")) {
						System.out.print("번호 : ");
						int id = Integer.parseInt(sc.nextLine());
						
						String sql = "SELECT * FROM article WHERE id = ?;";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, id);
						
						ResultSet rs = pstmt.executeQuery();
						if(rs.next()) {
							String sql2 = "UPDATE article SET title = ?, `body` = ? WHERE id = ?;";
							PreparedStatement pstmt2 = conn.prepareStatement(sql2);
							System.out.print("제목 : ");
							String title = sc.nextLine();
							System.out.print("내용 : ");
							String body = sc.nextLine();
							
							pstmt2.setString(1, title);
							pstmt2.setString(2, body);
							pstmt2.setInt(3, id);
							
							pstmt2.executeUpdate();
						}else {
							System.out.println("없는 게시물 입니다.");
						}
					}else if (cmd.equals("delete")) {
						System.out.print("번호 : ");
						int id = Integer.parseInt(sc.nextLine());
						
						String sql = "SELECT * FROM article WHERE id = ?";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, id);
						
						ResultSet rs = pstmt.executeQuery();
						
						if(rs.next()) {
							String sql2 = "DELETE FROM article WHERE id = ?";
							PreparedStatement pstmt2 = conn.prepareStatement(sql2);
							
							pstmt2.setInt(1, id);
							
							pstmt2.executeUpdate();
						}else {
							System.out.println("없는 게시물 입니다.");
						}
					}else if (cmd.equals("read")) {
						System.out.print("번호 : ");
						int id = Integer.parseInt(sc.nextLine());

						String sql2 = "select * from article where id = ?";
						PreparedStatement pstmt2 = conn.prepareStatement(sql2);
						pstmt2.setInt(1, id);

						ResultSet rs = pstmt2.executeQuery();

						if (rs.next()) {
							int id2 = rs.getInt("id");
							String title = rs.getString("title");
							String body = rs.getString("body");
							String nickname = rs.getString("nickname");
							int hit = rs.getInt("hit");
							
							System.out.println("번호 : " + id2);
							System.out.println("제목 : " + title);
							System.out.println("내용 : " + body);
							System.out.println("닉네임 : " + nickname);
							System.out.println("조회수 : " + hit);
							
						} else {
							System.out.println("없는 게시물입니다.");
						}
					}
				}
	}

}
