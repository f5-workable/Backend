package egovframework.job.dto;

import java.util.List;

import egovframework.job.vo.ResumeRegionVO;
import egovframework.job.vo.ResumeVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDTO {

	private Long r_id;
	private String education;
	private String[] place;
    private String job;
    private String payment_type;
    private int payment;
    private String ob_type;
    private String disease;
    private String career;
    private String pr;
    private boolean r_default;
    private Long m_num;
    private String title;
    
    private List<ResumeRegionVO> region;
    
    @Builder
    public ResumeDTO(ResumeVO entity) {
    	this.r_id =entity.getR_id();
    	this.education = entity.getEducation();
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
    			.education(education)
    			.job(job)
    			.payment(payment)
    			.payment_type(payment_type)
    			.ob_type(ob_type)
    			.disease(disease)
    			.career(career)
    			.pr(pr)
    			.r_default(r_default)
    			.m_num(m_num)
    			.title(title)
    			.build();
    }
}
