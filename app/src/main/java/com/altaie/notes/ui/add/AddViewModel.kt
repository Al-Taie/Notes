package com.altaie.notes.ui.add

import com.altaie.notes.R
import com.altaie.notes.model.Repository
import com.altaie.notes.model.data.doamin.Note
import com.altaie.notes.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import java.util.*

class AddViewModel : BaseViewModel(), AddInteractionListener {
    val title = MutableStateFlow<String?>(null)
    val content = MutableStateFlow<String?>(null)
    val color = MutableStateFlow(R.color.white)
    val tags = MutableStateFlow<List<String>>(emptyList())
    val currentNote = MutableStateFlow<Note?>(null)
    val date = MutableStateFlow(Date())

    init {
        launcher { title.collect { emitNote() } }
        launcher { content.collect { emitNote() } }
    }

    override fun saveNote(note: Note) = launcher {  Repository.insert(note) }

    private suspend fun emitNote() {
        currentNote.emit(
            Note(
                title = title.value ?: "",
                content = content.value ?: "",
                tags = tags.value,
                color = color.value
            )
        )
    }
}
