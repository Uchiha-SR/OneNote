package com.example.onenote.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.onenote.ui.presentation.bottomnavigation.BottomBarwithFab
import com.example.onenote.ui.presentation.component.TopAppBarComponent



@Composable
fun HomeScreen(navController: NavHostController) {


        Scaffold(
            topBar = {
                TopAppBarComponent() },
            bottomBar = { BottomBarwithFab(navController) }
        ){
            paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = "Rest of the app UI")
            }
        }


}