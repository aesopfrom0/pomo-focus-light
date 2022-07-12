package models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val lastName: String,
    val firstName: String,
    val email: String,
    val phoneNumber: String
)