package com.example.noteflow.data.repository

import com.example.noteflow.data.local.NoteDao
import com.example.noteflow.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val dao: NoteDao) {
    val allNotes: Flow<List<Note>> = dao.getAllNotes()
    suspend fun getNoteById(id: Int): Note? = dao.getNoteById(id)
    suspend fun addNote(note: Note) = dao.insertNote(note)
    suspend fun updateNote(note: Note) = dao.updateNote(note)
    suspend fun deleteNote(note: Note) = dao.deleteNote(note)
}
