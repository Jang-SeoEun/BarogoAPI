package barogo.delivery.rs.module.common.dto;

import barogo.delivery.rs.module.application.domain.Address;
import barogo.delivery.rs.module.common.code.AddressType;
import barogo.delivery.rs.module.common.embedded.DefaultValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "주소 생성Dto",description = "배달주문자 명, 휴대폰번호, 우편번호, 앞주소, 뒷주소를 가진 DTo")
public class AddressCreateDto {
    @NotBlank
    @ApiModelProperty(value = "배달주문자명",example = "배달주문자명",required = true)
    private String name;
    @NotBlank
    @ApiModelProperty(value = "휴대폰번호" , example = "01016165555",required = true)
    private String cellPhone;
    @NotBlank
    @ApiModelProperty(value = "우편번호", example = "06615",required = true)
    private String zipcode;
    @NotBlank
    @ApiModelProperty(value = "앞주소" , example = "앞주소 추가 생성테스트",required = true)
    private String frontAddress;
    @NotBlank
    @ApiModelProperty(value = "뒷주소" , example = "뒷주소 추가 생성테스트",required = true)
    private String backAddress;


    public Address convertEntity() {
        return Address.builder()
                .addressType(AddressType.RECEIVER)
                .rcvName(name)
                .cellPhone(cellPhone)
                .zipcode(zipcode)
                .frontAddress(frontAddress)
                .backAddress(backAddress)
                .build();
    }
}
