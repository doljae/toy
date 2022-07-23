package com.example.kopring.domain.board.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
class Board(
    @Column(name = "title", nullable = false)
    var title: String,
    @Column(name = "author", nullable = false)
    var author: String,
    @Column(name = "content", length = 2000)
    var content: String?
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long = 0

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    var createdAt: OffsetDateTime = OffsetDateTime.now()

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    var updatedAt: OffsetDateTime = OffsetDateTime.now()

    companion object {
        fun toDao(board: Board) = BoardDao(
            board.title,
            board.author,
            board.content,
            board.id,
            board.createdAt,
            board.updatedAt
        )
    }
}

data class BoardDao(
    val title: String,
    val author: String,
    val content: String?,
    var id: Long,
    val createdDate: OffsetDateTime,
    val updatedAt: OffsetDateTime
)