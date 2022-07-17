package domain.user

import com.example.plugins.DBProviders
import models.User
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import tables.UsersTable
import java.sql.Connection

class UserService {
    private val logger = LoggerFactory.getLogger("UserService")
    fun createUser(user: User): String {
        logger.info(user.toString())
        transaction(Connection.TRANSACTION_SERIALIZABLE, 1, DBProviders.db) {
            val id = UsersTable.insertAndGetId {
                it[lastName] =  user.lastName
                it[firstName] = user.firstName
                it[email] = user.email
                it[phoneNumber] = user.phoneNumber
            }
            logger.debug(id.toString())
        }
        return "유저가 정상 추가되었습니다."
    }
}