package com.example.projekuts_anmp_atur.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.projekuts_anmp_atur.databinding.FragmentCreateBinding
import com.example.projekuts_anmp_atur.viewmodel.HabitViewModel

class CreateFragment : Fragment() {
    private lateinit var binding: FragmentCreateBinding
    private lateinit var viewModel: HabitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HabitViewModel::class.java)

        val icons = arrayOf("Water", "Run", "Pray", "Sleep")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, icons)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIcon.adapter = adapter

        binding.btnCreateHabit.setOnClickListener {
            val name = binding.txtHabitNameInput.text.toString()
            val desc = binding.txtDescInput.text.toString()
            val goalStr = binding.txtGoalInput.text.toString()
            val icon = binding.spinnerIcon.selectedItem.toString()

            if (name.isEmpty() || desc.isEmpty() || goalStr.isEmpty()) {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Memanggil fungsi add di ViewModel (Sesuai konsep Business Logic di VM)
                viewModel.addHabit(name, desc, goalStr.toInt(), icon)

                Toast.makeText(context, "Habit Created!", Toast.LENGTH_SHORT).show()

                findNavController().popBackStack()
            }
        }
    }
}