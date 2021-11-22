package com.pm.fittrain.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pm.fittrain.R
import com.pm.fittrain.data.entities.Workout
import com.pm.fittrain.data.viewmodel.WorkoutViewModel
import com.pm.fittrain.utils.Utils.Companion.hideKeyboard
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment(){

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mWorkoutViewModel: WorkoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mWorkoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)

        view.updateWorkoutName.setText(args.currentWorkout.name)
        view.updateWorkoutTime.setText(args.currentWorkout.time)
        view.updateWorkoutCalories.setText(args.currentWorkout.calories)
        view.updateWorkoutMachine.setText(args.currentWorkout.machines)

        setHasOptionsMenu(true)

        return view;
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_update_delete_workout, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        hideKeyboard()

        if (item.itemId == R.id.menu_update) {
            updateWorkout()
        }

        if (item.itemId == R.id.menu_delete) {
            deleteWorkout()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun updateWorkout() {
        if (!isValid()) {
            return Toast.makeText(
                requireContext(),
                getString(R.string.empty_workout_name),
                Toast.LENGTH_LONG
            ).show()
        }
        val workout = Workout(args.currentWorkout.id, updateWorkoutName.text.toString()
           , updateWorkoutTime.text.toString(),updateWorkoutCalories.text.toString(),
             updateWorkoutMachine.text.toString()
                )

        mWorkoutViewModel.updateWorkout(workout)

        Toast.makeText(
            requireContext(),
            getString(R.string.workout_successfully_updated),
            Toast.LENGTH_LONG
        ).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    private fun deleteWorkout() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            mWorkoutViewModel.deleteWorkout(args.currentWorkout)
            Toast.makeText(
                requireContext(),
                getString(R.string.workout_successfully_deleted),
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton(getString(R.string.no)) { _, _ -> }
        builder.setTitle(getString(R.string.delete))
        builder.setMessage(getString(R.string.question_delete))
        builder.create().show()
    }

    private fun isValid(): Boolean {
        return !TextUtils.isEmpty(updateWorkoutName.text.toString())
    }
}