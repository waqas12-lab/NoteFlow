package com.example.noteflow.di

import android.content.Context
import com.example.noteflow.data.local.NoteDatabase
import com.example.noteflow.data.repository.NoteRepository

object AppContainer {
    fun provideRepository(context: Context): NoteRepository {
        return NoteRepository(NoteDatabase.getDatabase(context).noteDao())
    }
}
