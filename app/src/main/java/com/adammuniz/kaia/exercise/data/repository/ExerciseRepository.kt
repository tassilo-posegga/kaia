package com.adammuniz.kaia.exercise.data.repository

import com.adammuniz.kaia.exercise.data.datasource.local.LocalExerciseDataSource
import com.adammuniz.kaia.exercise.data.datasource.remote.RemoteExerciseDataSource
import com.adammuniz.kaia.exercise.domain.model.Exercise
import javax.inject.Inject

class ExerciseRepository @Inject constructor(
    private val localDataSource: LocalExerciseDataSource,
    private val remoteDataSource: RemoteExerciseDataSource,
) {
    // Create
    suspend fun insert(exercise: Exercise) {
        localDataSource.insert(exercise)
    }

    suspend fun insert(exercises: List<Exercise>) {
        localDataSource.insert(exercises)
    }

    // Read
    suspend fun read(id: Long): Exercise {
        // Check LocalDataSource for data
        return localDataSource.read(id)
    }

    suspend fun read(): List<Exercise> {
        // Check LocalDataSource for data
        val localExerciseList = localDataSource.read()

        // Check if local data is out of date
        return when (localExerciseList.isEmpty()) {
            true -> {
                // Read from the RemoteDataSource
                val remoteExerciseList = remoteDataSource.read()
                // Store the remote data into the LocalDataSource
                insert(remoteExerciseList)
                // Return the local data
                localDataSource.read()
            }
            else -> localExerciseList
        }
    }

    // Update
    suspend fun update(exercise: Exercise): List<Exercise> {
        localDataSource.update(exercise)
        return read()
    }

    suspend fun update(exercises: List<Exercise>) {
        localDataSource.update(exercises)
    }

    // Delete
    suspend fun delete(exercise: Exercise) {
        localDataSource.delete(exercise)
    }

    suspend fun delete(exercises: List<Exercise>) {
        localDataSource.delete(exercises)
    }
}