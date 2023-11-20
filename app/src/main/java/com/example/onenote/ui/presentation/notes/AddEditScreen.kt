package com.example.onenote.ui.presentation.notes

/*
@Composable
fun NoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    val noteTitle = viewModel.noteTitle.value

    val noteContent = viewModel.noteContent.value



    val uiEvent = viewModel.event

    val coroutineScope = rememberCoroutineScope()



    LaunchedEffect(key1 = true) {
        // Observe the ui events.
        uiEvent.collectLatest { event ->
            when (event) {
                is AddEditNoteViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is UiEvent.SaveNote -> {
                    navController.navigateUp() // Navigate to the previous screen.
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(NoteEvent.SaveNote)
            }, backgroundColor = MaterialTheme.colors.primary) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save note",
                    tint = MaterialTheme.colors.background
                )
            }
        },
        scaffoldState = scaffoldState,
        backgroundColor = backgroundColor.value
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row {
                ColorBubbles(noteColor = viewModel.noteColor.value) { color ->
                    coroutineScope.launch {
                        backgroundColor.animateTo(
                            targetValue = color,
                            animationSpec = tween(
                                durationMillis = 500
                            )
                        )
                    }

                    viewModel.onEvent(NoteEvent.ChangeColor(color.toArgb()))
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Note Title
            NoteTextField(
                text = noteTitle.text,
                hint = noteTitle.hint,
                isHintVisible = noteTitle.isHintVisible,
                textStyle = MaterialTheme.typography.h5,
                isSingleLine = true,
                testTag = TITLE_TEXT_FIELD,
                onValueChange = { text ->
                    viewModel.onEvent(NoteEvent.ChangeTitle(text))
                },
                onFocusStateChanged = { focusState ->
                    viewModel.onEvent(NoteEvent.ChangeTitleFocus(focusState))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )

            // Note Content
            NoteTextField(
                text = noteContent.text,
                hint = noteContent.hint,
                isHintVisible = noteContent.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                isSingleLine = false,
                testTag = CONTENT_TEXT_FIELD,
                onValueChange = { text ->
                    viewModel.onEvent(NoteEvent.ChangeContent(text))
                },
                onFocusStateChanged = { focusState ->
                    viewModel.onEvent(NoteEvent.ChangeContentFocus(focusState))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        }
    }
}

/**
 * Composable for rendering color bubble(note background color selector).
 */
@Composable
fun ColorBubbles(
    modifier: Modifier = Modifier,
    noteColor: Int,
    onColorChange: (Color) -> Unit
) {
    Note.noteColors.forEach { color ->
        Box(
            modifier = modifier
                .padding(8.dp)
                .size(50.dp)
                .shadow(15.dp, CircleShape)
                .clip(CircleShape)
                .background(
                    color = color,
                    shape = CircleShape
                )
                .border(
                    width = 2.dp,
                    color = if (noteColor == color.toArgb()) {
                        Color.DarkGray
                    } else {
                        Color.Transparent
                    },
                    shape = CircleShape
                )
                .clickable {
                    onColorChange(color)
                }
        )
    }
}
*/
