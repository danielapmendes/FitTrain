package com.pm.fittrain.fragments

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pm.fittrain.R
import com.pm.fittrain.data.entities.Workout
import com.pm.fittrain.fragments.ListFragmentDirections
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    private var workoutList = emptyList<Workout>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: ListAdapter.MyViewHolder, position: Int) {
        val currentItem = workoutList[position]
        holder.itemView.lst_workoutId.text = currentItem.id.toString()
        holder.itemView.lst_workoutName.text = currentItem.name
        holder.itemView.lst_workoutTime.text = currentItem.time
        holder.itemView.lst_workoutCalories.text = currentItem.calories
        holder.itemView.lst_workoutMachines.text = currentItem.machines


        if(position%2 == 0){
            holder.itemView.rowLayout.setBackgroundColor(Color.parseColor("#2874A6"))
        }
        else {
            holder.itemView.rowLayout.setBackgroundColor(Color.parseColor("#85C1E9"))
        }

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return workoutList.size
    }

    fun setData(products: List<Workout>){
        this.workoutList = products
        notifyDataSetChanged()
    }
}