package barogo.delivery.rs.module.common.utils;

import barogo.delivery.rs.module.common.code.AuthRole;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class SecurityUtil {

    public static Optional<String> getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.info("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String userId = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            userId = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            userId = (String) authentication.getPrincipal();
        }
        return Optional.ofNullable(userId);
    }

    public static Collection<GrantedAuthority> getRole() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null ? Collections.emptyList() : (Collection<GrantedAuthority>) authentication.getAuthorities();
    }

    public static boolean hasRoleUser() {
        return getRole().stream().anyMatch(match-> AuthRole.USER.getCode().equals(match.getAuthority()));
    }
}
