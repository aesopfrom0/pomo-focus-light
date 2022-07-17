package domain.task

import com.example.plugins.DBProviders
import models.Task
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import tables.TasksTable
import java.sql.Connection

class TaskService {
    private val logger = LoggerFactory.getLogger("TaskService")
    fun createTask(task: Task): String {
        logger.info(task.toString())
        transaction(Connection.TRANSACTION_SERIALIZABLE, 1, DBProviders.db) {
            val id = TasksTable.insertAndGetId {
                it[userId] = task.userId
                it[title] = task.title
                it[estAttempts] = task.estAttempts
                it[actAttempts] = task.actAttempts
                it[memo] = task.memo
            }
            logger.debug(id.toString())
        }
        return "task가 정상 추가되었습니다."
    }
}