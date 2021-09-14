package com.adammuniz.kaia.exercise.ui.exerciselist

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.adammuniz.kaia.databinding.FragmentExerciseListBinding
import com.adammuniz.kaia.exercise.ui.ExercisesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseListFragment : Fragment() {

    private val viewModel: ExercisesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val binding = FragmentExerciseListBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@ExerciseListFragment
            }

        val adapter = ExerciseListAdapter(viewModel)
        binding.rvExerciseList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: ExerciseListAdapter) {
        viewModel.exercises.observe(viewLifecycleOwner) { exercises ->
            adapter.submitList(exercises)
        }
    }
}