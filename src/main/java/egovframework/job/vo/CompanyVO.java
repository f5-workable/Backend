package egovframework.job.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyVO {
   private String c_name;   // 기업명(PK)
   private String c_id;   // 기업 로그인 id
   private String password;   // 기업 로그인 비밀번호
   private String rnum; // 사업자 번호
   private String address;   // 주소
   private String logo; // 기업 로고 이미지 경로
   private String company_type;   // 기업 종류(공공기관, 협회 등,,)
   private String phone;   // 기업 연락처
}