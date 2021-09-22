package com.adammuniz.kaia.exercise.domain.usecase

import com.adammuniz.kaia.exercise.data.repository.ExerciseRepository
import com.adammuniz.kaia.exercise.domain.model.Exercise
import javax.inject.Inject

class GetExercisesUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
) {
    suspend fun getExercises(): List<Exercise> {
        return exerciseRepository.read()
    }
}