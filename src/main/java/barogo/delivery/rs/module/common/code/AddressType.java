package barogo.delivery.rs.module.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AddressType {
    SENDER("S","발송지"),
    RECEIVER("R","배송지");
    private final String code;
    private final String desc;
}
