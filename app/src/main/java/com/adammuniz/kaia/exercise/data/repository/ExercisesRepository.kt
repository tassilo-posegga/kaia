package com.adammuniz.kaia.exercise.data.repository

import com.adammuniz.kaia.exercise.data.database.ExerciseDao
import com.adammuniz.kaia.exercise.data.datasource.ExerciseDataSource
import com.adammuniz.kaia.exercise.data.model.Exercise
import com.adammuniz.kaia.exercise.domain.model.ExerciseData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class ExercisesRepository @Inject constructor(
    private val exerciseDao: ExerciseDao,
    private val dataSource: ExerciseDataSource,
) {
    suspend fun deleteLocalExercises() {
        exerciseDao.deleteExercises()
    }

    suspend fun getLocalExercise(exerciseId: Long): ExerciseData {
        return exerciseDao.getExercise(exerciseId)
    }

    suspend fun getLocalExercises(): List<ExerciseData> {
        return exerciseDao.getExercises()
    }

    suspend fun getRemoteExercises(): List<Exercise> {
        return dataSource.getExercisesList()
    }

    suspend fun insertExercise(exerciseData: ExerciseData) {
        exerciseDao.insertExercise(exerciseData)
    }

    suspend fun insertExercises(exerciseDataList: List<ExerciseData>) {
        exerciseDao.insertExercises(exerciseDataList)
    }
}