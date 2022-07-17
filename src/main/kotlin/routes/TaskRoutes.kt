package routes

import domain.task.TaskService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import models.Task
import org.slf4j.LoggerFactory

fun Route.taskRouting() {
    val logger = LoggerFactory.getLogger("Route.taskRouting")
    route("/tasks") {
        post {
            val task = call.receive<Task>()
            logger.info(task.toString())
            val taskService = TaskService()
            val result = taskService.createTask(task)

            call.respond(HttpStatusCode.Created, result)
        }
    }
}