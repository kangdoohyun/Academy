package main;

import java.util.Scanner;

public class Exam {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String title = "";
		String body = "";
		while(true) {
			System.out.print("명령어 입력 : ");
			String str = sc.next();
			if(str.equals("exit")) {
				System.out.println("종료");
				break;
			}
			if(str.equals("add")) {
				
				System.out.print("게시물 제목을 출력해주세요 : ");
				title = sc.next();
				System.out.print("게시물 내용을 출력해주세요 : ");
				body = sc.next();
			}
			if(str.equals("list")) {
				System.out.println("제목 : " + title);
				System.out.println("내용 : " + body);
			}
		}
	}

}
