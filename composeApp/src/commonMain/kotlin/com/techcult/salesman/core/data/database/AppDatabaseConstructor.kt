@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.techcult.salesman.core.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("KotlinNoActualForExpect")

expect object AppDatabaseConstructor: RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}