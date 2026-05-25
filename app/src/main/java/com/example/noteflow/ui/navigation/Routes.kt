package com.example.noteflow.ui.navigation

object Routes {
    const val HOME = "home"
    const val EDIT = "edit?noteId={noteId}"
    fun edit(noteId: Int? = null) = "edit?noteId=${noteId ?: -1}"
}
