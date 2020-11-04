package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class ArticleDao {
	private ArrayList<Article> articles;
	private int no = 4;

	public ArticleDao() {
		articles = new ArrayList<>();
		Article a1 = new Article(1, "제목1", "내용1", 1, Util.getCurrentDate());
		Article a2 = new Article(2, "제목2", "내용2", 2, Util.getCurrentDate());
		Article a3 = new Article(3, "제목3", "내용3", 3, Util.getCurrentDate());

		articles.add(a1);
		articles.add(a2);
		articles.add(a3);
	}

	public void insertArticle(Article a) {
		a.setId(no);
		no++;
		a.setRegDate(Util.getCurrentDate());

		articles.add(a);
	}

	public void removeArticle(Article a) {
		articles.remove(a);
	}
	public ArrayList<Article> getSearchedArticlesByFlag(int flag, String keyword) {
		ArrayList<Article> searchedArticles = new ArrayList<>();

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			String str = article.getPropertiesByFlag(flag); // 각 게시물 제목
			if (str.contains(keyword)) {
				searchedArticles.add(article);
			}
		}

		return searchedArticles;
	}

	public Article getArticleById(int targetId) {
		for (int i = 0; i < articles.size(); i++) {
			int id = articles.get(i).getId();
			if (id == targetId) {
				return articles.get(i);
			}
		}
		return null;
	}

	public ArrayList<Article> getArticles() {
		return articles;
	}
}
