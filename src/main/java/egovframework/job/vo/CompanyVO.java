package egovframework.job.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyVO {
	
	// 기업 테이블 시퀀스 번호 (PK)
	private	Long c_num;
	
	// 기업 아이디
	private String c_id;

	// 기업 비밀번호
	private String c_password;
	
	// 기업 이름
	private String c_name;
	
	// 기업 사업자 번호
	private String rnum;
	
	// 기업 주소
	private String address;
	
	// 기업 형태
	private String c_type;
	
	//기업 연락처
	private String phone;
	
	// 기업 로고
	private String logo;
	
	// 기업 구분
	private String role;
}