package tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object UsersTable: Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val lastName: Column<String> = varchar("last_name", 64)
    val firstName: Column<String> = varchar("first_name", 64)
    val email: Column<String> = varchar("email", 64)
    val phoneNumber: Column<String> = varchar("phone_number", 16)
}