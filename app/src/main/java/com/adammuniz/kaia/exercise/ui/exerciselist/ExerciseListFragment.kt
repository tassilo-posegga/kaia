package com.adammuniz.kaia.exercise.ui.exerciselist

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.adammuniz.kaia.databinding.FragmentExerciseListBinding
import com.adammuniz.kaia.util.EventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseListFragment : Fragment(), EventListener<ExerciseListEvent> {

    private val exerciseListViewModel: ExerciseListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Force Portrait
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // Inflate
        val binding = FragmentExerciseListBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@ExerciseListFragment
            }

        // Setup the Adapter
        val adapter = ExerciseListAdapter(this)
        binding.rvExerciseList.adapter = adapter
        subscribeUi(binding, adapter)

        return binding.root
    }

    private fun subscribeUi(binding: FragmentExerciseListBinding, adapter: ExerciseListAdapter) {
        binding.lifecycleOwner?.let {
            exerciseListViewModel.exercises.observe(it) { exercises ->
                adapter.submitList(exercises)
            }
        }
    }

    override fun onEvent(event: ExerciseListEvent) {
        when (event) {
            is ExerciseClickedEvent -> onExerciseClickedEvent(event)
            is ExerciseFavoriteClickedEvent -> onExerciseFavoriteClickedEvent(event)
        }
    }

    private fun onExerciseClickedEvent(event: ExerciseClickedEvent) {
        exerciseListViewModel.onExerciseClickedEvent(event)

        val direction = ExerciseListFragmentDirections
            .actionExerciseListFragmentToExerciseDetailFragment(
                exerciseId = event.exercise.id,
            )

        findNavController().navigate(direction)
    }

    private fun onExerciseFavoriteClickedEvent(event: ExerciseFavoriteClickedEvent) {
        exerciseListViewModel.onExerciseFavoriteClickedEvent(event)
    }
}