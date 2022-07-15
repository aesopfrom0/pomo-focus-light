package tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object TasksTable: IntIdTable(name = "tasks") {
    val userId: Column<Int> = integer("user_id")
    val title: Column<String> = varchar("title", 500)
    val estAttempts: Column<Int> = integer("est_attempts")
    val actAttempts: Column<Int> = integer("act_attempts")
    val memo: Column<String> = varchar("memo", 10000)
}