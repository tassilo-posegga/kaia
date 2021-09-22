package com.adammuniz.kaia.exercise.data.datasource.remote

import com.adammuniz.kaia.exercise.domain.model.Exercise
import javax.inject.Inject

class RemoteExerciseDataSource @Inject constructor(
    private val remoteExerciseApi: RemoteExerciseApi,
    private val remoteExerciseModelMapper: RemoteExerciseModelMapper,
) {
    // CRUD operations

    suspend fun read(): List<Exercise> {
        return remoteExerciseApi.getExercisesList().map {
            remoteExerciseModelMapper(it)
        }
    }
}