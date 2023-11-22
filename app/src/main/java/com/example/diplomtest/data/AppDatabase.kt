package com.example.diplomtest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
@Database(
    entities = [
        TimerSessionEntity::class
    ],
    version = 1
)
abstract class MainDB : RoomDatabase() {
    abstract val dao: Dao
    companion object{
        fun createDataBase(context: Context): MainDB{
            return Room.databaseBuilder(
                context,
                MainDB::class.java,
                "test.db"
            ).build()
        }
    }
}*/
@Database(entities = [TimerSessionEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}