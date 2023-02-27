package com.andres.noteapp.feature_note.data.data_source

import androidx.room.*
import com.andres.noteapp.feature_note.data.data_source.NoteDatabase.Companion.NOTE_DATABASE
import com.andres.noteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM $NOTE_DATABASE")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM $NOTE_DATABASE WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}