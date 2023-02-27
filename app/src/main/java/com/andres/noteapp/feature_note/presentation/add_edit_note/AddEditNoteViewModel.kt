package com.andres.noteapp.feature_note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andres.noteapp.feature_note.domain.model.Note
import com.andres.noteapp.feature_note.domain.use_case.NoteUseCases
import com.andres.noteapp.feature_note.domain.util.NoteOrder
import com.andres.noteapp.feature_note.domain.util.OrderType
import com.andres.noteapp.feature_note.presentation.notes.NotesEvent
import com.andres.noteapp.feature_note.presentation.notes.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    private var getNotesJob: Job? = null

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.AddNote -> {
                getNoteById(event.id)
            }
            is AddEditEvent.EditNote -> {

            }
        }
    }

    private fun getNoteById(id: Int) {
        viewModelScope.launch {
            noteUseCases.getNoteByIdUseCase(id = id)
        }
    }

    fun insertNote(note: Note) {
        viewModelScope.launch {
            noteUseCases.insertNoteUseCase(note)
        }
    }
}