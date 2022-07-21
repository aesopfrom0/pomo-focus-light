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
    val taskService = TaskService()
    route("/tasks") {
        post {
            val task = call.receive<Task>()
            logger.info(task.toString())
            val result = taskService.createTask(task)

            call.respond(HttpStatusCode.Created, result)
        }

        put("/{id}") {
            val task = call.receive<Task>()
            val taskId = call.parameters["id"]!!.toInt()
            logger.info(taskId.toString(), task.toString())
            val result = taskService.updateTask(taskId, task)

            call.respond(HttpStatusCode.OK, result)
        }
    }
}