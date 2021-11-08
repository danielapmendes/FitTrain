package com.pm.fittrain.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pm.fittrain.data.database.WorkoutsDatabase
import com.pm.fittrain.data.entities.workout
import com.pm.fittrain.data.repository.WorkoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutViewModel(application: Application) : AndroidViewModel(application){
    val readAllWorkouts: LiveData<List<workout>>
    private val repository: WorkoutRepository

    init {
        val workoutDao = WorkoutsDatabase.getDatabase(application).workoutDao()
        repository = WorkoutRepository(workoutDao)
        readAllWorkouts = repository.readAllWorkouts
    }

    fun  addWorkout(workout: workout){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWorkout(workout)
        }
    }

    fun updateWorkout(workout: workout) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWorkout(workout)        }
    }

    fun  deleteWorkout(workout: workout) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWorkout(workout)
        }
    }
}