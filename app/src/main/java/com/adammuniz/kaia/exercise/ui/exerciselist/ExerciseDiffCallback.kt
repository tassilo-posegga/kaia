package com.adammuniz.kaia.exercise.ui.exerciselist

import androidx.recyclerview.widget.DiffUtil
import com.adammuniz.kaia.exercise.domain.model.Exercise

class ExerciseDiffCallback : DiffUtil.ItemCallback<Exercise>() {

    override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem == newItem
    }
}