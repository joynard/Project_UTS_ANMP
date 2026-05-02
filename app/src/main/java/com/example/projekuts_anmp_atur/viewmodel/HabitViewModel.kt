package com.example.projekuts_anmp_atur.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projekuts_anmp_atur.model.Habit
import com.example.projekuts_anmp_atur.model.HabitRepository

class HabitViewModel(application: Application) : AndroidViewModel(application) {

    val habits = MutableLiveData<List<Habit>>()

    init {
        HabitRepository.loadFromFile(getApplication())
        refresh()
    }

    fun refresh() {
        habits.value = HabitRepository.getHabits()
    }

    fun addHabit(name: String, desc: String, goal: Int, icon: String) {
        val newHabit = Habit(
            id = (HabitRepository.getHabits().size + 1),
            name = name,
            description = desc,
            goal = goal,
            currentProgress = 0,
            iconName = icon
        )
        HabitRepository.addHabit(newHabit)
        HabitRepository.saveToFile(getApplication())
        refresh()
    }

    fun incrementProgress(habitId: Int) {
        HabitRepository.updateProgress(habitId, 1)
        HabitRepository.saveToFile(getApplication())
        refresh()
    }

    fun decrementProgress(habitId: Int) {
        HabitRepository.updateProgress(habitId, -1)
        HabitRepository.saveToFile(getApplication())
        refresh()
    }
}
