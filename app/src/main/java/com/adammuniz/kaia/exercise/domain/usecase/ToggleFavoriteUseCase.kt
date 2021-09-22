package com.adammuniz.kaia.exercise.domain.usecase

import com.adammuniz.kaia.exercise.data.repository.ExerciseRepository
import com.adammuniz.kaia.exercise.domain.model.Exercise
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
) {
    suspend fun toggleFavorite(exercise: Exercise): List<Exercise> {
        return exerciseRepository.update(exercise.copy(favorite = !exercise.favorite))
    }
}