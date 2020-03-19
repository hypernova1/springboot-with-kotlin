package org.board.api.domain

import org.board.api.domain.audit.DateAudit
import org.board.api.dto.CategoryDto
import javax.persistence.Column
import javax.persistence.Entity

@Entity
data class Category(

        @Column(unique = true)
        var name: String = "",
        @Column(unique = true)
        var path: String = "",
        var orderNo: Int = 0

) : DateAudit() {

    companion object {
        fun setInstance(request: CategoryDto.Request): Category {
            val category = Category()
            category.name = request.name
            category.path = request.path
            category.orderNo = request.orderNo
            return category
        }
    }

    fun update(request: CategoryDto.Request) {
        this.name = request.name
        this.path = request.path
        this.orderNo = request.orderNo
    }

}