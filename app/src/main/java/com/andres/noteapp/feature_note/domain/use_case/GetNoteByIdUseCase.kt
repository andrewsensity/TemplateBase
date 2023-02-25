package com.andres.noteapp.feature_note.domain.use_case

import com.andres.noteapp.feature_note.domain.repository.NoteRepository

class GetNoteByIdUseCase(
    private val noteRepository: NoteRepository,
) {

    suspend operator fun invoke(id: Int) = noteRepository.getNoteById(id = id)
}