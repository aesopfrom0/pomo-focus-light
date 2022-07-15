package domain.user

import com.example.plugins.DBProviders
import models.User
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import tables.UsersTable
import java.sql.Connection

class UserService {
    val logger = LoggerFactory.getLogger("UserService")
    fun createUser(user: User): Int {
        logger.info(user.toString())
        transaction(Connection.TRANSACTION_SERIALIZABLE, 1, DBProviders.db) {
            UsersTable.insert {
                it[lastName] =  user.lastName
                it[firstName] = user.firstName
                it[email] = user.email
                it[phoneNumber] = user.phoneNumber
            }

            val query: Query = UsersTable.select {
                UsersTable.email eq user.email
            }
            query.forEach {
                println(it[UsersTable.id])
            }
        }
        return 0
    }
}