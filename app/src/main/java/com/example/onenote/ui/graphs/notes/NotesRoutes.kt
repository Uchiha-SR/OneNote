package com.example.onenote.ui.graphs.notes

sealed class NotesRoutes(val route: String) {
    object NotesScreen : NotesRoutes("notes_screen")
    object NoteViewerScreen : NotesRoutes("note_viewer")
}
