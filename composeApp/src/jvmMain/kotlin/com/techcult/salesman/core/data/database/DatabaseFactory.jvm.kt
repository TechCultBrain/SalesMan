package com.techcult.salesman.core.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<AppDatabase> {

        val dbFile = File(System.getProperty("java.io.tmpdir"), AppDatabase.DB_NAME)

        return Room.databaseBuilder<AppDatabase>(name =dbFile.absolutePath )
    }
}