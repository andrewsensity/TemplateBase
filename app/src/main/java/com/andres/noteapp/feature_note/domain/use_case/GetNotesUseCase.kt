package com.andres.noteapp.feature_note.domain.use_case

import com.andres.noteapp.feature_note.domain.model.Note
import com.andres.noteapp.feature_note.domain.repository.NoteRepository
import com.andres.noteapp.feature_note.domain.util.NoteOrder
import com.andres.noteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val notesRepository: NoteRepository,
) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    ): Flow<List<Note>> {
        return notesRepository.getNotes().map { notes ->
            when (noteOrder.orderType) {
                OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Color -> notes.sortedBy { note -> note.color }
                        is NoteOrder.Date -> notes.sortedBy { note -> note.timeStamp }
                        is NoteOrder.Title -> notes.sortedBy { note -> note.title.lowercase() }
                    }
                }
                OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.Color -> notes.sortedByDescending { note -> note.color }
                        is NoteOrder.Date -> notes.sortedByDescending { note -> note.timeStamp }
                        is NoteOrder.Title -> notes.sortedByDescending { note -> note.title.lowercase() }
                    }
                }
            }
        }
    }
}