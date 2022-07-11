package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.transactions.transaction

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

        get("readiness") {
            val connRes: MutableMap<String, Boolean> = mutableMapOf()

            connRes["conn_pomo_focus_db"] = transaction(com.example.plugins.DBProviders.db) {
                try {
                    !connection.isClosed
                } catch (e: Exception) {
                    System.err.println("while connecting to main db ->")
                    System.err.println(e)
                    false
                }
            }

            if (connRes.all { (_, value) -> value }) {
                call.respond(HttpStatusCode.OK, connRes)
            } else {
                call.respond(HttpStatusCode.InternalServerError, connRes)
            }
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
