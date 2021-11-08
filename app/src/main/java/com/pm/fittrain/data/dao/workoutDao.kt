package com.pm.fittrain.data.dao
import androidx.lifecycle.LiveData
import androidx.room.*
import com.pm.fittrain.data.entities.workout

interface workoutDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addWorkout(workout: workout)

    @Update
    fun updateWorkout(workout: workout)

    @Query("SELECT * FROM workouts ORDER BY id DESC")
    fun readAllWorkouts(): LiveData<List<workout>>

    @Delete
    fun deleteWorkout(workout: workout)
}

