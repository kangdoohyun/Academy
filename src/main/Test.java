package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ArticleDao dao = new ArticleDao();

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
				}
				
			}
			if (cmd.equals("search")) {
				System.out.print("검색 키워드를 입력해주세요 : ");
				String keyword = sc.next();
				ArrayList<Article> searchedArticles = dao.getSearchedArticlesByTitle(keyword);
				
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
}
