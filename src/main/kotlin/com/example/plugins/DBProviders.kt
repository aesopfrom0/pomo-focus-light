package com.example.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.http.*
import org.jetbrains.exposed.sql.*

object DBProviders {
    private val cfgDb = HikariConfig().apply {
        jdbcUrl = "jdbc:mysql://localhost:3306/pomo_focus"

        driverClassName = "com.mysql.cj.jdbc.Driver"
        username = "root"
        password = "qqqq"
        maximumPoolSize = 10
        isAutoCommit = false
    }

    val db: Database = Database.connect(HikariDataSource(cfgDb))
}