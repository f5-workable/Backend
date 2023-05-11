package egovframework.job.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberVO {
   private Long m_num;   // member 테이블 시퀀스 번호 (PK)
   private String id;
   private String email;
   private String password;
   private String name;
   private String phone;
   private String profil;   // 프로필 이미지 경로
   private boolean deleted;   // 탈퇴 여부
}