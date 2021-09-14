package com.adammuniz.kaia.exercise.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adammuniz.kaia.exercise.domain.model.ExerciseData

@Database(entities = [ExerciseData::class], version = 1, exportSchema = false)
abstract class ExerciseDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao

    companion object {

        private const val DATABASE_NAME = "exercises-dp"

        // For Singleton instantiation
        @Volatile
        private var instance: ExerciseDatabase? = null

        fun getInstance(context: Context): ExerciseDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ExerciseDatabase {
            return Room.databaseBuilder(context, ExerciseDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}