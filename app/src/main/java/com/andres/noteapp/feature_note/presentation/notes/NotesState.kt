package com.andres.noteapp.feature_note.presentation.notes

import com.andres.noteapp.feature_note.domain.model.Note
import com.andres.noteapp.feature_note.domain.util.NoteOrder
import com.andres.noteapp.feature_note.domain.util.OrderType

data class NotesState(
    val listNotes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(orderType = OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)