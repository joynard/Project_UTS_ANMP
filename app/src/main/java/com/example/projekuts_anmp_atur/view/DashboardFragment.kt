package com.example.projekuts_anmp_atur.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projekuts_anmp_atur.R
import com.example.projekuts_anmp_atur.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private lateinit var binding: FragmentDashboardBinding
    private val viewModel: HabitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentDashboardBinding.bind(view)

        val adapter = HabitListAdapter(emptyList(), viewModel)

        binding.recViewHabit.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.habits.observe(viewLifecycleOwner) { habitList ->
            adapter.updateData(habitList)
        }

        binding.fabAddHabit.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardToCreateHabit()
            findNavController().navigate(action)
        }
    }
}