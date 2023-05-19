package egovframework.job.vo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeVO {

	/** 지원자 아이디 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long r_id;
	private int age;
	/** 학력 */
	private String education;
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
    private ResumeRegionVO region;
}