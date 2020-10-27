package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommentDao {
private static ArrayList<Comment> comments ;
	
	private int no = 1;
	public CommentDao() {
			comments = new ArrayList<>();
//			Article a1 = new Article(1, "제목1", "내용1", "익명", getCurrentDate());
//			Article a2 = new Article(2, "제목2", "내용2", "익명", getCurrentDate());
//			Article a3 = new Article(3, "제목3", "내용3", "익명", getCurrentDate());
//			
//			articles.add(a1);
//			articles.add(a2);
//			articles.add(a3);
	}
	public void insertComment(Comment a) {
		a.setId(no);
		no++;
		a.setRegDate(getCurrentDate());
		
		comments.add(a);
	}
	
	public void removeArticle(Comment a) {
		comments.remove(a);
	}
	private static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);
		return time1;
	}
	/*public ArrayList<Article> getSearchedArticlesByFlag(int flag, String keyword) {
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
	public static Article getArticleById(int targetId) {
		for (int i = 0; i < articles.size(); i++) {
			int id = articles.get(i).getId();
			if (id == targetId) {
				return articles.get(i);
			}
		}
		return null;
	}*/
	public ArrayList<Comment> getComments() {
		return comments;
	}
	public ArrayList<Comment> getRepliesByParentId(int parentId){
		ArrayList<Comment> searchedComments  = new ArrayList<>();
		for (int i = 0; i < comments.size(); i++) {
			Comment comment = comments.get(i);
			if(comment.getParentId() == parentId) {
				searchedComments.add(comment);
			}
		}
		return comments;
	}
}
