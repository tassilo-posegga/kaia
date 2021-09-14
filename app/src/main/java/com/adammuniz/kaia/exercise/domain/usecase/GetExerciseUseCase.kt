package com.adammuniz.kaia.exercise.domain.usecase

import com.adammuniz.kaia.exercise.data.repository.ExercisesRepository
import com.adammuniz.kaia.exercise.domain.model.ExerciseData
import javax.inject.Inject

class GetExerciseUseCase @Inject constructor(
    private val exercisesRepository: ExercisesRepository,
) {
    suspend fun getExercise(exerciseId: Long): ExerciseData {
        return exercisesRepository.getLocalExercise(exerciseId)
    }
}