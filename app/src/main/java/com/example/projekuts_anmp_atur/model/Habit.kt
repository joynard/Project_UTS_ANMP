package com.example.projekuts_anmp_atur.model

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

data class Habit(
    val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("goal") val goal: Int?,
    @SerializedName("current_progress") var currentProgress: Int? = 0,
    @SerializedName("icon_name") val iconName: String?
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

    fun saveToFile(context: Context) {
        try {
            val gson = Gson()
            val jsonString = gson.toJson(habits)
            context.openFileOutput("habits.json", Context.MODE_PRIVATE).use {
                it.write(jsonString.toByteArray())
            }
            Log.d("save_file", "Data berhasil disimpan ke JSON")
        } catch (e: Exception) {
            Log.e("save_error", "Gagal menyimpan: ${e.message}")
        }
    }

    fun loadFromFile(context: Context) {
        try {
            val jsonString = context.openFileInput("habits.json").bufferedReader().use { it.readText() }
            val sType = object : TypeToken<MutableList<Habit>>() {}.type
            val decoded: MutableList<Habit> = Gson().fromJson(jsonString, sType)
            habits.clear()
            habits.addAll(decoded)
        } catch (e: java.io.FileNotFoundException) {
            Log.i("habit_repo", "File habits.json belum ada (pengguna baru).")
        } catch (e: Exception) {
            Log.e("habit_error", "Gagal membaca file: ${e.message}")
        }
    }
}