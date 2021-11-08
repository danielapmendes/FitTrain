package com.pm.fittrain.data.database

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pm.fittrain.data.dao.workoutDao
import com.pm.fittrain.data.entities.workout

@Database(entities = [workout :: class], version = 1, exportSchema = false)
abstract class WorkoutsDatabase : RoomDatabase(){
    abstract fun workoutDao() : workoutDao
    companion object {
        @Volatile
        private var INSTANCE: WorkoutsDatabase? = null

        fun getDatabase(context: Context): WorkoutsDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WorkoutsDatabase::class.java,
                    "Workout_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}