package com.andres.noteapp.feature_note.presentation.add_edit_note

import com.andres.noteapp.feature_note.domain.model.Note

sealed class AddEditEvent {
    class AddNote(val id: Int) : AddEditEvent()
    class EditNote(val note: Note) : AddEditEvent()
}