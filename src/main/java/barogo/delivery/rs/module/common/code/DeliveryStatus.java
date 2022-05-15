package barogo.delivery.rs.module.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum DeliveryStatus {
    REQUEST_DELIVERY("REQ","배달요청"),
    CONFIRM_DELIVERY("CON","배달확인"),
    PROCESS_DELIVERY("PRO","배달중"),
    COMPLETE_DELIVERY("CMP","배달완료");
    private final String code;
    private final String desc;
}
