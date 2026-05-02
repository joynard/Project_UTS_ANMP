package com.example.projekuts_anmp_atur.model

import com.google.gson.annotations.SerializedName

data class Habit(
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("goal")
    val goal: Int?,
    @SerializedName("current_progress")
    var currentProgress: Int? = 0,
    @SerializedName("icon_name")
    val iconName: String?
) {
    val isCompleted: Boolean
        get() = (currentProgress ?: 0) >= (goal ?: 0)
}


object HabitRepository {
    private val habits = mutableListOf<Habit>()

    fun getHabits(): List<Habit> {
        return habits
    }

    fun addHabit(habit: Habit) {
        habits.add(habit)
    }

    fun updateProgress(id: Int, delta: Int) {
        val habit = habits.find { it.id == id }
        habit?.let {
            val newProgress = (it.currentProgress ?: 0) + delta
            // Validasi agar tidak kurang dari 0 dan tidak lebih dari goal
            if (newProgress >= 0 && newProgress <= (it.goal ?: 0)) {
                it.currentProgress = newProgress
            }
        }
    }
}