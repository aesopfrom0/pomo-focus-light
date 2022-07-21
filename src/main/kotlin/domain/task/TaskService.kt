package domain.task

import com.example.plugins.DBProviders
import models.Task
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
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
                it[title] = task.title ?: ""
                it[estAttempts] = task.estAttempts ?: 1
                it[actAttempts] = task.actAttempts ?: 0
                it[memo] = task.memo ?: ""
            }
            logger.debug(id.toString())
        }
        return "task가 정상 추가되었습니다."
    }

    fun updateTask(id: Int, task: Task): String {
        logger.info("[updateTask] 시작")
        transaction(Connection.TRANSACTION_SERIALIZABLE, 1 , DBProviders.db) {
            val res = TasksTable.update({ TasksTable.id eq id and (TasksTable.userId eq task.userId)}) {
                if(task.title != null) it[title] = task.title
                if(task.estAttempts != null) it[estAttempts] = task.estAttempts
                if(task.actAttempts != null) it[actAttempts] = task.actAttempts
                if(task.memo != null) it[memo] = task.memo
            }
            logger.debug(res.toString())
        }
        return "task가 정상 수정되었습니다."
    }
}
