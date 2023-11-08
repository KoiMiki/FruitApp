package com.koimiki.fruitapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fruit(
@PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fruit: String,
    val calories: String
)
