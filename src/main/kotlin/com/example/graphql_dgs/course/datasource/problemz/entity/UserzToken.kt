package com.example.graphql_dgs.course.datasource.problemz.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "userz_token")
@NoArgsConstructor
@AllArgsConstructor
@Data
class UserzToken {

    @Id
    @Column
    lateinit var userId: UUID
    @Column
    var authToken: String? = null

    @Column
    @CreationTimestamp
    var creationTimestamp: LocalDateTime? = null
    @Column
    var expiryTimestamp: LocalDateTime? = null

}