package egovframework.job.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberVO {
	
	// 멤버 테이블 시퀀스 번호 (PK)
	private Long m_num;
	
	// 멤버 아이디
	private String id;
	
	// 멤버 비밀번호
	private String password;
	
	// 멤버 생년월일
	private String birth;
	
	// 멤버 이름
	private String name;
	
	// 멤버 성별
	private String gender;
		
	// 멤버 이메일
	private String email;
	
	// 멤버 전화번호
	private String phone;
	
	// 멤버 프로필 경로
	private String profil;
	
	// 멤버 구분
	private String role;
	
	// 멤버 탈퇴 여부
	private boolean deleted;
}