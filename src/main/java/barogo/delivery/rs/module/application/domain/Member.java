package barogo.delivery.rs.module.application.domain;

import barogo.delivery.rs.module.common.dto.MemberDto;
import barogo.delivery.rs.module.common.code.AuthRole;
import barogo.delivery.rs.module.common.converter.AuthRoleTypeConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk01_member",columnNames = {"memberId"})})
@Entity
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long Id;

    @Column(length = 20)
    private String memberId;

    @Column(length = 20)
    private String password;
    @Column(length = 30)
    private String memberName;
    @Convert(converter = AuthRoleTypeConverter.class)
    @Column(length = 1,nullable = false)
    private AuthRole authRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(authRole.getCode()));
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password; // 계정의 비밀번호 리턴
    }

    @Override
    public String getUsername() {
        return this.memberId; // 계정의 고유한 값 리턴
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public MemberDto convertDto() {
        return MemberDto.builder()
                .memberId(memberId)
                .memberName(memberName)
                .authRole(authRole)
                .build();
    }
}
