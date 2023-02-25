package com.andres.noteapp.feature_note.domain.use_case

import com.andres.noteapp.feature_note.domain.model.InvalidNoteException
import com.andres.noteapp.feature_note.domain.model.Note
import com.andres.noteapp.feature_note.domain.repository.NoteRepository

class InsertNoteUseCase(
    private val noteRepository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty")
        }
        noteRepository.insertNote(note = note)
    }
}