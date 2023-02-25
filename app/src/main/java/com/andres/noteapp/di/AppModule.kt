package com.andres.noteapp.di

import android.app.Application
import androidx.room.Room
import com.andres.noteapp.feature_note.data.data_source.NoteDao
import com.andres.noteapp.feature_note.data.data_source.NoteDatabase
import com.andres.noteapp.feature_note.data.data_source.NoteDatabase.Companion.NOTE_DATABASE
import com.andres.noteapp.feature_note.data.repository.NoteRepositoryImpl
import com.andres.noteapp.feature_note.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@InstallIn(ViewModelComponent::class)
@Module
object AppModule {

    @ViewModelScoped
    @Provides
    fun provideNoteUseCases(
        noteRepositoryImpl: NoteRepositoryImpl,
    ): NoteUseCases {
        return NoteUseCases(
            insertNoteUseCase = InsertNoteUseCase(noteRepository = noteRepositoryImpl),
            deleteNoteUseCase = DeleteNoteUseCase(noteRepository = noteRepositoryImpl),
            getNoteByIdUseCase = GetNoteByIdUseCase(noteRepository = noteRepositoryImpl),
            getNotesUseCase = GetNotesUseCase(notesRepository = noteRepositoryImpl)
        )
    }

    @Provides
    @Singleton
    fun provideNoteRepository(dao: NoteDao) = NoteRepositoryImpl(dao = dao)

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase =
        Room.databaseBuilder(app, NoteDatabase::class.java, NOTE_DATABASE).build()
}