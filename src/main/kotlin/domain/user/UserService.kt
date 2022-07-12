package domain.user

import com.example.plugins.DBProviders
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

class UserService {
    fun createUser(user: User): Int {
//        transaction(Connection.TRANSACTION_SERIALIZABLE, 1, DBProviders.db) {
//
//        }
        return 1
    }
}