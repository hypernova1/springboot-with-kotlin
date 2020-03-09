package org.board.api.domain

import lombok.Getter
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Getter
@Entity
class Post(
        @Id
        @GeneratedValue
        var id: Long?,
        var title: String,
        var content: String,
        @ManyToOne
        var writer: Account?
) {
    constructor() : this(null, "", "", null)
}