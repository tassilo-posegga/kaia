package com.adammuniz.kaia.exercise.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adammuniz.kaia.exercise.domain.model.ExerciseData
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercises: List<ExerciseData>)

    @Query("SELECT * FROM exercises ORDER BY id")
    suspend fun getExercises(): List<ExerciseData>

    @Query("DELETE FROM exercises")
    suspend fun deleteExercises(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: ExerciseData)

    @Query("SELECT * FROM exercises WHERE id = :id")
    suspend fun getExercise(id: Long): ExerciseData
}