package com.pm.fittrain.data.repository

import androidx.lifecycle.LiveData
import com.pm.fittrain.data.dao.workoutDao
import com.pm.fittrain.data.entities.workout

class WorkoutRepository(private  val workoutDao: workoutDao) {
    val readAllWorkouts : LiveData<List<workout>> = workoutDao.readAllWorkouts()

    suspend fun addWorkout(workout: workout){
        workoutDao.addWorkout(workout)
    }

    suspend fun updateWorkout(workout: workout) {
        workoutDao.updateWorkout(workout)
    }

    suspend fun deleteWorkout(workout: workout) {
        workoutDao.deleteWorkout(workout)
    }
}