package com.luanvv.microservices.bookstore.product_import.entities

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import lombok.ToString
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

@Table
@EntityListeners(AuditingEntityListener::class)
@Entity
data class Book (
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    var id: String = "",
    @NotEmpty
    var title: String = "",
    @NotEmpty
    var author: String = "",
    @NotEmpty
    var genre: String = "",
    @NotEmpty
    var status: String = "",
    @Min(1)
    var height: Int = 0,
    @NotEmpty
    var publisher: String = "",
    @Column(name = "isbn_10")
    @NotEmpty
    var isbn10: String = "",
)