package com.example.culinarycrafter.models.entities

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_entity")
class UserEntity {
    @Id
    @Column(name = "login", nullable = false)
    private val login: String? = null

    @Column(name = "password", nullable = false)
    private val password: String? = null
}

