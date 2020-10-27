package main;


public class Comment {
	private int id;
	private int parentId;
	private String comment;
	private String nickname;
	private String regDate;
	
	public Comment() {
		
	}
	public Comment (int id, int parentId, String comment, String nickname, String regDate) {
		this.id = id;
		this.parentId = parentId;
		this.comment = comment;
		this.regDate = regDate;
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
}
