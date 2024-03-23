package com.example.diplomtest.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.diplomtest.domain.NoteData

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes WHERE notes.id=:id")
    suspend fun getNoteById(id: Int) : NoteData?

    @Query("SELECT * FROM Notes ORDER BY dateUpdated DESC")
    fun getNotes() : LiveData<List<NoteData>>

    @Delete
    fun deleteNote(note: NoteData) : Int

    @Update
    fun updateNote(note: NoteData) : Int

    @Insert
    fun insertNote(note: NoteData)
}