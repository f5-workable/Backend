package egovframework.job.dto;

import egovframework.job.vo.ResumeVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResumeDTO {

	private Long r_id;
	private int age;
    private String place;
    private String job;
    private String payment_type;
    private int payment;
    private String ob_type;
    private String disease;
    private String career;
    private String pr;
    private boolean r_default;
    private boolean r_open;
    private Long m_num;
    private String title;
    
    @Builder
    public ResumeDTO(final ResumeVO entity) {
    	this.r_id =entity.getR_id();
    	this.age = entity.getAge();
    	this.place = entity.getPlace();
    	this.job = entity.getJob();
    	this.payment_type = entity.getPayment_type();
    	this.payment = entity.getPayment();
    	this.ob_type = entity.getOb_type();
    	this.disease = entity.getDisease();
    	this.career = entity.getCareer();
    	this.pr = entity.getPr();
    	this.title = entity.getTitle();
    }
    
    public ResumeVO toEntity() {
    	return ResumeVO.builder()
    			.r_id(r_id)
    			.age(age)
    			.place(place)
    			.job(job)
    			.payment(payment)
    			.payment_type(payment_type)
    			.ob_type(ob_type)
    			.disease(disease)
    			.career(career)
    			.pr(pr)
    			.r_default(r_default)
    			.r_open(r_open)
    			.m_num(m_num)
    			.title(title)
    			.build();
    }
}
