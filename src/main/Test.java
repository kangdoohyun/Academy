package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ArticleDao dao = new ArticleDao();
		CommentDao cDao = new CommentDao();

		while (true) {
			System.out.print("명령어 입력 : ");
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
				
				dao.insertArticle(a);
				System.out.println("게시물이 등록되었습니다.");
				
				
			}
			if (cmd.equals("list")) {
				ArrayList<Article> articles = dao.getArticles();
				
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
				Article target = dao.getArticleById(targetId);
				if (target == null) {
					System.out.println("없는 게시물입니다.");
				}
				else {
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
				Article target = dao.getArticleById(targetId);
				if (target == null) {
					System.out.println("게시물이 존재하지 않습니다");
				}
				else {
					dao.removeArticle(target);
				}
				
				
			}
			if (cmd.equals("read")) {
				System.out.print("몇번 게시물을 확인하시겠습니까? : ");
				int targetId = sc.nextInt();
				Article target = dao.getArticleById(targetId);
				if(target == null) {
					System.out.println("게시물이 존재하지 않습니다.");
				}
				else {
					target.setHit(target.getHit() + 1);
					System.out.println("==== " + target.getId() + "번게시물 ====");
					System.out.println("번호 : " + target.getId());
					System.out.println("제목 : " + target.getTitle());
					System.out.println("내용 : " + target.getBody());
					System.out.println("===================");
					System.out.println("--------댓글--------");
					ArrayList<Comment> replies1 = cDao.getRepliesByParentId(target.getId());
					printComments(replies1);
					while(true) {
						System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) :");
						int readCmd = sc.nextInt();
						if(readCmd == 1) {
							Comment c = new Comment();
							System.out.print("댓글 내용을 입력해 주세요 : ");
							String comment = sc.next();
							c.setParentId(target.getId());
							c.setComment(comment);
							c.setNickname("익명");
							
							cDao.insertComment(c);
							System.out.println("댓글이 등록되었습니다.");
							System.out.println("==== " + target.getId() + " ====");
							System.out.println("번호 : " + target.getId());
							System.out.println("제목 : " + target.getTitle());
							System.out.println("내용 : " + target.getBody());
							System.out.println("===============");
							System.out.println("================댓글==============");
							ArrayList<Comment> replies2 = cDao.getRepliesByParentId(target.getId());
							printComments(replies2);
							
			
							}else if (readCmd == 2) {
								System.out.println("좋아요 기능");
							}else if (readCmd == 3) {
								System.out.println("수정 기능");
							}else if (readCmd == 4) {
								System.out.println("삭제 기능");
							}else if (readCmd == 5){
								break;
							}else {
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
				searchedArticles = dao.getSearchedArticlesByFlag(flag, keyword);	
				
				printArticles(searchedArticles);
			}
		}
	}
		
	
	public static void printArticles(ArrayList<Article> articleList) {
		for(int i = 0; i < articleList.size(); i++) {
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
		for(int i = 0; i < commentList.size(); i++) {
			Comment comment = commentList.get(i);
			System.out.println("등록날짜 : " + comment.getComment());
			System.out.println("작성자 : " + comment.getNickname());
			System.out.println("조회수 : " + comment.getRegDate());
			System.out.println("===================");
		}
	}
}
