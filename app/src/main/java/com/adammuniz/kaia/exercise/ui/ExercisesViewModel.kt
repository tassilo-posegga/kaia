package com.adammuniz.kaia.exercise.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammuniz.kaia.exercise.domain.model.ExerciseData
import com.adammuniz.kaia.exercise.domain.model.toggleFavorite
import com.adammuniz.kaia.exercise.domain.usecase.GetExercisesUseCase
import com.adammuniz.kaia.exercise.domain.usecase.InsertExerciseUseCase
import com.adammuniz.kaia.exercise.domain.usecase.InsertExercisesUseCase
import com.adammuniz.kaia.util.replace
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor(
    private val getExercisesUseCase: GetExercisesUseCase,
    private val insertExerciseUseCase: InsertExerciseUseCase,
    private val insertExercisesUseCase: InsertExercisesUseCase,
) : ViewModel() {

    private val _exercisesLiveData: MutableLiveData<List<ExerciseData>> = fetchExercises()
    val exercises: LiveData<List<ExerciseData>> = _exercisesLiveData

    private fun fetchExercises(): MutableLiveData<List<ExerciseData>> {
        val liveData = MutableLiveData<List<ExerciseData>>()
        viewModelScope.launch {
            val exercises = getExercisesUseCase.getExercises()
            insertExercisesUseCase.insertExercises(exercises)
            liveData.postValue(exercises)
        }
        return liveData
    }

    fun getExercise(exerciseId: Long): ExerciseData? {
        return exercises.value?.find { it.id == exerciseId }
    }

    fun toggleFavorite(exercise: ExerciseData) {
        val newExercise = exercise.toggleFavorite()
        insertExercise(newExercise)
        updateLiveData(newExercise)
    }

    private fun insertExercise(exercise: ExerciseData) {
        viewModelScope.launch {
            insertExerciseUseCase.insertExercise(exercise)
        }
    }

    private fun updateLiveData(exercise: ExerciseData) {
        _exercisesLiveData.value = _exercisesLiveData.value?.replace(
            newValue = exercise,
        ) { it.id == exercise.id }
    }
}