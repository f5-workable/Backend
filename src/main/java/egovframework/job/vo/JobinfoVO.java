package egovframework.job.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobinfoVO {
	
	private Long j_id;
	
	private String d_date;
	private String j_name;
	private String job_type;
	private String employment_type;
	private String payment_type;
	private int payment;
	private String career;
	private String edu;
	private String r_date;
	private String detail;
}