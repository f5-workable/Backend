package egovframework.job.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import egovframework.job.vo.ResumeRegionVO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // 알 수 없는 필드 무시
public class EResumeDTO {
    private int r_id;
    private String education;
    private String[] regions;
    private String job;
    private String payment_type;
    private int payment;
    private String ob_type;
    private String disease;
    private String title;
    private String member_name;
}
