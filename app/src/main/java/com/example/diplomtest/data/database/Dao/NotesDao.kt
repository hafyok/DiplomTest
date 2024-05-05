package com.example.diplomtest.data.database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.diplomtest.data.database.Entity.NoteEntity

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes WHERE notes.id=:id")
    suspend fun getNoteById(id: Int) : NoteEntity?

    @Query("SELECT * FROM Notes ORDER BY dateUpdated DESC")
    fun getNotes() : LiveData<List<NoteEntity>>

    @Delete
    fun deleteNote(note: NoteEntity) : Int

    @Update
    fun updateNote(note: NoteEntity) : Int

    @Insert
    fun insertNote(note: NoteEntity)
}