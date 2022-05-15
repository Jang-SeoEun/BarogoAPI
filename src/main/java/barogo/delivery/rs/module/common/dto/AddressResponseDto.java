package barogo.delivery.rs.module.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "주소정보 DTO", description = "주소 인덱스, 주소 타입, 우편번호, 주문자명, 휴대폰번호, 우편번호, 앞주소, 뒷주소 를 가진 DTO")
public class AddressResponseDto {
    private Long addressId;
    private String addressType;
    private String rcvName;
    private String cellPhone;
    private String zipcode;
    private String frontAddress;
    private String backAddress;
}
