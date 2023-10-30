package egovframework.job.dto;

import lombok.Getter;

@Getter
public class JobinfoSearchResponse {
	private Long j_id;
//	마감일
	private String d_date;
	private String c_name;
//	주소
	private String address;
//	기업형태
	private String c_type;
	private String job_type;
	private String employment_type;
	private String payment_type;
	private int payment;
	private String career;
	private String edu;

//	등록일
	private String r_date;
	private String detail;
//	state
	private boolean state;
}
