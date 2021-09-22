package com.adammuniz.kaia.exercise.ui.exercisedetail

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.adammuniz.kaia.databinding.FragmentExerciseDetailBinding
import com.adammuniz.kaia.util.EventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseDetailFragment : Fragment(), EventListener<ExerciseDetailEvent> {

    private val exerciseDetailViewModel: ExerciseDetailViewModel by viewModels()

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
                viewModel = exerciseDetailViewModel
                lifecycleOwner = this@ExerciseDetailFragment
            }

        subscribeUi(binding)

        // Setup the Cancel button
        binding.bCancel.setOnClickListener {
            onEvent(CancelClickedEvent)
        }

        // Setup the Favorite button
        binding.ibFavorite.setOnClickListener {
            onEvent(FavoriteClickedEvent)
        }

        return binding.root
    }

    private fun subscribeUi(binding: FragmentExerciseDetailBinding) {
//        exerciseDetailViewModel.observe(viewLifecycleOwner) { exercise ->
//            binding.viewModel
//        }
    }

    override fun onEvent(event: ExerciseDetailEvent) {
        when (event) {
            is CancelClickedEvent -> onCancelClickedEvent()
            is FavoriteClickedEvent -> onFavoriteClickedEvent()
        }
    }

    private fun onCancelClickedEvent() {
        findNavController().navigateUp()
    }

    private fun onFavoriteClickedEvent() {
        exerciseDetailViewModel.toggleFavorite()
    }
}