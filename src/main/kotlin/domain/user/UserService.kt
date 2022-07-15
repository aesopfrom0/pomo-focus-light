package domain.user

import com.example.plugins.DBProviders
import models.User
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import java.sql.Connection

class UserService {
    val logger = LoggerFactory.getLogger("UserService")
    fun createUser(user: User): Int {
        logger.info(user.toString())
        transaction(Connection.TRANSACTION_SERIALIZABLE, 1, DBProviders.db) {

        }
        return 1
    }
}