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
@Table(name = "recipes")
class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id = 0

    @Column(name = "name", nullable = false, length = 255)
    private val name: String? = null

    @Column(name = "description", columnDefinition = "TEXT")
    private val description: String? = null

    @Column(name = "process", columnDefinition = "TEXT")
    private val process: String? = null
}