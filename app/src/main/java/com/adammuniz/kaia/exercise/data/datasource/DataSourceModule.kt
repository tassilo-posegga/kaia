package com.adammuniz.kaia.exercise.data.datasource

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideExerciseDataSource(): ExerciseDataSource {
        return ExerciseDataSource.create()
    }
}