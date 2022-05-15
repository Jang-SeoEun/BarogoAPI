package barogo.delivery.rs.module.common.dto;

import barogo.delivery.rs.module.application.domain.Member;
import barogo.delivery.rs.module.common.code.AuthRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ApiModel(value = "회원 Dto", description = "회원아이디, 비밀번호, 이름, 권한을 가진 DTO")
public class MemberDto {
    @ApiModelProperty(value = "회원아이디",example = "SeoEun444",required = true)
    @NotBlank(message = "아이디는 필수값 입니다.")
    String memberId;
    @ApiModelProperty(value = "비밀번호",required = true)
    @NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{12,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 12자이상 20자 이하의 문자열이여야 합니다.")
    private String password;
    @ApiModelProperty(value = "회원 이름")
    String memberName;
    @ApiModelProperty(value = "권한" , required = true,example = "USER")
    AuthRole authRole;

    public Member convertEntity() {
        return Member.builder()
                .memberId(memberId)
                .memberName(memberName)
                .password(password)
                .authRole(authRole)
                .build();
    }

    public Optional<String> isValid() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Collection<ConstraintViolation<MemberDto>> validate = validator.validate(this);

        if (!validate.isEmpty()) {
            return Optional.of(validate.stream().map(invalid -> invalid.getPropertyPath() + " : " + invalid.getMessage())
                    .collect(Collectors.joining("\n")));
        }
        return Optional.empty();
    }
}
