package barogo.delivery.rs.module.application.infra;

import barogo.delivery.rs.module.application.domain.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
    Page<Delivery> findByMemberIdAndDefaultValue_CreatedDateBetween(String memberId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    Optional<Delivery> findByDeliverNo(String deliveryNo);
    @Query(value = "select TO_CHAR(NEXTVAL('seq_delivery_no'), 'FM00000')",nativeQuery = true)
    String findByDeliverySeqNo();
}
