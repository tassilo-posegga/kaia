package com.adammuniz.kaia.exercise.ui.exerciselist

import com.adammuniz.kaia.exercise.domain.model.Exercise
import com.adammuniz.kaia.util.Event

sealed interface ExerciseListEvent : Event

data class ExerciseClickedEvent(
    val exercise: Exercise,
) : ExerciseListEvent

data class ExerciseFavoriteClickedEvent(
    val exercise: Exercise,
) : ExerciseListEvent