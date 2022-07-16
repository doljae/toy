package com.example.kopring.board.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class Board(
    var title: String,
    var author: String,
    var content: String?
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long = 0

    @CreatedDate
    @Column(updatable = false, nullable = false)
    var createdAt: OffsetDateTime = OffsetDateTime.now()

    @LastModifiedDate
    @Column(nullable = false)
    var modifiedAt: OffsetDateTime = OffsetDateTime.now()

}