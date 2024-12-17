package com.example.graphql_dgs.course.datasource.problemz.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "solutionz")
@NoArgsConstructor
@AllArgsConstructor
@Data
class Solutionz {

    @Id
    @Column
    lateinit var id: UUID
    @Column
    lateinit var content: String
    @Column
    lateinit var category: String
    @Column
    var voteGoodCount: Int? = null
    @Column
    var voteBadCount: Int? = null

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    lateinit var createdBy: Userz

    @ManyToOne
    @JoinColumn(name = "problemz_id", nullable = false)
    var problemz: Problemz? = null

    @Column
    @CreationTimestamp
    lateinit var creationTimestamp: LocalDateTime

}
