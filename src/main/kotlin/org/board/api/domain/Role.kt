package org.board.api.domain

import javax.persistence.*

@Entity
data class Role(
        @Id
        @GeneratedValue
        val id: Long = -1,

        @Enumerated(EnumType.STRING)
        var name: RoleName = RoleName.USER
)