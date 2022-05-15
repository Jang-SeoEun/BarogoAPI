package barogo.delivery.rs.module.application;

import barogo.delivery.rs.module.common.dto.AddressUpdateDto;
import barogo.delivery.rs.module.common.dto.DeliveryCreateDto;
import barogo.delivery.rs.module.common.dto.DeliverySearchRequestDto;
import barogo.delivery.rs.module.common.dto.DeliverySearchResponseDto;
import barogo.delivery.rs.module.common.ResponseDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"배달"})
@RequestMapping(path = "/delivery")
@RequiredArgsConstructor
@RestController
public class DeliveryController {
    private final DeliveryService deliveryService;

    @ApiOperation(value = "배달조회", notes = "최대 3일내의 배달을 조회")
    @GetMapping
    public ResponseEntity<ResponseDto<List<DeliverySearchResponseDto>>> selectList(@RequestBody @ApiParam(value = "배달조회조건",required = true) DeliverySearchRequestDto requestDto) {
        return ResponseDto.ok(deliveryService.selectDelivery(requestDto));
    }
    @ApiOperation(value = "배달요청",notes = "배달주문을 요청")
    @PostMapping
    public ResponseEntity<ResponseDto<List<DeliverySearchResponseDto>>> create(@RequestBody List<DeliveryCreateDto> deliveryCreateDto) {
        return ResponseDto.ok(deliveryService.deliverySave(deliveryCreateDto));
    }

    @ApiOperation(value = "배달주소수정", notes = "해당 배달의 주소를 수정  * 배달상태가 REQ(배달요청),CON(배달확인)  상태에서만 주소수정이 가능합니다.")
    @PatchMapping("/{deliveryNo}")
    public ResponseEntity<ResponseDto<DeliverySearchResponseDto>> update(@PathVariable("deliveryNo") @ApiParam(value = "배달 주문번호",required = true) String deliveryNo
            , @RequestBody @ApiParam(value = "배달 주소수정 Dto") AddressUpdateDto addressUpdateDto) {
        return ResponseDto.ok(deliveryService.update(deliveryNo,addressUpdateDto));
    }
}
