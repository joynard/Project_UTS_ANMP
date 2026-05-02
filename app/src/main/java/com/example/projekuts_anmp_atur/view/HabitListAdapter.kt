package com.example.projekuts_anmp_atur.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projekuts_anmp_atur.databinding.CardHabitBinding
import com.example.projekuts_anmp_atur.model.Habit
import com.example.projekuts_anmp_atur.viewmodel.HabitViewModel

class HabitListAdapter(
    val habitList: ArrayList<Habit>,
    val viewModel: HabitViewModel
) : RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {


    class HabitViewHolder(var binding: CardHabitBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = CardHabitBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitList[position]

        with(holder.binding) {
            // Mapping data model ke UI (Week 3)
            txtHabitName.text = habit.name
            txtHabitDesc.text = habit.description
            val goal = habit.goal ?: 0
            val current = habit.currentProgress ?: 0

            txtProgressLabel.text = "${current} / ${goal}"

            progressBarHabit.max = goal
            progressBarHabit.progress = current

            if (current >= goal && goal>0) {
                txtStatus.text = "Completed"
                txtStatus.setTextColor(android.graphics.Color.GREEN)
            } else {
                txtStatus.text = "In Progress"
                txtStatus.setTextColor(android.graphics.Color.BLUE)
            }

            btnPlus.setOnClickListener {
                habit.id?.let { id ->
                    viewModel.incrementProgress(id)
                }
            }

            btnMinus.setOnClickListener {
                habit.id?.let { id ->
                    viewModel.incrementProgress(id)
                }
            }
        }
    }

    override fun getItemCount(): Int = habitList.size

    fun updateHabitList(newHabitList: List<Habit>) {
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }
}