package com.adammuniz.kaia.exercise.ui.exerciselist

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.adammuniz.kaia.databinding.ListItemExerciseBinding
import com.adammuniz.kaia.exercise.domain.model.ExerciseData
import com.adammuniz.kaia.exercise.ui.ExercisesViewModel

class ExerciseViewHolder(
    private val binding: ListItemExerciseBinding,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        // Setup the View Click
        binding.setOnClickListener {
            binding.exercise?.let { exercise ->
                navigateToExercise(it, exercise)
            }
        }
    }

    fun bind(listItem: ExerciseData, exercisesViewModel: ExercisesViewModel) {
        binding.apply {
            exercise = listItem
            viewModel = exercisesViewModel
            executePendingBindings()
        }
    }

    private fun navigateToExercise(view: View, exercise: ExerciseData) {
        val direction = ExerciseListFragmentDirections
            .actionExerciseListFragmentToExerciseDetailFragment(
                exerciseId = exercise.id,
            )

        view.findNavController().navigate(direction)
    }
}