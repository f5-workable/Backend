package egovframework.job.dto;
import java.util.List;
import java.util.stream.Collectors;

import egovframework.job.vo.CompanyVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDTO {
      private String c_name;   // 기업명(PK)
      private String c_id;   // 기업 로그인 id
      private String password;   // 기업 로그인 비밀번호
      private String rnum; // 사업자 번호
      private String address;   // 주소
      private String logo; // 기업 로고 이미지 경로
      private String company_type;   // 기업 종류(공공기관, 협회 등,,)
      private String phone;   // 기업 연락처
      
      public static CompanyDTO of(CompanyVO entity) {
          return CompanyDTO.builder()
              .c_name(entity.getC_name())
              .c_id(entity.getC_id())
              .password(entity.getPassword())
              .rnum(entity.getRnum())
              .address(entity.getAddress())
              .logo(entity.getLogo())
              .company_type(entity.getCompany_type())
              .phone(entity.getPhone())
              .build();
      }

      public static List<CompanyDTO> of(List<CompanyVO> entityList) {
          return entityList.stream()
              .map(CompanyDTO::of)
              .collect(Collectors.toList());
      }

       public CompanyVO toEntity(){
           return CompanyVO.builder()
                 .c_name(c_name)
                 .c_id(c_id)
                 .password(password)
                 .rnum(rnum)
                 .address(address)
                 .logo(logo)
                 .company_type(company_type)
                 .phone(phone)
                   .build();
       }
}