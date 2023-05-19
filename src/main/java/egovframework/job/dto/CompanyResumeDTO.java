package egovframework.job.dto;

import egovframework.job.vo.CompanyResumeVO;
import egovframework.job.vo.ResumeVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyResumeDTO {
   private Long cr_num;
   private String payment_type;
   private int payment;
   private String ob_type;
   private String disease;
   private String career;
   private String pr;
   private Long m_num;
   private String education;
   
   @Builder
    public CompanyResumeDTO(final CompanyResumeVO entity) {
       this.cr_num = entity.getCr_num();
       this.payment_type = entity.getPayment_type();
       this.payment = entity.getPayment();
       this.ob_type = entity.getOb_type();
       this.disease = entity.getDisease();
       this.career = entity.getCareer();
       this.pr = entity.getPr();
       this.education = entity.getEducation();
    }
    
    public CompanyResumeVO toEntity() {
       return CompanyResumeVO.builder()
             .cr_num(cr_num)
             .payment(payment)
             .payment_type(payment_type)
             .ob_type(ob_type)
             .disease(disease)
             .career(career)
             .pr(pr)
             .m_num(m_num)
             .education(education)
             .build();
    }
    
    public void toCompanyResume(ResumeVO entity) {
       this.education = entity.getEducation();  
       this.payment_type = entity.getPayment_type();
       this.payment = entity.getPayment();
       this.ob_type = entity.getOb_type();
       this.disease = entity.getDisease();
       this.career = entity.getCareer();
       this.pr = entity.getPr();
    }
}