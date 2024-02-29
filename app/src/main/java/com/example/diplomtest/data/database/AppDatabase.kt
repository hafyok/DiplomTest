package com.example.diplomtest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.diplomtest.data.TimerSessionDao
import com.example.diplomtest.data.TimerSessionEntity

/*
@Database(
    entities = [
        TimerSessionEntity::class
    ],
    version = 1
)
abstract class MainDB : RoomDatabase() {
    abstract val dao: TimerSessionDao
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
    abstract fun dao(): TimerSessionDao

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