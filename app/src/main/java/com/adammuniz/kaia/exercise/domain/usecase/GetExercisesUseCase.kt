package com.adammuniz.kaia.exercise.domain.usecase

import com.adammuniz.kaia.exercise.data.repository.ExercisesRepository
import com.adammuniz.kaia.exercise.domain.model.ExerciseData
import com.adammuniz.kaia.exercise.domain.model.ExerciseDataMapper
import javax.inject.Inject

class GetExercisesUseCase @Inject constructor(
    private val exerciseDataMapper: ExerciseDataMapper,
    private val exercisesRepository: ExercisesRepository,
) {
    suspend fun getExercises(): List<ExerciseData> {
        val localExerciseList = getLocalExercises()
        return when (localExerciseList.isEmpty()) {
            true -> getRemoteExercises()
            else -> localExerciseList
        }
    }

    private suspend fun getLocalExercises(): List<ExerciseData> {
        return exercisesRepository.getLocalExercises().map { it }
    }

    private suspend fun getRemoteExercises(): List<ExerciseData> {
        exercisesRepository.deleteLocalExercises()
        return exercisesRepository.getRemoteExercises().map {
            exerciseDataMapper(it)
        }
    }
}