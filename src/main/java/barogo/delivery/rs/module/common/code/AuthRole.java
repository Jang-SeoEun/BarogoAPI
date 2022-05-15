package barogo.delivery.rs.module.common.code;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel(value = "회원권한 Enum",description = "관리자는 ADMIN, 사용자는 USER라는 권한을 갖는다")
public enum AuthRole {
    USER("USER","회원"),
    ADMIN("ADMIN","관리자");
    private String code;
    private String desc;
}
