package org.board.api.domain.audit

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class DateAudit(

        @CreatedDate
        @Column(updatable = false)
        @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
        var createdDate: LocalDateTime? = null,

        @LastModifiedDate
        @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
        var updatedDate: LocalDateTime? = null
)