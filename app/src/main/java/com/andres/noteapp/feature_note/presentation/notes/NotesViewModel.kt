package com.andres.noteapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andres.noteapp.feature_note.domain.model.Note
import com.andres.noteapp.feature_note.domain.use_case.NoteUseCases
import com.andres.noteapp.feature_note.domain.util.NoteOrder
import com.andres.noteapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
) : ViewModel() {

    private var recentlyDeletedNote: Note? = null
    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.DeleteNote -> {
                deleteNote(event.note)
                recentlyDeletedNote = event.note
            }
            is NotesEvent.Order -> {
                if (
                    state.value.noteOrder == event.noteOrder &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) return
                getNotes(event.noteOrder)
            }
            is NotesEvent.RestoreNote -> {
                recentlyDeletedNote?.let { insertNote(note = it) }
                recentlyDeletedNote = null
            }
            is NotesEvent.ToggleOrderSection -> {
                toggleOrder()
            }
        }
    }

    private fun toggleOrder() {
        _state.value = _state.value.copy(
            isOrderSectionVisible = !state.value.isOrderSectionVisible
        )
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotesUseCase(noteOrder = noteOrder).onEach { listNotes ->
            _state.value = _state.value.copy(
                listNotes = listNotes,
                noteOrder = noteOrder
            )
        }.launchIn(viewModelScope)
    }

    fun getNoteById(id: Int) {
        viewModelScope.launch {
            noteUseCases.getNoteByIdUseCase(id = id)
        }
    }

    private fun insertNote(note: Note) {
        viewModelScope.launch {
            noteUseCases.insertNoteUseCase(note)
        }
    }

    private fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteUseCases.deleteNoteUseCase(note)
        }
    }
}