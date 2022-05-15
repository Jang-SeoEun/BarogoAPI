package barogo.delivery.rs.module.common;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseDto<T> {
    private int code;
    private String message;
    private String error;

    private T response;

    public ResponseDto(String message) {
        this(502,message, null, null);
    }
    public ResponseDto(int code, T response) {
        this(code, null, null, response);
    }


    public static <T> ResponseEntity<ResponseDto<T>> ok(T response) {
        return ResponseEntity.ok(new ResponseDto<>(HttpStatus.OK.value(), response));
    }

}