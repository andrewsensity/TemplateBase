package com.andres.noteapp.feature_note.domain.use_case

data class NoteUseCases(
    val insertNoteUseCase: InsertNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val getNoteByIdUseCase: GetNoteByIdUseCase,
    val getNotesUseCase: GetNotesUseCase
)