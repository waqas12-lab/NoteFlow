package com.example.noteflow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteflow.di.AppContainer
import com.example.noteflow.ui.screens.EditNoteScreen
import com.example.noteflow.ui.screens.HomeScreen
import com.example.noteflow.viewmodel.NoteViewModel
import com.example.noteflow.viewmodel.NoteViewModelFactory

@Composable
fun NoteFlowNavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val viewModel: NoteViewModel = viewModel(factory = NoteViewModelFactory(AppContainer.provideRepository(context)))

    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(viewModel, onAdd = { navController.navigate(Routes.edit()) }, onEdit = { navController.navigate(Routes.edit(it)) })
        }
        composable(
            route = Routes.EDIT,
            arguments = listOf(navArgument("noteId") { type = NavType.IntType; defaultValue = -1 })
        ) { entry ->
            val noteId = entry.arguments?.getInt("noteId")?.takeIf { it != -1 }
            EditNoteScreen(noteId = noteId, viewModel = viewModel, onBack = { navController.popBackStack() })
        }
    }
}
