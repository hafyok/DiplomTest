package com.example.diplomtest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.diplomtest.data.database.Dao.NotesDao
import com.example.diplomtest.data.database.Dao.TimerSessionDao
import com.example.diplomtest.data.database.Dao.TodoDao
import com.example.diplomtest.data.database.Entity.NoteEntity
import com.example.diplomtest.data.database.Entity.TimerSessionEntity
import com.example.diplomtest.data.database.Entity.TodoEntity

@Database(
    entities = [TimerSessionEntity::class, NoteEntity::class, TodoEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "app_database"
        private val LOCK = Any()

        //@Volatile
        //private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun timerSessionDao(): TimerSessionDao
    abstract fun notesDao(): NotesDao
    abstract fun todoDao(): TodoDao

}