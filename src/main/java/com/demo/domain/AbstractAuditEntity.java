package com.demo.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditEntity {
    @CreatedDate
    private Instant createdAt;
    @ManyToOne
    @JoinColumn(name = "created_by")
    @CreatedBy
    private Employee createdBy;
    @LastModifiedDate
    private Instant lastModifiedAt;
    @ManyToOne
    @JoinColumn(name = "last_modified_by")
    @LastModifiedBy
    private Employee lastModifiedBy;
}
