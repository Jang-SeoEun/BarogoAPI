package barogo.delivery.rs.infra;

import barogo.delivery.rs.infra.security.JwtTokenProvider;
import barogo.delivery.rs.module.common.BizException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            {
        final String token = request.getHeader("jwt");
        log.info("preHandle: " + token);
        if (!jwtTokenProvider.validateToken(token)) {
            throw new BizException("권한이 유효하지 않습니다");
        }
        request.setAttribute("userID", jwtTokenProvider.getUserPk(token));
        return true;
    }

}
