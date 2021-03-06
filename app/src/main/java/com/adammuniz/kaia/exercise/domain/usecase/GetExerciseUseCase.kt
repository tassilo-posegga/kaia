package com.adammuniz.kaia.exercise.domain.usecase

import com.adammuniz.kaia.exercise.data.repository.ExerciseRepository
import com.adammuniz.kaia.exercise.domain.model.Exercise
import javax.inject.Inject

class GetExerciseUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
) {
    suspend fun getExercise(exerciseId: Long): Exercise {
        return exerciseRepository.read(exerciseId)
    }
}