package com.pm.fittrain.data.repository

import androidx.lifecycle.LiveData
import com.pm.fittrain.data.dao.workoutDao
import com.pm.fittrain.data.entities.Workout

class WorkoutRepository(private  val workoutDao: workoutDao) {
    val readAllWorkouts : LiveData<List<Workout>> = workoutDao.readAllWorkouts()

    suspend fun addWorkout(workout: Workout){
        workoutDao.addWorkout(workout)
    }

    suspend fun updateWorkout(workout: Workout) {
        workoutDao.updateWorkout(workout)
    }

    suspend fun deleteWorkout(workout: Workout) {
        workoutDao.deleteWorkout(workout)
    }
}