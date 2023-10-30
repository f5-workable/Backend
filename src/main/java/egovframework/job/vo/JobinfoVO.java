package egovframework.job.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JobinfoVO {
	
	private Long j_id;
//	마감일
	private String d_date;
	private String j_name;
	private String job_type;
	private String employment_type;
	private String payment_type;
	private int payment;
	private String career;
	private String edu;

//	등록일
	private String r_date;
	private String detail;
	
	@Builder
    public JobinfoVO(Long j_id, String d_date, String j_name, String job_type, String employment_type, String payment_type, int payment, String career, String edu, String r_date, String detail) {
		this.j_id = j_id;
		this.d_date = d_date;
		this.j_name = j_name;
		this.job_type = job_type;
		this.employment_type = employment_type;
		this.payment_type = payment_type;
		this.payment = payment;
		this.career = career;
		this.edu = edu;
		this.r_date = r_date;
		this.detail = detail;
	}
}