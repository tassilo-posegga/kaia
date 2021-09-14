package com.adammuniz.kaia.exercise.domain.model

import com.adammuniz.kaia.exercise.data.model.Exercise
import com.adammuniz.kaia.util.Mapper
import javax.inject.Inject

class ExerciseDataMapper @Inject constructor() : Mapper<Exercise, ExerciseData> {

    override fun invoke(input: Exercise): ExerciseData {
        return ExerciseData(
            id = input.id,
            coverImageUrl = input.coverImageUrl,
            name = input.name,
            videoUrl = input.videoUrl,
        )
    }
}