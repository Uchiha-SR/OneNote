package com.example.onenote.ui.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.ContentAlpha
import com.example.onenote.R


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent() {
    var isGridView by rememberSaveable { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "OneNote",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,

                    )
                },
               /* navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },*/
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        Row(Modifier.padding(innerPadding).padding(start = 40.dp)) {
            MenuBar()
            LayoutToggleButton(
                isGridView = isGridView,
                onToggleClick = { isGridView = !isGridView }
            )


        }
    }


}

@Composable
fun MenuBar() {

    var switchCheckedState by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
        ,

    ) {
        Switch(
            checked = switchCheckedState,
            onCheckedChange = { switchCheckedState = it },
            modifier = Modifier.scale(1.5f),
            thumbContent = {

                  if (switchCheckedState) Text("Settings") else Text(text = "Home")


            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Green,
                checkedIconColor = Color.DarkGray,
                uncheckedThumbColor = Color.Red,
                uncheckedIconColor = Color.LightGray,
                disabledCheckedThumbColor = Color.Green.copy(alpha = ContentAlpha.disabled),
                disabledUncheckedThumbColor = Color.Red.copy(alpha = ContentAlpha.disabled),
            )
        )
    }




}

            @Composable
            fun LayoutToggleButton(
                isGridView: Boolean,
                onToggleClick: () -> Unit,
            ) {
                val customGridViewImage = painterResource(R.drawable.grid_icon)
                val customAgendaViewImage = painterResource(R.drawable.list_icon)

                val imageToShow = if (isGridView) customGridViewImage else customAgendaViewImage

                val tint = LocalContentColor.current

                IconButton(
                    onClick = onToggleClick,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Image(imageToShow, contentDescription = "Toggle Button", colorFilter = ColorFilter.tint(tint))
                }
            }

