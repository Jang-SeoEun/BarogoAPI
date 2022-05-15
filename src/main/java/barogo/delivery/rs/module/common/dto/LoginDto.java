package barogo.delivery.rs.module.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ApiModel(value = "로그인 Dto", description = "회원아이디, 비밀번호를 가진 DTO")
public class LoginDto {
    @ApiModelProperty(value = "회원아이디",example = "SeoEun444",required = true)
    @NotBlank(message = "아이디는 필수값 입니다.")
    String memberId;
    @ApiModelProperty(value = "비밀번호",required = true)
    private String password;
}
