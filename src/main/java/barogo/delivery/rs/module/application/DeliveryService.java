package barogo.delivery.rs.module.application;

import barogo.delivery.rs.module.application.domain.Delivery;
import barogo.delivery.rs.module.application.infra.DeliveryRepository;
import barogo.delivery.rs.module.common.BizException;
import barogo.delivery.rs.module.common.dto.*;
import barogo.delivery.rs.module.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    /**
     * 배달주문요청
     *
     * @param createDtoList
     * @return
     */
    @Transactional
    public List<DeliverySearchResponseDto> deliverySave(List<DeliveryCreateDto> createDtoList) {
        List<Delivery> deliveryList = createDtoList.stream().map(createDto -> {
            Delivery delivery = createDto.convertEntity();
            String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            delivery.updateDeliveryNo("D" + formatDate + deliveryRepository.findByDeliverySeqNo());
            return delivery;
        }).collect(Collectors.toList());
        return deliveryRepository.saveAll(deliveryList)
                .stream().map(Delivery::convertDto)
                .collect(Collectors.toList());

    }

    /**
     * 배달 주문요청 (테스트 데이터를 쌓아놓기 위해서)
     *
     * @param createDtoList
     * @return
     */
    public List<DeliverySearchResponseDto> deliverTestSave(List<DeliveryCreateDto> createDtoList) {
        List<Delivery> deliveryList = createDtoList.stream().map(createDto -> {
            Delivery delivery = createDto.convertEntity();
            String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            delivery.updateDeliveryNo("D" + formatDate + deliveryRepository.findByDeliverySeqNo());
            //사전에 테스트 데이터를 쌓아놓기 위해서
            delivery.testCreatedId();
            return delivery;
        }).collect(Collectors.toList());
        return deliveryRepository.saveAll(deliveryList)
                .stream()
                .map(Delivery::convertDto)
                .collect(Collectors.toList());
    }

    /**
     * 배달조회
     *
     * @param requestDto
     * @return
     */
    @Transactional(readOnly = true)
    public List<DeliverySearchResponseDto> selectDelivery(DeliverySearchRequestDto requestDto) {
        Period period = Period.between(requestDto.getFromDate().toLocalDate(), requestDto.getToDate().toLocalDate());
        if (period.getDays() > 3) {
            throw new BizException("날짜는 최대 3일 까지만 조회 가능 합니다.");
        }

        //헤더에 입력된 토큰정보와 조회조건으로 입려간 memberId가 같은지 확인
        if (StringUtils.isNotEmpty(requestDto.getMemberId())) {
            checkOwnData(requestDto.getMemberId());
        }
        if (!SecurityUtil.hasRoleUser() && StringUtils.isBlank(requestDto.getMemberId())) {
            throw new BizException("memberId는 필수값 입니다.");
        }

        return deliveryRepository.findByMemberIdAndDefaultValue_CreatedDateBetween
                        (requestDto.getMemberId(), requestDto.getFromDate(), requestDto.getToDate(), PageRequest.of(requestDto.getPageNo(), requestDto.getPageSize()))
                .get()
                .map(Delivery::convertDto)
                .collect(Collectors.toList());
    }

    /**
     * 사용자 권한을 가지고 있는 회원에 한해서
     * 토큰정보에 있는 회원아이디와 검색하려고 하는 배달의 주문아이디가 같은지 체크
     *
     * @param memberId
     */
    public void checkOwnData(String memberId) {
        if (SecurityUtil.hasRoleUser() && !memberId.equals(SecurityUtil.getCurrentMemberId().get())) {
            throw new BizException("JWT 토큰 정보와 조회 조건의 memberId가 다릅니다.");
        }
    }

    @Transactional
    public DeliverySearchResponseDto update(String deliveryNo, AddressUpdateDto addressUpdateDto) {
        Optional<Delivery> deliveryOptional = deliveryRepository.findByDeliverNo(deliveryNo);
        if (!deliveryOptional.isPresent()) {
            throw new BizException("해당 배송건은 존재하지 않습니다.");
        }
        Delivery delivery = deliveryOptional.get();
        checkOwnData(delivery.getMemberId());
        delivery.updateAddress(addressUpdateDto);

        return delivery.convertDto();
    }


}
