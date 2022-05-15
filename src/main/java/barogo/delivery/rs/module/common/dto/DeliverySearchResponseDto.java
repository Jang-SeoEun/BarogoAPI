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
@ApiModel(value = "배달정보DTO",description = "배달시퀀스, 회원아이디, 배달번호, 배달상태, 주소정보를 DTO")
public class DeliverySearchResponseDto {
    @ApiModelProperty(value = "배달인덱스")
    private Long deliveryId;
    @ApiModelProperty(value = "회원아이디")
    private String memberId;
    @ApiModelProperty(value = "배달번호")
    private String deliveryNo;
    @ApiModelProperty(value = "배달상태")
    private String deliveryStatus;
    @ApiModelProperty(value = "배송메시지")
    private String deliveryMsg;
    @ApiModelProperty(value = "주소정보DTO")
    private AddressResponseDto addressResponseDto;
}
