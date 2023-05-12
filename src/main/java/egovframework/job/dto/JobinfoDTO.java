package egovframework.job.dto;

import egovframework.job.vo.JobinfoVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobinfoDTO {
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

    @Builder
    public JobinfoDTO(JobinfoVO entity) {
       this.j_id = entity.getJ_id();
       this.d_date = entity.getD_date();
       this.j_name = entity.getJ_name();
       this.job_type = entity.getJob_type();
       this.employment_type = entity.getEmployment_type();
       this.payment_type = entity.getPayment_type();
       this.payment = entity.getPayment();
       this.career = entity.getCareer();
       this.edu = entity.getEdu();
       this.r_date = entity.getR_date();
       this.detail = entity.getDetail();
    }
    
    public JobinfoVO toEntity() {
       return JobinfoVO.builder()
             .j_id(j_id)
             .d_date(d_date)
             .j_name(j_name)
             .job_type(job_type)
             .employment_type(employment_type)
             .payment_type(payment_type)
             .payment(payment)
             .career(career)
             .edu(edu)
             .r_date(r_date)
             .detail(detail)
             .build();
    }
}