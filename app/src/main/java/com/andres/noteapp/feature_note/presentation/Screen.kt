package com.andres.noteapp.feature_note.presentation

sealed class Screen(val route: String) {
    object NotesScreen: Screen(route = "notes")
    object AddEditScreen: Screen(route = "add_edit")
}
