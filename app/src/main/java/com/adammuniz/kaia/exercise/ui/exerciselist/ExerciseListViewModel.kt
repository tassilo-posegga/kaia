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

    private val _exercisesLiveData = MutableLiveData<List<Exercise>>()
    val exercises: LiveData<List<Exercise>> = _exercisesLiveData

    private val _navigateToDetail = MutableLiveData<Long>()
    val navigateToDetail: LiveData<Long> = _navigateToDetail

    init {
        viewModelScope.launch {
            val exercises = getExercisesUseCase.getExercises()
            _exercisesLiveData.postValue(exercises)
        }
    }

    fun onExerciseClickedEvent(event: ExerciseClickedEvent) {
        _navigateToDetail.postValue(event.exercise.id)
    }

    fun onExerciseFavoriteClickedEvent(event: ExerciseFavoriteClickedEvent) {
        viewModelScope.launch {
            _exercisesLiveData.postValue(toggleFavoriteUseCase.toggleFavorite(event.exercise))
        }
    }
}