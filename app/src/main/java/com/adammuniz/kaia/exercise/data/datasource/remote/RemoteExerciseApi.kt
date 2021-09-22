package com.adammuniz.kaia.exercise.data.datasource.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RemoteExerciseApi {

    @GET("/api/jsonBlob/027787de-c76e-11eb-ae0a-39a1b8479ec2")
    suspend fun getExercisesList(): List<RemoteExerciseModel>

    companion object {
        private const val BASE_URL = "https://jsonblob.com"

        fun create(): RemoteExerciseApi {
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
                .create(RemoteExerciseApi::class.java)
        }
    }
}