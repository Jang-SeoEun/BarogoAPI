package barogo.delivery.rs.module.application;

import barogo.delivery.rs.infra.security.JwtTokenProvider;
import barogo.delivery.rs.module.common.dto.AddressCreateDto;
import barogo.delivery.rs.module.common.dto.MemberDto;
import barogo.delivery.rs.module.common.dto.DeliveryCreateDto;
import barogo.delivery.rs.module.common.code.AuthRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitService implements CommandLineRunner {

    public final DeliveryService deliveryService;
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public void run(String... args) throws Exception {
        MemberDto m1 = memberService.join(MemberDto.builder()
                .memberId("SeoEun22")
                .password("Seoeunee0401!")
                .authRole(AuthRole.USER)
                .build());
        memberService.join(MemberDto.builder()
                .memberId("SeoEun33")
                .password("Seoeunee0613!")
                .authRole(AuthRole.USER)
                .build());

        memberService.join(MemberDto.builder()
                .memberId("barogoAdmin")
                .password("barogoAdmin1!")
                .authRole(AuthRole.ADMIN)
                .build());
        List<DeliveryCreateDto> dtoList = new ArrayList<>();
       DeliveryCreateDto createDto = DeliveryCreateDto.builder()
                .memberId("SeoEun")
                .deliveryMsg("Test Message 20220511")
                .addressCreateDto(
                        AddressCreateDto.builder()
                                .name("TestRcvName")
                                .cellPhone("010-1234-1356")
                                .zipcode("06061")
                                .frontAddress("서울특별시 강남구 언주로134길 32")
                                .backAddress("32")
                                .build()
                ).build();
       dtoList.add(createDto);
        DeliveryCreateDto createDto1 = DeliveryCreateDto.builder()
                .memberId("SeoEun22")
                .deliveryMsg("Test Message 20220511")
                .addressCreateDto(
                        AddressCreateDto.builder()
                                .name("TestRcvName")
                                .cellPhone("010-1234-1356")
                                .zipcode("06061")
                                .frontAddress("서울특별시 강남구 언주로134길 32")
                                .backAddress("32")
                                .build()
                ).build();
        dtoList.add(createDto1);
        dtoList.add(createDto1);
        dtoList.add(createDto1);
        dtoList.add(createDto1);
        dtoList.add(createDto1);
        dtoList.add(createDto1);
        dtoList.add(createDto1);
        dtoList.add(createDto1);
        dtoList.add(createDto1);
        dtoList.add(createDto1);
        dtoList.add(createDto1);
        dtoList.add(createDto1);
        dtoList.add(createDto1);
        dtoList.add(createDto1);
        dtoList.add(createDto1);

        deliveryService.deliverTestSave(dtoList);

    }
}
