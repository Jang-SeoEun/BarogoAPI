package barogo.delivery.rs.module.application;

import barogo.delivery.rs.infra.security.JwtTokenProvider;
import barogo.delivery.rs.module.application.domain.Member;
import barogo.delivery.rs.module.common.dto.LoginDto;
import barogo.delivery.rs.module.common.dto.MemberDto;
import barogo.delivery.rs.module.application.infra.MemberRepository;
import barogo.delivery.rs.module.common.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    @Transactional(readOnly = true)
    public Optional<Member> findByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    /**
     * 회원가입
     *
     * @param memberDto
     * @return
     */
    @Transactional
    public MemberDto join(MemberDto memberDto) {
        if (memberDto.isValid().isPresent()) {
            throw new BizException(memberDto.isValid().get());
        } else {
            memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
            return memberRepository.save(memberDto.convertEntity()).convertDto();
        }
    }
    @Transactional
    public String login(LoginDto loginDto) {
        Optional<Member> userOptional = memberRepository.findByMemberId(loginDto.getMemberId());
        if (userOptional.isPresent()) {
            Member member = userOptional.get();
            if (passwordEncoder.matches(loginDto.getPassword(), userOptional.get().getPassword())) {
                return jwtTokenProvider.createToken(member.getMemberId(), member.getAuthRole().getCode());
            } else {
                throw new BizException("아이디 or 비밀번호가 틀렸습니다.");
            }
        } else {
            throw new BizException("해당 회원정보가 존재하지 않습니다.");
        }
    }
}
