package com.example.projekuts_anmp_atur.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projekuts_anmp_atur.databinding.FragmentDashboardBinding
import com.example.projekuts_anmp_atur.viewmodel.HabitViewModel

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: HabitViewModel
    private val habitListAdapter = HabitListAdapter(arrayListOf(), HabitViewModel()) // Placeholder awal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HabitViewModel::class.java)

        val adapter = HabitListAdapter(arrayListOf(), viewModel)
        binding.recViewHabit.layoutManager = LinearLayoutManager(context)
        binding.recViewHabit.adapter = adapter

        viewModel.habits.observe(viewLifecycleOwner, Observer { habitList ->
            adapter.updateHabitList(habitList)

            if(habitList.isEmpty()) {
                binding.recViewHabit.visibility = View.GONE
            } else {
                binding.recViewHabit.visibility = View.VISIBLE
            }
        } )

        binding.fabAddHabit.setOnClickListener {
            val action = DashboardFragmentDirections.actioncreateFragment()
            findNavController().navigate(action)
        }
    }
}