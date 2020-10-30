package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {

	static MemberDao memberDao = new MemberDao();
	static CommentDao commentDao = new CommentDao();
	static ArticleDao articleDao = new ArticleDao();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Member loginedMember = null;
		
		while (true) {
			if (loginedMember == null) {
				System.out.print("명령어 입력 : ");
			}
			else {
				System.out.print("명령어 입력 ["+ loginedMember.getLoginId() +"("+ loginedMember.getNickname()+")] : ");
			}
			String cmd = sc.next();
			if (cmd.equals("exit")) {
				System.out.println("종료");
				break;
			}
			if (cmd.equals("add")) {

				Article a = new Article();

				System.out.print("게시물 제목을 입력해주세요 :");
				String title = sc.next();
				a.setTitle(title);

				System.out.print("게시물 내용을 입력해주세요 :");
				String body = sc.next();
				a.setBody(body);

				a.setNickname("익명");

				articleDao.insertArticle(a);
				System.out.println("게시물이 등록되었습니다.");

			}
			if (cmd.equals("list")) {
				ArrayList<Article> articles = articleDao.getArticles();

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					System.out.println("번호 : " + article.getId());
					System.out.println("제목 : " + article.getTitle());
					System.out.println("등록 날자 : " + article.getRegDate());
					System.out.println("작성자 : " + article.getNickname());
					System.out.println("조회수 : " + article.getHit());
					System.out.println("===================");
				}

			}
			if (cmd.equals("update")) {

				System.out.print("수정할 게시물 선택 : ");
				int targetId = sc.nextInt();
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("없는 게시물입니다.");
				} else {
					System.out.print("게시물 제목을 입력해주세요 : ");
					String newTitle = sc.next();

					System.out.print("게시물 내용을 입력해주세요 : ");
					String newBody = sc.next();

					target.setTitle(newTitle);
					target.setBody(newBody);

				}
			}
			if (cmd.equals("delete")) {

				System.out.print("몇번 게시물을 지우시곘습니까 : ");
				int targetId = sc.nextInt();
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다");
				} else {
					articleDao.removeArticle(target);
				}

			}
			if (cmd.equals("read")) {
				System.out.print("몇번 게시물을 확인하시겠습니까? : ");
				int targetId = sc.nextInt();
				Article target = articleDao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					target.setHit(target.getHit() + 1);
					printArticle(target);
					while (true) {
						System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) :");
						int readCmd = sc.nextInt();
						if (readCmd == 1) {
							Comment c = new Comment();
							System.out.print("댓글 내용을 입력해 주세요 : ");
							String comment = sc.next();
							c.setParentId(target.getId());
							c.setComment(comment);
							c.setNickname("익명");

							commentDao.insertComment(c);
							System.out.println("댓글이 등록되었습니다.");
							printArticle(target);

						} else if (readCmd == 2) {
							System.out.println("좋아요 기능");
						} else if (readCmd == 3) {
							System.out.println("수정 기능");
						} else if (readCmd == 4) {
							System.out.println("삭제 기능");
						} else if (readCmd == 5) {
							break;
						} else {
							System.out.println("잘못된 명령어 입니다.");
						}
					}
				}
			}

			if (cmd.equals("search")) {
				System.out.print("검색 항목을 선택해 주세요 (1.제목, 2.내용, 3.제목+내용, 4.작성자) : ");
				int flag = sc.nextInt();
				System.out.print("검색 키워드를 입력해주세요 : ");
				String keyword = sc.next();
				ArrayList<Article> searchedArticles;
				searchedArticles = articleDao.getSearchedArticlesByFlag(flag, keyword);

				printArticles(searchedArticles);
			}
			if (cmd.equals("signup")) {
				System.out.println("==== 회원가입을 진행합니다. ====");
				Member m = new Member();

				System.out.print("아이디를 입력해주세요 :");
				String id = sc.next();
				m.setLoginId(id);

				System.out.print("비밀번호를 입력해주세요 :");
				String pw = sc.next();
				m.setLoginPw(pw);

				System.out.print("닉네임을 입력해주세요 :");
				String nick = sc.next();
				m.setNickname(nick);

				memberDao.insertMember(m);
				System.out.println("==== 회원가입이 완료되었습니다. ====");
			}
			if (cmd.equals("signin")) {
				System.out.print("아이디 :");
				String id = sc.next();
				System.out.print("비밀번호 :");
				String pw = sc.next();
				
				Member member = memberDao.getMembrByLoginIdAndLoginPw(id, pw);
				if (member == null) {
					System.out.println("비밀번호를 틀렸거나 잘못된 회원정보 입니다.");
				}
				else {
					loginedMember = member;
					System.out.println(loginedMember.getNickname() + "님 안녕하세요");
				}
			}
		}
	}

	public static void printArticles(ArrayList<Article> articleList) {
		for (int i = 0; i < articleList.size(); i++) {
			Article article = articleList.get(i);
			System.out.println("번호 : " + article.getId());
			System.out.println("제목 : " + article.getTitle());
			System.out.println("등록날짜 : " + article.getRegDate());
			System.out.println("작성자 : " + article.getNickname());
			System.out.println("조회수 : " + article.getHit());
			System.out.println("===================");
		}
	}

	public static void printComments(ArrayList<Comment> commentList) {
		for (int i = 0; i < commentList.size(); i++) {
			Comment comment = commentList.get(i);
			System.out.println("등록날짜 : " + comment.getComment());
			System.out.println("작성자 : " + comment.getNickname());
			System.out.println("조회수 : " + comment.getRegDate());
			System.out.println("===================");
		}
	}

	public static void printArticle(Article target) {
		System.out.println("==== " + target.getId() + "번게시물 ====");
		System.out.println("번호 : " + target.getId());
		System.out.println("제목 : " + target.getTitle());
		System.out.println("내용 : " + target.getBody());
		System.out.println("등록날짜 : " + target.getRegDate());
		System.out.println("조회수 : " + target.getHit());
		System.out.println("===================");
		System.out.println("--------댓글--------");
		ArrayList<Comment> replies = commentDao.getRepliesByParentId(target.getId());
		printComments(replies);
	}
}
