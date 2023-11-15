package com.example.onenote.model

import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override fun getAllNotesFlow(): Flow<List<Note>> {
       return  noteDao.getAllNotesFlow()
    }

    override suspend fun getAllNotes(): List<Note> {
       return   noteDao.getAllNotes()
    }

    override suspend fun deleteNoteById(noteId: Int) {
        noteDao.deleteNoteById(noteId)
    }


    override suspend fun getNoteById(noteId: Int): Note? {
        return  noteDao.getNoteById(noteId)
    }

    override suspend fun insertNote(note: Note): Long {
        return noteDao.insertNote(note)
    }



    override suspend fun updateNote(note: Note) {
       // val getOldNote = getNoteById(note.id)
        noteDao.updateNote(note)
    }

    override suspend fun insertListOfNotesToRoom(notes: List<Note>): List<Long> = noteDao.insertListOfNotes(notes)

    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)



    override suspend fun deleteListOfNote(noteList: List<Note>) =noteDao.deleteListOfNote(noteList)
}



