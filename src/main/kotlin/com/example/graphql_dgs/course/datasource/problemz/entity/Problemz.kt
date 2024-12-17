package com.example.graphql_dgs.course.datasource.problemz.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "problemz")
@AllArgsConstructor
@NoArgsConstructor
@Data
class Problemz{
    @Id
    @Column
    lateinit var id: UUID

    @Column
    lateinit var  title: String

    @Column
    lateinit var content: String

    @Column
    lateinit var tags: String

    @OneToMany(mappedBy = "problemz")
    val solutions: List<Solutionz>? = null

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    lateinit var createdBy: Userz

    @Column
    @CreationTimestamp
    lateinit var creationTimestamp: LocalDateTime
}
