package com.adammuniz.kaia.exercise.data.model

import com.google.gson.annotations.SerializedName

data class Exercise(
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("cover_image_url") val coverImageUrl: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("video_url") val videoUrl: String,
)