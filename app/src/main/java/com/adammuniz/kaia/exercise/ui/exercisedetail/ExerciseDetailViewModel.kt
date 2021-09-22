package com.adammuniz.kaia.exercise.ui.exercisedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammuniz.kaia.exercise.domain.model.Exercise
import com.adammuniz.kaia.exercise.domain.usecase.GetExerciseUseCase
import com.adammuniz.kaia.exercise.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getExerciseUseCase: GetExerciseUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : ViewModel() {

    private val exerciseId: Long? = savedStateHandle.get<Long>(EXERCISE_ID_SAVED_STATE_KEY)

    private val _exerciseLiveData: MutableLiveData<Exercise> = fetchExercise(exerciseId)
    val exercise: LiveData<Exercise> = _exerciseLiveData

    private fun fetchExercise(exerciseId: Long?): MutableLiveData<Exercise> {
        val liveData = MutableLiveData<Exercise>()

        exerciseId?.let {
            viewModelScope.launch {
                val exercises = getExerciseUseCase.getExercise(exerciseId)
                liveData.postValue(exercises)
            }
        }

        return liveData
    }

    fun toggleFavorite() {
        exercise.value?.let {
            viewModelScope.launch {
                toggleFavoriteUseCase.toggleFavorite(it)
            }
        }
    }

    companion object {
        private const val EXERCISE_ID_SAVED_STATE_KEY = "exerciseId"
    }
}