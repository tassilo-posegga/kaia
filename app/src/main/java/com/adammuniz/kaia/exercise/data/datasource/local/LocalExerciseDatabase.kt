package com.adammuniz.kaia.exercise.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalExerciseModel::class], version = 1, exportSchema = false)
abstract class LocalExerciseDatabase : RoomDatabase() {
    abstract fun exerciseDao(): LocalExerciseDao

    companion object {

        private const val DATABASE_NAME = "exercises-dp"

        // For Singleton instantiation
        @Volatile
        private var instance: LocalExerciseDatabase? = null

        fun getInstance(context: Context): LocalExerciseDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): LocalExerciseDatabase {
            return Room.databaseBuilder(context, LocalExerciseDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}