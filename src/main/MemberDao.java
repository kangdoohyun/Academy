package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemberDao {
	private ArrayList<Member> members;
	private int no = 1;

	public MemberDao() {
		members = new ArrayList<>();
		Member a1 = new Member(1, "k123", "1234", "강두현", getCurrentDate());
		members.add(a1);
	}

	public void insertMember(Member m) {
		m.setId(no);
		no++;
		m.setRegDate(getCurrentDate());

		members.add(m);
	}

	private static String getCurrentDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date time = new Date();
		String time1 = format1.format(time);
		return time1;
	}

	public ArrayList<Member> getMembers() {
		return members;
	}
//internet
//	public Member FindById(String id) {
//		for (Member member : members) {
//			if (member.getLoginId().equals(id)) {
//				return member;
//			}
//		}
//		return null;
//	}

	public Member getMembrByLoginIdAndLoginPw(String id, String pw) {
		for(int i = 0; i <members.size(); i++) {
			Member m = members.get(i);
			if(m.getLoginId().equals(id) && m.getLoginPw().equals(pw)) {
				return m;
			}
		}
		return null;
	}

}
