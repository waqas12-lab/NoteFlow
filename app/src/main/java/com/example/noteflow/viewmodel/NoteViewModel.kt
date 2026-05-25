package com.example.noteflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.noteflow.data.repository.NoteRepository
import com.example.noteflow.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class NotesUiState(
    val notes: List<Note> = emptyList(),
    val searchQuery: String = ""
)

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {
    private val searchQuery = MutableStateFlow("")

    val uiState: StateFlow<NotesUiState> = combine(repository.allNotes, searchQuery) { notes, query ->
        val filtered = if (query.isBlank()) notes else notes.filter {
            it.title.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
        }
        NotesUiState(notes = filtered, searchQuery = query)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), NotesUiState())

    fun updateSearchQuery(query: String) { searchQuery.value = query }

    suspend fun getNote(id: Int): Note? = repository.getNoteById(id)

    fun saveNote(id: Int?, title: String, description: String, pinned: Boolean) {
        if (title.isBlank() && description.isBlank()) return
        viewModelScope.launch {
            val now = System.currentTimeMillis()
            if (id == null) {
                repository.addNote(Note(title = title.trim(), description = description.trim(), isPinned = pinned, createdAt = now, updatedAt = now))
            } else {
                val old = repository.getNoteById(id) ?: return@launch
                repository.updateNote(old.copy(title = title.trim(), description = description.trim(), isPinned = pinned, updatedAt = now))
            }
        }
    }

    fun togglePin(note: Note) = viewModelScope.launch {
        repository.updateNote(note.copy(isPinned = !note.isPinned, updatedAt = System.currentTimeMillis()))
    }

    fun deleteNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }
}

class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = NoteViewModel(repository) as T
}
