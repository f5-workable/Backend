package egovframework.job.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobinfoResultVO {
	private Long j_id;
//	마감일
	private String d_date;
	private String c_name;
	private String job_type;
	private String employment_type;
	private String payment_type;
	private int payment;
	private String career;
	private String edu;

//	등록일
	private String r_date;
	private String detail;
}
