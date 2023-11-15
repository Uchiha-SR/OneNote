package com.example.onenote.ui.graphs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun RootNavigationGraph(navHostController: NavHostController, context: Context) {
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.SPLASH
    ) {
        authNavGraph(navHostController, context)
        notesNavGraph(navHostController = navHostController)

        composable(Graph.SPLASH) {
            SplashScreen(navigate = { isUserExist ->
                if (isUserExist) {
                    navHostController.popBackStack()
                    navHostController.navigate(NotesRoutes.NotesScreen.route)
                } else {
                    navHostController.navigate(Graph.AUTHENTICATION)
                }
            })
        }
    }
}