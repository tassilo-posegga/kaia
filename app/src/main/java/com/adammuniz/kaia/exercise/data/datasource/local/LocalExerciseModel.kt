package com.adammuniz.kaia.exercise.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class LocalExerciseModel(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val coverImageUrl: String,
    val name: String,
    val videoUrl: String,
    val favorite: Boolean = false,
)