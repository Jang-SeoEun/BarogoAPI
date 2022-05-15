package barogo.delivery.rs.module.application.infra;

import barogo.delivery.rs.module.application.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByMemberId(String memberId);
}
