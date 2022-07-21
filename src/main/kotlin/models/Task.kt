package models

import kotlinx.serialization.Serializable

@Serializable
data class Task(
        val userId: Int,
        val title: String?,
        val estAttempts: Int?,
        val actAttempts: Int?,
        val memo: String?,
)