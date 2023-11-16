package com.example.onenote.ui.presentation.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController


//refer tohttps://github.com/Mariamikv/Notes-App/
sealed class Screens(val route : String) {
    object Page : Screens("add page")
    object Canvas : Screens("canvas")
    object Camera : Screens("camera")

    object Speech: Screens("speech")

    object Stickynote: Screens("stickynote")
}

/*
data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Add,
    val route : String = ""
) {

    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                icon = Icons.Filled.Add,
                route = Screens.Page.route
            ),
       /*     BottomNavigationItem(
                label = "Search",
                icon = Icons.Filled.Search,
                route = Screens.Search.route
            ),
            BottomNavigationItem(
                label = "Profile",
                icon = Icons.Filled.AccountCircle,
                route = Screens.Profile.route
            ),  */
        )
    }
}

*/

    @Composable
    fun BottomBarwithFab(navController: NavHostController,) {
        BottomAppBar(
            actions = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Localized description")
                }
                IconButton(onClick = { /* doSomething() */ }) {

                    Icon(Icons.Filled.Mic, contentDescription = "Localized description")

                }
                IconButton(onClick = { /* doSomething() */ }) {

                    Icon(Icons.Filled.CameraAlt, contentDescription = "Localized description")
                }

                IconButton(onClick = { /* doSomething() */ }) {

                    Icon(Icons.Filled.Brush,contentDescription = null)
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.CreateNote.route) },
                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                ) {
                    Icon(Icons.Filled.Add, "Localized description")
                }
            },

        )

}