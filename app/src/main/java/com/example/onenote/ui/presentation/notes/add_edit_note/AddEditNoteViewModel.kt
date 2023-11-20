package com.example.onenote.ui.presentation.notes.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onenote.model.Note

import com.example.onenote.model.NoteRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/*
@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteRepositoryImpl,
    savedStateHandle: SavedStateHandle
): ViewModel(){


    //exposing all the states required
    private val _noteTitle = mutableStateOf(NoteTextFieldState(hint = "Enter title"))
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldState(hint = "Enter some content"))
    val noteContent: State<NoteTextFieldState> = _noteContent

 /*   private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor  */

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("NoteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNoteById(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )

                        _noteContent.value = noteContent.value.copy(
                            text = note.note,
                            isHintVisible = false
                        )

                            //   _noteColor.value = note.
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            //if title is entered, then only update it
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(text = event.newTitle)
            }

            //when we are not focused on the text-field and the text is empty, we will show the hint
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteTitle.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(text = event.newContent)
            }

            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteContent.value.text.isBlank()
                )
            }

        /*    is AddEditNoteEvent.ChangeColor -> {
                _noteColor.value = event.color
            }  */

            is AddEditNoteEvent.SaveNote -> {
                //call addNote Usecase
                viewModelScope.launch {
                    try {
                        noteUseCases.insertNote(
                            Note(
                                title = noteTitle.value.text,
                                note = noteContent.value.text,
                                dateTime = System.currentTimeMillis(),
                                id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(message = e.message ?: "Couldn't Save Note")
                        )
                    }
                }
            }

        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }


*/