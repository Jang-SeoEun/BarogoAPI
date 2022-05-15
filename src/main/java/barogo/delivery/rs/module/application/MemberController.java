package barogo.delivery.rs.module.application;

import barogo.delivery.rs.module.common.dto.LoginDto;
import barogo.delivery.rs.module.common.dto.MemberDto;
import barogo.delivery.rs.module.common.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"회원"})
@Slf4j
@RequestMapping(produces = "application/json")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @ApiOperation(value = "회원가입",notes = "회원가입 API")
    @PostMapping("/join")
    public ResponseEntity<ResponseDto<MemberDto>> join(@RequestBody @ApiParam(value = "회원가입 요청 DTO") MemberDto memberDto) {
        return ResponseDto.ok(memberService.join(memberDto)) ;
    }

    @ApiOperation(value = "로그인" , notes = "로그인 API")
    @GetMapping("/login")
    public ResponseEntity<ResponseDto<String>> login(@RequestBody @ApiParam(value = "로그인 DTO") LoginDto loginDto) {
        return ResponseDto.ok(memberService.login(loginDto));
    }
}
