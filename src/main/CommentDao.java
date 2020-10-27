package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommentDao {
	private static ArrayList<Comment> comments = new ArrayList<>();
	
	
	public CommentDao() {
		
	}
	private static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);
		return time1;
	}
	public ArrayList<Comment> getComments() {
		return comments;
	}
}
