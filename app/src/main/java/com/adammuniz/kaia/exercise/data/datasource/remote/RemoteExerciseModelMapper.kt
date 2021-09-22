package com.adammuniz.kaia.exercise.data.datasource.remote

import com.adammuniz.kaia.exercise.domain.model.Exercise
import com.adammuniz.kaia.util.Mapper

class RemoteExerciseModelMapper : Mapper<RemoteExerciseModel, Exercise> {

    override fun invoke(input: RemoteExerciseModel): Exercise {
        return Exercise(
            id = input.id,
            coverImageUrl = input.coverImageUrl,
            name = input.name,
            videoUrl = input.videoUrl,
            favorite = false,
        )
    }
}