package com.altaie.notes.ui.edit

import com.altaie.notes.model.data.doamin.Note
import com.altaie.notes.ui.base.BaseInteractionListener

interface EditInteractionListener : BaseInteractionListener {
    fun saveNote(note: Note)
    fun loadNote(noteID: Long)
    fun deleteNote()
}