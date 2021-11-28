package com.altaie.notes.ui.add

import com.altaie.notes.model.data.doamin.Note
import com.altaie.notes.ui.base.BaseInteractionListener

interface AddInteractionListener : BaseInteractionListener {
    fun saveNote(note: Note)
}