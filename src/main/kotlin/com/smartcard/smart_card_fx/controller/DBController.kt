package com.smartcard.smart_card_fx.controller

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DBController {

    companion object {
        private const val URL = "jdbc:sqlite:database.db"

        fun getConnection(): Connection {
            return DriverManager.getConnection(URL)
        }

        fun createTableIfNotExists() {
            val createTableCitizenSQL = """
            CREATE TABLE IF NOT EXISTS Citizen (
                citizenId VARCHAR(12) PRIMARY KEY,
                fullName VARCHAR(255),
                gender VARCHAR(10),
                birthDate VARCHAR(10),
                address VARCHAR(255),
                hometown VARCHAR(255),
                nationality VARCHAR(255),
                ethnicity VARCHAR(255),
                religion VARCHAR(255),
                identification VARCHAR(255),
                avatar TEXT,
                publicKey TEXT
            )
        """.trimIndent()

            try {
                getConnection().use { conn ->
                    conn.createStatement().use { stmt ->
                        stmt.execute(createTableCitizenSQL)
//                        stmt.execute(createTableDrivingLicenseSQL)
//                        stmt.execute(createTableVehicleRegisterSQL)
//                        stmt.execute(createTableHealthInsuranceSQL)
                        println("Tables created (or already exist).")
                    }
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        fun getPublicKeyById(citizenId: String): String? {
            val querySQL = "SELECT publicKey FROM Citizen WHERE citizenId = ?"
            return try {
                getConnection().use { conn ->
                    conn.prepareStatement(querySQL).use { pstmt ->
                        pstmt.setString(1, citizenId)

                        pstmt.executeQuery().use { rs ->
                            if (rs.next()) {
                                rs.getString("publicKey")
                            } else {
                                null
                            }
                        }
                    }
                }
            } catch (e: SQLException) {
                e.printStackTrace()
                null
            }
        }
    }
}