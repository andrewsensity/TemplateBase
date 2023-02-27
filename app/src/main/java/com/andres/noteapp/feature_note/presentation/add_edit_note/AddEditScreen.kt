package com.andres.noteapp.feature_note.presentation.add_edit_note

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.andres.noteapp.feature_note.domain.model.Note
import com.andres.noteapp.feature_note.presentation.Screen
import com.andres.noteapp.feature_note.presentation.add_edit_note.components.CustomTextField
import com.andres.noteapp.ui.theme.*
import java.util.*

@Composable
fun AddEditScreen(
    navController: NavController,
    addEditNoteViewModel: AddEditNoteViewModel = hiltViewModel(),
) {
    val size = 60.dp
    val radio = 80f
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    addEditNoteViewModel.insertNote(
                        Note(
                            title = title,
                            content = content,
                            timeStamp = Date().time,
                            color = Violet.toArgb()
                        )
                    )
                    navController.navigate(Screen.NotesScreen.route)
                },
                containerColor = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = null)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(BabyBlue)
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Canvas(modifier = Modifier.size(size)) {
                    drawCircle(Violet, radius = radio)
                }
                Canvas(modifier = Modifier.size(size)) {
                    drawCircle(LightGreen, radius = radio)
                }
                Canvas(modifier = Modifier.size(size)) {
                    drawCircle(RedOrange, radius = radio)
                }
                Canvas(modifier = Modifier.size(size)) {
                    drawCircle(BabyBlue, radius = radio)
                }
                Canvas(modifier = Modifier.size(size)) {
                    drawCircle(RedPink, radius = radio)
                }
            }
            CustomTextField(
                value = title,
                onValueChange = {
                    title = it
                },
                label = "Choose a title"
            )
            CustomTextField(
                value = content,
                onValueChange = {
                    content = it
                },
                label = "Enter some content"
            )
        }
    }
}

@Preview
@Composable
fun AddEditScreenPrev() {
    AddEditScreen(rememberNavController())
}