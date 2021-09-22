package com.adammuniz.kaia.exercise.ui.exerciselist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammuniz.kaia.exercise.domain.model.Exercise
import com.adammuniz.kaia.exercise.domain.usecase.GetExercisesUseCase
import com.adammuniz.kaia.exercise.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val getExercisesUseCase: GetExercisesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : ViewModel() {

    private val _exercisesLiveData: MutableLiveData<List<Exercise>> = fetchExercises()
    val exercises: LiveData<List<Exercise>> = _exercisesLiveData

    private fun fetchExercises(): MutableLiveData<List<Exercise>> {
        val liveData = MutableLiveData<List<Exercise>>()

        viewModelScope.launch {
            val exercises = getExercisesUseCase.getExercises()
            liveData.postValue(exercises)
        }

        return liveData
    }

    fun onExerciseClickedEvent(event: ExerciseClickedEvent) {
        // No Op
    }

    fun onExerciseFavoriteClickedEvent(event: ExerciseFavoriteClickedEvent) {
        viewModelScope.launch {
            toggleFavoriteUseCase.toggleFavorite(event.exercise)
        }
    }
}