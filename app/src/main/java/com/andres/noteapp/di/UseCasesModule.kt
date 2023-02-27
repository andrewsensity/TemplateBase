package com.andres.noteapp.di

import com.andres.noteapp.feature_note.data.repository.NoteRepositoryImpl
import com.andres.noteapp.feature_note.domain.repository.NoteRepository
import com.andres.noteapp.feature_note.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideNoteUseCases(
        noteRepository: NoteRepository,
    ): NoteUseCases {
        return NoteUseCases(
            insertNoteUseCase = InsertNoteUseCase(noteRepository = noteRepository),
            deleteNoteUseCase = DeleteNoteUseCase(noteRepository = noteRepository),
            getNoteByIdUseCase = GetNoteByIdUseCase(noteRepository = noteRepository),
            getNotesUseCase = GetNotesUseCase(notesRepository = noteRepository)
        )
    }
}