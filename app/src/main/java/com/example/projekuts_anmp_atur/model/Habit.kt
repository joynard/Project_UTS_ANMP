package com.example.projekuts_anmp_atur.model

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

annotation class SerializedName(val value: String)
