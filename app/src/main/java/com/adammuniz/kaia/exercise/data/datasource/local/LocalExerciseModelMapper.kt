package com.adammuniz.kaia.exercise.data.datasource.local

import androidx.databinding.ObservableBoolean
import com.adammuniz.kaia.exercise.domain.model.Exercise
import com.adammuniz.kaia.util.Mapper

class LocalExerciseModelMapper : Mapper<LocalExerciseModel, Exercise> {

    override fun invoke(input: LocalExerciseModel): Exercise {
        return Exercise(
            id = input.id,
            coverImageUrl = input.coverImageUrl,
            name = input.name,
            videoUrl = input.videoUrl,
            favorite = ObservableBoolean(input.favorite),
        )
    }
}