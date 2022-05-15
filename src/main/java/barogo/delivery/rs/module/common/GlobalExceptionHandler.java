package barogo.delivery.rs.module.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    protected ResponseEntity<ResponseDto> handleBizException(BizException e) {
        log.error("BizException",e);
        ResponseDto responseDto = new ResponseDto(e.getMessage());

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseDto> handleException(Exception e) {
        log.error("Exception",e);
        ResponseDto responseDto = new ResponseDto(e.getMessage());

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
}
