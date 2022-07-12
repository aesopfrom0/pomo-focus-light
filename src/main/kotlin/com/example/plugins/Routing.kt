package com.example.plugins

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import org.jetbrains.exposed.sql.transactions.transaction
import routes.userRouting

fun Application.configureRouting() {
    routing {
        userRouting()
    }

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

        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }

        get("/users/{userId}") {
            val userId = call.parameters["userId"]
            call.respondText("Hello $userId");
        }
    }
}

private operator fun <K, V> MutableMap<K, V>.set(k: K, value: V) {

}
