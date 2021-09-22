package com.adammuniz.kaia.exercise.domain.model

import androidx.databinding.ObservableBoolean
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val coverImageUrl: String,
    val name: String,
    val videoUrl: String,
    val favorite: ObservableBoolean,
)