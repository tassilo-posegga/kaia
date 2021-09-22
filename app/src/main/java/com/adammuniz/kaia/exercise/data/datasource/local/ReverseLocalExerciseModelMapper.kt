package com.adammuniz.kaia.exercise.data.datasource.local

import com.adammuniz.kaia.exercise.domain.model.Exercise
import com.adammuniz.kaia.util.Mapper

class ReverseLocalExerciseModelMapper : Mapper<Exercise, LocalExerciseModel> {

    override fun invoke(input: Exercise): LocalExerciseModel {
        return LocalExerciseModel(
            id = input.id,
            coverImageUrl = input.coverImageUrl,
            name = input.name,
            videoUrl = input.videoUrl,
            favorite = input.favorite.get()
        )
    }
}