package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String
)

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello AESOP!")
        }
        post("/users") {
            val user = call.receive<User>()
            println(user)

            val (firstName) = user

            println("Have a nice day, $firstName!")

            call.respondText("Have a nice day, $firstName!")
        }

        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
}
