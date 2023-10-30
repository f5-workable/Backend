package egovframework.job.dto;

import lombok.Getter;

@Getter
public class ResumeSearchResponse {

	private Long r_id;
	private String education;
	private String region;
    private String job;
    private String payment_type;
    private int payment;
    private String ob_type;
    private String disease;
    private String career;
    private String pr;
//  대표이력서 여부
    private boolean r_default;
    private Long m_num;
    private String title;
//  이력서를 작성한 회원의 이름
    private String name;
}
