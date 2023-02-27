package com.andres.noteapp.di

import android.content.Context
import androidx.room.Room
import com.andres.noteapp.feature_note.data.data_source.NoteDao
import com.andres.noteapp.feature_note.data.data_source.NoteDatabase
import com.andres.noteapp.feature_note.data.data_source.NoteDatabase.Companion.NOTE_DATABASE
import com.andres.noteapp.feature_note.data.repository.NoteRepositoryImpl
import com.andres.noteapp.feature_note.domain.repository.NoteRepository
import com.andres.noteapp.feature_note.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase): NoteRepository =
        NoteRepositoryImpl(dao = noteDatabase.noteDao)

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(context, NoteDatabase::class.java, NOTE_DATABASE)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
}