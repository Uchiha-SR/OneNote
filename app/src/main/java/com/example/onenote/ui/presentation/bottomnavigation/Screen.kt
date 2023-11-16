package com.example.onenote.ui.presentation.bottomnavigation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("homw_screen")
    object CreateNote: Screen("create_note")
  //  object SplashScreen: Screen("splash_screen")
}
