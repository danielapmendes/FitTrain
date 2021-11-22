package com.pm.fittrain.data.dao
import androidx.lifecycle.LiveData
import androidx.room.*
import com.pm.fittrain.data.entities.Workout

@Dao
interface workoutDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addWorkout(workout: Workout)

    @Update
    fun updateWorkout(workout: Workout)

    @Query("SELECT * FROM workouts ORDER BY id DESC")
    fun readAllWorkouts(): LiveData<List<Workout>>

    @Delete
    fun deleteWorkout(workout: Workout)
}

