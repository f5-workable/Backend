package egovframework.job.vo;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResumeVO {

	/** 지원자 아이디 */
	private Long r_id;
	/** 학력 */
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
    public ResumeVO(Long r_id, String education, String job, int payment, String payment_type, String ob_type, String disease, String career, String pr, Long m_num, String title) {
		this.r_id = r_id;
		this.education = education;
		this.job = job;
		this.payment = payment;
		this.payment_type = payment_type;
		this.ob_type = ob_type;
		this.disease = disease;
		this.career = career;
		this.pr = pr;
		this.m_num = m_num;
		this.title = title;
	}
}
