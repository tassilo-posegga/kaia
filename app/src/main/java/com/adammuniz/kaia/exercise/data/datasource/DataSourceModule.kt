package com.adammuniz.kaia.exercise.data.datasource

import android.content.Context
import com.adammuniz.kaia.exercise.data.datasource.local.LocalExerciseDao
import com.adammuniz.kaia.exercise.data.datasource.local.LocalExerciseDatabase
import com.adammuniz.kaia.exercise.data.datasource.local.LocalExerciseModelMapper
import com.adammuniz.kaia.exercise.data.datasource.local.ReverseLocalExerciseModelMapper
import com.adammuniz.kaia.exercise.data.datasource.remote.RemoteExerciseApi
import com.adammuniz.kaia.exercise.data.datasource.remote.RemoteExerciseModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteExerciseApi(): RemoteExerciseApi {
        return RemoteExerciseApi.create()
    }

    @Provides
    @Singleton
    fun provideExerciseDatabase(
        @ApplicationContext context: Context,
    ): LocalExerciseDatabase {
        return LocalExerciseDatabase.getInstance(context)
    }

    @Provides
    fun provideLocalExerciseDao(localExerciseDatabase: LocalExerciseDatabase): LocalExerciseDao {
        return localExerciseDatabase.exerciseDao()
    }

    @Provides
    fun provideLocalExerciseModelMapper(): LocalExerciseModelMapper {
        return LocalExerciseModelMapper()
    }

    @Provides
    fun provideRemoteExerciseModelMapper(): RemoteExerciseModelMapper {
        return RemoteExerciseModelMapper()
    }

    @Provides
    fun provideReverseLocalExerciseModelMapper(): ReverseLocalExerciseModelMapper {
        return ReverseLocalExerciseModelMapper()
    }
}