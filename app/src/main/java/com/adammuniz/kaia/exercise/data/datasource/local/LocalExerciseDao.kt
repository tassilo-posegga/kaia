package com.adammuniz.kaia.exercise.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface LocalExerciseDao {

    // CRUD Operations

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exercise: LocalExerciseModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exercises: List<LocalExerciseModel>)

    @Query("SELECT * FROM exercises WHERE id = :id")
    suspend fun read(id: Long): LocalExerciseModel

    @Query("SELECT * FROM exercises")
    suspend fun read(): List<LocalExerciseModel>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(exercise: LocalExerciseModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(exercise: List<LocalExerciseModel>)

    @Delete
    suspend fun delete(exercise: LocalExerciseModel)

    @Delete
    suspend fun delete(exercise: List<LocalExerciseModel>)
}