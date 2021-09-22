package com.adammuniz.kaia.exercise.ui.exerciselist

import androidx.recyclerview.widget.RecyclerView
import com.adammuniz.kaia.databinding.ListItemExerciseBinding
import com.adammuniz.kaia.exercise.domain.model.Exercise
import com.adammuniz.kaia.util.EventListener

class ExerciseViewHolder(
    private val binding: ListItemExerciseBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(listItem: Exercise, eventListener: EventListener<ExerciseListEvent>) {
        binding.apply {
            exercise = listItem
            executePendingBindings()
        }

        // Setup the View Click
        binding.root.setOnClickListener {
            binding.exercise?.let { exercise ->
                eventListener.onEvent(
                    ExerciseClickedEvent(exercise)
                )
            }
        }

        // Setup the Favorite Click
        binding.ibFavorite.setOnClickListener {
            binding.exercise?.let { exercise ->
                eventListener.onEvent(
                    ExerciseFavoriteClickedEvent(exercise)
                )
            }
        }
    }
}