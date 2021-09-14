package com.adammuniz.kaia.exercise.data.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideExerciseDatabase(
        @ApplicationContext context: Context,
    ): ExerciseDatabase {
        return ExerciseDatabase.getInstance(context)
    }

    @Provides
    fun provideExerciseDao(exerciseDatabase: ExerciseDatabase): ExerciseDao {
        return exerciseDatabase.exerciseDao()
    }
}