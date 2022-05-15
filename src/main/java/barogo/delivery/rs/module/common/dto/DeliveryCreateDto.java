package barogo.delivery.rs.module.common.dto;

import barogo.delivery.rs.module.application.domain.Delivery;
import barogo.delivery.rs.module.common.code.DeliveryStatus;
import barogo.delivery.rs.module.common.embedded.DefaultValue;
import barogo.delivery.rs.module.common.utils.SecurityUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "배달추가DTO", description = "회원아이디, 배송메시지, 배달지 주소정보를 가진 DTO")
public class DeliveryCreateDto {
    @ApiModelProperty(value = "회원아이디", example = "SeoEun22")
    private String memberId;
    @ApiModelProperty(value = "배송메시지", example = "배송메시지 예시")
    private String deliveryMsg;
    @NotBlank
    @ApiModelProperty(value = "배달지 주소정보DTO")
    private AddressCreateDto addressCreateDto;

    public Delivery convertEntity() {
        Delivery delivery = Delivery.builder()
                .memberId(memberId)
                .deliveryMsg(deliveryMsg)
                .build();
        delivery.addDeliveryAddress(
                AddressCreateDto.builder()
                        .name(addressCreateDto.getName())
                        .cellPhone(addressCreateDto.getCellPhone())
                        .zipcode(addressCreateDto.getZipcode())
                        .frontAddress(addressCreateDto.getFrontAddress())
                        .backAddress(addressCreateDto.getBackAddress())
                        .build().convertEntity());
        return delivery;
    }

    public Delivery convertTestEntity() {
        Delivery delivery = Delivery.builder()
                .memberId(memberId)
                .deliveryMsg(deliveryMsg)
                .build();
        delivery.addDeliveryAddress(
                AddressCreateDto.builder()
                        .name(addressCreateDto.getName())
                        .cellPhone(addressCreateDto.getCellPhone())
                        .zipcode(addressCreateDto.getZipcode())
                        .frontAddress(addressCreateDto.getFrontAddress())
                        .backAddress(addressCreateDto.getBackAddress())
                        .build().convertEntity());
        return delivery;
    }

    public String getMemberId() {
        return SecurityUtil.hasRoleUser() ? SecurityUtil.getCurrentMemberId().get() : this.memberId;
    }
}
