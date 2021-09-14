package com.adammuniz.kaia.exercise.data.datasource

import com.adammuniz.kaia.exercise.data.model.Exercise
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ExerciseDataSource {

    @GET("/api/jsonBlob/027787de-c76e-11eb-ae0a-39a1b8479ec2")
    suspend fun getExercisesList(): List<Exercise>

    companion object {
        private const val BASE_URL = "https://jsonblob.com"

        fun create(): ExerciseDataSource {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ExerciseDataSource::class.java)
        }
    }
}