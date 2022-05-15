package barogo.delivery.rs.module.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "배달주소수정",description = "우편번호, 앞주소, 뒷주소 수정Dto")
public class AddressUpdateDto {
    @ApiModelProperty(value = "우편번호",example = "06243")
    String zipcode;
    @ApiModelProperty(value = "앞주소" ,example = "앞주소 변경테스트")
    String frontAddress;
    @ApiModelProperty(value = "뒷주소",example = "뒷주소 변경 테스트")
    String backAddress;
}
