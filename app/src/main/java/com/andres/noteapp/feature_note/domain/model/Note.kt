package com.andres.noteapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andres.noteapp.feature_note.data.data_source.NoteDatabase.Companion.NOTE_DATABASE
import com.andres.noteapp.ui.theme.*

@Entity(tableName = NOTE_DATABASE)
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null,
) {
    companion object {
        val noteColors = listOf(RedOrange, Violet, RedPink, LightGreen, BabyBlue)
    }
}

class InvalidNoteException(message: String) : Exception(message)