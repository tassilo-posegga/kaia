package com.adammuniz.kaia.exercise.ui.exercisedetail

import com.adammuniz.kaia.util.Event

sealed interface ExerciseDetailEvent : Event

object CancelClickedEvent : ExerciseDetailEvent

object FavoriteClickedEvent : ExerciseDetailEvent