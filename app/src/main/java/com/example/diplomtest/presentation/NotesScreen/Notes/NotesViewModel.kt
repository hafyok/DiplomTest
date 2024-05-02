package com.example.diplomtest.presentation.NotesScreen.Notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplomtest.data.database.NoteEntity
import com.example.diplomtest.data.database.NotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(
    private val db: NotesDao,
) : ViewModel() {

    val notes: LiveData<List<NoteEntity>> = db.getNotes()


    fun deleteNotes(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO){
            db.deleteNote(note)
        }
    }

    fun updateNote(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO){
            db.updateNote(note)
        }
    }

    fun createNote(title: String, note: String, image: String? = null) {
        viewModelScope.launch(Dispatchers.IO){
            db.insertNote(NoteEntity(title = title, note = note, imageUri = image))
        }
    }

    suspend fun getNote(noteId : Int) : NoteEntity? {
        return db.getNoteById(noteId)
    }

}

