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
   private int age;
   private String place;
   private String job;
   private String payment_type;
   private int payment;
   private String ob_type;
   private String disease;
   private String career;
   private String pr;
   private Long m_num;
   private String title;

   @Builder
    public CompanyResumeDTO(final CompanyResumeVO entity) {
       this.cr_num = entity.getCr_num();
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
    
    public CompanyResumeVO toEntity() {
       return CompanyResumeVO.builder()
             .cr_num(cr_num)
             .age(age)
             .place(place)
             .job(job)
             .payment(payment)
             .payment_type(payment_type)
             .ob_type(ob_type)
             .disease(disease)
             .career(career)
             .pr(pr)
             .m_num(m_num)
             .title(title)
             .build();
    }
    
    public void toCompanyResume(ResumeVO entity) {
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
}
