package com.adammuniz.kaia.exercise.ui.exercisedetail

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.adammuniz.kaia.databinding.FragmentExerciseDetailBinding
import com.adammuniz.kaia.exercise.ui.ExercisesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseDetailFragment : Fragment() {

    private val viewModel: ExercisesViewModel by activityViewModels()
    private val args: ExerciseDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Force landscape
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        // Inflate
        val binding = FragmentExerciseDetailBinding.inflate(inflater, container, false)
            .apply {
                exercise = viewModel.getExercise(args.exerciseId)
                lifecycleOwner = this@ExerciseDetailFragment
            }

        subscribeUi(binding)

        // Setup the Cancel button
        binding.bCancel.setOnClickListener {
            it.findNavController().navigateUp()
        }

        // Setup the Favorite button
        binding.ibFavorite.setOnClickListener {
            binding.exercise?.let { exercise ->
                viewModel.toggleFavorite(exercise)
            }
        }

        return binding.root
    }

    private fun subscribeUi(binding: FragmentExerciseDetailBinding) {
        viewModel.exercises.observe(viewLifecycleOwner) { exercises ->
            exercises.find { it.id == args.exerciseId }?.let {
                binding.exercise = it
            }
        }
    }
}