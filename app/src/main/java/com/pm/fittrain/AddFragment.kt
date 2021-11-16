package com.pm.fittrain

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pm.fittrain.data.entities.workout
import com.pm.fittrain.data.viewmodel.WorkoutViewModel
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {

    private lateinit var mWorkoutViewModel: WorkoutViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        setHasOptionsMenu(true)

        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_workout, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //hideKeyboard()

        if (item.itemId == R.id.menu_add) {
            addWorkout()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun addWorkout() {
        if (!isValid()) {
            return Toast.makeText(
                requireContext(),
                getString(R.string.empty_workout_name),
                Toast.LENGTH_LONG
            ).show()
        }

        val workout = workout(0, workoutName.text.toString())

        mWorkoutViewModel.addWorkout(workout)

        Toast.makeText(
            requireContext(),
            getString(R.string.workout_successfully_added),
            Toast.LENGTH_LONG
        ).show()

        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }

    private fun isValid() : Boolean {
        return !TextUtils.isEmpty(workoutName.text.toString())
    }



}