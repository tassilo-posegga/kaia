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

    private lateinit var binding: FragmentExerciseListBinding
    private lateinit var adapter: ExerciseListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Force Portrait
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // Inflate
        binding = FragmentExerciseListBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@ExerciseListFragment
            }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Setup the Adapter
        adapter = ExerciseListAdapter(this)
        binding.rvExerciseList.adapter = adapter

        exerciseListViewModel.exercises.observe(viewLifecycleOwner) { exercises ->
            adapter.submitList(exercises)
        }

        exerciseListViewModel.navigateToDetail.observe(viewLifecycleOwner) {
            navigateToDetail(it)
        }
    }

    private fun navigateToDetail(exerciseId: Long) {
        val direction = ExerciseListFragmentDirections
            .actionExerciseListFragmentToExerciseDetailFragment(
                exerciseId = exerciseId,
            )

        findNavController().navigate(direction)
    }

    override fun onEvent(event: ExerciseListEvent) {
        when (event) {
            is ExerciseClickedEvent -> onExerciseClickedEvent(event)
            is ExerciseFavoriteClickedEvent -> onExerciseFavoriteClickedEvent(event)
        }
    }

    private fun onExerciseClickedEvent(event: ExerciseClickedEvent) {
        exerciseListViewModel.onExerciseClickedEvent(event)
    }

    private fun onExerciseFavoriteClickedEvent(event: ExerciseFavoriteClickedEvent) {
        exerciseListViewModel.onExerciseFavoriteClickedEvent(event)
    }
}