package barogo.delivery.rs.module.application.domain;

import barogo.delivery.rs.module.common.dto.AddressResponseDto;
import barogo.delivery.rs.module.common.code.AddressType;
import barogo.delivery.rs.module.common.converter.AddressTypeConverter;
import barogo.delivery.rs.module.common.embedded.DefaultValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long addressId;

    @Column(length = 1,nullable = false)
    @Convert(converter = AddressTypeConverter.class)
    private AddressType addressType;

    @Column(length = 50,nullable = false)
    private String rcvName;

    @Column(length = 25,nullable = false)
    private String cellPhone;

    @Column(length = 20,nullable = false)
    private String zipcode;

    @Column(length = 200,nullable = false)
    private String frontAddress;

    @Column(length = 200)
    private String backAddress;

    @Embedded
    @Builder.Default
    private DefaultValue defaultValue = new DefaultValue();
    public AddressResponseDto convertDto(){
        return AddressResponseDto.builder()
                .addressId(addressId)
                .addressType(addressType.getDesc())
                .rcvName(rcvName)
                .cellPhone(cellPhone)
                .zipcode(zipcode)
                .frontAddress(frontAddress)
                .backAddress(backAddress)
                .build();
    }

    public void updateAddress(String zipcode,String frontAddress,String backAddress) {
        this.zipcode = zipcode;
        this.frontAddress = frontAddress;
        this.backAddress = backAddress;
    }
    public void testCreatedId() {
        DefaultValue defaultValue = new DefaultValue();
        defaultValue.setCreatedId("TestId");
        this.defaultValue = defaultValue;
    }

}
