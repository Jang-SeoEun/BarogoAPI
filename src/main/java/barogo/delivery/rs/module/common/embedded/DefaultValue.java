package barogo.delivery.rs.module.common.embedded;

import barogo.delivery.rs.module.common.utils.SecurityUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Data
@Embeddable
public class DefaultValue {
    @Column(length = 20,nullable = false)
    private String createdId;

    @Column(updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;

    @Column(length = 50)
    private String updatedId;

    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime updatedDate;

    @PrePersist
    public void prePersist() {
        if(StringUtils.isBlank(this.createdId)) {
            this.createdId = SecurityUtil.getCurrentMemberId().get();
        }
        if(createdDate == null) {
            createdDate = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedId = SecurityUtil.getCurrentMemberId().get();
        this.updatedDate = LocalDateTime.now();
    }
}
