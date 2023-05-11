package egovframework.job.dto;
import java.util.List;
import java.util.stream.Collectors;

import egovframework.job.vo.MemberVO;
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
public class MemberDTO {
   private Long m_num;   // member 테이블 시퀀스 번호 (PK)
   private String id;
   private String password;
   private String email;
   private String name;
   private String phone;
   private String profil;   // 프로필 이미지 경로
   private boolean deleted;   // 탈퇴 여부
   
   public static MemberDTO of(MemberVO entity) {
       return MemberDTO.builder()
           .m_num(entity.getM_num())
           .id(entity.getId())
           .email(entity.getEmail())
           .password(entity.getPassword())
           .name(entity.getName())
           .phone(entity.getPhone())
           .profil(entity.getProfil())
           .deleted(entity.isDeleted())
           .build();
   }

   public static List<MemberDTO> of(List<MemberVO> entityList) {
       return entityList.stream()
           .map(MemberDTO::of)
           .collect(Collectors.toList());
   }

    public MemberVO toEntity(){
        return MemberVO.builder()
              .m_num(m_num)
              .id(id)
                .email(email)
                .name(name)
                .password(password)
                .phone(phone)
                .profil(profil)
                .deleted(deleted)
                .build();
    }
}