package com.example.diplomtest.presentation.NotesScreen.Notes

import com.example.diplomtest.data.database.NoteEntity

object Constants {
    const val NAVIGATION_NOTES_LIST = "notesList"
    const val NAVIGATION_NOTES_CREATE = "notesCreated"
    const val NAVIGATION_NOTE_DETAIL = "noteDetail/{noteId}"
    const val NAVIGATION_NOTE_EDIT= "noteEdit/{noteId}"
    const val NAVIGATION_NOTE_ID_Argument = "noteId"
    const val TABLE_NAME = "Notes"
    const val DATABASE_NAME = "NotesDatabase"

    fun noteDetailNavigation(noteId : Int) = "noteDetail/$noteId"
    fun noteEditNavigation(noteId : Int) = "noteEdit/$noteId"

    fun List<NoteEntity>?.orPlaceHolderList(): List<NoteEntity> {
        fun placeHolderList(): List<NoteEntity> {
            return listOf(NoteEntity(id = 0, title = "No Notes Found", note = "Please create a note.", dateUpdated = ""))
        }
        return if (this != null && this.isNotEmpty()){
            this
        } else placeHolderList()
    }

    val noteDetailPlaceHolder = NoteEntity(note = "Cannot find note details", id = 0, title = "Cannot find note details")
}