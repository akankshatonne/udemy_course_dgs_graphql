package com.example.graphql_dgs.course.datasource.problemz.entity

import com.netflix.dgs.codegen.generated.types.Book
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.CreationTimestamp
import java.net.URL
import java.time.LocalDateTime

import java.util.*

@Entity
@Table(name = "userz")
@NoArgsConstructor
@AllArgsConstructor
@Data
class Userz {

    @Id @Column lateinit var id: UUID
    @Column lateinit var username: String
    @Column lateinit var email: String
    @Column var hashedPassword: String? = null
    @Column var avatar: URL? = null
    @Column lateinit @CreationTimestamp var creationTimestamp: LocalDateTime
    @Column  var displayName: String? = null
    @Column  var active: Boolean? = null
}