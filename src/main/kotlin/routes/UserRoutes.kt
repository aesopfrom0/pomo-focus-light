package routes

import domain.user.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import models.User
import org.slf4j.LoggerFactory

fun Route.userRouting() {
    val logger = LoggerFactory.getLogger("Route.userRouting")
    route("/users") {
        post {
            val user = call.receive<User>()
            logger.info(user.toString())
            val userService = UserService()
            val result = userService.createUser()

            call.respond(HttpStatusCode.Created, result)
        }
    }
}