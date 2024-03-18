package com.example.culinarycrafter.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val login: String,
    val password: String
)
