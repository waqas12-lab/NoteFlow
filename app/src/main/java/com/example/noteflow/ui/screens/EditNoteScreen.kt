package com.example.noteflow.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.noteflow.ui.components.formatDate
import com.example.noteflow.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(noteId: Int?, viewModel: NoteViewModel, onBack: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var pinned by remember { mutableStateOf(false) }
    var createdAt by remember { mutableStateOf<Long?>(null) }
    var updatedAt by remember { mutableStateOf<Long?>(null) }

    LaunchedEffect(noteId) {
        if (noteId != null) {
            viewModel.getNote(noteId)?.let { note ->
                title = note.title
                description = note.description
                pinned = note.isPinned
                createdAt = note.createdAt
                updatedAt = note.updatedAt
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (noteId == null) "Add Note" else "Edit Note", fontWeight = FontWeight.Bold) },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") } },
                actions = {
                    IconButton(onClick = { pinned = !pinned }) { Icon(if (pinned) Icons.Filled.PushPin else Icons.Outlined.PushPin, contentDescription = "Pin") }
                    IconButton(onClick = { viewModel.saveNote(noteId, title, description, pinned); onBack() }) { Icon(Icons.Default.Check, contentDescription = "Save") }
                }
            )
        }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            OutlinedTextField(value = title, onValueChange = { title = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Title") }, singleLine = true, shape = MaterialTheme.shapes.large)
            OutlinedTextField(value = description, onValueChange = { description = it }, modifier = Modifier.fillMaxWidth().weight(1f), label = { Text("Description") }, shape = MaterialTheme.shapes.large)
            createdAt?.let { Text("Created: ${formatDate(it)}", style = MaterialTheme.typography.labelMedium) }
            updatedAt?.let { Text("Updated: ${formatDate(it)}", style = MaterialTheme.typography.labelMedium) }
        }
    }
}
