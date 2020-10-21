package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	static ArrayList<Article> articles ;
	public static void main(String[] args) {
		articles = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		
		for (int i = 1; i <= 3; i++) {
			Article a1 = new Article(1, "제목1", "내용1");
			Article a2 = new Article(2, "제목2", "내용2");
			Article a3 = new Article(3, "제목3", "내용3");
			
			articles.add(a1);
			articles.add(a2);
			articles.add(a3);
		}
		

		int no = 4;

		while (true) {
			System.out.print("명령어 입력 : ");
			String cmd = sc.next();
			if (cmd.equals("exit")) {
				System.out.println("종료");
				break;
			}
			if (cmd.equals("add")) {

				Article a = new Article();
				a.setId(no);
				no++;
				System.out.print("게시물 제목을 입력해주세요 :");
				String title = sc.next();
				a.setTitle(title);

				System.out.print("게시물 내용을 입력해주세요 :");
				String body = sc.next();
				a.setBody(body);

				articles.add(a);
				System.out.println("게시물이 등록되었습니다.");
//				size++;
			}
			if (cmd.equals("list")) {
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					System.out.println("번호 : " + article.getId());
					System.out.println("제목 : " + article.getTitle());
//					System.out.println("내용 : " + article.getBody());
					System.out.println("===================");
				}

			}
			if (cmd.equals("update")) {

				System.out.print("수정할 게시물 선택 : ");
				int targetid = sc.nextInt();
				int index = getArticleIndexById();
				for (int i = 0; i < articles.size(); i++) {

					int id = articles.get(i).getId();
					if (id == targetid) {
						System.out.print("게시물 제목을 입력해주세요 :");
						String newTitle = sc.next();

						System.out.print("게시물 내용을 입력해주세요 :");
						String newBody = sc.next();

						Article newArticle = new Article();
						newArticle.setId(id);
						newArticle.setTitle(newTitle);
						newArticle.setBody(newBody);
						articles.set(i, newArticle);
						break;
					}
				}
			}
			if (cmd.equals("delete")) {
				System.out.print("몇번 게시물을 지우시곘습니까 : ");
				int targetId = sc.nextInt();
				int exitFlag = 2;
				for (int i = 0; i < articles.size(); i++) {
					int id = articles.get(i).getId();
					if (id == targetId) {
						exitFlag = 1;
						articles.remove(i);

						break;
					}

				}
				if (exitFlag == 2) {
					System.out.println("게시물이 존재하지 않습니다.");
				} else {
					System.out.println(targetid + "번 게시물이 삭제되었습니다.");
				}
			}
			if (cmd.equals("read")) {

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					System.out.print("몇번 게시물을 확인하시겠습니까 : ");
					int targetId = sc.nextInt();
					int joinFlag = 2;
					int j;
					for (j = 0; j < articles.size(); j++) {
						int id = articles.get(j).getId();
						if (id == targetId) {
							joinFlag = 1;
							articles.remove(j);
							System.out.println("==== 1번게시물 ====");
							System.out.println("번호 : " + article.getId());
							System.out.println("제목 : " + article.getTitle());
							System.out.println("내용 : " + article.getBody());
							System.out.println("===================");
							break;
						}
						
					}
					if (joinFlag == 2) {
						System.out.println("게시물이 존재하지 않습니다.");
					} else {
						
					}
					
				}
			}
		}
	}
	//index 버전
	public static int getArticleIndexById(int targetId) {
		for (int i = 0; i < articles.size(); i++) {
			int id = articles.get(i).getId();
			if (id == targetId) {
				return i;
			}
		}
		return -1;
	}
	//Article 버전
	public static Article getArticleIndexById(int targetId) {
		for (int i = 0; i < articles.size(); i++) {
			int id = articles.get(i).getId();
			if (id == targetId) {
				return i;
			}
		}
		return -1;
	}
}
