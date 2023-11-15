package com.example.onenote.model

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAllNotesFlow(): Flow<List<Note>>


    suspend fun getAllNotes(): List<Note>


    suspend fun deleteNoteById(noteId: Int)





    suspend fun getNoteById(noteId: Int): Note?


    suspend fun insertNote(note: Note): Long




    suspend fun insertListOfNotesToRoom(notes: List<Note>): List<Long>
    suspend fun updateNote(note: Note)


    suspend fun deleteNote(note: Note)


    suspend fun deleteListOfNote(noteList: List<Note>)



}


