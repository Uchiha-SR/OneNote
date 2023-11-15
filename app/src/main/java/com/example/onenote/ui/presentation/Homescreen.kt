package com.example.onenote.ui.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.onenote.ui.presentation.component.TopAppBarComponent
import com.example.onenote.ui.presentation.notes.TopBar



@Preview
@Composable
fun Homescreen() {

    Column {
        TopAppBarComponent()
    }

}