package org.board.api.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Role(
        @Id
        @GeneratedValue
        val id: Long = -1,

        val name: RoleName = RoleName.USER
)