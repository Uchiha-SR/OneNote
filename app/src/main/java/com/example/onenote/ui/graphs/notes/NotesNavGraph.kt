package com.example.onenote.ui.graphs.notes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.onenote.model.Note
import com.example.onenote.ui.graphs.Graph

fun NavGraphBuilder.notesNavGraph(navHostController: NavHostController) {
    var noteData: Note? = null
    navigation(
        startDestination = NotesRoutes.NotesScreen.route,
        route = Graph.NOTE
    ) {

        composable(NotesRoutes.NotesScreen.route) {
            NotesHomeScreen(onNoteClick = { note ->
                noteData = note
                navHostController.navigate(NotesRoutes.NoteViewerScreen.route)
            }, onNewNoteClick = {
                noteData = null
                navHostController.navigate(NotesRoutes.NoteViewerScreen.route)
            })
        }
        composable(NotesRoutes.NoteViewerScreen.route) {
            NoteViewerScreen(note = noteData, onBackPress = {
                navHostController.popBackStack()
            })
        }

    }
}