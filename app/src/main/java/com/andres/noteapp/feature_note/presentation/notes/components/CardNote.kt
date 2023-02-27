package com.andres.noteapp.feature_note.presentation.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andres.noteapp.feature_note.domain.model.Note

@Composable
fun CardNote(
    note: Note,
    onCLickDelete: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 10.dp, bottomEnd = 10.dp, bottomStart = 10.dp
                )
            )
            .clip(CutCornerShape(topEnd = 30.dp))
            .background(Color(0xFF3DFAC9))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(modifier = Modifier.weight(0.8f)) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = note.content,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Normal,
                        lineHeight = 16.sp
                    )
                )
            }
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onCLickDelete() },
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = Color.Black
            )
        }
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(
                    Color.Gray.copy(alpha = 0.4f),
                    RoundedCornerShape(bottomStart = 8.dp)
                )
                .align(Alignment.TopEnd)
        )
    }
}