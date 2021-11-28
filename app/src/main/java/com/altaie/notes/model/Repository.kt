package com.altaie.notes.model

import com.altaie.notes.model.data.doamin.Note
import com.altaie.notes.model.data.database.NoteDatabase

object Repository {
    private val dao = NoteDatabase.getInstance.noteDao()

    suspend fun insert(note: Note) = dao.insert(note)

    suspend fun delete(note: Note) = dao.delete(note)

    suspend fun update(note: Note) = dao.update(note)

    fun searchByTitleOrContent(query: String) = dao.searchByTitleOrContent(query)

    fun getAllNotes() = dao.getAllNotes()

    fun getNote(noteID: Long) = dao.getNotes(noteID)
}
