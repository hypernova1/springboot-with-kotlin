package org.board.api.domain

import org.board.api.domain.audit.DateAudit
import javax.persistence.*

@Entity
data class Role(@Enumerated(EnumType.STRING) var name: RoleName = RoleName.USER) : DateAudit()