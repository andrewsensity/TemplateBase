package com.andres.noteapp.feature_note.presentation.notes

import com.andres.noteapp.feature_note.domain.model.Note
import com.andres.noteapp.feature_note.domain.util.NoteOrder

sealed class NotesEvent{
    class Order(val noteOrder: NoteOrder): NotesEvent()
    class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}