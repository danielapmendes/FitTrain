package com.pm.fittrain.data.entities

import android.os.Parcelable
import android.widget.EditText
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Workouts")
class Workout(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val time: String,
    val calories: String,
    val machines: String
) : Parcelable

