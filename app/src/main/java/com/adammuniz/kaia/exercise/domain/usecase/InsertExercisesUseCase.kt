package com.adammuniz.kaia.exercise.domain.usecase

import com.adammuniz.kaia.exercise.data.repository.ExercisesRepository
import com.adammuniz.kaia.exercise.domain.model.ExerciseData
import javax.inject.Inject

class InsertExercisesUseCase @Inject constructor(
    private val exercisesRepository: ExercisesRepository,
) {
    suspend fun insertExercises(exerciseDataList: List<ExerciseData>) {
        exercisesRepository.insertExercises(exerciseDataList)
    }
}