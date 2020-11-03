package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LikeDao {
	private ArrayList<Like> likes;
	private int no = 4;
	public LikeDao() {
		likes = new ArrayList<>();
		
	}
	public void insertLike(Like l) {
		l.setId(no);
		no++;
		l.setRegDate(getCurrentDate());

		likes.add(l);
	}
	public void insertArticle(Article a) {
		a.setId(no);
		no++;
		a.setRegDate(getCurrentDate());

		likes.add(l);
	}
	private static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);
		return time1;
	}
}
