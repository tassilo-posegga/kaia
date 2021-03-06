package com.adammuniz.kaia.exercise.ui.exerciselist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.adammuniz.kaia.databinding.ListItemExerciseBinding
import com.adammuniz.kaia.exercise.domain.model.Exercise
import com.adammuniz.kaia.util.EventListener

class ExerciseListAdapter(
    private val eventListener: EventListener<ExerciseListEvent>,
) : ListAdapter<Exercise, ExerciseViewHolder>(ExerciseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(
            ListItemExerciseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = getItem(position)
        holder.bind(exercise, eventListener)
    }
}