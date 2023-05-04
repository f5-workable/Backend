package egovframework.job.dto;

import egovframework.job.domain.Resume;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeDTO {
	private Long r_id;
	private int age;
    private String place;
    private String job;
    private String payment;
    private String payment_type;
    private String ob_type;
    private String disease;
    private String career;
    private String pr;
    private boolean r_default;
    private boolean r_open;
    private Long m_num;
    private String title;
    
    @Builder
    public ResumeDTO(final Resume entity) {
    	this.r_id = entity.getR_id();
    	this.age = entity.getAge();
    	this.place = entity.getPlace();
    	this.job = entity.getJob();
    	this.payment = entity.getPayment();
    	this.payment_type = entity.getPayment_type();
    	this.ob_type = entity.getOb_type();
    	this.disease = entity.getDisease();
    	this.career = entity.getCareer();
    	this.pr = entity.getPr();
    	this.title = entity.getTitle();
    }
    
    public Resume toEntity() {
    	return Resume.builder()
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
