package barogo.delivery.rs.module.application.domain;

import barogo.delivery.rs.module.common.dto.AddressUpdateDto;
import barogo.delivery.rs.module.common.dto.DeliverySearchResponseDto;
import barogo.delivery.rs.module.common.BizException;
import barogo.delivery.rs.module.common.code.DeliveryStatus;
import barogo.delivery.rs.module.common.converter.DeliveryStatusConverter;
import barogo.delivery.rs.module.common.embedded.DefaultValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk01_delivery",columnNames = {"deliverNo"})})
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long deliveryId;

    @Column(length = 14,nullable = false)
    private String deliverNo;

    @Column(length = 20,nullable = false)
    private String memberId;

    @Convert(converter = DeliveryStatusConverter.class)
    @Column(length = 3,nullable = false)
    @Builder.Default
    private DeliveryStatus deliveryStatus = DeliveryStatus.REQUEST_DELIVERY;

    @Column
    private String deliveryMsg;

    @Embedded
    @Builder.Default
    private DefaultValue defaultValue = new DefaultValue();


    @JoinColumn(name = "addressId",foreignKey = @ForeignKey(name = "fk01_delivery"))
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    public void addDeliveryAddress(Address address) {
        this.address = address;
    }

    public DeliverySearchResponseDto convertDto() {
        return DeliverySearchResponseDto.builder()
                .deliveryId(deliveryId)
                .memberId(memberId)
                .deliveryStatus(deliveryStatus.getDesc())
                .deliveryMsg(deliveryMsg)
                .addressResponseDto(getAddress().convertDto())
                .build();

    }

    public Delivery updateDeliveryNo(String deliveryNo) {
        this.deliverNo = deliveryNo;
        return  this;
    }

    public void updateAddress(AddressUpdateDto addressUpdateDto) {
        if(this.deliveryStatus != DeliveryStatus.REQUEST_DELIVERY && this.deliveryStatus != DeliveryStatus.CONFIRM_DELIVERY) {
            throw new BizException("배달 주소수정 가능한 배달상태가 아닙니다.");
        }
        if(ObjectUtils.isNotEmpty(getAddress())) {
            this.getAddress().updateAddress(
                    addressUpdateDto.getZipcode()
                    ,addressUpdateDto.getFrontAddress()
                    ,addressUpdateDto.getBackAddress());
        }
    }
    public void testCreatedId() {
        DefaultValue defaultValue = new DefaultValue();
        defaultValue.setCreatedId("TestId");
        this.defaultValue = defaultValue;
        address.testCreatedId();

    }
}
