package com.adammuniz.kaia.exercise.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import com.adammuniz.kaia.R
import com.adammuniz.kaia.databinding.ActivityExercisesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExercisesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityExercisesBinding>(this, R.layout.activity_exercises)
    }
}