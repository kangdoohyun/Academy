package main;

public class Like {
	private int id; //좋아요 번호
	private int parentId; //게시물(원글) 번호
	private int likeFlag; 
	private int memberId; //체크한 유저 번호
	private String regDate;
	
	public Like(int parentId, int memberId) {
		this.parentId = parentId;
		this.memberId = memberId;
	}
	
	public Like(int parentId, int memberId, String regDate) {
		this.parentId = parentId;
		this.memberId = memberId;
		this.regDate = regDate;
	}

	public Like() {
		
	}
	
	public Like(int parentId, int likeFlag, int checkMemberId) {
		this.parentId = parentId;
		this.likeFlag = likeFlag;
		this.memberId = checkMemberId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getLikeFlag() {
		return likeFlag;
	}
	public void setLikeFlag(int likeFlag) {
		this.likeFlag = likeFlag;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
}
