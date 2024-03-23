package com.example.diplomtest.domain

import androidx.lifecycle.LiveData

interface NotesRepository {
    suspend fun insertNote(noteData: NoteData)

    suspend fun getNoteById(id: Int) : NoteData?

    suspend fun getNotes() : LiveData<List<NoteData>>

    suspend fun deleteNote(note: NoteData) : Int

    fun updateNote(note: NoteData) : Int
}