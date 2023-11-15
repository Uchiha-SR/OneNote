package com.example.onenote.model


import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.time.LocalDateTime
import java.util.Date
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    application: Application,
) : AndroidViewModel(application){

    private var noteId: Int? = null

    private val _note = MutableStateFlow(Note(dateTime = Date()))
    val note: StateFlow<Note> = _note

    fun insertListOfNote(notes: List<Note>, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insertListOfNotesToRoom(notes)

            notes.forEach { note ->
                val imageUris = note.image.filterNotNull()
                if (imageUris.isNotEmpty()) {
                    // update the note with the new image uri
                    noteRepository.updateNote(
                        note.copy(
                            id = note.id,
                            image = SaveSelectedImageUseCase(
                                context = getApplication(),
                                uris = imageUris,
                                noteId = note.id
                            )
                        )
                    )
                }
            }

            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }
    }

    fun getNoteById(noteId: Int?) = viewModelScope.launch(Dispatchers.IO) {
        this@NotesViewModel.noteId = noteId

        var note = note.value
        if (noteId != null) {
            note = noteRepository.getNoteById(noteId) ?: note
        }
        _note.update { note }
    }

    fun insertNote(
        title: String,
        description: String,
        images: List<Uri>,
        checklist: List<Todo>,
        onSuccess: () -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = _note.value.copy(
                title = title,
                note = description,
                checklist = checklist
            )

            val id: Int = noteRepository.insertNote(note).toInt()

            if (images.isNotEmpty()) {
                noteRepository.updateNote(
                    note.copy(
                        id = id,
                        // update the note with the new image uri
                        image = SaveSelectedImageUseCase(
                            context = getApplication(),
                            uris = images,
                            noteId = id
                        ),
                        // update the note with the new date time
                        dateTime = Date()
                    )
                )
            }

            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }
    }

    fun updateNote(
        title: String,
        description: String,
        images: List<Uri>,
        checklist: List<Todo>,
        onSuccess: (updated: Boolean) -> Unit,
    ) = viewModelScope.launch(Dispatchers.IO) {
        val newNote = note.value
        // retrieve the note from the database to check if the image has been modified
        val oldNote = noteRepository.getNoteById(newNote.id) ?: return@launch

        // exit the method if the note has not been modified
        if (
            oldNote.title == title &&
            oldNote.note == description &&
            oldNote.image == images &&
            checklist == oldNote.checklist &&
            oldNote.reminderDateTime == newNote.reminderDateTime
        ) {
            // Note has not been modified
            withContext(Dispatchers.Main) {
                onSuccess(false)
            }
            return@launch
        }

        noteRepository.updateNote(
            newNote.copy(
                title = title,
                note = description,
                dateTime = Date(),
                checklist = checklist
                // if the image has not been modified, use the old image uri
//                image = if (oldNote.image == newNote.image) {
//                    oldNote.image
//                } else if (newNote.image.filterNotNull().isNotEmpty()) {
//                    // if the image has been modified, save the new image uri
//                    SaveSelectedImageUseCase(
//                        getApplication(),
//                        newNote.image.filterNotNull(),
//                        newNote.id
//                    )
//                } else {
//                    emptyList()
//                }
            )
        )

        withContext(Dispatchers.Main) {
            onSuccess(true)
        }
    }

    fun updateReminderDateTime(dateTime: LocalDateTime?) {
        _note.update {
            it.copy(
                reminderDateTime = dateTime,
                isReminded = false
            )
        }
    }
}

object SaveSelectedImageUseCase {
    const val DIRECTORY = "image"

    /**
     * Returns the image file in the cache directory
     */
    fun image(context: Context, id: Int, index: Int) = File(
        File(context.externalCacheDir, DIRECTORY).apply {
            if (!exists()) {
                mkdirs()
            }
        },
        "image_${id}_($index).webp"
    )

    private fun imageFileUri(context: Context, uri: Uri, noteId: Int, index: Int): Uri? = try {
        // copy the image to cache directory because opening the
        // image uri after app restart doesn't work for external storage uri on android 11 and above
        val image = image(context, noteId, index)
        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(
                    context.contentResolver,
                    uri
                )
            )
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }
        // compress the image to 80% webp quality before saving
        bitmap.compress(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Bitmap.CompressFormat.WEBP_LOSSY
            } else {
                Bitmap.CompressFormat.WEBP
            },
            80,
            image.outputStream()
        )
        FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            image
        )
    } catch (_: Exception) {
        null
    }

    /**
     * Saves the selected image to the cache directory and returns the uri of the saved image
     */
    operator fun invoke(context: Context, uris: List<Uri>, noteId: Int): List<Uri?> {
        val imageFileUris = mutableListOf<Uri?>()
        uris.forEachIndexed { index, uri ->
            imageFileUris.add(imageFileUri(context, uri, noteId, index))
        }
        return imageFileUris.toList()
    }
}