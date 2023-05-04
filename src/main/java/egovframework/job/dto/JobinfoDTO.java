package egovframework.job.dto;


import egovframework.job.domain.Jobinfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobinfoDTO {
	private Long j_id;
	private String d_date;
    private String j_name;
    private String job_type;
    private String employment_type;
    private String payment_type;
    private String payment;
    private String career;
    private String edu;
    private String detail;

    @Builder
    public JobinfoDTO(final Jobinfo entity) {
    	this.j_id = entity.getJ_id();
    	this.d_date = entity.getD_date();
    	this.j_name = entity.getJ_name();
    	this.job_type = entity.getJob_type();
    	this.employment_type = entity.getEmployment_type();
    	this.payment_type = entity.getPayment_type();
    	this.payment = entity.getPayment();
    	this.career = entity.getCareer();
    	this.edu = entity.getEdu();
    	this.detail = entity.getDetail();
    }
    
    public Jobinfo toEntity() {
    	return Jobinfo.builder()
    			.j_id(j_id)
    			.d_date(d_date)
    			.j_name(j_name)
    			.job_type(job_type)
    			.employment_type(employment_type)
    			.payment_type(payment_type)
    			.payment(payment)
    			.career(career)
    			.edu(edu)
    			.detail(detail)
    			.build();
    }
}