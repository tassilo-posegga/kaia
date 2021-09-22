package com.adammuniz.kaia.exercise.data.datasource.local

import com.adammuniz.kaia.exercise.domain.model.Exercise
import javax.inject.Inject

class LocalExerciseDataSource @Inject constructor(
    private val localExerciseDao: LocalExerciseDao,
    private val localExerciseModelMapper: LocalExerciseModelMapper,
    private val reverseLocalExerciseModelMapper: ReverseLocalExerciseModelMapper,
) {

    // Create
    suspend fun insert(exercise: Exercise) {
        localExerciseDao.insert(reverseLocalExerciseModelMapper(exercise))
    }

    suspend fun insert(exercises: List<Exercise>) {
        localExerciseDao.insert(
            exercises.map {
                reverseLocalExerciseModelMapper(it)
            }
        )
    }

    // Read
    suspend fun read(id: Long): Exercise {
        return localExerciseModelMapper(localExerciseDao.read(id))
    }

    suspend fun read(): List<Exercise> {
        return localExerciseDao.read().map {
            localExerciseModelMapper(it)
        }
    }

    // Update
    suspend fun update(exercise: Exercise) {
        localExerciseDao.update(reverseLocalExerciseModelMapper(exercise))
    }

    suspend fun update(exercises: List<Exercise>) {
        localExerciseDao.update(
            exercises.map {
                reverseLocalExerciseModelMapper(it)
            }
        )
    }

    // Delete
    suspend fun delete(exercise: Exercise) {
        localExerciseDao.delete(reverseLocalExerciseModelMapper(exercise))
    }

    suspend fun delete(exercises: List<Exercise>) {
        localExerciseDao.delete(
            exercises.map {
                reverseLocalExerciseModelMapper(it)
            }
        )
    }
}