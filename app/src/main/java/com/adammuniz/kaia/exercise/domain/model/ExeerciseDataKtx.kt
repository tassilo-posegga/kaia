package com.adammuniz.kaia.exercise.domain.model

fun ExerciseData.toggleFavorite(): ExerciseData {
    return this.copy(
        favorite = !this.favorite,
    )
}