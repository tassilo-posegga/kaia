package com.adammuniz.kaia.exercise.ui.exerciselist

import androidx.recyclerview.widget.DiffUtil
import com.adammuniz.kaia.exercise.domain.model.ExerciseData

class ExerciseDiffCallback : DiffUtil.ItemCallback<ExerciseData>() {

    override fun areItemsTheSame(oldItem: ExerciseData, newItem: ExerciseData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ExerciseData, newItem: ExerciseData): Boolean {
        return oldItem == newItem
    }
}