package egovframework.job.dto;

import egovframework.job.vo.ApplyVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyDTO {
   private Long a_id;
   private String state;
   private Long j_num;
   private Long cr_num;
   
   @Builder
   public ApplyDTO(ApplyVO entity) {
      this.a_id = entity.getA_id();
      this.state = entity.getState();
      this.j_num = entity.getJ_num();
      this.cr_num = entity.getCr_num();
   }
   
   public ApplyVO toEntity() {
      return ApplyVO.builder()
            .a_id(a_id)
            .state(state)
            .j_num(j_num)
            .cr_num(cr_num)
            .build();
   }
}