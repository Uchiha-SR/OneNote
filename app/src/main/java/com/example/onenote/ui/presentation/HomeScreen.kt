package com.example.onenote.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.onenote.ui.presentation.bottomnavigation.BottomBarwithFab
import com.example.onenote.ui.presentation.component.TopAppBarComponent



@Preview
@Composable
fun HomeScreen() {


        Scaffold(
            topBar = {
                TopAppBarComponent() },
            bottomBar = { BottomBarwithFab() }
        ){
                paddingValues ->
            // rest of the app's UI
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Rest of the app UI")
            }
        }


}