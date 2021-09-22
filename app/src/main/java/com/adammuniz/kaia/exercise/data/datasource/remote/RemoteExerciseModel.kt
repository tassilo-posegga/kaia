package com.adammuniz.kaia.exercise.data.datasource.remote

import com.google.gson.annotations.SerializedName

data class RemoteExerciseModel(
    @SerializedName("id") val id: Long,
    @SerializedName("cover_image_url") val coverImageUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("video_url") val videoUrl: String,
)