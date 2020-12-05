package honestit.projects.eventuator.model.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter @ToString
public abstract class BaseEntity implements Serializable {

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdOn;
    @Column(insertable = false)
    private LocalDateTime updatedOn;

    @PrePersist
    protected void prePersist() {
        createdOn = LocalDateTime.now();
        updatedOn = null;
    }

    @PreUpdate
    protected void preUpdate() {
        updatedOn = LocalDateTime.now();
    }
}
