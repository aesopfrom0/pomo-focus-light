package tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object UsersTable: IntIdTable(name = "users") {
    val lastName: Column<String> = varchar("last_name", 64)
    val firstName: Column<String> = varchar("first_name", 64)
    val email: Column<String> = varchar("email", 64)
    val phoneNumber: Column<String> = varchar("phone_number", 16)
}