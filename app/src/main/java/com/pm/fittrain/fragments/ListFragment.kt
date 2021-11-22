package com.pm.fittrain.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pm.fittrain.R
import com.pm.fittrain.data.viewmodel.WorkoutViewModel
import com.pm.fittrain.fragments.ListAdapter
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private  lateinit var mWorkoutViewModel: WorkoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mWorkoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)
        mWorkoutViewModel.readAllWorkouts.observe(viewLifecycleOwner, Observer { workouts->
            adapter.setData(workouts)
        })

        view.btnAddWorkoutFromList.setOnClickListener(){
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
        }

    }


