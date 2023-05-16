package egovframework.job.vo;

<<<<<<< .merge_file_3ZB4zf
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobinfoVO {
	
	private Long j_id;
	
=======
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobinfoVO {
	
	private Long j_id;
//	마감일
>>>>>>> .merge_file_t6F315
	private String d_date;
	private String j_name;
	private String job_type;
	private String employment_type;
	private String payment_type;
	private int payment;
	private String career;
	private String edu;
<<<<<<< .merge_file_3ZB4zf
=======
//	등록일
>>>>>>> .merge_file_t6F315
	private String r_date;
	private String detail;
}