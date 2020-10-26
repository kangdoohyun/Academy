package main_test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import main.Article;

public class ArticleDao {
	private static ArrayList<Article> articles ;
	private int no = 4;
	public ArticleDao() {
			articles = new ArrayList<>();
			Article a1 = new Article(1, "제목1", "내용1", "익명", getCurrentDate());
			Article a2 = new Article(2, "제목2", "내용2", "익명", getCurrentDate());
			Article a3 = new Article(3, "제목3", "내용3", "익명", getCurrentDate());
			
			articles.add(a1);
			articles.add(a2);
			articles.add(a3);
	}
	public void insertArticle(Article a) {
		a.setId(no);
		no++;
		a.setRegDate(getCurrentDate());
		
		articles.add(a);
	}
	
	public void removeArticle(Article a) {
		articles.remove(a);
	}
	private static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);
		return time1;
	}
	public ArrayList<Article> getSearchedArticlesByTitle(String keywordTitle) {
		ArrayList<Article> searchArticles = new ArrayList<>();
		
		for(int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			String str = article.getTitle();
			if(str.contains(keywordTitle)) {
				searchArticles.add(article);
			}
		}
		return searchArticles;      
	}
	public ArrayList<Article> getSearchedArticlesByBody(String keywordBody){
		ArrayList<Article> searchItem = new ArrayList<>();
		for(int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			String str = article.getBody();
			if(str.contains(keywordBody)) {
				searchItem.add(article);
			}
		}
		return searchItem;  
	}
	public ArrayList<Article> getSearchedArticlesByTitleBody(String keywordTitle, String keywordBody){
		ArrayList<Article> searchItem = new ArrayList<>();
		for(int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			String body = article.getBody();
			String title = article.getTitle();
			if(body.contains(keywordBody)) {
				searchItem.add(article);
			}else if(title.contains(keywordTitle)){
				searchItem.add(article);
			}
		}
		return searchItem;  
	}
	public static Article getArticleById(int targetId) {
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
